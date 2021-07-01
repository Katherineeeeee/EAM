<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<el-header style="height: 8%; background-color: #2B2B2B;">
    <el-row style="height: 100%;">
        <el-col :span="4" style="height: 100%; display: flex; align-items: center;">
            <h3 style="color: #ffffff;">企业资产管理系统</h3>
        </el-col>
        <el-col :offset="2" :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color: <c:choose><c:when test="${param.url == 'users'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/manager/users/pNo/1/pSz/10" :underline="false">用户管理</el-link>
            <!-- <span class="btnstyle" style="font-size: 18.72px; color: #ffffff; font-weight: bold;" v-on:click="user_manage">用户管理</span> -->
        </el-col>

        <el-col :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color: <c:choose><c:when test="${param.url == 'properties'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/manager/properties/pNo/1/pSz/10" :underline="false">资产管理</el-link>
            <!-- <span class="btnstyle" style="font-size: 18.72px; color: #ffffff; font-weight: bold;" v-on:click="user_manage">用户管理</span> -->
        </el-col>
        <el-col :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color: <c:choose><c:when test="${param.url == 'uses'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/manager/applications/pNo/1/pSz/10" :underline="false">使用审批</el-link>
            <!-- <span class="btnstyle" style="font-size: 18.72px; color: #ffffff; font-weight: bold;" v-on:click="user_manage">用户管理</span> -->
        </el-col>
        <el-col :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color: <c:choose><c:when test="${param.url == 'managers'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/manager/managers/pNo/1/pSz/10" :underline="false">管理员管理</el-link>
        </el-col>

        <el-col :offset="3" :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-dropdown :hide-on-click="false" @command="self">
							  <span class="btnstyle" style="color: #ffffff; font-weight: bold;">
							    ${sessionScope.get(cookie.get("manager").value).mName}<i class="el-icon-arrow-down el-icon--right"></i>
							  </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="updateInfo">修改信息</el-dropdown-item>
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-col>
    </el-row>
</el-header>
