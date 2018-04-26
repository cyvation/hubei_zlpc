package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.CaseInfoMapper;
import com.start.boot.domain.Param_CaseInfo;
import com.start.boot.service.CaseInfoService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CaseInfoServiceImpl implements CaseInfoService {

    @Autowired
    private CaseInfoMapper caseInfoMapper;

    // 文书卷宗列表
    @Override
    public List<Map> getGlajs(String pcslbm, String bmsah, String param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", pcslbm);
        map.put("p_bmsah", bmsah);
        map.put("p_param", param);

        // 操作数据库
        caseInfoMapper.getGlajs(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 文书卷宗列表
    @Override
    public List<Map> getDocFiles(String pcslbm, String bmsah, String param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", pcslbm);
        map.put("p_bmsah", bmsah);
        map.put("p_param", param);

        // 操作数据库
        caseInfoMapper.getDocFiles(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 电子卷宗列表
    @Override
    public List<Map> getDossierFiles(String pcslbm, String bmsah, String param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", pcslbm);
        map.put("p_bmsah", bmsah);
        map.put("p_param", param);

        // 操作数据库
        caseInfoMapper.getDossierFiles(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 案件详细信息
    @Override
    public Param_CaseInfo getCaseInfo(String pcslbm, String bmsah) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", pcslbm);
        map.put("p_bmsah", bmsah);

        // 操作数据库
        caseInfoMapper.getCaseInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Param_CaseInfo> list = (List<Param_CaseInfo>) map.get("p_cursor");

        return list == null || list.size() <= 0 ? null : list.get(0);
    }

}
