package com.ujiuye.auth.service;

import com.ujiuye.auth.mapper.RoleSourcesMapper;
import com.ujiuye.usual.controller.NoticeController;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourService {
    @Resource
    private RoleSourcesMapper roleSourcesMapper;

    public void saveInfo(Integer roleid, int parseInt) {
        //roleSourcesMapper.saveInfo();
        roleSourcesMapper.saveInfo(roleid,parseInt);
    }
}
