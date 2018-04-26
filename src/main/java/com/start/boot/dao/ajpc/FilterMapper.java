package com.start.boot.dao.ajpc;

import com.start.boot.domain.ParamSx;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/11/2.
 */
@Repository
public interface FilterMapper {

    void getSxgz(Map map);

    void getSxgzMonitor(Map map);

    void getHdsxgz(Map map);

    void getSjsx(Map map);

    void get_sjsx_bm(Map map);

    void get_sjsx_jcg(Map map);

    void getPcjk(Map map);

    void getPcjl(Map map);

    void getPczt(Map map);

    void getBmwpcList(Map map);

    void getCbrwpcList(Map map);

    void uptSjpcsx(Map map);

    void uptZxpcsx(Map map);

    void getBmList(Map map);

    void getAllBm(Map map);

    void getPcbmj(Map paramSx);

    Map getAjxxByBmsah(Map param);
}
