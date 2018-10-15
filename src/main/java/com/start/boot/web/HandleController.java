package com.start.boot.web;


import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.YjMapper;
import com.start.boot.domain.*;
import com.start.boot.service.HandleService;
import com.start.boot.service.JbxxService;
import com.start.boot.service.MessageService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import com.start.boot.utils.WebServiceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评查办理控制器
 * Created by lei on 2017/11/4.
 */
@RestController
@RequestMapping("/handle")
public class HandleController extends ArchivesSystemBaseController {

    @Autowired
    private HandleService handleService;
    @Autowired
    private WebServiceUtils webServiceUtils;

    @Autowired
    MessageService messageService;

    @Autowired
    JbxxService jbxxService;

    @Autowired
    SystemCoreConfigService systemConfigService;

    @Autowired
    YjMapper yjMapper;
    /**
     * 获取评查案件列表
     *
     * @param
     * @return
     */
    @RequestMapping("/get_pclist")
    public String get_pclist(String json) {

        //设置相应内容
        String result = "";

        try {
            Param_sp param = FastJsonUtils.toObject(Param_sp.class, json);
            param.setPcrdwbm(getCurrentDwbm());
            param.setPcrgh(getCurrentGh());
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_pclist(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {

            super.errMsg("获取评查列表失败", json, e);
            result = failure(e.getMessage(), "获取评查列表失败");
        }

        return result;
    }

    // 获取评查审批案件列表
    @RequestMapping("get_splst")
    public String get_splst(String json) {

        String result = "";

        try {
            Param_sp param = FastJsonUtils.toObject(Param_sp.class, json);
            param.setSprdwbm(getCurrentDwbm());
            param.setSprgh(getCurrentGh());
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_splst(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {

            super.errMsg("获取评查审批案件列表失败", json, e);
            result = failure(e.getMessage(), "获取评查审批案件列表失败");
        }

        return result;
    }
    // 根据评查活动编号获取当前活动的审批信息（意见，结论）
    @RequestMapping("get_spxx")
    public String get_spxx(String json) {

        String result = "";

        try {
            Param_sp param = FastJsonUtils.toObject(Param_sp.class, json);

            Param_Pager data = handleService.get_spxx(param);

            result = success(EasyUIHelper.buildDataGridDataSource(data.getList(), data.getList().size()),"获取评查审批案件列表成功。");
        } catch (Exception e) {

            super.errMsg("获取评查审批案件列表失败", json, e);
            result = failure(e.getMessage(), "获取审批意见，结论失败");
        }

        return result;
    }

   /* // 根据评查活动编号获取当前活动的审批意见，结论
    @RequestMapping("get_spxx")
    public String get_spxx(String json) {

        String result = "";

        try {
            Param_sp param = FastJsonUtils.toObject(Param_sp.class, json);
            List<Map> list =handleService.get_spxx(param);
            if (list == null || list.size() <= 0 ) {
                throw new Exception("未获取到审批意见，结论。");
            }

            result = success(list.get(0), "获取审批意见，结论成功");
        } catch (Exception e) {

            super.errMsg("获取评查审批案件列表失败", json, e);
            result = failure(e.getMessage(), "获取审批意见，结论失败");
        }

        return result;
    }*/


    // 承办人反馈列表
    @RequestMapping("get_cbrfklist")
    public String get_cbrfklist(String json) {

        String result = "";

        try {
            Param_Jbxx param = FastJsonUtils.toObject(Param_Jbxx.class, json);
            param.setBpc_dwbm(getCurrentDwbm());
            param.setBpc_gh(getCurrentGh());
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_cbrfklist(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {

            super.errMsg("获取承办人反馈列表失败", json, e);
            result = failure(e.getMessage(), "获取承办人反馈列表失败");
        }

        return result;
    }

    // 承办部门反馈列表
    @RequestMapping("get_cbbmfklist")
    public String get_cbbmfklist(String json) {

        String result = "";

        try {
            Param_Jbxx param = FastJsonUtils.toObject(Param_Jbxx.class, json);
            param.setBpc_dwbm(getCurrentDwbm());
            param.setBpc_gh(getCurrentGh());
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_cbbmfklist(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {

            super.errMsg("获取承办部门反馈列表失败", json, e);
            result = failure(e.getMessage(), "获取承办部门反馈列表失败");
        }

        return result;
    }

    /**
     * 获取评查卷宗文件
     *
     * @param json
     * @return
     */
    @RequestMapping("/getDocFile")
    public String getDocFile(String json) {
        String result = "";

        try {
            Param_Pc param = FastJsonUtils.toObject(Param_Pc.class, json);
            param.setDwbm(getCurrentDwbm());
            param.setGh(getCurrentGh());

            List<Map> list = handleService.getDocFiles(param);
            result = EasyUIHelper.buildTreeListDataSource(list, "BM", "FBM", "MC", "ICON", "-1");
            result = result.replaceAll("\"state\":\"closed\"", "\"state\":\"open\"");
        } catch (Exception e) {
            super.errMsg("获取评查卷宗失败", json, e);
        }

        return result;
    }

    // 获取评查活动列表
    @RequestMapping("/getPchdList")
    public String getPchdList(String json) {

        //设置相应内容
        String result = "";

        try {
            Param_HdList param = FastJsonUtils.toObject(Param_HdList.class, json);
            param.setCjrdwbm(getCurrentDwbm());
            param.setP_cjrgh(getCurrentGh());
            //param.setPage(parsePage(getParameter("page")));
            //param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.getPchdList(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("获取评查活动列表失败", json, e);
            result = failure(e.getMessage(), "获取评查活动列表失败");
        }

        return result;
    }

    // 评查案件确认
    @RequestMapping("/caseConfirm")
    public String caseConfirm(String pcslbm) {
        String result = "";

        try {
            Param_Send param = new Param_Send();
            param.setPcslbm(pcslbm);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());

            List<Map> list = handleService.caseConfirm(param);
            if (list == null || list.size() <= 0 || list.get(0).get("PCSLBM") == null) {
                throw new Exception("未获取到评查案件信息。");
            }

            result = success(list.get(0), "评查案件确认成功");
        } catch (Exception e) {
            super.errMsg("评查案件确认失败", pcslbm, e);
            result = failure(e.getMessage(), "评查案件确认失败");
        }

        return result;
    }

    // 发送评查审批
    @RequestMapping("/sendApprove")
    @Transactional
    public String sendApprove(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setSsrdwbm(getCurrentDwbm());
            param.setSsrdwmc(getCurrentDwmc());
            param.setSsrgh(getCurrentGh());
            param.setSsrxm(getCurrentMC());

            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());


            //评查报审，发送消息给领导
            Message message = new Message();
            message.setJsrgh(param.getSprgh());
            message.setJsrdwbm(param.getSprdwbm());
            message.setXxlx("1");
            message.setGlbmsah(param.getPcslbm());
            message.setDwbm(getCurrentDwbm());
            String xxbt = systemConfigService.getSystemConfigValue("message.ss.bt");
            String  nr= systemConfigService.getSystemConfigValue("message.ss.nr");
            String bgnr = nr
                    .replace("[pcr]", getCurrentMC())
                    .replace("[ajmc]", jbxx.getAJMC());

            message.setXxbt(xxbt);
            message.setXxnr(bgnr);
            messageService.save(message);


            boolean isSuccess = handleService.sendApprove(param);
            result = success(isSuccess, "发送评查审批成功");
        } catch (Exception e) {

            super.errMsg("发送评查审批失败", json, e);
            result = failure(e.getMessage(), "发送评查审批失败");
        }

        return result;
    }

    // 评查案件审批
    @RequestMapping("/dealApprove")
    @Transactional
    public String dealApprove(String json, String appinfo) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setSprdwbm(getCurrentDwbm());
            param.setSprdwmc(getCurrentDwmc());
            param.setSprgh(getCurrentGh());
            param.setSprxm(getCurrentMC());

            if(param.getSpjl().equals("退回")){

                // 调用WebService/RemoveTableRow移除审批人意见
                String path = SystemConfiguration.pcjzPath + param.getPclzdlj();
                Map params = new HashMap();
                params.put("docPath", SystemConfiguration.wzbsPath + path);
                params.put("firstCellValue", param.getSpjsmc()); //审批人意见抬头
                Object data = webServiceUtils.post(SystemConfiguration.webservice + "/RemoveTableRow", params);
                if (data == null) {
                    throw new Exception("调用WebService移除审批人意见失败，返回为空。");
                }
                // 解析XML文档
                List<String> list = webServiceUtils.parseXml(data.toString());
                if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                    throw new Exception("调用WebService移除审批人意见失败，" + list.get(0));
                }
            } else {
                // 调用WebService/RemoveBookMark移除评查员意见书签
                String path = SystemConfiguration.pcjzPath + param.getPclzdlj();
                Map params = new HashMap();
                params.put("docPath", SystemConfiguration.wzbsPath + path);
                params.put("markName", param.getPcyyjsq());
                Object data = webServiceUtils.post(SystemConfiguration.webservice + "/RemoveBookMark", params);
                if (data == null) {
                    throw new Exception("调用WebService移除评查员意见书签失败，返回为空。");
                }
                // 解析XML文档
                List<String> list = webServiceUtils.parseXml(data.toString());
                if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                    throw new Exception("调用WebService移除评查员意见书签失败，" + list.get(0));
                }
            }

            //领导审批后，给评查人发送审批结果通知
            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            Message message = new Message();
            message.setJsrdwbm(jbxx.getPCRDWBM());
            message.setJsrgh(jbxx.getPCRGH());
            message.setGlbmsah(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("message.pcr.bt");
            message.setXxbt(bt);
            String nr = systemConfigService.getSystemConfigValue("message.pcr.nr");
            String replace = nr.replace("[ajmc]", jbxx.getAJMC())
                    .replace("[spr]", getCurrentMC()).replace("[spjl]",param.getSpjl());
            message.setXxnr(replace);
            message.setXxlx("2");
            message.setDwbm(getCurrentDwbm());
            messageService.save(message);

            // 审批
            boolean isSuccess = handleService.dealApprove(param);
            if(!isSuccess)
                throw new Exception("更新审批信息失败。");

            // 送审
            if(StringUtils.isNotEmpty(appinfo)){
                Param_Send paramSend = FastJsonUtils.toObject(Param_Send.class, appinfo);
                paramSend.setSsrdwbm(getCurrentDwbm());
                paramSend.setSsrdwmc(getCurrentDwmc());
                paramSend.setSsrgh(getCurrentGh());
                paramSend.setSsrxm(getCurrentMC());
                paramSend.setSsrxm(getCurrentMC());
                isSuccess = handleService.sendApprove(paramSend);
            }

            result = success(isSuccess, "评查案件审批成功");
        } catch (Exception e) {

            super.errMsg("评查案件审批失败", json, e);
            result = failure(e.getMessage(), "评查案件审批失败");
        }

        return result;
    }

    // 发送给承办人
    @RequestMapping("/sendFeedback")
    @Transactional
    public String sendFeedback(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());

            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("message.cbr.bt");
            String nr = systemConfigService.getSystemConfigValue("message.cbr.nr");
            Message message=new Message();
            message.setJsrdwbm(jbxx.getBPCDWBM());
            message.setJsrgh(jbxx.getBPCGH());
            message.setGlbmsah(param.getPcslbm());
            message.setXxlx("3");
            message.setXxbt(bt);
            message.setDwbm(getCurrentDwbm());
            String replace = nr.replace("[ajmc]", jbxx.getAJMC());
            message.setXxnr(replace);
            messageService.save(message);
            boolean isSuccess = handleService.sendFeedback(param);
            result = success(isSuccess, "发送评查反馈成功");
        } catch (Exception e) {
            super.errMsg("发送评查反馈失败", json, e);
            result = failure(e.getMessage(), "发送评查反馈失败");
        }

        return result;
    }

    // 承办人反馈
    @RequestMapping("/dealFeedbcak")
    @Transactional
    public String dealFeedbcak(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);

            // 调用WebService/RemoveBookMark移除评查员意见书签
            String path = SystemConfiguration.pcjzPath + param.getPclzdlj();
            Map params = new HashMap();
            params.put("docPath", SystemConfiguration.wzbsPath + path);
            params.put("markName", param.getPcyyjsq());
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/RemoveBookMark", params);
            if (data == null) {
                throw new Exception("调用WebService移除评查员意见书签失败，返回为空。");
            }
            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                throw new Exception("调用WebService移除评查员意见书签失败，" + list.get(0));
            }

            //承办人反馈消息给评查人
            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            Message message = new Message();
            message.setJsrdwbm(jbxx.getPCRDWBM());
            message.setJsrgh(jbxx.getPCRGH());
            message.setGlbmsah(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("message.pcrfk.bt");
            message.setXxbt(bt);
            String nr = systemConfigService.getSystemConfigValue("message.pcrfk.nr");
            String replace = nr.replace("[ajmc]", jbxx.getAJMC())
                    .replace("[cbr]", getCurrentMC()).replace("[fkjl]",param.getFkjl());
            message.setXxnr(replace);
            message.setXxlx("4");
            message.setDwbm(getCurrentDwbm());
            messageService.save(message);

            //承办人有意见，发送给部门领导
//            if (StringUtils.isNotEmpty(param.getSprxm())){
//                String bt1 = systemConfigService.getSystemConfigValue("message.bm.bt");
//                String nr1 =  systemConfigService.getSystemConfigValue("message.bm.nr");
//                String replace1 = nr1.replace("[cbr]", getCurrentMC()).replace("[ajmc]", jbxx.getAJMC()).replace("[fkjl]", param.getFkjl());
//                Message message1 = new Message();
//                message1.setJsrdwbm(param.getSprdwbm());
//                message1.setJsrgh(param.getSprgh());
//                message1.setGlbmsah(param.getPcslbm());
//                message1.setXxlx("6");
//                message1.setXxbt(bt1);
//                message1.setXxnr(replace1);
//                message1.setDwbm(getCurrentDwbm());
//                messageService.save(message1);
//            }
            param.setBpc_dwbm(getCurrentDwbm());
            param.setBpc_dwmc(getCurrentDwmc());
            param.setBpc_gh(getCurrentGh());
            param.setBpc_mc(getCurrentMC());
            boolean isSuccess = handleService.dealFeedbcak(param);

            result = success(isSuccess, "承办人反馈成功");
        } catch (Exception e) {

            super.errMsg("承办人反馈失败", json, e);
            result = failure(e.getMessage(), "承办人反馈失败");
        }

        return result;
    }

    // 部门反馈
    @RequestMapping("/dealDeptFeedbcak")
    @Transactional
    public String dealDeptFeedbcak(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());

            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            //承办人反馈消息给评查人
            Message message = new Message();
            message.setJsrdwbm(jbxx.getPCRDWBM());
            message.setJsrgh(jbxx.getPCRGH());
            message.setGlbmsah(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("message.pcrfk.bt");
            message.setXxbt(bt);
            String nr = systemConfigService.getSystemConfigValue("message.pcrfk.nr");
            String replace = nr.replace("[ajmc]", jbxx.getAJMC())
                    .replace("[cbr]", getCurrentMC()).replace("[fkjl]",param.getFkjl());
            message.setXxnr(replace);
            message.setXxlx("4");
            message.setDwbm(getCurrentDwbm());
            messageService.save(message);


            boolean isSuccess = handleService.dealDeptFeedbcak(param);
            result = success(isSuccess, "部门反馈成功");
        } catch (Exception e) {

            super.errMsg("部门反馈失败", json, e);
            result = failure(e.getMessage(), "部门反馈失败");
        }

        return result;
    }

    // 评查结束
    @RequestMapping("/finishEval")
    @Transactional
    public String finishEval(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());

            boolean isSuccess = handleService.finishEval(param);
            result = success(isSuccess, "确认成功");
        } catch (Exception e) {
            super.errMsg("评查结束失败", json, e);
            result = failure(e.getMessage(), "评查结束失败");
        }

        return result;
    }

    // 新增评查意见信息
    @RequestMapping("/addPcyj")
    public String addPcyj(String json) {
        String result = "";

        try {
            Param_Pcyj param = FastJsonUtils.toObject(Param_Pcyj.class, json);
            param.setTcrdwbm(getCurrentDwbm());
            param.setTcrdwmc(getCurrentDwmc());
            param.setTcrgh(getCurrentGh());
            param.setTcrmc(getCurrentMC());
            boolean isSuccess = handleService.addPcyj(param);
            result = success(isSuccess, "新增评查意见信息成功");
        } catch (Exception e) {

            super.errMsg("新增评查意见信息失败", json, e);
            result = failure(e.getMessage(), "新增评查意见信息失败");
        }

        return result;
    }


    @RequestMapping("/get_pcyblist")
    public String get_pcyblist(String json) {
        //设置相应内容
        String result = "";
        try {
            Param_Jbxx param = FastJsonUtils.toObject(Param_Jbxx.class, json);
            param.setPcdwbm(getCurrentDwbm());
            param.setPcr_gh(getCurrentGh());
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_pcyblist(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {

            super.errMsg("获取评查列表失败", json, e);
            result = failure(e.getMessage(), "获取评查列表失败");
        }
        return result;
    }

    @RequestMapping("/validateSfbmyyy")
    public String validate(String pcslbm){
        try {
            if (StringUtils.isNotEmpty(pcslbm)){
                YjExample yjExample = new YjExample();
                yjExample.createCriteria().andPcslbmEqualTo(pcslbm).andPcyjlxEqualTo("3");
                List<Yj> yjs = yjMapper.selectByExample(yjExample);
                if (!CollectionUtils.isEmpty(yjs)){
                    yjs.sort(Comparator.comparing(Yj::getPcyjbh));
                    String spyj = yjs.get(yjs.size() - 1).getPcyjjl();
                    if (SystemConfiguration.bmyybs.equalsIgnoreCase(spyj)){
                        return success(true,"成功");
                    }
                }
            }
            return success(false,"成功");
        }catch (Exception e){
            e.printStackTrace();
        }
       return failure(false,"失败");
    }

    // 撤回评查审批
    @RequestMapping("/cancelApprove")
    @Transactional
    public String cancelApprove(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());

            boolean isSuccess = handleService.cancelApprove(param);
            result = success(isSuccess, "撤回评查审批成功");
        } catch (Exception e) {
            super.errMsg("撤回评查审批失败", json, e);
            result = failure(e.getMessage(), "撤回评查审批失败");
        }

        return result;
    }

    // 退回评查送审
    @RequestMapping("/backApprove")
    @Transactional
    public String backApprove(String json) {
        String result = "";

        try {
            Param_Send param = FastJsonUtils.toObject(Param_Send.class, json);
            param.setCzr_dwbm(getCurrentDwbm());
            param.setCzr_dwmc(getCurrentDwmc());
            param.setCzr_gh(getCurrentGh());
            param.setCzr_mc(getCurrentMC());



            YX_PC_JBXX jbxx = jbxxService.getJbxx(param.getPcslbm());
            //承办人反馈消息给评查人
            Message message = new Message();
            message.setJsrdwbm(jbxx.getPCRDWBM());
            message.setJsrgh(jbxx.getPCRGH());
            message.setGlbmsah(param.getPcslbm());
            String bt = systemConfigService.getSystemConfigValue("messgae.pc.tuihui.bt");
            message.setXxbt(bt);
            String nr = systemConfigService.getSystemConfigValue("messgae.pc.tuihui.nr");
            String replace = nr.replace("[ajmc]", jbxx.getAJMC());
            message.setXxnr(replace);
            message.setXxlx("7");
            message.setDwbm(getCurrentDwbm());
            messageService.save(message);

            boolean isSuccess = handleService.backApprove(param);
            result = success(isSuccess, "退回评查送审成功");
        } catch (Exception e) {
            super.errMsg("退回评查送审失败", json, e);
            result = failure(e.getMessage(), "退回评查送审失败");
        }

        return result;
    }

    // 获取业务部门负责人
    @RequestMapping("/getYwbmfzr")
    public String getYwbmfzr(String spjsbm) {
        String result = "";

        try {
            List<Map> list = handleService.getYwbmfzr(getCurrentDwbm(), getCurrentGh(), spjsbm);
            result = FastJsonUtils.toString(list);
        } catch (Exception e) {
            super.errMsg("获取业务部门负责人失败", spjsbm, e);
            result = failure(e.getMessage(), "获取业务部门负责人失败");
        }

        return result;
    }

}
