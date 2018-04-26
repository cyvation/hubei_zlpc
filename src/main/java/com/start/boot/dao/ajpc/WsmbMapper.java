package com.start.boot.dao.ajpc;

import com.start.boot.domain.Wsmb;
import com.start.boot.domain.WsmbExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WsmbMapper {
    long countByExample(WsmbExample example);

    int deleteByExample(WsmbExample example);

    int deleteByPrimaryKey(String wsmbbh);

    int insert(Wsmb record);

    int insertSelective(Wsmb record);

    List<Wsmb> selectByExampleWithBLOBs(WsmbExample example);

    List<Wsmb> selectByExample(WsmbExample example);

    Wsmb selectByPrimaryKey(String wsmbbh);

    int updateByExampleSelective(@Param("record") Wsmb record, @Param("example") WsmbExample example);

    int updateByExampleWithBLOBs(@Param("record") Wsmb record, @Param("example") WsmbExample example);

    int updateByExample(@Param("record") Wsmb record, @Param("example") WsmbExample example);

    int updateByPrimaryKeySelective(Wsmb record);

    int updateByPrimaryKeyWithBLOBs(Wsmb record);

    int updateByPrimaryKey(Wsmb record);
}