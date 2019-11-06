package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    //通知公告信息保存
    public void saveinfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }
    //通知公告展示list
    public PageInfo<Notice> noticeList(Integer pageNum) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(pageNum,3);
        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(notices,5);
        return pageInfo;
    }
    //首页通知公告的显示listNotice
    public List<Notice> listNotice() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 0,3");
        List<Notice> list = noticeMapper.selectByExample(example);
        return list;
    }
    //根据id查找通知信息
    public Notice selectInfo(Integer obj) {
        Notice notice = noticeMapper.selectByPrimaryKey(obj);
        return notice;
    }
}

