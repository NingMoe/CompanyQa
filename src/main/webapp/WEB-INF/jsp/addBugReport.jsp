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
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysiwyg/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysiwyg/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.css" />
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
        <div class="container">
            <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                <div class="btn-group">
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a data-edit="fontSize 5" class="fs-Five">Huge</a></li>
                        <li><a data-edit="fontSize 3" class="fs-Three">Normal</a></li>
                        <li><a data-edit="fontSize 1" class="fs-One">Small</a></li>
                    </ul>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Text Highlight Color"><i class="fa fa-paint-brush"></i>&nbsp;<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <p>&nbsp;&nbsp;&nbsp;Text Highlight Color:</p>
                        <li><a data-edit="backColor #FFFFFF">white</a></li>
                        <li><a data-edit="backColor #0000FF">Blue</a></li>
                        <li><a data-edit="backColor #00FF00">Green</a></li>
                        <li><a data-edit="backColor #FF7F00">Orange</a></li>
                        <li><a data-edit="backColor #FF0000">Red</a></li>
                        <li><a data-edit="backColor #FFFF00">Yellow</a></li>
                    </ul>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Color"><i class="fa fa-font"></i>&nbsp;<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <p>&nbsp;&nbsp;&nbsp;Font Color:</p>
                        <li><a data-edit="foreColor #000000">Black</a></li>
                        <li><a data-edit="foreColor #0000FF">Blue</a></li>
                        <li><a data-edit="foreColor #30AD23">Green</a></li>
                        <li><a data-edit="foreColor #FF7F00">Orange</a></li>
                        <li><a data-edit="foreColor #FF0000">Red</a></li>
                        <li><a data-edit="foreColor #FFFF00">Yellow</a></li>
                    </ul>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
                    <a class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
                    <a class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><i class="fa fa-strikethrough"></i></a>
                    <a class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
                    <a class="btn btn-default" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
                    <a class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-outdent"></i></a>
                    <a class="btn btn-default" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
                    <a class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
                    <a class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
                    <a class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
                    <div class="dropdown-menu input-append">
                        <input placeholder="URL" type="text" data-edit="createLink" />
                        <button class="btn" type="button">Add</button>
                    </div>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
                    <a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
                    <a class="btn btn-default" data-edit="clearformat" title="Clear Formatting" onClick="$('#editor').html($('#editor').text());"><i class='glyphicon glyphicon-fire'></i></a>
                </div>
                </div>
            <div id="editor" class="lead" contenteditable="true" data-placeholder="测试结论">${bugReport.testResult}</div>
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
    <input type='hidden' name='testResult' id='testResult'/>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysiwyg/external/jquery.hotkeys.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysiwyg/external/google-code-prettify/prettify.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#editor").wysiwyg();
        $(".dropdown-menu > input").click(function (e) {
            e.stopPropagation();
        });
    });
</script>
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
        if ($('#editor').html() == "") {
            alert("测试结论不能为空");
            document.getElementById("editor").focus();
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
        $('#testResult').val($('#editor').html());
        return true;
    }
</script>
</body>
</html>
