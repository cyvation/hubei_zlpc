package com.start.boot.service;

import com.start.boot.domain.YxPcJzwj;
import com.start.boot.domain.YxPcSp;
import com.start.boot.query.ReportQuery;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/10/26.
 */
public interface PcReportService {
    List<Map> getPcbgMb(String pcflbm, String wsmblb);

    String generateDoc(YxPcJzwj yxPcJzwj) throws Exception;

    void delPcbg(YxPcJzwj yxPcJzwj);

    List<Map> getReportList(ReportQuery reportQuery);

    List<Map> getReportHd(ReportQuery reportQuery);

    List<Map> getPcsp(String spjsbm, String currentDwbm, String currentGh);

    void updateSpyj(YxPcSp yxPcSp);

    YxPcSp getSpInfo(String pcspbm);

    boolean sendPcbgSp(YxPcSp yxPcSp) throws Exception;

    void sendDoc(String jzwjbh);

    void backDoc(YxPcSp yxPcSp, YxPcJzwj yxPcJzwj);

    void updateJzwj(YxPcJzwj yxPcJzwj);

    YxPcJzwj getPcbgDetailInfo(YxPcJzwj yxPcJzwj);

    List<Map> getPcspbgInfo(YxPcSp yxPcSp);
}
