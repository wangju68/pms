package com.ujiuye.jobs;

import com.ujiuye.usual.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = new JobDataMap();
        Email email = (Email) jobDataMap.get("email");
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl)jobDataMap.get("javaMailSenderImpl");
        Scheduler sched = (Scheduler)jobDataMap.get("sched");

        try {
            //邮件对象
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom("wj2424588930@163.com");
            helper.setTo("twz1781748566@163.com");
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);

            //作为附件下载
            FileSystemResource file = new FileSystemResource(new File(email.getPath()));
            helper.addAttachment("CoolImage.jpg", file);

            //图片作为内置资源
            FileSystemResource file1 = new FileSystemResource(new File(email.getPath()));
            helper.addInline("identifie",file1);
            //发送邮件
            javaMailSenderImpl.send(mimeMessage);
            System.out.println("EMAIL PASS");
        }catch (Exception ex){
            System.out.println("213213213");
            System.out.println(ex.getMessage());
        }

    }
}
