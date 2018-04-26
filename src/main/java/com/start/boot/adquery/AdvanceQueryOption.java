package com.start.boot.adquery;

import com.start.boot.dao.tyyw.Tyyw_FldmMapper;
import com.start.boot.domain.Tyyw_Fldm;
import com.start.boot.domain.Tyyw_FldmExample;
import com.start.boot.utils.TyywQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高级查询获取可选值
 *本类所有方法，参数类型必须为map类型。参数值 通过map传递
 *
 * @caomin
 * @create 2017-12-13 14:00
 **/
@Component
public class AdvanceQueryOption {

      @Autowired
      TyywQueryUtils tyywQueryUtils;


      @Autowired
     Tyyw_FldmMapper tyyw_fldmMapper;


    /**
     * 获取案由的数据
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
      public List<Tyyw_Fldm>getAnYouSelectValue(Map<String,String> parmarter) throws Exception {
          Tyyw_FldmExample example = new Tyyw_FldmExample();

          String dm = parmarter.get("dm");
          String lbbm = parmarter.get("LBBM");

          example.createCriteria().andDmLike(dm).andLbbmEqualTo(lbbm).andFdmIsNull();
          List<Tyyw_Fldm> parent = tyyw_fldmMapper.selectByExample(example);
          getAnYouChildren(dm,lbbm,parent);
          return parent;
      }

    private void getAnYouChildren(String dm, String lbbm, List<Tyyw_Fldm> parent) {

        for (Tyyw_Fldm tyyw_fldm : parent) {
            Tyyw_FldmExample example=new Tyyw_FldmExample();
            example.clear();
            String dm1 = tyyw_fldm.getDm();
            example.createCriteria().andDmLike(dm).andLbbmEqualTo(lbbm).andFdmEqualTo(dm1);
            long l = tyyw_fldmMapper.countByExample(example);
            if (l!=0){
                List<Tyyw_Fldm> children = tyyw_fldmMapper.selectByExample(example);
                tyyw_fldm.setChildren(children);
                getAnYouChildren(dm,lbbm,children);
            }
        }
    }



    /**
     * 获取审查逮捕的数据
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getScdbOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmTree(parmarter);
    }
    private void getScdChildren(String dm, List<Tyyw_Fldm> parent) {

        for (Tyyw_Fldm tyyw_fldm : parent) {
            Tyyw_FldmExample example=new Tyyw_FldmExample();
            example.clear();
            String dm1 = tyyw_fldm.getDm();
            example.createCriteria().andDmLike(dm).andFdmEqualTo(dm1);
            long l = tyyw_fldmMapper.countByExample(example);
            if (l!=0){
                List<Tyyw_Fldm> children = tyyw_fldmMapper.selectByExample(example);
                tyyw_fldm.setChildren(children);
                getScdChildren(dm,children);
            }
        }
    }


    /**
     * 获取一审公诉的数据
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getYsgsOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmTree(parmarter);
    }

    /**
     * 获取二审公诉的数据
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getEsgsOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmTree(parmarter);
    }

   /**
     * 获取二审抗诉
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public Map<String,String>getEsksOption(Map<String,String> parmarter) throws Exception {
        return parmarter;
    }


    /**
     * 获取审判监督程序抗诉案件的数据
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getSpjdctksAjOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmList(parmarter);
    }

    /**
     * 获取一审宣告刑
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getYsXgxOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmTree(parmarter);
    }

    /**
     * 获取附加刑
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getFjxOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmList(parmarter);
    }

    /**
     * 获取适用程序
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getSycxOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmTree(parmarter);
    }


    /**
     * 适用制度，认罪认罚制度
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public Map<String,String> getSyzdOption(Map<String,String> parmarter) throws Exception {
        return parmarter;
    }

    /**
     * 获取强制措施
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public List<Tyyw_Fldm>getQzcsOption(Map<String,String> parmarter) throws Exception {
        return getTyywFldmList(parmarter);
    }
    /**
     * 获取审查起诉日期
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public Map<String,String>getScqsRqOption(Map<String,String> parmarter) throws Exception {

        return parmarter;
    }

    /**
     * 获取有无涉案物
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public Map<String,String> getYwSawOption(Map<String,String> parmarter) throws Exception {

        Map<String,String> result = new HashMap<>();
        result.put("Y","有");
        result.put("N","无");
        return result;
    }
    /**
     * 获取input
     * @param  parmarter 参数
     * @reurn
     * @throws Exception
     */
    public Map<String,String> getInputCommon(Map<String,String> parmarter) throws Exception {
        return parmarter;
    }



    /**
     * 通过dm 获取xt_dm_fldm中的列表数据
     * @param parmarter
     * @return
     */
    private List<Tyyw_Fldm> getTyywFldmList(Map<String, String> parmarter) {
        Tyyw_FldmExample example = new Tyyw_FldmExample();
        String dm = parmarter.get("dm");
        example.createCriteria().andDmLike(dm).andSfscEqualTo("N");
        List<Tyyw_Fldm> parent = tyyw_fldmMapper.selectByExample(example);
        return parent;
    }


    /**
     * 通过DM  获取 xt_dm_fldm中的树形数据
     * @param parmarter
     * @return
     */
    private List<Tyyw_Fldm> getTyywFldmTree(Map<String, String> parmarter) {
        Tyyw_FldmExample example = new Tyyw_FldmExample();
        String dm = parmarter.get("dm");
        example.createCriteria().andDmLike(dm).andFdmIsNull();
        List<Tyyw_Fldm> parent = tyyw_fldmMapper.selectByExample(example);
        getScdChildren(dm,parent);
        return parent;
    }



}
