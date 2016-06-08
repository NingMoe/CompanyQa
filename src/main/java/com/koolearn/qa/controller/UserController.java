package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.Role;
import com.koolearn.qa.model.User;
import com.koolearn.qa.model.UserRole;
import com.koolearn.qa.service.IRoleService;
import com.koolearn.qa.service.IUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Subject subject = SecurityUtils.getSubject();
            // 已登陆则 跳到首页
            if (subject.isAuthenticated()) {
                return "/index";
            }
            // 身份验证
            subject.login(new UsernamePasswordToken(username, password));
            // 验证成功在Session中保存用户信息
            if (username.indexOf(Constant.EMAIL_SUFFIX) < 0) {
                username = username + Constant.EMAIL_SUFFIX;
            }
            String filter = "(mail=" + username + ")";
            List<LdapUser> list = ldapService.queryUser(filter);
            LdapUser authUserInfo = list.get(0);
            User user = new User();
            user.setUserInfo(authUserInfo);
            user.setPassword(password);
            request.getSession().setAttribute("user", user);
        } catch (AuthenticationException e) {
            // 身份验证失败
            request.setAttribute("error", "用户名或密码错误 ！");
            return "login";
        }
        return "/index";

    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("userInfo");
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping(value = "/user/toSecurityManager")
    public String toSecurityManager(HttpServletRequest request) {
        List<UserRole> list = userRoleService.selectAllUsers();
        request.setAttribute("userRoleList", list);
        return "/securityManager";
    }

    @RequestMapping(value = "/user/delUserRole")
    public String delUserRole(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            userRoleService.delete(Integer.valueOf(id));
        }
        return toSecurityManager(request);
    }

    /**
     * 更新用户角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/editUserRole")
    public String editUerRole(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            UserRole userRole = filluserRoleParam(request);
            userRoleService.updateUserRole(userRole);
        }
        return toSecurityManager(request);
    }
    @RequestMapping(value = "/user/toEditUserRole")
    public String toEditUserRole(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            UserRole userRole = userRoleService.selectById(Integer.valueOf(id));
            request.setAttribute("userRole",userRole);
        }
        List<Role> roles = roleService.selectAllRoles();
        request.setAttribute("roles", roles);
        return "editUserRole";
    }

    @RequestMapping(value = "/user/toAddUserRole")
    public String toAddUserRole(HttpServletRequest request) {
        List<Role> roles = roleService.selectAllRoles();
        request.setAttribute("roles", roles);
        return "addUserRole";
    }

    /**
     * 新增用户角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/addUserRole")
    public String addUserRole(HttpServletRequest request) {
        UserRole userRole = filluserRoleParam(request);
        userRoleService.insertUserRole(userRole);
        return toSecurityManager(request);
    }

    @RequestMapping(value = "/user/checkUser")
    @ResponseBody
    public Map<String, Object> checkUser(HttpServletRequest request) {
        Map<String, Object> message = new HashMap<>();
        String userName = request.getParameter("userName");
        if(userRoleService.checkIsExist(userName)){
            message.put("errMsg", "用户已存在，请勿重复添加");
        }
        return message;
    }

    private UserRole filluserRoleParam(HttpServletRequest request) {
        UserRole dto = new UserRole();
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            dto.setId(Integer.valueOf(id));
        }
        String roleId = request.getParameter("role");
        if (StringUtils.isNotBlank(roleId)) {
            dto.setRoleId(Integer.valueOf(roleId));
        }
        String userName = request.getParameter("userName");
        if (StringUtils.isNotBlank(userName)) {
            dto.setUserName(userName);
        }
        return dto;
    }
}
