package com.start.boot.dao.ajpc;

import com.start.boot.domain.YX_PC_HD;
import com.start.boot.domain.YX_PC_HDExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YX_PC_HDMapper {
    long countByExample(YX_PC_HDExample example);

    int deleteByExample(YX_PC_HDExample example);

    int deleteByPrimaryKey(String pchdbm);

    int insert(YX_PC_HD record);

    int insertSelective(YX_PC_HD record);

    List<YX_PC_HD> selectByExample(YX_PC_HDExample example);

    YX_PC_HD selectByPrimaryKey(String pchdbm);

    int updateByExampleSelective(@Param("record") YX_PC_HD record, @Param("example") YX_PC_HDExample example);

    int updateByExample(@Param("record") YX_PC_HD record, @Param("example") YX_PC_HDExample example);

    int updateByPrimaryKeySelective(YX_PC_HD record);

    int updateByPrimaryKey(YX_PC_HD record);
}