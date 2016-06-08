<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/6/8
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户权限</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/select2-4.0.2/dist/css/select2.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath() %>/user/addUserRole" class="form-horizontal" method="post"
      onsubmit="return check()" style="width: 98%">
    <div class="form-group">
        <label class="col-sm-1 control-label" for="userName"><span style="color:red;">*</span>用户名</label>
        <div class="col-sm-5">
            <input type="text" name="userName" id="userName" class="form-control" value=""/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="role"><span style="color:red;">*</span>角色</label>
        <div class="col-sm-5">
            <select name="role" id="role" class="form-control">
                <option></option>
                <c:forEach var="role" items="${roles}">
                    <option value="${role.id}">${role.roleName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3">
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
        if (document.getElementById('userName').value == "") {
            alert("用户名不能为空！");
            document.getElementById('userName').focus();
            return false;
        }
        if (document.getElementById('role').value == "") {
            alert("角色不能为空！");
            document.getElementById('role').focus();
            return false;
        }
        var userName = document.getElementById("userName").value;
        var flag=true;
         $.ajax({
            url: "<%=request.getContextPath() %>/user/checkUser",
            type: "post",
            data: "userName=" + userName,
            // datatype: "json",
            async: false,
            success: function (data, stats) {
                if (stats == "success") {
                    if (data.errMsg != null) {
                        alert(data.errMsg);
                        flag =  false;
                    }
                } else {
                    alert(data.errMsg);
                    flag =  false;
                }
            },
            error: function (XMLHttpRequest) {
                alert("请求失败，请联系管理员！" + XMLHttpRequest.status);
                flag =  false;
            }
        });
       return flag;
    }
</script>
</body>
</html>
