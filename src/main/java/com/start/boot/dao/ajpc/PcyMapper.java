package com.start.boot.dao.ajpc;


import com.start.boot.domain.Param_Pcy;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/30.
 */
@Repository
public interface PcyMapper {
   List<Param_Pcy> getPcyByPchdbmAndpchdbm(@Param("pchdbm") String pchdbm, @Param("pczbm") String pczbm);

   List<Map>getPcyInfo(@Param("pchdbm")String pchdbm);
}
