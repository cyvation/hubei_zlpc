package com.start.boot.service;

import com.start.boot.domain.MenuButton;
import com.start.boot.domain.MenuButtonWithBLOBs;
import com.start.boot.query.MenuQuery;

import java.util.List;

/**
 * 菜单
 */
public interface MenuButtonService {

    List<MenuButton> selectMenuButtonByQuery(MenuQuery menuQuery) throws Exception;

    MenuButtonWithBLOBs save(MenuButtonWithBLOBs menuButton);

}
