package com.koolearn.qa.service.impl;

import com.koolearn.qa.constant.BugPlatformEnum;
import com.koolearn.qa.dao.jira.JiraMapper;
import com.koolearn.qa.dao.mantis.MantisMapper;
import com.koolearn.qa.dao.platform.BugReportMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.BugReport;
import com.koolearn.qa.model.BugStatistics;
import com.koolearn.qa.model.Project;
import com.koolearn.qa.service.IBugReportService;
import com.koolearn.qa.util.CommonUtil;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.Boolean;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Service("bugreportService")
public class BugReportServiceImpl extends GenericServiceImpl<BugReport, Integer> implements IBugReportService {
    @Autowired
    private BugReportMapper bugReportMapper;

    @Autowired
    private MantisMapper mantisMapper;

    @Autowired
    private JiraMapper jiraMapper;

    @Override
    public GenericDao<BugReport, Integer> getDao() {
        return bugReportMapper;
    }

    @Override
    public BugReport getBugReportByProjectId(Integer projectId) {
        return bugReportMapper.selectByProjectId(projectId);
    }

    @Override
    public Boolean isExist(Integer projectId) {
        int count = bugReportMapper.selectCountByProjectId(projectId);
        if (count == 0) {
            return false;
        } else if (count > 0)
            return true;
        return false;
    }

    public BugStatistics statisticsBySeriousness(Map<String, Object> map, int platform) throws UnsupportedEncodingException {
        if (platform == Integer.valueOf(BugPlatformEnum.mantis.getValue())) {
            if (map.get("version") != null) {
                map.put("version", new String(map.get("version").toString().getBytes(), "latin1"));
            }
            if (map.get("category") != null) {
                map.put("category", new String(map.get("category").toString().getBytes(), "latin1"));
            }
            return mantisMapper.statisticsBySeriousness(map);
        } else if (platform == Integer.valueOf(BugPlatformEnum.jira.getValue())) {
            return jiraMapper.statisticsBySeriousness(map);
        }
        return null;
    }

    public String transHtmlContent(Project project, BugReport bugReport) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html><table border='2' style='font-family: 宋体;font-size:18px;'><tr><td colspan='7' align='center'><b>缺陷报告</b></td></tr><tr><td>测试人员</td><td colspan='6'>");
        if (StringUtils.isNotBlank(project.getTester())) {
            sb.append(project.getTester());
        }
        sb.append("</td></tr><tr><td>开发人员</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(project.getProducter())) {
            sb.append(project.getDeveloper());
        }
        sb.append("</td></tr><tr><td>测试计划时间</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(project.getStartTime2plan())) {
            sb.append(project.getStartTime2plan());
            if (StringUtils.isNotBlank(project.getEndTime2plan())) {
                sb.append("---").append(project.getEndTime2plan());
            }
        } else {
            if (StringUtils.isNotBlank(project.getEndTime2plan())) {
                sb.append(project.getEndTime2plan());
            }
        }
        sb.append("</td></tr><tr><td>实际测试时间</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(project.getStartTime2actual())) {
            sb.append(project.getStartTime2actual());
            if (StringUtils.isNotBlank(project.getEndTime2actual())) {
                sb.append("---").append(project.getEndTime2actual());
            }
        } else {
            if (StringUtils.isNotBlank(project.getEndTime2actual())) {
                sb.append(project.getEndTime2actual());
            }
        }
        sb.append("</td></tr><tr><td>测试环境</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(bugReport.getEnvironment())) {
            sb.append(bugReport.getEnvironment());
        }
        sb.append("</td></tr><tr><td>未解决问题</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(bugReport.getUnresolved())) {
            sb.append(bugReport.getUnresolved());
        }
        sb.append("</td></tr><tr><td>反馈问题</td> <td colspan=\"6\">");
        if (StringUtils.isNotBlank(bugReport.getFeedback())) {
            sb.append(bugReport.getFeedback());
        }
        sb.append("</td></tr><tr><td>bug统计</td><td align='center'>严重错误</td><td align='center'>次要错误</td><td align='center'>一般错误</td><td align='center'>布局错误</td><td align='center'>文字错误</td><td align='center'>新特性</td></tr><tr><td>数量</td><td align='center'>")
                .append(bugReport.getSeriousBugs()).append("</td><td align='center'>").append(bugReport.getSeriousBugs()).append("</td><td align='center'>").append(bugReport.getGeneralBugs())
                .append("</td><td align='center'>").append(bugReport.getLayoutBugs()).append("</td><td align='center'>").append(bugReport.getTextBugs()).append("</td><td align='center'>").append(bugReport.getNewfeatureBugs())
                .append("</td></tr><tr><td>bug总数</td><td colspan=\"6\">").append(bugReport.getTotalBugs()).append("</td></tr><tr><td>测试用例总数</td><td colspan=\"6\">")
                .append(bugReport.getTestCases()).append("</td></tr><tr><td>测试用例执行总数</td><td colspan=\"6\">").append(bugReport.getExecutedCases())
                .append("</td></tr><tr><td colspan=\"7\">bug级别描述</td></tr><tr><td colspan=\"7\">")
                .append("严重错误：系统不能访问、主流程断点、功能模块未实现、财务数据问题；<br/>次要错误：流程断点、错误页面、功能未实现；<br/>")
                .append("一般错误：功能基本实现，但边界上存在问题；<br/>布局错误：页面样式问题；<br/>文字错误：错别字、中文乱码；<br/>新特性：建议、需求")
                .append("</td></tr><tr><td colspan=\"7\">测试结论</td></tr><tr><td colspan=\"7\">").append(bugReport.getTestResult())
                .append("</td></tr></table></html>");
        return sb.toString();
    }

    public void exportExcel(HttpServletResponse response, Project project, BugReport bugReport) {
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            String fileName = project.getName() + "测试报告.xls";
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setContentType("text/html;charset=UTF-8");
            //创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
            WritableWorkbook wk = Workbook.createWorkbook(output);
            generateExcel(wk, project, bugReport);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateExcelFile(Project project, BugReport bugReport) {
        try {
            String fileName = project.getName() + "缺陷报告.xls";
            String randomPath = RandomStringUtils.randomAlphabetic(6);
            File dir = new File("c:\\temp\\" + randomPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String path = dir + "\\" + fileName;
            WritableWorkbook wk = Workbook.createWorkbook(new File(path));
            generateExcel(wk, project, bugReport);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generateExcel(WritableWorkbook wk, Project project, BugReport bugReport) {
        try {
            WritableSheet sheet = wk.createSheet(project.getName() + "缺陷报告", 0);
            sheet.getSettings().setDefaultColumnWidth(18);
            sheet.getSettings().setDefaultRowHeight(200);
            sheet.mergeCells(0, 0, 6, 0);
            sheet.mergeCells(1, 1, 6, 1);
            sheet.mergeCells(1, 2, 6, 2);
            sheet.mergeCells(1, 3, 6, 3);
            sheet.mergeCells(1, 4, 6, 4);
            sheet.mergeCells(1, 5, 6, 5);
            sheet.mergeCells(1, 6, 6, 6);
            sheet.mergeCells(1, 7, 6, 7);
            sheet.mergeCells(1, 10, 6, 10);
            sheet.mergeCells(1, 11, 6, 11);
            sheet.mergeCells(1, 12, 6, 12);
            sheet.mergeCells(0, 13, 6, 13);
            sheet.mergeCells(0, 14, 6, 14);
            sheet.mergeCells(0, 15, 6, 15);
            sheet.mergeCells(0, 16, 6, 16);
            sheet.setRowView(0, 300);
            WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat titleFormat = new WritableCellFormat();
            titleFormat.setFont(titleFont);
            titleFormat.setBackground(Colour.GRAY_25);
            titleFormat.setWrap(true);
            titleFormat.setAlignment(Alignment.CENTRE);
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            Label lab_00 = new Label(0, 0, "缺陷报告", titleFormat);
            sheet.addCell(lab_00);
            WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
            cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
            cloumnTitleFormat.setWrap(true);
            cloumnTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cloumnTitleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            Label lab_01 = new Label(0, 1, "测试人员", cloumnTitleFormat);
            Label lab_02 = new Label(0, 2, "开发人员", cloumnTitleFormat);
            Label lab_03 = new Label(0, 3, "测试计划时间", cloumnTitleFormat);
            Label lab_04 = new Label(0, 4, "实际测试时间", cloumnTitleFormat);
            Label lab_05 = new Label(0, 5, "测试环境", cloumnTitleFormat);
            Label lab_06 = new Label(0, 6, "未解决问题", cloumnTitleFormat);
            Label lab_07 = new Label(0, 7, "反馈问题", cloumnTitleFormat);
            Label lab_08 = new Label(0, 8, "bug统计", cloumnTitleFormat);
            Label lab_18 = new Label(1, 8, "严重错误", cloumnTitleFormat);
            Label lab_28 = new Label(2, 8, "次要错误", cloumnTitleFormat);
            Label lab_38 = new Label(3, 8, "一般错误", cloumnTitleFormat);
            Label lab_48 = new Label(4, 8, "布局错误", cloumnTitleFormat);
            Label lab_58 = new Label(5, 8, "文字错误", cloumnTitleFormat);
            Label lab_68 = new Label(6, 8, "新特性", cloumnTitleFormat);
            Label lab_09 = new Label(0, 9, "数量", cloumnTitleFormat);
            Label lab_010 = new Label(0, 10, "bug总数", cloumnTitleFormat);
            Label lab_011 = new Label(0, 11, "测试用例总数", cloumnTitleFormat);
            Label lab_012 = new Label(0, 12, "测试用例执行总数", cloumnTitleFormat);
            Label lab_013 = new Label(0, 13, "bug级别描述", cloumnTitleFormat);
            Label lab_015 = new Label(0, 15, "测试结论", cloumnTitleFormat);
            sheet.addCell(lab_01);
            sheet.addCell(lab_02);
            sheet.addCell(lab_03);
            sheet.addCell(lab_04);
            sheet.addCell(lab_05);
            sheet.addCell(lab_06);
            sheet.addCell(lab_07);
            sheet.addCell(lab_08);
            sheet.addCell(lab_18);
            sheet.addCell(lab_28);
            sheet.addCell(lab_38);
            sheet.addCell(lab_48);
            sheet.addCell(lab_58);
            sheet.addCell(lab_68);
            sheet.addCell(lab_09);
            sheet.addCell(lab_010);
            sheet.addCell(lab_011);
            sheet.addCell(lab_012);
            sheet.addCell(lab_013);
            sheet.addCell(lab_015);
            WritableCellFormat cloumnFormat = new WritableCellFormat();
            cloumnFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false));
            cloumnFormat.setWrap(true);
            cloumnFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cloumnFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            sheet.addCell(new Label(1, 1, project.getTester(), cloumnFormat));
            sheet.addCell(new Label(1, 2, project.getDeveloper(), cloumnFormat));
            StringBuffer timt2plan = new StringBuffer();
            if (StringUtils.isNotBlank(project.getStartTime2plan())) {
                timt2plan.append(project.getStartTime2plan());
                if (StringUtils.isNotBlank(project.getEndTime2plan())) {
                    timt2plan.append("---").append(project.getEndTime2plan());
                }
            } else {
                if (StringUtils.isNotBlank(project.getEndTime2plan())) {
                    timt2plan.append(project.getEndTime2plan());
                }
            }
            sheet.addCell(new Label(1, 3, timt2plan.toString(), cloumnFormat));
            StringBuffer time2actual = new StringBuffer();
            if (StringUtils.isNotBlank(project.getStartTime2actual())) {
                time2actual.append(project.getStartTime2actual());
                if (StringUtils.isNotBlank(project.getEndTime2actual())) {
                    time2actual.append("---").append(project.getEndTime2actual());
                }
            } else {
                if (StringUtils.isNotBlank(project.getEndTime2actual())) {
                    time2actual.append(project.getEndTime2actual());
                }
            }
            sheet.addCell(new Label(1, 4, time2actual.toString(), cloumnFormat));
            sheet.addCell(new Label(1, 5, bugReport.getEnvironment(), cloumnFormat));
            sheet.addCell(new Label(1, 6, bugReport.getUnresolved(), cloumnFormat));
            sheet.addCell(new Label(1, 7, bugReport.getFeedback(), cloumnFormat));
            Label lab_014 = new Label(0, 14,
                    "严重错误：系统不能访问、主流程断点、功能模块未实现、财务数据问题；\n次要错误：流程断点、错误页面、功能未实现；\n" +
                            "一般错误：功能基本实现，但边界上存在问题；\n布局错误：页面样式问题；\n文字错误：错别字、中文乱码；\n" +
                            "新特性：建议、需求\n", cloumnFormat);
            sheet.setRowView(14, 2100);
            sheet.addCell(lab_014);
            //定义数字格式
            NumberFormat nf = new NumberFormat("0");
            WritableCellFormat wcf = new WritableCellFormat(nf);
            wcf.setFont(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false));
            wcf.setWrap(true);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            //类似于Label对象，区别Label表示文本数据，Number表示数值型数据
            Number numlab_19 = new Number(1, 9, bugReport.getSeriousBugs(), wcf);
            Number numlab_29 = new Number(2, 9, bugReport.getSecondaryBugs(), wcf);
            Number numlab_39 = new Number(3, 9, bugReport.getGeneralBugs(), wcf);
            Number numlab_49 = new Number(4, 9, bugReport.getLayoutBugs(), wcf);
            Number numlab_59 = new Number(5, 9, bugReport.getTextBugs(), wcf);
            Number numlab_69 = new Number(6, 9, bugReport.getNewfeatureBugs(), wcf);
            wcf.setAlignment(Alignment.LEFT);
            Number numlab_110 = new Number(1, 10, bugReport.getTotalBugs(), wcf);
            Number numlab_111 = new Number(1, 11, bugReport.getTestCases(), wcf);
            Number numlab_112 = new Number(1, 12, bugReport.getExecutedCases(), wcf);
            sheet.addCell(numlab_19);
            sheet.addCell(numlab_29);
            sheet.addCell(numlab_39);
            sheet.addCell(numlab_49);
            sheet.addCell(numlab_59);
            sheet.addCell(numlab_69);
            sheet.addCell(numlab_110);
            sheet.addCell(numlab_111);
            sheet.addCell(numlab_112);
            WritableCellFormat lab_016Format = new WritableCellFormat();
            lab_016Format.setFont(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false));
            lab_016Format.setWrap(true);
            lab_016Format.setVerticalAlignment(VerticalAlignment.TOP);
            lab_016Format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            sheet.setRowView(16, 3000);
            sheet.addCell(new Label(0, 16, CommonUtil.html2Text(bugReport.getTestResult()), lab_016Format));
            //将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
            wk.write();
            //操作完成时，关闭对象，释放占用的内存空间
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
