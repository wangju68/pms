package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Email;
import com.ujiuye.usual.bean.EmailExample;
import com.ujiuye.usual.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;
    //发邮件时发件人下拉选择
    public List<Email> sendEma(Integer eid) {
        EmailExample example = new EmailExample();
        EmailExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkNotEqualTo(eid);
        List<Email> emails = emailMapper.selectByExample(example);
        return emails;
    }
    //保存发送的邮件信息
    public void saveEma(Email email) {
        emailMapper.insertSelective(email);
    }
}
