package com.koolearn.qa.controller;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private LdapService ldapService;


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
            if(username.indexOf(Constant.EMAIL_SUFFIX)<0){
                username = username+Constant.EMAIL_SUFFIX;
            }
            String filter= "(mail="+username+")";
            List<LdapUser> list = ldapService.queryUser(filter);
            LdapUser authUserInfo = list.get(0);
            User user = new User();
            user.setUserInfo(authUserInfo);
            user.setPassword(password);
            request.getSession().setAttribute("user", user);
        } catch (AuthenticationException e) {
            // 身份验证失败
            request.setAttribute("error","用户名或密码错误 ！");
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

}
