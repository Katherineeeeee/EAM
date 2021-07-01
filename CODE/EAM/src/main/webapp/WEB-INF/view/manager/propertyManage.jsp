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
    <div id="property_manage" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="managerHeader.jsp">
                <c:param name="url" value="properties"/>
            </c:import>
            <el-main style="height: 88%;">
                <div style="margin-top: 10px; display: flex; flex-direction: row;">
                    <el-button type="primary" icon="el-icon-plus" @click="propertyAddDialog=true">增加</el-button>
                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                        <p style="font-size:16px">
                            第${curPage}页, 共${sumPage}页
                        </p>
                        <el-link style="font-size:16px" type="primary" href="/api/manager/properties/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/10" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                        <el-link style="font-size:16px" type="primary" href="/api/manager/properties/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/10" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                    </div>
                </div>
                <div style="margin-top: 20px;">
                    <table style="width: 100%;">
                        <tr>
                            <th>编号</th>
                            <th>设备名称</th>
                            <th>品牌</th>
                            <th>型号</th>
                            <th>规格</th>
                            <th>采购日期</th>
                            <th>使用情况</th>
                        </tr>
                        <lt:PMListTag/>
                    </table>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
        <c:import url="changeInfo.jsp"/>
        <el-dialog
                title="添加资产"
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false"
                :visible.sync="propertyAddDialog"
                width="50%">
            <el-form label-width="80px" :model="propertyAddForm" :rules="propertyAddRules"  ref="propertyAddForm">
                <el-form-item label="设备名称" required prop="name"><el-input v-model="propertyAddForm.name"></el-input></el-form-item>
                <el-form-item label="品牌" required prop="brand"><el-input v-model="propertyAddForm.brand" autocomplete="off"></el-input></el-form-item>
                <el-form-item label="型号" required prop="type"><el-input  v-model="propertyAddForm.type" autocomplete="off"></el-input></el-form-item>
                <el-form-item label="规格" required prop="standard"><el-input v-model="propertyAddForm.standard"></el-input></el-form-item>
<%--                <el-form-item label="采购日期" required prop="time">--%>
<%--                    <el-date-picker type="date" placeholder="采购日期" v-model="propertyAddForm.time" style="width: 100%;"></el-date-picker>--%>
<%--                </el-form-item>--%>
                <el-form-item label="数量" required prop="num">
                    <el-input v-model="propertyAddForm.num"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="cancelAddProperty('propertyAddForm')">取 消</el-button>
                        <el-button type="primary" @click="addProperty('propertyAddForm')">确 定</el-button>
                    </span>
        </el-dialog>
    </div>
</body>
<script src="/static/js/propertyManage.js"></script>
</html>

