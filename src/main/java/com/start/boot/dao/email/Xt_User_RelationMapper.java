package com.start.boot.dao.email;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by  on 2017/10/30.
 */
@Repository
public interface Xt_User_RelationMapper {

    String getAppId(@Param("dwbm") String dwbm,@Param("gh")  String gh);

    String getWeixin(@Param("appid") String appid );

}
