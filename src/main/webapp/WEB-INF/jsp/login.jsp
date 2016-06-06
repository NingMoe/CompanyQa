<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>登录测试平台</title>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="<%=request.getContextPath() %>/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="<%=request.getContextPath() %>/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="<%=request.getContextPath() %>/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="<%=request.getContextPath() %>/login" method="post">
        <h3>用户登录</h3>
        <c:if test="${requestScope.error != null}">
        <div class="alert alert-danger">
			<span>
				 ${requestScope.error}
			</span>
        </div>
        </c:if>

        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input name="username" id="username" size="25" value="" class="form-control placeholder-no-fix"
                       type="text" autocomplete="off" placeholder="用户名"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input name="password" id="password" size="25" value="" class="form-control placeholder-no-fix"
                       type="password" autocomplete="off" placeholder="密码"/>
            </div>
        </div>
        <div class="form-actions">
            <%--<label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住我 </label>--%>
            <button id="loginBut" type="submit" class="btn blue pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END LOGIN FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<!--<script src="<%=request.getContextPath() %>/assets/plugins/respond.min.js"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/excanvas.min.js"></script>-->
<![endif]-->
<script src="<%=request.getContextPath() %>/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<%=request.getContextPath() %>/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath() %>/assets/scripts/app.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/assets/scripts/login-soft.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>