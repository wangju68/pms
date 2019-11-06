package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;
    @Resource
    private ProjectMapper projectMapper;
    //保存附件
    public void saveInfo(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }

    //查询所有的附件信息
    public List<Map<String,Object>> selectAtta() {

        List<Map<String, Object>> lists = attachmentMapper.selectAtta();
        for (Map<String, Object> map: lists) {
            Integer pro_fk = (Integer) map.get("pro_fk");
            Integer count = attachmentMapper.selectCount(pro_fk);
            map.put("count",count);
        }
        return lists;


    }
    //根据id，查询部分的附件信息
    public List<Map<String, Object>> selectAttaById(String ids) {
        List<Map<String, Object>> lists = attachmentMapper.selectAttaById(ids);
        for (Map<String, Object> map: lists) {
            Integer pro_fk = (Integer) map.get("pro_fk");
            Integer count = attachmentMapper.selectCount(pro_fk);
            map.put("count",count);
        }
        return lists;
    }

    //根据id，查询附件详情信息 lookById
    public Map<String, Object> lookById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = attachmentMapper.lookById(id);
        return map;
    }
}
