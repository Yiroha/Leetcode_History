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
    <script src="js/md5.js"></script>

</head>
<body>
<%
    if(session.getAttribute("message") == null){

    }else{
        String message = session.getAttribute("message").toString();{%>
        <script type="text/javascript">
            alert("<%=message%>");
        </script>
<%      session.setAttribute("message",null);
    }
}%>

<%
    Random random = new Random();
    int salt = random.nextInt(1000);
    //加盐产生随机数，和密码一起生成MD5
    request.getSession().setAttribute("salt", salt);

%>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <!-- Start Sign In Form -->
            <form action="/login" method="post" class="fh5co-form animate-box" data-animate-effect="fadeIn" onsubmit="return checkForm()">
                <h2>Sign In</h2>
                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <p>Not registered? <a onclick="return signUp()">Sign Up</a></p>
                </div>
                <div class="form-group">
                    <input type="submit" id = "SignIn" value="Sign In" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->
            <form id="signUp" action="/signup" method="post"></form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function signUp() {
        document.getElementById("signUp").submit();
    }

    function checkForm() {
        var errString="";//用户对错误的字符串信息进行填写操作
        var username=document.getElementById("username").value;
        var password=document.getElementById("password").value;

        /*对用户名当中的数据进行校验操作*/
        if(username=="" || username==null)
        {
            errString=errString+"请输入用户名";
        }else if(password=="" || password==null)
        {
            errString=errString+"请输入密码";
        }else if(!/^\w{4,25}$/.test(password))
        {
            errString=errString+"输入的密码必须是字母或数字，且长度在4到25位之间"
        }

        if(errString==""){
            var hash = MD5(password + ${sessionScope.salt});
            document.getElementById("password").value = hash;
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
