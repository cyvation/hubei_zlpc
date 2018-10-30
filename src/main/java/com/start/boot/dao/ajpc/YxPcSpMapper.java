package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxPcSp;
import com.start.boot.domain.YxPcSpExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YxPcSpMapper {
    long countByExample(YxPcSpExample example);

    int deleteByExample(YxPcSpExample example);

    int deleteByPrimaryKey(String pcspbm);

    int insert(YxPcSp record);

    int insertSelective(YxPcSp record);

    List<YxPcSp> selectByExample(YxPcSpExample example);

    YxPcSp selectByPrimaryKey(String pcspbm);

    int updateByExampleSelective(@Param("record") YxPcSp record, @Param("example") YxPcSpExample example);

    int updateByExample(@Param("record") YxPcSp record, @Param("example") YxPcSpExample example);

    int updateByPrimaryKeySelective(YxPcSp record);

    int updateByPrimaryKey(YxPcSp record);
}