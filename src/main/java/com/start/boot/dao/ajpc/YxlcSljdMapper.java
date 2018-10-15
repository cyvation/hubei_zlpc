package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxlcSljd;
import com.start.boot.domain.YxlcSljdExample;
import com.start.boot.domain.YxlcSljdKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YxlcSljdMapper {
    long countByExample(YxlcSljdExample example);

    int deleteByExample(YxlcSljdExample example);

    int deleteByPrimaryKey(YxlcSljdKey key);

    int insert(YxlcSljd record);

    int insertSelective(YxlcSljd record);

    List<YxlcSljd> selectByExample(YxlcSljdExample example);

    YxlcSljd selectByPrimaryKey(YxlcSljdKey key);

    int updateByExampleSelective(@Param("record") YxlcSljd record, @Param("example") YxlcSljdExample example);

    int updateByExample(@Param("record") YxlcSljd record, @Param("example") YxlcSljdExample example);

    int updateByPrimaryKeySelective(YxlcSljd record);

    int updateByPrimaryKey(YxlcSljd record);
}