package com.start.boot.dao.tyyw;

import com.start.boot.domain.Tyyw_Fldm;
import com.start.boot.domain.Tyyw_FldmExample;
import com.start.boot.domain.Tyyw_FldmKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Tyyw_FldmMapper {
    long countByExample(Tyyw_FldmExample example);

    int deleteByExample(Tyyw_FldmExample example);

    int deleteByPrimaryKey(Tyyw_FldmKey key);

    int insert(Tyyw_Fldm record);

    int insertSelective(Tyyw_Fldm record);

    List<Tyyw_Fldm> selectByExample(Tyyw_FldmExample example);

    Tyyw_Fldm selectByPrimaryKey(Tyyw_FldmKey key);

    int updateByExampleSelective(@Param("record") Tyyw_Fldm record, @Param("example") Tyyw_FldmExample example);

    int updateByExample(@Param("record") Tyyw_Fldm record, @Param("example") Tyyw_FldmExample example);

    int updateByPrimaryKeySelective(Tyyw_Fldm record);

    int updateByPrimaryKey(Tyyw_Fldm record);


    List<Tyyw_Fldm>getTyywFldm(@Param("dm")String dm,@Param("lbbm")String lbbm);
}