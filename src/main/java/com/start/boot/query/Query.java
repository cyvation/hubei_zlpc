package com.start.boot.query;

import com.start.boot.annotation.BseeColumnName;
import com.start.boot.annotation.TableName;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 查询对象
 */
public class Query{

    /**
     * 排序列
     */
    protected String orderByClause;

    /**
     * 是否去重
     */
    protected boolean distinct;

    /**
     * 表名
     */
    protected  String tableName;

    /**
     * 设置要显示的字段（对应数据里面的字段名），如只显示少量字段时。设置该值
     * key为数据库字段，value为别名
     */
    protected Map<String,String> displayColumnName =null;

    /**
     * 最终生成的列
     */
    protected  String columnNames="*";

    /**
     * 过滤的字段，不显示的（对应数据里面的字段名）
     * todo 设置了别名，移除不了
     *
     */
    protected  ArrayList<String>filter=null;

    /**
     * 实体对象
     */
    private Class clazz=null;

    /**
     * 条件封装
     */
    protected List<Criteria> oredCriteria;


    /**
     * 分组字段
     */
    private LinkedList groupByClause=null;


    private String groupByStrinSql=null;

    /**
     * 构建要查询的字段及过滤的字段
     * @return
     */
    public Query build(){
        Set<String> tempColumnName = new HashSet<>();
        /**
         * 设置要显示的字段
         */
        if (displayColumnName !=null){
            displayColumnName.keySet().stream().forEach(data->{
                String value = displayColumnName.get(data);
                if (value==null){
                    tempColumnName.add(data);
                }else {
                    tempColumnName.add(data+" "+ value);
                }
            });
        }else {
            /**
             * 如果设置了实体对象，默认显示实体字段里面所有对应的数据库字段
             */
            if (clazz!=null){
                TableName tableName = (TableName) clazz.getDeclaredAnnotation(TableName.class);
                if (tableName!=null){
                    this.tableName=tableName.value();
                }
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    BseeColumnName annotation = declaredField.getAnnotation(BseeColumnName.class);
                    if (annotation!=null){
                        String name = annotation.value();
                        tempColumnName.add(name);
                    }else {
                        tempColumnName.add(declaredField.getName());
                    }
                }
            }
        }

        /**
         * 设置分组
         */
        if (groupByClause!=null){
            StringBuffer groupTemp = new StringBuffer("");
            groupByClause.stream().forEach(data->{
                groupTemp.append(" "+data+",");
            });
          groupByStrinSql=groupTemp.substring(0,groupTemp.length()-1);
        }

        /**
         * 过滤不显示的字段
         */
        if (filter!=null){
            filter.stream().forEach(data->{
                tempColumnName.remove(data);
            });
        }
        if (tempColumnName!=null){
            StringBuffer stringBuffer = new StringBuffer();
            tempColumnName.stream().forEach(data->{
                stringBuffer.append(data+",");
            });
            if (!stringBuffer.toString().equalsIgnoreCase("*")){
                this.columnNames=stringBuffer.substring(0,stringBuffer.length()-1);
            }
        }
        return this;
    }

    public void clear() {
        tableName=null;
        columnNames=null;
        clazz=null;
        filter.clear();
        displayColumnName.clear();
        oredCriteria.clear();
        groupByClause.clear();
        groupByStrinSql=null;
        orderByClause = null;
        distinct = false;
    }


    public Map<String,String> getDisplayColumnName() {
        if (displayColumnName ==null){
            displayColumnName=  new HashMap<>();
        }
        return displayColumnName;
    }
    public String getTableName() {
        return tableName;
    }

    public Query setFilter(ArrayList<String> filter) {
        this.filter = filter;
        return  this;
    }

    public Class getClazz() {
        return clazz;
    }

    public Query setTableName(String tableName) {
        this.tableName = tableName;
        return  this;
    }

    public ArrayList<String> getFilter() {
        if (filter==null){
            this.filter=new ArrayList<>();
        }
        return filter;
    }

    public Query setClazz(Class clazz) {
        this.clazz = clazz;
        return  this;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public Query setDisplayColumnName(Map<String,String> displayColumnName) {
        this.displayColumnName = displayColumnName;
        return this;
    }

    public void setOredCriteria(List<Criteria> oredCriteria) {
        this.oredCriteria = oredCriteria;
    }

    public Query() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public Query setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }


    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }


        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }




        public Criteria andIsNull(String parmarterName) {
            addCriterion(parmarterName+" is null");
            return (Criteria) this;
        }

        public Criteria andIsNull(Date parmarterName) {
            addCriterion(parmarterName+" is null");
            return (Criteria) this;
        }
        public Criteria andIsNull(Integer parmarterName) {
            addCriterion(parmarterName+" is null");
            return (Criteria) this;
        }
        public Criteria andIsNotNull(String parmarterName) {
            addCriterion(parmarterName+" is not null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(Date parmarterName) {
            addCriterion(parmarterName+" is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String parmarterName,String value) {

            addCriterion(parmarterName+" =",value,parmarterName);
            return (Criteria) this;
        }
        public Criteria andEqualTo(String parmarterName,Date value) {

            addCriterion(parmarterName+" =",value,parmarterName);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String parmarterName,Integer value) {
            addCriterion(parmarterName+" = ",value,parmarterName);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String parmarterName,String value) {
            addCriterion(parmarterName+"<>",value,parmarterName);
            return (Criteria) this;
        }
        public Criteria andNotEqualTo(String parmarterName,Integer value) {
            addCriterion(parmarterName+"<>",value,parmarterName);
            return (Criteria) this;
        }

        /**
         * 大于 >
         * @param parmarterName
         * @return
         */
        public Criteria  andGreaterThan(String parmarterName,String value) {
            addCriterion(parmarterName+" >", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria  andGreaterThan(String parmarterName,Date value) {
            addCriterion(parmarterName+" >", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria  andGreaterThan(String parmarterName,Integer value) {
            addCriterion(parmarterName+" >", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String parmarterName,String value) {
            addCriterion(parmarterName+" >=", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria andGreaterThanOrEqualTo(String parmarterName,Date value) {
            addCriterion(parmarterName+" >=", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria  andGreaterThanOrEqualTo(String parmarterName,Integer value) {
            addCriterion(parmarterName+" >=", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andlessThan(String parmarterName,Integer value) {
            addCriterion(parmarterName+" <", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria andlessThan(String parmarterName,String value) {
            addCriterion(parmarterName+" <", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria andlessThan(String parmarterName,Date value) {
            addCriterion(parmarterName+" <", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andlessThanOrEqualTo(String parmarterName,String value) {
            addCriterion(parmarterName+" <=", value, parmarterName);
            return (Criteria) this;
        }
        public Criteria andlessThanOrEqualTo(String parmarterName,Date value) {
            addCriterion(parmarterName+" <=", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andlike(String parmarterName,String value) {
            addCriterion(parmarterName+" like", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andnotLike(String parmarterName,String value) {
            addCriterion(parmarterName+" not like", value, parmarterName);
            return (Criteria) this;
        }

        public Criteria andin(String parmarterName,List<String> values) {
            addCriterion(parmarterName+" in", values, parmarterName);
            return (Criteria) this;
        }
        public Criteria andin(String parmarterName,ArrayList<Integer> values) {
            addCriterion(parmarterName+" in", values, parmarterName);
            return (Criteria) this;
        }

        public Criteria andnotIn(String parmarterName,List<String> values) {
            addCriterion(parmarterName+" not in", values, parmarterName);
            return (Criteria) this;
        }
        public Criteria andnotIn(String parmarterName,ArrayList<Integer> values) {
            addCriterion(parmarterName+" not in", values, parmarterName);
            return (Criteria) this;
        }

        public Criteria andbetween(String parmarterName,String value1, String value2) {
            addCriterion(parmarterName+" between", value1, value2, parmarterName);
            return (Criteria) this;
        }
        public Criteria andbetween(String parmarterName,Date value1, Date value2) {
            addCriterion(parmarterName+" between", value1, value2, parmarterName);
            return (Criteria) this;
        }

        public Criteria andnotBetween(String parmarterName,String value1, String value2) {
            addCriterion(parmarterName+" not between", value1, value2, parmarterName);
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public LinkedList getGroupByClause() {
        if (groupByClause==null){
            groupByClause=new LinkedList();
        }
        return groupByClause;
    }

    public Query setGroupByClause(LinkedList groupByClause) {
        this.groupByClause = groupByClause;
        return this;
    }

    public String getGroupByStrinSql() {
        return groupByStrinSql;
    }

    public void setGroupByStrinSql(String groupByStrinSql) {
        this.groupByStrinSql = groupByStrinSql;
    }
}