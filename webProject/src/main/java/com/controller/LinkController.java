/**
 * LinkController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:27
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.LinkInfo;
import com.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:27
 * @Description
 **/
@Controller
@RequestMapping("/link")
@Api(tags = "友情链接")
public class LinkController {
    @Resource
    private LinkService linkService;
    @ApiOperation(value = "查询所有链接",notes = "查询",httpMethod = "POST")
    @RequestMapping(value = "/selectAllLink",method={RequestMethod.POST})
    @ResponseBody
    public LinkInfo[] selectAllLink(){
        LinkInfo[] lists = linkService.selectAllLink();
        return lists;
    }

    @ApiOperation(value = "查询链接",notes = "查询",httpMethod = "POST")
    @RequestMapping(value = "/selectLink",method={RequestMethod.POST})
    @ResponseBody
    public LinkInfo[] selectLink(@RequestBody LinkInfo linkInfo){
        LinkInfo[] lists = linkService.selectLink(linkInfo);
        return lists;
    }

    @ApiOperation(value = "链接添加",notes = "添加",httpMethod = "POST")
    @ApiImplicitParam(name = "LinkInfo" ,value = "输入linkname,link_url,link_order")
    @RequestMapping(value="/addLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addLink(@RequestBody LinkInfo linkInfo) {
        try {
            int flag = linkService.addLink(linkInfo);
            if(flag>0){
                return AjaxResult.success("添加成功",1);
            }else{
                return AjaxResult.error(-1,"添加失败,链接名已存在");
            }
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"添加失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"添加失败,没有登录");
        }


    }

    @ApiOperation(value = "链接删除",notes = "删除",httpMethod = "POST")
    @ApiImplicitParam(name = "LinkIds" ,value = "输入linkId")
    @RequestMapping(value="/deleteLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteLink(@RequestBody Integer[] linkIds){
        try{

            linkService.deleteLink(linkIds);
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"删除失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"删除失败,没有登录");
        }
        return AjaxResult.success("删除成功",1);
    }

    @ApiOperation(value = "链接修改",notes = "修改",httpMethod = "POST")
    @ApiImplicitParam(name = "linkInfo" ,value = "输入linkId,linkname,link_url,link_order")
    @RequestMapping(value="/updateLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateLink(@RequestBody LinkInfo linkInfo){
        System.out.println("连接修改"+ linkInfo.getLinkId()+" "+linkInfo.getLinkname());
        try{
            int flag = linkService.updateLink(linkInfo);
            if(flag>0){
                return AjaxResult.success("修改成功",1);
            }else{
                return AjaxResult.error(-1,"修改失败,链接名已存在");
            }
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"修改失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"修改失败,没有登录");
        }


    }






}
