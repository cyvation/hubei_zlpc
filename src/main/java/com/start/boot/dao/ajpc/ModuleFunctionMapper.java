package com.start.boot.dao.ajpc;


import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by cj on 2018/1/24.
 */
@Repository
public interface ModuleFunctionMapper {

    //获取功能定义树
    void GetGnflCombTree(Map map);

    //新增/更新功能分类
    void addOrUpdateGnfl(Map map);

    //获取功能分类详情
    void GetGnflInfo(Map map);

    //删除功能分类信息
    void DeleteGnfl(Map map);

    //获取功能定义列表
    void GetGndyList(Map map);

    //新增/更新功能定义
    void AddGndy(Map map);

    //删除功能定义信息
    void DeleteGndy(Map map);

}
