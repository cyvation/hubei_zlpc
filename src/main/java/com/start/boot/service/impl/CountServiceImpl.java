package com.start.boot.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.dao.ajpc.CountMapper;
import com.start.boot.dao.ajpc.QueryMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.dto.*;
import com.start.boot.pojo.vo.CountVo;
import com.start.boot.pojo.vo.PcjbGkVo;
import com.start.boot.pojo.vo.PcxVo;
import com.start.boot.query.Query;
import com.start.boot.query.ShPcjbqkerQuery;
import com.start.boot.query.ShYPNAQuery;
import com.start.boot.query.YearProblemAnalyzeQuery;
import com.start.boot.service.CountService;
import com.start.boot.service.XtDmFldmService;
import com.start.boot.service.XtPcLbService;
import com.start.boot.service.XtZzjgDwbmService;
import com.start.boot.utils.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    QueryMapper queryMapper;

    @Autowired
    XtDmFldmService xtDmFldmService;

    @Autowired
    QueryUtils queryUtils;


    @Autowired
    YX_PC_JBXXMapper jbxxMapper;

    @Autowired
    XtZzjgDwbmService xtZzjgDwbmService;


    @Autowired
    XtPcLbService xtPcLbService;

    @Autowired
    CountMapper countMapper;

    @Override
    public Map<Integer, List<CountDo>> getPcByYear(Integer year, String pcflbm) {
        List<CountDo> query = queryMapper.query(year,pcflbm);
        if (query.isEmpty()){
            return null;
        }
        Map<Integer, List<CountDo>> collect = query.stream()
                .collect(Collectors.groupingBy(CountDo::getMonth, Collectors.toList()));
        List<MonthDo> monthDos = queryMapper.querySum(year,pcflbm);
        Set<Map.Entry<Integer, List<CountDo>>> entries = collect.entrySet();
        if (entries!=null){
            entries.stream().forEach((Map.Entry<Integer, List<CountDo>> data) ->{
                List<CountDo> value = data.getValue();
                if(value!=null){
                    value.stream().forEach(month->{
                        monthDos.stream().forEach(sum->{
                            if (month.getMonth()==sum.getMonth()){
                                month.setPercent(deciMal(month.getCount(),sum.getSum()));
                            }
                        });

                    });
                }

            });
        }

       return collect;
    }

    private double deciMal(int top, int below) {
        double result = new BigDecimal((float)top / below).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result*100;
    }


    @Override
    public List<CountVo> getYearPercentByYearAndPcflbm(Integer year, String pcflbm) {
        Integer sum = queryMapper.getYearCountByYearAndPcflbm(year, pcflbm);
        List<CountDo> yearPcjgCount = queryMapper.getYearPcjgCount(year, pcflbm);
        if (!yearPcjgCount.isEmpty()){
            ArrayList<CountVo> result = new ArrayList<>();
            yearPcjgCount.stream().forEach(data->{
                CountVo countVo = new CountVo();
                data.setPercent(deciMal(data.getCount(),sum));
                countVo.setPcjg(data.getPcjg()==null?"其它":data.getPcjg());
                countVo.setValue(data);
                result.add(countVo);
            });
            return result;
        }
        return null;
    }

    @Override
    public List<PcxVo> getPcxFlByYearAndMbbmAndPcjg(String pcflbm, Integer year) throws Exception {
        ArrayList<PcxVo> VoResult = new ArrayList<>();
        Query query = new Query();
        query.setClazz(PcMbDto.class)
                .setTableName("xt_pc_mb").createCriteria()
                .andEqualTo("PCFLBM",pcflbm)
                .andEqualTo("SFQY","Y")
                ;
        List<PcMbDto> javaBean = queryUtils.getJavaBean(query.build());
        if (javaBean.isEmpty()){
            return null;
        }
        String pcmbbm = javaBean.get(0).getPcmbbm();

        Query queryPcflbm = new Query();
        queryPcflbm.setTableName("xt_pc_pcxfl")
                    .createCriteria()
                    .andEqualTo("PCMBBM",pcmbbm)
                    .andIsNotNull("PCXFLFBM");


        List<Map> result = queryUtils.getMap(queryPcflbm);

        HashMap<String, ArrayList<String>> temp = new HashMap<>();

        result.stream().forEach(data->{
            String pcxflmc = (String) data.get("PCXFLMC");
            ArrayList<String> value = temp.getOrDefault(pcxflmc, null);
            String pcxflbm = (String) data.get("PCXFLBM");
            if (value==null){
                ArrayList<String> strings = new ArrayList<>();
                strings.add(pcxflbm);
                temp.put(pcxflmc,strings);
            }else {
                value.add(pcxflbm);
            }
        });

        Set<Map.Entry<String, ArrayList<String>>> entries = temp.entrySet();
        if (entries.isEmpty()){
            return null;
        }
        //分月数据处理
        HashMap<Integer,List<String>>pcslbmMap=new HashMap<>();
        //根据年份查评查受理编码list
        List<JbxxDto> pcslbmListByYearAndMonth = queryMapper.getPcslbmListByYearAndMonth(year);
        if (!pcslbmListByYearAndMonth.isEmpty()){
            pcslbmListByYearAndMonth.stream().forEach(data->{
                Integer month = data.getMonth();
                List<String> pcslbmList = pcslbmMap.getOrDefault(month, null);
                String pcxflbm =  data.getPcslbm();
                if (pcslbmList==null){
                    ArrayList<String> pcslbmListTemp = new ArrayList<>();
                    pcslbmListTemp.add(pcxflbm);
                    pcslbmMap.put(month,pcslbmListTemp);
                }else {
                    pcslbmList.add(pcxflbm);
                }
            });
        }
        Set<Map.Entry<Integer, List<String>>> pcslbmEntry = pcslbmMap.entrySet();
        entries.stream().forEach(data->{
            String key = data.getKey();
            ArrayList<String> pcxbmList = data.getValue();
            if (!data.getValue().isEmpty()){
                pcslbmEntry.forEach(pcslbmList->{
                    Query pcxDtoQuery = new Query();
                    LinkedList<String> groupByList = new LinkedList<>();
                    groupByList.add("PCXMC");
                    HashMap<String, String> displayName = new HashMap<>();
                    displayName.put("COUNT(*)","count");
                    displayName.put("PCXMC",null);
                    pcxDtoQuery.setTableName("YX_PC_PCX")
                            .setClazz(PcxDto.class)
                            .setDisplayColumnName(displayName)
                            .setGroupByClause(groupByList)
                            .setOrderByClause("1 DESC")
                            .createCriteria()
                            .andin("PCXBM",pcxbmList)
                            .andin("PCSLBM",pcslbmList.getValue())
                            .andNotEqualTo("PCJG","0")
                    ;
                    try {//只返回前5个
                        List<PcxDto> dtoList = queryUtils.getJavaBean(pcxDtoQuery);
                        PcxVo pcxVo = new PcxVo();
                        pcxVo.setName(key);
                        pcxVo.setMonth(pcslbmList.getKey());
                        int n=5;
                        if (dtoList!=null){
                            if (dtoList.size()<5){
                                n=dtoList.size();
                            }
                            pcxVo.setResult(dtoList.subList(0,n));
                            VoResult.add(pcxVo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });

            }
        });
        return VoResult;
    }

    @Override
    public List<PcxVo> getPcxFlByYearAndBinTu(String pcflbm, Integer year,String pcjg) throws Exception {
        ArrayList<PcxVo> VoResult = new ArrayList<>();
        Query query = new Query();
        query.setClazz(PcMbDto.class)
                .setTableName("xt_pc_mb").createCriteria()
                .andEqualTo("PCFLBM",pcflbm)
                .andEqualTo("SFQY","Y")
        ;
        List<PcMbDto> javaBean = queryUtils.getJavaBean(query.build());
        if (javaBean.isEmpty()){
            return null;
        }
        String pcmbbm = javaBean.get(0).getPcmbbm();

        Query queryPcflbm = new Query();
        queryPcflbm.setTableName("xt_pc_pcxfl")
                .createCriteria()
                .andEqualTo("PCMBBM",pcmbbm)
                .andlike("PCXFLMC",pcjg)
                .andIsNotNull("PCXFLFBM");


        List<Map> result = queryUtils.getMap(queryPcflbm);

        HashMap<String, ArrayList<String>> temp = new HashMap<>();

        result.stream().forEach(data->{
            String pcxflmc = (String) data.get("PCXFLMC");
            ArrayList<String> value = temp.getOrDefault(pcxflmc, null);
            String pcxflbm = (String) data.get("PCXFLBM");
            if (value==null){
                ArrayList<String> strings = new ArrayList<>();
                strings.add(pcxflbm);
                temp.put(pcxflmc,strings);
            }else {
                value.add(pcxflbm);
            }
        });

        Set<Map.Entry<String, ArrayList<String>>> entries = temp.entrySet();
        if (entries.isEmpty()){
            return null;
        }
        entries.stream().forEach(data->{
            ArrayList<String> pcxbmList = data.getValue();
            //根据年份查评查受理编码list
            List<String> pcslbmList = queryMapper.getPcslbmListByYear(year);

            Query pcxDtoQuery = new Query();
            LinkedList<String> groupByList = new LinkedList<>();
            groupByList.add("PCXMC");
            HashMap<String, String> displayName = new HashMap<>();
            displayName.put("COUNT(*)","count");
            displayName.put("PCXMC",null);
            pcxDtoQuery.setTableName("YX_PC_PCX")
                    .setClazz(PcxDto.class)
                    .setDisplayColumnName(displayName)
                    .setGroupByClause(groupByList)
                    .setOrderByClause("1 DESC")
                    .createCriteria()
                    .andin("PCXBM",pcxbmList)
                    .andin("PCSLBM",pcslbmList)
                    .andNotEqualTo("PCJG","0");
            try {
                List<PcxDto> dtoList = queryUtils.getJavaBean(pcxDtoQuery);
                PcxVo pcxVo = new PcxVo();
                pcxVo.setName(data.getKey());
                pcxVo.setResult(dtoList);
                VoResult.add(pcxVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return VoResult;
    }


    @Override
    public List<ChartWithGroup> getAjzlLinesByYearAndPcflbm(Integer year,String pcflbm){
        List<ChartWithGroup> linesByYearAndPcflbm = queryMapper.getAjzlLinesByYearAndPcflbm(year,pcflbm);
        return linesByYearAndPcflbm;
    }

    @Override
    public List<ChartNOGroup> getAjzlPieByYearAndPcflbm(Integer year, String pcflbm) throws Exception {
        List<ChartNOGroup> pieByYearAndPcflbm = queryMapper.getAjzlPieByYearAndPcflbm(year, pcflbm);
        return pieByYearAndPcflbm;
    }

    @Override
    public List<ShPcjbqkerMothDto> shPcjbqkerGetYearMonthPcjlIsNull(ShPcjbqkerQuery query) {
        return queryMapper.shPcjbqkerGetYearMonthPcjlIsNull(query);
    }

    @Override
    public List<ShPcjbqkerMothDto> shPcjbqkerGetYearMonthPcjlIsNotNull(ShPcjbqkerQuery query) {
        return queryMapper.shPcjbqkerGetYearMonthPcjlIsNotNull(query);
    }

    @Override
    public List<ShPcjbqkerDto > shPcjbqkerGetPcslbmPcjlIsNotNull(ShPcjbqkerQuery query) {
        return queryMapper.shPcjbqkerGetPcslbmPcjlIsNotNull(query);
    }

    @Override
    public List<ShPcjbqkerDto > shPcjbqkerGetPcslbmPcjlIsNull(ShPcjbqkerQuery query) {
        return queryMapper.shPcjbqkerGetPcslbmPcjlIsNull(query);
    }

    @Override
    public List<PcxDto> shYearProlbemNatureAnalyze(ShYPNAQuery query) {
        PageHelper.startPage(1, 5);
        List<PcxDto> pcxDtos = queryMapper.shYearProlbemNatureAnalyze(query);
        PageInfo page = new PageInfo(pcxDtos);
        return page.getList();
    }

    @Override
    public List<YX_PC_JBXX> getAjjbxxYearProlbemNatureAnalyze(ShYPNAQuery query) {
       return queryMapper.getAjjbxxYearProlbemNatureAnalyze(query);
    }

    @Override
    public List<YX_PC_JBXX> shGetYearProblemAnalyzeJbxx(YearProblemAnalyzeQuery query) {
        return queryMapper.shGetYearProblemAnalyzeJbxx(query);
    }

    @Override
    public List<YX_PC_JBXX> shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(ShPcjbqkerQuery query) {
        return  queryMapper.shPcjbqkerGetPcslbmPcjlIsNotNullJbxx(query);

    }

    @Override
    public List<YX_PC_JBXX> shPcjbqkerGetPcslbmPcjlIsNotNullSxjl(ShPcjbqkerQuery query) {
        return   queryMapper.shPcjbqkerGetPcslbmPcjlIsNotNullSxjl(query);
    }

    @Override
    public List shGetMapJbqk(ShYPNAQuery query) {
        ArrayList<Object> objects = new ArrayList<>();
        List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy("9102");
        if (CollectionUtils.isEmpty(xtDmFldmBy)){
            return null;
        }
        if (StringUtils.isEmpty(query.getDwbm())){
            return null;
        }
        List<XtZzjgDwbm> dwbmTreeList = xtZzjgDwbmService.getDwbmTreeList(query.getDwbm());
        xtDmFldmBy.stream().forEach(lb->{
            ArrayList<MapDto> mapDtos = new ArrayList<>();
            dwbmTreeList.stream().forEach(dw->{
                MapDto mapDto = new MapDto();
                query.setDwbm(dw.getDwbm());
                query.setPcjl(lb.getMc());
                Integer mapVaule = queryMapper.shGetMapJbqk(query);
                mapDto.setName(dw.getDwjc());
                mapDto.setDwbm(dw.getDwbm());
                mapDto.setValue(mapVaule);
                mapDtos.add(mapDto);
            });
           HashMap<String, Object> temp = new HashMap<>();
           temp.put("name",lb.getMc());
           temp.put("data",mapDtos);
                    objects.add(temp);
        }
        );
        return objects;
    }

    @Override
    public List shGetMapAjZlqk(ShYPNAQuery query) {
        ArrayList<Object> objects = new ArrayList<>();
        List<XtPcLb> allXtPcLb = xtPcLbService.getAllXtPcLb();
        if (CollectionUtils.isEmpty(allXtPcLb)){
            return null;
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        if (StringUtils.isEmpty(query.getDwbm())){
            return null;
        }
        List<XtZzjgDwbm> dwbmTreeList = xtZzjgDwbmService.getDwbmTreeList(query.getDwbm());
        allXtPcLb.stream().forEach(lb->{
                   // int tempWpc=0;
                    //int tempYpc=0;
                    ArrayList<MapDto> mapDtos = new ArrayList<>();
                    for (XtZzjgDwbm dw : dwbmTreeList) {
                        MapDto mapDto = new MapDto();
                        query.setDwbm(dw.getDwbm());
                        query.setPcflbm(lb.getPcflbm());
                        Integer mapVaule = queryMapper.getMapVaulePcflbm(query);
                        mapDto.setName((dw.getDwjc()==null?dw.getDwmc():dw.getDwjc()));
                        mapDto.setValue(mapVaule);
                        mapDto.setDwbm(dw.getDwbm());
                        mapDto.setPcflbm(lb.getPcflbm());
                        mapDto.setPclbmc(lb.getPcflmc());
                        query.setDwbm(dw.getDwbm());
                        Integer wpc = queryMapper.getWpc(query);
                        Integer ypc = queryMapper.getYpc(query);
                        mapDto.setWpc(wpc);
                        mapDto.setYpc(ypc);
                        mapDtos.add(mapDto);
                    }
                    //String format = decimalFormat.format((float)tempYpc / (tempWpc + tempYpc));
                    HashMap<String, Object> temp = new HashMap<>();
                    //temp.put("pcl",format);
                    temp.put("name",lb.getPcflmc());
                    temp.put("data",mapDtos);
                    objects.add(temp);
                }
        );
        return objects;
    }

    @Override
    public List<PersonPaimin> getPersonPaiMinByPcjlAndRQ(ShYPNAQuery query) {
        return queryMapper.getPersonPaiMinByPcjlAndRQ(query);
    }

    @Override
    public List<YX_PC_JBXX> getPersonPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query) {
        return queryMapper.getPersonPaiMinByPcjlAndRQAjJbxx(query);
    }

    @Override
    public List<BmZlPm> bmBanAjZhiLiangPaiMin(ShYPNAQuery query) {
        return queryMapper.bmBanAjZhiLiangPaiMin(query);
    }

    @Override
    public List<YX_PC_JBXX> bmBanAjZhiLiangPaiMinAjJbxx(ShYPNAQuery query) {
        return queryMapper.bmBanAjZhiLiangPaiMinAjJbxx(query);
    }

    @Override
    public List<PmDto> getPm(ShYPNAQuery query) {
        return queryMapper.getPm(query);
    }

    @Override
    public List<PcjbGkVo>  getPcjbGk(ShYPNAQuery query) {
        List<PcjbGkVo> result=new ArrayList<>();
        String dwbm = query.getDwbm();
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        List<XtZzjgDwbm> dwbmTreeList = xtZzjgDwbmService.getDwbmTreeList(dwbm);
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        dwbmTreeList.stream().forEach(t->{
            query.setDwbm(t.getDwbm());
            int wpc = queryMapper.getWpc(query);
            int ypc = queryMapper.getYpc(query);
            PcjbGkVo temp = new PcjbGkVo();
            temp.setPc(ypc);
            temp.setSj(wpc+ypc);
            String format = decimalFormat.format((float)ypc / (wpc + ypc));
            temp.setPcl(format);
            temp.setDwbm(t.getDwbm());
            temp.setDwmc(t.getDwmc());
            result.add(temp);
        });
        return result;
    }

    @Override
    public List<YX_PC_JBXX> getPmAjJbxx(ShYPNAQuery query) {
        return queryMapper.getPmAjJbxx(query);
    }

    @Override
    public List<YX_PC_JBXX> getMapAjJbxx(ShYPNAQuery query) {
        return queryMapper.getMapAjJbxx(query);
    }

    @Override
    public List<YX_PC_JBXX> getMapAjJbxxJbqk(ShYPNAQuery query) {
        return queryMapper.getMapAjJbxxJbqk(query);
    }

    @Override
    public List<DwPaimin> getDwPaiMinByPcjlAndRQ(ShYPNAQuery query) {
        return queryMapper.getDwPaiMinByPcjlAndRQ(query);
    }

    @Override
    public List<YX_PC_JBXX> getDWPaiMinByPcjlAndRQAjJbxx(ShYPNAQuery query) {
        return queryMapper.getDWPaiMinByPcjlAndRQAjJbxx(query);
    }

    @Override
    public Map countPcqkOrBaqk(Map query) {
        query.put("dwbm","".equals(query.get("dwbm"))?"":(query.get("dwbm")+"").split(","));
        query.put("bmbm","".equals(query.get("bmbm"))?"":(query.get("bmbm")+"").split(","));
        int pagenum = (Integer.parseInt(query.get("page") + "") - 1) * Integer.parseInt(query.get("row") + "");
        int row = Integer.parseInt(query.get("row") + "") + pagenum;
        query.put("page", pagenum);
        query.put("row", row);
//        list.addAll(countMapper.getOfflineJbxxCount(query));
        Map dataMap = new HashMap();
        List<Map> list= countMapper.getJbxxCount(query);
        Map count= countMapper.getJbxxCountNum(query);
        dataMap.put("total", count.get("NUM"));
        dataMap.put("rows", list);
        return dataMap;
    }
    @Override
    public List<Map> loadPcInfo(Map query) {
        List<Map> list= countMapper.getPcxFlInfo(query);//18524修改：从视图查询
//        list.addAll(countMapper.getOfflinePcxFlInfo(query));
        return list;
    }

    @Override
    public List<YwtxPm> ywtxAjZhiLiangPaiMin(ShYPNAQuery query) {
        return queryMapper.ywtxAjZhiLiangPaiMin(query);
    }

    @Override
    public List<YX_PC_JBXX> ywtxAjZhiLiangPaiMinAjJbxx(ShYPNAQuery query) {
        return queryMapper.ywtxAjZhiLiangPaiMinAjJbxx(query);
    }

}