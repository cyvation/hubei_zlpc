package com.start.boot.dao.ajpc;


import java.util.List;

import com.start.boot.domain.YxRzCz;
import com.start.boot.domain.YxRzCzExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YxRzCzMapper {
    long countByExample(YxRzCzExample example);

    int deleteByExample(YxRzCzExample example);

    int insert(YxRzCz record);

    int insertSelective(YxRzCz record);

    List<YxRzCz> selectByExample(YxRzCzExample example);

    int updateByExampleSelective(@Param("record") YxRzCz record, @Param("example") YxRzCzExample example);

    int updateByExample(@Param("record") YxRzCz record, @Param("example") YxRzCzExample example);
}