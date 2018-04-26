package com.start.boot.service;


import com.start.boot.common.Param_Pager;
import com.start.boot.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
public interface AccountService {

    User signIn(String dwbm, String username, String password) throws Exception;

    List<FunctionFl> getFunction(String dwbm, String gh, String sflx) throws Exception;

    List<Map> getKjcd(String currentDwbm, String gh) throws Exception;

    Integer getXxsl(String jsrdwbm, String jsrgh) throws Exception;

    Param_Pager getXxlb(Param_Xxlb param) throws Exception;

    List<Map> getTzgg(String dwbm) throws Exception;

    boolean addKjcd(String dwbm, String gh, List<Param_KJCD> list) throws Exception;

    boolean updXxlb(String dwbm, String xxxhj) throws Exception;

    boolean updatePassword(String dwbm, String gh, String pass_old, String pass_new) throws Exception;

    Integer getTaskCount(String dwbm, String gh, String gnbm) throws Exception;

    List<Jsbm> getJsbm(String dwbm, String gh) throws Exception;

    User signByCaid(String caid) throws Exception;

    List<Map> getGncsByDwbm(User user, String gnbm) throws Exception;
}
