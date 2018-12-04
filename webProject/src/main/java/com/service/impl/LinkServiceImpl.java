/**
 * LinkServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:11
 */
package com.service.impl;

import com.mapper.LinkMapper;
import com.pojo.LinkInfo;
import com.service.LinkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:11
 * @Description
 **/
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public LinkInfo[] selectAllLink() {
        return linkMapper.selectAllLink();
    }

    @Override
    @RequiresPermissions(value = {"add"})
    public Integer addLink(LinkInfo linkInfo) {
        Integer linkId = null;
        linkId = linkMapper.getLinkIdByName(linkInfo.getLinkname());

        if(linkId == null||linkId.equals(linkInfo.getLinkId())) {
            return linkMapper.addLink(linkInfo);
        }else{
            return -1;
        }
    }

    @Override
    @RequiresPermissions(value = {"update"})
    public Integer updateLink(LinkInfo linkInfo) {
        Integer linkId = null;
        linkId = linkMapper.getLinkIdByName(linkInfo.getLinkname());

        if(linkId == null||linkId.equals(linkInfo.getLinkId())) {
            return linkMapper.updateLink(linkInfo);
        }else{
            return -1;
        }

    }

    @Override
    @RequiresPermissions(value = {"delete"})
    public Integer deleteLink(Integer[] linkIds) {

        return linkMapper.deleteLink(linkIds);
    }

    @Override
    public LinkInfo[] selectLink(LinkInfo linkInfo) {
        return linkMapper.selectLink(linkInfo);
    }
}
