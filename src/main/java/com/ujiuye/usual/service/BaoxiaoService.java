package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaoxiaoService {
    @Resource
    private BaoxiaoMapper baoxiaoMapper;
    //显示登录用户所有的报销信息
    public PageInfo<Baoxiao> myBXList(Integer eid, Integer pageNum) {
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        PageHelper.startPage(pageNum,3);
        List<Baoxiao> list = baoxiaoMapper.selectByExample(example);
        PageInfo<Baoxiao> page = new PageInfo<Baoxiao>(list,5);

        return page;

    }
    //添加报销信息
    public void saveInfoBx(Baoxiao baoxiao) {
        baoxiaoMapper.insertSelective(baoxiao);
    }
}
