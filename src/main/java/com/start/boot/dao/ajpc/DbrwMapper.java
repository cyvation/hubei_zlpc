package com.start.boot.dao.ajpc;

import com.start.boot.domain.Dbrw;
import com.start.boot.domain.DbrwExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbrwMapper {
    long countByExample(DbrwExample example);

    int deleteByExample(DbrwExample example);

    int deleteByPrimaryKey(String gnbm);

    int insert(Dbrw record);

    int insertSelective(Dbrw record);

    List<Dbrw> selectByExample(DbrwExample example);

    Dbrw selectByPrimaryKey(String gnbm);

    int updateByExampleSelective(@Param("record") Dbrw record, @Param("example") DbrwExample example);

    int updateByExample(@Param("record") Dbrw record, @Param("example") DbrwExample example);

    int updateByPrimaryKeySelective(Dbrw record);

    int updateByPrimaryKey(Dbrw record);
}