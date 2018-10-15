package com.start.boot.service;/**
 * Created by Administrator on 2018/1/26.
 */

import com.start.boot.common.PageInfo;
import com.start.boot.domain.XtQxRyJsfp;
import com.start.boot.domain.XtZzjgRmbm;

import java.util.List;

/**
 * @author caomin
 * @date 2018/1/26
 * @说明 组织机构 部门
 */
public interface XtZzjgRmbmService {

    /**
     * 根据单位编码获取所有人员
     * @param dwbm
     * @return
     */
    List<XtZzjgRmbm> getDwRmTreeList(String dwbm);

    /**
     * 根据单位编码和部门编码获取人员
     * @param dwbm
     * @param bmbm
     * @return
     */
    List<XtZzjgRmbm> getDwBmRmTreeList(String dwbm,String bmbm,PageInfo query);

    /**
     * 获取角色权限分配信息
     * @param dwbm
     * @param gh
     * @return
     */
    List<XtQxRyJsfp>getXtQxRyJsfp(String dwbm, String gh);
}
