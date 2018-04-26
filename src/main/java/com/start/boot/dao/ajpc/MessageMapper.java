package com.start.boot.dao.ajpc;

import com.start.boot.domain.Message;
import com.start.boot.domain.MessageExample;
import com.start.boot.domain.MessageKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(MessageKey key);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(MessageKey key);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    void updateMessageZt(@Param("glbmsah") String glbmsah);
    void updateMessageFszt(@Param("xxlx") String xxlx,@Param("dwbm")String dwbm,@Param("gh")String gh);

    void batchUpdateXxzt(@Param("ids")List<String> ids);
}