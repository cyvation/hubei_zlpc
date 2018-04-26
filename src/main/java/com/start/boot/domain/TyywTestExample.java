package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TyywTestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TyywTestExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
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

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
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

        public Criteria andDwbmIsNull() {
            addCriterion("DWBM is null");
            return (Criteria) this;
        }

        public Criteria andDwbmIsNotNull() {
            addCriterion("DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andDwbmEqualTo(String value) {
            addCriterion("DWBM =", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmNotEqualTo(String value) {
            addCriterion("DWBM <>", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmGreaterThan(String value) {
            addCriterion("DWBM >", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("DWBM >=", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmLessThan(String value) {
            addCriterion("DWBM <", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmLessThanOrEqualTo(String value) {
            addCriterion("DWBM <=", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmLike(String value) {
            addCriterion("DWBM like", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmNotLike(String value) {
            addCriterion("DWBM not like", value, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmIn(List<String> values) {
            addCriterion("DWBM in", values, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmNotIn(List<String> values) {
            addCriterion("DWBM not in", values, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmBetween(String value1, String value2) {
            addCriterion("DWBM between", value1, value2, "dwbm");
            return (Criteria) this;
        }

        public Criteria andDwbmNotBetween(String value1, String value2) {
            addCriterion("DWBM not between", value1, value2, "dwbm");
            return (Criteria) this;
        }

        public Criteria andBmbmjhIsNull() {
            addCriterion("BMBMJH is null");
            return (Criteria) this;
        }

        public Criteria andBmbmjhIsNotNull() {
            addCriterion("BMBMJH is not null");
            return (Criteria) this;
        }

        public Criteria andBmbmjhEqualTo(String value) {
            addCriterion("BMBMJH =", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhNotEqualTo(String value) {
            addCriterion("BMBMJH <>", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhGreaterThan(String value) {
            addCriterion("BMBMJH >", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhGreaterThanOrEqualTo(String value) {
            addCriterion("BMBMJH >=", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhLessThan(String value) {
            addCriterion("BMBMJH <", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhLessThanOrEqualTo(String value) {
            addCriterion("BMBMJH <=", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhLike(String value) {
            addCriterion("BMBMJH like", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhNotLike(String value) {
            addCriterion("BMBMJH not like", value, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhIn(List<String> values) {
            addCriterion("BMBMJH in", values, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhNotIn(List<String> values) {
            addCriterion("BMBMJH not in", values, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhBetween(String value1, String value2) {
            addCriterion("BMBMJH between", value1, value2, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andBmbmjhNotBetween(String value1, String value2) {
            addCriterion("BMBMJH not between", value1, value2, "bmbmjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhIsNull() {
            addCriterion("WSMBBHJH is null");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhIsNotNull() {
            addCriterion("WSMBBHJH is not null");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhEqualTo(String value) {
            addCriterion("WSMBBHJH =", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhNotEqualTo(String value) {
            addCriterion("WSMBBHJH <>", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhGreaterThan(String value) {
            addCriterion("WSMBBHJH >", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhGreaterThanOrEqualTo(String value) {
            addCriterion("WSMBBHJH >=", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhLessThan(String value) {
            addCriterion("WSMBBHJH <", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhLessThanOrEqualTo(String value) {
            addCriterion("WSMBBHJH <=", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhLike(String value) {
            addCriterion("WSMBBHJH like", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhNotLike(String value) {
            addCriterion("WSMBBHJH not like", value, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhIn(List<String> values) {
            addCriterion("WSMBBHJH in", values, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhNotIn(List<String> values) {
            addCriterion("WSMBBHJH not in", values, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhBetween(String value1, String value2) {
            addCriterion("WSMBBHJH between", value1, value2, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhjhNotBetween(String value1, String value2) {
            addCriterion("WSMBBHJH not between", value1, value2, "wsmbbhjh");
            return (Criteria) this;
        }

        public Criteria andWsjcIsNull() {
            addCriterion("WSJC is null");
            return (Criteria) this;
        }

        public Criteria andWsjcIsNotNull() {
            addCriterion("WSJC is not null");
            return (Criteria) this;
        }

        public Criteria andWsjcEqualTo(String value) {
            addCriterion("WSJC =", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcNotEqualTo(String value) {
            addCriterion("WSJC <>", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcGreaterThan(String value) {
            addCriterion("WSJC >", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcGreaterThanOrEqualTo(String value) {
            addCriterion("WSJC >=", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcLessThan(String value) {
            addCriterion("WSJC <", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcLessThanOrEqualTo(String value) {
            addCriterion("WSJC <=", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcLike(String value) {
            addCriterion("WSJC like", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcNotLike(String value) {
            addCriterion("WSJC not like", value, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcIn(List<String> values) {
            addCriterion("WSJC in", values, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcNotIn(List<String> values) {
            addCriterion("WSJC not in", values, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcBetween(String value1, String value2) {
            addCriterion("WSJC between", value1, value2, "wsjc");
            return (Criteria) this;
        }

        public Criteria andWsjcNotBetween(String value1, String value2) {
            addCriterion("WSJC not between", value1, value2, "wsjc");
            return (Criteria) this;
        }

        public Criteria andDqzIsNull() {
            addCriterion("DQZ is null");
            return (Criteria) this;
        }

        public Criteria andDqzIsNotNull() {
            addCriterion("DQZ is not null");
            return (Criteria) this;
        }

        public Criteria andDqzEqualTo(BigDecimal value) {
            addCriterion("DQZ =", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzNotEqualTo(BigDecimal value) {
            addCriterion("DQZ <>", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzGreaterThan(BigDecimal value) {
            addCriterion("DQZ >", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DQZ >=", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzLessThan(BigDecimal value) {
            addCriterion("DQZ <", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DQZ <=", value, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzIn(List<BigDecimal> values) {
            addCriterion("DQZ in", values, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzNotIn(List<BigDecimal> values) {
            addCriterion("DQZ not in", values, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DQZ between", value1, value2, "dqz");
            return (Criteria) this;
        }

        public Criteria andDqzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DQZ not between", value1, value2, "dqz");
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
}