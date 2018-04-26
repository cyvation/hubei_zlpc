package com.start.boot.service;


import com.start.boot.domain.Param_CaseInfo;

import java.util.List;
import java.util.Map;

public interface CaseInfoService {

    List<Map> getGlajs(String pcslbm, String bmsah, String param) throws Exception;

    List<Map> getDocFiles(String pcslbm, String bmsah, String param) throws Exception;

    List<Map> getDossierFiles(String pcslbm, String bmsah, String param) throws Exception;

    Param_CaseInfo getCaseInfo(String pcslbm, String bmsah) throws Exception;
}
