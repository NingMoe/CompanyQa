<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/27
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <title>进度报告</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="form-group">
    <shiro:hasAnyRoles  name="tester,admin">
        <input type="button" value="添加进度报告" name="addProgress" id="addProgress" class="btn btn-default"
               onclick="window.location.href='<%=request.getContextPath() %>/progress/toAddProgress?projectId=${projectId}'">
        <input type="button" value="修改进度报告" name="editProgress" id="editProgress" class="btn btn-default"
               onclick="window.location.href='<%=request.getContextPath() %>/progress/progressList?projectId=${projectId}'">
        <button name="exportExcel" id="exportExcel" class="btn btn-default" onclick="window.location.href='<%=request.getContextPath()%>/progress/exportExcel?projectId=${projectId}'">导出Excel</button>
        <button name="sendMail" id="sendMail" class="btn btn-default" onclick="sendMail()">发送邮件</button>
    </shiro:hasAnyRoles >
   <%-- <a href="<%=request.getContextPath() %>/project/toProjectManager">返回</a>--%>
</div>
<input type="hidden" name="projectId" id="projectId" value="${projectId}">
<table class="table-bordered" width="99%">
    <tr>
        <td width="120px"><b>项目名称</b></td>
        <td colspan="7">${project.name}</td>
    </tr>
    <tr>
        <td><b>测试人员</b></td>
        <td colspan="7">${project.tester}</td>
    </tr>
    <tr>
        <td><b>验收人员</b></td>
        <td colspan="7">${project.producter}</td>
    </tr>
    <tr>
        <td><b>开发人员</b></td>
        <td colspan="7">${project.developer}</td>
    </tr>
    <tr>
        <td colspan="8"><b>测试进度</b></td>
    </tr>
    <c:forEach items="${list}" var="progress">
        <tr>
            <td>${progress.date}</td>
            <td colspan="7">${progress.progress}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8"><b>存在的问题</b></td>
    </tr>
    <c:forEach items="${list}" var="progress1">
        <tr>
            <td>${progress1.date}</td>
            <td colspan="7">${progress1.problem}</td>
        </tr>
    </c:forEach>
    <tr>
        <td align="center"><b>日期</b></td>
        <td align="center"><b>已测用例数</b></td>
        <td align="center"><b>新提交bug数</b></td>
        <td align="center"><b>已指派bug数</b></td>
        <td align="center"><b>已确认bug数</b></td>
        <td align="center"><b>已解决bug数</b></td>
        <td align="center"><b>反馈bug数</b></td>
        <td align="center"><b>已关闭bug数</b></td>
    </tr>
    <c:forEach items="${list}" var="progress2">
        <tr>
            <td align="center">${progress2.date}</td>
            <td align="center">${progress2.testCases}</td>
            <td align="center">${progress2.newBugs}</td>
            <td align="center">${progress2.assignedBugs}</td>
            <td align="center">${progress2.confirmedBugs}</td>
            <td align="center">${progress2.resolvedBugs}</td>
            <td align="center">${progress2.feedbackBugs}</td>
            <td align="center">${progress2.closedBugs}</td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8"><b>说明</b></td>
    </tr>
    <tr>
        <td colspan="8">
            1.“已测用例数”是指每天执行testlink的case数<br/>

            2.“新提交bug数”是指汇报当日新提交的bug数<br/>

            3.“已指派bug数”是指汇报时mantis上“已指派”状态的bug总数<br/>

            4.“已确认bug数”是指汇报时mantis上“已确认”状态的bug总数<br/>

            5.“已解决bug数”是指汇报时mantis上“已解决”状态的bug总数<br/>

            6.“反馈bug数”　是指汇报时mantis上“反馈”　状态的bug总数<br/>

            7.“已关闭bug数”是指汇报时mantis上“已关闭”状态的bug总数
        </td>
    </tr>
</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js" charset="UTF-8"></script>
<script type="text/javascript">
    function sendMail() {
        var projectId = document.getElementById("projectId").value;
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "<%=request.getContextPath()%>/progress/sendMail",
            type: "post",
            data: "projectId=" + projectId,
            async: false,
            success: function (msg) {
                if(msg=="success"){
                    alert("邮件发送成功");
                }else{
                    alert("邮件发送失败，请联系管理员!");
                }
            },
            error: function (XMLHttpRequest) {
                alert("请求失败，请联系管理员！" + XMLHttpRequest.status);
            }
        });
    }
</script>
</body>
</html>
