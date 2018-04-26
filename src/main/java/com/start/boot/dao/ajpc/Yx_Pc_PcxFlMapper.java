package com.start.boot.dao.ajpc;


import com.start.boot.domain.Yx_Pc_PcxFl;
import com.start.boot.domain.Yx_Pc_PcxFlExample;
import com.start.boot.domain.Yx_Pc_PcxFlKey;
import com.start.boot.pojo.dto.CqPcxFlDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Yx_Pc_PcxFlMapper {
    long countByExample(Yx_Pc_PcxFlExample example);

    int deleteByExample(Yx_Pc_PcxFlExample example);

    int deleteByPrimaryKey(Yx_Pc_PcxFlKey key);

    int insert(Yx_Pc_PcxFl record);

    int insertSelective(Yx_Pc_PcxFl record);

    List<Yx_Pc_PcxFl> selectByExample(Yx_Pc_PcxFlExample example);

    Yx_Pc_PcxFl selectByPrimaryKey(Yx_Pc_PcxFlKey key);

    int updateByExampleSelective(@Param("record") Yx_Pc_PcxFl record, @Param("example")Yx_Pc_PcxFlExample example);

    int updateByExample(@Param("record") Yx_Pc_PcxFl record, @Param("example") Yx_Pc_PcxFlExample example);

    int updateByPrimaryKeySelective(Yx_Pc_PcxFl record);

    int updateByPrimaryKey(Yx_Pc_PcxFl record);
    List<CqPcxFlDto>  getCqPcx(@Param("pcslbm")String pcslbm);
}