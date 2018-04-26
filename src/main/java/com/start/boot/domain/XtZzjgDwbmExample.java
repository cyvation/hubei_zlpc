package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class XtZzjgDwbmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtZzjgDwbmExample() {
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

        public Criteria andDwmcIsNull() {
            addCriterion("DWMC is null");
            return (Criteria) this;
        }

        public Criteria andDwmcIsNotNull() {
            addCriterion("DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andDwmcEqualTo(String value) {
            addCriterion("DWMC =", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotEqualTo(String value) {
            addCriterion("DWMC <>", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThan(String value) {
            addCriterion("DWMC >", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("DWMC >=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThan(String value) {
            addCriterion("DWMC <", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLessThanOrEqualTo(String value) {
            addCriterion("DWMC <=", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcLike(String value) {
            addCriterion("DWMC like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotLike(String value) {
            addCriterion("DWMC not like", value, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcIn(List<String> values) {
            addCriterion("DWMC in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotIn(List<String> values) {
            addCriterion("DWMC not in", values, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcBetween(String value1, String value2) {
            addCriterion("DWMC between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andDwmcNotBetween(String value1, String value2) {
            addCriterion("DWMC not between", value1, value2, "dwmc");
            return (Criteria) this;
        }

        public Criteria andFdwbmIsNull() {
            addCriterion("FDWBM is null");
            return (Criteria) this;
        }

        public Criteria andFdwbmIsNotNull() {
            addCriterion("FDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andFdwbmEqualTo(String value) {
            addCriterion("FDWBM =", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmNotEqualTo(String value) {
            addCriterion("FDWBM <>", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmGreaterThan(String value) {
            addCriterion("FDWBM >", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("FDWBM >=", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmLessThan(String value) {
            addCriterion("FDWBM <", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmLessThanOrEqualTo(String value) {
            addCriterion("FDWBM <=", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmLike(String value) {
            addCriterion("FDWBM like", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmNotLike(String value) {
            addCriterion("FDWBM not like", value, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmIn(List<String> values) {
            addCriterion("FDWBM in", values, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmNotIn(List<String> values) {
            addCriterion("FDWBM not in", values, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmBetween(String value1, String value2) {
            addCriterion("FDWBM between", value1, value2, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andFdwbmNotBetween(String value1, String value2) {
            addCriterion("FDWBM not between", value1, value2, "fdwbm");
            return (Criteria) this;
        }

        public Criteria andDwjbIsNull() {
            addCriterion("DWJB is null");
            return (Criteria) this;
        }

        public Criteria andDwjbIsNotNull() {
            addCriterion("DWJB is not null");
            return (Criteria) this;
        }

        public Criteria andDwjbEqualTo(String value) {
            addCriterion("DWJB =", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbNotEqualTo(String value) {
            addCriterion("DWJB <>", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbGreaterThan(String value) {
            addCriterion("DWJB >", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbGreaterThanOrEqualTo(String value) {
            addCriterion("DWJB >=", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbLessThan(String value) {
            addCriterion("DWJB <", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbLessThanOrEqualTo(String value) {
            addCriterion("DWJB <=", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbLike(String value) {
            addCriterion("DWJB like", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbNotLike(String value) {
            addCriterion("DWJB not like", value, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbIn(List<String> values) {
            addCriterion("DWJB in", values, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbNotIn(List<String> values) {
            addCriterion("DWJB not in", values, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbBetween(String value1, String value2) {
            addCriterion("DWJB between", value1, value2, "dwjb");
            return (Criteria) this;
        }

        public Criteria andDwjbNotBetween(String value1, String value2) {
            addCriterion("DWJB not between", value1, value2, "dwjb");
            return (Criteria) this;
        }

        public Criteria andSfscIsNull() {
            addCriterion("SFSC is null");
            return (Criteria) this;
        }

        public Criteria andSfscIsNotNull() {
            addCriterion("SFSC is not null");
            return (Criteria) this;
        }

        public Criteria andSfscEqualTo(String value) {
            addCriterion("SFSC =", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscNotEqualTo(String value) {
            addCriterion("SFSC <>", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscGreaterThan(String value) {
            addCriterion("SFSC >", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscGreaterThanOrEqualTo(String value) {
            addCriterion("SFSC >=", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscLessThan(String value) {
            addCriterion("SFSC <", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscLessThanOrEqualTo(String value) {
            addCriterion("SFSC <=", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscLike(String value) {
            addCriterion("SFSC like", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscNotLike(String value) {
            addCriterion("SFSC not like", value, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscIn(List<String> values) {
            addCriterion("SFSC in", values, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscNotIn(List<String> values) {
            addCriterion("SFSC not in", values, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscBetween(String value1, String value2) {
            addCriterion("SFSC between", value1, value2, "sfsc");
            return (Criteria) this;
        }

        public Criteria andSfscNotBetween(String value1, String value2) {
            addCriterion("SFSC not between", value1, value2, "sfsc");
            return (Criteria) this;
        }

        public Criteria andDwjcIsNull() {
            addCriterion("DWJC is null");
            return (Criteria) this;
        }

        public Criteria andDwjcIsNotNull() {
            addCriterion("DWJC is not null");
            return (Criteria) this;
        }

        public Criteria andDwjcEqualTo(String value) {
            addCriterion("DWJC =", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotEqualTo(String value) {
            addCriterion("DWJC <>", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcGreaterThan(String value) {
            addCriterion("DWJC >", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcGreaterThanOrEqualTo(String value) {
            addCriterion("DWJC >=", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLessThan(String value) {
            addCriterion("DWJC <", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLessThanOrEqualTo(String value) {
            addCriterion("DWJC <=", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcLike(String value) {
            addCriterion("DWJC like", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotLike(String value) {
            addCriterion("DWJC not like", value, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcIn(List<String> values) {
            addCriterion("DWJC in", values, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotIn(List<String> values) {
            addCriterion("DWJC not in", values, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcBetween(String value1, String value2) {
            addCriterion("DWJC between", value1, value2, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwjcNotBetween(String value1, String value2) {
            addCriterion("DWJC not between", value1, value2, "dwjc");
            return (Criteria) this;
        }

        public Criteria andDwsxIsNull() {
            addCriterion("DWSX is null");
            return (Criteria) this;
        }

        public Criteria andDwsxIsNotNull() {
            addCriterion("DWSX is not null");
            return (Criteria) this;
        }

        public Criteria andDwsxEqualTo(String value) {
            addCriterion("DWSX =", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxNotEqualTo(String value) {
            addCriterion("DWSX <>", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxGreaterThan(String value) {
            addCriterion("DWSX >", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxGreaterThanOrEqualTo(String value) {
            addCriterion("DWSX >=", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxLessThan(String value) {
            addCriterion("DWSX <", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxLessThanOrEqualTo(String value) {
            addCriterion("DWSX <=", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxLike(String value) {
            addCriterion("DWSX like", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxNotLike(String value) {
            addCriterion("DWSX not like", value, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxIn(List<String> values) {
            addCriterion("DWSX in", values, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxNotIn(List<String> values) {
            addCriterion("DWSX not in", values, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxBetween(String value1, String value2) {
            addCriterion("DWSX between", value1, value2, "dwsx");
            return (Criteria) this;
        }

        public Criteria andDwsxNotBetween(String value1, String value2) {
            addCriterion("DWSX not between", value1, value2, "dwsx");
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