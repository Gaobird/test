/**
 * UserServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 14:13
 */
package com.service.impl;

import com.mapper.UserMapper;
import com.pojo.UserInfo;
import com.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.owasp.esapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
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
    public List<UserInfo> getUserFuzzy(String username) {
        return userMapper.getUserFuzzy(username);
    }

    @Override
    public Integer addUser(UserInfo userInfo) {
        userInfo.setPassword(md5(userInfo.getPassword()));
        return  userMapper.addUser(userInfo);
    }

    @Override
    @RequiresRoles(value={"admin"})
    public Integer deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    /**
     * 描述：md5方法说明 md5加密
     * 创建人: 梁家鹄 2018-10-24 15:17
     * 修改人：
     * 修改说明：
     * @param：[s]
     * @return：java.lang.String
     */
    public static String md5(String s){
        return DigestUtils.md5DigestAsHex((salt + s).getBytes());
    }


/*    public static void main(String[] args){
        System.out.println(UserServiceImpl.md5("123"));
    }*/
    /*public  Boolean verifiMd5(String s, String m){
        if (m.equals(md5(s))){
            return true;
        }
        return false;
    }*/

}
