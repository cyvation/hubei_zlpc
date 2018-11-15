package com.start.boot.web;

import com.alibaba.fastjson.JSONArray;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.YxDcPdx;
import com.start.boot.query.PdxQuery;
import com.start.boot.service.PdxService;
import com.start.boot.support.utils.EasyUIHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/11/13.
 */
@RestController
@RequestMapping("/pdx")
public class PdxController extends ArchivesSystemBaseController{

    @Autowired
    private PdxService pdxService;

    @RequestMapping("/getPdx")
    public MessageResult getPdx(PdxQuery query){

        MessageResult result =null;
        try{
            List<Map> list = pdxService.getPdx(query);
            String s = EasyUIHelper.buildDataGridDataSource(list, list.size());
            result = new MessageResult(200, s);

        }catch (Exception e){
            result = new MessageResult("获取评定项失败",500, e.getMessage());
        }

        return result;
    }

    @RequestMapping("/savePdx")
    public MessageResult savePdx(String pcslbm,String json){
        MessageResult result = null;

        try{
            List<YxDcPdx> yxDcPdxes = JSONArray.parseArray(json, YxDcPdx.class);

            pdxService.savePdx(pcslbm, yxDcPdxes);

            result = new MessageResult(200, "保存成功");
        }catch (Exception e){
            result = new MessageResult("保存评定项失败",500, e.getMessage());
        }

        return  result;
    }

    @RequestMapping("/generatePdxDoc")
    public MessageResult generatePdxDoc(String pcslbm){

        MessageResult result = null;
        try{

           String path =  pdxService.generatePdxDoc(pcslbm);
           result = new MessageResult(200, path);
        }catch (Exception e){
            result = new MessageResult("生成失败", 500, e.getMessage());
        }

        return result;
    }



}
