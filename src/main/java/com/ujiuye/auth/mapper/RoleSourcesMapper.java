package com.ujiuye.auth.mapper;

import java.util.List;

import com.ujiuye.auth.bean.RoleSourcesExample;
import com.ujiuye.auth.bean.RoleSourcesKey;
import org.apache.ibatis.annotations.Param;

public interface RoleSourcesMapper {
    int countByExample(RoleSourcesExample example);

    int deleteByExample(RoleSourcesExample example);

    int deleteByPrimaryKey(RoleSourcesKey key);

    int insert(RoleSourcesKey record);

    int insertSelective(RoleSourcesKey record);

    List<RoleSourcesKey> selectByExample(RoleSourcesExample example);

    int updateByExampleSelective(@Param("record") RoleSourcesKey record, @Param("example") RoleSourcesExample example);

    int updateByExample(@Param("record") RoleSourcesKey record, @Param("example") RoleSourcesExample example);

    int saveInfo(@Param("rid") Integer roleid, @Param("sid") int parseInt);
}