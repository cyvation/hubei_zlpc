package com.start.boot.service;

import com.start.boot.domain.Message;
import com.start.boot.domain.YX_PC_JBXX;
import com.start.boot.query.MessageQuery;

import java.util.List;
import java.util.Map;

/**
 * 消息service yx_xt_xxjl
 */
public interface MessageService {

    Message save(Message message);


    /**
     * 获取未发送的消息
     * @param
     * @param
     * @return
     */
    List<Map<String,Object>>  getMessage() throws Exception;


    /**
     * 获取邮件地址
     * @param dwbm 单位编码
     * @param gh 工号
     * @return
     */
    String getEmailAdress(String dwbm,String gh) throws Exception;


    /**
     * 获取消息类型为0,且未读的消息
     * @param dwbm
     * @param gh
     * @param ajmc
     * @return
     */
    List<YX_PC_JBXX>getMessagePcEndList(String dwbm, String gh, String ajmc);


    /**
     * 修改消息状态
     * @param glbmsah
     * @return
     */
    void updateMessageZt(String glbmsah);


    /**
     * 修改消息发送邮件状态
     *
     * @param dwbm 单位编码
     * @param gh 工号
     *@param xxlx 消息类型
     */
    void updateMessageFszt(String xxlx,String dwbm, String gh);


    /**
     * 获取消息
     * @param messageQuery
     * @return
     */
    List<Message>getMessageList(MessageQuery messageQuery);

    /**
     * 获取未读消息数量
     * @return
     * @param messageQuery
     */
   Integer getMessageCount(MessageQuery messageQuery);

    /**
     * 批量更新消息状态
     * @param messageQuery
     */
    void batchUpdateXxzt(MessageQuery messageQuery);

    void updateMessageZtByDwbmAndGh(String currentDwbm, String currentGh);
}
