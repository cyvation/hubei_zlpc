package com.start.boot.service;/**
 * Created by Administrator on 2018/1/21.
 */

import com.start.boot.domain.YxPcZdPc;

/**
 * @author caomin
 * @date 2018/1/21
 * @说明 对应YX_PC_ZDPC表
 */
public interface YxPcZdpcxService {

    /**
     * 保存自动评查项
     * @param yxPcZdPc
     * @return
     */
    YxPcZdPc save(YxPcZdPc yxPcZdPc);

    /**
     * 根据主键查询
     * @param pcxbm
     * @param pcslbm
     * @return
     */
    YxPcZdPc getYxPcZdPcByKey(String pcxbm,String pcslbm);


}
