package com.start.boot.dao.tyyw;

import com.start.boot.domain.TyywTest;
import com.start.boot.domain.TyywTestExample;
import com.start.boot.domain.TyywTestKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TyywTestMapper {
    long countByExample(TyywTestExample example);

    int deleteByExample(TyywTestExample example);

    int deleteByPrimaryKey(TyywTestKey key);

    int insert(TyywTest record);

    int insertSelective(TyywTest record);

    List<TyywTest> selectByExample(TyywTestExample example);

    TyywTest selectByPrimaryKey(TyywTestKey key);

    int updateByExampleSelective(@Param("record") TyywTest record, @Param("example") TyywTestExample example);

    int updateByExample(@Param("record") TyywTest record, @Param("example") TyywTestExample example);

    int updateByPrimaryKeySelective(TyywTest record);

    int updateByPrimaryKey(TyywTest record);
}