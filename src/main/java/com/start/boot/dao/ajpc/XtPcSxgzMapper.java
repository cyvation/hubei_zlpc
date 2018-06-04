package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtPcSxgz;
import com.start.boot.domain.XtPcSxgzExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtPcSxgzMapper {
    long countByExample(XtPcSxgzExample example);

    int deleteByExample(XtPcSxgzExample example);

    int deleteByPrimaryKey(String gzbm);

    int insert(XtPcSxgz record);

    int insertSelective(XtPcSxgz record);

    List<XtPcSxgz> selectByExample(XtPcSxgzExample example);

    XtPcSxgz selectByPrimaryKey(String gzbm);

    int updateByExampleSelective(@Param("record") XtPcSxgz record, @Param("example") XtPcSxgzExample example);

    int updateByExample(@Param("record") XtPcSxgz record, @Param("example") XtPcSxgzExample example);

    int updateByPrimaryKeySelective(XtPcSxgz record);

    int updateByPrimaryKey(XtPcSxgz record);
}