<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/6/8
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<input type="button" class="btn btn-default" name="addRole" id="addRole"
       onclick="window.location.href='<%=request.getContextPath() %>/user/toAddUserRole'"
       value="添加权限"/>
<table class="table" width="80%">
    <thead>
    <tr>
        <th>用户名</th>
        <th>角色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${userRoleList}" var="userRole">
    <tr>
        <td>
            <div>${userRole.userName}</div>
        </td>
        <td>
            <div>${userRole.roleName}</div>
        </td>
        <td>
            <div>
                    <a href="<%=request.getContextPath() %>/user/toEditUserRole?id=${userRole.id}" class="btn-link">编辑</a>
                    <a href="<%=request.getContextPath() %>/user/delUserRole?id=${userRole.id}" class="btn-link"
                       onclick="javascript:return confirm('确定要删除吗？')">删除</a>
            </div>
        </td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
