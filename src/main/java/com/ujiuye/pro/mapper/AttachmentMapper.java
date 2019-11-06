package com.ujiuye.pro.mapper;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AttachmentMapper {
    int countByExample(AttachmentExample example);

    int deleteByExample(AttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    List<Attachment> selectByExample(AttachmentExample example);

    Attachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
    //查询所有的附件信息
    List<Map<String, Object>> selectAtta();

    //统计各个项目的附件个数
    Integer selectCount(Integer pro_fk);
    //根据id，查询部分的附件信息
    List<Map<String, Object>> selectAttaById(String ids);
    //根据id，查询附件详情信息 lookById
    Map<String, Object> lookById(Integer id);
}