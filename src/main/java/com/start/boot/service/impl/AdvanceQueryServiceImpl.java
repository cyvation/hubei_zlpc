package com.start.boot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.start.boot.adquery.AdQueryRequest;
import com.start.boot.adquery.AdvanceQueryOption;
import com.start.boot.dao.ajpc.AdvancedQueryMapper;
import com.start.boot.dao.ajpc.TyywQueryUtilsMapper;
import com.start.boot.domain.AdvancedQuery;
import com.start.boot.domain.AdvancedQueryExample;
import com.start.boot.pojo.vo.AdvanceQueryVo;
import com.start.boot.query.AdvanceQueryView;
import com.start.boot.service.AdvanceQueryService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.service.XtZzjgDwbmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @caomin
 * @create 2017-12-13 11:35
 **/
@Service
public class AdvanceQueryServiceImpl implements AdvanceQueryService {


    @Autowired
    AdvanceQueryOption advanceQueryValidate;

    @Autowired
    AdvancedQueryMapper advancedQueryMapper;

    @Autowired
    TyywQueryUtilsMapper tyywQueryUtilsMapper;


   @Autowired
    SystemCoreConfigService systemCoreConfigService;

    @Autowired
    XtZzjgDwbmService xtZzjgDwbmService;

    @Override
    public List<AdvanceQueryVo> getAllCondition() {
        AdvancedQueryExample advancedQueryExample = new AdvancedQueryExample();
        advancedQueryExample.createCriteria().andSfscEqualTo("N");
        advancedQueryExample.setOrderByClause("PX ASC");
        List<AdvancedQuery> advancedResult = advancedQueryMapper.selectByExample(advancedQueryExample);
        ArrayList<AdvanceQueryVo> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(advancedResult)){
             for (AdvancedQuery advancedQuery : advancedResult) {
                 getAdvanceVoResult(result, advancedQuery);
             }

        }
        return result;
    }

    @Override
    public AdvancedQuery getByBh(String bh) {
       return advancedQueryMapper.selectByPrimaryKey(bh);
    }

    private void getAdvanceVoResult(ArrayList<AdvanceQueryVo> result, AdvancedQuery advancedQuery) {
        AdvanceQueryVo temp = new AdvanceQueryVo();
        BeanUtils.copyProperties(advancedQuery,temp);
        String ysf = advancedQuery.getYsf();
        //获取运算符
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(ysf)){
            Object parse = JSONObject.parse(ysf);
            List<Object> ysf1 = temp.getYsf();
            if(parse instanceof JSONObject){
              JSONObject  jsonObject= (JSONObject) parse;
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    ysf1.add( entry.getValue());
                }
            }else if (parse instanceof JSONArray){
                JSONArray jsonArray= (JSONArray) parse;
                ysf1.add(jsonArray);
            }

        }
        result.add(temp);
    }

    /**
     * 根据处理程序获取结果
     * @param result
     * @param temp
     * @param parmarter
     * @param cx
     * @param cs
     */
    private void getResultByCx(ArrayList<AdvanceQueryVo> result, AdvanceQueryVo temp, HashMap<String, Object> parmarter, String cx, String cs) {
        invokeCs(parmarter, cs);

        try {
            if (StringUtils.isNotEmpty(cx)){
                Method declaredMethod = advanceQueryValidate.getClass().getDeclaredMethod(cx,Map.class);
                Object invoke = declaredMethod.invoke(advanceQueryValidate, parmarter);
                if (invoke !=null){
                    temp.setValue( invoke);
                }
            }
            result.add(temp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理参数
     * @param parmarter
     * @param cs
     */
    private void invokeCs(HashMap<String, Object> parmarter, String cs) {
        //处理参数
        if (StringUtils.isNotEmpty(cs)){
            Object parse = JSONObject.parse(cs);
            if(parse instanceof JSONObject){
                JSONObject jsonObject= (JSONObject) parse;
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    parmarter.put(entry.getKey(),entry.getValue());
                }
            }else if (parse instanceof JSONArray){
                JSONArray jsonArray= (JSONArray) parse;
                parmarter.put("value",jsonArray);
            }
        }
    }

    /**
     * 根据前端传过来的案由代码查询 部门受案号
     * @param
     * @param currentDwbm
     * @return
     * @throws Exception
     */
    public String startQuery(AdQueryRequest request, String currentDwbm) throws Exception {
        String tyyw_dblinkName = systemCoreConfigService.getSystemConfigValue("tyyw_dblinkName");
        String tyyw_gjcx_extSql = systemCoreConfigService.getSystemConfigValue("tyyw_gjcx_extSql").replaceAll(":pcflbm", "'" + request.getPcflbm() + "'");
        List<AdvanceQueryView> parmarter = request.getParmarter();
        // 完成日期
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        StringBuffer wcrq = new StringBuffer(" and jb.wcrq").append(" >= to_date(");
        wcrq.append("'").append(startDate).append("','yyyy/mm/dd')").append(" and jb.wcrq").append(" <= to_date(");
        wcrq.append("'").append(endDate).append("','yyyy/mm/dd')");

        if (!CollectionUtils.isEmpty(parmarter)){
            String parentBh = parmarter.get(0).getParent();
            AdvancedQuery parent = this.getByBh(parentBh);
            String sfcx1 = parent.getSfcx();
            int from = sfcx1.lastIndexOf("FROM");

            int where = sfcx1.indexOf("WHERE");

            /**
             * 待替换的from sql
             */
            String substring = sfcx1.substring(from + 4, where);
            /**
             * 待替换的where sql
             */
            String substring1 = sfcx1.substring(where, sfcx1.length());

            StringBuffer whereSql = new StringBuffer(" "+substring1);

            StringBuffer fromSql = new StringBuffer(substring);

            for (AdvanceQueryView advanceQueryView : parmarter) {
                String bh = advanceQueryView.getBh();
                AdvancedQuery advancedQuery = this.getByBh(bh);
                String sfcx = advancedQuery.getSfcx();
                if (StringUtils.isNotEmpty(sfcx)){
                    fromSql.append(" "+sfcx+" ");
                }
                String sfcs = advancedQuery.getSfcs();
                if (StringUtils.isNotEmpty(sfcs)){
                    whereSql.append(" and "+sfcs);
                }
                String key = advanceQueryView.getKey();
                if (StringUtils.isNotEmpty(key)){
                    String value = advanceQueryView.getValue();
                    String ysf = advanceQueryView.getYsf();
                    String bm = advancedQuery.getBm();
                    String temp ="";
                   if (StringUtils.isNotEmpty(value)){
                     if (value.startsWith("'IS")||value.startsWith("'is")){
                         value=value.replace("'","");
                     }
                    }
                    if (StringUtils.isEmpty(ysf)){
                        temp= " and "+bm+"."+key+" "+value+" ";
                    }
                    else if ("like".equalsIgnoreCase(ysf)) {
                         temp=" and " + bm + "." + key + " " + ysf + " " + "'" + value + "'"+" ";
                    }else if (ysf.startsWith("not in")){
                        temp= " and "+bm+"."+key+" "+ysf+value+")"+" ";
                    }
                    else if (ysf.startsWith("in")){
                        temp= " and "+bm+"."+key+" "+ysf+value+")"+" ";
                    }
                    else {
                         temp= " and "+bm+"."+key+" "+ysf+value+" ";
                     }
                    whereSql.append(temp);
                }
            }
            String replace = sfcx1.replace(substring, fromSql.toString()).replace(substring1, whereSql);
            //完成日期不为空
            String cs = parent.getCs();
            replace+=cs;
            //已筛选的不筛选
            String s = replace + " " + tyyw_gjcx_extSql;
            StringBuffer cbdw=new StringBuffer(" and jb.cbdw_bm").append(" in ( ");
            cbdw.append("'").append(request.getExtCbdw()).append("')");

           return s + cbdw.toString() + wcrq.toString() + " order by jb.WCRQ desc";
        }
        return "select jb.* from tyyw_gg_ajjbxx"+tyyw_dblinkName+" jb where jb.cbdw_bm in ('"+request.getExtCbdw()+"')  "+ wcrq.toString() + tyyw_gjcx_extSql+"  order by jb.WCRQ desc";
    }



    public List<Map> getReuslt(String sql)throws Exception{
     return   tyywQueryUtilsMapper.getResult(sql);
    }


    @Override
    public Object getSingleCondition(String bh) {

        AdvancedQuery advancedQuery = this.getByBh(bh);
        String cs = advancedQuery.getCs();
        String cx = advancedQuery.getCx();
        HashMap<String, Object> hashMap = new HashMap<>();
        invokeCs(hashMap,cs);

        Method declaredMethod = null;
        try {
            if (StringUtils.isNotEmpty(cx)){
                declaredMethod = advanceQueryValidate.getClass().getDeclaredMethod(cx,Map.class);
                Object invoke = declaredMethod.invoke(advanceQueryValidate, hashMap);
                if (invoke !=null && invoke instanceof HashMap){
                    Object value = ((HashMap) invoke).get("value");
                    if (value!=null){
                        return value;
                    }
                }
                return invoke;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("通过bh获取组件处理程序失败！");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("通过bh获取组件可选值失败！");
        }
       return null;
    }


}
