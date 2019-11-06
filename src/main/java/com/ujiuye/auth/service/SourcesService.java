package com.ujiuye.auth.service;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import com.ujiuye.auth.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesService {
    @Resource
    private SourcesMapper sourcesMapper;

    //权限管理（显示所有的模块）
    public List<Sources> showAuth(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }
    //权限的添加
    public void addPm(Sources sources) {
        sourcesMapper.insert(sources);
    }
    //权限的信息回填
    public Sources getOneById(Integer id) {
        Sources sources = sourcesMapper.selectByPrimaryKey(id);
        return sources;
    }
    //权限的信息修改
    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }
    //权限的删除
    public void deleteSour(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }
}
