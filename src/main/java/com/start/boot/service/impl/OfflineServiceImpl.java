package com.start.boot.service.impl;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.OfflineMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import com.start.boot.service.OfflineService;
import com.start.boot.service.UserService;
import com.start.boot.support.utils.DataAccessHelper;
import com.start.boot.support.utils.OracleTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.groups.ConvertGroup;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OfflineServiceImpl implements OfflineService {
    @Autowired
    private OfflineMapper offlineMapper;

    @Autowired
    private UserService userService;


    @Override
    public List<Map> getPcbz(String pcflbm,String dwbm) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        seach.put("pcflbm",pcflbm);
        seach.put("dwbm",dwbm);
        // 操作数据库
        List<Map> list= offlineMapper.getPcbz(seach);
        return list;
    }
    @Override
    public List<Map> getAjmb(String pcflbm,String pcmbbm,String dwbm) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        seach.put("pcflbm",pcflbm);
        seach.put("pcmbbm",pcmbbm);
        seach.put("dwbm",dwbm);
        // 操作数据库
        List<Map> list= offlineMapper.getAjmb(seach);
        return list;
    }
    @Override
    public List<Map> getPcTree(String mbNo,String pcflbm) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        seach.put("mbNo",mbNo);
        seach.put("pcflbm",pcflbm);
        // 操作数据库
        List<Map> list= offlineMapper.getPcTree(seach);
        //逻辑处理
        List<Map> flx=new ArrayList();//分类详情
        for(int i=0;i<list.size();i++){
            Map flxMap=new HashMap();
            Map map=list.get(i);
            if(map.get("PCXBM")!=null){
                flxMap.put("pcxmc",map.get("PCXMC"));
                flxMap.put("pcxbm",map.get("PCXBM"));
                flxMap.put("pcxflbm",map.get("PCXFLBMS"));
                flxMap.put("sm",map.get("BZ"));
                flxMap.put("PCMBBM",map.get("PCMBBMS"));
                flxMap.put("PCFS",map.get("PCFS"));
                flxMap.put("PCJLBM",map.get("PCJLBMS"));
                flxMap.put("FZ_GD",map.get("FZ_GD"));
                flxMap.put("FZ_QSZ",map.get("FZ_QSZ"));
                flxMap.put("FZ_JSZ",map.get("FZ_JSZ"));
                flxMap.put("ZDPCCX",map.get("ZDPCCX"));
                flxMap.put("XH",map.get("XHS"));
                flxMap.put("SFTJ",map.get("SFTJS"));
                flxMap.put("MRZ",map.get("MRZS"));
                flxMap.put("FLXTDM",map.get("FLXTDMS"));
                flxMap.put("FFLXTDM",map.get("FFLXTDMS"));
                flxMap.put("YWTX",map.get("YWTXS"));
                flxMap.put("PCFLBM",map.get("PCFLBMS"));
                flxMap.put("XTDM",map.get("XTDM"));
                flx.add(flxMap);
            }
        }
        String flNo="";
        List<Map> fl=new ArrayList();//分类
        for(int i=0;i<list.size();i++){
            Map flMap=new HashMap();
            Map map=list.get(i);
            if(map.get("PCXFLFBM")!=null){
                if(!flNo.equals(map.get("PCXFLBM"))){
                    flNo=String.valueOf(map.get("PCXFLBM"));
                    flMap.put("pcxflmc",map.get("PCXFLMC"));
                    flMap.put("pcxflbm",map.get("PCXFLBM"));
                    flMap.put("pcxflfbm",map.get("PCXFLFBM"));
                    flMap.put("PCMBBM",map.get("PCMBBM"));
                    flMap.put("PCJLBM",map.get("PCJLBM"));
                    flMap.put("XH",map.get("XH"));
                    flMap.put("SFTJ",map.get("SFTJ"));
                    flMap.put("SM",map.get("SM"));
                    flMap.put("MRZ",map.get("MRZ"));
                    flMap.put("FLXTDM",map.get("FLXTDM"));
                    flMap.put("FFLXTDM",map.get("FFLXTDM"));
                    flMap.put("YWTX",map.get("YWTX"));
                    flMap.put("PCFLBM",map.get("PCFLBM"));
                    fl.add(flMap);
                }
            }
        }
        for(int i=0;i<fl.size();i++){
            List<Map> flxCh=new ArrayList();
            Map m=fl.get(i);
            for(int a=0;a<flx.size();a++){
                Map mx=flx.get(a);
                if(m.get("pcxflbm").equals(mx.get("pcxflbm"))){
                    flxCh.add(mx);
                }
            }
            m.put("children",flxCh);
        }
        List<Map> mb=new ArrayList();//模板
        for(int i=0;i<list.size();i++){
            Map map=list.get(i);
            Map mbfl=new HashMap();
            if(map.get("PCXFLFBM")==null){
                mbfl.put("pcxflbm",map.get("PCXFLBM"));
                mbfl.put("pcxflmc",map.get("PCXFLMC"));
                mbfl.put("PCXFLFBM",map.get("PCXFLFBM"));
                mbfl.put("PCMBBM",map.get("PCMBBM"));
                mbfl.put("PCJLBM",map.get("PCJLBM"));
                mbfl.put("XH",map.get("XH"));
                mbfl.put("SFTJ",map.get("SFTJ"));
                mbfl.put("SM",map.get("SM"));
                mbfl.put("MRZ",map.get("MRZ"));
                mbfl.put("FLXTDM",map.get("FLXTDM"));
                mbfl.put("FFLXTDM",map.get("FFLXTDM"));
                mbfl.put("YWTX",map.get("YWTX"));
                mbfl.put("PCFLBM",map.get("PCFLBM"));
                mb.add(mbfl);
            }
        }
        for(int i=0;i<mb.size();i++){
            List<Map> flch=new ArrayList();//分类
            Map m=mb.get(i);
            for(int a=0;a<fl.size();a++){
                Map mx=fl.get(a);
                if(m.get("pcxflbm").equals(mx.get("pcxflfbm"))){
                    flch.add(mx);
                }
            }
            m.put("children",flch);
        }
        return mb;
    }
    @Override
    public int saveOfflineInfo(List<Yx_Pc_PcxFlVo> vo, Map map) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        int i=0;
        if(map.get("type")==null){
            seach.put("pcdwbm",map.get("userdwbm"));
            seach.put("pcflbm",map.get("pcflbm"));
            // 操作数据库
            String num = "";
            String bmsa = "";
            String ty = "";
            List<Map> pcslList= offlineMapper.getPcslBm(seach);
            Map dwjc= offlineMapper.getDwJc(seach);
            if(pcslList.size()>0){
                Map pcslNo=pcslList.get(0);
                String code=pcslNo.get("PCSLBM")+"";
                code=code.substring(code.length()-6,code.length());
                num = String.format("%0" + code.length() + "d", Integer.parseInt(code) + 1);
                code=pcslNo.get("BMSAH")+"";
                code=code.substring(code.length()-6,code.length()-1);
                bmsa = String.format("%0" + code.length() + "d", Integer.parseInt(code) + 1);
                code=pcslNo.get("TYSAH")+"";
                code=code.substring(10);
                ty = String.format("%0" + code.length() + "d", Integer.parseInt(code) + 1);
            }else{
                num="000001";
                bmsa="00001";
                ty="0000001";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            String nowdate=sdf.format(date);
            String newPcslNo=map.get("userdwbm")+nowdate+"XSL"+map.get("pcflbm")+num;
            map.put("wcrqnf",(map.get("date")+"").split("-")[0]);
            map.put("pcslbm",newPcslNo);
            map.put("pcsah",dwjc.get("DWJC")+"线下评查["+nowdate+"]"+map.get("userdwbm")+Integer.valueOf(map.get("pcflbm")+"")+num+"号");
            map.put("bmsah",dwjc.get("DWJC")+"线下"+map.get("ajsljc")+"["+(map.get("date")+"").split("-")[0]+"]"+map.get("userdwbm")+bmsa+"号");
            map.put("tysah",map.get("userdwbm")+""+(map.get("date")+"").split("-")[0]+ty);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            nowdate=sdf.format(date);
            map.put("nowdate",nowdate);
            map.put("pcjdbh","011");
            map.put("pcjdms","评查结束");
            map.put("way","1");
            if(map.containsKey("cbrgh") && "".equals(map.get("cbrgh").toString())){
                Rybm rybm = new Rybm();
                rybm.setDwbm(map.get("pcdw").toString());
                rybm.setMc(map.get("cbr").toString());
                rybm.setDlbm(map.get("cbr").toString());
                rybm.setXb("1");
                rybm.setSfsc("N");
                rybm.setSflsry("N");
                rybm.setSftz("N");
                int gh = userService.offlineAddRybm(rybm);
                map.put("cbrgh", gh);
            }
            i= offlineMapper.saveAnJian(map);
            for (Yx_Pc_PcxFlVo fl:vo) {
                fl.setPcslbm(newPcslNo);
                fl.setJlsj(date);
                fl.setPcrdwbm(map.get("userdwbm")+"");
                fl.setPcrdwmc(map.get("userdwmc")+"");
                fl.setPcrgh(map.get("usergh")+"");
                fl.setPcrmc(map.get("usermc")+"");
                offlineMapper.savePCFL(fl);
                for (Yx_Pc_PcxFlVo flx:fl.getChildren()) {
                    flx.setPcslbm(newPcslNo);
                    flx.setJlsj(date);
                    flx.setPcrdwbm(map.get("userdwbm")+"");
                    flx.setPcrdwmc(map.get("userdwmc")+"");
                    flx.setPcrgh(map.get("usergh")+"");
                    flx.setPcrmc(map.get("usermc")+"");
                    offlineMapper.savePCFL(flx);
                    for (Yx_Pc_Pcx pcx:flx.getPcxList()) {
                        pcx.setPcslbm(newPcslNo);
                        pcx.setJlsj(date);
                        pcx.setPcrdwbm(map.get("userdwbm")+"");
                        pcx.setPcrdwmc(map.get("userdwmc")+"");
                        pcx.setPcrgh(map.get("usergh")+"");
                        pcx.setPcrmc(map.get("usermc")+"");
                        offlineMapper.savePCX(pcx);
                    }
                }
            }
        }else{
            offlineMapper.updatePcajjg(map);
            for (Yx_Pc_PcxFlVo fl:vo) {
                offlineMapper.updatePCFL(fl);
                for (Yx_Pc_PcxFlVo flx:fl.getChildren()) {
                    offlineMapper.updatePCFL(flx);
                    for (Yx_Pc_Pcx pcx:flx.getPcxList()) {
                        offlineMapper.updatePCX(pcx);
                    }
                }
            }
            i=1;
        }
        return i;
    }
    // 已办已评查已反馈 列表
    @Override
    public Map loadOfflineList(Map map) throws Exception {
        String errMsg = "";
        int pagenum=(Integer.parseInt(map.get("page")+"")-1)*Integer.parseInt(map.get("rows")+"" );
        int row=Integer.parseInt( map.get("rows")+"")+pagenum;
        String dw=map.get("dwbm")+"";
        String [] d=dw.split(",");
        map.put("dwbm",d);
        map.put("bpcdw",(null==map.get("bpcdw")||"".equals(map.get("bpcdw"))) ? "":(map.get("bpcdw")+"").split(","));
        map.put("page",pagenum);
        map.put("row",row);
        // 操作数据库
        List<Map> list=offlineMapper.loadOfflineList(map);
        Map count=offlineMapper.loadOfflineCount(map);
        Map dataMap=new HashMap();
        dataMap.put("total", count.get("NUM"));
        dataMap.put("rows", list);
        return dataMap;
    }
    @Override
    public List<Map> getPcjgInfo(String pcslbm,String dw) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        seach.put("pcslbm",pcslbm);
        seach.put("dw",dw);
        // 操作数据库
        List<Map> list= offlineMapper.getPcflInfo(seach);
        // 操作数据库
        List<Map> listx= offlineMapper.getPcflxInfo(seach);
        //逻辑处理
        List<Map> flx=new ArrayList();//分类详情
        for(int i=0;i<listx.size();i++){
            Map flxMap=new HashMap();
            Map map=listx.get(i);
            if(map.get("PCXBM")!=null){
                flxMap.put("pcxmc",map.get("PCXMC"));
                flxMap.put("pcxbm",map.get("PCXBM"));
                flxMap.put("pcxflbm",map.get("PCXFLBMS"));
                flxMap.put("sm",map.get("SMS"));
                flxMap.put("pcslbm",map.get("PCSLBMS"));
                flxMap.put("PCRDWBM",map.get("PCRDWBMS"));
                flxMap.put("PCMBBM",map.get("PCMBBMS"));
                flxMap.put("PCFS",map.get("PCFS"));
                flxMap.put("PCJLBM",map.get("PCJLBMS"));
                flxMap.put("FZ_GD",map.get("FZ_GD"));
                flxMap.put("FZ_QSZ",map.get("FZ_QSZ"));
                flxMap.put("FZ_JSZ",map.get("FZ_JSZ"));
                flxMap.put("ZDPCCX",map.get("ZDPCCX"));
                flxMap.put("XH",map.get("XHS"));
                flxMap.put("SFTJ",map.get("SFTJS"));
                flxMap.put("pcjg",map.get("PCJGS"));
                flxMap.put("FLXTDM",map.get("FLXTDMS"));
                flxMap.put("FFLXTDM",map.get("FFLXTDMS"));
                flxMap.put("YWTX",map.get("YWTXS"));
                flxMap.put("PCFLBM",map.get("PCFLBMS"));
                flxMap.put("XTDM",map.get("XTDM"));
                flx.add(flxMap);
            }
        }
        String flNo="";
        List<Map> fl=new ArrayList();//分类
        for(int i=0;i<list.size();i++){
            Map flMap=new HashMap();
            Map map=list.get(i);
            if(map.get("PCXFLFBM")!=null){
                if(!flNo.equals(map.get("PCXFLBM"))){
                    flNo=String.valueOf(map.get("PCXFLBM"));
                    flMap.put("pcxflmc",map.get("PCXFLMC"));
                    flMap.put("pcxflbm",map.get("PCXFLBM"));
                    flMap.put("pcxflfbm",map.get("PCXFLFBM"));
                    flMap.put("pcslbm",map.get("PCSLBM"));
                    flMap.put("PCRDWBM",map.get("PCRDWBM"));
                    flMap.put("PCMBBM",map.get("PCMBBM"));
                    flMap.put("PCJLBM",map.get("PCJLBM"));
                    flMap.put("XH",map.get("XH"));
                    flMap.put("SFTJ",map.get("SFTJ"));
                    flMap.put("SM",map.get("SM"));
                    flMap.put("pcjg",map.get("PCJG"));
                    flMap.put("FLXTDM",map.get("FLXTDM"));
                    flMap.put("FFLXTDM",map.get("FFLXTDM"));
                    flMap.put("YWTX",map.get("YWTX"));
                    flMap.put("PCFLBM",map.get("PCFLBM"));
                    fl.add(flMap);
                }
            }
        }
        for(int i=0;i<fl.size();i++){
            List<Map> flxCh=new ArrayList();
            Map m=fl.get(i);
            for(int a=0;a<flx.size();a++){
                Map mx=flx.get(a);
                if(m.get("pcxflbm").equals(mx.get("pcxflbm"))){
                    flxCh.add(mx);
                }
            }
            m.put("children",flxCh);
        }
        List<Map> mb=new ArrayList();//模板
        for(int i=0;i<list.size();i++){
            Map map=list.get(i);
            Map mbfl=new HashMap();
            if(map.get("PCXFLFBM")==null){
                mbfl.put("pcxflbm",map.get("PCXFLBM"));
                mbfl.put("pcxflmc",map.get("PCXFLMC"));
                mbfl.put("PCXFLFBM",map.get("PCXFLFBM"));
                mbfl.put("PCMBBM",map.get("PCMBBM"));
                mbfl.put("PCJLBM",map.get("PCJLBM"));
                mbfl.put("pcslbm",map.get("PCSLBM"));
                mbfl.put("PCRDWBM",map.get("PCRDWBM"));
                mbfl.put("XH",map.get("XH"));
                mbfl.put("SFTJ",map.get("SFTJ"));
                mbfl.put("SM",map.get("SM"));
                mbfl.put("pcjg",map.get("PCJG"));
                mbfl.put("FLXTDM",map.get("FLXTDM"));
                mbfl.put("FFLXTDM",map.get("FFLXTDM"));
                mbfl.put("YWTX",map.get("YWTX"));
                mbfl.put("PCFLBM",map.get("PCFLBM"));
                mb.add(mbfl);
            }
        }
        for(int i=0;i<mb.size();i++){
            List<Map> flch=new ArrayList();//分类
            Map m=mb.get(i);
            for(int a=0;a<fl.size();a++){
                Map mx=fl.get(a);
                if(m.get("pcxflbm").equals(mx.get("pcxflfbm"))){
                    flch.add(mx);
                }
            }
            m.put("children",flch);
        }
        return mb;
    }
    // 已办已评查已反馈 列表
    @Override
    public Map getPcAjInfo(Map map) throws Exception {
        String errMsg = "";
        // 操作数据库
        Map dataMap=offlineMapper.getPcAjInfo(map);
        return dataMap;
    }
    // 修改案件删除
    @Override
    public int updatePcaj(String pcslbm,String dw) throws Exception {
        String errMsg = "";
        Map seach=new HashMap();
        seach.put("pcslbm",pcslbm);
        seach.put("dw",dw);
        // 操作数据库
        int i=offlineMapper.updatePcaj(seach);
        return i;
    }
    //判断是否存在
    @Override
    public int isOnAj(Map map) throws Exception {
        String errMsg = "";
        // 操作数据库
        List<Map> list=offlineMapper.isOnAj(map);
        return list.size()>0?1:0;
    }

    // 已办已评查已反馈 列表
    @Override
    public List<Map> loadOfflineListExcel(Map map) throws Exception {
        String errMsg = "";
        String dw=map.get("dwbm")+"";
        String [] d=dw.split(",");
        map.put("dwbm",d);
        // 操作数据库
        List<Map> list=offlineMapper.loadOfflineListExcel(map);
        return list;
    }
}
