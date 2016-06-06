package com.koolearn.qa.security;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 *
 **/
public class PermissionSign {

    /**
     * 用户新增权限 标识
     */
    public static final String USER_CREATE = "user:create";

    /**
     * 用户删除权限 标识
     */
    public static final String USER_DELETE = "user:delete";

    /**
     * 用户编辑权限 标识
     */
    public static final String USER_UPDATE = "user:update";

    /**
     * 用户查看权限 标识
     */
    public static final String USER_VIEW = "user:view";



}
