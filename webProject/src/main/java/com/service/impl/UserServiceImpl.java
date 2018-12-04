/**
 * UserServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 14:13
 */
package com.service.impl;

import com.mapper.UserMapper;
import com.pojo.RoleInfo;
import com.pojo.UserInfo;
import com.service.UserService;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.owasp.esapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 14:13
 * @Description
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    private static String salt = "123";
    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public UserInfo[] getUserFuzzy(UserInfo userInfo) {
        return userMapper.getUserFuzzy(userInfo);
    }

    @Override
    @RequiresPermissions(value = {"add"})
    public Integer addUser(UserInfo userInfo) {
        //对密码进行md5加盐加密
        userInfo.setPassword(md5(userInfo.getPassword()));

        UserInfo userInfo1 = null;
        //判断用户名是否存在
        System.out.println("用户添加Service");
        userInfo1 = userMapper.getUserByName(userInfo.getUsername());

        if(userInfo1==null) {
            return userMapper.addUser(userInfo);
        }
        else{
            return -1;
        }
    }

    @Override
    @RequiresPermissions(value = {"delete"})
    public Integer deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    @RequiresPermissions(value = {"update"})
    public Integer updateUser(UserInfo userInfo) {

        Integer userId = null;
        UserInfo userInfo1 = null;

        if(userInfo.getUsername()==null){
            return userMapper.updateUser(userInfo);
        }
        System.out.println("修改用户Service方法里");
        userId  =  userMapper.getUserIdByName(userInfo.getUsername());
        userInfo1 = userMapper.getUserByName(userInfo.getUsername());

        if(userInfo1==null||userId.equals(userInfo.getUserId())){
            if(userInfo.getPassword()!=null) {
                userInfo.setPassword(md5(userInfo.getPassword()));
            }
            return userMapper.updateUser(userInfo);
        }
        else {
            return -1;
        }

    }

    @Override
    @RequiresPermissions(value = {"update"})
    synchronized public void  updateUserRole(UserInfo userInfo) {

           String username =  userMapper.getUserNameById(userInfo.getUserId());
           UserInfo userInfo1 = userMapper.getUserByName(username);
           int [] ids = userMapper.selectRoleByUser(userInfo.getUserId());
           if(ids.length>0){
               userMapper.deleteUserRole(userInfo1);
           }
           try{
              List<RoleInfo> roleInfos = userInfo.getRoleInfoList();
              userMapper.addUserRole(userInfo);
           }catch (Exception e){}
    }

    @Override
    public Integer userLoginTime(UserInfo userInfo) {
        return userMapper.userLoginTime(userInfo);
    }

    public static String md5(String s){
        return DigestUtils.md5DigestAsHex((salt + s).getBytes());
    }

    /*public  Boolean verifiMd5(String s, String m){
        if (m.equals(md5(s))){
            return true;
        }
        return false;
    }*/

}
