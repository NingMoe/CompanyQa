<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/27
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>缺陷报告</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<shiro:hasAnyRoles  name="tester,admin">
    <input type="button" value="添加/修改缺陷报告" name="addBugReport" id="addBugReport" class="btn btn-default"
           onclick="window.location.href='<%=request.getContextPath() %>/bugReport/toAddBugReport?projectId=${projectId}'">
    <button name="exportExcel" id="exportExcel" class="btn btn-default"
            onclick="window.location.href='<%=request.getContextPath()%>/bugReport/exportExcel?projectId=${projectId}'">
        导出Excel
    </button>
    <button name="sendMail" id="sendMail" class="btn btn-default" onclick="sendMail()">发送邮件</button>
</shiro:hasAnyRoles >
<input type="hidden" name="projectId" id="projectId" value="${projectId}">
<table class="table-bordered" width="98%">
    <tr>
        <td colspan="7" align="center"><b>缺陷报告</b></td>
    </tr>
    <tr>
        <td width="150px"><b>测试人员</b></td>
        <td colspan="6">${project.tester}</td>
    </tr>
    <tr>
        <td><b>开发人员</b></td>
        <td colspan="6">${project.producter}</td>
    </tr>
    <tr>
        <td><b>测试计划时间</b></td>
        <td colspan="6">
            <c:if test="${project.startTime2plan!=null}">${project.startTime2plan}</c:if>
            <c:if test="${project.endTime2plan!=null && project.endTime2plan!=null}">---</c:if>
            <c:if test="${project.endTime2plan!=null}">${project.endTime2plan}</c:if>
        </td>
    </tr>
    <tr>
        <td><b>实际测试时间</b></td>
        <td colspan="6">
            <c:if test="${project.startTime2actual!=null}">${project.startTime2actual}</c:if>
            <c:if test="${project.endTime2actual!=null && project.endTime2actual!=null}">---</c:if>
            <c:if test="${project.endTime2actual!=null}">${project.endTime2actual}</c:if>
        </td>
    </tr>
    <tr>
        <td><b>测试环境</b></td>
        <td colspan="6">${bugReport.environment}</td>
    </tr>
    <tr>
        <td><b>未解决问题</b></td>
        <td colspan="6">${bugReport.unresolved}</td>
    </tr>
    <tr>
        <td><b>反馈问题</b></td>
        <td colspan="6">${bugReport.feedback}</td>
    </tr>
    <tr>
        <td><b>bug统计</b></td>
        <td align="center"><b>严重错误</b></td>
        <td align="center"><b>次要错误</b></td>
        <td align="center"><b>一般错误</b></td>
        <td align="center"><b>布局错误</b></td>
        <td align="center"><b>文字错误</b></td>
        <td align="center"><b>新特性</b></td>
    </tr>
    <tr>
        <td><b>数量</b></td>
        <td align="center">${bugReport.seriousBugs}</td>
        <td align="center">${bugReport.secondaryBugs}</td>
        <td align="center">${bugReport.generalBugs}</td>
        <td align="center">${bugReport.layoutBugs}</td>
        <td align="center">${bugReport.textBugs}</td>
        <td align="center">${bugReport.newfeatureBugs}</td>
    </tr>
    <tr>
        <td><b>bug总数</b></td>
        <td colspan="6">${bugReport.totalBugs}</td>
    </tr>
    <tr>
        <td><b>测试用例总数</b></td>
        <td colspan="6">${bugReport.testCases}</td>
    </tr>
    <tr>
        <td><b>测试用例执行总数</b></td>
        <td colspan="6">${bugReport.executedCases}</td>
    </tr>
    <tr>
        <td colspan="7"><b>bug级别描述</b></td>
    </tr>
    <tr>
        <td colspan="7">
            严重错误：系统不能访问、主流程断点、功能模块未实现、财务数据问题；<br/>
            次要错误：流程断点、错误页面、功能未实现；<br/>
            一般错误：功能基本实现，但边界上存在问题；<br/>
            布局错误：页面样式问题；<br/>
            文字错误：错别字、中文乱码；<br/>
            新特性：建议、需求
        </td>
    </tr>
    <tr>
        <td colspan="7"><b>测试结论</b></td>
    </tr>
    <tr>
        <td colspan="7">${bugReport.testResult}</td>
    </tr>
</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    function sendMail() {
        var projectId = document.getElementById("projectId").value;
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "<%=request.getContextPath()%>/bugReport/sendMail",
            type: "post",
            data: "projectId=" + projectId,
            async: false,
            success: function (msg) {
                if (msg == "success") {
                    alert("邮件发送成功");
                } else if(msg == "null"){
                    alert("请添加缺陷报告");
                }else{
                    alert("邮件发送失败，请联系管理员");
                }
            },
            error: function (XMLHttpRequest) {
                alert("请求失败，请联系管理员！" + XMLHttpRequest.status);
            }
        });
    }
</script>
<%--<script type="text/javascript">
    function exportExcel() {
        var projectId = document.getElementById("projectId").value;
        $.ajax({
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            url: "<%=request.getContextPath()%>/bugReport/exportExcel",
            type: "post",
            data: "projectId=" + projectId,
            async: false,
            success: function (msg) {
                if(msg=="success"){
                    alert("导出成功");
                }else{
                    alert("导出失败，请联系管理员!");
                }
            },
            error: function (XMLHttpRequest) {
                alert("请求失败，请联系管理员！" + XMLHttpRequest.status);
            }
        });
    }
</script>--%>
</body>
</html>
