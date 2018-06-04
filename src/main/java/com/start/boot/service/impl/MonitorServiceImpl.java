package com.start.boot.service.impl;

import com.start.boot.dao.ajpc.MonitorMapper;
import com.start.boot.domain.XtPcSxgz;
import com.start.boot.domain.XtPcSxgzExample;
import com.start.boot.pojo.dto.GzbmDto;
import com.start.boot.pojo.dto.PcglDto;
import com.start.boot.pojo.dto.PcglViewDto;
import com.start.boot.pojo.vo.PcglVo;
import com.start.boot.pojo.vo.SxgzBmVo;
import com.start.boot.query.MonitoryPcjsQuery;
import com.start.boot.query.PcglQuery;
import com.start.boot.service.CountService;
import com.start.boot.service.MonitorService;
import com.start.boot.service.XtPcSxgzService;
import com.start.boot.service.XtZzjgDwbmService;
import com.start.boot.support.structure.EasyUITree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author caomin
 * @date 2018/3/20
 * @说明
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    CountService coutService;

    @Autowired
    XtZzjgDwbmService zzjgDwbmService;

    @Autowired
    MonitorMapper monitorMapper;

    @Autowired
    XtPcSxgzService xtPcSxgzService;

    @Override
    @Cacheable(cacheNames = "getPcyl")
    public PcglViewDto pcgl(PcglQuery query) {
        PcglViewDto pcglViewDto = new PcglViewDto();
       /* List<PcglVo> wpc = monitorMapper.getWpc(query);*/
        query.setDwbms(query.getDwbm().split(","));
        List<PcglVo> ypc = query.getPcflbm().equals("009")?monitorMapper.getYpcOffline(query): monitorMapper.getYpc(query);
        ArrayList<SxgzBmVo> header = new ArrayList<>();
        List< PcglDto> resultDto = new ArrayList<>();
        /**
         * header init
         */
        LinkedHashMap<SxgzBmVo, Boolean> talbeHeaderList = new LinkedHashMap<SxgzBmVo, Boolean>();
        List<XtPcSxgz> sxgz = monitorMapper.getMbbm(query);
        if (!CollectionUtils.isEmpty(sxgz)){
            for(int i=0;i<sxgz.size();i++){
                XtPcSxgz t=sxgz.get(i);
                if(!t.getGzmc().equals("自定义案件")){
                    talbeHeaderList.put(new SxgzBmVo(t.getGzbm(),t.getGzmc()),true);
                }
            }
           /* sxgz.stream().forEach(t->{
                talbeHeaderList.put(new SxgzBmVo(t.getGzbm(),t.getGzmc()),false);
            });*/
        }
        //设置哪些header要显示
      /*  wpc.stream().forEach(w->{
            talbeHeaderList.put(new SxgzBmVo(w.getSxgzbm(),w.getSxgzmc()),true);
        });*/
       /* ypc.stream().forEach(y->{
            talbeHeaderList.put(new SxgzBmVo(y.getSxgzbm(),y.getSxgzmc()),true);
        });*/
        for (Map.Entry<SxgzBmVo, Boolean> entry : talbeHeaderList.entrySet()) {
            if (entry.getValue()==true){
                header.add(entry.getKey());
            }
        }

        /*if (!CollectionUtils.isEmpty(wpc)){
            wpc.stream().forEach(w->{
                //获取筛选规则的下标位置
                SxgzBmVo e = new SxgzBmVo(w.getSxgzbm(), w.getSxgzmc());
                int sxgzIndex = header.indexOf(e);
                PcglDto orDefault = new PcglDto();
                orDefault.setDwbm(w.getDwbm());

                if (!resultDto.contains(orDefault)){
                    orDefault.setDwmc(w.getDwmc());
                    orDefault.setDwbm(w.getDwbm());
                    GzbmDto gzbmDto = new GzbmDto();
                    gzbmDto.setWpc(w.getWpc());
                    gzbmDto.setSxgzbm(w.getSxgzbm());
                    ArrayList<GzbmDto> temp = new ArrayList<>();
                    for (int i = 0; i < header.size(); i++) {
                        temp.add(new GzbmDto(header.get(i).getSxgzbm()));
                    }
                    orDefault.setGzbmDtoList(temp);
                    orDefault.getGzbmDtoList().set(sxgzIndex,gzbmDto);
                    resultDto.add(orDefault);
                }else {
                    PcglDto pcglDto = resultDto.get(resultDto.indexOf(orDefault));
                    GzbmDto gzbmDto = new GzbmDto();
                    gzbmDto.setWpc(w.getWpc());
                    gzbmDto.setSxgzbm(w.getSxgzbm());
                    pcglDto.getGzbmDtoList().set(sxgzIndex,gzbmDto);
                }

            });
        }*/
        if (!CollectionUtils.isEmpty(ypc)){
          for(int i=0;i<ypc.size();i++) {
              PcglVo y = ypc.get(i);
              SxgzBmVo e = new SxgzBmVo(y.getSxgzbm(), y.getSxgzmc());
              int sxgzIndex = header.indexOf(e);
              PcglDto orDefault = new PcglDto();
              orDefault.setDwbm(y.getDwbm());
              if (!resultDto.contains(orDefault)) {
                  orDefault.setDwmc(y.getDwmc());
                  orDefault.setDwbm(y.getDwbm());
                  GzbmDto gzbmDto = new GzbmDto();
                  gzbmDto.setYpc(y.getYpc());
                  gzbmDto.setSxgzbm(y.getSxgzbm());
                  ArrayList<GzbmDto> temp = new ArrayList<>();
                  for (int c = 0; c < header.size(); c++) {
                      SxgzBmVo v=header.get(c);
                      GzbmDto gzbmDtos = new GzbmDto();
                      gzbmDtos.setYpc(0);
                      gzbmDtos.setSxgzbm(v.getSxgzbm());
                      temp.add(gzbmDtos);
                  }
                  orDefault.setGzbmDtoList(temp);
                  orDefault.getGzbmDtoList().set(sxgzIndex,gzbmDto);
                  resultDto.add(orDefault);
              } else {
                  PcglDto pcglDto = resultDto.get(resultDto.indexOf(orDefault));
                  GzbmDto gzbmDto = pcglDto.getGzbmDtoList().get(sxgzIndex);
                  gzbmDto.setYpc(y.getYpc());
                  gzbmDto.setSxgzbm(y.getSxgzbm());
              }
          }
                //获取筛选规则的下标位置
             /*   SxgzBmVo e = new SxgzBmVo(y.getSxgzbm(), y.getSxgzmc());
                int sxgzIndex = header.indexOf(e);
                PcglDto orDefault = new PcglDto();
                orDefault.setDwbm(y.getDwbm());
                if (!resultDto.contains(orDefault)){
                    orDefault.setDwmc(y.getDwmc());
                    orDefault.setDwbm(y.getDwbm());
                    GzbmDto gzbmDto = new GzbmDto();
                    gzbmDto.setYpc(y.getYpc());
                    gzbmDto.setSxgzbm(y.getSxgzbm());
                    ArrayList<GzbmDto> temp = new ArrayList<>();
                    for (int i = 0; i < header.size(); i++) {
                        temp.add(new GzbmDto());
                    }
                    orDefault.setGzbmDtoList(temp);
                    orDefault.getGzbmDtoList().set(sxgzIndex,gzbmDto);
                    resultDto.add(orDefault);
                }else {
                    PcglDto pcglDto = resultDto.get(resultDto.indexOf(orDefault));
                    GzbmDto gzbmDto = pcglDto.getGzbmDtoList().get(sxgzIndex);
                    gzbmDto.setYpc(y.getYpc());
                    gzbmDto.setSxgzbm(y.getSxgzbm());
                }
            });*/
            ypc.stream().forEach(y->{
                //获取筛选规则的下标位置
                SxgzBmVo e = new SxgzBmVo(y.getSxgzbm(), y.getSxgzmc());
                int sxgzIndex = header.indexOf(e);
                PcglDto orDefault = new PcglDto();
                orDefault.setDwbm(y.getDwbm());
                if (!resultDto.contains(orDefault)){
                    orDefault.setDwmc(y.getDwmc());
                    orDefault.setDwbm(y.getDwbm());
                    GzbmDto gzbmDto = new GzbmDto();
                    gzbmDto.setYpc(y.getYpc());
                    gzbmDto.setSxgzbm(y.getSxgzbm());
                    ArrayList<GzbmDto> temp = new ArrayList<>();
                    for (int i = 0; i < header.size(); i++) {
                        temp.add(new GzbmDto());
                    }
                    orDefault.setGzbmDtoList(temp);
                    orDefault.getGzbmDtoList().set(sxgzIndex,gzbmDto);
                    resultDto.add(orDefault);
                }else {
                    PcglDto pcglDto = resultDto.get(resultDto.indexOf(orDefault));
                    GzbmDto gzbmDto = pcglDto.getGzbmDtoList().get(sxgzIndex);
                    gzbmDto.setYpc(y.getYpc());
                    gzbmDto.setSxgzbm(y.getSxgzbm());
                }
            });
        }
        pcglViewDto.setHeader(header);
        pcglViewDto.setData(resultDto);

        List<Map> rowList = new ArrayList<>();
        // 评接datagrid的数据
        for(int i =0; i< pcglViewDto.getData().size();i++){
            PcglDto pcglDto = pcglViewDto.getData().get(i);
            Map map = new LinkedHashMap();
            map.put("dwbm",pcglDto.getDwbm());
            map.put("dwmc",pcglDto.getDwmc());
            for (GzbmDto gzbmDto: pcglDto.getGzbmDtoList()) {
                if (StringUtils.isEmpty(gzbmDto.getSxgzbm())){
                    continue;
                }
            StringBuilder sb = new StringBuilder();
            sb.append("<a style='cursor:pointer;font-weight:bold'>")
                    .append("<span data-index=").append(i).append(" data-field=").append(gzbmDto.getSxgzbm())
                    .append(" class='ypc' onclick=ypc_monitor_overview_rd_click(this)>").append(gzbmDto.getYpc()).append("</span>");
            if (query.getPcflbm().equals("002")){
                String row = sb.toString().replaceAll("rd", "key");
                map.put(gzbmDto.getSxgzbm(),row);
            }else{
                map.put(gzbmDto.getSxgzbm(),sb.toString());
            }

//               map.put(gzbmDto.getSxgzbm(),gzbmDto.getYpc() + "/" + gzbmDto.getWpc());

            }
            rowList.add(map);

        }

        pcglViewDto.setRowList(rowList);
        return pcglViewDto;

    }

    @Override
    public List<Map> getWpcAjJbxx(PcglQuery query) {
        return monitorMapper.getWpcAjJbxx(query);
    }

    @Override
    public List<Map> getYpcAjJbxx(PcglQuery query) {
        return query.getPcflbm().equals("009")?monitorMapper.getYpcAjJbxxOffline(query):monitorMapper.getYpcAjJbxx(query);
    }

    @Override
    public List<EasyUITree> getDwGroup(MonitoryPcjsQuery monitoryPcjsQuery) {

        List<EasyUITree> trees =new ArrayList<>();
        // 单位：
        if ("BPC_DWMC".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> dw = new ArrayList<>();

            List<EasyUITree> dwGroup=  monitorMapper.getDwGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("单位已评查数量(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(dwGroup);

            trees.add(easyUITree);
        }

        // 检察官：
        if ("BPC_MC".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> jcgGroup = monitorMapper.getJcgGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("检察官(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(jcgGroup);
            trees.add(easyUITree);
        }

        // 评查员：
        if ("PCR_MC".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> pcyGroup = monitorMapper.getPcyGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("评查员(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(pcyGroup);
            trees.add(easyUITree);
        }

        // 部门：
        if ("BPC_BMMC".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> pcyGroup = monitorMapper.getBmGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("部门(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(pcyGroup);
            trees.add(easyUITree);
        }

        // 评查状态：
        if ("PCJDMS".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> pcyGroup = monitorMapper.getPcztGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("评查状态(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(pcyGroup);
            trees.add(easyUITree);
        }

        // 评查结论：
        if ("PCJL".equalsIgnoreCase(monitoryPcjsQuery.getType())) {
            List<EasyUITree> pcyGroup = monitorMapper.getPcjlGroup(monitoryPcjsQuery);
            int pcajCount = monitorMapper.getPcajCount(monitoryPcjsQuery);
            // 头顶节点：
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setState("open");
            easyUITree.setText("评查结论(" + pcajCount +")");
            easyUITree.setChecked(true);

            easyUITree.setChildren(pcyGroup);
            trees.add(easyUITree);
        }

        return trees;
//        return monitorMapper.getDwGroup(monitoryPcjsQuery);
    }
}
