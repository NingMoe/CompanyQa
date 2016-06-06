package com.koolearn.qa.controller;

import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.Plan;
import com.koolearn.qa.service.IPlanService;
import com.koolearn.qa.util.FileUtil;
import com.koolearn.qa.util.MppUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private IPlanService planService;

    @RequestMapping(value = "/toPlanDetail")
    public String toPlanDetail(HttpServletRequest request, HttpServletResponse response){
        String projectId = request.getParameter("projectId");
        if(StringUtils.isNotBlank(projectId)){
            request.setAttribute("projectId",projectId);
            Plan plan = planService.getPlanByProjectId(Integer.valueOf(projectId));
            if(plan != null){
                request.setAttribute("plan",plan);
               String filePath = FileUtil.getAbsolutePath(plan.getFilePath());
                request.setAttribute("treeJson",MppUtil.dumpJson(filePath));
            }
        }else{
            //TODO异常处理
        }
        return "/planDetail";
    }

    @RequestMapping(value = "/upload")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile filemark = multipartRequest.getFile("planFile");
        try {
            byte[] bb = filemark.getBytes();
            if (bb.length > Constant.MAX_IMAGE_SIZE) {
                String errInfo = "文件过大,最大大小:"+Constant.MAX_IMAGE_SIZE/1024+"k";
                request.setAttribute("state",errInfo);
                return toPlanDetail(request,response);
            }
            String originalFilename = filemark.getOriginalFilename();
            if(StringUtils.isBlank(originalFilename)){
                String errorInfo = "请选择文件!";
                request.setAttribute("state",errorInfo);
                return toPlanDetail(request,response);
            }
            String name = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if(StringUtils.isBlank(name) || !originalFilename.endsWith(".mpp")){
                String errorInfo = "仅支持mpp格式的文件!";
                request.setAttribute("state",errorInfo);
                return toPlanDetail(request,response);
            }
            String path = FileUtil.generateFileName(name);
            if(StringUtils.isBlank(path)){
                String errorInfo = "GererateFileName failed!";
                request.setAttribute("state",errorInfo);
                return toPlanDetail(request,response);
            }
            FileCopyUtils.copy(bb, new File(path));
            String relPath = FileUtil.getRelativePath(path);
            String projectId = request.getParameter("projectId");
            Plan plan = planService.getPlanByProjectId(Integer.valueOf(projectId));
            if(plan != null){
                plan.setFilePath(relPath);
                plan.setFileName(originalFilename);
                planService.update(plan);
                request.setAttribute("plan",plan);
            }else{
                plan = new Plan();
                plan.setProjectId(Integer.valueOf(projectId));
                plan.setFileName(originalFilename);
                plan.setFilePath(relPath);
                plan.setCreateTime(new Date());
                plan.setStatus(1);
                planService.insert(plan);
                request.setAttribute("plan",plan);
            }
            request.setAttribute("treeJson",MppUtil.dumpJson(path));
            return "/planDetail";
        } catch (IOException e) {
            request.setAttribute("state",e.getMessage());
            return toPlanDetail(request,response);
        }
    }

    @RequestMapping("/download")
    public String downLoadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String projectId = request.getParameter("projectId");
        if(StringUtils.isNotBlank(projectId)){
            request.setAttribute("projectId",projectId);
            Plan plan = planService.getPlanByProjectId(Integer.valueOf(projectId));
            request.setAttribute("plan",plan);
            if(plan != null){
                String downLoadPath = FileUtil.getAbsolutePath(plan.getFilePath());
                long fileLength = new File(downLoadPath).length();
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(plan.getFileName().getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));
                try {
                    bis = new BufferedInputStream(new FileInputStream(downLoadPath));
                    bos = new BufferedOutputStream(response.getOutputStream());
                    byte[] buff = new byte[2048];
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                    bis.close();
                    bos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                request.setAttribute("treeJson",MppUtil.dumpJson(downLoadPath));
            }
        }else{
            //TODO异常处理
        }
        return "/planDetail";
    }
}
