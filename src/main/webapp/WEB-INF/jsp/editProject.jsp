<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/26
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑项目</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
          rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath() %>/project/updateProject" class="form-horizontal" role="form" method="post"
      onsubmit="return check()" style="width: 98%">
    <input type="text" name="id" id="id" value="${project.id}" hidden="true" readonly="true">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="name"><span style="color:red;">*</span>项目名称</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="name" id="name" value="${project.name}"/>
        </div>
        <label class="col-sm-2 control-label" for="bugPlatform"><span style="color:red;">*</span>bug平台</label>
        <div class="col-sm-3">
            <select name="bugPlatform" id="bugPlatform" class="form-control">
                <option></option>
                <c:forEach var="bugPlatform" items="${bugPlatform}">
                    <option value="${bugPlatform.value}"
                            <c:if test="${project.bugPlatform==bugPlatform.value}">selected</c:if>>${bugPlatform.text}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group" id="mantis" <c:if test="${project.bugPlatform!=1}">hidden</c:if>>
        <label class="col-sm-2 control-label" for="projectMantis"><span
                style="color:red;">*</span>mantis项目id</label>
        <div class="col-sm-1">
            <input type="text" class="form-control" name="projectMantis" id="projectMantis"
                   value="${project.projectMantis}"/>
        </div>
        <label class="col-sm-2 control-label" for="categoryMantis">mantis项目分类</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="categoryMantis" id="categoryMantis"
                   value="${project.categoryMantis}"/>
        </div>
        <label class="col-sm-2 control-label" for="versionMantis">mantis版本号</label>
        <div class="col-sm-1">
            <input type="text" class="form-control" name="versionMantis" id="versionMantis"
                   value="${project.versionMantis}"/>
        </div>
    </div>

    <div class="form-group" id="jira" <c:if test="${project.bugPlatform!=2}">hidden</c:if>>
        <label class="col-sm-2 control-label" for="pKey"><span style="color:red;">*</span>jira键值</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="pKey" id="pKey" value="${project.pKey}"/>
        </div>
        <label class="col-sm-1 control-label" for="component">模块</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="component" id="component" value="${project.component}"/>
        </div>
        <label class="col-sm-2 control-label" for="issuenum">需求号(逗号分隔)</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="issuenum" id="issuenum" value="${project.issuenum}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="productId"><span style="color:red;">*</span>所属系统</label>
        <div class="col-sm-3">
            <select name="productId" id="productId" class="form-control">
                <c:forEach var="productMap" items="${productMap}">
                    <option value="${productMap.key}"
                            <c:if test="${project.productId==productMap.key}">selected</c:if>>${productMap.value}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-sm-2 control-label" for="producter">产品人员</label>
        <div class="col-sm-3">
            <select name="producter" id="producter" class="placeholder-multiple"
                    multiple="multiple">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:forEach var="selectName" items="${project.producter.split(',')}">
                                <c:if test="${user.name==selectName}">selected</c:if>
                            </c:forEach>
                    >${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="developer">开发人员</label>
        <div class="col-sm-3">
            <select name="developer" id="developer" class="placeholder-multiple" multiple="multiple">
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:forEach var="selectName" items="${project.developer.split(',')}">
                                <c:if test="${user.name==selectName}">selected</c:if>
                            </c:forEach>
                    >${user.name}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-sm-2 control-label" for="tester">测试人员</label>
        <div class="col-sm-3">
            <select name="tester" id="tester" class="placeholder-multiple" multiple="multiple">
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:forEach var="selectName" items="${project.tester.split(',')}">
                                <c:if test="${user.name==selectName}">selected</c:if>
                            </c:forEach>
                    >${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="startTime2plan">计划开始时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="startTime2plan" id="startTime2plan"
                       value="${project.startTime2plan}" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
        <label class="col-sm-2 control-label" for="endTime2plan">计划结束时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="endTime2plan" id="endTime2plan"
                       value="${project.endTime2plan}" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="startTime2actual">实际开始时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="startTime2actual" id="startTime2actual"
                       value="${project.startTime2actual}" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
        <label class="col-sm-2 control-label" for="endTime2actual">实际结束时间</label>
        <div class="col-sm-3">
            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" name="endTime2actual" id="endTime2actual"
                       value="${project.endTime2actual}" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"><span
                            class="glyphicon fa-times"></span></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="projectStatus">项目完成状态</label>
        <div class="col-sm-3">
            <select name="projectStatus" id="projectStatus" class="form-control">
                <option></option>
                <c:forEach var="projectStatus" items="${projectStatus}">
                    <option value="${projectStatus.value}"
                            <c:if test="${project.projectStatus==projectStatus.value}">selected</c:if>>${projectStatus.text}</option>
                </c:forEach>
            </select>
        </div>
        <label class="col-sm-2 control-label" for="days">提前/延期天数</label>
        <div class="col-sm-3">
            <input type="text" name="days" id="days" class="form-control" value="${project.days}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="requirementDays">需求阶段天数</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="requirementDays" id="requirementDays"
                   value="${project.requirementDays}">
        </div>
        <label class="col-sm-2 control-label" for="developDays">代码阶段天数</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="developDays" id="developDays" value="${project.developDays}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="testDays">测试阶段天数</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="testDays" id="testDays" value="${project.testDays}">
        </div>
        <label class="col-sm-2 control-label" for="acceptanceDays">业务验收阶段天数</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="acceptanceDays" id="acceptanceDays"
                   value="${project.acceptanceDays}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="onlineDays">上线试运行阶段天数</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="onlineDays" id="onlineDays" value="${project.onlineDays}">
        </div>
        <input type="hidden" name="status" id="status" value="${project.status}">
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="description">项目说明</label>
        <div class="col-sm-8">
            <textarea name="description" id="description" class="form-control">${project.description}</textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-6">
            <button type="submit" name="submit" id="submit" class="btn btn-default">保存</button>
            <button type="button" name="cancel" id="cancel" class="btn btn-default"
                    onclick="javascript:history.go(-1);">取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/select2.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/i18n/zh-CN.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".placeholder-multiple").select2({
            placeholder: "" //默认提示语
        });
    });
</script>

<script type="text/javascript">
    function check() {
        if (document.getElementById('name').value == "") {
            alert("项目名称不能为空！");
            document.getElementById('name').focus();
            return false;
        }
        if (document.getElementById('bugPlatform').value == "") {
            alert("bug平台不能为空！");
            document.getElementById('bugPlatform').focus();
            return false;
        }
        if (document.getElementById('bugPlatform').value == 1 && document.getElementById('projectMantis').value == "") {
            alert("mantis项目id不能为空！");
            document.getElementById('projectMantis').focus();
            return false;
        } else if (document.getElementById('bugPlatform').value == 2 && document.getElementById('pKey').value == "") {
            alert("jira键值不能为空！");
            document.getElementById('pKey').focus();
            return false;
        }
        if (document.getElementById("productId").value == "") {
            alert("所属系统不能为空");
            document.getElementById("productId").focus();
            return false;
        }
        var days = document.getElementById("days").value;
        if (days != "" && isNaN(days)) {
            alert("提前/延期天数只能为数字");
            document.getElementById('days').focus();
            return false;
        }
        var requirementDays = document.getElementById("requirementDays").value;
        if (requirementDays != "" && isNaN(requirementDays)) {
            alert("需求阶段天数只能为数字");
            document.getElementById("requirementDays").focus();
            return false;
        }
        var developDays = document.getElementById("developDays").value;
        if (developDays != "" && isNaN(developDays)) {
            alert("代码阶段天数只能为数字");
            document.getElementById("developDays").focus();
            return false;
        }
        var testDays = document.getElementById("testDays").value;
        if (testDays != "" && isNaN(testDays)) {
            alert("测试阶段天数只能为数字");
            document.getElementById("testDays").focus();
            return false;
        }
        var acceptanceDays = document.getElementById("acceptanceDays").value;
        if (acceptanceDays != "" && isNaN(acceptanceDays)) {
            alert("业务验收阶段天数只能为数字");
            document.getElementById("acceptanceDays").focus();
            return false;
        }
        var onlineDays = document.getElementById("onlineDays").value;
        if (onlineDays != "" && isNaN(onlineDays)) {
            alert("上线试运行阶段天数只能为数字");
            document.getElementById("onlineDays").focus();
            return false;
        }
        return true;
    }
</script>
<script type="text/javascript">
    $(function () {
        $("#bugPlatform").change(
                function () {
                    var t = $("#bugPlatform").val();
                    if (t == 1) {
                        $('#jira').hide();
                        $('#mantis').show();
                    } else if (t == 2) {
                        $('#mantis').hide();
                        $('#jira').show();
                    } else {
                        $('#mantis').hide();
                        $('#jira').hide();
                    }
                });
    });
</script>
</body>
</html>