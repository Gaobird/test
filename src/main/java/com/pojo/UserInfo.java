/**
 * UserInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 11:07
 */
package com.pojo;

import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 11:07
 * @Description
 **/
public class UserInfo {

    private Integer userid;

    private String username;

    private String password;

    private String salt;

    private String status;

    private List<RoleInfo> roleInfoList;


    public UserInfo() {}

    public UserInfo(Integer userid, String username, String password, String salt, String status) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }
}