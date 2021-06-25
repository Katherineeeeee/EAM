<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<el-header style="height: 8%; background-color: #2B2B2B;">
    <el-row style="height: 100%;">
        <el-col :span="4" style="height: 100%; display: flex; align-items: center;">
            <h3 style="color: #ffffff;">企业资产管理系统</h3>
        </el-col>

        <el-col :offset="2" :span="4" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color:<c:choose><c:when test="${param.url == 'properties'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/user/properties/pNo/1/pSz/10" :underline="false">资产浏览</el-link>
        </el-col>
        <el-col :span="4" style="height: 100%; display: flex; align-items: center;">
            <el-link style="font-size: 18.72px; color: <c:choose><c:when test="${param.url == 'uses'}">#409EFF</c:when><c:otherwise>#ffffff</c:otherwise></c:choose>; font-weight: bold;" href="/api/user/applications/pNo/1/pSz/10" :underline="false">我的使用</el-link>
        </el-col>
        <el-col :offset="7" :span="3" style="height: 100%; display: flex; align-items: center;">
            <el-dropdown :hide-on-click="false" @command="self">
							  <span class="btnstyle" style="font-size: 18.72px; color: #ffffff; font-weight: bold;">
							    ${sessionScope.get(cookie.get("user").value).uName}<i class="el-icon-arrow-down el-icon--right"></i>
							  </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-col>
    </el-row>
</el-header>