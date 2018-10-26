/**
 * UserTest.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-14 13:53
 */
package com.test;

import com.pojo.RoleInfo;
import com.pojo.UserInfo;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-14 13:53
 * @Description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUser()  {

        UserInfo userInfo = new UserInfo();
        userInfo = userService.getUserByName("告鸟");
        System.out.println(userInfo.getUsername());
        for(RoleInfo r : userInfo.getRoleInfoList()){
            System.out.println(r.getRolename());
        }

    }
}