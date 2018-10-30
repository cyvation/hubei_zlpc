package com.start.boot.dao.ajpc;

import com.start.boot.domain.YxPcJzwj;
import com.start.boot.domain.YxPcSp;
import com.start.boot.pojo.vo.BgAjVo;
import com.start.boot.pojo.vo.BgAjlb;
import com.start.boot.query.ReportQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2018/10/26.
 */
@Repository
public interface PcReportMapper {

    List<Map> getPcbgMb(@Param("pcflbm") String pcflbm, @Param("wsmblb") String wsmblb);

    List<Map> getBgKt(YxPcJzwj yxPcJzwj);

    String getPcflAj(YxPcJzwj yxPcJzwj);

    String getPcjlAj(YxPcJzwj yxPcJzwj);

    List<Map> getCount(YxPcJzwj yxPcJzwj);

    String getbmAj(YxPcJzwj yxPcJzwj);

    String getJlAj(YxPcJzwj yxPcJzwj);

    List<Map> specialKz(YxPcJzwj yxPcJzwj);

    List<BgAjlb> getAjlb(YxPcJzwj yxPcJzwj);

    List<BgAjVo> getwtx(YxPcJzwj yxPcJzwj);

    List<Map> getReportList(ReportQuery reportQuery);

    List<Map> getReportHd(ReportQuery reportQuery);

    List<Map> getPcsp(@Param("spjsbm") String spjsbm,@Param("dwbm") String dwbm, @Param("gh") String gh);

    int updatePcsp(YxPcSp yxPcSp);

    void sendPcbgSp(Map map);

    void sendDoc(String jzwjbh);

    void backDoc(YxPcSp yxPcSp);

    void addCzrz(YxPcSp yxPcSp);

    void updateJzwj(YxPcJzwj yxPcJzwj);

    List<Map> getPcspbgInfo(YxPcSp yxPcSp);
}
