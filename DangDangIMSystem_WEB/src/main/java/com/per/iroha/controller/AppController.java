package com.per.iroha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping(value = "chat/{id}", method = RequestMethod.GET)
    public String toWeChat(@PathVariable int id){
        return "chat";
    }
}
