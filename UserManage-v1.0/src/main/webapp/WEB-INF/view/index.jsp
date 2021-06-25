<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>企业资产管理系统</title>
    <link rel="stylesheet" href="/static/css/style.css" />
    <link rel="stylesheet" href="/static/css/element.css"/>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/element.js"></script>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/base64.min.js"></script>
</head>
<body class="container">
<div id="app">
    <div class="login-form" style="width: 400px; height: 200px; margin-top: 150px;">
        <h1 style="color: #FFFFFF;" align="center">企业资产管理系统</h1>
        <p style="color: #FFFFFF;">用户名: </p>
        <el-input prefix-icon="el-icon-user" v-model="username" placeholder="用户名"></el-input>
        <p style="color: #FFFFFF;">密码: </p>
        <el-input prefix-icon="el-icon-lock" v-model="password" placeholder="密码" show-password></el-input>
        <div style="margin-top: 20px; display: flex; justify-content: space-around;">
            <el-button style="width:40%;margin-bottom:30px;" type="info" @click="userLogin" round>用户登录</el-button>
            <el-button style="width:40%;margin-bottom:30px;" type="info" @click="managerLogin" round>管理员登录</el-button>
        </div>
    </div>
</div>

</body>
<script src="/static/js/login.js"></script>
</html>


