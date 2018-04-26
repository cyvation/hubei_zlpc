package com.start.boot.service;

import com.start.boot.domain.Function;
import com.start.boot.domain.FunctionFl;

import java.util.Map;

/**
 * Created by cj on 2018/1/24.
 */
public interface ModuleFunctionService {

    /**
     * 获取功能定义树
     * @param dwbm
     * @return
     */
    Map GetGnflCombTree(String dwbm) throws Exception;

    /**
     * 新增/更新功能分类
     * @param functionFl
     * @return
     */
    String addOrUpdateGnfl(FunctionFl functionFl) throws Exception;

    /**
     * 获取功能分类详情
     * @param dwbm
     * @param flbm
     * @return
     */
    Map GetGnflInfo(String dwbm, String flbm) throws Exception;

    /**
     * 删除功能分类信息
     * @param dwbm
     * @param flbm
     * @return
     */
    Map DeleteGnfl(String dwbm, String flbm) throws Exception;

    /**
     * 获取功能定义列表
     * @param function
     * @param page
     * @param rows
     * @return
     */
    Map GetGndyList(Function function, int page, int rows) throws Exception;

    /**
     * 新增/更新功能定义
     * @param function
     * @return
     */
    String AddGndy(Function function) throws Exception;

    /**
     * 删除功能定义信息
     * @param dwbm
     * @param gnbm
     * @return
     */
    String DeleteGndy(String dwbm, String gnbm) throws Exception;
}
