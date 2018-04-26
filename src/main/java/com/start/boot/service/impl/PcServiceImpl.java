package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.PcMapper;
import com.start.boot.domain.*;
import com.start.boot.service.PcService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Service
@Transactional
public class PcServiceImpl implements PcService {

    @Autowired
    private PcMapper pcMapper;


    // 获取评查分类列表
    @Override
    public List<Map> getPcfl() throws Exception {
        String errMsg = "";

        Map map = new HashMap<>();
        pcMapper.getPcfl(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 获取评查活动
    @Override
    public List<Map> getPchd(Param_Hd param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcdwbm", param.getPcdwbm());
        map.put("p_pcflbm",param.getPcflbm());

        //操作数据库
        pcMapper.getPchd(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 获取评查人员库人员列表
    @Override
    public Param_Ryk getPckryAll(Param_Ryk param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm_ryk",param.getRykdwbm());
        map.put("p_dwbm_ry",param.getDwbm_ry());
        map.put("p_ywbm",param.getYwbm());

        // 操作数据库：
        pcMapper.getPckryAll(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Map> list = DataAccessHelper.getListMap(map,"p_cursor");
        param.setList(list);

        return param;
    }

    // 添加评查方案
    @Override
    public Param_Pcfa addPcfa(Param_Pcfa param) throws Exception {
        String errMsg = "";

        // 清空用户快捷功能列表
        Map map = new HashMap();
        map.put("p_pcdwbm", param.getPcdwbm());
        map.put("p_pchdmc", param.getPchdmc());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_sxgzbm", param.getSxgzj());
        map.put("p_sfss", param.getSfss());
        map.put("p_sffy", param.getSffy());
        map.put("p_pckssj", OracleTimeUtils.format(param.getPckssj()));
        map.put("p_pcjssj", OracleTimeUtils.format(param.getPcjssj()));
        map.put("p_cjrdwbm", param.getCjrdwbm());
        map.put("p_cjrgh", param.getCjrgh());
        map.put("p_cjrmc", param.getCjrmc());
        map.put("p_sm", param.getSm());
        map.put("p_pcmbj", param.getPcmbj());

        pcMapper.addPchd(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        param.setPchdbm(DataAccessHelper.getString(map, "p_pchdbm").trim());
        if (StringUtils.isEmpty(param.getPchdbm()))
            throw new Exception("评查活动编码为空。");

        // 遍历添加评查组及评查员
        map.clear();
        for (Param_Pcz pcz : param.getPczlb()) {
            map.put("p_pchdbm", param.getPchdbm());
            map.put("p_pczmc", pcz.getPczmc());
            map.put("p_sm", pcz.getSm());

            pcMapper.addPcfz(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);

            pcz.setPczbm(DataAccessHelper.getString(map, "p_pczbm").trim());
            if (StringUtils.isEmpty(pcz.getPczbm()))
                throw new Exception("评查组【" + pcz.getPczmc() + "】编码为空。");
            pcz.setPchdbm(param.getPchdbm());

            for (Param_Pcy pcy : pcz.getPcyarr()) {
                map.put("p_pchdbm", param.getPchdbm());
                map.put("p_pczbm", pcz.getPczbm());
                map.put("p_dwbm", pcy.getDWBM());
                map.put("p_gh", pcy.getGH());
                map.put("p_jsbm", pcy.getJSBM());
                map.put("p_sm", pcy.getSM());

                pcMapper.addXzry(map);
                errMsg = DataAccessHelper.getString(map, "p_errmsg");
                if (StringUtils.isNoneEmpty(errMsg))
                    throw new Exception(errMsg);
            }

        }

        // 遍历新增评查案件
        map.clear();
        for (Param_Pcaj pcaj : param.getPcajlb()) {

            pcaj.setPcdwbm(param.getPcdwbm());
            pcaj.setPcflbm(param.getPcflbm());
            pcaj.setPchdbm(param.getPchdbm());
            pcaj.setPcmbbm(param.getPcmbj());
            map.put("p_pcdwbm", pcaj.getPcdwbm());
            map.put("p_pcflbm", pcaj.getPcflbm());
            map.put("p_pchdbm", pcaj.getPchdbm());
            map.put("p_pcmbbm", pcaj.getPcmbbm());
            map.put("p_sxgzbm", pcaj.getSxgzbm());
            map.put("p_bpc_wcbzrq", OracleTimeUtils.format(pcaj.getWcbzrq()));
            map.put("p_dpc_bmsah", pcaj.getBmsah());
            map.put("p_dpc_dwbm", pcaj.getDwbm());
            map.put("p_czr_dwbm", param.getCjrdwbm());
            map.put("p_czr_dwmc", param.getCjrdwmc());
            map.put("p_czr_gh", param.getCjrgh());
            map.put("p_czr_mc", param.getCjrmc());

            pcMapper.addPcaj(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);

            pcaj.setPcslbm(DataAccessHelper.getString(map, "p_pcslbm").trim());
            if (StringUtils.isEmpty(pcaj.getPcslbm()))
                throw new Exception("案件【" + pcaj.getBmsah() + "】评查受理编码为空。");

            pcaj.setPcsah(DataAccessHelper.getString(map, "p_pcsah").trim());
            if (StringUtils.isEmpty(pcaj.getPcsah()))
                throw new Exception("案件【" + pcaj.getBmsah() + "】评查受案号为空。");

        }

        return param;
    }

    // 修改评查活动信息
    @Override
    public boolean updPchd(Param_Pcfa param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_pchdmc", param.getPchdmc());
        map.put("p_sxgzbm", param.getSxgzj());
        map.put("p_sfss", param.getSfss());
        map.put("p_sffy", param.getSffy());
        map.put("p_pckssj", OracleTimeUtils.format(param.getPckssj()));
        map.put("p_pcjssj", OracleTimeUtils.format(param.getPckssj()));
        map.put("p_sm", param.getSm());
        map.put("p_pcmbj", param.getPcmbj());

        pcMapper.updPchd(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 新增评查分组
    @Override
    public String addPcfz(Param_Pcz param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_pczmc", param.getPczmc());
        map.put("p_sm", param.getSm());

        // 新增或编辑评查组
        if (param.getPczbm().contains("PCZ")){
            pcMapper.addPcfz(map);
        }else{
            map.put("p_pczbm", param.getPczbm());
            pcMapper.updPcfz(map);
        }
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        param.setPczbm(DataAccessHelper.getString(map, "p_pczbm").trim());
        if (StringUtils.isEmpty(param.getPczbm()))
            throw new Exception("评查组【" + param.getPczmc() + "】编码为空。");

        // 清除小组人员
        map.clear();
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_pczbm", param.getPczbm());
        pcMapper.delXzry(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        // 添加小组人员
        map.clear();
        for (Param_Pcy pcy : param.getPcyarr()) {
            map.put("p_pchdbm", param.getPchdbm());
            map.put("p_pczbm", param.getPczbm());
            map.put("p_dwbm", pcy.getDWBM());
            map.put("p_gh", pcy.getGH());
            map.put("p_jsbm", pcy.getJSBM());
            map.put("p_sm", pcy.getSM());

            pcMapper.addXzry(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);
        }

        //评查组编码
        return  param.getPczbm();
    }

    // 校验评查组是否已分配有评查案件
    @Override
    public String valPcfz(Param_Pcz param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_pczbm",param.getPczbm());

        pcMapper.valPcfz(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getString(map,"p_tsxx");
    }

    // 删除评查分组
    @Override
    public boolean delPcfz(Param_Pcz param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_pczbm",param.getPczbm());

        pcMapper.delPcfz(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 校验评查员是否已分配有评查案件
    @Override
    public String valXzry(Param_Pcy param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",param.getPCHDBM());
        map.put("p_pczbm",param.getPCZBM());
        map.put("p_dwbm",param.getDWBM());
        map.put("p_gh",param.getGH());
        pcMapper.valXzry(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");

        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return  DataAccessHelper.getString(map,"p_tsxx");

    }

    // 评查案件分配
    @Override
    public boolean assignCase(User userInfo, List<Param_Pcaj> list) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        for (Param_Pcaj item : list) {
            map.put("p_pcslbm", item.getPcslbm());
            map.put("p_pcflbm", item.getPcflbm());
            map.put("p_pchdbm", item.getPchdbm());
            map.put("p_pcmbbm", item.getPcmbbm());
            map.put("p_bmsah", item.getBmsah());
            map.put("p_pczbm", item.getPcz_bm());
            map.put("p_pczmc", item.getPcz_mc());
            map.put("p_czr_dwbm", userInfo.getDWBM());
            map.put("p_czr_dwmc", userInfo.getDWMC());
            map.put("p_czr_gh", userInfo.getGH());
            map.put("p_czr_mc", userInfo.getMC());

            pcMapper.assignPcz(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);

            map.put("p_pcr_dwbm", item.getPcr_dwbm());
            map.put("p_pcr_dwmc", item.getPcr_dwmc());
            map.put("p_pcr_gh", item.getPcr_gh());
            map.put("p_pcr_mc", item.getPcr_mc());
            pcMapper.assignPcr(map);
            errMsg = DataAccessHelper.getString(map, "p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);
        }

        return true;
    }

    // 删除评查活动
    @Override
    public boolean delPchd(String pchdbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",pchdbm);

        // 操作数据库
        pcMapper.delPchd(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 获取评查人员库人员列表
    @Override
    public Param_Ryk getPckry(Param_Ryk ryk) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_rykdwbm",ryk.getRykdwbm());
        map.put("p_dwbm_ry",ryk.getDwbm_ry());
        map.put("p_pcy_mc",ryk.getPcr_mc());
        map.put("p_ywbm",ryk.getYwbm());
        map.put("p_pagesize",ryk.getRows());
        map.put("p_pageindex",ryk.getPage());


        // 操作数据库：
        pcMapper.getPckry(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        ryk.setList(DataAccessHelper.getListMap(map,"p_cursor"));
        ryk.setCount(DataAccessHelper.getInteger(map,"p_count"));

        return ryk;

    }

    // 获取评查人员库人员列表
    @Override
    public Param_Ryk getZzjgry(Param_Ryk ryk) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_rykdwbm",ryk.getRykdwbm());
        map.put("p_dwbm",ryk.getDwbm_ry());
        map.put("p_jsmc", ryk.getJsmc());
        map.put("p_mc",ryk.getPcr_mc());
        map.put("p_ywbm",ryk.getYwbm());
        map.put("p_type",ryk.getType());
        map.put("p_pagesize",ryk.getRows());
        map.put("p_pageindex",ryk.getPage());

        // 操作数据库：
        pcMapper.getZzjgry(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        ryk.setList(DataAccessHelper.getListMap(map,"p_cursor"));
        ryk.setCount(DataAccessHelper.getInteger(map,"p_count"));

        return ryk;
    }

    // 从组织机构中提取人员到人员库的人员列表
    @Override
    public List<Param_Ryk> add_pcryk(List<Param_Ryk> list,String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) != null){
                map.put("p_ryk_dwbm",dwbm);
                map.put("p_dwbm",list.get(i).getDwbm_ry());
                map.put("p_gh",list.get(i).getPcr_gh());
                map.put("p_bmbm",list.get(i).getBmbm());
                map.put("p_bmmc",list.get(i).getBmmc());
                map.put("p_jsbm",list.get(i).getJsbm());
                map.put("p_jsmc",list.get(i).getJsmc());
                map.put("p_ywbm",list.get(i).getYwbm());

                // 操作数据库：
                pcMapper.add_pcryk(map);
                errMsg = DataAccessHelper.getString(map,"p_errmsg");
                if (StringUtils.isNoneEmpty(errMsg))
                    throw new Exception(errMsg);
            }
        }

        return list;
    }

    // 删除人员库列表人员
    @Override
    public List<Param_Ryk> del_rykry(List<Param_Ryk> list,String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        for (int i = 0; i < list.size(); i++) {

            map.put("p_ryk_dwbm",dwbm);
            map.put("p_dwbm",list.get(i).getDwbm_ry());
            map.put("p_gh",list.get(i).getPcr_gh());
            map.put("p_ywbm",list.get(i).getYwbm());

            // 操作数据库：
            pcMapper.del_rykry(map);
            errMsg = DataAccessHelper.getString(map,"p_errmsg");
            if (StringUtils.isNoneEmpty(errMsg))
                throw new Exception(errMsg);
        }

        return list;
    }

    // 添加卷宗文件
    @Override
    public Param_Jzwj addJzwj(Param_Jzwj param) throws Exception {
        String errMsg = "";

        // 清空用户快捷功能列表
        Map map = new HashMap();
        map.put("p_fjzwjbh", param.getFjzwjbh());
        map.put("p_dwbm", param.getDwbm());
        map.put("p_pczybm", param.getPczybm());
        map.put("p_wjlx", param.getWjlx());
        map.put("p_wsmbbh", param.getWsmbbh());
        map.put("p_jzmlh", param.getJzmlh());
        map.put("p_gxlx", param.getGxlx());
        map.put("p_wjmc", param.getWjmc());
        map.put("p_wscflj", param.getWscflj());
        map.put("p_wjkzm", param.getWjkzm());
        map.put("p_nzrdwbm", param.getNzrdwbm());
        map.put("p_nzrdwmc", param.getNzrdwmc());
        map.put("p_nzrgh", param.getNzrgh());
        map.put("p_nzrxm", param.getNzrxm());

        pcMapper.addJzwj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        param.setJzwjbh(DataAccessHelper.getString(map, "p_jzwjbh").trim());
        if (StringUtils.isEmpty(param.getJzwjbh()))
            throw new Exception("卷宗文件编号为空。");

        return param;
    }

    // 修改卷宗文件
    @Override
    public Param_Jzwj updJzwj(Param_Jzwj param) throws Exception {
        String errMsg = "";

        // 清空用户快捷功能列表
        Map map = new HashMap();
        map.put("p_jzwjbh", param.getJzwjbh());
        map.put("p_gxlx", param.getGxlx());
        map.put("p_wjmc", param.getWjmc());

        pcMapper.updJzwj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return param;
    }

    // 删除卷宗文件
    @Override
    public Param_Jzwj delJzwj(Param_Jzwj param) throws Exception {
        String errMsg = "";

        // 删除卷宗文件
        Map map = new HashMap();
        map.put("p_jzwjbh", param.getJzwjbh());

        pcMapper.delJzwj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return param;
    }

    // 删除卷宗文件（通过评查资源编码）
    @Override
    public Param_Jzwj delJzwjByPczybm(Param_Jzwj param) throws Exception {
        String errMsg = "";

        // 删除卷宗文件
        Map map = new HashMap();
        map.put("p_pczybm", param.getPczybm());

        pcMapper.delJzwjByPczybm(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return param;
    }

    // 获取活动审批，送审接收人员（案管负责人）
    @Override
    public List<Map> getHdsp(String dwbm, String gh) throws Exception{
        String errMsg = "";

        Map map = new HashMap<>();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        pcMapper.getHdsp(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 评查方案送审
    @Override
    public boolean addPcfaps(Param_Pcsp pcspParam) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm", pcspParam.getPchdbm());
        map.put("p_ssrgh",pcspParam.getSsrgh());
        map.put("p_ssrxm", pcspParam.getSsrxm());
        map.put("p_ssrdwbm",pcspParam.getSsrdwbm());
        map.put("p_ssrdwmc",pcspParam.getSsrdwmc());
        map.put("p_sprdwbm",pcspParam.getSprdwbm());
        map.put("p_sprdwmc", pcspParam.getSprdwmc());
        map.put("p_sprgh",pcspParam.getSprgh());
        map.put("p_sprxm", pcspParam.getSprxm());
        map.put("p_pcspbm", pcspParam.getPcspbm());
        // 操作数据库
        pcMapper.addPcfaps(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 评查方案审批
    @Override
    public boolean uptDealFasp(Param_Pcsp pcspParam) throws Exception{

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcspbm", pcspParam.getPcspbm());
        map.put("p_sprdwbm",pcspParam.getSprdwbm());
        map.put("p_sprdwmc", pcspParam.getSprdwmc());
        map.put("p_sprgh", pcspParam.getSprgh());
        map.put("p_sprxm",pcspParam.getSprxm());
        map.put("p_spjl", pcspParam.getSpjl());
        map.put("p_spyj", pcspParam.getSpyj());

        // 操作数据库
        pcMapper.uptDealFasp(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 启动评查活动
    @Override
    public boolean startPchd(Param_Hd param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_qdr_dwbm",param.getQdr_dwbm());
        map.put("p_qdr_gh",param.getQdr_gh());
        map.put("p_qdr_mc",param.getQdr_mc());

        // 操作数据库
        pcMapper.startPchd(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 结束评查活动
    @Override
    public boolean finishPchd(Param_Hd param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_jsr_dwbm",param.getJsr_dwbm());
        map.put("p_jsr_gh",param.getJsr_gh());
        map.put("p_jsr_mc",param.getJsr_mc());

        // 操作数据库
        pcMapper.finishPchd(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 获取活动状态编码
    @Override
    public Param_Pchdzt getHdztBM(String pchdbm) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pchdbm", pchdbm);

        pcMapper.getHdztBM(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Param_Pchdzt> list = (List<Param_Pchdzt>)map.get("p_cursor");

        return list == null || list.size() <= 0 ? null : list.get(0);
    }

    // 获取评查审批送审接收人员
    @Override
    public List<Map> getPcsp(Map map) throws Exception {
        String errMsg = "";

        // 操作数据库
        pcMapper.getPcsp(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 获取角色名称
    @Override
    public List<Map> getJsmc(Map map) throws Exception {
        List<Map> list = null;
        try {
            // 操作数据库
            list = pcMapper.getJsmc(map);
        }catch (Exception e){
            throw e;
        }
        return list;
    }

}
