/**
 * UserController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-16 16:45
 */
package com.controller;


import com.common.AjaxResult;
import com.pojo.UserInfo;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-16 16:45
 * @Description
 **/
@Controller
@RequestMapping("/user")
public class UserController  {

    @Resource
    private UserService userService;


/**
 * 描述：Login登录方法说明
 * 创建人: 梁家鹄 2018-10-24 15:00
 * 修改人：     
 * 修改说明：
 * @param：[userInfo, model, session]
 * @return：java.lang.String
 */
    @RequestMapping(value = "/login",method={RequestMethod.POST})
    public String Login(UserInfo userInfo, Model model, HttpSession session){
        
        System.out.println("参数"+userInfo.getUsername() + userInfo.getPassword());
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        if(userInfo.getUsername()==null){
            model.addAttribute("message", "账号不为空");
            return "login";
        }
        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            //利用异常操作
            //需要开始调用到Realm中
            System.out.println("========================================");
            System.out.println("1、进入认证方法");
            subject.login(token);
            userInfo = (UserInfo) subject.getPrincipal();
            System.out.println("69行");
            session.setAttribute("user","已登录");
            model.addAttribute("message", "登录完成3");
            System.out.println("登录完成3");
        } catch (Exception e) {
            model.addAttribute("message", "账号密码不正确");
            return "login";
        }


        return "index";
    }

    @RequestMapping(value = "/loginOut",method={RequestMethod.GET})
    public String loginOut(Model model,HttpSession session){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("登出2");
       // session.setAttribute("user","a");
        return "login";
    }

    /**
     * 描述：selectUser方法说明
     * 创建人: 梁家鹄 2018-10-24 14:45
     * 修改人：     
     * 修改说明：
     * @param：[username]
     * @return：java.util.List<com.pojo.UserInfo>
     */
    @RequestMapping(value = "/selectUser",method={RequestMethod.POST})
    @ResponseBody
    public List<UserInfo> selectUser(@RequestBody String username){

        List<UserInfo> lists = userService.getUserFuzzy(username);

        return lists;
    }

    /**
     * 描述：addUser方法说明
     * 创建人: 梁家鹄 2018-10-24 15:51
     * 修改人：
     * 修改说明：
     * @param：[userInfo]
     * @return：java.util.List<com.pojo.UserInfo>
     */
    @RequestMapping(value="/addUser",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addUser(@RequestBody UserInfo userInfo) {
        try{
            userService.getUserByName(userInfo.getUsername());
        } catch (NullPointerException e){
            userService.addUser(userInfo);
            return AjaxResult.success("success");
            }
        return AjaxResult.success("用户名已存在");
    }

    @RequestMapping(value="/deleteUser",method = {RequestMethod.POST})
    @ResponseBody
    //@RequiresRoles(value={"admin"})
    public AjaxResult deleteUser(@RequestBody Integer userId){
        try{
            userService.deleteUser(userId);
        }catch (UnauthorizedException exception){
            return AjaxResult.error("删除失败");
        }
        return AjaxResult.success("删除成功");
    }

    @RequestMapping(value="/check",method = {RequestMethod.POST})
    @ResponseBody
    public int test (@RequestBody int userInfo){
        System.out.println("我的名字2："+userInfo);
        return userInfo;
    }

    @RequestMapping(value="/role",method = {RequestMethod.POST})
    public String test (Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.toString();
        if(subject.hasRole("admin")) {
        //有权限
            model.addAttribute("role1", "有权限");
        } else {
        //无权限
            model.addAttribute("role1", "无");
        }
        return "roletest";
    }


}
