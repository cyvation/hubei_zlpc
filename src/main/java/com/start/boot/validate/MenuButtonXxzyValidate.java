package com.start.boot.validate;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.PersonMapper;
import com.start.boot.dao.ajpc.SpMapper;
import com.start.boot.dao.ajpc.YjMapper;
import com.start.boot.domain.*;
import com.start.boot.query.MenuQuery;
import com.start.boot.service.JbxxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * 对应XT_PC_DZZY xycx 这个字段
 *
 * 该类中，所有方法必须有且仅有 MenuQuery参数定义
 * 所有方法返回值 必须为boolean类型
 * @caomin
 * @create 2017-12-03 21:28
 **/
@Component
public class MenuButtonXxzyValidate {

    @Autowired
    SpMapper spMapper;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    JbxxService jbxxService;

    @Autowired
    YjMapper yjMapper;

    /**
     * 验证  是否完成所有审批
     * czlxbm 为1
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfwcscsp(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            SpExample spExample = new SpExample();
            spExample.createCriteria().andSpwjbmEqualTo(pczybm);
            List<Sp> sps = spMapper.selectByExample(spExample);
            if (!CollectionUtils.isEmpty(sps)){
                sps.sort(Comparator.comparing(Sp::getPcspbm));
                String spyj = sps.get(sps.size() - 1).getSpyj();
                if (StringUtils.isNotEmpty(spyj)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证  是否送审未完成审批
     * czlxbm 为1
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfsswsp(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            SpExample spExample = new SpExample();
            spExample.createCriteria().andSpwjbmEqualTo(pczybm).andSsrdwbmEqualTo(menuQuery.getDwbm()).andSsrghEqualTo(menuQuery.getGh());
            List<Sp> sps = spMapper.selectByExample(spExample);
            if (!CollectionUtils.isEmpty(sps)){
                sps.sort(Comparator.comparing(Sp::getPcspbm));
                String spyj = sps.get(sps.size() - 1).getSpyj();
                if (StringUtils.isEmpty(spyj)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证  是否未完成审批
     * czlxbm 为2
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfspwwc(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            SpExample spExample = new SpExample();
            spExample.createCriteria().andSpwjbmEqualTo(pczybm).andSprdwbmEqualTo(menuQuery.getDwbm()).andSprghEqualTo(menuQuery.getGh());
            List<Sp> sps = spMapper.selectByExample(spExample);
            if (!CollectionUtils.isEmpty(sps)){
                sps.sort(Comparator.comparing(Sp::getPcspbm));
                String spyj = sps.get(sps.size() - 1).getSpyj();
                if (StringUtils.isEmpty(spyj)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证 是否业务部门有异议
     * czlxbm 为1
     * lcjdbm 为010
     * @param menuQuery
     * @return
     */
    public boolean validateSfbmyyy(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            YjExample yjExample = new YjExample();
            yjExample.createCriteria().andPcslbmEqualTo(pczybm).andPcyjlxEqualTo("3");
            List<Yj> yjs = yjMapper.selectByExample(yjExample);
            if (!CollectionUtils.isEmpty(yjs)){
                yjs.sort(Comparator.comparing(Yj::getPcyjbh));
                String spyj = yjs.get(yjs.size() - 1).getPcyjjl();
                if (SystemConfiguration.bmyybs.equalsIgnoreCase(spyj)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证 是否业务部门无异议
     * czlxbm 为1
     * lcjdbm 为010
     * @param menuQuery
     * @return
     */
    public boolean validateSfbmwyy(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            YjExample yjExample = new YjExample();
            yjExample.createCriteria().andPcslbmEqualTo(pczybm).andPcyjlxEqualTo("3");
            List<Yj> yjs = yjMapper.selectByExample(yjExample);
            if (!CollectionUtils.isEmpty(yjs)){
                yjs.sort(Comparator.comparing(Yj::getPcyjbh));
                String spyj = yjs.get(yjs.size() - 1).getPcyjjl();
                if (SystemConfiguration.bmyybs.equalsIgnoreCase(spyj)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证 是否业务部门有异议且为评查审批人
     * czlxbm 为2
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfsprAndBmyyy(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            YjExample yjExample = new YjExample();
            yjExample.createCriteria().andPcslbmEqualTo(pczybm).andPcyjlxEqualTo("3");
            List<Yj> yjs = yjMapper.selectByExample(yjExample);
            if (!CollectionUtils.isEmpty(yjs)){
                yjs.sort(Comparator.comparing(Yj::getPcyjbh));
                String fkyj = yjs.get(yjs.size() - 1).getPcyjjl();
                if (SystemConfiguration.bmyybs.equalsIgnoreCase(fkyj)){

                    SpExample spExample = new SpExample();
                    spExample.createCriteria().andSpwjbmEqualTo(pczybm);
                    List<Sp> sps = spMapper.selectByExample(spExample);
                    if (!CollectionUtils.isEmpty(sps)){
                        sps.sort(Comparator.comparing(Sp::getPcspbm));
                        Sp spFirst = sps.get(0);
                        Sp spLast = sps.get(sps.size() - 1);
                        if (spFirst.getSprdwbm().equals(menuQuery.getDwbm()) && spFirst.getSprgh().equals(menuQuery.getGh())
                                && StringUtils.isNotEmpty(spLast.getSpjl())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证 是否业务部门有反馈且为评查审批人
     * czlxbm 为2
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfsprAndBmyfk(MenuQuery menuQuery){
        String pczybm = menuQuery.getPczybm();
        if (StringUtils.isNotEmpty(pczybm)){
            YjExample yjExample = new YjExample();
            yjExample.createCriteria().andPcslbmEqualTo(pczybm).andPcyjlxEqualTo("3");
            List<Yj> yjs = yjMapper.selectByExample(yjExample);
            if (!CollectionUtils.isEmpty(yjs)){
                yjs.sort(Comparator.comparing(Yj::getPcyjbh));
                String fkyj = yjs.get(yjs.size() - 1).getPcyjjl();
                if (StringUtils.isNoneEmpty(fkyj)){

                    SpExample spExample = new SpExample();
                    spExample.createCriteria().andSpwjbmEqualTo(pczybm);
                    List<Sp> sps = spMapper.selectByExample(spExample);
                    if (!CollectionUtils.isEmpty(sps)){
                        sps.sort(Comparator.comparing(Sp::getPcspbm));
                        Sp spFirst = sps.get(0);
                        Sp spLast = sps.get(sps.size() - 1);
                        if (spFirst.getSprdwbm().equals(menuQuery.getDwbm()) && spFirst.getSprgh().equals(menuQuery.getGh())
                                && StringUtils.isNotEmpty(spLast.getSpjl())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * c验证收藏按钮是否显示
     * @param menuQuery
     * @return
     */
    public  boolean validateFavorite(MenuQuery menuQuery){
        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());

        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andDwbmEqualTo(menuQuery.getDwbm()).andGhEqualTo(menuQuery.getGh())
                .andGyzyEqualTo(jbxx.getPCSAH());
        List<Person> people = personMapper.selectByExample(personExample);
        if(CollectionUtils.isEmpty(people)){
            return true;
        }
        return false;
    }


}
