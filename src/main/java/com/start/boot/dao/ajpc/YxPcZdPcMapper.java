package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxPcZdPc;
import com.start.boot.domain.YxPcZdPcExample;
import com.start.boot.domain.YxPcZdPcKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YxPcZdPcMapper {
    long countByExample(YxPcZdPcExample example);

    int deleteByExample(YxPcZdPcExample example);

    int deleteByPrimaryKey(YxPcZdPcKey key);

    int insert(YxPcZdPc record);

    int insertSelective(YxPcZdPc record);

    List<YxPcZdPc> selectByExample(YxPcZdPcExample example);

    YxPcZdPc selectByPrimaryKey(YxPcZdPcKey key);

    int updateByExampleSelective(@Param("record") YxPcZdPc record, @Param("example") YxPcZdPcExample example);

    int updateByExample(@Param("record") YxPcZdPc record, @Param("example") YxPcZdPcExample example);

    int updateByPrimaryKeySelective(YxPcZdPc record);

    int updateByPrimaryKey(YxPcZdPc record);
}