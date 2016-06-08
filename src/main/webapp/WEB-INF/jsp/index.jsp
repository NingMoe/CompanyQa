<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/css/custom.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/css/style.css" rel="stylesheet">
</head>
<body style="overflow-x:hidden;overflow-y:hidden;">
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom:0px">
    <div class="navbar-header">
        <span><h3>测试平台</h3></span>
    </div>
    <p class="navbar-text pull-right">
        ${sessionScope.user.userInfo.name}<a href="<%=request.getContextPath() %>/logout" class="navbar-link">logout</a>
    </p>
</nav>
<div class="row">
    <div class="col-md-2">
        <ul class="nav nav-list">
            <li class="active">
                <a href="<%=request.getContextPath()%>/product/toProductManager" target="mainFrame">系统管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/project/toProjectManager" target="mainFrame">项目管理</a>
            </li>
            <shiro:hasRole name="admin">
                <li>
                    <a href="<%=request.getContextPath() %>/group/toGroupManager" target="mainFrame">分组管理</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath() %>/user/toSecurityManager" target="mainFrame">权限管理</a>
                </li>
            </shiro:hasRole>
        </ul>
    </div>
    <div class="col-md-10">
        <iframe name="mainFrame" frameborder="0" marginheight="0"
                marginwidth="0" style="height:90%;width:100%;padding-top: 15px;" style="overflow-x:hidden"></iframe>
    </div>
</div>
</div>
<script type="text/javascript" src="/assets/plugins/bootstrap/js/bootstrap.min.js"/>
</body>
</html>
