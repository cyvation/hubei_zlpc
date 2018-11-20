package com.start.boot.validate;

import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.*;
import com.start.boot.domain.*;
import com.start.boot.query.MenuQuery;
import com.start.boot.service.JbxxService;
import com.start.boot.service.XtZzjgRmbmService;
import com.start.boot.service.YjService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    XtZzjgRmbmService xtZzjgRmbmService;

    @Autowired
    YjService yjService;

    @Autowired
    YxlcSljdMapper yxlcSljdMapper;

    @Autowired
    XtPcLbMapper xtPcLbMapper;

    /**
     * 验证  是否完成所有审批
     * czlxbm 为1
     * lcjdbm 为007
     * @param menuQuery
     * @return
     */
    public boolean validateSfwcscsp(MenuQuery menuQuery){

        // 1. 合格案件，报审完毕，可以结束
        //2.  其他类型，报审完毕，可以结束
        //3.  活动形式的，二次报审？

        // 只有活动形式才能二次送审
//        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
//
//        XtPcLbExample example = new XtPcLbExample();
//        example.createCriteria().andPcflbmEqualTo(jbxx.getPCFLBM()).andSfqyEqualTo("Y").andSfpcfpEqualTo("Y");
//        List<XtPcLb> xtPcLbs = xtPcLbMapper.selectByExample(example);
//        if (CollectionUtils.isEmpty(xtPcLbs)){
//            return false;
//        }

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
     * 小组联席会议
     *
     * @param menuQuery
     * @return
     */
    public  boolean validatefXzlxhy(MenuQuery menuQuery){
        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
        YxlcSljdExample example=new YxlcSljdExample();
        example.createCriteria().andLcjdbmEqualTo("007").andPcslbmEqualTo(menuQuery.getPczybm());
        long l = yxlcSljdMapper.countByExample(example);
        if (l!=0){
                return true;
        }
        return false;
    }


    /**
     * 检委会意见
     *
     * @param menuQuery
     * @return
     */
    public  boolean validatefJwhyj(MenuQuery menuQuery){
        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
        YxlcSljdExample example=new YxlcSljdExample();
        example.createCriteria().andLcjdbmEqualTo("007").andPcslbmEqualTo(menuQuery.getPczybm());
        long l = yxlcSljdMapper.countByExample(example);
        if (l!=0){
                return true;
        }
        return false;
    }



    /**
     *办理阶段，验证
     *
     * 评查报审按钮是否显示（反馈过后的审批阶段）
     *
     * 如果是评查员角色就显示，如果是案管负责人、分管检察长、检察长。则不显示
     *
     * @param menuQuery
     * @return
     */
    public  boolean validatePcbs(MenuQuery menuQuery){

        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
        List<Yj> yjList = yjService.getYjList(menuQuery.getPczybm());
        if (!CollectionUtils.isEmpty(yjList)){
            if ("同意承办人意见".equalsIgnoreCase(yjList.get(0).getPcyjjl())){
                return true;
            }
        }
        List<XtQxRyJsfp> xtQxRyJsfp = xtZzjgRmbmService.getXtQxRyJsfp(menuQuery.getDwbm(), menuQuery.getGh());
        if (!CollectionUtils.isEmpty(xtQxRyJsfp)) {
            List<String> collect = xtQxRyJsfp.stream().map(x -> x.getJsbm()).collect(Collectors.toList());
            //评查助理没有评查报审按钮
            if(collect.contains("110")){
                return false;
            }
            //合格案件，分管和检察长。不需要送审
/*            if ("合格案件".equalsIgnoreCase(jbxx.getPCJL())){
                if (collect.contains("107") || collect.contains("120")) {
                    return false;
                }
            }else { //结论为其它的，分管，案管负责人。检察长都不需要送审
                if (collect.contains("107") || collect.contains("120")||collect.contains("104")) {
                    return false;
                }
            }*/
        }
        return true;
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

    /**
     *  审批阶段， 发送承办人按钮
     *
     * @param menuQuery
     * @return
     */
    public  boolean validateSpJdFscbr(MenuQuery menuQuery){
        boolean b = validateSffscbr(menuQuery);
        if (b){
            return false;
        }
        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
        //评查审批完成后才可以发送承办人
        if (jbxx.getPCJDBH().equals("007")){
            SpExample example = new SpExample();
            example.createCriteria().andSpwjbmEqualTo(menuQuery.getPczybm()).andSpjlIsNotNull();
            example.setOrderByClause("ZHXGSJ DESC");
            List<Sp> sps = spMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(sps)){ // 审批完成，显示
                return true;
            }

        }
        return false;
    }

    /**
     * 判断是否已经发送过承办人
     * @param menuQuery
     * @return
     */
    private boolean validateSffscbr(MenuQuery menuQuery){
        YxlcSljdExample example=new YxlcSljdExample();
        example.createCriteria().andLcjdbmEqualTo("010").andPcslbmEqualTo(menuQuery.getPczybm());
        long l = yxlcSljdMapper.countByExample(example);
        if (l==0){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 办理阶段，新增检察官意见按钮
     * @param menuQuery
     * @return
     */
    public boolean validateAddStuff(MenuQuery menuQuery){
        YX_PC_JBXX jbxx = jbxxService.getJbxx(menuQuery.getPczybm());
        if ("006".equals(jbxx.getPCJDBH())){
            return true;
        }
        return false;
    }




}
