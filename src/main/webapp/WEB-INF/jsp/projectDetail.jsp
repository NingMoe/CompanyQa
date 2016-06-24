<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihuiyan
  Date: 2016/5/24
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目详情页</title>
    <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/easyui.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-default col-md-11">
    <a href="javascript:history.go(-1);" class="col-md-offset-9" style="text-align: end"><h4>返回</h4></a>
</div>
<div class="panel panel-default col-md-11">
    <div class="panel-heading">
        <h2 class="panel-title">
            基本信息
        </h2>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" style="width: 98%">
            <div class="form-group">
                <label class="col-md-2 control-label" for="name">项目名称:</label>
                <label id="name" class="col-md-2 control-label" style="text-align:left">${project.name}</label>
                <label class="col-md-2 control-label" for="bugPlatform">bug平台:</label>
                <label id="bugPlatform" class="col-md-2 control-label"
                       style="text-align:left">
                    <c:forEach items="${bugPlatform}" var="platform">
                        <c:choose>
                            <c:when test="${platform.value==project.bugPlatform}">${platform.text}</c:when>
                        </c:choose>
                    </c:forEach>
                </label>
                <c:if test="${project.bugPlatform==1}">
                    <label class="col-md-2 control-label" for="projectMantis">mantis项目id:</label>
                    <label id="projectMantis" class="col-md-2 control-label"
                           style="text-align:left">${project.projectMantis}</label>
                </c:if>
                <c:if test="${project.bugPlatform==2}">
                    <label class="col-md-2 control-label" for="pKey">jira键值:</label>
                    <label id="pKey" class="col-md-2 control-label"
                           style="text-align:left">${project.pKey}</label>
                </c:if>
            </div>
            <c:if test="${project.bugPlatform==1}">
                <div class="form-group">
                    <label class="col-md-2 control-label" for="categoryMantis">mantis项目分类:</label>
                    <label id="categoryMantis" class="col-md-2 control-label"
                           style="text-align:left">${project.categoryMantis}</label>
                    <label class="col-md-2 control-label" for="versionMantis">mantis版本号:</label>
                    <label id="versionMantis" class="col-md-2 control-label"
                           style="text-align:left">${project.versionMantis}</label>
                </div>
            </c:if>
            <div class="form-group">
                <label class="col-md-2 control-label" for="productId">所属系统:</label>
                <label id="productId" class="col-md-2 control-label" style="text-align:left">
                    <c:forEach var="productMap" items="${productMap}">
                        <c:if test="${project.productId==productMap.key}">${productMap.value}</c:if>
                    </c:forEach>
                </label>
                <label class="col-md-2 control-label">产品人员:</label>
                <label id="producter" class="col-md-2 control-label"
                       style="text-align:left">${project.producter}</label>
                <label class="col-md-2 col-sm-offset-0 control-label" for="developer">开发人员:</label>
                <label id="developer" class="col-md-2 control-label"
                       style="text-align:left">${project.developer}</label>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="tester">测试人员:</label>
                <label id="tester" class="col-md-2 control-label" style="text-align:left">${project.tester}</label>
                <label class="col-md-2 control-label" for="startTime2plan">计划开始时间:</label>
                <label id="startTime2plan" class="col-md-2 control-label"
                       style="text-align:left">${project.startTime2plan}</label>
                <label class="col-md-2 control-label" for="endTime2plan">计划结束时间:</label>
                <label id="endTime2plan" class="col-md-2 control-label"
                       style="text-align:left">${project.endTime2plan}</label>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="startTime2actual">实际开始时间:</label>
                <label id="startTime2actual" class="col-md-2 control-label"
                       style="text-align:left">${project.startTime2actual}</label>
                <label class="col-md-2 control-label" for="endTime2actual">实际结束时间:</label>
                <label id="endTime2actual" class="col-md-2 control-label"
                       style="text-align:left">${project.endTime2actual}</label>
                <label class="col-md-2 control-label" for="projectStatus">项目完成状态:</label>
                <label id="projectStatus" class="col-md-2 control-label" style="text-align:left">
                    <c:forEach var="projectStatus" items="${projectStatus}">
                        <c:if test="${project.projectStatus==projectStatus.value}">${projectStatus.text}</c:if>
                    </c:forEach>
                </label>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="days">提前/延期天数:</label>
                <label id="days" class="col-md-2 control-label" style="text-align:left">${project.days}</label>
                <label class="col-md-2 control-label" for="requirementDays">需求阶段天数:</label>
                <label id="requirementDays" class="col-md-2 control-label"
                       style="text-align:left">${project.requirementDays}</label>
                <label class="col-md-2 control-label" for="developDays">代码阶段天数:</label>
                <label id="developDays" class="col-md-2 control-label"
                       style="text-align:left">${project.developDays}</label>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="testDays">测试阶段天数:</label>
                <label id="testDays" class="col-md-2 control-label" style="text-align:left">${project.testDays}</label>
                <label class="col-md-2 control-label" for="acceptanceDays">业务验收阶段天数:</label>
                <label id="acceptanceDays" class="col-md-2 control-label"
                       style="text-align:left">${project.acceptanceDays}</label>
                <label class="col-md-2 control-label" for="onlineDays">上线试运行阶段天数:</label>
                <label id="onlineDays" class="col-md-2 control-label"
                       style="text-align:left">${project.onlineDays}</label>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label" for="description">项目说明</label>
                <label id="description" class="col-md-10 control-label"
                       style="text-align:left">${project.description}</label>
            </div>
        </form>
    </div>
</div>
<c:if test="${treeJson!=null}">
    <div class="panel panel-default col-md-11">
        <div class="panel-heading">
            <h2 class="panel-title">
                测试计划
            </h2>
        </div>
        <div class="panel-body">
            <table id="tree">
                <thead>
                <tr>
                    <th data-options=" field: 'name'">任务名称</th>
                    <th data-options="field:'duration'">工期(天)</th>
                    <th data-options="field:'start'">开始时间</th>
                    <th data-options="field:'finish'">完成时间</th>
                    <th data-options="field:'beforeTask'">前置任务</th>
                    <th data-options="field:'resource'">资源</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${progressList!=null && progressList.size() != 0 }">
    <div class="panel panel-default col-md-11">
        <div class="panel-heading">
            <h2 class="panel-title">
                进度报告
            </h2>
        </div>
        <div class="panel-body">
            <table class="table-bordered">
                <tr>
                    <td width="130px"><b>项目名称</b></td>
                    <td colspan="7">${project.name}</td>
                </tr>
                <tr>
                    <td><b>测试人员</b></td>
                    <td colspan="7">${project.tester}</td>
                </tr>
                <tr>
                    <td><b>验收人员</b></td>
                    <td colspan="7">${project.producter}</td>
                </tr>
                <tr>
                    <td><b>开发人员</b></td>
                    <td colspan="7">${project.developer}</td>
                </tr>
                <tr>
                    <td colspan="8"><b>测试进度</b></td>
                </tr>
                <c:forEach items="${progressList}" var="progress">
                    <tr>
                        <td>${progress.date}</td>
                        <td colspan="7">${progress.progress}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8"><b>存在的问题</b></td>
                </tr>
                <c:forEach items="${progressList}" var="progress1">
                    <tr>
                        <td>${progress1.date}</td>
                        <td colspan="7">${progress1.problem}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td align="center"><b>日期</b></td>
                    <td align="center"><b>已测用例数</b></td>
                    <td align="center"><b>新提交bug数</b></td>
                    <td align="center"><b>已指派bug数</b></td>
                    <td align="center"><b>已确认bug数</b></td>
                    <td align="center"><b>已解决bug数</b></td>
                    <td align="center"><b>反馈bug数</b></td>
                    <td align="center"><b>已关闭bug数</b></td>
                </tr>
                <c:forEach items="${progressList}" var="progress2">
                    <tr>
                        <td align="center">${progress2.date}</td>
                        <td align="center">${progress2.testCases}</td>
                        <td align="center">${progress2.newBugs}</td>
                        <td align="center">${progress2.assignedBugs}</td>
                        <td align="center">${progress2.confirmedBugs}</td>
                        <td align="center">${progress2.resolvedBugs}</td>
                        <td align="center">${progress2.feedbackBugs}</td>
                        <td align="center">${progress2.closedBugs}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8"><b>说明</b></td>
                </tr>
                <tr>
                    <td colspan="8">
                        1.“已测用例数”是指每天执行testlink的case数<br/>

                        2.“新提交bug数”是指汇报当日新提交的bug数<br/>

                        3.“已指派bug数”是指汇报时mantis上“已指派”状态的bug总数<br/>

                        4.“已确认bug数”是指汇报时mantis上“已确认”状态的bug总数<br/>

                        5.“已解决bug数”是指汇报时mantis上“已解决”状态的bug总数<br/>

                        6.“反馈bug数”　是指汇报时mantis上“反馈”　状态的bug总数<br/>

                        7.“已关闭bug数”是指汇报时mantis上“已关闭”状态的bug总数
                    </td>
                </tr>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${bugReport!=null}">
<div class="panel panel-default col-md-11">
    <div class="panel-heading">
        <h2 class="panel-title">
            缺陷报告
        </h2>
    </div>
    <div class="panel-body">
        <table class="table-bordered">
            <tr>
                <td colspan="7" align="center"><b>缺陷报告</b></td>
            </tr>
            <tr>
                <td width="150px"><b>测试人员</b></td>
                <td colspan="6">${project.tester}</td>
            </tr>
            <tr>
                <td><b>开发人员</b></td>
                <td colspan="6">${project.producter}</td>
            </tr>
            <tr>
                <td><b>测试计划时间</b></td>
                <td colspan="6">
                    <c:if test="${project.startTime2plan!=null}">${project.startTime2plan}</c:if>
                    <c:if test="${project.endTime2plan!=null && project.endTime2plan!=null}">---</c:if>
                    <c:if test="${project.endTime2plan!=null}">${project.endTime2plan}</c:if>
                </td>
            </tr>
            <tr>
                <td><b>实际测试时间</b></td>
                <td colspan="6">
                    <c:if test="${project.startTime2actual!=null}">${project.startTime2actual}</c:if>
                    <c:if test="${project.endTime2actual!=null && project.endTime2actual!=null}">---</c:if>
                    <c:if test="${project.endTime2actual!=null}">${project.endTime2actual}</c:if>
                </td>
                </td>
            </tr>
            <tr>
                <td><b>测试环境</b></td>
                <td colspan="6">${bugReport.environment}</td>
            </tr>
            <tr>
                <td><b>未解决问题</b></td>
                <td colspan="6">${bugReport.unresolved}</td>
            </tr>
            <tr>
                <td><b>反馈问题</b></td>
                <td colspan="6">${bugReport.feedback}</td>
            </tr>
            <tr>
                <td><b>bug统计</b></td>
                <td align="center"><b>严重错误</b></td>
                <td align="center"><b>次要错误</b></td>
                <td align="center"><b>一般错误</b></td>
                <td align="center"><b>布局错误</b></td>
                <td align="center"><b>文字错误</b></td>
                <td align="center"><b>新特性</b></td>
            </tr>
            <tr>
                <td><b>数量</b></td>
                <td align="center">${bugReport.seriousBugs}</td>
                <td align="center">${bugReport.secondaryBugs}</td>
                <td align="center">${bugReport.generalBugs}</td>
                <td align="center">${bugReport.layoutBugs}</td>
                <td align="center">${bugReport.textBugs}</td>
                <td align="center">${bugReport.newfeatureBugs}</td>
            </tr>
            <tr>
                <td><b>bug总数</b></td>
                <td colspan="6">${bugReport.totalBugs}</td>
            </tr>
            <tr>
                <td><b>测试用例总数</b></td>
                <td colspan="6">${bugReport.testCases}</td>
            </tr>
            <tr>
                <td><b>测试用例执行总数</b></td>
                <td colspan="6">${bugReport.executedCases}</td>
            </tr>
            <tr>
                <td colspan="7"><b>bug级别描述</b></td>
            </tr>
            <tr>
                <td colspan="7">
                    严重错误：系统不能访问、主流程断点、功能模块未实现、财务数据问题；<br/>
                    次要错误：流程断点、错误页面、功能未实现；<br/>
                    一般错误：功能基本实现，但边界上存在问题；<br/>
                    布局错误：页面样式问题；<br/>
                    文字错误：错别字、中文乱码；<br/>
                    新特性：建议、需求
                </td>
            </tr>
            <tr>
                <td colspan="7"><b>测试结论</b></td>
            </tr>
            <tr>
                <td colspan="7">${bugReport.testResult}</td>
            </tr>
        </table>
    </div>
    </c:if>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/plugins/jquery/jquery-1.11.1.min.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/jquery.treegrid.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/assets/plugins/easyui-treeGrid/jquery.easyui.min.js"></script>
<script>
    $('#tree').treegrid({
        rownumbers: true,
        idField: 'id',
        treeField: 'name',
        data:${treeJson}
    });
</script>
</body>
</html>
