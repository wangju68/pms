package com.ujiuye.pro.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //查出所有的项目名
    @RequestMapping(value = "selectPname",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> selectPname(){
        return projectService.selectAllPro();
    }

    //展示所有项目信息
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView selectAllPro(){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> projects = projectService.selectAllPro();
        mv.addObject("projects",projects);
        return mv;
    }

    //添加项目
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addPro(Project project){
        projectService.addPro(project);
        return "redirect:/pro/list";
    }
    //根据id查看项目详情信息
    @RequestMapping(value = "/look/{pid}",method = RequestMethod.GET)
    public ModelAndView lookProByPid(@PathVariable("pid") Integer pid){
        ModelAndView mv = new ModelAndView("project-base-look");
        Project project = projectService.lookProByPid(pid);
        mv.addObject("pro",project);

        return mv;
    }
    //先根据id查看项目详情信息，在进行信息回填
    @RequestMapping(value = "/lookk/{pid}",method = RequestMethod.GET)
    public ModelAndView lookkProByPid(@PathVariable("pid") Integer pid){
        ModelAndView mv = new ModelAndView("project-base-edit");
        Project project = projectService.lookProByPid(pid);
        mv.addObject("pro",project);
        return mv;
    }

    //保存修改后的信息
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String updatePro(Project project){
        projectService.updatePro(project);
        return "redirect:/pro/list";
    }

    //删除选中的错误
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePro(String pids){
        projectService.deletePro(pids);
        return null;
    }

    //条件模糊查询
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchPro(Integer cid,String keyword,Integer orderby){
        System.out.println(cid+keyword+orderby);
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> projects =  projectService.searchPro(cid,keyword,orderby);
        System.out.println(cid+keyword+orderby);
        mv.addObject("projects",projects);
        return mv;
    }

    //删除选中的附件信息
    @RequestMapping(value = "delFile/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delFile(@PathVariable("ids") String ids){
        projectService.delFile(ids);
        return null;
    }
}
