package com.start.boot.service;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/11/4.
 */
public interface HandleService {

    Param_Pager get_pclist(Param_sp param_sp) throws Exception;

    Param_Pager get_splst(Param_sp param_sp) throws Exception;

    Param_Pager get_spxx(Param_sp param_sp) throws Exception;

    /*List<Map> get_spxx(Param_sp param_sp) throws Exception;*/

    Param_Pager get_cbrfklist(Param_Jbxx param_jbxx) throws Exception;

    Param_Pager get_cbbmfklist(Param_Jbxx param_jbxx) throws Exception;

    List<Map> getDocFiles(Param_Pc param) throws Exception;

    Param_Pager getPchdList(Param_HdList param_hd)throws Exception;

    List<Map> caseConfirm(Param_Send param_send)throws Exception;

    Param_Pager get_pcyblist(Param_Jbxx param_jbxx) throws Exception;

    boolean sendApprove(Param_Send param_send)throws Exception;

    boolean dealApprove(Param_Send param_send)throws Exception;

    boolean sendFeedback(Param_Send param_send)throws Exception;

    boolean dealFeedbcak(Param_Send param_send)throws Exception;

    boolean dealDeptFeedbcak(Param_Send param_send)throws Exception;

    boolean finishEval(Param_Send param_send)throws Exception;

    boolean addPcyj(Param_Pcyj param_pcyj)throws Exception;

    boolean cancelApprove(Param_Send param)throws Exception;

    boolean backApprove(Param_Send param)throws Exception;

    List<Map> getYwbmfzr(String dwbm, String gh, String spjsbm)throws Exception;

}
