package com.start.boot.service.impl;


import com.start.boot.dao.ajpc.EvalinfoMapper;
import com.start.boot.service.EvalinfoService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/11/12.
 */
@Service
public class EvalinfoServiceimpl implements EvalinfoService {
    @Autowired
    private EvalinfoMapper evalinfoMapper;
    @Override
    public List<Map> getPcjz(String cbdw_bm, String nzrgh) throws Exception {
        String errMsg = "";
        Map map = new HashMap();
        map.put("p_cbdw_bm", cbdw_bm);
        map.put("p_nzrgh", nzrgh);
        evalinfoMapper.getPcjz(map);
        errMsg = DataAccessHelper.getString(map, errMsg);
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return DataAccessHelper.getListMap(map, "p_cursor");
    }
}
