package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.*;
import com.start.boot.domain.*;
import com.start.boot.pojo.dto.CqPcxFlDto;
import com.start.boot.pojo.vo.ZdpcxVo;
import com.start.boot.query.ZdpccxQuery;
import com.start.boot.service.PcxFlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/18
 * @说明
 */
@Service
public class PcxFlServiceImpl implements PcxFlService {

    @Autowired
    Yx_Pc_PcxMapper yx_pc_pcxMapper;

    @Autowired
    Yx_Pc_PcxFlMapper yx_pc_pcxFlMapper;

    @Autowired
    YX_PC_JBXXMapper yx_pc_jbxxMapper;

    @Autowired
    @Qualifier("ajpcJdbcTemplate")
    JdbcTemplate jdbcTemplate;


    @Autowired
    ZdpccxMapper zdpccxMapper;

    @Autowired
    XtPcZdPcCxMapper xtPcZdPcCxMapper;

    @Override
    public List<Yx_Pc_Pcx> getPcxByPcxflbmAndPcslbm(String pcxflbm, String pcslbm) {
        Yx_Pc_PcxExample example = new Yx_Pc_PcxExample();
        example.createCriteria().andPcxflbmEqualTo(pcxflbm)
                .andPcslbmEqualTo(pcslbm);
        List<Yx_Pc_Pcx> yx_pc_pcxes = yx_pc_pcxMapper.selectByExample(example);
        return yx_pc_pcxes;
    }

    @Override
    public List<Yx_Pc_PcxFl>  getPcxFl(String pcslbm) {
        Yx_Pc_PcxFlExample example = new Yx_Pc_PcxFlExample();
        example.createCriteria().andPcslbmEqualTo(pcslbm);
        example.setOrderByClause("XH ASC");
        return yx_pc_pcxFlMapper.selectByExample(example);
    }

    @Override
    public String getSumKfz(String pcslbm) {
        String pcslbmTemp = new StringBuffer("'").append(pcslbm).append("'").toString();
        String sql="select SUM(pcfs) AS  kfztotal FROM yx_PC_PCX WHERE pcslbm="+pcslbmTemp;
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        Object kfztotal = result.get("kfztotal");
        if (kfztotal==null){
            return "";
        }
        return NumberFormat.getInstance().format(kfztotal);
    }

    @Override
    public String getSumKfzByPcslbmAndPcflbm(String pcslbm, String pcxflbm) {
        String s = new StringBuffer("'").append(pcxflbm).append("'").toString();
        String pcslbmTemp = new StringBuffer("'").append(pcslbm).append("'").toString();
        String sql="select SUM(pcfs) AS  kfztotal FROM yx_PC_PCX WHERE pcslbm="+pcslbmTemp+" and pcxflbm="+s + " and pcjg > 0";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        Object kfztotal = result.get("kfztotal");
        if (kfztotal==null){
            return "";
        }
        return NumberFormat.getInstance().format(kfztotal);
    }

    @Override
    public List<ZdpcxVo> getZdpcxResult(String pcslbm, String pcxflbm) {
        if (StringUtils.isEmpty(pcslbm)||StringUtils.isEmpty(pcxflbm)){
            return null;
        }
        ArrayList<ZdpcxVo> result = new ArrayList<>();
        //查询有哪些自动评查项
        Yx_Pc_PcxExample example = new Yx_Pc_PcxExample();
        example.createCriteria().andPcslbmEqualTo(pcslbm)
                                .andPcxflbmEqualTo(pcxflbm)
                                .andZdpccxIsNotNull();
        List<Yx_Pc_Pcx> zdpcList = yx_pc_pcxMapper.selectByExample(example);
        //查询基本参数
        YX_PC_JBXX jbxx = yx_pc_jbxxMapper.selectByPrimaryKey(pcslbm);
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("p_bmsah", jbxx.getBMSAH());
        parmMap.put("p_pclbbm", jbxx.getPCHDBM());
        parmMap.put("p_pcslbm", pcslbm);
        //设置参数值
        if (!CollectionUtils.isEmpty(zdpcList)){
            zdpcList.stream().forEach(data->{
                parmMap.put("p_pcxbm", data.getPcxbm());
                parmMap.put("p_pcxmc", data.getPcxmc());
                parmMap.put("p_pcxzf", data.getFz_gd()==null?0:data.getFz_gd());
                parmMap.put("p_mxkf", data.getFz_gd()==null?0:data.getFz_gd());
                parmMap.put("p_pcxfz", data.getPcfs());
                String zdpccx = data.getZdpccx();

                //获取自动评查程序的参数
                String sql="select zdpc_cxcs AS cxcs,zdpc_cx AS  cx FROM XT_PC_ZDPCCX WHERE zdpc_cxbm="+zdpccx;
                Map<String, Object> zdpccxResult = jdbcTemplate.queryForMap(sql);

                String cx = (String) zdpccxResult.get("cx");
                parmMap.put("cx",cx);
                String cxcs = (String) zdpccxResult.get("cxcs");
                if (!StringUtils.isEmpty(cxcs)){
                    String[] cxList = cxcs.split(";");
                    for (int i = 0; i < cxList.length; i++) {

                        parmMap.put("p_char"+(i+1),cxList[i]);
                    }
                }
                parmMap.put("p_result",null);
                parmMap.put("p_errmsg",null);
                //调用存储过程中的自动评查程序，获取结果
                try {
                    zdpccxMapper.getZdpccxResult(parmMap);
                    Object p_result = parmMap.get("p_result");
                    Object p_pcxfz = parmMap.get("p_pcxfz");
                    ZdpcxVo zdpcxVo = new ZdpcxVo();
                    zdpcxVo.setFz(p_pcxfz);
                    zdpcxVo.setMessage((String) p_result);
                    result.add(zdpcxVo);
                }catch (Exception e){
                    e.printStackTrace();
                    ZdpcxVo zdpcxVo = new ZdpcxVo();
                    zdpcxVo.setFz(0);
                    zdpcxVo.setMessage((String) parmMap.get("p_errmsg"));
                    result.add(zdpcxVo);
                }
            });
        }
        return result;
    }

    @Override
    public ZdpcxVo getZdpccxByPcx(ZdpccxQuery query) throws Exception{
        HashMap<String, Object> parmMap = new HashMap<>();
        parmMap.put("p_bmsah", query.getBmsah());
        parmMap.put("p_pclbbm", query.getPclbbm());
        String pcslbm = query.getPcslbm();
        parmMap.put("p_pcslbm", pcslbm);
        parmMap.put("p_pcxbm", query.getPcxbm());
        parmMap.put("p_pcxmc", query.getPcxmc());
        parmMap.put("p_mxkf", query.getMxkf());
        parmMap.put("p_pcxzf", query.getPcxzf());

        //查询有哪些自动评查项
        XtPcZdPcCx xtPcZdPcCx = xtPcZdPcCxMapper.selectByPrimaryKey(query.getZdpccxbm());
        parmMap.put("cx",xtPcZdPcCx.getZdpcCx());

        String zdpcCxcs = xtPcZdPcCx.getZdpcCxcs();
        if (!StringUtils.isEmpty(zdpcCxcs)){
            String[] cxList = zdpcCxcs.split(";");
            for (int i = 0; i < cxList.length; i++) {
                parmMap.put("p_char"+(i+1),cxList[i]);
            }
        }else{
            parmMap.put("p_char1","");
            parmMap.put("p_char2","");
            parmMap.put("p_char3","");
            parmMap.put("p_char4","");
            parmMap.put("p_char5","");
        }
        parmMap.put("p_errmsg",null);
        parmMap.put("p_result",null);
        parmMap.put("p_pcxfz",null);
        zdpccxMapper.getZdpccxResult(parmMap);
        Object errmsg = parmMap.get("p_errmsg");
        Object p_result = parmMap.get("p_result");
        ZdpcxVo zdpcxVo = new ZdpcxVo();
        zdpcxVo.setFz(p_result);
        zdpcxVo.setMessage((String) errmsg);
        return zdpcxVo;
    }

    @Override
    public List<CqPcxFlDto> getCqPcx(String pcslbm) {
        return yx_pc_pcxFlMapper.getCqPcx(pcslbm);
    }
}
