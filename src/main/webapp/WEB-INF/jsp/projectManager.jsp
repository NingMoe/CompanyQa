<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/26
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目管理</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath() %>/project/getProjects" method="post" class="form-horizontal" role="form" style="width: 98%">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="productId">系统</label>
        <div class="col-sm-3">
            <select name="productId" id="productId" class="form-control">
                <option></option>
                <c:forEach var="productMap" items="${productMap}">
                    <option value="${productMap.key}" <c:if test="${searchConditions.productId==productMap.key}">selected</c:if>>${productMap.value}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-sm-2 control-label" for="name">项目名称</label>
        <div class="col-sm-3">
            <input type="text" name="name" id="name" class="form-control" value="${searchConditions.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="startTimePlan">计划开始时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="startTimePlan" id="startTimePlan" value="${searchConditions.startTimePlan}" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
        <label class="col-sm-2 control-label" for="endTimePlan">至</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="endTimePlan" id="endTimePlan" value="${searchConditions.endTimePlan}" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="startTimeActual">实际开始时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="startTimeActual" id="startTimeActual"
                       value="${searchConditions.startTimeActual}" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
        <label class="col-sm-2 control-label" for="endTimeActual">至</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="endTimeActual" id="endTimeActual" value="${searchConditions.endTimeActual}"
                       readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="projectStatus">项目状态</label>
        <div class="col-sm-3">
            <select name="projectStatus" id="projectStatus" class="form-control">
                <option></option>
                <c:forEach var="projectStatus" items="${projectStatus}">
                    <option value="${projectStatus.value}" <c:if test="${searchConditions.projectStatus==projectStatus.value}">selected</c:if>>${projectStatus.text}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-sm-2 control-label" for="tester">测试人员</label>
        <div class="col-sm-3">
            <select name="tester" id="tester" class="placeholder-single">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}" <c:if test="${searchConditions.tester==user.name}">selected</c:if>>${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4">
            <input type="submit" name="search" id="search" class="btn btn-default" value="查询"/>
            <input type="reset" name="reset" id="reset" class="btn btn-default" value="重置" onclick="window.location.href='<%=request.getContextPath() %>/project/toProjectManager'">
        </div>
    </div>
</form>
<shiro:hasRole name="tester">
<input type="button" class="btn btn-default" name="addProject" id="addProject" onclick="window.location.href='<%=request.getContextPath() %>/project/toAddProject'"
       value="添加项目"/>
</shiro:hasRole>
<jsp:include page="projectList.jsp"/>

<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
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
