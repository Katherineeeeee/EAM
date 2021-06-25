<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<el-dialog
        @open="getInfo"
        title="信息修改"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :show-close="false"
        :visible.sync="infoDialog"
        width="50%">
    <el-form label-width="80px" :model="manager" :rules="managerRules" ref="manager">
        <el-form-item label="用户名">
            <el-input v-model="manager.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="新密码" required prop="password">
            <el-input type="password" v-model="manager.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" required prop="password2">
            <el-input type="password" v-model="manager.password2" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
            <el-input v-model="manager.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="manager.email" autocomplete="off"></el-input>
        </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button @click="cancelUpdateInfo('manager')">取 消</el-button>
        <el-button type="primary" @click="updateInfo('manager')">确 定</el-button>
    </span>
</el-dialog>
