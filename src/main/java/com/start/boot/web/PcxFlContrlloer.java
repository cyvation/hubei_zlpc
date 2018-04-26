package com.start.boot.web;

import com.start.boot.common.MessageResult;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.dao.ajpc.Yx_Pc_PcxMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.dto.CqPcxFlDto;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import com.start.boot.pojo.vo.ZdpcxVo;
import com.start.boot.query.ZdpccxQuery;
import com.start.boot.service.PcxFlService;
import com.start.boot.service.XtDmFldmService;
import com.start.boot.service.YxPcZdpcxService;
import com.start.boot.support.utils.EasyUIHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/18
 * @说明 评查项办理、评查项
 */
@RestController
@RequestMapping("/pcx")
public class PcxFlContrlloer {

    @Autowired
    PcxFlService pcxFlService;

    @Autowired
    Yx_Pc_PcxMapper yx_pc_pcxMapper;

    @Autowired
    YX_PC_JBXXMapper yx_pc_jbxxMapper;

    @Autowired
    YxPcZdpcxService yxPcZdpcxService;

    @Autowired
    XtDmFldmService xtDmFldmService;



    @ApiOperation("根据评查分类编码和评查受理编码获取评查项")
    @GetMapping("/getPcxByPcxflbmAndPcslbm")
    public MessageResult getPcxByPcxflbmAndPcslbm(String pcslbm,String pcxflbm) throws Exception {
        List<Yx_Pc_Pcx> result = pcxFlService.getPcxByPcxflbmAndPcslbm(pcxflbm, pcslbm);
        String sumKfz = pcxFlService.getSumKfzByPcslbmAndPcflbm(pcslbm,pcxflbm);
        return new MessageResult(sumKfz,200,result);
    }

    @ApiOperation("根据评查受理编码获取评查项分类")
    @GetMapping("/getPcxFl")
    public MessageResult getPcxFl(String pcslbm) throws Exception {
        List<Yx_Pc_PcxFl> pcxFl = pcxFlService.getPcxFl(pcslbm);
        String sumKfz = pcxFlService.getSumKfz(pcslbm);
        ArrayList<Map> resultMap = new ArrayList<>();
        pcxFl.stream().forEach(yx_pc_pcxFl -> {
            try {
                if (StringUtils.isEmpty(yx_pc_pcxFl.getPcxflfbm())) {
                    yx_pc_pcxFl.setPcxflfbm("empty");
                }
                resultMap.add(BeanUtils.describe(yx_pc_pcxFl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String json = EasyUIHelper.buildTreeDataSourceWithoutIconCol(resultMap, "pcxflbm", "pcxflfbm", "pcxflmc", "empty");
        return new MessageResult(sumKfz, 200, json);
    }


    @ApiOperation("保存评查项分类")
    @PostMapping("/savePcx")
    public MessageResult savePcx(@RequestBody Yx_Pc_PcxFlVo pcxFlVo)throws Exception{
        List<Yx_Pc_Pcx> pcxList = pcxFlVo.getPcxList();
        if (!CollectionUtils.isEmpty(pcxList)){
            pcxList.stream().forEach(data->{
                yx_pc_pcxMapper.updateByPrimaryKeySelective(data);
            });
        }
        return new MessageResult("保存成功",200);
    }


    @ApiOperation("保存评查结果")
    @PostMapping("/savePcjl")
    public MessageResult savePcjl(YX_PC_JBXX jbxx)throws Exception{
        yx_pc_jbxxMapper.updateByPrimaryKeySelective(jbxx);
        return new MessageResult("保存成功",200);
    }



    @ApiOperation("根据每个小评查项，获取自动评查程序结果")
    @PostMapping("/getZdpccxByPcx")
    public MessageResult getZdpccxByPcx(ZdpccxQuery query)throws Exception{
        try {
            ZdpcxVo zdpccxByPcx = pcxFlService.getZdpccxByPcx(query);
            return  new MessageResult("自动评查成功",200,zdpccxByPcx);
        }catch (Exception e){
            return  new MessageResult("自动评查成功",200,new ZdpcxVo());
        }
    }

    @ApiOperation("根据评查项编码和评查受理编码，获取单个评查结果")
    @GetMapping("/getYxPcZdPcByKey")
    public MessageResult getYxPcZdPcByKey(String pcxbm,String pcslbm)throws Exception{
        YxPcZdPc yxPcZdPcByKey = yxPcZdpcxService.getYxPcZdPcByKey(pcxbm, pcslbm);
        return  new MessageResult("获取成功",200,yxPcZdPcByKey);

    }
    @ApiOperation("根据类别编码获取数据字典")
    @GetMapping("/getXtDmFldmBy")
    public MessageResult getXtDmFldmBy(String lbbm)throws Exception{
        List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy(lbbm);
        if (!CollectionUtils.isEmpty(xtDmFldmBy)) {
            ArrayList<Map> result = new ArrayList<>();
            xtDmFldmBy.stream().forEach(t -> {
                try {
                    Map<String, String> describe = BeanUtils.describe(t);
                    result.add(describe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            String json = EasyUIHelper.buildDataGridDataSource(result, result.size());
            return new MessageResult("获取成功", 200, json);
        }
      return new MessageResult("获取结果为空", 200, null);
    }

    @ApiOperation("获取评查表")
    @GetMapping("/getMarkSheet")
    public MessageResult getMarkSheet(String pcslbm)throws Exception {
        List<CqPcxFlDto> cqPcx = pcxFlService.getCqPcx(pcslbm);
        ArrayList<Map> resultMap = new ArrayList<>();
        cqPcx.stream().forEach(t -> {
            try {
                resultMap.add(BeanUtils.describe(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String s = EasyUIHelper.buildDataGridDataSource(resultMap, cqPcx.size());
        return new MessageResult("获取评查表成功", 200, s);
    }


}
