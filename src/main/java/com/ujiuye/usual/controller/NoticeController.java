package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.common.ResultEntity;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //通知公告信息保存
    @RequestMapping(value = "saveinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveinfo(Notice notice){
        noticeService.saveinfo(notice);
        return ResultEntity.success();
    }

    //通知公告展示list
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity noticeList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        PageInfo<Notice> page = noticeService.noticeList(pageNum);
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        return ResultEntity.success().put("page",page).put("requestURI",requestURI);
    }
    //首页通知公告的显示listNotice
    @RequestMapping(value = "listNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity listNotice(){
        List<Notice> list = noticeService.listNotice();
        return ResultEntity.success().put("list",list);
    }
    //根据id查找通知信息
    @RequestMapping(value = "selectInfo/{obj}",method = RequestMethod.GET)
    @ResponseBody
    public Notice selectInfo(@PathVariable("obj")Integer obj){
        Notice notice = noticeService.selectInfo(obj);
        return notice;
    }
}
