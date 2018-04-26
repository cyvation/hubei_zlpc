package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.PcxVoList;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import com.start.boot.service.OfflineService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import com.start.boot.support.utils.GetDw;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offline")
@SuppressWarnings("all")
public class OfflineController extends ArchivesSystemBaseController {

    @Autowired
    private OfflineService offlineService;

    @ApiOperation("获取评查标准")
    @GetMapping("/get_pcbz")
    public String getPcbz(String pcflbm,String dwbm) {
        String result = "";
        try {
            List<Map> list = offlineService.getPcbz(pcflbm,dwbm);
            result =  success(list,"获取评查标准成功");
        } catch (Exception e) {
            super.errMsg("获取评查标准失败", "标准", e);
            result = failure(e.getMessage(), "获取评查标准失败");
        }

        return result;
    }
    @ApiOperation("获取案件类别")
    @GetMapping("/getAjmb")
    public String getAjmb(String pcflbm,String pchdbm,String dwbm) {
        String result = "";
        try {
            List<Map> list = offlineService.getAjmb(pcflbm,pchdbm,dwbm);
            result =  success(list,"获取评查标准成功");
        } catch (Exception e) {
            super.errMsg("获取评查标准失败", "标准", e);
            result = failure(e.getMessage(), "获取评查标准失败");
        }

        return result;
    }
    @ApiOperation("获取评查分类树形")
    @GetMapping("/getPcTree")
    public String getPcTree(String mbNo,String pcflbm) {
        String result = "";
        try {
            List<Map> list = offlineService.getPcTree(mbNo,pcflbm);
            result =  success(list,"获取评查标准成功");
        } catch (Exception e) {
            super.errMsg("获取评查标准失败", "标准", e);
            result = failure(e.getMessage(), "获取评查标准失败");
        }

        return result;
    }
    @ResponseBody
    @RequestMapping("/saveOfflineInfo")
    public String saveOfflineInfo(String vo, String baseInfo) {
        String result = "";
        try {
            Map maps = (Map) JSON.parse(baseInfo);
            PcxVoList param = FastJsonUtils.toObject(PcxVoList.class, vo);
            List<Yx_Pc_PcxFlVo> pcxFlVos = param.getPcxFlVos();
            if (pcxFlVos.isEmpty()) {
                return failure("400", "提交数据为空，保存失败");
            }
            int i=offlineService.saveOfflineInfo(pcxFlVos,maps);
            result =  success(i,"保存案件评查信息成功");
        } catch (Exception e) {
            super.errMsg("保存案件评查信息失败", vo, e);
            result = failure(e.getMessage(), "保存案件评查信息失败");
        }
        return result;
    }
    @RequestMapping("/loadOfflineList")
    public String loadOfflineList(String json) {
        //设置相应内容
        String result = "";
        try {
            Map maps = (Map) JSON.parse(json);
            maps.put("page",getParameter("page"));
            maps.put("rows",getParameter("rows"));
            Map data = offlineService.loadOfflineList(maps);
            result = success(data,"获取线下案件评查信息列表成功");//EasyUIHelper.buildDataGridDataSource((List<Map>) data.get("rows"), Integer.valueOf(data.get("total")+""));
        } catch (Exception e) {

            super.errMsg("获取线下案件评查信息列表失败", json, e);
            result = failure(e.getMessage(), "获取线下案件评查信息列表失败");
        }
        return result;
    }
    @ApiOperation("获取评查结果")
    @GetMapping("/getPcjgInfo")
    public String getPcjgInfo(String pcslbm,String dw) {
        String result = "";
        try {
            List<Map> list = offlineService.getPcjgInfo(pcslbm,dw);
            result =  success(list,"获取评查结果成功");
        } catch (Exception e) {
            super.errMsg("获取评查结果失败", pcslbm, e);
            result = failure(e.getMessage(), "获取评查结果失败");
        }

        return result;
    }
    @ApiOperation("获取评查数据")
    @RequestMapping("/getPcAjInfo")
    public String getPcAjInfo(String pcslbm,String dwbm) {
        //设置相应内容
        String result = "";
        try {
            Map maps = new HashMap<>();
            maps.put("pcslbm",pcslbm);
            maps.put("dwbm",dwbm);
            Map data = offlineService.getPcAjInfo(maps);
            result = success(data,"获取线下案件评查信息成功");
        } catch (Exception e) {

            super.errMsg("获取线下案件评查信息失败", pcslbm, e);
            result = failure(e.getMessage(), "获取线下案件评查信息失败");
        }
        return result;
    }
    @ApiOperation("获取评查结果")
    @GetMapping("/updatePcaj")
    public String updatePcaj(String pcslbm,String dw) {
        String result = "";
        try {
            int i = offlineService.updatePcaj(pcslbm,dw);
            result =  success(i,"删除案件成功");
        } catch (Exception e) {
            super.errMsg("删除案件失败", pcslbm, e);
            result = failure(e.getMessage(), "删除案件失败");
        }

        return result;
    }
    @ApiOperation("判断案件是否存在")
    @ResponseBody
    @RequestMapping("/isOnAj")
    public String isOnAj(String json) {
        String result = "";
        try {
            Map maps = (Map) JSON.parse(json);
            int i = offlineService.isOnAj(maps);
            result =  success(i,"查询案件成功");
        } catch (Exception e) {
            super.errMsg("查询案件失败", json, e);
            result = failure(e.getMessage(), "查询案件失败");
        }
        return result;
    }
}
