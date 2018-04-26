package com.start.boot.validate;

import com.google.common.base.Strings;
import com.start.boot.dao.ajpc.YX_PC_FZMapper;
import com.start.boot.dao.ajpc.YX_PC_JBXXMapper;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.domain.YX_PC_JBXXExample;
import com.start.boot.query.Query;
import com.start.boot.utils.QueryUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 算法处理
 *
 * @caomin
 * @create 2018-01-04 16:37
 **/
@Component
public class SfInvoke {


    @Autowired
    YX_PC_JBXXMapper jbxxMapper;


    @Autowired
    YX_PC_FZMapper yx_pc_fzMapper;


    @Autowired
    QueryUtils queryUtils;


    /**
     * 专项评查算法
     *
     * @param pchdbm      评查活动编码
     * @param currentDwbm 登录人单位编码
     * @param currentGh   登录人工号
     * @param currentDwmc 登录人单位名称
     * @param currentMc   登录人名称
     * @param type        算法类型 1，平均算法，2同院互斥，3，案件互斥
     * @throws Exception
     */
    public void fpsf(String pchdbm, String currentDwbm, String currentGh, String currentDwmc, String currentMc, String type) throws Exception {
        YX_PC_JBXXExample jbxxExample = new YX_PC_JBXXExample();
        jbxxExample.createCriteria().andPchdbmEqualTo(pchdbm).andPcjdbhBetween("001","005").andPcrGhIsNull();
        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(jbxxExample);


        List<RyTempDto> ryTempDtoList = new ArrayList<>();
        getRyTempList(pchdbm, ryTempDtoList);
        int rySize = ryTempDtoList.size();
        boolean flag = true;
        while (flag) {
            int size = yx_pc_jbxxes.size();
            if (size != 0) {
                int i = RandomUtils.nextInt(0, size);
                int j = RandomUtils.nextInt(0, rySize);
                YX_PC_JBXX jbxx = yx_pc_jbxxes.get(i);
                RyTempDto ryTempDto = ryTempDtoList.get(j);
                if (type != null) {
                    if ("2".equalsIgnoreCase(type)) {
                        //同院互斥
                        if (jbxx.getBPCDWBM().equalsIgnoreCase(ryTempDto.getDwbm())) {
                            continue;
                        } else {
                            yx_pc_jbxxes.remove(i);
                            updateAjjbxx(jbxx, ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                        }
                    } else if ("3".equalsIgnoreCase(type)) {
                        //案件互斥
                        if (jbxx.getBPCDWBM().equalsIgnoreCase(ryTempDto.getDwbm()) && jbxx.getBPCGH().equalsIgnoreCase(ryTempDto.getGh())) {
                            continue;
                        } else {
                            yx_pc_jbxxes.remove(i);
                            updateAjjbxx(jbxx, ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                        }
                    } else {
                        yx_pc_jbxxes.remove(i);
                        updateAjjbxx(jbxx, ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                    }
                }

            } else {
                flag = false;
            }
        }
    }


    /**
     * 平均算法
     */
    public void pjsf(SfDto sfDto) throws Exception {
        // 分配人信息
        String pchdbm = sfDto.getPchdbm();
        String currentDwbm = sfDto.getCurrentDwbm();
        String currentDwmc = sfDto.getCurrentDwmc();
        String currentGh = sfDto.getCurrentGh();
        String currentMc = sfDto.getCurrentMc();

        // 获取案件列表
        YX_PC_JBXXExample jbxxExample = new YX_PC_JBXXExample();
        jbxxExample.createCriteria().andPchdbmEqualTo(pchdbm).andPcjdbhBetween("001","005").andPcrGhIsNull();
        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(jbxxExample);

        // 获取评查员列表
        List<RyTempDto> ryTempDtoList = new ArrayList<>();
        getRyTempList(pchdbm, ryTempDtoList);

        // 案件数量大于评查员数量，即至少人均一件，则先执行平均分配
        if (yx_pc_jbxxes.size() >= ryTempDtoList.size()) {
            // 计算人均案件数
            int pNum = ryTempDtoList.size();
            int average = yx_pc_jbxxes.size() / pNum;
            for (int pIndex = 0; pIndex < pNum; pIndex++) {

                // 获取当前被分配人员信息
                RyTempDto ryTempDto = ryTempDtoList.get(pIndex);

                // 随机抽取案件列表中案件并分配
                for (int i = 0; i < average; i++) {
                    // 随机抽取案件
                    int rdNum = RandomUtils.nextInt(0, yx_pc_jbxxes.size());
                    // 分配案件
                    updateAjjbxx(yx_pc_jbxxes.get(rdNum), ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                    // 移除并更新案件列表
                    yx_pc_jbxxes.remove(rdNum);
                }
            }
        }

        // 分配剩余案件（平均分配后，案件仍有余量）
        if (yx_pc_jbxxes.size() >= 1) {
            // 遍历并分配案件
            for (int cIndex = 0; cIndex < yx_pc_jbxxes.size(); cIndex++) {
                // 随机抽取评查员
                int rdNum = RandomUtils.nextInt(0, ryTempDtoList.size());
                // 分配案件
                updateAjjbxx(yx_pc_jbxxes.get(cIndex), ryTempDtoList.get(rdNum), currentDwbm, currentGh, currentDwmc, currentMc);
                // 移除并更新人员列表
                ryTempDtoList.remove(rdNum);
            }
        }

    }

    public List<YX_PC_JBXX> getCaseList(String pchdbm){

        YX_PC_JBXXExample jbxxExample = new YX_PC_JBXXExample();
        jbxxExample.createCriteria().andPchdbmEqualTo(pchdbm);
        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(jbxxExample);
        return  yx_pc_jbxxes;
    }

    /**
     * 同院互斥
     */
    public void tyhc(SfDto sfDto) throws Exception {
        String pchdbm = sfDto.getPchdbm();
        String currentDwbm = sfDto.getCurrentDwbm();
        String currentDwmc = sfDto.getCurrentDwmc();
        String currentGh = sfDto.getCurrentGh();
        String currentMc = sfDto.getCurrentMc();

        YX_PC_JBXXExample jbxxExample = new YX_PC_JBXXExample();
        jbxxExample.createCriteria().andPchdbmEqualTo(pchdbm).andPcjdbhBetween("001","005").andPcrGhIsNull();
        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(jbxxExample);

        List<RyTempDto> ryTempDtoList = new ArrayList<>();
        getRyTempList(pchdbm, ryTempDtoList);


        // 案件数量大于评查员数量，即至少人均一件，则先执行平均分配
        if (yx_pc_jbxxes.size() >= ryTempDtoList.size()) {
            // 计算人均案件数
            int pNum = ryTempDtoList.size();
            int average = yx_pc_jbxxes.size() / pNum;
            for (int pIndex = 0; pIndex < pNum; pIndex++) {

                // 获取当前被分配人员信息
                RyTempDto ryTempDto = ryTempDtoList.get(pIndex);
                List<YX_PC_JBXX> caseList = yx_pc_jbxxes.stream().filter(t->!t.getBPCDWBM().equals(ryTempDto.getDwbm())).collect(Collectors.toList());
                if (caseList == null || caseList.size() <= 0)
                    continue;

                // 随机抽取案件列表中案件并分配
                for (int i = 0; i < average; i++) {
                    // 随机抽取案件
                    int rdNum = RandomUtils.nextInt(0, caseList.size());
                    // 分配案件
                    updateAjjbxx(caseList.get(rdNum), ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                    // 移除并更新案件列表
                    yx_pc_jbxxes.remove(caseList.get(rdNum));
                }
            }
        }


        // 分配剩余案件（平均分配后，案件仍有余量）
        if (yx_pc_jbxxes.size() >= 1) {
            // 遍历并分配案件
            for (int cIndex = 0; cIndex < yx_pc_jbxxes.size(); cIndex++) {
                YX_PC_JBXX yx_pc_jbxx = yx_pc_jbxxes.get(cIndex);
                List<RyTempDto> personList = ryTempDtoList.stream().filter(t->!t.getDwbm().equals(yx_pc_jbxx.getBPCDWBM())).collect(Collectors.toList());
                if (personList == null || personList.size() <= 0)
                    continue;

                // 随机抽取评查员
                int rdNum = RandomUtils.nextInt(0, personList.size());
                // 分配案件
                updateAjjbxx(yx_pc_jbxxes.get(cIndex), personList.get(rdNum), currentDwbm, currentGh, currentDwmc, currentMc);
            }
        }



        /*int rySize = ryTempDtoList.size();
        boolean flag = true;
        while (flag) {
            int size = yx_pc_jbxxes.size();
            if (size != 0) {
                int i = 0;
                //int i = RandomUtils.nextInt(0, size);
                int j = RandomUtils.nextInt(0, rySize);
                YX_PC_JBXX jbxx = yx_pc_jbxxes.get(i);
                RyTempDto ryTempDto = ryTempDtoList.get(j);
                //同院互斥
                if (jbxx.getBPCDWBM().equalsIgnoreCase(ryTempDto.getDwbm())) {
                    continue;
                } else {
                    yx_pc_jbxxes.remove(i);
                    updateAjjbxx(jbxx, ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                }
            } else {
                flag = false;
            }
        }
*/

    }

    /**
     * 案件互斥
     *
     * @throws Exception
     */
    public void ajhc(SfDto sfDto) throws Exception {
        String pchdbm = sfDto.getPchdbm();
        String currentDwbm = sfDto.getCurrentDwbm();
        String currentDwmc = sfDto.getCurrentDwmc();
        String currentGh = sfDto.getCurrentGh();
        String currentMc = sfDto.getCurrentMc();

        YX_PC_JBXXExample jbxxExample = new YX_PC_JBXXExample();
        jbxxExample.createCriteria().andPchdbmEqualTo(pchdbm).andPcjdbhBetween("001","005").andPcrGhIsNull();

        List<YX_PC_JBXX> yx_pc_jbxxes = jbxxMapper.selectByExample(jbxxExample);

        List<RyTempDto> ryTempDtoList = new ArrayList<>();
        getRyTempList(pchdbm, ryTempDtoList);


        // 案件数量大于评查员数量，即至少人均一件，则先执行平均分配
        if (yx_pc_jbxxes.size() >= ryTempDtoList.size()) {
            // 计算人均案件数
            int pNum = ryTempDtoList.size();
            int average = yx_pc_jbxxes.size() / pNum;
            for (int pIndex = 0; pIndex < pNum; pIndex++) {

                // 获取当前被分配人员信息
                RyTempDto ryTempDto = ryTempDtoList.get(pIndex);
                List<YX_PC_JBXX> caseList = yx_pc_jbxxes.stream().filter(t->!(t.getBPCDWBM().equals(ryTempDto.getDwbm()) && t.getBPCGH().equals(ryTempDto.gh))).collect(Collectors.toList());
                if (caseList == null || caseList.size() <= 0)
                    continue;

                // 随机抽取案件列表中案件并分配
                for (int i = 0; i < average; i++) {
                    // 随机抽取案件
                    int rdNum = RandomUtils.nextInt(0, caseList.size());
                    // 分配案件
                    updateAjjbxx(caseList.get(rdNum), ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                    // 移除并更新案件列表
                    yx_pc_jbxxes.remove(caseList.get(rdNum));
                }
            }
        }


        // 分配剩余案件（平均分配后，案件仍有余量）
        if (yx_pc_jbxxes.size() >= 1) {
            // 遍历并分配案件
            for (int cIndex = 0; cIndex < yx_pc_jbxxes.size(); cIndex++) {
                YX_PC_JBXX yx_pc_jbxx = yx_pc_jbxxes.get(cIndex);
                List<RyTempDto> personList = ryTempDtoList.stream().filter(t->!(t.getDwbm().equals(yx_pc_jbxx.getBPCDWBM()) && t.getGh().equals(yx_pc_jbxx.getBPCGH()))).collect(Collectors.toList());
                if (personList == null || personList.size() <= 0)
                    continue;

                // 随机抽取评查员
                int rdNum = RandomUtils.nextInt(0, personList.size());
                // 分配案件
                updateAjjbxx(yx_pc_jbxxes.get(cIndex), personList.get(rdNum), currentDwbm, currentGh, currentDwmc, currentMc);
            }
        }

        /*int rySize = ryTempDtoList.size();
        boolean flag = true;
        while (flag) {
            int size = yx_pc_jbxxes.size();
            if (size != 0) {
                int i = RandomUtils.nextInt(0, size);
                int j = RandomUtils.nextInt(0, rySize);
                YX_PC_JBXX jbxx = yx_pc_jbxxes.get(i);
                RyTempDto ryTempDto = ryTempDtoList.get(j);
                //案件互斥
                if (jbxx.getBPCDWBM().equalsIgnoreCase(ryTempDto.getDwbm()) && jbxx.getBPCGH().equalsIgnoreCase(ryTempDto.getGh())) {
                    continue;
                } else {
                    yx_pc_jbxxes.remove(i);
                    updateAjjbxx(jbxx, ryTempDto, currentDwbm, currentGh, currentDwmc, currentMc);
                }
            } else {
                flag = false;
            }
        }*/
    }


    private void getRyTempList(String pchdbm, List<RyTempDto> ryTempDtoList) throws Exception {
        Query query = new Query();
        HashMap<String, String> display = new HashMap<>();
        display.put("DWBM", "DWBM");
        display.put("GH", "GH");
        display.put("PCZBM", "PCZBM");
        query.createCriteria().andEqualTo("PCHDBM", pchdbm);
        query.setTableName("YX_PC_XZRYDY").setDisplayColumnName(display);
        List<Map<String, Object>> result = queryUtils.getMap(query);

        for (Map<String, Object> map : result) {
            String dwbm = (String) map.get("DWBM");
            String pczbm = (String) map.get("PCZBM");
            String gh = (String) map.get("GH");
            RyTempDto ryTemp = getRyTemp(dwbm, gh);
            ryTemp.setPczbm(pczbm);
            //查pcz名称
            getPczmc(pchdbm, ryTemp);
            ryTempDtoList.add(ryTemp);
        }
    }

    /**
     * 设置评查组名称
     *
     * @param pchdbm
     * @param ryTempDto
     * @throws Exception
     */
    private void getPczmc(String pchdbm, RyTempDto ryTempDto) throws Exception {
        //查单位名称
        String pczbm = ryTempDto.getPczbm();
        HashMap<String, String> dwmcCache = new HashMap<>();
        String s = dwmcCache.get(pczbm);
        if (StringUtils.isNotEmpty(s)) {
            ryTempDto.setPczmc(s);
            return;
        }
        Query query = new Query();
        HashMap<String, String> display = new HashMap<>();
        display.put("PCZMC", "PCZMC");
        query.createCriteria().andEqualTo("PCHDBM", pchdbm).andEqualTo("PCZBM", pczbm);
        query.setTableName("YX_PC_FZ").setDisplayColumnName(display);
        List<Map<String, Object>> result = queryUtils.getMap(query);
        if (!CollectionUtils.isEmpty(result)) {
            String pczmc = (String) result.get(0).get("PCZMC");
            ryTempDto.setPczmc(pczmc);
            dwmcCache.put(pczbm, pczmc);
        }

    }

    /**
     * 人员信息
     *
     * @param dwbm
     * @param gh
     * @return
     * @throws Exception
     */
    private RyTempDto getRyTemp(String dwbm, String gh) throws Exception {

        String dwmc = getDwmc(dwbm);
        RyTempDto ryTempDto = new RyTempDto();
        ryTempDto.setDwbm(dwbm);
        ryTempDto.setDwmc(dwmc);
        ryTempDto.setGh(gh);

        //查人的名称
        Query mcQuery = new Query();
        HashMap<String, String> mcDisplay = new HashMap<>();
        mcDisplay.put("MC", "MC");

        mcQuery.createCriteria().andEqualTo("DWBM", dwbm).andEqualTo("GH", gh);
        mcQuery.setTableName("XT_ZZJG_RYBM").setDisplayColumnName(mcDisplay);
        List<Map<String, Object>> bmResult = queryUtils.getMap(mcQuery);
        if (!CollectionUtils.isEmpty(bmResult)) {
            String dwmc1 = (String) bmResult.get(0).get("MC");
            ryTempDto.setMc(dwmc1);
        }
        return ryTempDto;
    }

    /**
     * 获取单位名称
     *
     * @param dwbm
     * @return
     * @throws Exception
     */
    private String getDwmc(String dwbm) throws Exception {
        //查单位名称
        HashMap<String, String> dwmcCache = new HashMap<>();
        String s = dwmcCache.get(dwbm);
        if (StringUtils.isNotEmpty(s)) {
            return s;
        }
        Query dwmcQuery = new Query();
        HashMap<String, String> dwmcDisplay = new HashMap<>();
        dwmcDisplay.put("DWMC", "DWMC");
        dwmcQuery.createCriteria().andEqualTo("DWBM", dwbm);
        dwmcQuery.setTableName("XT_ZZJG_DWBM").setDisplayColumnName(dwmcDisplay);
        List<Map<String, Object>> dwbmResult = queryUtils.getMap(dwmcQuery);
        if (!CollectionUtils.isEmpty(dwbmResult)) {
            String dwmc1 = (String) dwbmResult.get(0).get("DWMC");
            dwmcCache.put(dwbm, dwmc1);
            return dwmc1;
        }
        return null;
    }

    /**
     * 分配案件，更新记录
     *
     * @param jbxx
     * @param ryTempDto
     * @param currentDwbm
     * @param currentGh
     * @param currentDwmc
     * @param currentMc
     */
    private void updateAjjbxx(YX_PC_JBXX jbxx, RyTempDto ryTempDto, String currentDwbm, String currentGh, String currentDwmc, String currentMc) {
        /**
         * 获取评查详细信息
         */
        jbxx.setPCRDWBM(ryTempDto.dwbm);
        jbxx.setPCRGH(ryTempDto.gh);
        jbxx.setPCRDWMC(ryTempDto.getDwmc());
        jbxx.setPCRMC(ryTempDto.getMc());
        jbxx.setFPDRFPRDWBM(currentDwbm);
        jbxx.setFPDRFPRDWMC(currentDwmc);
        jbxx.setFPDRFPRGH(currentGh);
        jbxx.setFPDRFPRMC(currentMc);

        jbxx.setFPDZFPRDWBM(currentDwbm);
        jbxx.setFPDZFPRGH(currentGh);
        jbxx.setFPDZFPRMC(currentMc);
        jbxx.setFPDZFPRDWMC(currentDwmc);
        jbxx.setPCZMC(ryTempDto.getPczmc());
        jbxx.setPCZBM(ryTempDto.getPczbm());

        jbxx.setZHXGSJ(DateTime.now().toLocalDate().toDate());
        //湖北：如果没有评查人，则案件状态为005待评查
        if(Strings.isNullOrEmpty(jbxx.getPCRGH())){
            jbxx.setPCJDBH("005");
            jbxx.setPCJDMS("待评查");
        }
        jbxxMapper.updateByPrimaryKeySelective(jbxx);

    }


    public static class RyTempDto {
        private String pczbm;
        private String pczmc;
        private String dwbm;
        private String gh;
        private String dwmc;
        private String mc;

        public String getPczbm() {
            return pczbm;
        }

        public void setPczbm(String pczbm) {
            this.pczbm = pczbm;
        }

        public String getPczmc() {
            return pczmc;
        }

        public void setPczmc(String pczmc) {
            this.pczmc = pczmc;
        }

        public String getDwbm() {
            return dwbm;
        }

        public void setDwbm(String dwbm) {
            this.dwbm = dwbm;
        }

        public String getGh() {
            return gh;
        }

        public void setGh(String gh) {
            this.gh = gh;
        }

        public String getDwmc() {
            return dwmc;
        }

        public void setDwmc(String dwmc) {
            this.dwmc = dwmc;
        }

        public String getMc() {
            return mc;
        }

        public void setMc(String mc) {
            this.mc = mc;
        }
    }
}

