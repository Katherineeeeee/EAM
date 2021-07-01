<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lt" uri="ListTld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>企业资产管理系统</title>
    <link rel="stylesheet" href="/static/css/element.css"/>
    <link rel="stylesheet" href="/static/css/header.css"/>
    <link rel="stylesheet" href="/static/css/table.css"/>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/element.js"></script>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/base64.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
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
    <div id="manager" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="managerHeader.jsp">
                <c:param name="url" value="uses"/>
            </c:import>
            <el-main style="height: 88%; display: flex; flex-direction: column;">
                <div style="margin-top: 10px; display: flex; flex-direction: row;">
                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                        <p style="font-size:16px">
                            第${curPage}页, 共${sumPage}页
                        </p>
                        <el-link style="font-size:16px" type="primary" href="/api/manager/applications/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/10" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                        <el-link style="font-size:16px" type="primary" href="/api/manager/applications/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/10" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                    </div>
                </div>
                <div style="margin-top: 20px; height: 85%;">
                    <table style="width: 100%;">
                        <tr>
                            <th>申请ID</th>
                            <th>设备名称</th>
                            <th>申请人</th>
                            <th>发起时间</th>
                            <th>请求</th>
                            <th>审批</th>
                        </tr>
                        <lt:AMListTag/>
                    </table>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
        <c:import url="changeInfo.jsp"/>
    </div>
</body>
    <script src="/static/js/applicationManage.js"></script>
</html>
