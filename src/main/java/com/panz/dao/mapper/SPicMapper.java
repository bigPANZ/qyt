package com.panz.dao.mapper;

import com.panz.entity.SPic;
import com.panz.entity.SPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SPicMapper {
    long countByExample(SPicExample example);

    int deleteByExample(SPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SPic record);

    int insertSelective(SPic record);

    List<SPic> selectByExample(SPicExample example);

    SPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SPic record, @Param("example") SPicExample example);

    int updateByExample(@Param("record") SPic record, @Param("example") SPicExample example);

    int updateByPrimaryKeySelective(SPic record);

    int updateByPrimaryKey(SPic record);
}