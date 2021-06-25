<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lt" uri="ListTld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>企业资产管理系统</title>
    <link rel="stylesheet" href="/static/css/element.css"/>
    <link rel="stylesheet" href="/static/css/header.css"/>
    <link rel="stylesheet" href="/static/css/table.css"/>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/element.js"></script>
    <script src="/static/js/jquery.js"></script>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }
        body {
            background-color: #FBFBFB;
        }
    </style>
    <script>
        window.onload = function() {
            document.getElementsByTagName("body")[0].style.height = window.innerHeight + "px";
            document.getElementsByTagName("body")[0].style.width = window.innerWidth + "px";
        }
    </script>
</head>
<body>
    <div id="user" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="userHeader.jsp">
                <c:param name="url" value="properties"/>
            </c:import>
            <el-main style="height: 88%; display: flex; flex-direction: column; justify-content: flex-start;">
                <div style="margin-top: 10px; display: flex; flex-direction: row;">
                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                        <p style="font-size:16px">
                            第${curPage}页, 共${sumPage}页
                        </p>
                        <el-link style="font-size:16px" type="primary" href="/api/user/properties/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/10" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                        <el-link style="font-size:16px" type="primary" href="/api/user/properties/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/10" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                    </div>
                </div>
                <div style="margin-top: 20px; height: 85%;">
                    <table style="width: 100%;">
                        <tr>
                            <th>编号</th>
                            <th>设备名称</th>
                            <th>品牌</th>
                            <th>型号</th>
                            <th>规格</th>
                            <th>采购日期</th>
                            <th>领用</th>
                        </tr>
                        <lt:UPListTag/>
                    </table>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
    </div>
</body>
<script src="/static/js/userViewProperties.js"></script>
</html>
