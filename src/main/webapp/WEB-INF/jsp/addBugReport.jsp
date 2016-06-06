<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/27
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加缺陷报告</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath()%>/bugReport/saveBugReport" method="post" onsubmit="return check()" class="form-horizontal" role="form" style="width: 98%">
    <input type="hidden" id="projectId" name="projectId" value="${projectId}"/>
    <input type="hidden" id="id" name="id" value="${bugReport.id}"/>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="environment"><span style="color:red;">*</span>测试环境</label>
        <div class="col-sm-5">
            <textarea name="environment" id="environment" class="form-control"
                      cols="5">${bugReport.environment}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="unresolved"><span style="color:red;">*</span>未解决问题</label>
        <div class="col-sm-5">
            <textarea name="unresolved" id="unresolved" class="form-control" cols="5">${bugReport.unresolved}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="feedback"><span style="color:red;">*</span>反馈问题</label>
        <div class="col-sm-5">
            <textarea name="feedback" id="feedback" class="form-control" cols="5">${bugReport.feedback}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="testCases"><span style="color:red;">*</span>测试用例总数</label>
        <div class="col-sm-5">
            <input type="text" name="testCases" id="testCases" class="form-control" value="${bugReport.testCases}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="executedCases"><span style="color:red;">*</span>测试用例执行总数</label>
        <div class="col-sm-5">
            <input type="text" name="executedCases" id="executedCases" class="form-control"
                   value="${bugReport.executedCases}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="testResult"><span style="color:red;">*</span>测试结论</label>
        <div class="col-sm-5">
            <textarea name="testResult" id="testResult" class="form-control" cols="5">${bugReport.testResult}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="seriousBugs"><span style="color:red;">*</span>严重错误数</label>
        <div class="col-sm-5">
            <input type="text" name="seriousBugs" id="seriousBugs" class="form-control"
                   value="${bugReport.seriousBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>次要错误数</label>
        <div class="col-sm-5">
            <input type="text" name="secondaryBugs" id="secondaryBugs" class="form-control"
                   value="${bugReport.secondaryBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>一般错误数</label>
        <div class="col-sm-5">
            <input type="text" name="generalBugs" id="generalBugs" class="form-control"
                   value="${bugReport.generalBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>布局错误数</label>
        <div class="col-sm-5">
            <input type="text" name="layoutBugs" id="layoutBugs" class="form-control" value="${bugReport.layoutBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>文字错误数</label>
        <div class="col-sm-5">
            <input type="text" name="textBugs" id="textBugs" class="form-control" value="${bugReport.textBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>新特性错误数</label>
        <div class="col-sm-5">
            <input type="text" name="newfeatureBugs" id="newfeatureBugs" class="form-control"
                   value="${bugReport.newfeatureBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"><span style="color:red;">*</span>bug总数</label>
        <div class="col-sm-5">
            <input type="text" name="totalBugs" id="totalBugs" class="form-control" value="${bugReport.totalBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
        <input type="submit" name="submit" id="submit" class="btn btn-default" value="保存"/>
        <button type="button" name="cancel" id="cancel" class="btn btn-default" onclick="javascript:history.go(-1);">
            取消
        </button>
        </div>
    </div>

</form>

<script type="text/javascript">
    function check() {
        if (document.getElementById('environment').value == "") {
            alert("测试环境不能为空！");
            document.getElementById('environment').focus();
            return false;
        }
        if (document.getElementById('unresolved').value == "") {
            alert("未解决问题不能为空！");
            document.getElementById('unresolved').focus();
            return false;
        }
        if (document.getElementById("feedback").value == "") {
            alert("反馈问题不能为空");
            document.getElementById("feedback").focus();
            return false;
        }
        if (document.getElementById("testCases").value == "") {
            alert("测试用例总数不能为空");
            document.getElementById("testCases").focus();
            return false;
        }else if(isNaN(document.getElementById("testCases").value)){
            alert("测试用例总数只能为数字");
            document.getElementById("testCases").focus();
            return false;
        }
        if (document.getElementById("executedCases").value == "") {
            alert("测试用例执行总数不能为空");
            document.getElementById("executedCases").focus();
            return false;
        }else if(isNaN(document.getElementById("executedCases").value)){
            alert("测试用例执行总数只能为数字");
            document.getElementById("executedCases").focus();
            return false;
        }
        if (document.getElementById("testResult").value == "") {
            alert("测试结论不能为空");
            document.getElementById("testResult").focus();
            return false;
        }
        if (document.getElementById("seriousBugs").value == "") {
            alert("严重错误数不能为空");
            document.getElementById("seriousBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("seriousBugs").value)){
            alert("严重错误数只能为数字");
            document.getElementById("seriousBugs").focus();
            return false;
        }
        if (document.getElementById("secondaryBugs").value == "") {
            alert("次要错误数不能为空");
            document.getElementById("secondaryBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("secondaryBugs").value)){
            alert("次要错误数只能为数字");
            document.getElementById("secondaryBugs").focus();
            return false;
        }
        if (document.getElementById("generalBugs").value == "") {
            alert("一般错误数不能为空");
            document.getElementById("generalBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("generalBugs").value)){
            alert("一般错误数只能为数字");
            document.getElementById("generalBugs").focus();
            return false;
        }
        if (document.getElementById("layoutBugs").value == "") {
            alert("布局错误数不能为空");
            document.getElementById("layoutBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("layoutBugs").value)){
            alert("布局错误数只能为数字");
            document.getElementById("layoutBugs").focus();
            return false;
        }
        if (document.getElementById("textBugs").value == "") {
            alert("文字错误数不能为空");
            document.getElementById("textBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("textBugs").value)){
            alert("文字错误数只能为数字");
            document.getElementById("textBugs").focus();
            return false;
        }
        if (document.getElementById("newfeatureBugs").value == "") {
            alert("新特性错误数不能为空");
            document.getElementById("newfeatureBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("newfeatureBugs").value)){
            alert("新特性错误数只能为数字");
            document.getElementById("newfeatureBugs").focus();
            return false;
        }
        if (document.getElementById("totalBugs").value == "") {
            alert("bug总数不能为空");
            document.getElementById("totalBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("totalBugs").value)){
            alert("bug总数只能为数字");
            document.getElementById("totalBugs").focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>
