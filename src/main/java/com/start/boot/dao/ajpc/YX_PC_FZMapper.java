package com.start.boot.dao.ajpc;


import com.start.boot.domain.YX_PC_FZ;
import com.start.boot.domain.YX_PC_FZExample;
import com.start.boot.domain.YX_PC_FZKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YX_PC_FZMapper {
    long countByExample(YX_PC_FZExample example);

    int deleteByExample(YX_PC_FZExample example);

    int deleteByPrimaryKey(YX_PC_FZKey key);

    int insert(YX_PC_FZ record);

    int insertSelective(YX_PC_FZ record);

    List<YX_PC_FZ> selectByExample(YX_PC_FZExample example);

    YX_PC_FZ selectByPrimaryKey(YX_PC_FZKey key);

    int updateByExampleSelective(@Param("record") YX_PC_FZ record, @Param("example") YX_PC_FZExample example);

    int updateByExample(@Param("record") YX_PC_FZ record, @Param("example") YX_PC_FZExample example);

    int updateByPrimaryKeySelective(YX_PC_FZ record);

    int updateByPrimaryKey(YX_PC_FZ record);
}