/**
 * UserService.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 14:01
 */
package com.service;

import com.pojo.UserInfo;

import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 14:01
 * @Description
 **/
public interface UserService {

    UserInfo getUserByName(String username);

    List<UserInfo> getUserFuzzy(String username);

    Integer addUser(UserInfo userInfo);

    Integer deleteUser(Integer userId);
}
