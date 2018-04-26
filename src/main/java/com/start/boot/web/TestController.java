package com.start.boot.web;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.QueryMapper;
import com.start.boot.domain.Yx_Pc_PcxFl;
import com.start.boot.service.PcxFlService;
import com.start.boot.service.XtDmFldmService;
import com.start.boot.support.utils.EasyUIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SystemConfiguration systemConfiguration;
    @Autowired
    private PcxFlService pcxFlService;

    @Autowired
    EasyUIUtils easyUIUtils;


    @Autowired
    QueryMapper queryMapper;

    @Autowired
    XtDmFldmService xtDmFldmService;


    @RequestMapping("/refresh")
    public String test() throws Exception {
        systemConfiguration.init();
        return "刷新数据配置成功!";
    }
    @RequestMapping("/test1")
    public Object test1(String pcslbm,String pcxflbm)throws Exception{

      return  pcxFlService.getZdpcxResult(pcslbm,pcxflbm);


    }
    @RequestMapping("/getPcxFl")
    public void getPcxFl() throws Exception {

        List<Yx_Pc_PcxFl> pcxFl = pcxFlService.getPcxFl("3700002018PSL001000002");
        EasyUIUtils<Yx_Pc_PcxFl> easyUIUtils = new EasyUIUtils();
        easyUIUtils.buildEasyUiTree(pcxFl,"pcslbm","pcxflmc","pcxflfbm","pcxflbm",null);

    }


}
