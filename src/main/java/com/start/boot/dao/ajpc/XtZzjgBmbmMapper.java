package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtZzjgBmbm;
import com.start.boot.domain.XtZzjgBmbmExample;
import com.start.boot.domain.XtZzjgBmbmKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtZzjgBmbmMapper {
    long countByExample(XtZzjgBmbmExample example);

    int deleteByExample(XtZzjgBmbmExample example);

    int deleteByPrimaryKey(XtZzjgBmbmKey key);

    int insert(XtZzjgBmbm record);

    int insertSelective(XtZzjgBmbm record);

    List<XtZzjgBmbm> selectByExample(XtZzjgBmbmExample example);

    XtZzjgBmbm selectByPrimaryKey(XtZzjgBmbmKey key);

    int updateByExampleSelective(@Param("record") XtZzjgBmbm record, @Param("example") XtZzjgBmbmExample example);

    int updateByExample(@Param("record") XtZzjgBmbm record, @Param("example") XtZzjgBmbmExample example);

    int updateByPrimaryKeySelective(XtZzjgBmbm record);

    int updateByPrimaryKey(XtZzjgBmbm record);
}