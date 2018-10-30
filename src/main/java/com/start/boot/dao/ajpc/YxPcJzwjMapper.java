package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxPcJzwj;
import com.start.boot.domain.YxPcJzwjExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YxPcJzwjMapper {
    long countByExample(YxPcJzwjExample example);

    int deleteByExample(YxPcJzwjExample example);

    int deleteByPrimaryKey(String jzwjbh);

    int insert(YxPcJzwj record);

    int insertSelective(YxPcJzwj record);

    List<YxPcJzwj> selectByExample(YxPcJzwjExample example);

    YxPcJzwj selectByPrimaryKey(String jzwjbh);

    int updateByExampleSelective(@Param("record") YxPcJzwj record, @Param("example") YxPcJzwjExample example);

    int updateByExample(@Param("record") YxPcJzwj record, @Param("example") YxPcJzwjExample example);

    int updateByPrimaryKeySelective(YxPcJzwj record);

    int updateByPrimaryKey(YxPcJzwj record);

    List getPcbgList(YxPcJzwj record);

}