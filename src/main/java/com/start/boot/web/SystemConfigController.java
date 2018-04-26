package com.start.boot.web;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.support.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <h3>系统配置控制器</h3>
 *
 * @author 符黄辰君
 * @since 2017/7/25.
 */
@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController extends ArchivesSystemBaseController {
    /**
     * 获取最新版配置
     *
     * @return
     */
    @RequestMapping("/getPz")
    public String getNewest() throws Exception {
        HashMap<String, String> result = new HashMap<>();
        result.put("FALVHTML",SystemConfiguration.falvHtml);
        result.put("ALKHTML",SystemConfiguration.alkHtml);
        result.put("DJDWBM",SystemConfiguration.djdwbm);
        return FastJsonUtils.toString(result);
    }

}