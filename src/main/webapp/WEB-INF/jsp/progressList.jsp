<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/5/10
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>进度列表</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<a href="<%=request.getContextPath() %>/progress/progressDetail?projectId=${projectId}">返回</a>
<table class="table">
    <thead>
    <tr>
        <th width="9%">
            <div align="center">日期</div>
        </th>
        <th width="20%">
            <div align="center">测试进度</div>
        </th>
        <th width="20%">
            <div align="center">存在问题</div>
        </th>
        <th width="7%">
            <div align="center">已测用例</div>
        </th>
        <th width="6%">
            <div align="center">新提交</div>
        </th>
        <th width="6%">
            <div align="center">已指派</div>
        </th>
        <th width="6%">
            <div align="center">已确认</div>
        </th>
        <th width="6%">
            <div align="center">已解决</div>
        </th>
        <th width="5%">
            <div align="center">反馈</div>
        </th>
        <th width="6%">
            <div align="center">已关闭</div>
        </th>
        <th width="8%">
            <div align="center">操作</div>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="progress">
        <tr>
            <td>
                <div align="center">${progress.date}</div>
            </td>
            <td title="${progress.progress}">
                <div align="center">
                    <c:choose>
                        <c:when test="${fn:length(progress.progress) > 12}">
                            <c:out value="${fn:substring(progress.progress, 0, 12)}......"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${progress.progress}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
            <td title="${progress.problem}">
                <div align="center">
                    <c:choose>
                        <c:when test="${fn:length(progress.problem) > 12}">
                            <c:out value="${fn:substring(progress.problem, 0, 12)}......"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${progress.problem}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
            <td>
                <div align="center">${progress.testCases}</div>
            </td>
            <td>
                <div align="center">${progress.newBugs}</div>
            </td>
            <td>
                <div align="center">${progress.assignedBugs}</div>
            </td>
            <td>
                <div align="center">${progress.confirmedBugs}</div>
            </td>
            <td>
                <div align="center">${progress.resolvedBugs}</div>
            </td>
            <td>
                <div align="center">${progress.feedbackBugs}</div>
            </td>
            <td>
                <div align="center">${progress.closedBugs}</div>
            </td>
            <td>
                <div align="center">
                    <a href="<%=request.getContextPath() %>/progress/toEditProgress?id=${progress.id}">编辑</a>
                    <a href="<%=request.getContextPath() %>/progress/delProgress?id=${progress.id}&projectId=${projectId}"
                       onclick="javascript:return confirm('确定要删除吗？')">删除</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
