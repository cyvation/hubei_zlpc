package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class Tyyw_FldmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Tyyw_FldmExample() {
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

        public Criteria andDmIsNull() {
            addCriterion("DM is null");
            return (Criteria) this;
        }

        public Criteria andDmIsNotNull() {
            addCriterion("DM is not null");
            return (Criteria) this;
        }

        public Criteria andDmEqualTo(String value) {
            addCriterion("DM =", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmNotEqualTo(String value) {
            addCriterion("DM <>", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmGreaterThan(String value) {
            addCriterion("DM >", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmGreaterThanOrEqualTo(String value) {
            addCriterion("DM >=", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmLessThan(String value) {
            addCriterion("DM <", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmLessThanOrEqualTo(String value) {
            addCriterion("DM <=", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmLike(String value) {
            addCriterion("DM like", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmNotLike(String value) {
            addCriterion("DM not like", value, "dm");
            return (Criteria) this;
        }

        public Criteria andDmIn(List<String> values) {
            addCriterion("DM in", values, "dm");
            return (Criteria) this;
        }

        public Criteria andDmNotIn(List<String> values) {
            addCriterion("DM not in", values, "dm");
            return (Criteria) this;
        }

        public Criteria andDmBetween(String value1, String value2) {
            addCriterion("DM between", value1, value2, "dm");
            return (Criteria) this;
        }

        public Criteria andDmNotBetween(String value1, String value2) {
            addCriterion("DM not between", value1, value2, "dm");
            return (Criteria) this;
        }

        public Criteria andLbbmIsNull() {
            addCriterion("LBBM is null");
            return (Criteria) this;
        }

        public Criteria andLbbmIsNotNull() {
            addCriterion("LBBM is not null");
            return (Criteria) this;
        }

        public Criteria andLbbmEqualTo(String value) {
            addCriterion("LBBM =", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmNotEqualTo(String value) {
            addCriterion("LBBM <>", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmGreaterThan(String value) {
            addCriterion("LBBM >", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmGreaterThanOrEqualTo(String value) {
            addCriterion("LBBM >=", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmLessThan(String value) {
            addCriterion("LBBM <", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmLessThanOrEqualTo(String value) {
            addCriterion("LBBM <=", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmLike(String value) {
            addCriterion("LBBM like", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmNotLike(String value) {
            addCriterion("LBBM not like", value, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmIn(List<String> values) {
            addCriterion("LBBM in", values, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmNotIn(List<String> values) {
            addCriterion("LBBM not in", values, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmBetween(String value1, String value2) {
            addCriterion("LBBM between", value1, value2, "lbbm");
            return (Criteria) this;
        }

        public Criteria andLbbmNotBetween(String value1, String value2) {
            addCriterion("LBBM not between", value1, value2, "lbbm");
            return (Criteria) this;
        }

        public Criteria andFdmIsNull() {
            addCriterion("FDM is null");
            return (Criteria) this;
        }

        public Criteria andFdmIsNotNull() {
            addCriterion("FDM is not null");
            return (Criteria) this;
        }

        public Criteria andFdmEqualTo(String value) {
            addCriterion("FDM =", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmNotEqualTo(String value) {
            addCriterion("FDM <>", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmGreaterThan(String value) {
            addCriterion("FDM >", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmGreaterThanOrEqualTo(String value) {
            addCriterion("FDM >=", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmLessThan(String value) {
            addCriterion("FDM <", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmLessThanOrEqualTo(String value) {
            addCriterion("FDM <=", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmLike(String value) {
            addCriterion("FDM like", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmNotLike(String value) {
            addCriterion("FDM not like", value, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmIn(List<String> values) {
            addCriterion("FDM in", values, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmNotIn(List<String> values) {
            addCriterion("FDM not in", values, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmBetween(String value1, String value2) {
            addCriterion("FDM between", value1, value2, "fdm");
            return (Criteria) this;
        }

        public Criteria andFdmNotBetween(String value1, String value2) {
            addCriterion("FDM not between", value1, value2, "fdm");
            return (Criteria) this;
        }

        public Criteria andMcIsNull() {
            addCriterion("MC is null");
            return (Criteria) this;
        }

        public Criteria andMcIsNotNull() {
            addCriterion("MC is not null");
            return (Criteria) this;
        }

        public Criteria andMcEqualTo(String value) {
            addCriterion("MC =", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotEqualTo(String value) {
            addCriterion("MC <>", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThan(String value) {
            addCriterion("MC >", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThanOrEqualTo(String value) {
            addCriterion("MC >=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThan(String value) {
            addCriterion("MC <", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThanOrEqualTo(String value) {
            addCriterion("MC <=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLike(String value) {
            addCriterion("MC like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotLike(String value) {
            addCriterion("MC not like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcIn(List<String> values) {
            addCriterion("MC in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotIn(List<String> values) {
            addCriterion("MC not in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcBetween(String value1, String value2) {
            addCriterion("MC between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotBetween(String value1, String value2) {
            addCriterion("MC not between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andSmIsNull() {
            addCriterion("SM is null");
            return (Criteria) this;
        }

        public Criteria andSmIsNotNull() {
            addCriterion("SM is not null");
            return (Criteria) this;
        }

        public Criteria andSmEqualTo(String value) {
            addCriterion("SM =", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotEqualTo(String value) {
            addCriterion("SM <>", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThan(String value) {
            addCriterion("SM >", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThanOrEqualTo(String value) {
            addCriterion("SM >=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThan(String value) {
            addCriterion("SM <", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThanOrEqualTo(String value) {
            addCriterion("SM <=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLike(String value) {
            addCriterion("SM like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotLike(String value) {
            addCriterion("SM not like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmIn(List<String> values) {
            addCriterion("SM in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotIn(List<String> values) {
            addCriterion("SM not in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmBetween(String value1, String value2) {
            addCriterion("SM between", value1, value2, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotBetween(String value1, String value2) {
            addCriterion("SM not between", value1, value2, "sm");
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