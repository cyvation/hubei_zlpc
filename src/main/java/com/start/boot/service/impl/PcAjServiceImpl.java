package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.PcMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.Param_Pcaj;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import com.start.boot.service.PcAjService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caomin on 2017/11/8.
 */
@Service
public class PcAjServiceImpl implements PcAjService {

    @Autowired
    private YX_PC_JBXXMapper yx_pc_jbxxMapper;

    @Autowired
    private PcMapper pcMapper;

    @Override
    public List<YX_PC_JBXX> getByPchdbw(String pchdbw) {
        YX_PC_JBXXExample yx_pc_jbxxExample =
                new YX_PC_JBXXExample();
        yx_pc_jbxxExample.createCriteria().andPchdbmEqualTo(pchdbw).andSfscEqualTo("N");
        return   yx_pc_jbxxMapper.selectByExample(yx_pc_jbxxExample);
    }

    // 获取评查案件
    @Override
    public Param_Pcaj getPcaj(Param_Pcaj pcajParam) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",pcajParam.getPcdwbm());//获取要查询单位的（顶级单位可以查询下级院）
        map.put("p_pcflbm",pcajParam.getPcflbm());//评查分类编码
        map.put("p_ajmc",pcajParam.getAjmc());//案件名称
        map.put("p_pcrmc",pcajParam.getPcr_mc());//评查员
        map.put("p_cbrmc",pcajParam.getCbr());//承办人名称
        map.put("p_sfldba", pcajParam.getSfldba());//是否领导办案
        map.put("p_pcjl",pcajParam.getPcjl());//评查结论
        map.put("p_pczt",pcajParam.getPczt());//评查状态
        map.put("p_pckssj", OracleTimeUtils.format(pcajParam.getPckssj()));//评查开始时间
        map.put("p_pcjssj",OracleTimeUtils.format(pcajParam.getPcjssj()));//评查结束时间
        map.put("p_pagesize",pcajParam.getRows());//页大小
        map.put("p_pageindex",pcajParam.getPage());//页索引

        pcMapper.getPcaj(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        pcajParam.setCount(DataAccessHelper.getInteger(map, "p_count"));
        pcajParam.setList(DataAccessHelper.getListMap(map, "p_cursor"));
        return pcajParam;
    }

    // 删除评查案件
    @Override
    public boolean delPcaj(String pcslbm,String pcflbm) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm",pcslbm);
        map.put("p_pcflbm",pcflbm);

        // 操作数据库
        pcMapper.delPcaj(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 获取评查组（变更评查人）
    @Override
    public List<Map> getPcz(String pchdbm,String pcflbm) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",pchdbm);//评查活动编码
        map.put("p_pcflbm",pcflbm);//评查分类编码

        pcMapper.getPcz(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    // 获取评查人信息（变更评查人）
    @Override
    public List<Map> getPczry(String pczbm) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pczbm",pczbm);//评查组编码

        pcMapper.getPczry(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    // 变更评查人
    @Override
    public boolean updPcr(YX_PC_JBXX jxbbParam) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm",jxbbParam.getPCSLBM());
        map.put("p_fpdz_fpr_dwbm",jxbbParam.getFPDZFPRDWBM());
        map.put("p_fpdz_fpr_gh",jxbbParam.getFPDZFPRGH());
        map.put("p_fpdz_fpr_dwmc",jxbbParam.getFPDZFPRDWBM());
        map.put("p_fpdz_fpr_mc",jxbbParam.getFPDZFPRMC());
        map.put("p_fpdr_fpr_dwbm",jxbbParam.getFPDRFPRDWBM());
        map.put("p_fpdr_fpr_dwmc",jxbbParam.getFPDRFPRDWMC());
        map.put("p_fpdr_fpr_gh",jxbbParam.getFPDRFPRGH());
        map.put("p_fpdr_fpr_mc",jxbbParam.getFPDRFPRMC());
        map.put("p_pcz_bm",jxbbParam.getPCZBM());
        map.put("p_pcz_mc",jxbbParam.getPCZMC());
        map.put("p_pcr_dwbm",jxbbParam.getPCRDWBM());
        map.put("p_pcr_dwmc",jxbbParam.getPCRDWMC());
        map.put("p_pcr_gh",jxbbParam.getPCRGH());
        map.put("p_pcr_mc",jxbbParam.getPCRMC());

        // 操作数据库
        pcMapper.updPcr(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    @Override
    public void delPcajLog(Map map) {
        pcMapper.delPcajLog(map);
    }

}
