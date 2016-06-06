<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/25
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
</head>
<body>
<form id="searchForm" class="form-horizontal" role="form" action="<%=request.getContextPath() %>/product/getProducts"
      method="post" style="width: 98%">
    <div class="form-group">
        <label class="col-md-1 control-label" for="name">系统名称</label>
        <div class="col-md-3">
            <input class="form-control" type="text" name="name" id="name" value="${searchConditions.name}"/>
        </div>
        <label class="col-md-1 control-label" for="group">所属分组</label>
        <div class="col-md-3">
            <select name="group" id="group" class="placeholder-single">
                <option></option>
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}"
                            <c:if test="${group.id==searchConditions.group}">selected</c:if>>${group.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-1 control-label" for="leader">负责人</label>
        <div class="col-md-3">
            <select name="leader" id="leader" class="placeholder-single">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:if test="${searchConditions.leader==user.name}">selected</c:if>>${user.name}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-md-1 control-label" for="status">状态</label>
        <div class="col-md-2">
            <select name="status" id="status" class="form-control">
                <c:forEach var="s" items="${status}">
                    <option value="${s.value}"
                            <c:if test="${searchConditions.status==s.value}">selected</c:if>>${s.text}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-3">
            <button type="submit" class="btn btn-default" name="submit" id="submit">查询</button>
            <button type="reset" class="btn btn-default" name="reset" id="reset"  onclick="">重置</button>
        </div>
    </div>
</form>
<shiro:hasRole name="tester">
    <input type="button" class="btn btn-default" name="addProduct" id="addProduct"
           onclick="window.location.href='<%=request.getContextPath() %>/product/toAddProduct'"
           value="添加系统"/>
</shiro:hasRole>
<table class="table table-hover">
    <thead>
    <tr>
        <th>
            <div align="center">系统名称</div>
        </th>
        <th>
            <div align="center">所属分组</div>
        </th>
        <th>
            <div align="center">负责人</div>
        </th>
        <th>
            <div align="center">系统说明</div>
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
    <c:forEach items="${list}" var="product">
        <tr>
            <td>
                <div align="center">${product.name}</div>
            </td>
            <td>
                <div align="center">
                    <c:forEach items="${allGroups}" var="group">
                    <c:choose>
                        <c:when test="${group.id==product.group}">${group.name}</c:when>
                    </c:choose>
                    </c:forEach>
            </td>
            <td>
                <div align="center">${product.leader}</div>
            </td>
            <td>
                <div align="center">${product.description}</div>
            </td>
            <td>
                <div align="center">
                    <c:forEach items="${status}" var="s">
                        <c:choose>
                            <c:when test="${s.value==product.status}">${s.text}</c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </td>
            <td>
                <div align="center">
                    <a href="<%=request.getContextPath() %>/project/getProjectsByProductId?productId=${product.id}" class="btn-link">查看</a>
                    <shiro:hasRole name="tester">
                        <c:if test="${product.status==1}">
                            <a href="<%=request.getContextPath() %>/product/toEditProject?id=${product.id}" class="btn-link">编辑</a>
                            <a href="<%=request.getContextPath() %>/product/delProduct?id=${product.id}" class="btn-link" onclick=javascrip:window.confirm("您确定要删除吗?")>删除</a>
                        </c:if>
                    </shiro:hasRole>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".placeholder-single").select2({
            placeholder: "",
            allowClear: true
        });
    });
</script>

</body>
</html>
