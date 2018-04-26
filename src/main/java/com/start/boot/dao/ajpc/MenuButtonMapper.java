package com.start.boot.dao.ajpc;

import com.start.boot.domain.MenuButton;
import com.start.boot.domain.MenuButtonExample;
import com.start.boot.domain.MenuButtonWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuButtonMapper {
    long countByExample(MenuButtonExample example);

    int deleteByExample(MenuButtonExample example);

    int deleteByPrimaryKey(String dzbh);

    int insert(MenuButtonWithBLOBs record);

    int insertSelective(MenuButtonWithBLOBs record);

    List<MenuButtonWithBLOBs> selectByExampleWithBLOBs(MenuButtonExample example);

    List<MenuButton> selectByExample(MenuButtonExample example);

    MenuButtonWithBLOBs selectByPrimaryKey(String dzbh);

    int updateByExampleSelective(@Param("record") MenuButtonWithBLOBs record, @Param("example") MenuButtonExample example);

    int updateByExampleWithBLOBs(@Param("record") MenuButtonWithBLOBs record, @Param("example") MenuButtonExample example);

    int updateByExample(@Param("record") MenuButton record, @Param("example") MenuButtonExample example);

    int updateByPrimaryKeySelective(MenuButtonWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MenuButtonWithBLOBs record);

    int updateByPrimaryKey(MenuButton record);
}