package com.start.boot.service;

import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;

import java.util.List;
import java.util.Map;

public interface OfflineService {

    /**
     * 获取评查标准
     * @return
     */
    List<Map> getPcbz(String pcflbm, String dwbm)throws Exception;
    /**
     * 获取评查标准
     * @return
     */
    List<Map> getAjmb(String pcflbm, String pcmbbm, String dwbm)throws Exception;
    /**
     * 获取评查标准
     * @return
     */
    List<Map> getPcTree(String mbNo, String pcflbm)throws Exception;
    /**
     * 保存案件评查信息
     * @return
     */
    int saveOfflineInfo(List<Yx_Pc_PcxFlVo> vo, Map map)throws Exception;

    Map loadOfflineList(Map param) throws Exception;

    List<Map> getPcjgInfo(String pcslbm, String dw)throws Exception;

    Map getPcAjInfo(Map param) throws Exception;

    int updatePcaj(String pcslbm, String dw)throws Exception;

    int isOnAj(Map map)throws Exception;
}
