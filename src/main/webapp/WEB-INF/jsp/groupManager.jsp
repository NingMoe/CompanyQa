<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/6/7
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分组管理</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<input type="button" class="btn btn-default" name="addGroup" id="addGroup"
       onclick="window.location.href='<%=request.getContextPath() %>/group/toAddGroup'"
       value="添加分组"/>
<table class="table">
    <thead>
    <tr>
        <th>
            <div align="center">名称</div>
        </th>
        <th>
            <div align="center">描述</div>
        </th>
        <th>
            <div align="center">状态</div>
        </th>
        <th>
            <div align="center">操作</div>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="group">
        <tr>
            <td>
                <div align="center">${group.name}</div>
            </td>
            <td>
                <div align="center">${group.description}</div>
            </td>
            <td>
                <div align="center">
                    <c:forEach items="${status}" var="s">
                        <c:choose>
                            <c:when test="${s.value==group.status}">${s.text}</c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </td>
            <td>
                <div align="center">
                    <c:if test="${group.status==1}">
                        <a href="<%=request.getContextPath() %>/group/toEditGroup?id=${group.id}"
                           class="btn-link">编辑</a>
                        <a href="<%=request.getContextPath() %>/group/delGroup?id=${group.id}" class="btn-link"
                           onclick="javascrip:return window.confirm('您确定要删除吗?')">删除</a>
                    </c:if>
                    <c:if test="${group.status==2}">
                        <a href="<%=request.getContextPath() %>/group/recoveryGroup?id=${group.id}" class="btn-link"
                           onclick="javascrip:return window.confirm('您确定要恢复吗?')">恢复</a>
                    </c:if>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
