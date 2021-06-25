<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lt" uri="ListTld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <script src="/static/js/base64.min.js"></script>
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
    <div id="manager" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="managerHeader.jsp">
                <c:param name="url" value="properties"/>
            </c:import>
            <el-main style="height: 88%; display: flex; flex-direction: column;">
                <div style="margin-top: 20px; width:100%; display: flex; flex-direction: row;">
                    <el-row :gutter="15" style="width: 100%;">
                        <el-col :span="10">
                            <el-card>
                                <div slot="header">
                                    <span>资产详情</span>
                                </div>
                                <div style="display: flex; flex-direction: column;">
                                    <div style="margin-bottom: 30px;">设备编号: ${requestScope.get("id")}</div>
                                    <div style="margin-bottom: 30px;">设备名称: ${requestScope.get("name")}</div>
                                    <div style="margin-bottom: 30px;">设备品牌: ${requestScope.get("brand")}</div>
                                    <div style="margin-bottom: 30px;">设备型号: ${requestScope.get("model")}</div>
                                    <div style="margin-bottom: 30px;">设备规格: ${requestScope.get("spec")}</div>
                                    <div style="margin-bottom: 30px;">采购日期: ${requestScope.get("time")}</div>
                                    <div style="margin-bottom: 30px;">状态: ${requestScope.get("status")}</div>
                                </div>
                            </el-card>
                        </el-col>
                        <el-col :offset="2" :span="12">
                            <el-card>
                                <div slot="header" style="margin-top: 10px; display: flex; flex-direction: row;">
                                    <span>使用历史</span>
                                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                                        <p style="font-size:16px">
                                            第${curPage}页, 共${sumPage}页
                                        </p>
                                        <el-link style="font-size:16px" type="primary" href="/api/manager/properties/id/${requestScope.get("id")}/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/5" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                                        <el-link style="font-size:16px" type="primary" href="/api/manager/properties/id/${requestScope.get("id")}/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/5" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                                    </div>
                                </div>
                                <table style="width: 100%;">
                                    <tr>
                                        <th>申请人</th>
                                        <th>邮箱</th>
                                        <th>处理结果</th>
                                    </tr>
                                    <lt:PDListTag/>
                                </table>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
        <c:import url="changeInfo.jsp"/>
    </div>
</body>
<script src="/static/js/manager.js"></script>
</html>

