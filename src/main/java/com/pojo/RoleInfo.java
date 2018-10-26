/**
 * RoleInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 11:14
 */
package com.pojo;



import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 11:14
 * @Description
 **/
public class RoleInfo {

    private Integer roleid;

    private String rolename;

    private String role_description;

    private List<PermissionInfo> permissionInfoList;

    private List<UserInfo> userInfoList;


    public RoleInfo() {
    }

    public RoleInfo(Integer roleid, String rolename, String role_description) {
        this.roleid = roleid;
        this.rolename = rolename;
        this.role_description = role_description;
    }

    public List<PermissionInfo> getPermissionInfoList() {
        return permissionInfoList;
    }

    public void setPermissionInfoList(List<PermissionInfo> permissionInfoList) {
        this.permissionInfoList = permissionInfoList;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
