package com.start.boot.web;

import com.start.boot.common.MessageResult;
import com.start.boot.domain.MenuButton;
import com.start.boot.domain.MenuButtonWithBLOBs;
import com.start.boot.domain.Wsmb;
import com.start.boot.pojo.vo.DbrwVo;
import com.start.boot.query.DbrwQuery;
import com.start.boot.query.MenuQuery;
import com.start.boot.service.DbrwService;
import com.start.boot.service.MenuButtonService;
import com.start.boot.service.WsmbService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController extends ArchivesSystemBaseController {

    @Autowired
    MenuButtonService menuButtonService;
    @Autowired
    DbrwService dbrwService;

    @Autowired
    WsmbService wsmbService;

    @GetMapping("/queryMenuButton")
    @ApiOperation("查询要显示的菜单按钮")
    public MessageResult query(MenuQuery query) throws Exception{
        query.setDwbm(getCurrentDwbm());
        query.setGh(getCurrentGh());
        List<MenuButton> menuButtons = menuButtonService.selectMenuButtonByQuery(query);
        return new MessageResult("操作成功",200,menuButtons);
    }

    @PostMapping("/save")
    @ApiOperation("保存")
    public void save(@RequestBody MenuButtonWithBLOBs menuButton)throws Exception{
        menuButtonService.save(menuButton);
    }

    @GetMapping("/queryWsmb")
    @ApiOperation("根据评查分类编码查询文书模板")
    public MessageResult queryWsmb(String pcflbm,String wsmblb)throws Exception{
        List<Wsmb> wsbmList = wsmbService.getWsbmList(pcflbm,wsmblb);
        return new MessageResult("操作成功",200,wsbmList);

    }

    @ApiOperation("获取工作桌面所有菜单项")
    @GetMapping("/getDbrwMenu")
    public MessageResult getDbrwMenu() throws Exception {
        DbrwQuery dbrwQuery = new DbrwQuery(getCurrentDwbm(), getCurrentGh());
        List<DbrwVo> allDeskTopMenu = dbrwService.getAllDeskTopMenu(dbrwQuery);
        return new MessageResult("获取菜单成功",200,allDeskTopMenu);
    }


}
