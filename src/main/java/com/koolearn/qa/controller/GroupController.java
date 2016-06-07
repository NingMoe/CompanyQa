package com.koolearn.qa.controller;

import com.koolearn.qa.constant.StatusEnum;
import com.koolearn.qa.model.Group;
import com.koolearn.qa.service.IGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/7
 */
@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = "/toGroupManager")
    public String toGroupManager(HttpServletRequest request, HttpServletResponse response) {
        List<Group> list =groupService.getAllGroups();
        request.setAttribute("list",list);
        request.setAttribute("status", StatusEnum.values());
        return "/groupManager";
    }

    @RequestMapping(value = "/toAddGroup")
    public String toAddGroup(HttpServletRequest request, HttpServletResponse response){
        return "/addGroup";
    }

    @RequestMapping(value = "/toEditGroup")
    public String toEditGroup(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            Group group = groupService.selectById(Integer.valueOf(id));
            request.setAttribute("group",group);
        }
        return "/editGroup";
    }

    @RequestMapping(value = "/addGroup")
    public String addGroup(HttpServletRequest request, HttpServletResponse response){
        groupService.insert(fillGroupParam(request));
        return toGroupManager(request,response);
    }

    @RequestMapping(value = "/editGroup")
    public String editGroup(HttpServletRequest request, HttpServletResponse response){
        groupService.update(fillGroupParam(request));
        return toGroupManager(request,response);
    }

    @RequestMapping(value = "/recoveryGroup")
    public String recoveryGroup(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            groupService.recovery(Integer.valueOf(id));
        }
        return toGroupManager(request,response);
    }

    @RequestMapping(value = "/delGroup")
    public String delGroup(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            groupService.delete(Integer.valueOf(id));
        }
        return toGroupManager(request,response);
    }

    private Group fillGroupParam(HttpServletRequest request){
        Group dto = new Group();
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            dto.setId(Integer.valueOf(request.getParameter("id")));
        }
        dto.setName(request.getParameter("name"));
        dto.setDescription(request.getParameter("description"));
        return dto;
    }
}
