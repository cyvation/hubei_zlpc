package com.start.boot.service;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.ParamSx;
import com.start.boot.domain.Param_Ajsx;
import com.start.boot.domain.Param_Pcjk;
import com.start.boot.pojo.vo.PcxVoList;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/11/2.
 */
public interface FilterService {

    List<Map> getSxgz(String dwbm, String pcflbm, String sslb) throws Exception;

    List<Map> getSxgzMonitor(String dwbm, String pcflbm) throws Exception;

    List<Map> getHdsxgz(String gzdwbm, String pcflbm, String pchdbm) throws Exception;

    ParamSx getSjsx(ParamSx paramSx) throws Exception;


    ParamSx get_sjsx_bm(ParamSx paramSx) throws Exception;


    ParamSx get_sjsx_jcg(ParamSx paramSx) throws Exception;

    Param_Pager getPcjk(Param_Pcjk pcjkParam) throws Exception;

    List<Map> getPcjl() throws Exception;

    List<Map> getPczt() throws Exception;

    Param_Pager getBmwpcList(ParamSx param) throws Exception;

    Param_Pager getCbrwpcList(ParamSx param) throws Exception;

    Param_Ajsx uptSjpcsx(Param_Ajsx param) throws Exception;

    Param_Ajsx uptZxpcsx(Param_Ajsx param) throws Exception;
    /**
     *
     * 根据评查受理编码和评查项分类编码获取
     * @param pcslbm  评查受理编码
     * @param pcxflfbm 评查项分类编码
     * @return
     */
    PcxVoList getYxPcFl(String pcslbm, String pcxflfbm);

    List<Map> getBmList(String dwbm, String pcflbm) throws Exception;

    List<Map> getAllBm(String currentDwbm) throws Exception;

    List<Map>  getPcbmj(ParamSx paramSx) throws Exception;

    Map getAjxxByBmsah(Map param) throws  Exception;

    Map getTyywAjxxByBmsah(Map param) throws  Exception;

    ParamSx getSjsxAdvance(ParamSx paramSx) throws Exception;

    List<Map> getSxgzByPcflbmAndYwtx(Map param) throws Exception;
}
