package com.ujiuye.auth.controller;

import com.ujiuye.auth.bean.Role;
import com.ujiuye.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //角色信息的添加
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveInfo(Role role,String ids){
        roleService.saveInfo(role,ids);
        return null;
    }
}
