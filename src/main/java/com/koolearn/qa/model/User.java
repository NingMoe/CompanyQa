package com.koolearn.qa.model;

import com.koolearn.ldap.dto.LdapUser;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/28
 */
public class User {
    LdapUser userInfo;
    String password;

    public LdapUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LdapUser userInfo) {
        this.userInfo = userInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
