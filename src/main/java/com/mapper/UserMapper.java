/**
 * UserMapper.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 13:50
 */
package com.mapper;

import com.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 13:50
 * @Description
 **/
@Repository
public interface UserMapper {

    UserInfo getUserByName(String username);

    List<UserInfo> getUserFuzzy(String username);

    Integer addUser(UserInfo userInfo);

    Integer deleteUser(Integer userId);
}
