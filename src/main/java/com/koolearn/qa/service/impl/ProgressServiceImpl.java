package com.koolearn.qa.service.impl;

import com.koolearn.qa.constant.BugPlatformEnum;
import com.koolearn.qa.dao.jira.JiraMapper;
import com.koolearn.qa.dao.mantis.MantisMapper;
import com.koolearn.qa.dao.platform.ProgressMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.BugStatistics;
import com.koolearn.qa.model.Mantis;
import com.koolearn.qa.model.Progress;
import com.koolearn.qa.model.Project;
import com.koolearn.qa.service.IProgressService;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Service("progressService")
public class ProgressServiceImpl extends GenericServiceImpl<Progress, Integer> implements IProgressService {
    @Autowired
    private ProgressMapper progressMapper;

    @Autowired
    private MantisMapper mantisMapper;

    @Autowired
    private JiraMapper jiraMapper;

    @Override
    public GenericDao<Progress, Integer> getDao() {
        return progressMapper;
    }

    @Override
    public List<Progress> getProgressByProjectId(Integer projectId) {
        return progressMapper.selectByProjectId(projectId);
    }

    @Override
    public Progress getLastProgress(Integer projectId) {
        List<Progress> list =progressMapper.selectByProjectId(projectId);
        if(list != null){
            return list.get(list.size()-1);
        }
        return null;
    }

    @Override
    public Boolean isExist(Integer projectId, Date date) {
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",projectId);
        map.put("date",date);
        int count = progressMapper.selectCountByDate(map);
        if (count == 0) {
            return false;
        } else if (count > 0)
            return true;
        return false;
    }

    public BugStatistics statisticsBystaticEveryday(Map<String,Object> map, int platform) throws UnsupportedEncodingException {
        if (platform == Integer.valueOf(BugPlatformEnum.mantis.getValue())) {
            if (map.get("version") != null) {
                map.put("version", new String(map.get("version").toString().getBytes(), "latin1"));
            }
            if (map.get("category") != null) {
                map.put("category", new String(map.get("category").toString().getBytes(), "latin1"));
            }
            return mantisMapper.statisticsEveryday(map);
        }else if (platform == Integer.valueOf(BugPlatformEnum.jira.getValue())) {
            return jiraMapper.statisticsEveryday(map);
        }
        return null;
    }

    public String transHtmlContent(Project project,List<Progress> list){
        StringBuffer sb = new StringBuffer();
        sb.append("<html><table border='2' style='font-family: 宋体;font-size:18px;'><tr><td width='120'>项目名称</td><td colspan=\"7\">")
                .append(project.getName()).append("</td></tr><tr><td>测试人员</td><td colspan=\"7\">")
                .append(project.getTester()).append("</td></tr><tr><td>验收人员</td> <td colspan=\"7\">")
                .append(project.getProducter()).append("</td></tr><tr><td>开发人员</td><td colspan=\"7\">")
                .append(project.getDeveloper()).append("</td></tr><tr><td colspan=\"8\"><b>测试进度</b></td> </tr>");
        for(int i=0;i<list.size();i++){
            Progress progress = list.get(i);
            sb.append("<tr><td style='color: dodgerblue'>").append(progress.getDate()).append("</td><td colspan='7' style='color: dodgerblue'>").append(progress.getProgress()).append("</td></tr>");
        }
        sb.append("<tr><td colspan=\"8\"><b>存在的问题</b></td></tr>");
        for(int i=0;i<list.size();i++){
            Progress progress = list.get(i);
            sb.append("<tr><td style='color:dodgerblue'>").append(progress.getDate()).append("</td><td colspan='7' style='color: dodgerblue'>").append(progress.getProblem()).append("</td></tr>");
        }
        sb.append("<tr><td align='center'><b>日期</b></td><td align='center'><b>已测用例数</b></td><td align='center'><b>新提交bug数</b></td><td align='center'><b>已指派bug数</b></td><td align='center'><b>已确认bug数</b></td><td align='center'><b>已解决bug数</b></td><td align='center'><b>反馈bug数</b></td><td align='center'><b>已关闭bug数</b></td></tr>");
        for(int i=0;i<list.size();i++) {
            Progress progress = list.get(i);
            sb.append("<tr><td style='color: ' align='center'>").append(progress.getDate()).append("</td><td style='color: dodgerblue' align='center'>").append(progress.getTestCases()).append("</td><td style='color: dodgerblue' align='center'>")
                    .append(progress.getNewBugs()).append("</td><td style='color: dodgerblue' align='center'>").append(progress.getAssignedBugs()).append("</td><td style='color: dodgerblue'>")
                    .append(progress.getConfirmedBugs()).append("</td><td style='color: dodgerblue' align='center'>").append(progress.getResolvedBugs()).append("</td><td style='color: dodgerblue' align='center'>")
                    .append(progress.getFeedbackBugs()).append("</td><td style='color: dodgerblue' align='center'>").append(progress.getClosedBugs()).append("</td></tr>");
        }
        sb.append("<tr><td colspan=\"8\"><b>说明</b></td></tr><tr><td colspan=\"8\">1.“已测用例数”是指每天执行testlink的case数<br/>")
                .append("2.“新提交bug数”是指汇报当日新提交的bug数<br/>3.“已指派bug数”是指汇报时mantis上“已指派”状态的bug总数<br/>")
                .append("4.“已确认bug数”是指汇报时mantis上“已确认”状态的bug总数<br/>5.“已解决bug数”是指汇报时mantis上“已解决”状态的bug总数<br/>")
                .append("6.“反馈bug数”　是指汇报时mantis上“反馈”　状态的bug总数<br/>7.“已关闭bug数”是指汇报时mantis上“已关闭”状态的bug总数")
                .append("</td></tr></table></html>");
        return sb.toString();
    }

    public void exportExcel(HttpServletResponse response, Project project, List<Progress>list){
        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            String fileName = project.getName() + "测试进度报告.xls";
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setContentType("text/html;charset=UTF-8");
            //创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
            WritableWorkbook wk = Workbook.createWorkbook(output);
            generateExcel(wk, project, list);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateExcelFile(Project project, List<Progress>list){
        try {
            String fileName = project.getName() + "进度报告.xls";
            String randomPath = RandomStringUtils.randomAlphabetic(6);
            File dir = new File("c:\\temp\\" + randomPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String path = dir+"\\" + fileName;
            WritableWorkbook wk = Workbook.createWorkbook(new File(path));
            generateExcel(wk,project,list);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generateExcel(WritableWorkbook wk, Project project, List<Progress>list) {
        try {
            WritableSheet sheet = wk.createSheet(project.getName() + "进度报告", 0);
            sheet.getSettings().setDefaultColumnWidth(15);
            sheet.getSettings().setDefaultRowHeight(400);
            sheet.mergeCells(1, 0, 7, 0);
            sheet.mergeCells(1, 1, 7, 1);
            sheet.mergeCells(1, 2, 7, 2);
            sheet.mergeCells(1, 3, 7, 3);
            sheet.mergeCells(0, 4, 7, 4);
            WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
            cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
            cloumnTitleFormat.setWrap(true);
            cloumnTitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cloumnTitleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            Label lab_00 = new Label(0, 0, "项目名称", cloumnTitleFormat);
            Label lab_01 = new Label(0, 1, "测试人员", cloumnTitleFormat);
            Label lab_02 = new Label(0, 2, "验收人员", cloumnTitleFormat);
            Label lab_03 = new Label(0, 3, "开发人员", cloumnTitleFormat);
            Label lab_04 = new Label(0, 4, "测试进度", cloumnTitleFormat);
            sheet.addCell(lab_00);
            sheet.addCell(lab_01);
            sheet.addCell(lab_02);
            sheet.addCell(lab_03);
            sheet.addCell(lab_04);
            WritableCellFormat cloumnFormat = new WritableCellFormat();
            cloumnFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false));
            cloumnFormat.setWrap(true);
            cloumnFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cloumnFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            sheet.addCell(new Label(1, 0, project.getName(), cloumnFormat));
            sheet.addCell(new Label(1, 1, project.getTester(), cloumnFormat));
            sheet.addCell(new Label(1, 2, project.getProducter(), cloumnFormat));
            sheet.addCell(new Label(1, 3, project.getDeveloper(), cloumnFormat));
            int row = 5;
            if(list!=null){
                for(int i=0;i<list.size();i++){
                    Progress progress = list.get(i);
                    sheet.addCell(new Label(0, row, progress.getDate(), cloumnFormat));
                    sheet.mergeCells(1, row, 7, row);
                    sheet.addCell(new Label(1,row,progress.getProgress(),cloumnFormat));
                    row++;
                }
            }
            sheet.mergeCells(0, row, 7, row);
            sheet.addCell(new Label(0, row, "存在的问题", cloumnTitleFormat));
            row++;
            if(list!=null){
                for(int i=0;i<list.size();i++){
                    Progress progress = list.get(i);
                    sheet.addCell(new Label(0, row, progress.getDate(), cloumnFormat));
                    sheet.mergeCells(1, row, 7, row);
                    sheet.addCell(new Label(1,row,progress.getProblem(),cloumnFormat));
                    row++;
                }
            }
            sheet.addCell(new Label(0, row, "日期", cloumnTitleFormat));
            sheet.addCell(new Label(1, row, "已测用例数", cloumnTitleFormat));
            sheet.addCell(new Label(2, row, "新提交bug数", cloumnTitleFormat));
            sheet.addCell(new Label(3, row, "已指派bug数", cloumnTitleFormat));
            sheet.addCell(new Label(4, row, "已确认bug数", cloumnTitleFormat));
            sheet.addCell(new Label(5, row, "已解决bug数", cloumnTitleFormat));
            sheet.addCell(new Label(6, row, "反馈bug数", cloumnTitleFormat));
            sheet.addCell(new Label(7, row, "已关闭bug数", cloumnTitleFormat));
            row++;
            //定义数字格式
            NumberFormat nf = new NumberFormat("0");
            WritableCellFormat wcf = new WritableCellFormat(nf);
            wcf.setFont(new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false));
            wcf.setWrap(true);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
            if(list!=null){
                for(int i=0;i<list.size();i++){
                    Progress progress = list.get(i);
                    sheet.addCell(new Label(0, row, progress.getDate(), cloumnFormat));
                    sheet.addCell(new Number(1,row,progress.getTestCases(),wcf));
                    sheet.addCell(new Number(2,row,progress.getNewBugs(),wcf));
                    sheet.addCell(new Number(3,row,progress.getAssignedBugs(),wcf));
                    sheet.addCell(new Number(4,row,progress.getConfirmedBugs(),wcf));
                    sheet.addCell(new Number(5,row,progress.getResolvedBugs(),wcf));
                    sheet.addCell(new Number(6,row,progress.getFeedbackBugs(),wcf));
                    sheet.addCell(new Number(7,row,progress.getClosedBugs(),wcf));
                    row++;
                }
            }
            sheet.mergeCells(0, row, 7, row);
            sheet.addCell(new Label(0, row, "存在的问题", cloumnTitleFormat));
            row++;
            sheet.mergeCells(0, row, 7, row);
            sheet.setRowView(row, 2200);
            sheet.addCell(new Label(0, row, "1.“已测用例数”是指每天执行testlink的case数\n" +
                    "2.“新提交bug数”是指汇报当日新提交的bug数\n" +
                    "3.“已指派bug数”是指汇报时mantis上“已指派”状态的bug总数\n" +
                    "4.“已确认bug数”是指汇报时mantis上“已确认”状态的bug总数\n" +
                    "5.“已解决bug数”是指汇报时mantis上“已解决”状态的bug总数\n" +
                    "6.“反馈bug数”　是指汇报时mantis上“反馈”　状态的bug总数\n" +
                    "7.“已关闭bug数”是指汇报时mantis上“已关闭”状态的bug总数", cloumnFormat));
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
