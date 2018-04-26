package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtQxRyJsfp;
import com.start.boot.domain.XtQxRyJsfpExample;
import com.start.boot.domain.XtQxRyJsfpKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtQxRyJsfpMapper {
    long countByExample(XtQxRyJsfpExample example);

    int deleteByExample(XtQxRyJsfpExample example);

    int deleteByPrimaryKey(XtQxRyJsfpKey key);

    int insert(XtQxRyJsfp record);

    int insertSelective(XtQxRyJsfp record);

    List<XtQxRyJsfp> selectByExample(XtQxRyJsfpExample example);

    XtQxRyJsfp selectByPrimaryKey(XtQxRyJsfpKey key);

    int updateByExampleSelective(@Param("record") XtQxRyJsfp record, @Param("example") XtQxRyJsfpExample example);

    int updateByExample(@Param("record") XtQxRyJsfp record, @Param("example") XtQxRyJsfpExample example);

    int updateByPrimaryKeySelective(XtQxRyJsfp record);

    int updateByPrimaryKey(XtQxRyJsfp record);
}