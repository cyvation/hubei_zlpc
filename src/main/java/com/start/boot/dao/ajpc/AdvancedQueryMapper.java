package com.start.boot.dao.ajpc;

import com.start.boot.domain.AdvancedQuery;
import com.start.boot.domain.AdvancedQueryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvancedQueryMapper {
    long countByExample(AdvancedQueryExample example);

    int deleteByExample(AdvancedQueryExample example);

    int deleteByPrimaryKey(String bh);

    int insert(AdvancedQuery record);

    int insertSelective(AdvancedQuery record);

    List<AdvancedQuery> selectByExample(AdvancedQueryExample example);

    AdvancedQuery selectByPrimaryKey(String bh);

    int updateByExampleSelective(@Param("record") AdvancedQuery record, @Param("example") AdvancedQueryExample example);

    int updateByExample(@Param("record") AdvancedQuery record, @Param("example") AdvancedQueryExample example);

    int updateByPrimaryKeySelective(AdvancedQuery record);

    int updateByPrimaryKey(AdvancedQuery record);
}