package com.start.boot.service;/**
 * Created by Administrator on 2018/3/27.
 */

import com.start.boot.domain.XtPcSxgz;

import java.util.List;

/**
 * @author caomin
 * @date 2018/3/27
 * @说明 筛选规则service
 */
public interface XtPcSxgzService {

    /**
     * 获取所有的筛选规则
     * @return
     */
    List<XtPcSxgz>getAll();


    List<XtPcSxgz>getSxgz(String pcflbm);

}
