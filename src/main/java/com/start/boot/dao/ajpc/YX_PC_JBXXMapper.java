package com.start.boot.dao.ajpc;

import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YX_PC_JBXXMapper {
    long countByExample(YX_PC_JBXXExample example);

    int deleteByExample(YX_PC_JBXXExample example);

    int deleteByPrimaryKey(String pcslbm);

    int insert(YX_PC_JBXX record);

    int insertSelective(YX_PC_JBXX record);

    List<YX_PC_JBXX> selectByExample(YX_PC_JBXXExample example);

    YX_PC_JBXX selectByPrimaryKey(String pcslbm);

    int updateByExampleSelective(@Param("record") YX_PC_JBXX record, @Param("example") YX_PC_JBXXExample example);

    int updateByExample(@Param("record") YX_PC_JBXX record, @Param("example") YX_PC_JBXXExample example);

    int updateByPrimaryKeySelective(YX_PC_JBXX record);

    int updateByPrimaryKey(YX_PC_JBXX record);
}