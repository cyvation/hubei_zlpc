package com.start.boot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.adquery.AdQueryRequest;
import com.start.boot.common.MessageResult;
import com.start.boot.pojo.vo.AdvanceQueryVo;
import com.start.boot.service.AdvanceQueryService;
import com.start.boot.service.OriganizationService;
import com.start.boot.service.XtZzjgDwbmService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.EasyUIUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高级查询控制器
 *
 * @caomin
 * @create 2017-12-13 11:43
 **/
@RestController
@RequestMapping("/advanceQuery")
public class AdvanceQueryController extends ArchivesSystemBaseController{



    @Autowired
    AdvanceQueryService advanceQueryService;

    @Autowired
    XtZzjgDwbmService xtZzjgDwbmService;

    @Autowired
    private OriganizationService origanizationService;

    @Autowired
    EasyUIUtils easyUIUtils;

    @ApiOperation("获取所有初始化组件")
    @GetMapping("/getAllCondition")
    @org.springframework.cache.annotation.Cacheable("getAllCondition")
    public MessageResult getAllCondition()throws Exception{
        List<AdvanceQueryVo> allCondition = advanceQueryService.getAllCondition();
        return new MessageResult("获取成功",200,allCondition);
    }

    @ApiOperation("获取单个组件值")
    @GetMapping("/getSingleCondition")
   @org.springframework.cache.annotation.Cacheable("getSingleCondition")
    public MessageResult getSingleCondition(String bh)throws Exception{
        Object value= advanceQueryService.getSingleCondition(bh);
        return new MessageResult("获取成功",200,value);
    }


    @ApiOperation("获取查询结果")
    @PostMapping("/getResult")
    @org.springframework.cache.annotation.Cacheable("getResult")
    public  MessageResult getResult(@RequestBody AdQueryRequest request) throws Exception {
        String sql = advanceQueryService.startQuery(request,getCurrentDwbm());
        if (StringUtils.isNotEmpty(sql)){
            PageHelper.startPage(request.getPage(), request.getPageSize());
            List<Map> reuslt = advanceQueryService.getReuslt(sql);
            PageInfo page = new PageInfo(reuslt);
            return   new MessageResult("获取成功",200,reuslt, (int) page.getTotal());
        }
        return new MessageResult("获取成功",200,null);
    }
    @ApiOperation("获取单位编码")
    @GetMapping("/getDwbmTreeList")
    public  MessageResult getDwbmTreeList() throws Exception {


        //传入单位编码，返回结果集
        Map map = new HashMap();
        String currentDwbm = getCurrentDwbm();
        map.put("p_dwbm", currentDwbm);
        List<Map> list = null;
        try {
            list = origanizationService.getDwbm(currentDwbm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list == null) {
            super.errMsg("获取单位树失败", null, new Exception("获取单位树失败"));
            return null;
        }

        String topBM = "";
        //遍历集合，找到本单位的父单位
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> resultMap = list.get(i);
            for (String key : resultMap.keySet()) {
                if (key.equals("BM") && resultMap.get(key).equals(currentDwbm)) {
                    topBM = resultMap.get("FBM");
                    break;
                }
            }
        }
        //转为EasyUI结构：
        String s = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BM", "FBM", "MC", topBM);

        return new MessageResult("获取成功",200,s);
    }





}
