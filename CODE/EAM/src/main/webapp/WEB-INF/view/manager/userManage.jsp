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
    <div id="userManage" style="height: 100%;">
        <el-container style="height: 100%;">
            <c:import url="managerHeader.jsp">
                <c:param name="url" value="users"/>
            </c:import>
            <el-main style="height: 88%; display: flex; flex-direction: column;">
                <div style="margin-top: 10px; display: flex; flex-direction: row;">
                    <el-button type="primary" icon="el-icon-plus" @click="userAddDialog=true">增加</el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="deleteUsers">删除</el-button>
                    <div style="flex-grow: 1; display: flex; justify-content: flex-end;align-items: center;">
                        <p style="font-size:16px">
                            第${curPage}页, 共${sumPage}页
                        </p>
                        <el-link style="font-size:16px" type="primary" href="/api/manager/users/pNo/${requestScope.get("prePage") == -1 ? 1 : requestScope.get("prePage")}/pSz/10" ${requestScope.get("prePage") == -1 ? "disabled" : ""}>&lt;上一页</el-link>,
                        <el-link style="font-size:16px" type="primary" href="/api/manager/users/pNo/${requestScope.get("nextPage") == -1 ? 1 : requestScope.get("nextPage")}/pSz/10" ${requestScope.get("nextPage") == -1 ? "disabled" : ""}>下一页&gt;</el-link>
                    </div>
                </div>
                <div style="margin-top: 20px; height: 85%;">
                    <table style="width: 100%;" id="userTable">
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>邮箱</th>
                            <th>详情</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <lt:UMListTag/>
                    </table>
                </div>
            </el-main>
            <c:import url="../footer.jsp"/>
        </el-container>
        <c:import url="changeInfo.jsp"/>
        <el-dialog
                title="添加用户"
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false"
                :visible.sync="userAddDialog"
                width="50%">
            <el-form label-width="80px" :model="userAddForm" :rules="userAddRules"  ref="userAddForm">
                <el-form-item label="用户名" required prop="name"><el-input v-model="userAddForm.name"></el-input></el-form-item>
                <el-form-item label="密码" required prop="password"><el-input type="password" v-model="userAddForm.password" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="确认密码" required prop="password2"><el-input type="password" v-model="userAddForm.password2" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="邮箱" prop="email"><el-input v-model="userAddForm.email"></el-input></el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="cancelAddUser('userAddForm')">取 消</el-button>
                        <el-button type="primary" @click="addUser('userAddForm')">确 定</el-button>
                    </span>
        </el-dialog>
        <el-dialog
                title="更新用户信息"
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false"
                :visible.sync="userChangeDialog"
                width="50%">
            <el-form label-width="80px" :model="userChangeForm" :rules="userChangeRules" ref="userChangeForm">
                <el-form-item label="用户名"><el-input v-model="userChangeForm.name" :disabled="true"></el-input></el-form-item>
                <el-form-item label="密码" required prop="password"><el-input type="password" v-model="userChangeForm.password" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="确认密码" required prop="password2"><el-input type="password" v-model="userChangeForm.password2" show-password autocomplete="off"></el-input></el-form-item>
                <el-form-item label="邮箱" prop="email"><el-input v-model="userChangeForm.email"></el-input></el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelUpdateUserDetail('userChangeForm')">取 消</el-button>
                <el-button type="primary" @click="updateUserDetail('userChangeForm')">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</body>
<script src="/static/js/userManage.js"></script>
</html>
