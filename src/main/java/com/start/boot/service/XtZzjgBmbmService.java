package com.start.boot.service;/**
 * Created by Administrator on 2018/1/26.
 */

import com.start.boot.domain.XtZzjgBmbm;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明 组织机构 部门
 */
public interface XtZzjgBmbmService {

    /**
     * 根据单位编码获取部门
     * @param dwbm
     * @return
     */
    List<XtZzjgBmbm> getDwbmTreeList(String dwbm);

    /**
     * 根据单位编码获取部门
     * @param dwbm
     * @return
     */
    List<XtZzjgBmbm> getbmbmTreeList(String dwbm);
}
