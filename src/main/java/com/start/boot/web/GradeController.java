package com.start.boot.web;

import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.*;
import com.start.boot.service.GradeService;
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
 * 评分表控制器
 */
@RestController
@RequestMapping("/grade")
public class GradeController extends ArchivesSystemBaseController {

    @Autowired
    private GradeService gradeService;

    /**
     * 获取评分表
     *
     * @pcslbm
     * @return
     */
    @RequestMapping("/getMarkSheet")
    public String getMarkSheet(String pcslbm) {

        // 返回结果
        String result = "";

        try {
            List<Map> list = gradeService.getMarkSheet(pcslbm);
            result = EasyUIHelper.buildDataGridDataSource(list, list.size());
        } catch (Exception e) {

            super.errMsg("获取评分表失败", pcslbm, e);
            result = failure(e.getMessage(), "获取评分表失败");
        }

        return result;
    }

    /**
     * 获取自动评查项分数
     *
     * @json
     * @return
     */
    @RequestMapping("/getAutoScoreResult")
    public String getAutoScoreResult(String json) {

        // 返回结果
        String result = "";

        try {

        } catch (Exception e) {

            super.errMsg("获取自动评查项分数失败", json, e);
            result = failure(e.getMessage(), "获取自动评查项分数失败");
        }

        return result;
    }

    /**
     * 保存评查项分数
     *
     * @json
     * @return
     */
    @RequestMapping("/saveGradeScore")
    public String saveGradeScore(String pcslbm, String json) {

        // 返回结果
        String result = "";

        try {

        } catch (Exception e) {

            super.errMsg("保存评查项分数失败", pcslbm + ";" + json, e);
            result = failure(e.getMessage(), "保存评查项分数失败");
        }

        return result;
    }

    /**
     * 获取常见问题列表
     *
     * @json
     * @return
     */
    @RequestMapping("/getProblemList")
    public String getProblemList(String json) {

        // 返回结果
        String result = "";

        try {

        } catch (Exception e) {

            super.errMsg("获取常见问题列表失败", json, e);
            result = failure(e.getMessage(), "获取常见问题列表失败");
        }

        return result;
    }

    /**
     * 获取自动评查项扣分理由
     *
     * @json
     * @return
     */
    @RequestMapping("/getAutoScoreReason")
    public String getAutoScoreReason(String pcslbm, String pcxbm) {

        // 返回结果
        String result = "";

        try {

        } catch (Exception e) {

            super.errMsg("获取自动评查项扣分理由失败", pcslbm, e);
            result = failure(e.getMessage(), "获取自动评查项扣分理由失败");
        }

        return result;
    }
}
