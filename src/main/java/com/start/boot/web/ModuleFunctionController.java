package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.Function;
import com.start.boot.domain.FunctionFl;
import com.start.boot.service.ModuleFunctionService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.GetDw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能模板管理
 * Created by cj on 2018/1/24.
 */
@RestController
@RequestMapping("/modulefunction")
@SuppressWarnings("all")
public class ModuleFunctionController extends ArchivesSystemBaseController{


    @Autowired
    private ModuleFunctionService moduleFunctionService;


    // 获取功能定义树
    @RequestMapping("/GetGnflCombTree")
    public MessageResult GetGnflCombTree(String dwbm) {

        String result = "";
        String errMsg = "";

        if (StringUtils.isEmpty(dwbm)) {
            super.errMsg("获取功能分类树形下拉框失败",dwbm,new Exception("获取功能分类树形下拉框失败"));
        }

        try {
            Map map = moduleFunctionService.GetGnflCombTree(dwbm);
            List<Map> list = (List<Map>) map.get("p_cursor");
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (list != null) {
                //遍历集合，找到本单位的父单位
                String topBM = GetDw.topBM(list, dwbm);
                topBM = "-1";
                result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM);
            }
            return new MessageResult("获取功能分类树形下拉框成功",200, JSON.parse(result));
        } catch (Exception e) {
            super.errMsg("获取功能分类树形下拉框失败", dwbm, e);
            return new MessageResult("获取功能分类树形下拉框失败",200,errMsg);
        }
    }

    //新增/更新功能分类
    @RequestMapping("/AddGnfl")
    public MessageResult AddGnfl(FunctionFl functionFl) {//@RequestBody List<Param_rybm> list

        String errMsg = "";
        System.out.println(functionFl.toString());

        try {
            errMsg = moduleFunctionService.addOrUpdateGnfl(functionFl);
            if (errMsg != null) {
                super.errMsg("新增/更新功能分类失败",errMsg,new Exception("新增/更新功能分类失败"));
            }
            return new MessageResult("新增/更新功能分类成功",200,errMsg);
        } catch (Exception e) {
            super.errMsg("新增/更新功能分类失败", functionFl.getFlbm(), e);
            return new MessageResult("新增/更新功能分类失败",200,errMsg);
        }
    }

    //获取功能分类详情
    @RequestMapping("/GetGnflInfo")
    public MessageResult GetGnflInfo(String dwbm, String flbm) {

        String errMsg = "";

        try {
            Map map = moduleFunctionService.GetGnflInfo(dwbm, flbm);
            List<Map> list = (List<Map>) map.get("p_cursor");
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            return new MessageResult("获取功能分类详情成功", 200, list);
        } catch (Exception e) {
            super.errMsg("获取功能分类详情失败", dwbm, e);
            return new MessageResult("获取功能分类详情失败",200,errMsg);
        }

    }

    //删除功能分类信息
    @RequestMapping("/DeleteGnfl")
    public MessageResult DeleteGnfl(String dwbm, String flbm) {

        String errMsg = "";

        try {
            Map map = moduleFunctionService.DeleteGnfl(dwbm, flbm);
            Integer gndy_count = (Integer) map.get("p_count");
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            Map resultMap = new HashMap<>();
            if (errMsg != null) {
                super.errMsg("删除功能分类信息失败",errMsg,new Exception("删除功能分类信息失败"));
            }
            resultMap.put("gndy_count", gndy_count);
            resultMap.put("errMsg", errMsg);
            return new MessageResult("删除功能分类信息成功",200, resultMap);
        } catch (Exception e) {
            super.errMsg("删除人员信息失败", dwbm, e);
            return new MessageResult("删除功能分类信息失败",200,errMsg);
        }

    }

    //获取功能定义列表
    @RequestMapping("/GetGndyList")
    public MessageResult GetGndyList(Function function, int page, int rows) {

        String result = "";
        String errMsg = "";

        try {
            Map map = moduleFunctionService.GetGndyList(function, page ,rows);
            List<Map> list = (List<Map>) map.get("p_cursor");
            Integer p_count = (Integer) map.get("p_count");
            errMsg = DataAccessHelper.getString(map, "p_errmsg");

            result = EasyUIHelper.buildDataGridDataSource(list,p_count);
            return new MessageResult("获取功能定义列表成功",200, result);

        } catch (Exception e) {
            super.errMsg("获取人员信息列表发生错误", function.getFlbm(), e);
            return new MessageResult("获取功能定义列表失败",200, errMsg);
        }
    }

    //新增/更新功能定义
    @RequestMapping("/AddGndy")
    public MessageResult AddGndy(Function function) {

        String errMsg = "";

        try {
            errMsg = moduleFunctionService.AddGndy(function);
            if (errMsg != null) {
                super.errMsg("新增/更新功能定义失败",errMsg,new Exception("新增/更新功能定义失败"));
            }
            return new MessageResult("新增/更新功能定义成功",200, errMsg);
        } catch (Exception e) {
            super.errMsg("新增/更新功能定义失败", function.getFlbm(), e);
            return new MessageResult("新增/更新功能定义失败",200,errMsg);
        }
    }

    //删除功能定义信息
    @RequestMapping("/DeleteGndy")
    public MessageResult DeleteGndy(String dwbm, String gnbm) {

        String errMsg = "";

        try {
            errMsg = moduleFunctionService.DeleteGndy(dwbm, gnbm);
            if (errMsg != null) {
                super.errMsg("删除功能定义信息失败",errMsg,new Exception("删除功能定义信息失败"));
            }
            return new MessageResult("删除功能定义信息成功",200, errMsg);
        } catch (Exception e) {
            super.errMsg("删除功能定义信息失败", dwbm, e);
            return new MessageResult("删除功能定义信息失败",200,errMsg);
        }

    }




}
