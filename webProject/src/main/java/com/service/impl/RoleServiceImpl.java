/**
 * RoleServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 14:00
 */
package com.service.impl;

import com.google.inject.spi.PrivateElements;
import com.mapper.RoleMapper;
import com.pojo.PermissionInfo;
import com.pojo.RoleInfo;
import com.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 14:00
 * @Description
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public RoleInfo getRoleByName(String rolename) {
        return roleMapper.getRoleByName(rolename);
    }

    @Override
    public RoleInfo getPermissionByRoleName(String rolename) {
        return roleMapper.getPermissionByRoleName(rolename);
    }

    @Override
    public RoleInfo[] getRoleFuzzy(RoleInfo roleInfo) {
        return roleMapper.getRoleFuzzy(roleInfo);
    }

    @Override
    @RequiresPermissions(value = {"add"})
    public Integer addRole(RoleInfo roleInfo) {

        RoleInfo roleInfo1 = null;
        roleInfo1 = roleMapper.getRoleByName(roleInfo.getRolename());

        if(roleInfo1==null) {
            return roleMapper.addRole(roleInfo);
        }else{
            return -1;
        }
    }

    @Override
    @RequiresPermissions(value = {"delete"})
    public Integer deleteRole(Integer[] roleIds) {
        return roleMapper.deleteRole(roleIds);
    }

    @Override
    @RequiresPermissions(value = {"update"})
    public Integer updateRole(RoleInfo roleInfo) {

        if(roleInfo.getRolename()==null){
            return roleMapper.updateRole(roleInfo);
        }

        Integer roleId = null ;
        RoleInfo roleInfo1 = null;

        roleInfo1 = roleMapper.getRoleByName(roleInfo.getRolename());
        roleId = roleMapper.getRoleIdByName(roleInfo.getRolename());

        if(roleInfo1==null||roleId.equals(roleInfo.getRoleId()) ){
            return roleMapper.updateRole(roleInfo);
        }else{
            return -1;
        }

    }

    @Override
    @RequiresPermissions(value = {"update"})
    public synchronized void updateRolePermission(RoleInfo roleInfo) {
        String rolename = roleMapper.getRoleNameById(roleInfo.getRoleId());
        RoleInfo roleInfo1 = roleMapper.getRoleByName(rolename);
        System.out.println(rolename);
        for(PermissionInfo p: roleInfo.getPermissionInfoList()){
            System.out.println(p.getPermissionId());
        }
        System.out.println("id出来："+roleInfo1.getRoleId());

        int [] ids = roleMapper.selectPermissionByRole(roleInfo.getRoleId());
        if(ids.length>0){
            roleMapper.deleteRolePermission(roleInfo1);
        }

        try {
            List<PermissionInfo> permissionInfos = roleInfo.getPermissionInfoList();
            roleMapper.addRolePermission(roleInfo);
            System.out.println("here");
        }catch (Exception e){}

    }
}
