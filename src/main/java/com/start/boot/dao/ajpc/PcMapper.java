package com.start.boot.dao.ajpc;


import com.start.boot.domain.Param_Ryk;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface PcMapper {
    void getPcfl(Map map);

    void getPckry(Map map);

    void getZzjgry(Map map);

    void add_pcryk(Map map);

    void del_rykry(Map map);

    void getPchd(Map map);

    void addPchd(Map map);

    void updPchd(Map map);

    void delPchd(Map map);

    void addPcfz(Map map);

    void updPcfz(Map map);

    void valPcfz(Map map);

    void delPcfz(Map map);

    void addXzry(Map map);

    void updXzry(Map map);

    void valXzry(Map map);

    void delXzry(Map map);

    void getPckryAll(Map map);

    void addPcaj(Map map);

    void assignPcz(Map map);

    void assignPcr(Map map);

    void addJzwj(Map map);

    void updJzwj(Map map);

    void delJzwj(Map map);

    void delJzwjByPczybm(Map map);

    void getPcfaws(Map map);

    void getPcaj(Map map);

    void delPcaj(Map map);

    void getPcz(Map map);

    void getPczry(Map map);

    void updPcr(Map map);

    void getHdsp(Map map);

    void addPcfaps(Map map);

    void uptDealFasp(Map map);

    void startPchd(Map map);

    void finishPchd(Map map);

    void getHdztBM(Map map);

    void getPcsp(Map map);

    List<Map> getYwtxTree();

    List<Map> getJsmc(Map map);

    void backspace(Map map);

    void addBackspaceLog(Map map);
    void delPcajLog(Map map);

    int htPcaj(String pcslbm);

    int htRZ(Map map);

    List<Map> getPcbg(Map map);

    List<Map> getPcry(Param_Ryk ryk);
}
