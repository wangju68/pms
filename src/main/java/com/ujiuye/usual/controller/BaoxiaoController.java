package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoxiaoService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "bx")
public class BaoxiaoController {
    @Autowired
    private BaoxiaoService baoxiaoService;

    //显示登录用户所有的报销信息
    @RequestMapping(value = "myList")
    public ModelAndView myBXList(HttpSession session,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        ModelAndView mv = new ModelAndView();
        Employee emp = (Employee) session.getAttribute("emp");
        //没登录跳转登录页面
        if (emp==null){
            mv.setViewName("loginUse");
            return mv;
        }
        //登录显示报销列表
        Integer eid = emp.getEid();
        PageInfo<Baoxiao> page = baoxiaoService.myBXList(eid,pageNum);
        mv.setViewName("mybaoxiao-base");
        mv.addObject("page",page);


        return mv;
    }

    //添加报销
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfoBx(Baoxiao baoxiao,HttpSession session){
        Employee emp = (Employee) session.getAttribute("emp");
        baoxiao.setEmpFk(emp.getEid());
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoxiao.setBxid(bxid);
        baoxiaoService.saveInfoBx(baoxiao);
        return "redirect:/bx/myList";
    }

}

