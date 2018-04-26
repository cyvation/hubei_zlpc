package com.start.boot.service.impl;

import com.start.boot.validate.MenuButtonXxzyValidate;
import com.start.boot.dao.ajpc.MenuButtonMapper;
import com.start.boot.domain.MenuButton;
import com.start.boot.domain.MenuButtonExample;
import com.start.boot.domain.MenuButtonWithBLOBs;
import com.start.boot.query.MenuQuery;
import com.start.boot.service.MenuButtonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MenuButtonServiceImpl implements MenuButtonService {

    @Autowired
    MenuButtonMapper menuButtonMapper;

    @Autowired
    MenuButtonXxzyValidate menuButtonXxzy;

    @Override
    public List<MenuButton> selectMenuButtonByQuery(MenuQuery menuQuery) throws Exception {
        MenuButtonExample menuButtonExample = new MenuButtonExample();
        MenuButtonExample.Criteria criteria = menuButtonExample.createCriteria();
        String czlxbm = menuQuery.getCzlxbm();
        String pcflbm = menuQuery.getPcflbm();
        String lcmbbm = menuQuery.getLcmbbm();
        String lcjdbh = menuQuery.getLcjdbh();
        if (StringUtils.isNotEmpty(czlxbm)){
            criteria.andCzlxbmEqualTo(czlxbm);
        }
        if (StringUtils.isNotEmpty(pcflbm)){
            criteria.andPcflbmEqualTo(pcflbm);
        }
        if (StringUtils.isNotEmpty(lcmbbm)){
            criteria.andLcmbbmEqualTo(lcmbbm);
        }
        if (StringUtils.isNotEmpty(lcjdbh)){
            criteria.andLcjdbhEqualTo(lcjdbh);
        }
        menuButtonExample.setOrderByClause("DZXH");
        List<MenuButton> menuButtons = menuButtonMapper.selectByExample(menuButtonExample);
        ArrayList<MenuButton> result = new ArrayList<>();
        for (MenuButton menuButton : menuButtons) {
            String xycx = menuButton.getXycx();
            if (StringUtils.isNotEmpty(xycx)){
                try {
                    Method method = menuButtonXxzy.getClass().getDeclaredMethod(xycx,MenuQuery.class);
                    Object invoke = method.invoke(menuButtonXxzy, menuQuery);
                    if (invoke instanceof Boolean){
                        Boolean flag= (Boolean) invoke;
                        if (flag){
                            result.add(menuButton);
                        }
                    }
                }catch (NoSuchMethodException e){
                    e.printStackTrace();
                    throw new RuntimeException("获取验证方法失败");
                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException("执行验证方法失败");
                }
            }else {
                result.add(menuButton);
            }

        }
        return result;
    }

    @Override
    public MenuButtonWithBLOBs save(MenuButtonWithBLOBs menuButton) {
         menuButton.setDzbh(UUID.randomUUID().toString());
         menuButtonMapper.insertSelective(menuButton);
         return menuButton;
    }
}
