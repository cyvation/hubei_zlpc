package com.start.boot.web;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.Param_Tzgg;
import com.start.boot.domain.Tzgg;
import com.start.boot.service.ResService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FastJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 评查资料控制器--通知公告
 * Created by lei on 2017/10/31.
 */
@RestController
@RequestMapping("/resource")
public class ResController extends ArchivesSystemBaseController{


    @Autowired
    private ResService resService;

    /**
     * 获取通知公告
     * @return
     */
    @RequestMapping("/getTzgg")
    public String getAnnounce(String json) {
        // 响应内容
        String result = "";

        try {
            Param_Tzgg param = FastJsonUtils.toObject(Param_Tzgg.class, json);
            param.setDwbm(getCurrentDwbm());
            param.setPage(parsePage(getParameter("page")));
            param.setRows(parseRows(getParameter("rows")));

            Param_Pager data = resService.selectTzgg(param);

            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("获取通知公告列表失败", json, e);
        }

        return  result;
    }

    /**
     * 新增通知公告表信息
     * @return
     */
    @RequestMapping("/addTzgg")
    public String addTzgg(String json) throws ParseException {
        // 响应内容
        String result = "";

        try {
            Tzgg param = FastJsonUtils.toObject(Tzgg.class, json);
            param.setFbrdwbm(getCurrentDwbm());
            param.setFbrdwmc(getCurrentDwmc());
            param.setFbrgh(getCurrentGh());
            param.setFbrxm(getCurrentMC());

            boolean isSuccess = resService.addTzgg(param);

            result = success(isSuccess, "新增通知公告表信息成功");
        } catch (Exception e) {
            super.errMsg("新增通知公告表信息失败", json, e);
            result = failure(e.getMessage(), "新增通知公告表信息失败");
        }

        return  result;
    }

    /**
     *更新通知公告分类
     * @return
     */
    @RequestMapping("/UpdTzgg")
    public String updTzgg(String json) {
        // 响应内容
        String result = "";

        try {
            Tzgg param = FastJsonUtils.toObject(Tzgg.class, json);

            boolean isSuccess = resService.updTzgg(param);

            result = success(isSuccess, "更新通知公告分类成功");
        } catch (Exception e) {
            super.errMsg("更新通知公告分类失败", json, e);
            result = failure(e.getMessage(), "更新通知公告分类失败");
        }

        return  result;
    }

    /**
     *删除
     * @return
     */
    @RequestMapping("/delTzgg")
    public String delTzgg(String bh) {

        // 响应内容
        String result = "";

        try {
            boolean isSuccess = resService.delTzgg(bh);

            result = success(isSuccess, "删除成功");
        } catch (Exception e) {
            super.errMsg("删除失败", bh, e);
            result = failure(e.getMessage(), "删除失败");
        }

        return  result;
    }
}
