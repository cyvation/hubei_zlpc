package com.start.boot.validate;

import com.start.boot.common.Param_Pager;
import com.start.boot.dao.ajpc.MessageMapper;
import com.start.boot.domain.*;
import com.start.boot.query.DbrwQuery;
import com.start.boot.service.HandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取桌面菜单下面的待办数量
 *
 *
 *
 * @caomin
 * @create 2017-12-04 22:20
 **/
@Component
public class DbrwValidate {

    @Autowired
    HandleService handleService;

    @Autowired
    MessageMapper messageMapper;

    /**
     * 获取评查办理  的待办数量
     * @return
     */
    public int validateBl(DbrwQuery query) throws Exception {
        Param_sp param=new Param_sp();
        param.setPcrdwbm(query.getDwbm());
        param.setPcrgh(query.getGh());
        Param_Pager data = handleService.get_pclist(param);
        return data.getCount();
    }


    /**
     * 获取评查审批 的待办数量
     * @return
     */
    public int validateSp(DbrwQuery query)throws Exception {
        Param_sp param=new Param_sp();
        param.setSprdwbm(query.getDwbm());
        param.setSprgh(query.getGh());

        Param_Pager data = handleService.get_splst(param);
        return data.getCount();
    }


    /**
     * 获取评查反馈  的待办数量
     * @return
     */
    public int validateFk(DbrwQuery query)throws Exception {
        Param_Jbxx  param=new Param_Jbxx();
        param.setBpc_dwbm(query.getDwbm());
        param.setBpc_gh(query.getGh());
        Param_Pager data = handleService.get_cbrfklist(param);
        return data.getCount();
    }


    /**
     * 获取评查方案  的待办数量
     * @return
     */
    public int validateFa(DbrwQuery query)throws Exception {
        Param_HdList param = new Param_HdList();
        param.setCjrdwbm(query.getDwbm());
        param.setP_cjrgh(query.getGh());
        Param_Pager data = handleService.getPchdList(param);
        return data.getCount();
    }


    /**
     * 获取部门反馈  的待办数量
     * @return
     */
    public int validateBmFk(DbrwQuery query)throws Exception {
        Param_Jbxx  param=new Param_Jbxx();
        param.setBpc_dwbm(query.getDwbm());
        param.setBpc_gh(query.getGh());
        Param_Pager data = handleService.get_cbbmfklist(param);
        return data.getCount();
    }
    /**
     * 获取评查通知的 消息数量
     * @return
     */
    public int validateNotice(DbrwQuery query)throws Exception {
        MessageExample example = new MessageExample();

        example.createCriteria()
                .andJsrghEqualTo(query.getGh())
                .andJsrdwbmEqualTo(query.getDwbm())
                .andXxztEqualTo("0")
                .andXxlxEqualTo("0");
       return (int) messageMapper.countByExample(example);

    }



}
