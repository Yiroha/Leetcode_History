package com.per.iroha.controller;

import com.alibaba.fastjson.JSON;
import com.per.iroha.model.User;
import com.per.iroha.redis.RedisMq;
import com.per.iroha.service.UserService;
import com.per.iroha.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisMq redisMq;

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/signup")
    public String toSignup(){
        return "sign_up";
    }

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        User user = new User();
        user.setUsername(httpServletRequest.getParameter("username"));
        user.setPassword(httpServletRequest.getParameter("password"));
        HttpSession session = httpServletRequest.getSession();

        if(userService.hasUser(user.getUsername())){
            User userInfo = userService.findByName(user.getUsername());
            if(session.getAttribute(user.getUsername()) != null){

                session.setAttribute("message","用户已经登录");
                httpServletRequest.getRequestDispatcher("index").forward(httpServletRequest,httpServletResponse);

            }else{
                //md5
                int salt = (int)session.getAttribute("salt");
                if(userService.md5Password(user,salt)){
                    session.setAttribute("username",user.getUsername());

                    //登录信息存入消息队列
                    redisMq.push(JSON.toJSONString(userInfo));
                    Cookie cookie = new Cookie("userId", userInfo.getUserId().toString());
                    httpServletResponse.addCookie(cookie);

                    session.setAttribute("LoginState",true);
//                    httpServletResponse.addCookie(new Cookie("username",userInfo.getUsername()));
                    //签到
                    userService.checkIn(userInfo.getUserId());

                    httpServletResponse.sendRedirect("/app/chat/" + userInfo.getUserId());
                }else{
                    session.setAttribute("message","登录失败！密码错误");
                    httpServletResponse.sendRedirect("index");
                }
            }
        }else{
            session.setAttribute("message","登录失败！不存在用户名");
            httpServletResponse.sendRedirect("index");
        }
    }

    @RequestMapping(value = "/register")
    public void register(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        User user = new User();
        user.setUsername(httpServletRequest.getParameter("username"));
        user.setPassword(httpServletRequest.getParameter("password"));
        user.setRealName(httpServletRequest.getParameter("realName"));
        HttpSession session = httpServletRequest.getSession();

        if(userService.hasUser(user.getUsername())){
            session.setAttribute("registerMessage","注册失败，用户名已存在");
            httpServletResponse.sendRedirect("signup");
        }else{
            userService.register(user);
            session.setAttribute("message","注册成功");
            httpServletResponse.sendRedirect("index");
        }
    }
}
