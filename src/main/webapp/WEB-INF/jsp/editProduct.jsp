<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/4/26
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑系统</title>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath() %>/product/updateProduct" class="form-horizontal" role="form" method="post"
      onsubmit="return check()" style="width: 98%">
    <input type="text" name="id" id="id" value="${product.id}" hidden="true" readonly="true">
    <div class="form-group">
        <label class="col-sm-1 control-label" for="name"><span style="color:red;">*</span>系统名称</label>
        <div class="col-sm-5">
            <input type="text" name="name" id="name" class="form-control" value="${product.name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="group"><span style="color:red;">*</span>所属分组</label>
        <div class="col-sm-5">
            <select name="group" id="group" class="form-control">
                <option></option>
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}"
                            <c:if test="${product.group==group.id}">selected</c:if>>${group.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label"><span style="color:red;">*</span>负责人</label>
        <div class="col-sm-5">
            <select name="leader" id="leader" class="placeholder-single">
                <option></option>
                <c:forEach var="user" items="${userList}">
                    <option value="${user.name}"
                            <c:if test="${product.leader==user.name}">selected</c:if>>${user.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">系统说明</label>
        <div class="col-sm-5">
            <textarea name="description" class="form-control">${product.description}</textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-5">
            <button type="submit" name="submit" id="submit" class="btn btn-default">保存</button>
            <button type="button" name="cancel" id="cancel" class="btn btn-default"
                    onclick="javascript:history.go(-1);">取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".placeholder-single").select2({
            placeholder: "",
            allowClear: true
        });
    });
</script>
<script type="text/javascript">
    function check() {
        if (document.getElementById('name').value == "") {
            alert("系统名称不能为空！");
            document.getElementById('name').focus();
            return false;
        }
        if (document.getElementById('group').value == "") {
            alert("所属分组不能为空！");
            document.getElementById('group').focus();
            return false;
        }
        if (document.getElementById("leader").value == "") {
            alert("负责人不能为空");
            document.getElementById("leader").focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>
