package com.start.boot.dao.tyyw;

import com.start.boot.domain.Tyyw_Jbxx;
import com.start.boot.domain.Tyyw_JbxxExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Tyyw_JbxxMapper {
    long countByExample(Tyyw_JbxxExample example);

    int deleteByExample(Tyyw_JbxxExample example);

    int deleteByPrimaryKey(String bmsah);

    int insert(Tyyw_Jbxx record);

    int insertSelective(Tyyw_Jbxx record);

    List<Tyyw_Jbxx> selectByExample(Tyyw_JbxxExample example);

    Tyyw_Jbxx selectByPrimaryKey(String bmsah);

    int updateByExampleSelective(@Param("record") Tyyw_Jbxx record, @Param("example") Tyyw_JbxxExample example);

    int updateByExample(@Param("record") Tyyw_Jbxx record, @Param("example") Tyyw_JbxxExample example);

    int updateByPrimaryKeySelective(Tyyw_Jbxx record);

    int updateByPrimaryKey(Tyyw_Jbxx record);
}