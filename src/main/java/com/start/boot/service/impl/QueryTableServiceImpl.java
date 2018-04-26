package com.start.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.start.boot.dao.ajpc.QueryTableMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.QueryTableVo;
import com.start.boot.query.QueryTable;
import com.start.boot.query.QueryTableAjJbxx;
import com.start.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明
 */
@Service
public class QueryTableServiceImpl implements QueryTableService {

    @Autowired
    QueryTableMapper queryTableMapper;


    @Autowired
    XtDmFldmService xtDmFldmService;


    @Autowired
    XtPcLbService xtPcLbService;


    @Autowired
    XtZzjgDwbmService xtZzjgDwbmService;


    @Autowired
    XtZzjgBmbmService xtZzjgBmbmService;

    @Autowired
    XtZzjgRmbmService xtZzjgRmbmService;

    @Override
    public int getPcxFlCount(QueryTable query) {
        return queryTableMapper.getPcxFlCount(query);
    }

    @Override
    public List<YX_PC_JBXX> getPcxFlCountAjjbxx(QueryTable query) {
        return queryTableMapper.getPcxFlCountAjjbxx(query);
    }

    @Override
    public int getSjCount(QueryTable query) {
        return queryTableMapper.getSjCount(query);
    }

    @Override
    public List<Map> getAjjbxx(QueryTableAjJbxx query) {
        String zmc = query.getZmc();
        String pcxflmc = query.getPcxflmc();
        if(!StringUtils.isEmpty(pcxflmc)&& !StringUtils.isEmpty(zmc)) {
            List<Yx_Pc_PcxFl> pcxFlbmList = queryTableMapper.getPcxFlbmList(zmc, pcxflmc);
            if (!CollectionUtils.isEmpty(pcxFlbmList)){
                List<String> collect = pcxFlbmList.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
                query.setPcxflbmList(collect);
            }
        }
        PageHelper.startPage(query.getPage(), query.getRows());
        return queryTableMapper.getAjjbxx(query);
    }

    @Override
    public List<Map> getWpcAjjbxx(QueryTableAjJbxx query) {
        return queryTableMapper.getSjCountAjjbxx(query);
    }

    @Override
    public List<Map> getSjCountAjjbxx(QueryTableAjJbxx query) {
        return queryTableMapper.getSjCountAjjbxx(query);
    }

    private volatile  int pcfxwtzs=0;
    @Override
    public ArrayList<QueryTableVo> getDwTableData(QueryTable query)  {
        String dwbm = query.getDwbm();
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        ArrayList<QueryTableVo> resultList = new ArrayList<>();
        pcfxwtzs=0;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        List<XtZzjgDwbm> dwbmTreeList = xtZzjgDwbmService.getDwbmTreeList(dwbm);
        List<XtPcLb> allXtPcLb = xtPcLbService.getAllXtPcLb();
        String pcflbm = query.getPcflbm();
        dwbmTreeList.stream().forEach(dw->{
            QueryTableVo temp = new QueryTableVo();
            temp.setName(dw.getDwmc());
            temp.setDwbm(dw.getDwbm());
            query.setDwbm(dw.getDwbm());
            int sjCount = getSjCount(query);
            int pcCount = getPcxFlCount(query);
            int i = sjCount + pcCount;
            if (i!=0){
                String format = decimalFormat.format(((float)pcCount / i)*100.0);
                temp.setPcl(format);
            }else {
                temp.setPcl("0");
            }
            temp.setSjCount(i);
            temp.setPcCount(pcCount);
            getPcFlData(query, allXtPcLb, temp);
            List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy("9102");
            setPcjlData(query, temp, xtDmFldmBy);

            //=============事实认定
            setCommonData(query, temp);
            resultList.add(temp);
            query.setPcflbm(pcflbm);
        });
        setZj(resultList);
        return resultList;
    }

    private void getPcFlData(QueryTable query, List<XtPcLb> allXtPcLb, QueryTableVo temp) {
        allXtPcLb.stream().forEach(lb->{
            if ("常规抽查".equals(lb.getPcflmc())){
                query.setPcflbm(lb.getPcflbm());
                int pcxFlCount = getPcxFlCount(query);
                temp.setCgCount(pcxFlCount);
            }else if ("专项评查".equals(lb.getPcflmc())){
                query.setPcflbm(lb.getPcflbm());
                int pcxFlCount = getPcxFlCount(query);
                temp.setZxCount(pcxFlCount);
            }else if ("重点评查".equals(lb.getPcflmc())){
                query.setPcflbm(lb.getPcflbm());
                int pcxFlCount = getPcxFlCount(query);
                temp.setZdpcCount(pcxFlCount);
            }else if ("交叉评查".equals(lb.getPcflmc())){
                query.setPcflbm(lb.getPcflbm());
                int pcxFlCount = getPcxFlCount(query);
                temp.setJxCount(pcxFlCount);
            }
        });
    }

    /**
     * 设置合计数据
     * @param resultList
     */
    private void setZj(ArrayList<QueryTableVo> resultList) {
        //获取合计数据
        Integer sjCount = resultList.stream().map(t -> t.getSjCount()).reduce(0, (a, b) -> a + b);
        Integer pcCount = resultList.stream().map(t -> t.getPcCount()).reduce(0, (a, b) -> a + b);
        Integer zdpcCount = resultList.stream().map(t -> t.getZdpcCount()).reduce(0, (a, b) -> a + b);
        Integer cgCount = resultList.stream().map(t -> t.getCgCount()).reduce(0, (a, b) -> a + b);
        Integer zxCount = resultList.stream().map(t -> t.getZxCount()).reduce(0, (a, b) -> a + b);
        Integer jxCount = resultList.stream().map(t -> t.getJxCount()).reduce(0, (a, b) -> a + b);
        Integer yzCount = resultList.stream().map(t -> t.getYzCount()).reduce(0, (a, b) -> a + b);
        Integer hgCount = resultList.stream().map(t -> t.getHgCount()).reduce(0, (a, b) -> a + b);
        Integer xcCount = resultList.stream().map(t -> t.getXcCount()).reduce(0, (a, b) -> a + b);
        Integer bhgCount = resultList.stream().map(t -> t.getBhgCount()).reduce(0, (a, b) -> a + b);
        Integer pcwtzsCount = resultList.stream().map(t -> t.getPcwtzsCount()).reduce(0, (a, b) -> a + b);
        Integer ssrdXcCount = resultList.stream().map(t -> t.getSsrdXcCount()).reduce(0, (a, b) -> a + b);
        Integer ssrdCuCount = resultList.stream().map(t -> t.getSsrdCuCount()).reduce(0, (a, b) -> a + b);
        Integer zjcxXcCount = resultList.stream().map(t -> t.getZjcxXcCount()).reduce(0, (a, b) -> a + b);
        Integer zjcxCuCount = resultList.stream().map(t -> t.getZjcxCuCount()).reduce(0, (a, b) -> a + b);
        Integer zjcxfqCount = resultList.stream().map(t -> t.getZjcxfqCount()).reduce(0, (a, b) -> a + b);
        Integer flsyXcCount = resultList.stream().map(t -> t.getFlsyXcCount()).reduce(0, (a, b) -> a + b);
        Integer flsyCuCount = resultList.stream().map(t -> t.getFlsyCuCount()).reduce(0, (a, b) -> a + b);
        Integer flsyfqCount = resultList.stream().map(t -> t.getFlsyfqCount()).reduce(0, (a, b) -> a + b);
        Integer bacxXcCount = resultList.stream().map(t -> t.getBacxXcCount()).reduce(0, (a, b) -> a + b);
        Integer bacxCuCount = resultList.stream().map(t -> t.getBacxCuCount()).reduce(0, (a, b) -> a + b);
        Integer flwsXcCount = resultList.stream().map(t -> t.getFlwsXcCount()).reduce(0, (a, b) -> a + b);
        Integer baxgYbCount = resultList.stream().map(t -> t.getBaxgYbCount()).reduce(0, (a, b) -> a + b);
        BigDecimal bigDecimal1 = new BigDecimal(0.0);
        for (QueryTableVo queryTableVo : resultList) {
            String pcl = queryTableVo.getPcl();
            if (StringUtils.isEmpty(pcl)){
                pcl="0.00";
            }
            BigDecimal bigDecimal = new BigDecimal(pcl);
            bigDecimal1= bigDecimal1.add(bigDecimal);
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        resultList.stream().forEach(t->t.setPcl(t.getPcl()+"%"));
        QueryTableVo t = new QueryTableVo();
        BigDecimal divide = bigDecimal1.divide(new BigDecimal(resultList.size()));
        t.setPcl(decimalFormat.format(divide)+"%");
        t.setSjCount(sjCount);
        t.setPcCount(pcCount);
        t.setZdpcCount(zdpcCount);
        t.setCgCount(cgCount);
        t.setZxCount(zxCount);
        t.setJxCount(jxCount);
        t.setYzCount(yzCount);
        t.setHgCount(hgCount);
        t.setXcCount(xcCount);
        t.setBhgCount(bhgCount);
        t.setPcwtzsCount(pcwtzsCount);
        t.setSsrdXcCount(ssrdXcCount);
        t.setSsrdCuCount(ssrdCuCount);
        t.setZjcxXcCount(zjcxXcCount);
        t.setZjcxCuCount(zjcxCuCount);
        t.setZjcxfqCount(zjcxfqCount);
        t.setFlsyXcCount(flsyXcCount);
        t.setFlsyCuCount(flsyCuCount);
        t.setFlsyfqCount(flsyfqCount);
        t.setBacxXcCount(bacxXcCount);
        t.setBacxCuCount(bacxCuCount);
        t.setFlwsXcCount(flwsXcCount);
        t.setBaxgYbCount(baxgYbCount);
        t.setName("合计");
        resultList.add(0,t);
    }

    @Override
    public ArrayList<QueryTableVo> getBmTableData(QueryTable query){

        String dwbm = query.getDwbm();
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        ArrayList<QueryTableVo> resultList = new ArrayList<>();
        pcfxwtzs=0;

        List<XtZzjgBmbm> dwbmTreeList = xtZzjgBmbmService.getDwbmTreeList(dwbm);
        if (CollectionUtils.isEmpty(dwbmTreeList)){
            return  null;
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        List<XtPcLb> allXtPcLb = xtPcLbService.getAllXtPcLb();
        dwbmTreeList.stream().forEach(rm->{
            {
                QueryTableVo temp = new QueryTableVo();
                temp.setName(rm.getBmmc());
                query.setBmbm(rm.getBmbm());
                int sjCount = getSjCount(query);
                int pcCount = getPcxFlCount(query);
                int i = sjCount + pcCount;
                if (i!=0){
                    String format = decimalFormat.format(((float)pcCount / i)*100.0);
                    temp.setPcl(format);
                }else {
                    temp.setPcl("0");
                }
                temp.setSjCount(sjCount+pcCount);
                temp.setPcCount(pcCount);
                getPcFlData(query, allXtPcLb, temp);
                List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy("9102");
                setPcjlData(query, temp, xtDmFldmBy);
                setCommonData(query, temp);
                resultList.add(temp);
            }

        });
        setZj(resultList);
        return resultList;
    }

    private void setCommonData(QueryTable query, QueryTableVo temp) {
        //=============事实认定
        List<Yx_Pc_PcxFl> pcxFlbmList = queryTableMapper.getPcxFlbmList("事实认定", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmList.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setSsrdXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList1 = queryTableMapper.getPcxFlbmList("事实认定", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList1)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmList1.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setSsrdCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============证据采信
        List<Yx_Pc_PcxFl> pcxFlbmList2 = queryTableMapper.getPcxFlbmList("证据采信", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList2)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList2.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setZjcxCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList3 = queryTableMapper.getPcxFlbmList("证据采信", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList3)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList3.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setZjcxXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmList4 = queryTableMapper.getPcxFlbmList("证据采信", "分歧");
        if (!CollectionUtils.isEmpty(pcxFlbmList4)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList4.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setZjcxfqCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============法律适用

        List<Yx_Pc_PcxFl> pcxFlbmList5 = queryTableMapper.getPcxFlbmList("法律适用", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList5)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList5.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setFlsyCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList6 = queryTableMapper.getPcxFlbmList("法律适用", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList6)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList6.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setFlsyXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmList7 = queryTableMapper.getPcxFlbmList("法律适用", "分歧");
        if (!CollectionUtils.isEmpty(pcxFlbmList7)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList7.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setFlsyfqCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============办案程序
        List<Yx_Pc_PcxFl> pcxFlbmList8 = queryTableMapper.getPcxFlbmList("办案程序", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList8)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmList8.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setBacxXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList9 = queryTableMapper.getPcxFlbmList("办案程序", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList9)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmList9.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setBacxCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        //=============法律文书

        List<Yx_Pc_PcxFl> pcxFlbmList19 = queryTableMapper.getPcxFlbmList("法律文书", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList19)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmList19.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setFlwsXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmListflwscw = queryTableMapper.getPcxFlbmList("法律文书", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListflwscw)){
            query.setPcjl(null);
            query.setPcflbm(null);
            query.setPcxflbmList(null);
            List<String> collect = pcxFlbmListflwscw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setFlwsCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============办案效果
        List<Yx_Pc_PcxFl> pcxFlbmList92 = queryTableMapper.getPcxFlbmList("办案效果", "一般");
        if (!CollectionUtils.isEmpty(pcxFlbmList92)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmList92.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setBaxgYbCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============系统规范应用
        List<Yx_Pc_PcxFl> pcxFlbmListXtgfyyXc = queryTableMapper.getPcxFlbmList("系统规范应用", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListXtgfyyXc)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmListXtgfyyXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setXtgfyyXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============司法责任制落实  错误
        List<Yx_Pc_PcxFl> pcxFlbmListSfzrzlsCw = queryTableMapper.getPcxFlbmList("司法责任制落实", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListSfzrzlsCw)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmListSfzrzlsCw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setSfzrzlsCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============司法责任制落实  瑕疵
        List<Yx_Pc_PcxFl> pcxFlbmListSfzrzlsXc = queryTableMapper.getPcxFlbmList("司法责任制落实", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListSfzrzlsXc)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmListSfzrzlsXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setSfzrzlsXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============其他情况  错误
        List<Yx_Pc_PcxFl> pcxFlbmListQtqkCw = queryTableMapper.getPcxFlbmList("其他情况", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListQtqkCw)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmListQtqkCw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setQtqkCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============其他情况  瑕疵
        List<Yx_Pc_PcxFl> pcxFlbmListQtqkXc = queryTableMapper.getPcxFlbmList("其他情况", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListQtqkXc)){
            query.setPcjl(null);
            query.setPcxflbmList(null);
            query.setPcflbm(null);
            List<String> collect = pcxFlbmListQtqkXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.setPcxflbmList(collect);
            int pcxFlCount = getPcxFlCount(query);
            temp.setQtqkXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
    }

    @Override
    public ArrayList<QueryTableVo> getJcgTableData(QueryTable query) {

            String dwbm = query.getDwbm();
            if (StringUtils.isEmpty(dwbm)){
                return null;
            }
            ArrayList<QueryTableVo> resultList = new ArrayList<>();
            pcfxwtzs=0;

            List<XtZzjgRmbm> dwbmTreeList = xtZzjgRmbmService.getDwRmTreeList(dwbm);
            if (CollectionUtils.isEmpty(dwbmTreeList)){
                return  null;
            }
            DecimalFormat decimalFormat=new DecimalFormat("0.00");
            List<XtPcLb> allXtPcLb = xtPcLbService.getAllXtPcLb();
            dwbmTreeList.stream().forEach(rm->{
                {
                    QueryTableVo temp = new QueryTableVo();
                    temp.setName(rm.getMc());
                    query.setGh(rm.getGh());
                    int sjCount = getSjCount(query);
                    int pcCount = getPcxFlCount(query);
                    int sjCount1 = sjCount + pcCount;
                    temp.setSjCount(sjCount1);
                    temp.setPcCount(pcCount);
                    if (sjCount1!=0){
                        String format = decimalFormat.format(((float)pcCount / sjCount1)*100.0);
                        temp.setPcl(format);
                    }else {
                        temp.setPcl("0");
                    }
                    getPcFlData(query, allXtPcLb, temp);
                    List<XtDmFldm> xtDmFldmBy = xtDmFldmService.getXtDmFldmBy("9102");
                    setPcjlData(query, temp, xtDmFldmBy);

                    //=============事实认定
                    setCommonData(query, temp);
                    resultList.add(temp);
                }
            });
            setZj(resultList);
            return resultList;

    }

    private void setPcjlData(QueryTable query, QueryTableVo temp, List<XtDmFldm> xtDmFldmBy) {
        xtDmFldmBy.stream().forEach(jl->{
            query.setPcflbm(null);
            if ("优质案件".equals(jl.getMc())){
                query.setPcjl(jl.getMc());
                int pcxFlCount = getPcxFlCount(query);
                temp.setYzCount(pcxFlCount);
            }else if ("合格案件".equals(jl.getMc())){
                query.setPcjl(jl.getMc());
                int pcxFlCount = getPcxFlCount(query);
                temp.setHgCount(pcxFlCount);
            }else if ("瑕疵案件".equals(jl.getMc())){
                query.setPcjl(jl.getMc());
                int pcxFlCount = getPcxFlCount(query);
                temp.setXcCount(pcxFlCount);
            }else if ("不合格案件".equals(jl.getMc())){
                query.setPcjl(jl.getMc());
                int pcxFlCount = getPcxFlCount(query);
                temp.setBhgCount(pcxFlCount);
            }
        });
    }
}
