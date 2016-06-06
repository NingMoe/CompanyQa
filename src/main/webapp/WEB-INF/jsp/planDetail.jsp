<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/5/11
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试计划</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap-fileinput/css/fileinput.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/easyui.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div style="width:500px">
    <form enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/plan/upload"
          id="uploadForm">
        <input type="hidden" id="projectId" name="projectId" value="${projectId}">
        <input id="planFile" class="file" type="file" name="planFile">
        <c:if test="${state!=null}">
            <div id="success" class="alert alert-success fade in" style="margin-top:10px;">${state}</div>
        </c:if>
    </form>
    </div>
    <div style="height: 30px">
    <a href="<%=request.getContextPath() %>/plan/download?projectId=${projectId}">${plan.fileName}</a>
    </div>
    <c:if test="${treeJson !=null}">
    <table id="tree">
        <thead>
        <tr>
            <th data-options=" field: 'id'">#</th>
            <th data-options=" field: 'name'">任务名称</th>
            <th data-options="field:'duration'" width="80px">工期(天)</th>
            <th data-options="field:'start'" width="120px">开始时间</th>
            <th data-options="field:'finish'" width="120px">完成时间</th>
            <th data-options="field:'beforeTask'">前置任务</th>
            <th data-options="field:'resource'">资源</th>
        </tr>
        </thead>
    </table>
    </c:if>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/jquery.treegrid.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/jquery.easyui.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap-fileinput/js/fileinput.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<script>
    $('#tree').treegrid({
        idField: 'id',
        treeField: 'name',
        data:${treeJson}
    });
</script>
<script>
    $("#planFile").fileinput({
        language: 'zh', //设置语言
        allowedFileExtensions : ['mpp'],//接收的文件后缀
        browseClass: "btn btn-primary", //按钮样式
    });
</script>
</body>
</html>
