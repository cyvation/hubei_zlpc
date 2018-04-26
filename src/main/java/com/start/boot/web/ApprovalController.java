package com.start.boot.web;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.Param_sp;
import com.start.boot.service.HandleService;
import com.start.boot.support.utils.EasyUIHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评查审批控制器
 * Created by lei on 2017/11/4.
 */
@RestController
@RequestMapping("/approval")
public class ApprovalController extends ArchivesSystemBaseController{

    @Autowired
    private HandleService handleService;



    /**
     * 评查审批案件列表
     * @param
     * @return
     */
    @RequestMapping("/get_splst")
    public String get_splst() {
        //Param_Pc param_pc
        //设置相应内容
        String result = "";

        Param_sp param = new Param_sp();

        try {
            param.setSprdwbm(getCurrentDwbm());
            param.setSprgh(getCurrentGh());
            param.setKeyword(getParameter("keyword"));
//            param.setPage(parsePage(getParameter("page")));
//            param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = handleService.get_splst(param);
            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("获取评查审批案件列表失败",param.toString(),e);
            result = failure(e.getMessage(), "获取评查审批案件列表失败");
        }

        return result;
    }

}
