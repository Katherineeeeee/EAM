<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>企业资产管理系统</title>
    <link rel="stylesheet" href="/static/css/element.css"/>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/element.js"></script>
    <script src="/static/js/jquery.js"></script>
</head>
<body>
    <div id="app" style="display: flex; justify-content: center; align-items: center">
        <el-card
                style="width: 500px; height: 300px;"
                v-loading="loading"
                element-loading-text="退出成功,返回登录页面">
        </el-card>
    </div>
</body>
    <script>
        var vm = new Vue({
            el: "#app",
            data() {
                return {
                    loading: true
                }
            },
            methods: {
            }
        });
        window.onload = function(){
            setTimeout(function() {
                window.location.href="/";
            }, 3000);
        };
    </script>
</html>
