package com.start.boot.service;/**
 * Created by Administrator on 2018/1/26.
 */

import com.start.boot.domain.XtZzjgDwbm;

import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明 组织机构单位编码
 */
public interface XtZzjgDwbmService {

    /**
     * 根据单位编码获取所有子单位
     * @param dwbm
     * @return
     */
    List<XtZzjgDwbm> getDwbmTreeList(String dwbm);

    List<Map> getSibligDwbm(String dwbm);
}
