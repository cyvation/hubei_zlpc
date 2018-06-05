package com.start.boot.service.impl;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.FilterMapper;
import com.start.boot.dao.ajpc.Yx_Pc_PcxFlMapper;
import com.start.boot.dao.ajpc.Yx_Pc_PcxMapper;
import com.start.boot.domain.*;
import com.start.boot.service.FilterService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import com.start.boot.pojo.vo.PcxVoList;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/11/2.
 */
@Service
@Transactional
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterMapper filterMapper;

    @Autowired
    Yx_Pc_PcxMapper yx_pc_pcxMapper;

    @Autowired
    Yx_Pc_PcxFlMapper yx_pc_pcxFlMapper;

    // 评查筛选规则列表
    @Override
    public List<Map> getSxgz(String dwbm, String pcflbm, String sslb) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_dwbm",dwbm);//规则单位编码
        map.put("p_pcflbm",pcflbm);//评查分类编码
        map.put("p_sslb",sslb);

        filterMapper.getSxgz(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 评查筛选规则列表（监控里用）
    @Override
    public List<Map> getSxgzMonitor(String dwbm, String pcflbm) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_dwbm",dwbm);//规则单位编码
        map.put("p_pcflbm",pcflbm);//评查分类编码

        filterMapper.getSxgzMonitor(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 评查活动绑定的筛选规则列表
    @Override
    public List<Map> getHdsxgz(String gzdwbm, String pcflbm, String pchdbm) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_pcdwbm",gzdwbm);//规则单位编码
        map.put("p_pcflbm",pcflbm);//评查分类编码
        map.put("p_pchdbm",pchdbm);//评查活动编码

        filterMapper.getHdsxgz(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map, "p_cursor");
    }


    //随机评查案件筛选（自定义）
    @Override
    public ParamSx getSjsx(ParamSx paramSx) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_pcdwbm",paramSx.getPcdwbm());//评查单位编码
        map.put("p_pcflbm",paramSx.getPcflbm());//评查分类编码
        map.put("p_pchdbm",paramSx.getPchdbm());//评查活动编码
        map.put("p_gzdwbm",paramSx.getGzdwbm());//筛选规则所属单位编码
        map.put("p_sxgzbm",paramSx.getSxgzbm());//筛选规则编码
        map.put("p_cbdwbm",paramSx.getCbdwbm());//承办单位编码
        map.put("p_cbbmbm",paramSx.getCbbmbm());//承办部门编码
        map.put("p_ajlb",paramSx.getAjlb());//案件类别编码集合
        map.put("p_bmsah",paramSx.getBmsah());//部门受案号
        map.put("p_ajmc",paramSx.getAjmc());//案件名称
        map.put("p_cbrxm",paramSx.getCbrxm());//承办人姓名
        map.put("p_ay",paramSx.getAy());//案由

        map.put("p_slrqbng", OracleTimeUtils.format(paramSx.getSlrqbng()));//受理日期开始时间
        map.put("p_slrqend",OracleTimeUtils.format(paramSx.getSlrqend()));//受理日期结束时间
        map.put("p_bjrqbng",OracleTimeUtils.format(paramSx.getBjrqbng()));//办结日期开始时间
        map.put("p_bjrqend",OracleTimeUtils.format(paramSx.getBjrqend()));//办结日期结束时间
        map.put("p_wcrqbng",OracleTimeUtils.format(paramSx.getWcrqbng()));//完成日期开始时间
        map.put("p_wcrqend",OracleTimeUtils.format(paramSx.getWcrqend()));//完成日期结束时间
        map.put("p_gdrqbng",OracleTimeUtils.format(paramSx.getGdrqbng()));//归档日期开始时间
        map.put("p_gdrqend",OracleTimeUtils.format(paramSx.getGdrqend()));//归档日期结束时间
        map.put("p_gzrqbng",OracleTimeUtils.format(paramSx.getGzrqbng()));//筛选规则匹配日期开始时间
        map.put("p_gzrqend",OracleTimeUtils.format(paramSx.getGzrqend()));//筛选规则匹配日期结束时间

        map.put("p_zdycxtj",paramSx.getZdycxtj());//自定义查询条件
        map.put("p_pagesize",paramSx.getRows());//页大小
        map.put("p_pageindex",paramSx.getPage());//页索引

        filterMapper.getSjsx(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        Integer pCount = DataAccessHelper.getInteger(map, "p_count");
        List<Map> list = DataAccessHelper.getListMap(map, "p_cursor");

        paramSx.setList(list);
        paramSx.setCount(pCount);

        return paramSx;
    }


    @Override
    public ParamSx get_sjsx_bm(ParamSx paramSx) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_pcdwbm",paramSx.getPcdwbm());//评查单位编码
        map.put("p_pcflbm",paramSx.getPcflbm());//评查分类编码
        map.put("p_pchdbm",paramSx.getPchdbm());//评查活动编码
        map.put("p_sxgzbm",paramSx.getSxgzbm());//筛选规则编码
        map.put("p_cbdwbm",paramSx.getCbdwbm());//承办单位编码

        map.put("p_slrqbng", OracleTimeUtils.format(paramSx.getSlrqbng()));//受理日期开始时间
        map.put("p_slrqend",OracleTimeUtils.format(paramSx.getSlrqend()));//受理日期结束时间
        map.put("p_bjrqbng",OracleTimeUtils.format(paramSx.getBjrqbng()));//办结日期开始时间
        map.put("p_bjrqend",OracleTimeUtils.format(paramSx.getBjrqend()));//办结日期结束时间
        map.put("p_wcrqbng",OracleTimeUtils.format(paramSx.getWcrqbng()));//完成日期开始时间
        map.put("p_wcrqend",OracleTimeUtils.format(paramSx.getWcrqend()));//完成日期结束时间
        map.put("p_gdrqbng",OracleTimeUtils.format(paramSx.getGdrqbng()));//归档日期开始时间
        map.put("p_gdrqend",OracleTimeUtils.format(paramSx.getGdrqend()));//归档日期结束时间
        map.put("p_gzrqbng",OracleTimeUtils.format(paramSx.getGzrqbng()));//筛选规则匹配日期开始时间
        map.put("p_gzrqend",OracleTimeUtils.format(paramSx.getGzrqend()));//筛选规则匹配日期结束时间

        map.put("p_zdycxtj",paramSx.getZdycxtj());//自定义查询条件
        filterMapper.get_sjsx_bm(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }


        List<Map> listcols = (List<Map>) map.get("p_cursor_cols");
        List<Map> Listrows = (List<Map>) map.get("p_cursor_rows");
        List<Map> listdata = (List<Map>) map.get("p_cursor_data");

        paramSx.setListcols(listcols);
        paramSx.setListrows(Listrows);
        paramSx.setListdata(listdata);

        return paramSx;

    }

    @Override
    public ParamSx get_sjsx_jcg(ParamSx paramSx) throws Exception {
        String errMsg ="";

        Map map = new HashMap();
        map.put("p_pcdwbm",paramSx.getPcdwbm());//评查单位编码
        map.put("p_pcflbm",paramSx.getPcflbm());//评查分类编码
        map.put("p_pchdbm",paramSx.getPchdbm());//评查活动编码

        map.put("p_sxgzbm",paramSx.getSxgzbm());//筛选规则编码
        map.put("p_cbdwbm",paramSx.getCbdwbm());//承办单位编码
        map.put("p_cbbm",paramSx.getCbbmbm());//承办部门编码

        map.put("p_cbr",paramSx.getCbrxm());//承办检察官
        map.put("p_slrqbng", OracleTimeUtils.format(paramSx.getSlrqbng()));//受理日期开始时间
        map.put("p_slrqend",OracleTimeUtils.format(paramSx.getSlrqend()));//受理日期结束时间
        map.put("p_bjrqbng",OracleTimeUtils.format(paramSx.getBjrqbng()));//办结日期开始时间
        map.put("p_bjrqend",OracleTimeUtils.format(paramSx.getBjrqend()));//办结日期结束时间
        map.put("p_wcrqbng",OracleTimeUtils.format(paramSx.getWcrqbng()));//完成日期开始时间
        map.put("p_wcrqend",OracleTimeUtils.format(paramSx.getWcrqend()));//完成日期结束时间
        map.put("p_gdrqbng",OracleTimeUtils.format(paramSx.getGdrqbng()));//归档日期开始时间
        map.put("p_gdrqend",OracleTimeUtils.format(paramSx.getGdrqend()));//归档日期结束时间
        map.put("p_gzrqbng",OracleTimeUtils.format(paramSx.getGzrqbng()));//筛选规则匹配日期开始时间
        map.put("p_gzrqend",OracleTimeUtils.format(paramSx.getGzrqend()));//筛选规则匹配日期结束时间
        map.put("p_pagesize",paramSx.getRows());//页大小
        map.put("p_pageindex",paramSx.getPage());//页索引

        filterMapper.get_sjsx_jcg(map);

        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        List<Map> listcols = (List<Map>) map.get("p_cursor_cols");
        List<Map> Listrows = (List<Map>) map.get("p_cursor_rows");
        List<Map> listdata = (List<Map>) map.get("p_cursor_data");

        paramSx.setCount(DataAccessHelper.getInteger(map, "p_count"));
        paramSx.setListcols(listcols);
        paramSx.setListrows(Listrows);
        paramSx.setListdata(listdata);

        return paramSx;
    }

    // 评查监控
    @Override
    public Param_Pager getPcjk(Param_Pcjk pcjkParam) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",pcjkParam.getDwbm());//评查单位编码
        map.put("p_cbdwbm", pcjkParam.getCbdwbm()); //承办单位编码
        map.put("p_bmbm",pcjkParam.getBmbm()); // 部门编码
        map.put("p_gh",pcjkParam.getGh());//工号
        map.put("p_pcdwbm",pcjkParam.getPcdwbm());//评查单位编码
        map.put("p_pcflbm",pcjkParam.getPcflbm());//评查分类编码
        map.put("p_sxgzbm",pcjkParam.getSxgzbm());//筛选规则编码
        map.put("p_pcy",pcjkParam.getPcy());//评查员
        map.put("p_cbr",pcjkParam.getCbr());//承办人
        map.put("p_pcjl",pcjkParam.getPcjl());//评查结论
        map.put("p_pczt",pcjkParam.getPczt());//评查状态
        map.put("p_wcrqbng",OracleTimeUtils.format(pcjkParam.getWcrqbng()));//评查开始时间
        map.put("p_wcrqend",OracleTimeUtils.format(pcjkParam.getWcrqend()));//评查结束时间
        map.put("p_type",pcjkParam.getType());//类型
        map.put("p_ajmc", pcjkParam.getAjmc());//案件名称
        map.put("p_pagesize",pcjkParam.getRows());//页大小
        map.put("p_pageindex",pcjkParam.getPage());//页索引

        filterMapper.getPcjk(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        pcjkParam.setCount(DataAccessHelper.getInteger(map, "p_count"));
        pcjkParam.setList(DataAccessHelper.getListMap(map, "p_cursor"));

        return pcjkParam;
    }

    // 获取评查结论
    @Override
    public List<Map> getPcjl() throws Exception{
        String errMsg = "";

        Map map = new HashMap<>();
        filterMapper.getPcjl(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 获取评查状态
    @Override
    public List<Map> getPczt() throws Exception{
        String errMsg = "";

        Map map = new HashMap<>();
        filterMapper.getPczt(map);

        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        return DataAccessHelper.getListMap(map, "p_cursor");
    }

    // 评查案件筛选列（随机/重点）1.部门总表，未评查案件列表
    @Override
    public Param_Pager getBmwpcList(ParamSx param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",param.getCbdwbm());
        map.put("p_bmbm",param.getCbbmbm());
        map.put("p_gzbm",param.getSxgzbm());
        map.put("p_pcflbm",param.getPcflbm());
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_gzrqbng",OracleTimeUtils.format(param.getGzrqbng()));// 规则开始日期
        map.put("p_gzrqend",OracleTimeUtils.format(param.getGzrqend()));// 规则结束日期
        map.put("p_type",param.getType());
        map.put("p_pagesize",param.getRows());//页大小
        map.put("p_pageindex",param.getPage());//页索引

        filterMapper.getBmwpcList(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        param.setCount(DataAccessHelper.getInteger(map, "p_count"));
        param.setList(DataAccessHelper.getListMap(map, "p_cursor"));

        return param;
    }


    // 评查案件筛选列（随机/重点）检察官总表
    @Override
    public Param_Pager getCbrwpcList(ParamSx param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_dwbm",param.getCbdwbm());
        map.put("p_cbrgh",param.getCbrgh());
        map.put("p_gzbm",param.getSxgzbm());
        map.put("p_pcflbm",param.getPcflbm());
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_gzrqbng",OracleTimeUtils.format(param.getGzrqbng()));// 规则开始日期
        map.put("p_gzrqend",OracleTimeUtils.format(param.getGzrqend()));// 规则结束日期
        map.put("p_type",param.getType());// 案件是否评查
        map.put("p_pagesize",param.getRows());//页大小
        map.put("p_pageindex",param.getPage());//页索引

        filterMapper.getCbrwpcList(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        param.setCount(DataAccessHelper.getInteger(map, "p_count"));
        param.setList(DataAccessHelper.getListMap(map, "p_cursor"));

        return param;
    }

    // 随机评查
    @Override
    public Param_Ajsx uptSjpcsx(Param_Ajsx param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();

        map.put("p_pcflbm",param.getPcflbm());
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_bmsah",param.getBmsah());
        map.put("p_sxgzbm",param.getSxgzbm());
        map.put("p_pcmbbm",param.getPcmbbm());
        map.put("p_bpc_wcbzrq",OracleTimeUtils.format(param.getBpc_wcbzrq()));
        map.put("p_pcdwbm",param.getPcdwbm());
        map.put("p_dpc_dwbm",param.getBpc_dwbm());
        map.put("p_czr_dwbm",param.getCzr_dwbm());
        map.put("p_czr_dwmc",param.getCzr_dwmc());
        map.put("p_czr_gh",param.getCzr_gh());
        map.put("p_czr_mc",param.getCzr_mc());
        map.put("p_pcr_dwbm",param.getPcr_dwbm());
        map.put("p_pcr_dwmc",param.getPcr_dwmc());
        map.put("p_pcr_gh",param.getPcr_gh());
        map.put("p_pcr_mc",param.getPcr_mc());
        map.put("p_pcslbm",param.getPcslbm());
        map.put("p_pcsah",param.getPcsah());
        // 操作数据库
        filterMapper.uptSjpcsx(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        param.setPcslbm(DataAccessHelper.getString(map, "p_pcslbm"));
        param.setPcsah(DataAccessHelper.getString(map, "p_pcsah"));

        return param;

    }

    // 专项评查
    @Override
    public  Param_Ajsx uptZxpcsx(Param_Ajsx param) throws Exception{
        String errMsg = "";

        Map map = new HashMap();
        map.put("p_pcflbm",param.getPcflbm());
        map.put("p_pchdbm",param.getPchdbm());
        map.put("p_bmsah",param.getBmsah());
        map.put("p_sxgzbm",param.getSxgzbm());
        map.put("p_pcmbbm",param.getPcmbbm());
        map.put("p_bpc_wcbzrq",OracleTimeUtils.format(param.getBpc_wcbzrq()));
        map.put("p_pcdwbm",param.getPcdwbm());
        map.put("p_dpc_dwbm",param.getBpc_dwbm());
        map.put("p_czr_dwbm",param.getCzr_dwbm());
        map.put("p_czr_dwmc",param.getCzr_dwmc());
        map.put("p_czr_gh",param.getCzr_gh());
        map.put("p_czr_mc",param.getCzr_mc());
        map.put("p_pcslbm",param.getPcslbm());
        map.put("p_pcsah",param.getPcsah());

        // 操作数据库
        filterMapper.uptZxpcsx(map);
        errMsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        param.setPcslbm(DataAccessHelper.getString(map, "p_pcslbm"));
        param.setPcsah(DataAccessHelper.getString(map, "p_pcsah"));

        return param;
    }
    @Override
    public PcxVoList getYxPcFl(String pcslbm, String pcxflfbm) {

        //查爸爸
        Yx_Pc_PcxFlExample example = new Yx_Pc_PcxFlExample();
        Yx_Pc_PcxFlExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(pcslbm)){
            criteria.andPcslbmEqualTo(pcslbm).andPcxflfbmIsNull();
        }
        PcxVoList vo=new PcxVoList();
        example.setOrderByClause("XH ASC");
        List<Yx_Pc_PcxFl> parent = yx_pc_pcxFlMapper.selectByExample(example);

        if (!parent.isEmpty()){
            parent.stream().forEach(data->{
                Yx_Pc_PcxFlVo yx_pc_pcxFlVo = new Yx_Pc_PcxFlVo();
                BeanUtils.copyProperties(data,yx_pc_pcxFlVo);

                //爸爸的flbm就是儿子的flfbm
                String pcxflbm = data.getPcxflbm();
                //查儿子
                Yx_Pc_PcxFlExample childExample = new Yx_Pc_PcxFlExample();
                Yx_Pc_PcxFlExample.Criteria childExampleCriteria = childExample.createCriteria();

                if (!StringUtils.isEmpty(pcxflbm)){
                    childExampleCriteria.andPcslbmEqualTo(pcslbm).andPcxflfbmEqualTo(pcxflbm);
                }
                childExample.setOrderByClause("XH ASC");
                List<Yx_Pc_PcxFl> yx_pc_pcxFls = yx_pc_pcxFlMapper.selectByExample(childExample);

                //查儿子的评查项
                if (!yx_pc_pcxFls.isEmpty()){
                    yx_pc_pcxFls.stream().forEach(childData->{
                                Yx_Pc_PcxFlVo yx_pc_pcxFlVo1 = new Yx_Pc_PcxFlVo();
                                BeanUtils.copyProperties(childData,yx_pc_pcxFlVo1);
                                String childPcxflbm = childData.getPcxflbm();
                                if (!StringUtils.isEmpty(childPcxflbm)){
                                    Yx_Pc_PcxExample pcxExample=new Yx_Pc_PcxExample();
                                    pcxExample.createCriteria().andPcslbmEqualTo(pcslbm).andPcxflbmEqualTo(childPcxflbm);
                                    pcxExample.setOrderByClause("XH ASC");
                                    List<Yx_Pc_Pcx> pcxList = yx_pc_pcxMapper.selectByExample(pcxExample);
                                    yx_pc_pcxFlVo1.setPcxList(pcxList);
                                }
                                yx_pc_pcxFlVo.getChildren().add(yx_pc_pcxFlVo1);
                            }
                    );
                }
                vo.getPcxFlVos().add(yx_pc_pcxFlVo);
            });
        }
        return vo;
    }

    @Override
    public List<Map> getBmList(String dwbm, String pcflbm) throws Exception {
        Map map = new HashMap();
        map.put("p_dwbm", dwbm);
        map.put("p_pcflbm",pcflbm);

        filterMapper.getBmList(map);
        String p_errmsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNotBlank(p_errmsg)) {
            throw new Exception(p_errmsg);
        }
        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    @Override
    public List<Map> getAllBm(String currentDwbm) throws Exception {
        Map map = new HashMap();
        map.put("p_dwbm",currentDwbm);

        filterMapper.getAllBm(map);
        String errmsg =  DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNotEmpty(errmsg)){
            throw new Exception(errmsg);
        }

        return DataAccessHelper.getListMap(map,"p_cursor");
    }

    @Override
    public List<Map> getPcbmj(ParamSx paramSx) throws Exception {

        Map map = new HashMap();
        map.put("p_pcflbm",paramSx.getPcflbm());
        map.put("p_gzbm",paramSx.getSxgzbm());

        filterMapper.getPcbmj(map);

        String errmsg = DataAccessHelper.getString(map, "p_errmsg");
        if (StringUtils.isNotEmpty(errmsg)) {
            throw new Exception(errmsg);
        }

        List<Map> list= DataAccessHelper.getListMap(map,"p_cursor");

        return list;
    }

    @Override
    public  Map getAjxxByBmsah(Map param) throws  Exception{
        try{
            return filterMapper.getAjxxByBmsah(param);
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    public  Map getTyywAjxxByBmsah(Map param) throws  Exception{
        try{
            return filterMapper.getTyywAjxxByBmsah(param);
        }catch (Exception e){
            throw  e;
        }
    }

    //随机评查案件筛选（进阶版）
    @Override
    public ParamSx getSjsxAdvance(ParamSx paramSx) throws Exception {

        String errMsg ="";

        Map map = new HashMap();
        map.put("p_pcflbm",paramSx.getPcflbm());//评查分类编码
        map.put("p_pcmbbm",paramSx.getPchdbm());//评查模板编码
        map.put("p_sxgzbm",paramSx.getSxgzbm());//筛选规则编码
        map.put("p_cbdwbm",paramSx.getCbdwbm());//承办单位编码
        map.put("p_cbbmbm",paramSx.getCbbmbm());//承办部门编码
        map.put("p_bmsah",paramSx.getBmsah());//部门受案号
        map.put("p_ajmc",paramSx.getAjmc());//案件名称
        map.put("p_cbrxm",paramSx.getCbrxm());//承办人姓名

        map.put("p_wcrqbng",OracleTimeUtils.format(paramSx.getWcrqbng()));//完成日期开始时间
        map.put("p_wcrqend",OracleTimeUtils.format(paramSx.getWcrqend()));//完成日期结束时间

        map.put("p_zdycxtj",paramSx.getZdycxtj());//自定义查询条件
        map.put("p_pagesize",paramSx.getRows());//页大小
        map.put("p_pageindex",paramSx.getPage());//页索引

        filterMapper.getSjsxAdvance(map);
        errMsg = DataAccessHelper.getString(map,"p_errmsg");
        if (StringUtils.isNoneEmpty(errMsg)) {
            throw new Exception(errMsg);
        }

        Integer pCount = DataAccessHelper.getInteger(map, "p_count");
        List<Map> list = DataAccessHelper.getListMap(map, "p_cursor");

        paramSx.setList(list);
        paramSx.setCount(pCount);

        return paramSx;
    }

    @Override
    public List<Map> getSxgzByPcflbmAndYwtx(Map param){
        try {
            return filterMapper.getSxgzByPcflbmAndYwtx(param);
        }catch (Exception e){
            throw e;
        }
    }
}
