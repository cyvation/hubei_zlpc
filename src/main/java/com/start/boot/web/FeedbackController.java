package com.start.boot.web;

import com.start.boot.common.Param_Pager;
import com.start.boot.domain.Param_Jbxx;
import com.start.boot.service.HandleService;
import com.start.boot.support.utils.EasyUIHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 评查反馈控制器
 * Created by lei on 2017/11/4.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController extends ArchivesSystemBaseController{


    @Autowired
    private HandleService handleService;


    /**
     *  承办人反馈列表
     * @param
     * @return
     */
    @RequestMapping("/get_cbrfklist")
    public String get_cbrfklist() {
        //Param_Pc param_pcg
        //设置相应内容
        String result = "";

        Param_Jbxx param = new Param_Jbxx();

        try {
            param.setBpc_dwbm(getCurrentDwbm());
            param.setBpc_gh(getCurrentGh());
            /*param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));*/
            Param_Pager data =  handleService.get_cbrfklist(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(),data.getCount());
        } catch (Exception e) {
            super.errMsg("承办人反馈列表失败",param.toString(),e);
            result = failure(e.getMessage(), "承办人反馈列表失败");
        }
        return result;
    }


    /**
     *  承办部门反馈列表
     * @param
     * @return
     */
    @RequestMapping("/get_cbbmfklist")
    public String get_cbbmfklist() {
        //Param_Pc param_pc
        //设置相应内容
        String result = "";

        Param_Jbxx param = new Param_Jbxx();
        try {
            param.setBpc_dwbm(getCurrentDwbm());
            param.setBpc_gh(getCurrentGh());
            param.setPage(parsePage());
            param.setRows(parseRows());
            Param_Pager data =  handleService.get_cbbmfklist(param);

            result = EasyUIHelper.buildDataGridDataSource(data.getList(),data.getCount());
        } catch (Exception e) {
            super.errMsg("承办部门反馈列表失败",param.toString(),e);
            result = failure(e.getMessage(), "承办部门反馈列表失败");
        }

        return result;
    }


}
