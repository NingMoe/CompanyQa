package com.koolearn.qa.security;


import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.model.Permission;
import com.koolearn.qa.model.Role;
import com.koolearn.qa.service.IPermissionService;
import com.koolearn.qa.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户身份验证,授权 Realm 组件
 *
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private LdapService ldapService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;



    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());
        if(username.indexOf(Constant.EMAIL_SUFFIX)>0){
            username = username.substring(0,username.indexOf(Constant.EMAIL_SUFFIX));
        }
        final List<Role> roleInfos = roleService.selectRolesByUserName(username);
        if(roleInfos == null){
            //未获得用户角色时默认游客身份
            authorizationInfo.addRole(RoleSign.GUEST);
            final List<Permission> permissions = permissionService.selectPermissionsByRoleSign(RoleSign.GUEST);
            for (Permission permission : permissions) {
                // 添加权限
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }else {
            for (Role role : roleInfos) {
                // 添加角色
                authorizationInfo.addRole(role.getRoleSign());
                final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
                for (Permission permission : permissions) {
                    // 添加权限
                    authorizationInfo.addStringPermission(permission.getPermissionSign());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        if(!ldapService.verifyLdapUser(username,password)){
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
