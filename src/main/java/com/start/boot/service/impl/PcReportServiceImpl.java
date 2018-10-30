package com.start.boot.service.impl;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.PcReportMapper;
import com.start.boot.dao.ajpc.YxPcJzwjMapper;
import com.start.boot.dao.ajpc.YxPcSpMapper;
import com.start.boot.domain.YxPcJzwj;
import com.start.boot.domain.YxPcSp;
import com.start.boot.pojo.vo.BgAjVo;
import com.start.boot.pojo.vo.BgAjlb;
import com.start.boot.query.ReportQuery;
import com.start.boot.service.PcReportService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.utils.PoiUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by lei on 2018/10/26.
 */

@Service
public class PcReportServiceImpl implements PcReportService {

    @Autowired
    private PcReportMapper pcReportMapper;

    @Autowired
    YxPcJzwjMapper yxPcJzwjMapper;

    @Autowired
    YxPcSpMapper yxPcSpMapper;

    @Override
    public List<Map> getPcbgMb(String pcflbm, String wsmblb) {
        return pcReportMapper.getPcbgMb(pcflbm, wsmblb);
    }


    @Override
    public String generateDoc(YxPcJzwj yxPcJzwj) throws Exception {

        Map params = null;
        List<BgAjlb> ajlbs = null;

        // 季度报告
        if((SystemConfiguration.djdwbm + "000002").equals(yxPcJzwj.getWsmbbh())){
            params = jdbg(yxPcJzwj);
            // 获取案件问题项(瑕疵案件、优质案件、不合格案件)
            ajlbs = getWtx(yxPcJzwj);
        }

        // 年度报告、半年
        if ((SystemConfiguration.djdwbm +"000003").equals(yxPcJzwj.getWsmbbh()) || (SystemConfiguration.djdwbm +"000004").equals(yxPcJzwj.getWsmbbh())){
            params = ndbg(yxPcJzwj);
            ajlbs = getWtx(yxPcJzwj);
        }

        // 其他案件模板
        if ((SystemConfiguration.djdwbm  + "000006").equals(yxPcJzwj.getWsmbbh())){
            Map map = new HashMap();
            map.put("${TITLE}","案件质量评查模板");
            params = map;
        }


        // 模板配置路径

        String resourcePath = yxPcJzwj.getWsmblj();
        //String resourcePath = "C:\\Users\\lei\\Desktop\\附件1：专项评查报告模板\\新配置\\附件3：年度质量评查报告模板.doc";

        // 待放置位置
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        String saveFilePath = "/" + SystemConfiguration.djdwbm + "/" + DateTime.now().getYear() +   "/"+ yxPcJzwj.getPczybm()  + "/" + fileName + ".doc";
        String filePath = SystemConfiguration.pcfaPath + saveFilePath;
        String targetPath = SystemConfiguration.wzbsPath + filePath;

        // 将信息填入
        PoiUtils poiUtils = new PoiUtils();
        poiUtils.createBg( SystemConfiguration.wzbsPath +resourcePath,targetPath,params,ajlbs);

        return saveFilePath;
    }

    // 季度报告：
    protected Map<String,Object> jdbg(YxPcJzwj yxPcJzwj) {
        // 获取报告开头信息
        List<Map> list = pcReportMapper.getBgKt(yxPcJzwj);

        // 获取案件基本信息
        String pcflaj = pcReportMapper.getPcflAj(yxPcJzwj);
        String pcjlaj = pcReportMapper.getPcjlAj(yxPcJzwj);

        Map ktMap = list.get(0);

        Map params = new HashMap();
        params.put("${PCDWMC}",ktMap.get("PCDWMC"));
        params.put("${PCJD}",ktMap.get("PCJD"));
        params.put("${PCJD1}",ktMap.get("PCJD1"));
        params.put("${AJCOUNT}",ktMap.get("AJCOUNT").toString());

        params.put("${PCFL}", StringUtils.isEmpty(pcflaj) ? "无" : pcflaj);
        params.put("${PCJL}",StringUtils.isEmpty(pcjlaj)? "无问题" :pcjlaj);

        return params;
    }


    // 年度报告
    protected Map<String,Object> ndbg(YxPcJzwj yxPcJzwj) {
        Map params = new HashMap();

        // 获取报告开头信息
        List<Map> list = pcReportMapper.getBgKt(yxPcJzwj);
        Map ktMap = list.get(0);

        params.put("${PCDWMC}",ktMap.get("PCDWMC"));
        params.put("${PCSJ}", ObjectUtils.isEmpty(ktMap.get("PCSJ")) ? "": ktMap.get("PCSJ").toString());

        // 常规抽查,pcflbm =001
        yxPcJzwj.setPcflbm("001");
        List<Map> coutList = pcReportMapper.getCount(yxPcJzwj);
        params.put("${RDCOUNT}",coutList.get(0).get("AJCOUNT").toString());

        String bmAj = pcReportMapper.getbmAj(yxPcJzwj);
        params.put("${BMAJ}",StringUtils.isEmpty(bmAj)? "无" : bmAj);

        String jlAj = pcReportMapper.getJlAj(yxPcJzwj);
        params.put("${JLAJ}",StringUtils.isEmpty(jlAj)? "无问题":jlAj);


        // 重点评查，pcflbm = 008 湖北
        yxPcJzwj.setPcflbm("008");
        List<Map> coutList2 = pcReportMapper.getCount(yxPcJzwj);
        params.put("${KEYCOUNT}",coutList2.get(0).get("AJCOUNT").toString());
        params.put("${KEYRYCOUNT}",coutList2.get(0).get("RYCOUNT").toString());

        String keyjlAj = pcReportMapper.getJlAj(yxPcJzwj);
        params.put("${KEYJLAJ}",StringUtils.isEmpty(keyjlAj) ? "无问题": keyjlAj);

        // 专项评查，pcflbm = 003
        yxPcJzwj.setPcflbm("003");
        List<Map> zxMap = pcReportMapper.specialKz(yxPcJzwj);
        params.put("${ZXND}",ObjectUtils.isEmpty(zxMap.get(0).get("ZXND"))?"":zxMap.get(0).get("ZXND").toString());
        params.put("${HDCOUNT}",zxMap.get(0).get("HDCOUNT").toString());
        params.put("${AJCOUNT}",zxMap.get(0).get("AJCOUNT").toString());

        String zxjlAj = pcReportMapper.getJlAj(yxPcJzwj);
        params.put("${SPJLAJ}",StringUtils.isEmpty(zxjlAj) ?"无问题" :zxjlAj);

        return params;
    }

    //获取问题项
    // 报告格式：序号、办案单位、案件名称、承办人、评查员、评查单位、评查结论、评查发现问题
    // 其中：问题格式：1.瑕疵：格式不规范、内容错误、遗漏； 【评查项父名称(使用radio前面的的那个)】：【评查项】
    //               2.错误:遗漏重要案件事实或者犯罪嫌疑人
    protected List<BgAjlb> getWtx(YxPcJzwj yxPcJzwj){

        List<BgAjlb> ajlist = pcReportMapper.getAjlb(yxPcJzwj);
        if (CollectionUtils.isEmpty(ajlist)){
            return null;
        }

        List<BgAjVo> bgAjVoList = pcReportMapper.getwtx(yxPcJzwj);

        Map<String,String> wtMap = new HashMap();


        Map<String, List<BgAjVo>> collect = bgAjVoList.stream().collect(Collectors.groupingBy(BgAjVo::getPcslbm));
        // 针对每个案件，主要集中在拼问题上：
        for (String key: collect.keySet()){
            // 每个案件下包含的所有问题项
            List<BgAjVo> bgAjVos = collect.get(key);
            StringBuilder stringBuilder = new StringBuilder();
            for (int index = 0; index < bgAjVos.size(); index ++){
                BgAjVo bgAjVo = bgAjVos.get(index);
                if (StringUtils.isEmpty(bgAjVo.getPcxflmc())){
                    continue;
                }
                stringBuilder.append(index +1).append(".").append(bgAjVo.getPcxflmc()).append(bgAjVo.getPcxmc());
            }
            //  {"3189002018PSL001000021": "1.办案程序：未按规定提交讨论、审核、审批；2.法律文书：未签名、盖章、捺手印、注明时间等；"}
            wtMap.put(collect.get(key).get(0).getPcslbm(),stringBuilder.toString());

        }

        // 完善问题详细信息
        for(BgAjlb bgAjlb : ajlist) {
            for (String key : wtMap.keySet()){
                if (bgAjlb.getPcslbm().equalsIgnoreCase(key)){
                    bgAjlb.setPcfxwt(wtMap.get(key));
                }
            }

        }

        return  ajlist;
    }


    @Override
    public void delPcbg(YxPcJzwj yxPcJzwj) {
        yxPcJzwj.setSfsc("Y");
         yxPcJzwjMapper.updateByPrimaryKeySelective(yxPcJzwj);
    }

    /**
     * 根据报告模板获取报告列表
     * @param reportQuery
     * @return
     */
    @Override
    public List<Map> getReportList(ReportQuery reportQuery) {
        if (!StringUtils.isEmpty(reportQuery.getWsmbbh())) {
            return  pcReportMapper.getReportList(reportQuery);
        }
        return null;
    }

    @Override
    public List<Map> getReportHd(ReportQuery reportQuery) {
        return pcReportMapper.getReportHd(reportQuery);
    }

    @Override
    public List<Map> getPcsp(String spjsbm, String dwbm, String gh) {
        if (!StringUtils.isEmpty(spjsbm)) {
            return  pcReportMapper.getPcsp(spjsbm,dwbm, gh);
        }
        return null;
    }

    @Override
    public void updateSpyj(YxPcSp yxPcSp) {
         pcReportMapper.updatePcsp(yxPcSp);
    }

    @Override
    public YxPcSp getSpInfo(String pcspbm) {
        return yxPcSpMapper.selectByPrimaryKey(pcspbm);
    }

    @Override
    public boolean sendPcbgSp(YxPcSp record) throws Exception {
        String pcspbm = "";

        Map map = new HashMap();
        map.put("p_spwjlx", record.getSpwjlx());
        map.put("p_spwjbm", record.getSpwjbm());
        map.put("p_ssrdwbm", record.getSsrdwbm());
        map.put("p_ssrdwmc", record.getSsrdwmc());
        map.put("p_ssrgh", record.getSsrgh());
        map.put("p_ssrxm", record.getSsrxm());
        map.put("p_sprdwbm", record.getSprdwbm());
        map.put("p_sprdwmc", record.getSprdwmc());
        map.put("p_sprgh", record.getSprgh());
        map.put("p_sprxm", record.getSprxm());
        map.put("p_spjsbm", record.getSpjsbm());
        map.put("p_spjsmc", record.getSpjsmc());

        // 操作数据库
        pcReportMapper.sendPcbgSp(map);
        pcspbm = DataAccessHelper.getString(map, "p_pcspbm");
        if (!StringUtils.isNoneEmpty(pcspbm))
            throw new Exception("新增审批记录失败");

        return true;
    }

    @Override
    public void sendDoc(String jzwjbh) {
        pcReportMapper.sendDoc(jzwjbh);
    }

    @Override
    public void backDoc(YxPcSp yxPcSp, YxPcJzwj yxPcJzwj) {

        // 1. 添加退回意见
        pcReportMapper.updatePcsp(yxPcSp);

        // 2.删除审批记录(假删除)
        pcReportMapper.backDoc(yxPcSp);

        // 3.添加日志
        String sm = "报告【" + yxPcJzwj.getWjmc() + "】被退回给" + yxPcJzwj.getNzrxm();
        yxPcSp.setSm(sm);
        pcReportMapper.addCzrz(yxPcSp);

    }

    @Override
    public void updateJzwj(YxPcJzwj yxPcJzwj) {
        pcReportMapper.updateJzwj(yxPcJzwj);
    }

    @Override
    public YxPcJzwj getPcbgDetailInfo(YxPcJzwj yxPcJzwj) {
        YxPcJzwj yxPcJzwj1 = yxPcJzwjMapper.selectByPrimaryKey(yxPcJzwj.getJzwjbh());
        return yxPcJzwj1;
    }

    @Override
    public List<Map> getPcspbgInfo(YxPcSp yxPcSp) {
        return pcReportMapper.getPcspbgInfo(yxPcSp);
    }

}
