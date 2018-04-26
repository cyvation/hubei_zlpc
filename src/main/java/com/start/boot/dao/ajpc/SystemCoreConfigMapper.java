package com.start.boot.dao.ajpc;

import com.start.boot.domain.SystemCoreConfig;
import com.start.boot.domain.SystemCoreConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SystemCoreConfigMapper {
    long countByExample(SystemCoreConfigExample example);

    int deleteByExample(SystemCoreConfigExample example);

    int insert(SystemCoreConfig record);

    int insertSelective(SystemCoreConfig record);

    List<SystemCoreConfig> selectByExample(SystemCoreConfigExample example);

    int updateByExampleSelective(@Param("record") SystemCoreConfig record, @Param("example") SystemCoreConfigExample example);

    int updateByExample(@Param("record") SystemCoreConfig record, @Param("example") SystemCoreConfigExample example);
}