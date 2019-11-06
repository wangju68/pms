package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    //查询所有信息
    List<Project> selectAllPro();

    //根据id查看项目详情信息
    Project lookProByPid(Integer pid);

    void deletePro(String pids);

    void delFile(String ids);
}