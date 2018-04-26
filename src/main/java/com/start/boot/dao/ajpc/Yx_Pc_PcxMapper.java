package com.start.boot.dao.ajpc;

import com.start.boot.domain.Yx_Pc_Pcx;
import com.start.boot.domain.Yx_Pc_PcxExample;
import com.start.boot.domain.Yx_Pc_PcxKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Yx_Pc_PcxMapper {
    long countByExample(Yx_Pc_PcxExample example);

    int deleteByExample(Yx_Pc_PcxExample example);

    int deleteByPrimaryKey(Yx_Pc_PcxKey key);

    int insert(Yx_Pc_Pcx record);

    int insertSelective(Yx_Pc_Pcx record);

    List<Yx_Pc_Pcx> selectByExample(Yx_Pc_PcxExample example);

    Yx_Pc_Pcx selectByPrimaryKey(Yx_Pc_PcxKey key);

    int updateByExampleSelective(@Param("record") Yx_Pc_Pcx record, @Param("example") Yx_Pc_PcxExample example);

    int updateByExample(@Param("record") Yx_Pc_Pcx record, @Param("example") Yx_Pc_PcxExample example);

    int updateByPrimaryKeySelective(Yx_Pc_Pcx record);

    int updateByPrimaryKey(Yx_Pc_Pcx record);
}