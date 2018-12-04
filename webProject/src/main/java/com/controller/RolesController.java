/**
 * RolesController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 13:39
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.PermissionInfo;
import com.pojo.RoleInfo;
import com.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 13:39
 * @Description
 **/
@Controller
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RolesController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "角色查询",notes = "角色查询",httpMethod = "POST")
    @RequestMapping(value = "/selectRole",method={RequestMethod.POST})
    @ResponseBody
    public RoleInfo[] selectRole(@RequestBody RoleInfo roleInfo){
        System.out.println("角色查询"+roleInfo.getRolename()+roleInfo.getRole_status());
        RoleInfo[] lists = roleService.getRoleFuzzy(roleInfo);
        return lists;
    }

    @ApiOperation(value = "查询所有角色",notes = "角色查询",httpMethod = "POST")
    @RequestMapping(value = "/selectAllRole",method={RequestMethod.POST})
    @ResponseBody
    public RoleInfo[] selectAllRole(){
        RoleInfo roleInfo = new RoleInfo();
        System.out.println("查询所有角色");
        RoleInfo[] lists = roleService.getRoleFuzzy(roleInfo);
        return lists;
    }

    @ApiOperation(value = "角色添加",notes = "角色添加",httpMethod = "POST")
    @RequestMapping(value="/addRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addRole(@RequestBody RoleInfo roleInfo) {
        System.out.println("新增的角色名："+roleInfo.getRolename());
        try{
            int flag = roleService.addRole(roleInfo);
            if(flag>0) {
                return AjaxResult.success("添加成功", 1);
            }else{
                return AjaxResult.error(-1,"角色已存在");
            }
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"添加失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"添加失败,没有登录");
        }
    }

    @ApiOperation(value = "角色删除",notes = "角色删除",httpMethod = "POST")
    @RequestMapping(value="/deleteRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteRole(@RequestBody Integer[] roleIds){

        for(int a :roleIds){
            System.out.println("删除角色id"+a);
        }

        try{
            int flag = roleService.deleteRole(roleIds);
            if(flag>0){
                return AjaxResult.success("删除成功",1);
            }else{
                return AjaxResult.error(-1,"删除失败,没有该角色");
            }
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"删除失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"删除失败,没有登录");
        }

    }

    @ApiOperation(value = "角色基本信息修改",notes = "角色修改",httpMethod = "POST")
    @RequestMapping(value="/updateRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateUser(@RequestBody RoleInfo roleInfo){
        System.out.println("修改"+roleInfo.getRolename()+roleInfo.getRoleId());
        try{

            int flag = roleService.updateRole(roleInfo);
            if(flag>0){
                return AjaxResult.success("修改成功",1);
            }else{
                return AjaxResult.error(-1,"修改失败,角色名已存在");
            }
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"修改失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"修改失败,没有登录");
        }

    }

    @ApiOperation(value = "角色权限修改",notes = "角色修改权限",httpMethod = "POST")
    @RequestMapping(value="updateRolePermission",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateRolePermission(@RequestBody RoleInfo roleInfo){


        System.out.println(roleInfo.getRoleId()+"角色赋权id");
        List<PermissionInfo> ls = roleInfo.getPermissionInfoList();

        for(PermissionInfo p :roleInfo.getPermissionInfoList()){
            System.out.println("wod:::"+p.getPermissionId());
        }

        try {
            roleService.updateRolePermission(roleInfo);
            return AjaxResult.success("修改成功",1);
        }catch(UnauthorizedException exception){
            return AjaxResult.error(-1,"修改失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"修改失败,没有登录");
        }


    }








}
