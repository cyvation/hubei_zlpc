package com.start.boot.service;/**
 * Created by Administrator on 2018/1/21.
 */

import com.start.boot.domain.XtDmFldm;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/21
 * @说明 数据字典分类
 */
public interface XtDmFldmService {

    /**
     * 根据类别代码获取字典
     * @param lbbm
     * @return
     */
    List<XtDmFldm>getXtDmFldmBy(String lbbm);

}
