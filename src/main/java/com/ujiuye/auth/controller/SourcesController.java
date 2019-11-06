package com.ujiuye.auth.controller;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.service.SourcesService;
import com.ujiuye.common.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("auth")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    //权限管理（显示所有的模块）
    @RequestMapping(value = "showAuth",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> showAuth(){
        List<Sources> lists = sourcesService.showAuth(0);
        queryChildren(lists.get(0));
        return lists;
    }
    //一级权限文件夹下的子权限  递归
    private void queryChildren(Sources sources) {
        Integer id = sources.getId();
        List<Sources> sources1 = sourcesService.showAuth(id);
        for (Sources sources2 : sources1) {
            queryChildren(sources2);
        }
        sources.setChildren(sources1);
    }
    //权限的添加
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addPm(Sources sources){
        sourcesService.addPm(sources);
        return "redirect:/pm.jsp";
    }
    //权限的信息回填
    @RequestMapping(value = "/getOneById/{id}",method = RequestMethod.GET)
    public ModelAndView getOneById(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources sources = sourcesService.getOneById(id);
        mv.addObject("onesource",sources);
        return mv;
    }
    //权限的信息修改
    @RequestMapping(value = "updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }
    //权限的删除delete
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity deleteSour(Integer id){
        sourcesService.deleteSour(id);
        return ResultEntity.success();
    }
}
