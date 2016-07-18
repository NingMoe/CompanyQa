<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/26
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目列表</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>
            <div align="center">项目名称</div>
        </th>
        <th>
            <div align="center">所属系统</div>
        </th>
        <th>
            <div align="center">计划开始时间</div>
        </th>
        <th>
            <div align="center">计划结束时间</div>
        </th>
        <th>
            <div align="center">实际开始时间</div>
        </th>
        <th>
            <div align="center">实际结束时间</div>
        </th>
        <th>
            <div align="center">测试人员</div>
        </th>
        <th>
            <div align="center">项目状态</div>
        </th>
        <th>
            <div align="center">操作</div>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="project">
        <tr>
            <td>
                <div align="center">${project.name}</div>
            </td>
            <td>
                <div align="center">${productMap[project.productId]}</div>
            </td>
            <td>
                <div align="center">${project.startTime2plan}</div>
            </td>
            <td>
                <div align="center">${project.endTime2plan}</div>
            </td>
            <td>
                <div align="center">${project.startTime2actual}</div>
            </td>
            <td>
                <div align="center">${project.endTime2actual}</div>
            </td>
            <td>
                <div align="center">${project.tester}</div>
            </td>
            <td>
                <div align="center">
                    <c:forEach items="${projectStatus}" var="projectStatus">
                        <c:choose>
                            <c:when test="${projectStatus.value==project.projectStatus}">${projectStatus.text}</c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </td>
            <td>
                <div align="center">
                    <shiro:hasAnyRoles name="tester,admin">
                        <a href="<%=request.getContextPath() %>/project/toEditProject?id=${project.id}" class="btn-link">编辑</a>
                        <a href="<%=request.getContextPath() %>/plan/toPlanDetail?projectId=${project.id}" class="btn-link">计划</a>
                        <a href="<%=request.getContextPath() %>/progress/progressDetail?projectId=${project.id}" class="btn-link">进度</a>
                        <a href="<%=request.getContextPath() %>/bugReport/toBugReportDetail?projectId=${project.id}" class="btn-link">缺陷报告</a>
                        <a href="<%=request.getContextPath() %>/project/getEmails?projectId=${project.id}" class="btn-link">邮件配置</a>
                        <a href="<%=request.getContextPath() %>/project/delProject?id=${project.id}" class="btn-link"
                           onclick="javascript:return confirm('确定要删除吗？')">删除</a>
                    </shiro:hasAnyRoles>
                    <a href="<%=request.getContextPath() %>/project/projectDetail?id=${project.id}"
                       class="btn-link">查看</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
