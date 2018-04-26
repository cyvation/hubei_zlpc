package com.start.boot.service;

import com.start.boot.domain.DataDictionary;
import com.start.boot.domain.Fldmlb;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * 数据字典service
 *
 * @caomin
 * @create 2017-12-05 22:04
 **/
public interface DataDictionaryService {
    /**
     * 根据类别编码获取字典集合
     * @param lbbm
     * @return
     */
    List<DataDictionary> getDataDictionaryByLBBM(String lbbm);

    /**
     * 根据类别编码获取Map集合
     * @param lbbm
     * @return
     */
    List<Map> getDictionaryByLBBM(String lbbm) throws Exception;

    /**
     * 根据代码获取字典
     * @param dm
     * @return
     * @throws Exception
     */
    DataDictionary getDictionaryByDM(String dm) throws Exception;


    /**
     * 获取代码分类代码列表
     * @return
     * @throws Exception
     */
    List<Map> getSjzdfl() throws Exception;

    /**
     * 增加分类代码
     * @param dataDictionary
     * @return
     * @throws Exception
     */
    int addFldm(DataDictionary dataDictionary) throws Exception;

    /**
     * 更新分类代码
     * @param dataDictionary
     * @return
     * @throws Exception
     */
    int updateFldm(DataDictionary dataDictionary) throws Exception;

    /**
     * 删除分类代码
     * @param dataDictionary
     * @return
     * @throws Exception
     */
    int deleteFldm(DataDictionary dataDictionary) throws Exception;

    /**
     * 更改分类代码状态  是否启用
     * @param dataDictionarys
     * @return
     * @throws Exception
     */
    int updateFldmsStatus (List<DataDictionary> dataDictionarys) throws Exception;


    int updateFldmStatus (DataDictionary dataDictionary) throws Exception;
}
