package com.start.boot.service;

import java.util.Map;


/**
 *  评查报告
 */
public interface ReportService {
    Map getPcbgMb(String pcflbm, String wsmblb);
    String getMaxPczybm(String pczybm);
}


