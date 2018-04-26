package com.start.boot.service.impl;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.HandleMapper;
import com.start.boot.domain.*;
import com.start.boot.service.HandleService;
import com.start.boot.service.JbxxService;
import com.start.boot.service.MessageService;
import com.start.boot.service.SystemCoreConfigService;
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
 * Created by lei on 2017/11/4.
 */
@Service
@Transactional
public class HandleServiceImpl implements HandleService {


    @Autowired
    private HandleMapper handleMapper;
    @Autowired
    MessageService messageService;

    @Autowired
    JbxxService jbxxService;


    @Autowired
    SystemCoreConfigService systemConfigService;

    // 评查案件列表
    @Override
    public Param_Pager get_pclist(Param_sp param_sp) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcrdwbm", param_sp.getPcrdwbm());
        map.put("p_pcrgh", param_sp.getPcrgh());
        map.put("p_keyword", param_sp.getKeyword());
        map.put("p_pczt", param_sp.getPczt());
        map.put("p_pagesize", param_sp.getRows());
        map.put("p_pageindex", param_sp.getPage());

        // 操作数据库
        handleMapper.get_pclist(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_sp.setList(pcList);
        param_sp.setCount(p_count);

        return param_sp;
    }

    //评查审批案件列表
    @Override
    public Param_Pager get_splst(Param_sp param_sp) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_sprdwbm", param_sp.getSprdwbm());
        map.put("p_sprgh", param_sp.getSprgh());
        map.put("p_keyword", param_sp.getKeyword());
        map.put("p_pagesize", param_sp.getRows());
        map.put("p_pageindex", param_sp.getPage());

        // 操作数据库
        handleMapper.get_splst(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_sp.setList(pcList);
        param_sp.setCount(p_count);

        return param_sp;
    }

    // 根据评查活动编号获取当前活动的审批意见，结论
    @Override
    public Param_Pager get_spxx(Param_sp param_sp) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_spwjbm", param_sp.getSpwjbm());

        // 操作数据库
        handleMapper.get_spxx(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_sp.setList(pcList);

        return param_sp;
    }

    /*// 根据评查活动编号获取当前活动的审批意见，结论
    @Override
    public List<Map> get_spxx(Param_sp param_sp) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_spwjbm", param_sp.getSpwjbm());

        // 操作数据库
        handleMapper.get_spxx(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_sp.setList(pcList);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }*/

    // 承办人反馈列表
    @Override
    public Param_Pager get_cbrfklist(Param_Jbxx param_jbxx) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_bpc_dwbm", param_jbxx.getBpc_dwbm());
        map.put("p_bpc_gh", param_jbxx.getBpc_gh());
        map.put("p_keyword", param_jbxx.getKeyword());
        map.put("p_pagesize", param_jbxx.getRows());
        map.put("p_pageindex", param_jbxx.getPage());

        // 操作数据库
        handleMapper.get_cbrfklist(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_jbxx.setList(pcList);
        param_jbxx.setCount(p_count);

        return param_jbxx;
    }

    //承办部门反馈列表
    @Override
    public Param_Pager get_cbbmfklist(Param_Jbxx param_jbxx) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_bpc_dwbm", param_jbxx.getBpc_dwbm());
        map.put("p_bpc_gh", param_jbxx.getBpc_gh());
        map.put("p_keyword", param_jbxx.getKeyword());
        map.put("p_pagesize", param_jbxx.getRows());
        map.put("p_pageindex", param_jbxx.getPage());

        // 操作数据库
        handleMapper.get_cbbmfklist(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_jbxx.setList(pcList);
        param_jbxx.setCount(p_count);

        return param_jbxx;
    }

    // 文书卷宗列表
    @Override
    public List<Map> getDocFiles(Param_Pc param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_dwbm", param.getDwbm());
        map.put("p_gh", param.getGh());

        // 操作数据库
        handleMapper.getDocFiles(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 评查活动列表
    @Override
    public Param_Pager getPchdList(Param_HdList param_hd) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_cjrdwbm", param_hd.getCjrdwbm());
        map.put("p_cjrgh", param_hd.getP_cjrgh());
        map.put("p_keyword", param_hd.getKeyword());
        map.put("p_pagesize", param_hd.getRows());
        map.put("p_pageindex", param_hd.getPage());

        // 操作数据库
        handleMapper.getPchdList(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_hd.setList(pcList);
        param_hd.setCount(p_count);

        return param_hd;
    }

    // 评查案件确认
    @Override
    public List<Map> caseConfirm(Param_Send param_send) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param_send.getPcslbm());
        map.put("p_czrdwbm", param_send.getCzr_dwbm());
        map.put("p_czrdwmc", param_send.getCzr_dwmc());
        map.put("p_czrgh", param_send.getCzr_gh());
        map.put("p_czrxm", param_send.getCzr_mc());

        // 操作数据库
        handleMapper.uptDealAjqr(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 已办已评查已反馈 列表
    @Override
    public Param_Pager get_pcyblist(Param_Jbxx param_jbxx) throws Exception {

        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", param_jbxx.getPcdwbm());
        map.put("p_gh", param_jbxx.getPcr_gh());
        map.put("p_type", param_jbxx.getType());
        map.put("p_name", param_jbxx.getName());
        map.put("p_pagesize", param_jbxx.getRows());
        map.put("p_pageindex", param_jbxx.getPage());
        map.put("p_bng", OracleTimeUtils.format(param_jbxx.getBegin()));
        map.put("p_end",OracleTimeUtils.format(param_jbxx.getEnd()));


        // 操作数据库
        handleMapper.get_pcyblist(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");

        param_jbxx.setList(pcList);
        param_jbxx.setCount(p_count);

        return param_jbxx;
    }


    // 发送评查审批
    @Override
    public boolean sendApprove(Param_Send param_send) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param_send.getPcslbm());
        map.put("p_pcflbm", param_send.getPcflbm());
        map.put("p_pchdbm", param_send.getPchdbm());
        map.put("p_bmsah", param_send.getBmsah());
        map.put("p_ssrdwbm", param_send.getSsrdwbm());
        map.put("p_ssrdwmc", param_send.getSsrdwmc());
        map.put("p_ssrgh", param_send.getSsrgh());
        map.put("p_ssrxm", param_send.getSsrxm());
        map.put("p_sprdwbm", param_send.getSprdwbm());
        map.put("p_sprdwmc", param_send.getSprdwmc());
        map.put("p_sprgh", param_send.getSprgh());
        map.put("p_sprxm", param_send.getSprxm());
        map.put("p_spjsbm", param_send.getSpjsbm());
        map.put("p_spjsmc", param_send.getSpjsmc());
        map.put("p_pcspbm", param_send.getPcspbm());
        // 操作数据库
        handleMapper.uptSendPcsp(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 评查案件审批
    @Override
    public boolean dealApprove(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcspbm", param.getPcspbm());
        map.put("p_sprdwbm", param.getSprdwbm());
        map.put("p_sprdwmc", param.getSprdwmc());
        map.put("p_sprgh", param.getSprgh());
        map.put("p_sprxm", param.getSprxm());
        map.put("p_spjl", param.getSpjl());
        map.put("p_spyj", param.getSpyj());

        // 操作数据库
        handleMapper.uptDealPcsp(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        // 写入评查意见
        map.clear();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcyjlx", "1");
        map.put("p_pcyjjl", param.getSpjl());
        map.put("p_pcyj", param.getSpyj());
        map.put("p_tcrdwbm", param.getSprdwbm());
        map.put("p_tcrdwmc", param.getSprdwmc());
        map.put("p_tcrgh", param.getSprgh());
        map.put("p_tcrmc", param.getSprxm());

        // 操作数据库
        handleMapper.addPcyj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 发送评查反馈
    @Override
    public boolean sendFeedback(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_bmsah", param.getBmsah());
        map.put("p_czrdwbm", param.getCzr_dwbm());
        map.put("p_czrdwmc", param.getCzr_dwmc());
        map.put("p_czrgh", param.getCzr_gh());
        map.put("p_czrxm", param.getCzr_mc());

        // 操作数据库
        handleMapper.uptSendPcfk(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 承办人反馈
    @Override
    public boolean dealFeedbcak(Param_Send param) throws Exception {
        String errMsg = "";

        // 更改评查基本信息表状态
        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_bmsah", param.getBmsah());
        map.put("p_czrdwbm", param.getBpc_dwbm());
        map.put("p_czrdwmc", param.getBpc_dwmc());
        map.put("p_czrgh", param.getBpc_gh());
        map.put("p_czrxm", param.getBpc_mc());
        map.put("p_sfyy", param.getSfyy());
        map.put("p_sprdwbm", param.getSprdwbm());
        map.put("p_sprgh", param.getSprgh());

        // 操作数据库
        handleMapper.uptDealPcfk(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        // 写入评查意见
        map.clear();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcyjlx", "2");
        map.put("p_pcyjjl", param.getFkjl());
        map.put("p_pcyj", param.getFkyj());
        map.put("p_tcrdwbm", param.getBpc_dwbm());
        map.put("p_tcrdwmc", param.getBpc_dwmc());
        map.put("p_tcrgh", param.getBpc_gh());
        map.put("p_tcrmc", param.getBpc_mc());

        // 操作数据库
        handleMapper.addPcyj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 部门反馈
    @Override
    public boolean dealDeptFeedbcak(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_bmsah", param.getBmsah());
        map.put("p_czrdwbm", param.getCzr_dwbm());
        map.put("p_czrdwmc", param.getCzr_dwmc());
        map.put("p_czrgh", param.getCzr_gh());
        map.put("p_czrxm", param.getCzr_mc());

        // 操作数据库
        handleMapper.uptDealBmfk(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        // 写入评查意见
        map.clear();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcyjlx", "3");
        map.put("p_pcyjjl", param.getFkjl());
        map.put("p_pcyj", param.getFkyj());
        map.put("p_tcrdwbm", param.getCzr_dwbm());
        map.put("p_tcrdwmc", param.getCzr_dwmc());
        map.put("p_tcrgh", param.getCzr_gh());
        map.put("p_tcrmc", param.getCzr_mc());

        // 操作数据库
        handleMapper.addPcyj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 评查结束
    @Override
    public boolean finishEval(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_bmsah", param.getBmsah());
        map.put("p_czrdwbm", param.getCzr_dwbm());
        map.put("p_czrdwmc", param.getCzr_dwmc());
        map.put("p_czrgh", param.getCzr_gh());
        map.put("p_czrxm", param.getCzr_mc());

        // 操作数据库
        handleMapper.uptDealPcjs(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        //发送消息
        String sffs = param.getSffs();
        if ( StringUtils.isNotEmpty(sffs) && "Y".equalsIgnoreCase(sffs) ) {
            Message message = new Message();
            message.setXxlx("0");
            message.setGlbmsah(param.getPcslbm());
            message.setJsrdwbm(param.getBpc_dwbm());
            message.setJsrgh(param.getBpc_gh());
            message.setDwbm(param.getCzr_dwbm());

            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("message.cbr.bt");
            String nr = systemConfigService.getSystemConfigValue("message.cbr.nr");
            String replace = nr.replace("[ajmc]", jbxx.getAJMC());
            message.setXxbt(bt);
            message.setXxnr(replace);
            messageService.save(message);
        }

        return true;
    }

    // 新增评查意见信息
    @Override
    public boolean addPcyj(Param_Pcyj param_pcyj) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param_pcyj.getPcslbm());
        map.put("p_pcyjlx", param_pcyj.getPcyjlx());
        map.put("p_pcyjjl", param_pcyj.getPcyjjl());
        map.put("p_pcyj", param_pcyj.getPcyj());
        map.put("p_tcrdwbm", param_pcyj.getTcrdwbm());
        map.put("p_tcrdwmc", param_pcyj.getTcrdwmc());
        map.put("p_tcrgh", param_pcyj.getTcrgh());
        map.put("p_tcrmc", param_pcyj.getTcrmc());
        map.put("p_pcyjbh", param_pcyj.getPcyjbh());

        // 操作数据库
        handleMapper.addPcyj(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 撤回评查审批
    @Override
    public boolean cancelApprove(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_pcspbm", param.getPcspbm());
        map.put("p_czrdwbm", param.getCzr_dwbm());
        map.put("p_czrdwmc", param.getCzr_dwmc());
        map.put("p_czrgh", param.getCzr_gh());
        map.put("p_czrxm", param.getCzr_mc());

        // 操作数据库
        handleMapper.cancelApprove(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 退回评查送审
    @Override
    public boolean backApprove(Param_Send param) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcslbm", param.getPcslbm());
        map.put("p_pcflbm", param.getPcflbm());
        map.put("p_pchdbm", param.getPchdbm());
        map.put("p_pcspbm", param.getPcspbm());
        map.put("p_czrdwbm", param.getCzr_dwbm());
        map.put("p_czrdwmc", param.getCzr_dwmc());
        map.put("p_czrgh", param.getCzr_gh());
        map.put("p_czrxm", param.getCzr_mc());

        // 操作数据库
        handleMapper.backApprove(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return true;
    }

    // 获取业务部门负责人
    @Override
    public List<Map> getYwbmfzr(String dwbm, String gh, String spjsbm) throws Exception {
        String errMsg = "";

        // 操作数据库
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_gh", gh);
        map.put("p_spjsbm", spjsbm);
        handleMapper.getYwbmfzr(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }
}
