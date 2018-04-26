package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.DataDictionaryMapper;
import com.start.boot.domain.DataDictionary;
import com.start.boot.domain.DataDictionaryExample;
import com.start.boot.domain.Fldmlb;
import com.start.boot.service.DataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @caomin
 * @create 2017-12-05 22:04
 **/
@Service
@Transactional
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> getDataDictionaryByLBBM(String lbbm) {
        if (StringUtils.isNotEmpty(lbbm)){
            DataDictionaryExample dataDictionaryExample = new DataDictionaryExample();
            dataDictionaryExample.createCriteria().andLbbmEqualTo(lbbm).andSfscEqualTo("N");
            return dataDictionaryMapper.selectByExample(dataDictionaryExample);
        }
        return null;
    }

    @Override
    public List<Map> getDictionaryByLBBM(String lbbm) throws Exception{
        if (StringUtils.isNotEmpty(lbbm)){
            DataDictionaryExample dataDictionaryExample = new DataDictionaryExample();
            dataDictionaryExample.createCriteria().andLbbmEqualTo(lbbm);//.andSfscEqualTo("N");
            return dataDictionaryMapper.selectDictionaryByExample(dataDictionaryExample);
        }
        return null;
    }

    @Override
    public DataDictionary getDictionaryByDM(String dm) throws Exception {
        DataDictionaryExample dataDictionaryExample = new DataDictionaryExample();
        dataDictionaryExample.createCriteria().andDmEqualTo(dm);
        List<DataDictionary> dataDictionaries = dataDictionaryMapper.selectByExample(dataDictionaryExample);
        if(dataDictionaries.size()!=0){
            return dataDictionaries.get(0);
        }
        return null;

    }

    @Override
    public List<Map> getSjzdfl() throws Exception {
        return dataDictionaryMapper.getSjzdfl();
    }

    @Override
    public int addFldm(DataDictionary dataDictionary) throws Exception {
        return dataDictionaryMapper.insert(dataDictionary);
    }

    @Override
    public int updateFldm(DataDictionary dataDictionary) throws Exception {

        return dataDictionaryMapper.updateByPrimaryKey(dataDictionary);
    }

    @Override
    public int deleteFldm(DataDictionary dataDictionary) throws Exception {
        return dataDictionaryMapper.deleteFldm(dataDictionary);
    }

    @Override
    public int updateFldmsStatus(List<DataDictionary> dataDictionarys) throws Exception {
        return 0;
    }

    @Override
    public int updateFldmStatus(DataDictionary dataDictionary) throws Exception {
        return dataDictionaryMapper.updateSfscByPrimaryKey(dataDictionary);
    }


}
