package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtDmFldm;
import com.start.boot.domain.XtDmFldmExample;
import com.start.boot.domain.XtDmFldmKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtDmFldmMapper {
    long countByExample(XtDmFldmExample example);

    int deleteByExample(XtDmFldmExample example);

    int deleteByPrimaryKey(XtDmFldmKey key);

    int insert(XtDmFldm record);

    int insertSelective(XtDmFldm record);

    List<XtDmFldm> selectByExample(XtDmFldmExample example);

    XtDmFldm selectByPrimaryKey(XtDmFldmKey key);

    int updateByExampleSelective(@Param("record") XtDmFldm record, @Param("example") XtDmFldmExample example);

    int updateByExample(@Param("record") XtDmFldm record, @Param("example") XtDmFldmExample example);

    int updateByPrimaryKeySelective(XtDmFldm record);

    int updateByPrimaryKey(XtDmFldm record);
}