package com.start.boot.dao.ajpc;

import com.start.boot.domain.Yj;
import com.start.boot.domain.YjExample;
import com.start.boot.domain.YjKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YjMapper {
    long countByExample(YjExample example);

    int deleteByExample(YjExample example);

    int deleteByPrimaryKey(YjKey key);

    int insert(Yj record);

    int insertSelective(Yj record);

    List<Yj> selectByExampleWithBLOBs(YjExample example);

    List<Yj> selectByExample(YjExample example);

    Yj selectByPrimaryKey(YjKey key);

    int updateByExampleSelective(@Param("record") Yj record, @Param("example") YjExample example);

    int updateByExampleWithBLOBs(@Param("record") Yj record, @Param("example") YjExample example);

    int updateByExample(@Param("record") Yj record, @Param("example") YjExample example);

    int updateByPrimaryKeySelective(Yj record);

    int updateByPrimaryKeyWithBLOBs(Yj record);

    int updateByPrimaryKey(Yj record);
}