package com.start.boot.dao.ajpc;

import com.start.boot.domain.Jsgnfp;
import com.start.boot.domain.Yx_Pc_Pcx;
import com.start.boot.pojo.vo.Yx_Pc_PcxFlVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/25.
 */
@Repository
public interface OfflineMapper {

    List<Map> getPcbz(Map pcflbm);
    List<Map> getAjmb(Map map);
    List<Map> getPcTree(Map mbNo);
    int saveAnJian(Map map);
    int savePCFL(Yx_Pc_PcxFlVo map);
    int savePCX(Yx_Pc_Pcx map);
    List<Map> getPcslBm(Map map);
    Map getDwJc(Map map);
    List<Map> loadOfflineList(Map map);
    Map loadOfflineCount(Map map);
    List<Map> getPcflInfo(Map mbNo);
    List<Map> getPcflxInfo(Map mbNo);
    Map getPcAjInfo(Map map);
    int updatePCFL(Yx_Pc_PcxFlVo map);
    int updatePCX(Yx_Pc_Pcx map);
    int updatePcajjg(Map map);
    int updatePcaj(Map map);
    List<Map> isOnAj(Map map);
    List<Map> loadOfflineListExcel(Map map);
}
