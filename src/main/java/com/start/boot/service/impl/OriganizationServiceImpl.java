package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.OriganizationMapper;
import com.start.boot.domain.*;
import com.start.boot.service.OriganizationService;
import com.start.boot.support.utils.DataAccessHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OriganizationServiceImpl implements OriganizationService {
    @Autowired
    private OriganizationMapper origanizationMapper;

    @Override
    public List<Map> getDwbm(String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);

        // 操作数据库
        origanizationMapper.getDwbm(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }
        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    /**
     *  获取单位树:湖北线下评查跨院
     * 获取本单位平级及下级单位
     * @param dwbm
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> getPjDwbm(String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);

        // 操作数据库
        List<Map> list=  origanizationMapper.getPjDwbm(map);

        return list;
    }

    @Override
    public List<Map> getDwBmJsInfoByDwBm(String dwbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);

        // 操作数据库
        origanizationMapper.getDwBmJsInfoByDwBm(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }
        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    @Override
    public boolean addBmInfo(Bmbm param) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param.getDwbm());
        map.put("p_bmbm", param.getBmbm());
        map.put("p_bmmc", param.getBmmc());
        map.put("p_bmjc", param.getBmjc());
        map.put("p_bmxh", param.getBmxh());
        map.put("p_bz", param.getBz());
        map.put("p_fbmbm", param.getFbmbm());

        // 操作数据库
        origanizationMapper.addBmInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public List<Map> getBmInfo(String dwbm, String bmbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_bmbm", bmbm);
        origanizationMapper.getBmInfo(map);
        // 操作数据库
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");
        return pcList;
    }

    @Override
    public boolean deleteBmInfo(String dwbm, String bmbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_bmbm", bmbm);
        // 操作数据库
        origanizationMapper.deleteBmInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public boolean addJsInfo(Jsbm jsbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", jsbm.getDwbm());
        map.put("p_bmbm", jsbm.getBmbm());
        map.put("p_jsbm", jsbm.getJsbm());
        map.put("p_jsmc", jsbm.getJsmc());
        map.put("p_jsxh", jsbm.getJsxh());
        map.put("p_jsbm", jsbm.getJsbm());

        // 操作数据库
        origanizationMapper.addJsInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public Param_rybm getRyInfo(Param_rybm param_rybm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param_rybm.getDwbm());
        map.put("p_bmbm", param_rybm.getBmbm());
        map.put("p_jsbm", param_rybm.getJsbm());
        map.put("p_gh", param_rybm.getGh());
        map.put("p_gzzh", param_rybm.getGzzh());
        map.put("p_xm", param_rybm.getXm());
        map.put("p_pagesize", param_rybm.getRows());
        map.put("p_pageindex", param_rybm.getPage());

        // 操作数据库
        origanizationMapper.getRyInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");
        param_rybm.setList(pcList);
        param_rybm.setCount(p_count);
        return param_rybm;
    }

    @Override
    public List<Map> getJsxh(Jsbm jsbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", jsbm.getDwbm());
        map.put("p_bmbm", jsbm.getBmbm());
        map.put("p_jsbm", jsbm.getJsbm());

        // 操作数据库
        origanizationMapper.getJsxh(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");
        return pcList;
    }

    @Override
    public boolean deleteJsInfo(Jsbm jsbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", jsbm.getDwbm());
        map.put("p_bmbm", jsbm.getBmbm());
        map.put("p_jsbm", jsbm.getJsbm());

        // 操作数据库
        origanizationMapper.deleteJsInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public boolean addJsgnfp(Jsgnfp jsgnfp) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", jsgnfp.getDwbm());
        map.put("p_bmbm", jsgnfp.getBmbm());
        map.put("p_jsbm", jsgnfp.getJsbm());
        map.put("p_gnbmj", jsgnfp.getGnbm());

        // 操作数据库
        origanizationMapper.addJsgnfp(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public List<Map> getJsGnqx(String bmbm, String jsbm, String dwbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_bmbm", bmbm);
        map.put("p_jsbm", jsbm);

        // 操作数据库
        origanizationMapper.getJsGnqx(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    @Override
    public boolean addDwGnQx(String dwbm, String gnbmj) throws Exception {
        String errMsg = "";

        //移除单位权限：
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);

        origanizationMapper.removeDwGnQx(map);

        //添加单位权限
        Map map2 = new HashMap();
        map2.put("p_dwbm", dwbm);

        String[] gnbms = gnbmj.split(",");

        for (int i = 0; i < gnbms.length; i++) {
            if (StringUtils.isNotEmpty(gnbms[i])){
                map2.put("p_gnbm",gnbms[i]);
                origanizationMapper.addDwGnQx(map2);
            }
        }

        errMsg = DataAccessHelper.getString(map2, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public Param_rybm getWfpRyInfo(Param_rybm param_rybm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param_rybm.getDwbm());
        map.put("p_bmbm", param_rybm.getBmbm());
        map.put("p_jsbm", param_rybm.getJsbm());
        map.put("p_gh", param_rybm.getGh());
        map.put("p_gzzh", param_rybm.getGzzh());
        map.put("p_xm", param_rybm.getXm());
        map.put("p_pagesize", param_rybm.getRows());
        map.put("p_pageindex", param_rybm.getPage());

        // 操作数据库
        origanizationMapper.getWfpRyInfo(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);

        //获取分页数据
        Integer p_count = DataAccessHelper.getInteger(map, "p_count");
        List<Map> pcList = DataAccessHelper.getListMap(map, "p_cursor");
        param_rybm.setList(pcList);
        param_rybm.setCount(p_count);
        return param_rybm;
    }

    @Override
    public List<Map> getAllGnqx(String uerDwbm, String dwbm, String djdwbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", uerDwbm);
        map.put("p_xzdwbm", dwbm);
        map.put("p_djdwbm", djdwbm);

        // 操作数据库
        origanizationMapper.getAllGN(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    @Override
    public boolean addRYJSFP(Param_rybm param) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param.getDwbm());
        map.put("p_bmbm", param.getBmbm());
        map.put("p_jsbm", param.getJsbm());
        map.put("p_gh", param.getGh());
        map.put("p_ydwbm", param.getYdwbm());
        map.put("p_ydwmc", param.getYdwmc());
        map.put("p_ybmbm", param.getYbmbm());
        map.put("p_ybmmc", param.getYbmmc());
        map.put("p_yjsbm", param.getYjsbm());
        map.put("p_yjsmc", param.getYjsmc());

        // 操作数据库
        origanizationMapper.addRYJSFP(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public boolean transJob(Param_TransJob param_transJob) throws Exception {
        String errMsg = "";

       //调岗.移除
        param_transJob.setBMBM(param_transJob.getBMBM_Old());
        param_transJob.setJSBM(param_transJob.getJSBM_Old());
        removeJob(param_transJob);

        //调岗.添加  //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param_transJob.getDWBM());
        map.put("p_bmbm", param_transJob.getBMBM());
        map.put("p_jsbm", param_transJob.getJSBM());
        map.put("p_gh", param_transJob.getRYGH());

        // 操作数据库
        origanizationMapper.transJob(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public List<Map> getJSInfoByDwBm(String bmbm, String dwbm) throws Exception {
        String errMsg = "";

        //封装map，调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_bmbm", bmbm);

        // 操作数据库
        origanizationMapper.getJSInfoByDwBm(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    @Override
    public List<Map> getBMBM(String dwbm) throws Exception {
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm", dwbm);

        // 操作数据库
        origanizationMapper.getBMBM(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    @Override
    public  boolean removeJob(Param_TransJob param_transJob) throws Exception{
        String errMsg = "";

        //封装map调用存储过程
        Map map = new HashMap();
        map.put("p_dwbm", param_transJob.getDWBM());
        map.put("p_bmbm", param_transJob.getBMBM());
        map.put("p_jsbm", param_transJob.getJSBM());
        map.put("p_gh", param_transJob.getRYGH());

        // 操作数据库
        origanizationMapper.removeJob(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg))
            throw new Exception(errMsg);
        return true;
    }

    @Override
    public List<Map> getGncs(Jsgnfp jsgnfp) throws Exception {

        // 操作数据库
        List<Map> list =origanizationMapper.getGncs(jsgnfp);

        return list;
    }

    @Override
    public int updateGncs(Jsgnfp jsgnfp) throws Exception {

        // 操作数据库
        int i = origanizationMapper.updateGncs(jsgnfp);

        return i;
    }
}
