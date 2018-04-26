package com.start.boot.web;


import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.Param_CaseInfo;
import com.start.boot.service.CaseInfoService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FileUtils;
import com.start.boot.utils.WebServiceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/8.
 */
@RestController
@RequestMapping("/caseinfo")
public class CaseInfoController extends ArchivesSystemBaseController {

    @Autowired
    private CaseInfoService caseInfoService;
    @Autowired
    private WebServiceUtils webServiceUtils;

    /**
     * 获取关联案件列表
     *
     * @param pcslbm
     * @param bmsah
     * @return
     */
    @RequestMapping("/getGlajs")
    public String getGlajs(String pcslbm, String bmsah) {
        String result = "";

        try {
            List<Map> list = caseInfoService.getGlajs(pcslbm, bmsah, "");
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "BMSAH", "FBM", "AJLBMC", "-1");
        } catch (Exception e) {
            super.errMsg("获取关联案件列表失败。", pcslbm + ";" + bmsah, e);
        }

        return result;
    }

    /**
     * 获取文书卷宗文件列表
     *
     * @param pcslbm
     * @param bmsah
     * @return
     */
    @RequestMapping("/getDocFiles")
    public String getDocFiles(String pcslbm, String bmsah) {
        String result = "";

        try {
            List<Map> list = caseInfoService.getDocFiles(pcslbm, bmsah, "");
//            for (int i = 0; i < list.size(); i++) {
//                String str = list.get(i).get("NAME").toString();
//                str+="\\";
//                String newStr = str.replaceAll("\\\\","\\\\\\\\");
//                list.get(i).put("NAME",newStr);
//            }
           //result= "[{\"id\":\"42282611443\", \"text\":\"审查终结\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"42282611443\",\"ICON\":\"icon-folder\",\"NAME\":\"审查终结\"}, \"state\":\"open\",\"children\":[{\"id\":\"42282611444\", \"text\":\"起诉(含部分不起诉)\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611443\",\"ID\":\"42282611444\",\"ICON\":\"icon-folder\",\"NAME\":\"起诉(含部分不起诉)\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261146311\", \"text\":\"拟不起诉意见书（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/e38f5820925747a89dc050e565accf40.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611444\",\"ID\":\"4228261146311\",\"ICON\":\"icon-file\",\"NAME\":\"拟不起诉意见书（送）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261146310\", \"text\":\"拟不起诉意见书（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/f59088f5ff844f758da75450fb4bf172.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261146311\",\"ID\":\"4228261146310\",\"ICON\":\"icon-file\",\"NAME\":\"拟不起诉意见书（送）\"}}]}]},{\"id\":\"42282611445\", \"text\":\"全案不起诉\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611443\",\"ID\":\"42282611445\",\"ICON\":\"icon-folder\",\"NAME\":\"全案不起诉\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261146313\", \"text\":\"提请启动人民监督员监督案件意见表（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0790fc742c654257a4f4560e5b1859b2.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"4228261146313\",\"ICON\":\"icon-file\",\"NAME\":\"提请启动人民监督员监督案件意见表（送）审批表\"}},{\"id\":\"422826115805\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/dd0ebf4c18df4420a7f09fddf9851732.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115805\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"422826115804\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115805\",\"ID\":\"422826115804\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）\"}}]},{\"id\":\"422826115809\", \"text\":\"执法办案风险评估预警表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1438fdd8e37d44ed8372c9aa780717ac.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115809\",\"ICON\":\"icon-file\",\"NAME\":\"执法办案风险评估预警表审批表\"}},{\"id\":\"422826115932\", \"text\":\"不起诉决定书宣布笔录（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/06d3f936dff54c22a56307acfd223f1a.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115932\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书宣布笔录（杨学斌）\"}},{\"id\":\"4228261151129\", \"text\":\"检察委员会决定事项执行情况反馈表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/51bc48d4b45849ad8075268106d8055d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"4228261151129\",\"ICON\":\"icon-file\",\"NAME\":\"检察委员会决定事项执行情况反馈表审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261151128\", \"text\":\"检察委员会决定事项执行情况反馈表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/097ba866a543403cb11750c550e2362b.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261151129\",\"ID\":\"4228261151128\",\"ICON\":\"icon-file\",\"NAME\":\"检察委员会决定事项执行情况反馈表\"}}]}]}]},{\"id\":\"422826115742\", \"text\":\"公开文书\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826115742\",\"ICON\":\"icon-folder\",\"NAME\":\"公开文书\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261152439\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/cc15e62188ca4e5095960b95aafff33d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115742\",\"ID\":\"4228261152439\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261152438\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035fgk1152438.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261152439\",\"ID\":\"4228261152438\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\"}}]}]},{\"id\":\"422826442987\", \"text\":\"受理\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826442987\",\"ICON\":\"icon-folder\",\"NAME\":\"受理\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261246936\", \"text\":\"受理案件登记表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246936.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246936\",\"ICON\":\"icon-file\",\"NAME\":\"受理案件登记表\"}},{\"id\":\"4228261246924\", \"text\":\"案件材料移送清单\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246924.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246924\",\"ICON\":\"icon-file\",\"NAME\":\"案件材料移送清单\"}},{\"id\":\"4228261246921\", \"text\":\"接收案件通知书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246921.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246921\",\"ICON\":\"icon-file\",\"NAME\":\"接收案件通知书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261246913\", \"text\":\"接收案件通知书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246913.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261246921\",\"ID\":\"4228261246913\",\"ICON\":\"icon-file\",\"NAME\":\"接收案件通知书\"}}]}]},{\"id\":\"422826443060\", \"text\":\"分案\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826443060\",\"ICON\":\"icon-folder\",\"NAME\":\"分案\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261247236\", \"text\":\"审查起诉阶段委托辩护人\\申请法律援助（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1247236.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826443060\",\"ID\":\"4228261247236\",\"ICON\":\"icon-file\",\"NAME\":\"审查起诉阶段委托辩护人\\申请法律援助（杨学斌）\"}}]},{\"id\":\"422826474629\", \"text\":\"审查起诉\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826474629\",\"ICON\":\"icon-folder\",\"NAME\":\"审查起诉\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826474630\", \"text\":\"一次退回补充侦查\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826474630\",\"ICON\":\"icon-folder\",\"NAME\":\"一次退回补充侦查\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261348487\", \"text\":\"补充侦查决定书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1348487.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474630\",\"ID\":\"4228261348487\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261348282\", \"text\":\"补充侦查决定书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1348282.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261348487\",\"ID\":\"4228261348282\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书\"}}]}]},{\"id\":\"422826538947\", \"text\":\"二次退回补充侦查\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826538947\",\"ICON\":\"icon-folder\",\"NAME\":\"二次退回补充侦查\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261558259\", \"text\":\"补充侦查决定书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1558259.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826538947\",\"ID\":\"4228261558259\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261557899\", \"text\":\"补充侦查决定书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1557899.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261558259\",\"ID\":\"4228261557899\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书\"}}]}]},{\"id\":\"4228261146263\", \"text\":\"犯罪嫌疑人诉讼权利义务告知书（审查起诉阶段）（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/a0a92dbc4a2a4b7bb75145ef5b6b0959.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146263\",\"ICON\":\"icon-file\",\"NAME\":\"犯罪嫌疑人诉讼权利义务告知书（审查起诉阶段）（杨学斌）\"}},{\"id\":\"4228261146291\", \"text\":\"公诉案件审查报告（普通版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/30b3d0cf22804786aa562e505151377f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146291\",\"ICON\":\"icon-file\",\"NAME\":\"公诉案件审查报告（普通版）审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261670960\", \"text\":\"公诉案件审查报告（普通版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1670960.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261146291\",\"ID\":\"4228261670960\",\"ICON\":\"icon-file\",\"NAME\":\"公诉案件审查报告（普通版）\"}}]},{\"id\":\"4228261147085\", \"text\":\"执法办案风险评估预警表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/5933ba0ce2374f9fb36d3ed394aa6169.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261147085\",\"ICON\":\"icon-file\",\"NAME\":\"执法办案风险评估预警表审批表\"}},{\"id\":\"42282611518\", \"text\":\"延长审查起诉期限审批表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/40705500296e42e2aa871188e279ac9f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"42282611518\",\"ICON\":\"icon-file\",\"NAME\":\"延长审查起诉期限审批表审批表\"}},{\"id\":\"4228261146356\", \"text\":\"同步录音录像通知单\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/ca9ee32c1b014ed491aa53ff058ae2c0.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146356\",\"ICON\":\"icon-file\",\"NAME\":\"同步录音录像通知单\"}},{\"id\":\"422826115663\", \"text\":\"人民监督员表决意见书\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493D0112E0530A2A00C892BD.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115663\",\"ICON\":\"icon-file\",\"NAME\":\"人民监督员表决意见书\"}},{\"id\":\"422826115664\", \"text\":\"人民监督员表决意见书原件\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493E0112E0530A2A00C892BD.tif.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115664\",\"ICON\":\"icon-file\",\"NAME\":\"人民监督员表决意见书原件\"}},{\"id\":\"422826115665\", \"text\":\"告知表决意见书(告知本院业务部门用)\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493F0112E0530A2A00C892BD.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115665\",\"ICON\":\"icon-file\",\"NAME\":\"告知表决意见书(告知本院业务部门用)\"}},{\"id\":\"422826115731\", \"text\":\"提请检察委员会议题审批表（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/02a93c2ee83245b984f1eeabdc72e0f5.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115731\",\"ICON\":\"icon-file\",\"NAME\":\"提请检察委员会议题审批表（送）\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115732\", \"text\":\"杨学斌贪污案\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/9e2704eb35f14ba48d923f2e5d03efac.wps.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115731\",\"ID\":\"422826115732\",\"ICON\":\"icon-file\",\"NAME\":\"杨学斌贪污案\"}}]},{\"id\":\"422826115742\", \"text\":\"讨论案件笔录\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0b929d8ecae444cd9ee9d18b3096fa3b.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115742\",\"ICON\":\"icon-file\",\"NAME\":\"讨论案件笔录\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261152439\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/cc15e62188ca4e5095960b95aafff33d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115742\",\"ID\":\"4228261152439\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261152438\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035fgk1152438.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261152439\",\"ID\":\"4228261152438\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\"}}]}]},{\"id\":\"422826115747\", \"text\":\"案件请示（通用版）（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/826ac14e646d43d2a555ae2909bd3fa3.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115747\",\"ICON\":\"icon-file\",\"NAME\":\"案件请示（通用版）（送）审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115745\", \"text\":\"案件请示（通用版）（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/62974b8f78dc457baba55c009f23dd54.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115747\",\"ID\":\"422826115745\",\"ICON\":\"icon-file\",\"NAME\":\"案件请示（通用版）（送）\"}}]},{\"id\":\"422826115774\", \"text\":\"流程监控反馈意见书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/ac58d5b3d9e848a58567b45f29f0701f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115774\",\"ICON\":\"icon-file\",\"NAME\":\"流程监控反馈意见书审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115770\", \"text\":\"流程监控反馈意见书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/4f5a7955e27a40b7920987957291402f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115774\",\"ID\":\"422826115770\",\"ICON\":\"icon-file\",\"NAME\":\"流程监控反馈意见书\"}}]},{\"id\":\"422826115801\", \"text\":\"关于XX一案的批复\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D83A1D218630012E0530A2A00C9534C.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115801\",\"ICON\":\"icon-file\",\"NAME\":\"关于XX一案的批复\"}}]}]";
           //result = result.replaceAll("\\\\","\\\\\\\\") ;
            //result = org.apache.commons.lang3.StringEscapeUtils.unescapeJava(result);
            //result= "[{\"id\":\"42282611443\", \"text\":\"审查终结\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"42282611443\",\"ICON\":\"icon-folder\",\"NAME\":\"审查终结\"}, \"state\":\"open\",\"children\":[{\"id\":\"42282611444\", \"text\":\"起诉(含部分不起诉)\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611443\",\"ID\":\"42282611444\",\"ICON\":\"icon-folder\",\"NAME\":\"起诉(含部分不起诉)\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261146311\", \"text\":\"拟不起诉意见书（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/e38f5820925747a89dc050e565accf40.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611444\",\"ID\":\"4228261146311\",\"ICON\":\"icon-file\",\"NAME\":\"拟不起诉意见书（送）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261146310\", \"text\":\"拟不起诉意见书（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/f59088f5ff844f758da75450fb4bf172.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261146311\",\"ID\":\"4228261146310\",\"ICON\":\"icon-file\",\"NAME\":\"拟不起诉意见书（送）\"}}]}]},{\"id\":\"42282611445\", \"text\":\"全案不起诉\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611443\",\"ID\":\"42282611445\",\"ICON\":\"icon-folder\",\"NAME\":\"全案不起诉\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261146313\", \"text\":\"提请启动人民监督员监督案件意见表（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0790fc742c654257a4f4560e5b1859b2.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"4228261146313\",\"ICON\":\"icon-file\",\"NAME\":\"提请启动人民监督员监督案件意见表（送）审批表\"}},{\"id\":\"422826115805\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/dd0ebf4c18df4420a7f09fddf9851732.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115805\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"422826115804\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115805\",\"ID\":\"422826115804\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）\"}}]},{\"id\":\"422826115809\", \"text\":\"执法办案风险评估预警表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1438fdd8e37d44ed8372c9aa780717ac.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115809\",\"ICON\":\"icon-file\",\"NAME\":\"执法办案风险评估预警表审批表\"}},{\"id\":\"422826115932\", \"text\":\"不起诉决定书宣布笔录（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/06d3f936dff54c22a56307acfd223f1a.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"422826115932\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书宣布笔录（杨学斌）\"}},{\"id\":\"4228261151129\", \"text\":\"检察委员会决定事项执行情况反馈表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/51bc48d4b45849ad8075268106d8055d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"42282611445\",\"ID\":\"4228261151129\",\"ICON\":\"icon-file\",\"NAME\":\"检察委员会决定事项执行情况反馈表审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261151128\", \"text\":\"检察委员会决定事项执行情况反馈表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/097ba866a543403cb11750c550e2362b.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261151129\",\"ID\":\"4228261151128\",\"ICON\":\"icon-file\",\"NAME\":\"检察委员会决定事项执行情况反馈表\"}}]}]}]},{\"id\":\"422826115742\", \"text\":\"公开文书\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826115742\",\"ICON\":\"icon-folder\",\"NAME\":\"公开文书\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261152439\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/cc15e62188ca4e5095960b95aafff33d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115742\",\"ID\":\"4228261152439\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261152438\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035fgk1152438.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261152439\",\"ID\":\"4228261152438\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\"}}]}]},{\"id\":\"422826442987\", \"text\":\"受理\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826442987\",\"ICON\":\"icon-folder\",\"NAME\":\"受理\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261246936\", \"text\":\"受理案件登记表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246936.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246936\",\"ICON\":\"icon-file\",\"NAME\":\"受理案件登记表\"}},{\"id\":\"4228261246924\", \"text\":\"案件材料移送清单\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246924.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246924\",\"ICON\":\"icon-file\",\"NAME\":\"案件材料移送清单\"}},{\"id\":\"4228261246921\", \"text\":\"接收案件通知书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246921.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826442987\",\"ID\":\"4228261246921\",\"ICON\":\"icon-file\",\"NAME\":\"接收案件通知书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261246913\", \"text\":\"接收案件通知书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1246913.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261246921\",\"ID\":\"4228261246913\",\"ICON\":\"icon-file\",\"NAME\":\"接收案件通知书\"}}]}]},{\"id\":\"422826443060\", \"text\":\"分案\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826443060\",\"ICON\":\"icon-folder\",\"NAME\":\"分案\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261247236\", \"text\":\"审查起诉阶段委托辩护人/申请法律援助（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1247236.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826443060\",\"ID\":\"4228261247236\",\"ICON\":\"icon-file\",\"NAME\":\"审查起诉阶段委托辩护人/申请法律援助（杨学斌）\"}}]},{\"id\":\"422826474629\", \"text\":\"审查起诉\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"-1\",\"ID\":\"422826474629\",\"ICON\":\"icon-folder\",\"NAME\":\"审查起诉\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826474630\", \"text\":\"一次退回补充侦查\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826474630\",\"ICON\":\"icon-folder\",\"NAME\":\"一次退回补充侦查\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261348487\", \"text\":\"补充侦查决定书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1348487.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474630\",\"ID\":\"4228261348487\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261348282\", \"text\":\"补充侦查决定书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1348282.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261348487\",\"ID\":\"4228261348282\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书\"}}]}]},{\"id\":\"422826538947\", \"text\":\"二次退回补充侦查\",\"attributes\":{\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826538947\",\"ICON\":\"icon-folder\",\"NAME\":\"二次退回补充侦查\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261558259\", \"text\":\"补充侦查决定书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1558259.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826538947\",\"ID\":\"4228261558259\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261557899\", \"text\":\"补充侦查决定书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1557899.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261558259\",\"ID\":\"4228261557899\",\"ICON\":\"icon-file\",\"NAME\":\"补充侦查决定书\"}}]}]},{\"id\":\"4228261146263\", \"text\":\"犯罪嫌疑人诉讼权利义务告知书（审查起诉阶段）（杨学斌）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/a0a92dbc4a2a4b7bb75145ef5b6b0959.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146263\",\"ICON\":\"icon-file\",\"NAME\":\"犯罪嫌疑人诉讼权利义务告知书（审查起诉阶段）（杨学斌）\"}},{\"id\":\"4228261146291\", \"text\":\"公诉案件审查报告（普通版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/30b3d0cf22804786aa562e505151377f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146291\",\"ICON\":\"icon-file\",\"NAME\":\"公诉案件审查报告（普通版）审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261670960\", \"text\":\"公诉案件审查报告（普通版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/1670960.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261146291\",\"ID\":\"4228261670960\",\"ICON\":\"icon-file\",\"NAME\":\"公诉案件审查报告（普通版）\"}}]},{\"id\":\"4228261147085\", \"text\":\"执法办案风险评估预警表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/5933ba0ce2374f9fb36d3ed394aa6169.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261147085\",\"ICON\":\"icon-file\",\"NAME\":\"执法办案风险评估预警表审批表\"}},{\"id\":\"42282611518\", \"text\":\"延长审查起诉期限审批表审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/40705500296e42e2aa871188e279ac9f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"42282611518\",\"ICON\":\"icon-file\",\"NAME\":\"延长审查起诉期限审批表审批表\"}},{\"id\":\"4228261146356\", \"text\":\"同步录音录像通知单\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/ca9ee32c1b014ed491aa53ff058ae2c0.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"4228261146356\",\"ICON\":\"icon-file\",\"NAME\":\"同步录音录像通知单\"}},{\"id\":\"422826115663\", \"text\":\"人民监督员表决意见书\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493D0112E0530A2A00C892BD.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115663\",\"ICON\":\"icon-file\",\"NAME\":\"人民监督员表决意见书\"}},{\"id\":\"422826115664\", \"text\":\"人民监督员表决意见书原件\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493E0112E0530A2A00C892BD.tif.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115664\",\"ICON\":\"icon-file\",\"NAME\":\"人民监督员表决意见书原件\"}},{\"id\":\"422826115665\", \"text\":\"告知表决意见书(告知本院业务部门用)\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D338FB5493F0112E0530A2A00C892BD.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115665\",\"ICON\":\"icon-file\",\"NAME\":\"告知表决意见书(告知本院业务部门用)\"}},{\"id\":\"422826115731\", \"text\":\"提请检察委员会议题审批表（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/02a93c2ee83245b984f1eeabdc72e0f5.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115731\",\"ICON\":\"icon-file\",\"NAME\":\"提请检察委员会议题审批表（送）\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115732\", \"text\":\"杨学斌贪污案\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/9e2704eb35f14ba48d923f2e5d03efac.wps.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115731\",\"ID\":\"422826115732\",\"ICON\":\"icon-file\",\"NAME\":\"杨学斌贪污案\"}}]},{\"id\":\"422826115742\", \"text\":\"讨论案件笔录\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0b929d8ecae444cd9ee9d18b3096fa3b.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115742\",\"ICON\":\"icon-file\",\"NAME\":\"讨论案件笔录\"}, \"state\":\"closed\",\"children\":[{\"id\":\"4228261152439\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/cc15e62188ca4e5095960b95aafff33d.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115742\",\"ID\":\"4228261152439\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）审批表\"}, \"state\":\"open\",\"children\":[{\"id\":\"4228261152438\", \"text\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/6ce101bb7200493383c71d3ae69e035fgk1152438.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"4228261152439\",\"ID\":\"4228261152438\",\"ICON\":\"icon-file\",\"NAME\":\"不起诉决定书（存疑不起诉适用）（杨学斌）（公开版）\"}}]}]},{\"id\":\"422826115747\", \"text\":\"案件请示（通用版）（送）审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/826ac14e646d43d2a555ae2909bd3fa3.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115747\",\"ICON\":\"icon-file\",\"NAME\":\"案件请示（通用版）（送）审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115745\", \"text\":\"案件请示（通用版）（送）\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/62974b8f78dc457baba55c009f23dd54.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115747\",\"ID\":\"422826115745\",\"ICON\":\"icon-file\",\"NAME\":\"案件请示（通用版）（送）\"}}]},{\"id\":\"422826115774\", \"text\":\"流程监控反馈意见书审批表\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/ac58d5b3d9e848a58567b45f29f0701f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115774\",\"ICON\":\"icon-file\",\"NAME\":\"流程监控反馈意见书审批表\"}, \"state\":\"closed\",\"children\":[{\"id\":\"422826115770\", \"text\":\"流程监控反馈意见书\",\"attributes\":{\"WJLX\":\"1\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/4f5a7955e27a40b7920987957291402f.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826115774\",\"ID\":\"422826115770\",\"ICON\":\"icon-file\",\"NAME\":\"流程监控反馈意见书\"}}]},{\"id\":\"422826115801\", \"text\":\"关于XX一案的批复\",\"attributes\":{\"WJLX\":\"3\",\"WJLJ\":\"422826/2014/0301/咸检起诉受[2014]42282600109号/0D83A1D218630012E0530A2A00C9534C.doc.encry\",\"BMSAH\":\"咸检起诉受[2014]42282600109号\",\"PID\":\"422826474629\",\"ID\":\"422826115801\",\"ICON\":\"icon-file\",\"NAME\":\"关于XX一案的批复\"}}]}]";
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "ID", "PID", "NAME", "-1");
            result =  result.replaceAll("\\\\","\\\\\\\\");
        } catch (Exception e) {
            super.errMsg("获取文书卷宗文件列表失败", pcslbm + ";" + bmsah, e);
        }

        return result;
    }

    /**
     * 获取电子卷宗文件列表
     *
     * @param pcslbm
     * @param bmsah
     * @return
     */
    @RequestMapping("/getDossierFiles")
    public String getDossierFiles(String pcslbm, String bmsah) {
        String result = "";

        try {
            List<Map> list = caseInfoService.getDossierFiles(pcslbm, bmsah, "");
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "ID", "PID", "NAME", "-1");
        } catch (Exception e) {
            super.errMsg("获取电子卷宗文件列表失败", pcslbm + ";" + bmsah, e);
        }

        return result;
    }

    /**
     * 获取本地文件配置
     *
     * @return
     */
    @RequestMapping("/getLocalFilePath")
    public String getLocalFilePath() {

        return "/LocalFile/";
    }

    /**
     * 获取文书卷宗文件
     *
     * @param pcslbm
     * @param bmsah
     * @param wsbh
     * @param wjlj
     * @param wjlx
     * @return
     */
    @RequestMapping("/getDocFile")
    public String getDocFile(String pcslbm, String bmsah, String wsbh, String wjlj, String wjlx, String sfzpdf) {
        String result = "";

        try {

            // 调用WebService/GetDocmentFile获取文书卷宗文件
            Map params = new HashMap();
            params.put("pcslbm", pcslbm == null ? "" : pcslbm);
            params.put("bmsah", bmsah);
            params.put("wsbh", wsbh);
            params.put("filePath", wjlj);
            params.put("wjlx", wjlx);
            //params.put("isConvertToPDF", SystemConfiguration.wssfzpdf);
            params.put("isConvertToPDF", sfzpdf.equalsIgnoreCase("Y") ? "true" : "false");
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetDocmentFile", params);
            if(data == null){
                throw new Exception("调用WebService获取文书卷宗文件失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取文书卷宗文件失败，" + list.get(0));
            }

            // 文书卷宗文件路径
            String filePath = SystemConfiguration.wsjzPath + list.get(1);
            if (!(SystemConfiguration.fwwsPath + list.get(1)).equals(SystemConfiguration.wzbsPath + filePath)) {
                FileUtils.copyFile(new File(SystemConfiguration.fwwsPath + list.get(1)), new File(SystemConfiguration.wzbsPath + filePath));
            }
            result = success(filePath, "获取文书卷宗文件成功。");

        } catch (Exception e) {
            super.errMsg("获取文书卷宗文件失败。", pcslbm + ";" + bmsah + ";" + wsbh + ";" + wjlj, e);
            result = failure(e.getMessage(), "获取文书卷宗文件失败。");
        }

        return result;
    }

    /**
     * 获取电子卷宗文件
     *
     * @param pcslbm
     * @param dwbm
     * @param bmsah
     * @param jzbh
     * @return
     */
    @RequestMapping("/getDossierFile")
    public String getDossierFile(String pcslbm, String dwbm, String bmsah, String jzbh) {
        String result = "";

        try {

            // 调用WebService/GetDossierFile获取电子卷宗文件
            Map params = new HashMap();
            params.put("bmsah", bmsah);
            params.put("cbdwbm", dwbm);
            params.put("bm", jzbh);
            params.put("pcslbm", pcslbm);
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetDossierFile", params);
            if(data == null){
                throw new Exception("调用WebService获取电子卷宗文件失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取电子卷宗文件失败，" + list.get(0));
            }

            // 电子卷宗文件路径
            String filePath = SystemConfiguration.dzjzPath + list.get(1);
            if (!(SystemConfiguration.fwjzPath + list.get(1)).equals(SystemConfiguration.wzbsPath + filePath)) {
                FileUtils.copyFile(new File(SystemConfiguration.fwjzPath + list.get(1)), new File(SystemConfiguration.wzbsPath + filePath));
            }
            result = success(filePath, "获取电子卷宗文件成功。");

        } catch (Exception e) {
            super.errMsg("获取电子卷宗失败。", pcslbm + ";" + jzbh, e);
            result = failure(e.getMessage(), "获取电子卷宗文件失败。");
        }

        return result;
    }

    /**
     * 获取案卡配置
     * @param pcslbm
     * @param ajlbbm
     * @param bmsah
     * @return
     */
    @RequestMapping("/getCaseCards")
    public String getCaseCards(String pcslbm, String ajlbbm, String bmsah) {
        String result = "";

        try {

            // 调用WebService/GetCaseCards获取案卡配置项
            Map params = new HashMap();
            params.put("pcslbm", pcslbm);
            params.put("bmsah", bmsah);
            params.put("ajlbbm", ajlbbm);
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetCaseCards", params);
            if(data == null){
                throw new Exception("调用WebService获取案卡配置项失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取案卡配置项失败，" + list.get(0));
            }

            // 案卡项配置
            result = list.get(1);

        } catch (Exception e) {
            super.errMsg("获取案卡配置失败。", pcslbm + ";" + bmsah, e);
        }

        return result;
    }

    /**
     * 获取过程项配置
     * @param pcslbm
     * @param ajlbbm
     * @param bmsah
     * @return
     */
    @RequestMapping("/getProcessCards")
    public String getProcessCards(String pcslbm, String ajlbbm, String bmsah) {
        String result = "";

        try {

            // 调用WebService/GetProcessCards获取过程项配置项
            Map params = new HashMap();
            params.put("pcslbm", pcslbm);
            params.put("bmsah", bmsah);
            params.put("ajlbbm", ajlbbm);
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetProcessCards", params);
            if(data == null){
                throw new Exception("调用WebService获取过程项配置项失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取过程项配置项失败，" + list.get(0));
            }

            // 过程项项配置
            result = list.get(1);
        } catch (Exception e) {
            super.errMsg("获取过程项配置失败。", pcslbm + ";" + bmsah, e);
        }

        return result;
    }

    /**
     * 获取案卡项信息
     *
     * @param pcslbm
     * @param bmsah
     * @param ajlbbm
     * @param akname
     * @return
     */
    @RequestMapping("/getCaseCardInfo")
    public String getCaseCardInfo(String pcslbm, String bmsah, String ajlbbm, String akname) {
        String result = "";

        try {
            // 调用WebService/GetCaseCardInfo获取过程项配置项
            Map params = new HashMap();
            params.put("pcslbm", pcslbm);
            params.put("bmsah", bmsah);
            params.put("ajlbbm", ajlbbm);
            params.put("akname", akname);
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetCaseCardInfo", params);
            if(data == null){
                throw new Exception("调用WebService获取案卡项信息失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取案卡项信息失败，" + list.get(0));
            }

            // 案卡项信息
            result = success(list.get(1), "获取案卡项信息成功。");
        } catch (Exception e) {
            super.errMsg("获取案卡项信息失败。", bmsah + ";" + akname, e);
            // 案卡项信息
            result = failure(e.getMessage(), "获取案卡项信息失败。");
        }

        return result;
    }

    /**
     * 获取过程项信息
     *
     * @param pcslbm
     * @param bmsah
     * @param ajlbbm
     * @param gcakxh
     * @return
     */
    @RequestMapping("/getProcessCardInfo")
    public String getProcessCardInfo(String pcslbm, String bmsah, String ajlbbm, String gcakxh) {
        String result = "";

        try {
            // 调用WebService/GetProcessCardInfo获取过程项配置项
            Map params = new HashMap();
            params.put("pcslbm", pcslbm);
            params.put("bmsah", bmsah);
            params.put("ajlbbm", ajlbbm);
            params.put("akname", gcakxh);
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GetProcessCardInfo", params);
            if(data == null){
                throw new Exception("调用WebService获取过程项信息失败，返回为空。");
            }

            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if(StringUtils.isNoneEmpty(list.get(0))){
                throw new Exception("调用WebService获取过程项信息失败，" + list.get(0));
            }

            // 过程项信息
            result = success(list.get(1), "获取过程项信息成功。");
        } catch (Exception e) {
            super.errMsg("获取过程项信息失败。", bmsah + ";" + gcakxh, e);
            result = failure(e.getMessage(), "获取过程项信息失败。");
        }

        return result;
    }

    /**
     * 获取案件基本信息
     *
     * @param bmsah
     * @return
     */
    @RequestMapping("/getCaseInfo")
    public String getCaseInfo(String pcslbm, String bmsah) {
        String result = "";

        try {
            Param_CaseInfo caseInfo = caseInfoService.getCaseInfo(pcslbm, bmsah);
            if(caseInfo == null || StringUtils.isEmpty(caseInfo.BMSAH)){
                throw new Exception("获取到案件信息为空。");
            }

            String html = FileUtils.readFile(new File(SystemConfiguration.wzbsPath + "/view/CaseDetailInfo.htm"));
            html = html.replaceAll("@BMSAH", filterNull(caseInfo.BMSAH)).replaceAll("@TYSAH", filterNull(caseInfo.TYSAH));
            html = html.replaceAll("@CBDW", filterNull(caseInfo.CBDW_MC)).replaceAll("@SLRQ", filterNull(caseInfo.SLRQ));
            html = html.replaceAll("@AJMC", filterNull(caseInfo.AJMC)).replaceAll("@AJLB", filterNull(caseInfo.AJLB_MC));
            html = html.replaceAll("@CBBM", filterNull(caseInfo.CBBM_MC)).replaceAll("@CBR", filterNull(caseInfo.CBR));
            html = html.replaceAll("@ZCJG", filterNull(caseInfo.ZCJG_DWMC)).replaceAll("@YSDW", filterNull(caseInfo.YSDW_DWMC));
            html = html.replaceAll("@YSWSWH", filterNull(caseInfo.YSWSWH)).replaceAll("@YSAY", filterNull(caseInfo.YSAY_AYMC));
            html = html.replaceAll("@YSQTAY", filterNull(caseInfo.YSQTAY_AYMCS)).replaceAll("@SWAJ", filterNull(caseInfo.SFSWAJ));
            html = html.replaceAll("@DBAJ", filterNull(caseInfo.SFDBAJ)).replaceAll("@GZAJ", filterNull(caseInfo.SFGZAJ));
            html = html.replaceAll("@BLTS", filterNull(caseInfo.BLTS)).replaceAll("@WCRQ", filterNull(caseInfo.WCRQ));
            html = html.replaceAll("@GDRQ", filterNull(caseInfo.GDRQ)).replaceAll("@GDR", filterNull(caseInfo.GDR));
            html = html.replaceAll("@AQZY", filterNull(caseInfo.AQZY));

            // 过程项信息
            result = success(html, "获取案件基本信息成功。");
        } catch (Exception e) {
            super.errMsg("获取案件基本信息失败。", bmsah, e);
            result = failure(e.getMessage(), "获取案件基本信息失败。");
        }

        return result;
    }


    private static String filterNull(String value){
        return value == null ? "" : value;
    }
}
