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
    <title>编辑进度报告</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>
<body>

<form action="<%=request.getContextPath()%>/progress/editProgress" method="post" onsubmit="return check()" class="form-horizontal" role="form" style="width: 98%">
    <input type="hidden" id="projectId" name="projectId" value="${progress.projectId}"/>
    <input type="hidden" id="id" name="id" value="${progress.id}">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="date"><span style="color:red;">*</span>日期</label>
        <div class="col-sm-5">
            <input type="text" name="date" id="date" class="form-control" value="${progress.date}" readonly>
        </div>
     </div>
     <div class="form-group">
         <label class="col-sm-2 control-label" for="progress"><span style="color:red;">*</span>进度</label>
         <div class="col-sm-5">
             <textarea name="progress" id="progress"  class="form-control">${progress.progress}</textarea>
         </div>
     </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="problem"><span style="color:red;">*</span>存在问题</label>
        <div class="col-sm-5">
            <textarea name="problem" id="problem" class="form-control">${progress.problem}</textarea>
        </div>
    </div>
   <div class="form-group">
       <label class="col-sm-2 control-label" for="testCases"><span style="color:red;">*</span>已测用例数</label>
       <div class="col-sm-5">
           <input type="text" name="testCases" id="testCases" class="form-control" value="${progress.testCases}"/>
       </div>
   </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="newBugs"><span style="color:red;">*</span>新提交bug数</label>
        <div class="col-sm-5">
            <input type="text" name="newBugs" id="newBugs" class="form-control" value="${progress.newBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="assignedBugs"><span style="color:red;">*</span>已指派bug数</label>
        <div class="col-sm-5">
            <input type="text" name="assignedBugs" id="assignedBugs" class="form-control" value="${progress.assignedBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="confirmedBugs"><span style="color:red;">*</span>已确认bug数</label>
        <div class="col-sm-5">
            <input type="text" name="confirmedBugs" id="confirmedBugs" class="form-control" value="${progress.confirmedBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="resolvedBugs"><span style="color:red;">*</span>已解决bug数</label>
        <div class="col-sm-5">
            <input type="text" name="resolvedBugs" id="resolvedBugs" class="form-control" value="${progress.resolvedBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="feedbackBugs"><span style="color:red;">*</span>反馈bug数</label>
        <div class="col-sm-5">
            <input type="text" name="feedbackBugs" id="feedbackBugs" class="form-control" value="${progress.feedbackBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="closedBugs"><span style="color:red;">*</span>已关闭bug数</label>
        <div class="col-sm-5">
            <input type="text" name="closedBugs" id="closedBugs" class="form-control" value="${progress.closedBugs}"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2">
            <input type="submit" name="submit" id="submit" class="btn btn-default" value="保存"/>
            <button type="button" name="cancel" id="cancel" class="btn btn-default" onclick="javascript:history.go(-1);">
                取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript">
    function check() {
        if (document.getElementById('date').value == "") {
            alert("日期不能为空！");
            document.getElementById('date').focus();
            return false;
        }
        if (document.getElementById('progress').value == "") {
            alert("进度不能为空！");
            document.getElementById('progress').focus();
            return false;
        }
        if(document.getElementById("problem").value == ""){
            alert("存在问题不能为空");
            document.getElementById("problem").focus();
            return false;
        }
        if(document.getElementById("testCases").value == ""){
            alert("已测用例数不能为空");
            document.getElementById("testCases").focus();
            return false;
        }else if(isNaN(document.getElementById("testCases").value)){
            alert("已测用例数只能为数字");
            document.getElementById("testCases").focus();
            return false;
        }
        if(document.getElementById("newBugs").value == ""){
            alert("新提交bug数不能为空");
            document.getElementById("newBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("newBugs").value)){
            alert("新提交bug数只能为数字");
            document.getElementById("newBugs").focus();
            return false;
        }
        if(document.getElementById("assignedBugs").value == ""){
            alert("已指派bug数不能为空");
            document.getElementById("assignedBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("assignedBugs").value)){
            alert("已指派bug数只能为数字");
            document.getElementById("assignedBugs").focus();
            return false;
        }
        if(document.getElementById("confirmedBugs").value == ""){
            alert("已确认bug数不能为空");
            document.getElementById("confirmedBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("confirmedBugs").value)){
            alert("已确认bug数只能为数字");
            document.getElementById("confirmedBugs").focus();
            return false;
        }
        if(document.getElementById("resolvedBugs").value == ""){
            alert("已解决bug数不能为空");
            document.getElementById("resolvedBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("resolvedBugs").value)){
            alert("已解决bug数只能为数字");
            document.getElementById("resolvedBugs").focus();
            return false;
        }
        if(document.getElementById("feedbackBugs").value == ""){
            alert("反馈bug数不能为空");
            document.getElementById("feedbackBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("feedbackBugs").value)){
            alert("反馈bug数只能为数字");
            document.getElementById("feedbackBugs").focus();
            return false;
        }
        if(document.getElementById("closedBugs").value == ""){
            alert("已关闭bug数不能为空");
            document.getElementById("closedBugs").focus();
            return false;
        }else if(isNaN(document.getElementById("closedBugs").value)){
            alert("已关闭bug数只能为数字");
            document.getElementById("closedBugs").focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>
