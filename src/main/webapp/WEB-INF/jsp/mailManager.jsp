<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/7/12
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮件管理</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath()%>/project/saveEmails" method="post" class="form-horizontal" role="form"
      onsubmit="return check()" style="width: 98%">
    <div class="form-group">
        <input type="text" name="projectId" id="projectId" value="${projectId}" hidden="true" readonly="true">
        <label class="col-sm-2 control-label" for="recipients"><span style="color:red;">*</span>收件人</label>
        <div class="col-sm-7">
            <select name="recipients" id="recipients" class="placeholder-multiple" multiple="multiple">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:forEach var="selectName" items="${recipients.split(',')}">
                                <c:if test="${user.name==selectName}">selected</c:if>
                            </c:forEach>
                    >${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="cc">抄送人员</label>
        <div class="col-sm-7">
            <select name="cc" id="cc" class="placeholder-multiple" multiple="multiple">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:forEach var="selectName" items="${cc.split(',')}">
                                <c:if test="${user.name==selectName}">selected</c:if>
                            </c:forEach>
                    >${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <input type="submit" name="submit" id="submit" class="btn btn-default" value="保存"/>
            <button type="button" name="cancel" id="cancel" class="btn btn-default"
                    onclick="javascript:history.go(-1);">
                取消
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
<script type="text/javascript">
    $(document).ready(function () {
        $(".placeholder-multiple").select2({
            placeholder: "" //默认提示语
        });
    });
</script>

<script type="text/javascript">
    function check() {
        if (document.getElementById('recipients').value == "") {
            alert("收件人不能为空！");
            document.getElementById('recipients').focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>
