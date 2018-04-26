package com.start.boot.web;


import com.alibaba.fastjson.JSON;
import com.start.boot.common.MessageResult;
import com.start.boot.domain.DataDictionary;
import com.start.boot.domain.Fldmlb;
import com.start.boot.domain.Message;
import com.start.boot.service.DataDictionaryService;
import com.start.boot.support.structure.EasyUIDatagrid;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.EasyUIUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    DataDictionaryService dataDictionaryService;

    @ApiOperation("获取主要分类")
    @GetMapping("/getSjzdfl")
    public MessageResult getSjzdfl(){
        MessageResult messageResult;

        try {
            List<Map> sjzdfl = dataDictionaryService.getSjzdfl();
            String result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(sjzdfl, "lbbm", "fbm", "lbmc", "-1");
            messageResult = new MessageResult("查询成功",200, JSON.parse(result));
        } catch (Exception e) {
            messageResult = new MessageResult("查询失败",500,e);
        }

        return messageResult;
    }


    @ApiOperation("根据类别编码查询数据字典")
    @GetMapping("/getDataDictionaryByLBBM")
    public MessageResult getDataDictionaryByLBBM(String lbbm){
        MessageResult messageResult;

        try {
            List<Map> dictionaryByLBBM  = dataDictionaryService.getDictionaryByLBBM(lbbm);
            String result = EasyUIHelper.buildTreeGridDataSource(dictionaryByLBBM,dictionaryByLBBM.size(),"fdm");
            messageResult = new MessageResult("查询成功",200,JSON.parse(result));
        } catch (Exception e) {
            messageResult = new MessageResult("查询失败",500,e);
        }

        return messageResult;
    }

    @ApiOperation("增加/修改分类代码")
    @PostMapping("/addFldm")
    public MessageResult addFldm(DataDictionary dataDictionary,boolean isAdd){

        MessageResult messageResult;
        //根据参数判断是新增还是修改
        if(isAdd){
            try {
                int i = dataDictionaryService.addFldm(dataDictionary);
                messageResult = new MessageResult("增加分类代码成功",200);
            } catch (Exception e) {
                messageResult = new MessageResult("增加分类代码失败",500);
                e.printStackTrace();
            }
        }else {
            try {
                dataDictionaryService.updateFldm(dataDictionary);
                messageResult = new MessageResult("修改成功",200);
            } catch (Exception e) {
                messageResult = new MessageResult("修改失败",500);
            }
        }
        return messageResult;
    }

    @ApiOperation("修改分类代码")
    @PostMapping("/updateFldm")
    public MessageResult updateFldm(DataDictionary dataDictionary){
        MessageResult messageResult;

        try {

            int i = dataDictionaryService.updateFldm(dataDictionary);
            messageResult = new MessageResult("修改分类代码成功",200,i);
        } catch (Exception e) {
            messageResult = new MessageResult("修改分类代码失败",500,e);
        }

        return messageResult;
    }

    @ApiOperation("删除分类代码")
    @PostMapping("/deleteFldm")
    public MessageResult deleteFldm(DataDictionary dataDictionary){

        MessageResult messageResult;

        try {
            int i = dataDictionaryService.deleteFldm(dataDictionary);
            messageResult = new MessageResult("删除分类代码成功",200,i);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("删除分类代码失败",500,e);
        }

        return messageResult;
    }

    @ApiOperation("启用分类代码")
    @PostMapping("/updateFldmStatus")
    public MessageResult updateFldmStatus(DataDictionary dataDictionary){

        MessageResult messageResult;

        try {
            int i = dataDictionaryService.updateFldmStatus(dataDictionary);
            messageResult = new MessageResult("启用分类代码成功",200);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("启用分类代码失败",500);
        }

        return messageResult;
    }

}
