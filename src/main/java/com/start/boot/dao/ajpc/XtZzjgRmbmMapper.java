package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtZzjgRmbm;
import com.start.boot.domain.XtZzjgRmbmExample;
import com.start.boot.domain.XtZzjgRmbmKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtZzjgRmbmMapper {
    long countByExample(XtZzjgRmbmExample example);

    int deleteByExample(XtZzjgRmbmExample example);

    int deleteByPrimaryKey(XtZzjgRmbmKey key);

    int insert(XtZzjgRmbm record);

    int insertSelective(XtZzjgRmbm record);

    List<XtZzjgRmbm> selectByExampleWithBLOBs(XtZzjgRmbmExample example);

    List<XtZzjgRmbm> selectByExample(XtZzjgRmbmExample example);

    XtZzjgRmbm selectByPrimaryKey(XtZzjgRmbmKey key);

    int updateByExampleSelective(@Param("record") XtZzjgRmbm record, @Param("example") XtZzjgRmbmExample example);

    int updateByExampleWithBLOBs(@Param("record") XtZzjgRmbm record, @Param("example") XtZzjgRmbmExample example);

    int updateByExample(@Param("record") XtZzjgRmbm record, @Param("example") XtZzjgRmbmExample example);

    int updateByPrimaryKeySelective(XtZzjgRmbm record);

    int updateByPrimaryKeyWithBLOBs(XtZzjgRmbm record);

    int updateByPrimaryKey(XtZzjgRmbm record);
}