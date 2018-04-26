package com.start.boot.dao.ajpc;

import com.start.boot.domain.XtPcLb;
import com.start.boot.domain.XtPcLbExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface XtPcLbMapper {
    long countByExample(XtPcLbExample example);

    int deleteByExample(XtPcLbExample example);

    int deleteByPrimaryKey(String pcflbm);

    int insert(XtPcLb record);

    int insertSelective(XtPcLb record);

    List<XtPcLb> selectByExample(XtPcLbExample example);

    XtPcLb selectByPrimaryKey(String pcflbm);

    int updateByExampleSelective(@Param("record") XtPcLb record, @Param("example") XtPcLbExample example);

    int updateByExample(@Param("record") XtPcLb record, @Param("example") XtPcLbExample example);

    int updateByPrimaryKeySelective(XtPcLb record);

    int updateByPrimaryKey(XtPcLb record);
}