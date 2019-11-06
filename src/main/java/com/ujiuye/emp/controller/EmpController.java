package com.ujiuye.emp.controller;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    //查找所有的项目经理
    @RequestMapping(value = "selectLeadEmp",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> selectLeadEmp(){
        return empService.selectLeadEmp();
    }

    //登录校验
    @RequestMapping(value = "login")
    public String loginCheck(Employee employee, String codeCheck, HttpSession session, RedirectAttributes redirectAttributes){
        String validateCode = (String) session.getAttribute("validateCode");
        if(validateCode.equalsIgnoreCase(codeCheck)){
            session.removeAttribute("validateCode");
            Employee emp = empService.loginCheck(employee);
            if (emp!=null){
                session.setAttribute("emp",emp);
                return "redirect:/index.jsp";
            }
            redirectAttributes.addFlashAttribute("msg","用户名或者密码不正确");
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("msg","验证码输入错误");
        return "redirect:/login";
    }

    //注销退出
    @RequestMapping(value = "quit")
    public String quitMethod(HttpSession session){
        session.removeAttribute("emp");
        return "redirect:/login.jsp";
    }
}
