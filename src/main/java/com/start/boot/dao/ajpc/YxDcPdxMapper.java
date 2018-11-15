package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxDcPdx;
import com.start.boot.domain.YxDcPdxExample;
import com.start.boot.domain.YxDcPdxKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YxDcPdxMapper {
    long countByExample(YxDcPdxExample example);

    int deleteByExample(YxDcPdxExample example);

    int deleteByPrimaryKey(YxDcPdxKey key);

    int insert(YxDcPdx record);

    int insertSelective(YxDcPdx record);

    List<YxDcPdx> selectByExample(YxDcPdxExample example);

    YxDcPdx selectByPrimaryKey(YxDcPdxKey key);

    int updateByExampleSelective(@Param("record") YxDcPdx record, @Param("example") YxDcPdxExample example);

    int updateByExample(@Param("record") YxDcPdx record, @Param("example") YxDcPdxExample example);

    int updateByPrimaryKeySelective(YxDcPdx record);

    int updateByPrimaryKey(YxDcPdx record);
}