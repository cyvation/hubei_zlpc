package com.start.boot.service;

import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9.
 */
public interface GradeService {

    List<Map> getMarkSheet(String pcslbm) throws Exception;

}
