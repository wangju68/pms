package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleSourService roleSourService;
    //角色信息的添加
    public void saveInfo(Role role, String ids) {
        roleMapper.insertSelective(role);
        Integer roleid = role.getRoleid();
        String[] split = ids.split(",");
        for (String s : split) {
            roleSourService.saveInfo(roleid,Integer.parseInt(s));
        }
    }
}
