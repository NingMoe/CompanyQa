package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.GroupEnum;
import com.koolearn.qa.constant.StatusEnum;
import com.koolearn.qa.model.Product;
import com.koolearn.qa.service.IGroupService;
import com.koolearn.qa.service.IProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihuiyan
 * @description
 * @date 2016/4/21
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = "/toProductManager")
    public String toProductManager(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("groups", groupService.getAllValidGroups());
        request.setAttribute("status", StatusEnum.values());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        List<Product> list = productService.getAllEnabledProducts();
        request.setAttribute("list",list);
        return "/productManager";
    }
    @RequestMapping(value = "/toAddProduct")
    public String toAddProduct(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("groups", groupService.getAllValidGroups());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return "/addProduct";
    }

    @RequestMapping(value = "/addProduct")
    public String addProduct(HttpServletRequest request, HttpServletResponse response) {
        Product dto = fillProductParam(request);
        productService.saveProduct(dto);
        request.setAttribute("groups", groupService.getAllValidGroups());
        request.setAttribute("status", StatusEnum.values());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return toProductManager(request,response);
    }

    @RequestMapping(value = "/getProducts")
    public String getProductsByCondition(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        Map<String,String> searchConditions = new HashMap<>();
        if (StringUtils.isNotBlank(request.getParameter("name"))) {
            map.put("name", request.getParameter("name"));
            searchConditions.put("name",request.getParameter("name"));
        }
        if (StringUtils.isNotBlank(request.getParameter("group"))) {
            map.put("group", Integer.parseInt(request.getParameter("group")));
            searchConditions.put("group",request.getParameter("group"));
        }
        if (StringUtils.isNotBlank(request.getParameter("leader"))) {
            map.put("leader", request.getParameter("leader"));
            searchConditions.put("leader",request.getParameter("leader"));
        }
        if (StringUtils.isNotBlank(request.getParameter("status"))) {
            //状态为0查询全部状态
            if(Integer.valueOf(request.getParameter("status")) != 0){
                map.put("status", Integer.parseInt(request.getParameter("status")));
            }
            searchConditions.put("status",request.getParameter("status"));
        }
        List<Product> list = productService.getProductsByConditions(map);
        request.setAttribute("searchConditions",searchConditions);
        request.setAttribute("list", list);
        request.setAttribute("groups", groupService.getAllValidGroups());
        request.setAttribute("allGroups",groupService.getAllGroups());
        request.setAttribute("status", StatusEnum.values());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return "/productManager";
    }

    @RequestMapping("/delProduct")
    public String delProduct(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            productService.delete(Integer.valueOf(request.getParameter("id")));
        }
        request.setAttribute("groups",groupService.getAllValidGroups());
        request.setAttribute("status", StatusEnum.values());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return toProductManager(request,response);
    }

    @RequestMapping("/toEditProject")
    public String editProduct(HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            Product product = productService.selectById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("product", product);
            request.setAttribute("groups", groupService.getAllValidGroups());
            List<LdapUser> userList = ldapService.queryUserAll();
            request.setAttribute("userList", userList);
        }
        return "/editProduct";
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(HttpServletRequest request, HttpServletResponse response) {
        Product product = fillProductParam(request);
        productService.updateProduct(product);
        request.setAttribute("groups", groupService.getAllValidGroups());
        request.setAttribute("status", StatusEnum.values());
        List<LdapUser> userList = ldapService.queryUserAll();
        request.setAttribute("userList", userList);
        return toProductManager(request,response);
    }

    private Product fillProductParam(HttpServletRequest request) {
        Product dto = new Product();
        if (StringUtils.isNotBlank(request.getParameter("id"))) {
            dto.setId(Integer.valueOf(request.getParameter("id")));
        }
        if (StringUtils.isNotBlank(request.getParameter("name"))) {
            dto.setName(request.getParameter("name"));
        }
        if (StringUtils.isNotBlank(request.getParameter("group"))) {
            dto.setGroup(Integer.parseInt(request.getParameter("group")));
        }
        if (StringUtils.isNotBlank(request.getParameter("leader"))) {
            dto.setLeader(request.getParameter("leader"));
        }
        if (StringUtils.isNotBlank(request.getParameter("description"))) {
            dto.setDescription(request.getParameter("description"));
        }
        return dto;
    }
}
