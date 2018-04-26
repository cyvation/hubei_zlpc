package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class YX_PC_FZExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YX_PC_FZExample() {
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

        public Criteria andPchdbmIsNull() {
            addCriterion("PCHDBM is null");
            return (Criteria) this;
        }

        public Criteria andPchdbmIsNotNull() {
            addCriterion("PCHDBM is not null");
            return (Criteria) this;
        }

        public Criteria andPchdbmEqualTo(String value) {
            addCriterion("PCHDBM =", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotEqualTo(String value) {
            addCriterion("PCHDBM <>", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmGreaterThan(String value) {
            addCriterion("PCHDBM >", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCHDBM >=", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLessThan(String value) {
            addCriterion("PCHDBM <", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLessThanOrEqualTo(String value) {
            addCriterion("PCHDBM <=", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLike(String value) {
            addCriterion("PCHDBM like", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotLike(String value) {
            addCriterion("PCHDBM not like", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmIn(List<String> values) {
            addCriterion("PCHDBM in", values, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotIn(List<String> values) {
            addCriterion("PCHDBM not in", values, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmBetween(String value1, String value2) {
            addCriterion("PCHDBM between", value1, value2, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotBetween(String value1, String value2) {
            addCriterion("PCHDBM not between", value1, value2, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPczbmIsNull() {
            addCriterion("PCZBM is null");
            return (Criteria) this;
        }

        public Criteria andPczbmIsNotNull() {
            addCriterion("PCZBM is not null");
            return (Criteria) this;
        }

        public Criteria andPczbmEqualTo(String value) {
            addCriterion("PCZBM =", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmNotEqualTo(String value) {
            addCriterion("PCZBM <>", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmGreaterThan(String value) {
            addCriterion("PCZBM >", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCZBM >=", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmLessThan(String value) {
            addCriterion("PCZBM <", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmLessThanOrEqualTo(String value) {
            addCriterion("PCZBM <=", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmLike(String value) {
            addCriterion("PCZBM like", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmNotLike(String value) {
            addCriterion("PCZBM not like", value, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmIn(List<String> values) {
            addCriterion("PCZBM in", values, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmNotIn(List<String> values) {
            addCriterion("PCZBM not in", values, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmBetween(String value1, String value2) {
            addCriterion("PCZBM between", value1, value2, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczbmNotBetween(String value1, String value2) {
            addCriterion("PCZBM not between", value1, value2, "pczbm");
            return (Criteria) this;
        }

        public Criteria andPczmcIsNull() {
            addCriterion("PCZMC is null");
            return (Criteria) this;
        }

        public Criteria andPczmcIsNotNull() {
            addCriterion("PCZMC is not null");
            return (Criteria) this;
        }

        public Criteria andPczmcEqualTo(String value) {
            addCriterion("PCZMC =", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcNotEqualTo(String value) {
            addCriterion("PCZMC <>", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcGreaterThan(String value) {
            addCriterion("PCZMC >", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCZMC >=", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcLessThan(String value) {
            addCriterion("PCZMC <", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcLessThanOrEqualTo(String value) {
            addCriterion("PCZMC <=", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcLike(String value) {
            addCriterion("PCZMC like", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcNotLike(String value) {
            addCriterion("PCZMC not like", value, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcIn(List<String> values) {
            addCriterion("PCZMC in", values, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcNotIn(List<String> values) {
            addCriterion("PCZMC not in", values, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcBetween(String value1, String value2) {
            addCriterion("PCZMC between", value1, value2, "pczmc");
            return (Criteria) this;
        }

        public Criteria andPczmcNotBetween(String value1, String value2) {
            addCriterion("PCZMC not between", value1, value2, "pczmc");
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