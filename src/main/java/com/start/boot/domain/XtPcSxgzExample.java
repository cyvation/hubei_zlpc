package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XtPcSxgzExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtPcSxgzExample() {
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

        public Criteria andGzbmIsNull() {
            addCriterion("GZBM is null");
            return (Criteria) this;
        }

        public Criteria andGzbmIsNotNull() {
            addCriterion("GZBM is not null");
            return (Criteria) this;
        }

        public Criteria andGzbmEqualTo(String value) {
            addCriterion("GZBM =", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmNotEqualTo(String value) {
            addCriterion("GZBM <>", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmGreaterThan(String value) {
            addCriterion("GZBM >", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmGreaterThanOrEqualTo(String value) {
            addCriterion("GZBM >=", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmLessThan(String value) {
            addCriterion("GZBM <", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmLessThanOrEqualTo(String value) {
            addCriterion("GZBM <=", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmLike(String value) {
            addCriterion("GZBM like", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmNotLike(String value) {
            addCriterion("GZBM not like", value, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmIn(List<String> values) {
            addCriterion("GZBM in", values, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmNotIn(List<String> values) {
            addCriterion("GZBM not in", values, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmBetween(String value1, String value2) {
            addCriterion("GZBM between", value1, value2, "gzbm");
            return (Criteria) this;
        }

        public Criteria andGzbmNotBetween(String value1, String value2) {
            addCriterion("GZBM not between", value1, value2, "gzbm");
            return (Criteria) this;
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

        public Criteria andPcflbmIsNull() {
            addCriterion("PCFLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcflbmIsNotNull() {
            addCriterion("PCFLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcflbmEqualTo(String value) {
            addCriterion("PCFLBM =", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmNotEqualTo(String value) {
            addCriterion("PCFLBM <>", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmGreaterThan(String value) {
            addCriterion("PCFLBM >", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCFLBM >=", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmLessThan(String value) {
            addCriterion("PCFLBM <", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmLessThanOrEqualTo(String value) {
            addCriterion("PCFLBM <=", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmLike(String value) {
            addCriterion("PCFLBM like", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmNotLike(String value) {
            addCriterion("PCFLBM not like", value, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmIn(List<String> values) {
            addCriterion("PCFLBM in", values, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmNotIn(List<String> values) {
            addCriterion("PCFLBM not in", values, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmBetween(String value1, String value2) {
            addCriterion("PCFLBM between", value1, value2, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andPcflbmNotBetween(String value1, String value2) {
            addCriterion("PCFLBM not between", value1, value2, "pcflbm");
            return (Criteria) this;
        }

        public Criteria andGzmcIsNull() {
            addCriterion("GZMC is null");
            return (Criteria) this;
        }

        public Criteria andGzmcIsNotNull() {
            addCriterion("GZMC is not null");
            return (Criteria) this;
        }

        public Criteria andGzmcEqualTo(String value) {
            addCriterion("GZMC =", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcNotEqualTo(String value) {
            addCriterion("GZMC <>", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcGreaterThan(String value) {
            addCriterion("GZMC >", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcGreaterThanOrEqualTo(String value) {
            addCriterion("GZMC >=", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcLessThan(String value) {
            addCriterion("GZMC <", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcLessThanOrEqualTo(String value) {
            addCriterion("GZMC <=", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcLike(String value) {
            addCriterion("GZMC like", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcNotLike(String value) {
            addCriterion("GZMC not like", value, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcIn(List<String> values) {
            addCriterion("GZMC in", values, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcNotIn(List<String> values) {
            addCriterion("GZMC not in", values, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcBetween(String value1, String value2) {
            addCriterion("GZMC between", value1, value2, "gzmc");
            return (Criteria) this;
        }

        public Criteria andGzmcNotBetween(String value1, String value2) {
            addCriterion("GZMC not between", value1, value2, "gzmc");
            return (Criteria) this;
        }

        public Criteria andSxcxIsNull() {
            addCriterion("SXCX is null");
            return (Criteria) this;
        }

        public Criteria andSxcxIsNotNull() {
            addCriterion("SXCX is not null");
            return (Criteria) this;
        }

        public Criteria andSxcxEqualTo(String value) {
            addCriterion("SXCX =", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxNotEqualTo(String value) {
            addCriterion("SXCX <>", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxGreaterThan(String value) {
            addCriterion("SXCX >", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxGreaterThanOrEqualTo(String value) {
            addCriterion("SXCX >=", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxLessThan(String value) {
            addCriterion("SXCX <", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxLessThanOrEqualTo(String value) {
            addCriterion("SXCX <=", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxLike(String value) {
            addCriterion("SXCX like", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxNotLike(String value) {
            addCriterion("SXCX not like", value, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxIn(List<String> values) {
            addCriterion("SXCX in", values, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxNotIn(List<String> values) {
            addCriterion("SXCX not in", values, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxBetween(String value1, String value2) {
            addCriterion("SXCX between", value1, value2, "sxcx");
            return (Criteria) this;
        }

        public Criteria andSxcxNotBetween(String value1, String value2) {
            addCriterion("SXCX not between", value1, value2, "sxcx");
            return (Criteria) this;
        }

        public Criteria andCxcsIsNull() {
            addCriterion("CXCS is null");
            return (Criteria) this;
        }

        public Criteria andCxcsIsNotNull() {
            addCriterion("CXCS is not null");
            return (Criteria) this;
        }

        public Criteria andCxcsEqualTo(String value) {
            addCriterion("CXCS =", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotEqualTo(String value) {
            addCriterion("CXCS <>", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsGreaterThan(String value) {
            addCriterion("CXCS >", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsGreaterThanOrEqualTo(String value) {
            addCriterion("CXCS >=", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLessThan(String value) {
            addCriterion("CXCS <", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLessThanOrEqualTo(String value) {
            addCriterion("CXCS <=", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsLike(String value) {
            addCriterion("CXCS like", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotLike(String value) {
            addCriterion("CXCS not like", value, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsIn(List<String> values) {
            addCriterion("CXCS in", values, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotIn(List<String> values) {
            addCriterion("CXCS not in", values, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsBetween(String value1, String value2) {
            addCriterion("CXCS between", value1, value2, "cxcs");
            return (Criteria) this;
        }

        public Criteria andCxcsNotBetween(String value1, String value2) {
            addCriterion("CXCS not between", value1, value2, "cxcs");
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

        public Criteria andSslbIsNull() {
            addCriterion("SSLB is null");
            return (Criteria) this;
        }

        public Criteria andSslbIsNotNull() {
            addCriterion("SSLB is not null");
            return (Criteria) this;
        }

        public Criteria andSslbEqualTo(String value) {
            addCriterion("SSLB =", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbNotEqualTo(String value) {
            addCriterion("SSLB <>", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbGreaterThan(String value) {
            addCriterion("SSLB >", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbGreaterThanOrEqualTo(String value) {
            addCriterion("SSLB >=", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbLessThan(String value) {
            addCriterion("SSLB <", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbLessThanOrEqualTo(String value) {
            addCriterion("SSLB <=", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbLike(String value) {
            addCriterion("SSLB like", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbNotLike(String value) {
            addCriterion("SSLB not like", value, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbIn(List<String> values) {
            addCriterion("SSLB in", values, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbNotIn(List<String> values) {
            addCriterion("SSLB not in", values, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbBetween(String value1, String value2) {
            addCriterion("SSLB between", value1, value2, "sslb");
            return (Criteria) this;
        }

        public Criteria andSslbNotBetween(String value1, String value2) {
            addCriterion("SSLB not between", value1, value2, "sslb");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("XH is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("XH is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(BigDecimal value) {
            addCriterion("XH =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(BigDecimal value) {
            addCriterion("XH <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(BigDecimal value) {
            addCriterion("XH >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("XH >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(BigDecimal value) {
            addCriterion("XH <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("XH <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<BigDecimal> values) {
            addCriterion("XH in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<BigDecimal> values) {
            addCriterion("XH not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("XH between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("XH not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andPcmbjIsNull() {
            addCriterion("PCMBJ is null");
            return (Criteria) this;
        }

        public Criteria andPcmbjIsNotNull() {
            addCriterion("PCMBJ is not null");
            return (Criteria) this;
        }

        public Criteria andPcmbjEqualTo(String value) {
            addCriterion("PCMBJ =", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjNotEqualTo(String value) {
            addCriterion("PCMBJ <>", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjGreaterThan(String value) {
            addCriterion("PCMBJ >", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjGreaterThanOrEqualTo(String value) {
            addCriterion("PCMBJ >=", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjLessThan(String value) {
            addCriterion("PCMBJ <", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjLessThanOrEqualTo(String value) {
            addCriterion("PCMBJ <=", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjLike(String value) {
            addCriterion("PCMBJ like", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjNotLike(String value) {
            addCriterion("PCMBJ not like", value, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjIn(List<String> values) {
            addCriterion("PCMBJ in", values, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjNotIn(List<String> values) {
            addCriterion("PCMBJ not in", values, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjBetween(String value1, String value2) {
            addCriterion("PCMBJ between", value1, value2, "pcmbj");
            return (Criteria) this;
        }

        public Criteria andPcmbjNotBetween(String value1, String value2) {
            addCriterion("PCMBJ not between", value1, value2, "pcmbj");
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