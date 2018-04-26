package com.start.boot.dao.ajpc;

import com.start.boot.domain.YX_PC_PCZL;
import com.start.boot.domain.YX_PC_PCZLExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface YX_PC_PCZLMapper {
    long countByExample(YX_PC_PCZLExample example);

    int deleteByExample(YX_PC_PCZLExample example);

    int deleteByPrimaryKey(String bh);

    int insert(YX_PC_PCZL record);

    int insertSelective(YX_PC_PCZL record);

    List<Map> selectByExample(YX_PC_PCZLExample example);

    YX_PC_PCZL selectByPrimaryKey(String bh);

    int updateByExampleSelective(@Param("record") YX_PC_PCZL record, @Param("example") YX_PC_PCZLExample example);

    int updateByExample(@Param("record") YX_PC_PCZL record, @Param("example") YX_PC_PCZLExample example);

    int updateByPrimaryKeySelective(YX_PC_PCZL record);

    int updateByPrimaryKey(String record);
}