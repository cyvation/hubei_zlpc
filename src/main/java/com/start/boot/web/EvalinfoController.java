package com.start.boot.web;

import com.start.boot.service.EvalinfoService;
import com.start.boot.support.utils.EasyUIHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/11/12.
 */
@RestController
@RequestMapping("/jzwj")
public class EvalinfoController extends ArchivesSystemBaseController {
    @Autowired
    private EvalinfoService evalinfoService;

    @RequestMapping("/getGlajs")
    public String getGlajs() {
        String result = "";
        String dwbm = getCurrentDwbm();
        String gh = getCurrentGh();
        try {
            List<Map> list = evalinfoService.getPcjz(dwbm,gh);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list,"DM","FDM","MC","-1");
        } catch (Exception e) {
            super.errMsg("获取评查卷宗文件失败","单位：" + dwbm + "，拟作人：" + gh,e);
            result = failure(e.getMessage(), "获取评查卷宗文件失败");
        }
        return result;
    }
}
