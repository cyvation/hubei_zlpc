package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtZzjgDwbm;
import com.start.boot.domain.XtZzjgDwbmExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XtZzjgDwbmMapper {
    long countByExample(XtZzjgDwbmExample example);

    int deleteByExample(XtZzjgDwbmExample example);

    int deleteByPrimaryKey(String dwbm);

    int insert(XtZzjgDwbm record);

    int insertSelective(XtZzjgDwbm record);

    List<XtZzjgDwbm> selectByExample(XtZzjgDwbmExample example);

    XtZzjgDwbm selectByPrimaryKey(String dwbm);

    int updateByExampleSelective(@Param("record") XtZzjgDwbm record, @Param("example") XtZzjgDwbmExample example);

    int updateByExample(@Param("record") XtZzjgDwbm record, @Param("example") XtZzjgDwbmExample example);

    int updateByPrimaryKeySelective(XtZzjgDwbm record);

    int updateByPrimaryKey(XtZzjgDwbm record);


    List<XtZzjgDwbm>   getDwbmTreeList(@Param("dwbm")String dwbm);
}