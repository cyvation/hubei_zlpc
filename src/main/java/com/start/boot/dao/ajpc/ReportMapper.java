package com.start.boot.dao.ajpc;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface ReportMapper {

    Map getPcbgMb(@Param("pcflbm") String pcflbm, @Param("wsmblb") String wsmblb);

    String getMaxPczybm(@Param("pczybm")String pczybm);
}
