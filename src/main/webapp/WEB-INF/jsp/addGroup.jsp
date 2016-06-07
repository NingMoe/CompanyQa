<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/6/7
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加分组</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="<%=request.getContextPath() %>/group/addGroup" class="form-horizontal" method="post"
      onsubmit="return check()" style="width: 98%">
    <div class="form-group">
        <label class="col-sm-1 control-label" for="name"><span style="color:red;">*</span>名称</label>
        <div class="col-sm-5">
            <input type="text" name="name" id="name" class="form-control" value=""/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="description">描述</label>
        <div class="col-sm-5">
            <textarea name="description" id="description" class="form-control"></textarea>
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
<script type="text/javascript">
    function check() {
        if (document.getElementById('name').value == "") {
            alert("名称不能为空！");
            document.getElementById('name').focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>
