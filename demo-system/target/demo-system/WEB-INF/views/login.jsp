<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/7
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>继续教育学院综合管理系统-登陆</title>
    <link rel=”icon” href="img/favicon.ico" mce_href="img/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="img/favicon.ico" mce_href="img/favicon.ico" type="image/x-icon">
    <%--<link href="css/login/login.css" rel="stylesheet">--%>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/login/login.js"></script>
</head>
<body>
<div class="content">
    <%--<div class="left-content">--%>
        <%--<div class ="left-logo">--%>
            <%--<img src="images/logo.png" alt="川师图标"/>--%>
        <%--</div>--%>
        <%--<div class="left-font">--%>
            <%--继续教育学院综合管理系统--%>
        <%--</div>--%>

    <%--</div>--%>

    <%--<div class="middle-content">--%>
        <%--<div class ="left-fgx">--%>
            <%--<img src="images/login/fgx.png" alt="分割线"/>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="right-content">
        <form action="">
            <label>用户名:</label><br />
            <input type="text" id="userName" placeholder="请输入用户名" />
            <br />
            <label>密&#12288;码:</label><br />
            <input type="password" id="passwd" placeholder="请输入密码" />
            <br />
            <%--<label>验证码:</label><br />--%>
            <%--<input type="text" id="v_code" placeholder="请输入验证码" />--%>
            <%--<img class="v-code-img" id="imgObj" src="login/vcodeImg" onclick="changeImg()"/>--%>
            <%--<a class="v-code-but" onclick="changeImg()">换一换</a>--%>
            <%--<br />--%>
            <button id="login_but" class="login-button" type="button" onclick="sub('<%=basePath%>')" >登&#12288;录</button>
        </form>
    </div>
</div>


<script>
    //实现Enter键登录
    $("body").keydown(function(event) {
        if (event.keyCode == 13) {//keyCode=13是回车键
            $("#login_but").click();
        }
    });
</script>
</body>
</html>
