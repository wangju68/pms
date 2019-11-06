package com.ujiuye.usual.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.jobs.EmailJob;
import com.ujiuye.usual.bean.Email;
import com.ujiuye.usual.service.EmailService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("ema")
public class EmailController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private EmailService emailService;
    //发邮件时发件人下拉选择
    @RequestMapping(value = "sendEma",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity sendEma(HttpSession session){
        Employee emp = (Employee) session.getAttribute("emp");
        if (emp==null){
            return ResultEntity.success().put("statusCode","请先登录");
        }
        Integer eid = emp.getEid();
        List<Email> emails = emailService.sendEma(eid);
        return ResultEntity.success().put("statusCode","200").put("emails",emails);
    }
    //保存发送的邮件信息
    @RequestMapping(value = "saveEma",method = RequestMethod.POST)
    public String saveEma(HttpSession session,MultipartFile file,Email email){
        Employee emp = (Employee) session.getAttribute("emp");
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File files = new File(realPath);
        if(!files.exists()){
            files.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "")+originalFilename;
        try {
            file.transferTo(new File(realPath+"/"+newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        email.setPath(newFileName);
        email.setSendtime(new Date());
        email.setEmpFk(emp.getEid());


        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).withIdentity("job1", "group1").build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("javaMailSenderImpl",javaMailSender);

        //获取当前时间
        Date startTime = new Date(System.currentTimeMillis());
        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(startTime).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= null;
        try {
            sched = StdSchedulerFactory.getDefaultScheduler();
            jobDataMap.put("sched",sched);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        try {
            sched.scheduleJob(job, trigger);
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        emailService.saveEma(email);
        return "redirect:/main.jsp";
    }
}
