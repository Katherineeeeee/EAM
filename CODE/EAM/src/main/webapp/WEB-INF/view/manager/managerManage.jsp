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
    <div id="managerManage" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="managerHeader.jsp">
                <c:param name="url" value="managers"/>
            </c:import>
            <el-main style="height: 88%; display: flex; flex-direction: column;">
                <div style="margin-top: 10px; display: flex; flex-direction: row;">
                    <el-button type="primary" icon="el-icon-plus" @click="managerAddDialog=true">增加</el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="deleteManagers">删除</el-button>
                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                        <p style="font-size:16px">
                            第${curPage}页, 共${sumPage}页
                        </p>
                        <el-link style="font-size:16px" type="primary" href="/api/manager/managers/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/10" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                        <el-link style="font-size:16px" type="primary" href="/api/manager/managers/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/10" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                    </div>
                </div>
                <div style="margin-top: 20px;">
                    <table style="width: 100%;" id="managerTable">
                        <tr>
                            <th><!--<el-checkbox v-model="managerSelectedAll" @change="selectAll"/>--></th>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <lt:MMListTag/>
                    </table>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
        <c:import url="changeInfo.jsp"/>
        <el-dialog
                title="添加管理员"
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false"
                :visible.sync="managerAddDialog"
                width="50%">
            <el-form label-width="80px" :model="managerAddForm" :rules="managerAddRules"  ref="managerAddForm">
                <el-form-item label="用户名" required prop="name"><el-input v-model="managerAddForm.name"></el-input></el-form-item>
                <el-form-item label="密码" required prop="password"><el-input type="password" v-model="managerAddForm.password" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="确认密码" required prop="password2"><el-input type="password" v-model="managerAddForm.password2" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="手机" prop="phone"><el-input v-model="managerAddForm.phone"></el-input></el-form-item>
                <el-form-item label="邮箱" prop="email"><el-input v-model="managerAddForm.email"></el-input></el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="cancelAddManager('managerAddForm')">取 消</el-button>
                        <el-button type="primary" @click="addManager('managerAddForm')">确 定</el-button>
                    </span>
        </el-dialog>
    </div>
</body>
<script src="/static/js/managerManage.js"></script>
</html>

