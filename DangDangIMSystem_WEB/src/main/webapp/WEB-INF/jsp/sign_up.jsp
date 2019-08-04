<%@ page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DangDangIMSystem</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.placeholder.min.js"></script>
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Main JS -->
    <script src="js/main.js"></script>

</head>
<body>
<%
    if(session.getAttribute("registerMessage") == null){

    }else{
        String message = session.getAttribute("registerMessage").toString();{%>
<script type="text/javascript">
    alert("<%=message%>");
</script>
<%      session.setAttribute("registerMessage",null);
}
}%>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <!-- Start Sign In Form -->
            <form action="/register"  method="post" class="fh5co-form animate-box"  data-animate-effect="fadeIn" onsubmit="return checkForm()">
                <h2>Sign Up</h2>
                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="realName" class="sr-only">Nickname</label>
                    <input type="text" class="form-control" name="realName" id="realName" placeholder="RealName">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="rePassword" class="sr-only">Re-type Password</label>
                    <input type="password" class="form-control" name="rePassword" id="rePassword" placeholder="Re-type Password">
                </div>
                <div class="form-group">
                    <p>Already registered? <a onclick="return signIn()">Sign In</a></p>
                </div>
                <div class="form-group">
                    <input type="submit" value="Sign Up" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->
            <form id="signIn" action="/index" method="post"></form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function signIn() {
        document.getElementById("signIn").submit();
    }

    function checkForm() {
        var errString="";//用户对错误的字符串信息进行填写操作
        var username=document.getElementById("username").value;
        var password=document.getElementById("password").value;
        var realName=document.getElementById("realName").value;
        var rePassword=document.getElementById("rePassword").value;
        /*对用户名当中的数据进行校验操作*/
        if(username=="" || username==null)
        {
            errString="请输入用户名";
        }else if(realName=="" || realName==null)
        {
            errString="请输入真实姓名";
        }else if(password=="" || password==null)
        {
            errString="请输入密码";
        }else if(rePassword=="" || rePassword==null)
        {
            errString="请再次输入密码";
        }else if(!/^\w{4,25}$/.test(password))
        {
            errString="输入的密码必须是字母或数字，且长度在4到25位之间"
        }
        else if(password != rePassword)
        {
            errString="两次输入密码不同，请检查"
        }
        if(errString=="")/*表明没有错误的信息*/
            return true;
        else
        {
            alert(errString);

            return false;
        }
    }
</script>
</body>
</html>