package com.start.boot.service;/**
 * Created by Administrator on 2018/3/23.
 */

import com.start.boot.domain.Yj;

import java.util.List;

/**
 * yx_pc_yj
 * @author caomin
 * @date 2018/3/23
 * @说明
 */
public interface YjService {

    /**
     * 承办人是否已经反馈过
     * @param pcslbm
     * @return
     */
    boolean hasCbrFkCount(String pcslbm);


    /**
     * 根据评查受理编码获取意见列表
     * @param pcslbm
     * @return
     */
    List<Yj> getYjList(String pcslbm);


}
