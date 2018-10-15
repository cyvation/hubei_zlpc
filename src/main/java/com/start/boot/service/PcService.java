package com.start.boot.service;

import com.start.boot.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
public interface PcService {
    public List<Map> getPcfl() throws Exception;

    List<Map> getPchd(Param_Hd param) throws Exception;

    Param_Ryk getPckryAll(Param_Ryk param) throws Exception;

    Param_Pcfa addPcfa(Param_Pcfa param) throws Exception;

    boolean updPchd(Param_Pcfa param) throws Exception;

    String addPcfz(Param_Pcz param) throws Exception;

    String valPcfz(Param_Pcz param) throws Exception;

    boolean assignCase(User userInfo, List<Param_Pcaj> list) throws Exception;

    boolean delPcfz(Param_Pcz param) throws Exception;

    String valXzry(Param_Pcy param) throws Exception;

    boolean delPchd(String pchdbm) throws Exception;

    Param_Ryk getPckry(Param_Ryk ryk) throws Exception;

    Param_Ryk getZzjgry(Param_Ryk ryk) throws Exception;

    List<Param_Ryk> add_pcryk(List<Param_Ryk> list,String dwbm) throws Exception;

    List<Param_Ryk> del_rykry(List<Param_Ryk> list,String dwbm) throws Exception;

    Param_Jzwj addJzwj(Param_Jzwj param) throws Exception;

    Param_Jzwj updJzwj(Param_Jzwj param) throws Exception;

    Param_Jzwj delJzwj(Param_Jzwj param) throws Exception;

    Param_Jzwj delJzwjByPczybm(Param_Jzwj param) throws Exception;

    List<Map> getHdsp(String dwbm, String gh) throws Exception;

    boolean addPcfaps(Param_Pcsp pcspParam) throws Exception;

    boolean uptDealFasp(Param_Pcsp pcspParam) throws Exception;

    boolean startPchd(Param_Hd param) throws Exception;

    boolean finishPchd(Param_Hd param) throws Exception;

    Param_Pchdzt getHdztBM(String pchdbm) throws Exception;

    List<Map> getPcsp(Map map) throws Exception;

    List<Map> getYwtxTree();

    List<Map> getJsmc(Map map) throws  Exception;

    void backspace(Map map);

    List<Map> getPcbg(Map map) throws Exception;

    Param_Ryk getPcry(Param_Ryk ryk);
}
