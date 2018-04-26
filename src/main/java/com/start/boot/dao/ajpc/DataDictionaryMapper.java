package com.start.boot.dao.ajpc;

import com.start.boot.domain.DataDictionary;
import com.start.boot.domain.DataDictionaryExample;
import com.start.boot.domain.DataDictionaryKey;
import com.start.boot.domain.Fldmlb;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataDictionaryMapper {
    long countByExample(DataDictionaryExample example);

    int deleteByExample(DataDictionaryExample example);

    int deleteByPrimaryKey(DataDictionaryKey key);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    List<DataDictionary> selectByExample(DataDictionaryExample example);

    DataDictionary selectByPrimaryKey(DataDictionaryKey key);

    int updateByExampleSelective(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByExample(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);

    List<Map> getSjzdfl();

    List<Map> selectDictionaryByExample (DataDictionaryExample example);

    int deleteFldm (DataDictionary dataDictionary);

    int updateSfscByPrimaryKey (DataDictionary dataDictionarys);

}