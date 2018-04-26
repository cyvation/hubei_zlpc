package com.start.boot.service.impl;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.GradeMapper;
import com.start.boot.domain.*;
import com.start.boot.service.GradeService;
import com.start.boot.service.MessageService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9.
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    // 获取评分表
    @Override
    public List<Map> getMarkSheet(String pcslbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", pcslbm);

        // 操作数据库
        gradeMapper.getMarkSheet(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

}
