package com.start.boot.dao.ajpc;

import com.start.boot.domain.Sp;
import com.start.boot.domain.SpExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpMapper {
    long countByExample(SpExample example);

    int deleteByExample(SpExample example);

    int deleteByPrimaryKey(String pcspbm);

    int insert(Sp record);

    int insertSelective(Sp record);

    List<Sp> selectByExample(SpExample example);

    Sp selectByPrimaryKey(String pcspbm);

    int updateByExampleSelective(@Param("record") Sp record, @Param("example") SpExample example);

    int updateByExample(@Param("record") Sp record, @Param("example") SpExample example);

    int updateByPrimaryKeySelective(Sp record);

    int updateByPrimaryKey(Sp record);
}