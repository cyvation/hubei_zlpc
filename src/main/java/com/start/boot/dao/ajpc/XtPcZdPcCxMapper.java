package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtPcZdPcCx;
import com.start.boot.domain.XtPcZdPcCxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtPcZdPcCxMapper {
    long countByExample(XtPcZdPcCxExample example);

    int deleteByExample(XtPcZdPcCxExample example);

    int deleteByPrimaryKey(String zdpcCxbm);

    int insert(XtPcZdPcCx record);

    int insertSelective(XtPcZdPcCx record);

    List<XtPcZdPcCx> selectByExample(XtPcZdPcCxExample example);

    XtPcZdPcCx selectByPrimaryKey(String zdpcCxbm);

    int updateByExampleSelective(@Param("record") XtPcZdPcCx record, @Param("example") XtPcZdPcCxExample example);

    int updateByExample(@Param("record") XtPcZdPcCx record, @Param("example") XtPcZdPcCxExample example);

    int updateByPrimaryKeySelective(XtPcZdPcCx record);

    int updateByPrimaryKey(XtPcZdPcCx record);
}