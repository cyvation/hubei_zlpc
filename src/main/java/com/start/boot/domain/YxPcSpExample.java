package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YxPcSpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxPcSpExample() {
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

        public Criteria andPcspbmIsNull() {
            addCriterion("PCSPBM is null");
            return (Criteria) this;
        }

        public Criteria andPcspbmIsNotNull() {
            addCriterion("PCSPBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcspbmEqualTo(String value) {
            addCriterion("PCSPBM =", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmNotEqualTo(String value) {
            addCriterion("PCSPBM <>", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmGreaterThan(String value) {
            addCriterion("PCSPBM >", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCSPBM >=", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmLessThan(String value) {
            addCriterion("PCSPBM <", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmLessThanOrEqualTo(String value) {
            addCriterion("PCSPBM <=", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmLike(String value) {
            addCriterion("PCSPBM like", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmNotLike(String value) {
            addCriterion("PCSPBM not like", value, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmIn(List<String> values) {
            addCriterion("PCSPBM in", values, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmNotIn(List<String> values) {
            addCriterion("PCSPBM not in", values, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmBetween(String value1, String value2) {
            addCriterion("PCSPBM between", value1, value2, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andPcspbmNotBetween(String value1, String value2) {
            addCriterion("PCSPBM not between", value1, value2, "pcspbm");
            return (Criteria) this;
        }

        public Criteria andSpwjlxIsNull() {
            addCriterion("SPWJLX is null");
            return (Criteria) this;
        }

        public Criteria andSpwjlxIsNotNull() {
            addCriterion("SPWJLX is not null");
            return (Criteria) this;
        }

        public Criteria andSpwjlxEqualTo(String value) {
            addCriterion("SPWJLX =", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxNotEqualTo(String value) {
            addCriterion("SPWJLX <>", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxGreaterThan(String value) {
            addCriterion("SPWJLX >", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxGreaterThanOrEqualTo(String value) {
            addCriterion("SPWJLX >=", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxLessThan(String value) {
            addCriterion("SPWJLX <", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxLessThanOrEqualTo(String value) {
            addCriterion("SPWJLX <=", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxLike(String value) {
            addCriterion("SPWJLX like", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxNotLike(String value) {
            addCriterion("SPWJLX not like", value, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxIn(List<String> values) {
            addCriterion("SPWJLX in", values, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxNotIn(List<String> values) {
            addCriterion("SPWJLX not in", values, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxBetween(String value1, String value2) {
            addCriterion("SPWJLX between", value1, value2, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjlxNotBetween(String value1, String value2) {
            addCriterion("SPWJLX not between", value1, value2, "spwjlx");
            return (Criteria) this;
        }

        public Criteria andSpwjbmIsNull() {
            addCriterion("SPWJBM is null");
            return (Criteria) this;
        }

        public Criteria andSpwjbmIsNotNull() {
            addCriterion("SPWJBM is not null");
            return (Criteria) this;
        }

        public Criteria andSpwjbmEqualTo(String value) {
            addCriterion("SPWJBM =", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmNotEqualTo(String value) {
            addCriterion("SPWJBM <>", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmGreaterThan(String value) {
            addCriterion("SPWJBM >", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmGreaterThanOrEqualTo(String value) {
            addCriterion("SPWJBM >=", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmLessThan(String value) {
            addCriterion("SPWJBM <", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmLessThanOrEqualTo(String value) {
            addCriterion("SPWJBM <=", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmLike(String value) {
            addCriterion("SPWJBM like", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmNotLike(String value) {
            addCriterion("SPWJBM not like", value, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmIn(List<String> values) {
            addCriterion("SPWJBM in", values, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmNotIn(List<String> values) {
            addCriterion("SPWJBM not in", values, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmBetween(String value1, String value2) {
            addCriterion("SPWJBM between", value1, value2, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSpwjbmNotBetween(String value1, String value2) {
            addCriterion("SPWJBM not between", value1, value2, "spwjbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmIsNull() {
            addCriterion("SSRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmIsNotNull() {
            addCriterion("SSRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmEqualTo(String value) {
            addCriterion("SSRDWBM =", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmNotEqualTo(String value) {
            addCriterion("SSRDWBM <>", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmGreaterThan(String value) {
            addCriterion("SSRDWBM >", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("SSRDWBM >=", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmLessThan(String value) {
            addCriterion("SSRDWBM <", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmLessThanOrEqualTo(String value) {
            addCriterion("SSRDWBM <=", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmLike(String value) {
            addCriterion("SSRDWBM like", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmNotLike(String value) {
            addCriterion("SSRDWBM not like", value, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmIn(List<String> values) {
            addCriterion("SSRDWBM in", values, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmNotIn(List<String> values) {
            addCriterion("SSRDWBM not in", values, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmBetween(String value1, String value2) {
            addCriterion("SSRDWBM between", value1, value2, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwbmNotBetween(String value1, String value2) {
            addCriterion("SSRDWBM not between", value1, value2, "ssrdwbm");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcIsNull() {
            addCriterion("SSRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcIsNotNull() {
            addCriterion("SSRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcEqualTo(String value) {
            addCriterion("SSRDWMC =", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcNotEqualTo(String value) {
            addCriterion("SSRDWMC <>", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcGreaterThan(String value) {
            addCriterion("SSRDWMC >", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("SSRDWMC >=", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcLessThan(String value) {
            addCriterion("SSRDWMC <", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcLessThanOrEqualTo(String value) {
            addCriterion("SSRDWMC <=", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcLike(String value) {
            addCriterion("SSRDWMC like", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcNotLike(String value) {
            addCriterion("SSRDWMC not like", value, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcIn(List<String> values) {
            addCriterion("SSRDWMC in", values, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcNotIn(List<String> values) {
            addCriterion("SSRDWMC not in", values, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcBetween(String value1, String value2) {
            addCriterion("SSRDWMC between", value1, value2, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrdwmcNotBetween(String value1, String value2) {
            addCriterion("SSRDWMC not between", value1, value2, "ssrdwmc");
            return (Criteria) this;
        }

        public Criteria andSsrghIsNull() {
            addCriterion("SSRGH is null");
            return (Criteria) this;
        }

        public Criteria andSsrghIsNotNull() {
            addCriterion("SSRGH is not null");
            return (Criteria) this;
        }

        public Criteria andSsrghEqualTo(String value) {
            addCriterion("SSRGH =", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghNotEqualTo(String value) {
            addCriterion("SSRGH <>", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghGreaterThan(String value) {
            addCriterion("SSRGH >", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghGreaterThanOrEqualTo(String value) {
            addCriterion("SSRGH >=", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghLessThan(String value) {
            addCriterion("SSRGH <", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghLessThanOrEqualTo(String value) {
            addCriterion("SSRGH <=", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghLike(String value) {
            addCriterion("SSRGH like", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghNotLike(String value) {
            addCriterion("SSRGH not like", value, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghIn(List<String> values) {
            addCriterion("SSRGH in", values, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghNotIn(List<String> values) {
            addCriterion("SSRGH not in", values, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghBetween(String value1, String value2) {
            addCriterion("SSRGH between", value1, value2, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrghNotBetween(String value1, String value2) {
            addCriterion("SSRGH not between", value1, value2, "ssrgh");
            return (Criteria) this;
        }

        public Criteria andSsrxmIsNull() {
            addCriterion("SSRXM is null");
            return (Criteria) this;
        }

        public Criteria andSsrxmIsNotNull() {
            addCriterion("SSRXM is not null");
            return (Criteria) this;
        }

        public Criteria andSsrxmEqualTo(String value) {
            addCriterion("SSRXM =", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmNotEqualTo(String value) {
            addCriterion("SSRXM <>", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmGreaterThan(String value) {
            addCriterion("SSRXM >", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmGreaterThanOrEqualTo(String value) {
            addCriterion("SSRXM >=", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmLessThan(String value) {
            addCriterion("SSRXM <", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmLessThanOrEqualTo(String value) {
            addCriterion("SSRXM <=", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmLike(String value) {
            addCriterion("SSRXM like", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmNotLike(String value) {
            addCriterion("SSRXM not like", value, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmIn(List<String> values) {
            addCriterion("SSRXM in", values, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmNotIn(List<String> values) {
            addCriterion("SSRXM not in", values, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmBetween(String value1, String value2) {
            addCriterion("SSRXM between", value1, value2, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrxmNotBetween(String value1, String value2) {
            addCriterion("SSRXM not between", value1, value2, "ssrxm");
            return (Criteria) this;
        }

        public Criteria andSsrqIsNull() {
            addCriterion("SSRQ is null");
            return (Criteria) this;
        }

        public Criteria andSsrqIsNotNull() {
            addCriterion("SSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSsrqEqualTo(Date value) {
            addCriterion("SSRQ =", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqNotEqualTo(Date value) {
            addCriterion("SSRQ <>", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqGreaterThan(Date value) {
            addCriterion("SSRQ >", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqGreaterThanOrEqualTo(Date value) {
            addCriterion("SSRQ >=", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqLessThan(Date value) {
            addCriterion("SSRQ <", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqLessThanOrEqualTo(Date value) {
            addCriterion("SSRQ <=", value, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqIn(List<Date> values) {
            addCriterion("SSRQ in", values, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqNotIn(List<Date> values) {
            addCriterion("SSRQ not in", values, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqBetween(Date value1, Date value2) {
            addCriterion("SSRQ between", value1, value2, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSsrqNotBetween(Date value1, Date value2) {
            addCriterion("SSRQ not between", value1, value2, "ssrq");
            return (Criteria) this;
        }

        public Criteria andSprdwbmIsNull() {
            addCriterion("SPRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andSprdwbmIsNotNull() {
            addCriterion("SPRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andSprdwbmEqualTo(String value) {
            addCriterion("SPRDWBM =", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmNotEqualTo(String value) {
            addCriterion("SPRDWBM <>", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmGreaterThan(String value) {
            addCriterion("SPRDWBM >", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("SPRDWBM >=", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmLessThan(String value) {
            addCriterion("SPRDWBM <", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmLessThanOrEqualTo(String value) {
            addCriterion("SPRDWBM <=", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmLike(String value) {
            addCriterion("SPRDWBM like", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmNotLike(String value) {
            addCriterion("SPRDWBM not like", value, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmIn(List<String> values) {
            addCriterion("SPRDWBM in", values, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmNotIn(List<String> values) {
            addCriterion("SPRDWBM not in", values, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmBetween(String value1, String value2) {
            addCriterion("SPRDWBM between", value1, value2, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwbmNotBetween(String value1, String value2) {
            addCriterion("SPRDWBM not between", value1, value2, "sprdwbm");
            return (Criteria) this;
        }

        public Criteria andSprdwmcIsNull() {
            addCriterion("SPRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andSprdwmcIsNotNull() {
            addCriterion("SPRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andSprdwmcEqualTo(String value) {
            addCriterion("SPRDWMC =", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcNotEqualTo(String value) {
            addCriterion("SPRDWMC <>", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcGreaterThan(String value) {
            addCriterion("SPRDWMC >", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("SPRDWMC >=", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcLessThan(String value) {
            addCriterion("SPRDWMC <", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcLessThanOrEqualTo(String value) {
            addCriterion("SPRDWMC <=", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcLike(String value) {
            addCriterion("SPRDWMC like", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcNotLike(String value) {
            addCriterion("SPRDWMC not like", value, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcIn(List<String> values) {
            addCriterion("SPRDWMC in", values, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcNotIn(List<String> values) {
            addCriterion("SPRDWMC not in", values, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcBetween(String value1, String value2) {
            addCriterion("SPRDWMC between", value1, value2, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprdwmcNotBetween(String value1, String value2) {
            addCriterion("SPRDWMC not between", value1, value2, "sprdwmc");
            return (Criteria) this;
        }

        public Criteria andSprghIsNull() {
            addCriterion("SPRGH is null");
            return (Criteria) this;
        }

        public Criteria andSprghIsNotNull() {
            addCriterion("SPRGH is not null");
            return (Criteria) this;
        }

        public Criteria andSprghEqualTo(String value) {
            addCriterion("SPRGH =", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghNotEqualTo(String value) {
            addCriterion("SPRGH <>", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghGreaterThan(String value) {
            addCriterion("SPRGH >", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghGreaterThanOrEqualTo(String value) {
            addCriterion("SPRGH >=", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghLessThan(String value) {
            addCriterion("SPRGH <", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghLessThanOrEqualTo(String value) {
            addCriterion("SPRGH <=", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghLike(String value) {
            addCriterion("SPRGH like", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghNotLike(String value) {
            addCriterion("SPRGH not like", value, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghIn(List<String> values) {
            addCriterion("SPRGH in", values, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghNotIn(List<String> values) {
            addCriterion("SPRGH not in", values, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghBetween(String value1, String value2) {
            addCriterion("SPRGH between", value1, value2, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprghNotBetween(String value1, String value2) {
            addCriterion("SPRGH not between", value1, value2, "sprgh");
            return (Criteria) this;
        }

        public Criteria andSprxmIsNull() {
            addCriterion("SPRXM is null");
            return (Criteria) this;
        }

        public Criteria andSprxmIsNotNull() {
            addCriterion("SPRXM is not null");
            return (Criteria) this;
        }

        public Criteria andSprxmEqualTo(String value) {
            addCriterion("SPRXM =", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmNotEqualTo(String value) {
            addCriterion("SPRXM <>", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmGreaterThan(String value) {
            addCriterion("SPRXM >", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmGreaterThanOrEqualTo(String value) {
            addCriterion("SPRXM >=", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmLessThan(String value) {
            addCriterion("SPRXM <", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmLessThanOrEqualTo(String value) {
            addCriterion("SPRXM <=", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmLike(String value) {
            addCriterion("SPRXM like", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmNotLike(String value) {
            addCriterion("SPRXM not like", value, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmIn(List<String> values) {
            addCriterion("SPRXM in", values, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmNotIn(List<String> values) {
            addCriterion("SPRXM not in", values, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmBetween(String value1, String value2) {
            addCriterion("SPRXM between", value1, value2, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprxmNotBetween(String value1, String value2) {
            addCriterion("SPRXM not between", value1, value2, "sprxm");
            return (Criteria) this;
        }

        public Criteria andSprqIsNull() {
            addCriterion("SPRQ is null");
            return (Criteria) this;
        }

        public Criteria andSprqIsNotNull() {
            addCriterion("SPRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSprqEqualTo(Date value) {
            addCriterion("SPRQ =", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqNotEqualTo(Date value) {
            addCriterion("SPRQ <>", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqGreaterThan(Date value) {
            addCriterion("SPRQ >", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqGreaterThanOrEqualTo(Date value) {
            addCriterion("SPRQ >=", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqLessThan(Date value) {
            addCriterion("SPRQ <", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqLessThanOrEqualTo(Date value) {
            addCriterion("SPRQ <=", value, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqIn(List<Date> values) {
            addCriterion("SPRQ in", values, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqNotIn(List<Date> values) {
            addCriterion("SPRQ not in", values, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqBetween(Date value1, Date value2) {
            addCriterion("SPRQ between", value1, value2, "sprq");
            return (Criteria) this;
        }

        public Criteria andSprqNotBetween(Date value1, Date value2) {
            addCriterion("SPRQ not between", value1, value2, "sprq");
            return (Criteria) this;
        }

        public Criteria andSpjlIsNull() {
            addCriterion("SPJL is null");
            return (Criteria) this;
        }

        public Criteria andSpjlIsNotNull() {
            addCriterion("SPJL is not null");
            return (Criteria) this;
        }

        public Criteria andSpjlEqualTo(String value) {
            addCriterion("SPJL =", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlNotEqualTo(String value) {
            addCriterion("SPJL <>", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlGreaterThan(String value) {
            addCriterion("SPJL >", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlGreaterThanOrEqualTo(String value) {
            addCriterion("SPJL >=", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlLessThan(String value) {
            addCriterion("SPJL <", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlLessThanOrEqualTo(String value) {
            addCriterion("SPJL <=", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlLike(String value) {
            addCriterion("SPJL like", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlNotLike(String value) {
            addCriterion("SPJL not like", value, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlIn(List<String> values) {
            addCriterion("SPJL in", values, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlNotIn(List<String> values) {
            addCriterion("SPJL not in", values, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlBetween(String value1, String value2) {
            addCriterion("SPJL between", value1, value2, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpjlNotBetween(String value1, String value2) {
            addCriterion("SPJL not between", value1, value2, "spjl");
            return (Criteria) this;
        }

        public Criteria andSpyjIsNull() {
            addCriterion("SPYJ is null");
            return (Criteria) this;
        }

        public Criteria andSpyjIsNotNull() {
            addCriterion("SPYJ is not null");
            return (Criteria) this;
        }

        public Criteria andSpyjEqualTo(String value) {
            addCriterion("SPYJ =", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotEqualTo(String value) {
            addCriterion("SPYJ <>", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjGreaterThan(String value) {
            addCriterion("SPYJ >", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjGreaterThanOrEqualTo(String value) {
            addCriterion("SPYJ >=", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLessThan(String value) {
            addCriterion("SPYJ <", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLessThanOrEqualTo(String value) {
            addCriterion("SPYJ <=", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLike(String value) {
            addCriterion("SPYJ like", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotLike(String value) {
            addCriterion("SPYJ not like", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjIn(List<String> values) {
            addCriterion("SPYJ in", values, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotIn(List<String> values) {
            addCriterion("SPYJ not in", values, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjBetween(String value1, String value2) {
            addCriterion("SPYJ between", value1, value2, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotBetween(String value1, String value2) {
            addCriterion("SPYJ not between", value1, value2, "spyj");
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

        public Criteria andCjsjIsNull() {
            addCriterion("CJSJ is null");
            return (Criteria) this;
        }

        public Criteria andCjsjIsNotNull() {
            addCriterion("CJSJ is not null");
            return (Criteria) this;
        }

        public Criteria andCjsjEqualTo(Date value) {
            addCriterion("CJSJ =", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotEqualTo(Date value) {
            addCriterion("CJSJ <>", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThan(Date value) {
            addCriterion("CJSJ >", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjGreaterThanOrEqualTo(Date value) {
            addCriterion("CJSJ >=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThan(Date value) {
            addCriterion("CJSJ <", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjLessThanOrEqualTo(Date value) {
            addCriterion("CJSJ <=", value, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjIn(List<Date> values) {
            addCriterion("CJSJ in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotIn(List<Date> values) {
            addCriterion("CJSJ not in", values, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjBetween(Date value1, Date value2) {
            addCriterion("CJSJ between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andCjsjNotBetween(Date value1, Date value2) {
            addCriterion("CJSJ not between", value1, value2, "cjsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjIsNull() {
            addCriterion("ZHXGSJ is null");
            return (Criteria) this;
        }

        public Criteria andZhxgsjIsNotNull() {
            addCriterion("ZHXGSJ is not null");
            return (Criteria) this;
        }

        public Criteria andZhxgsjEqualTo(Date value) {
            addCriterion("ZHXGSJ =", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjNotEqualTo(Date value) {
            addCriterion("ZHXGSJ <>", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjGreaterThan(Date value) {
            addCriterion("ZHXGSJ >", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjGreaterThanOrEqualTo(Date value) {
            addCriterion("ZHXGSJ >=", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjLessThan(Date value) {
            addCriterion("ZHXGSJ <", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjLessThanOrEqualTo(Date value) {
            addCriterion("ZHXGSJ <=", value, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjIn(List<Date> values) {
            addCriterion("ZHXGSJ in", values, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjNotIn(List<Date> values) {
            addCriterion("ZHXGSJ not in", values, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjBetween(Date value1, Date value2) {
            addCriterion("ZHXGSJ between", value1, value2, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andZhxgsjNotBetween(Date value1, Date value2) {
            addCriterion("ZHXGSJ not between", value1, value2, "zhxgsj");
            return (Criteria) this;
        }

        public Criteria andSpjsbmIsNull() {
            addCriterion("SPJSBM is null");
            return (Criteria) this;
        }

        public Criteria andSpjsbmIsNotNull() {
            addCriterion("SPJSBM is not null");
            return (Criteria) this;
        }

        public Criteria andSpjsbmEqualTo(String value) {
            addCriterion("SPJSBM =", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmNotEqualTo(String value) {
            addCriterion("SPJSBM <>", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmGreaterThan(String value) {
            addCriterion("SPJSBM >", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmGreaterThanOrEqualTo(String value) {
            addCriterion("SPJSBM >=", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmLessThan(String value) {
            addCriterion("SPJSBM <", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmLessThanOrEqualTo(String value) {
            addCriterion("SPJSBM <=", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmLike(String value) {
            addCriterion("SPJSBM like", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmNotLike(String value) {
            addCriterion("SPJSBM not like", value, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmIn(List<String> values) {
            addCriterion("SPJSBM in", values, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmNotIn(List<String> values) {
            addCriterion("SPJSBM not in", values, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmBetween(String value1, String value2) {
            addCriterion("SPJSBM between", value1, value2, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsbmNotBetween(String value1, String value2) {
            addCriterion("SPJSBM not between", value1, value2, "spjsbm");
            return (Criteria) this;
        }

        public Criteria andSpjsmcIsNull() {
            addCriterion("SPJSMC is null");
            return (Criteria) this;
        }

        public Criteria andSpjsmcIsNotNull() {
            addCriterion("SPJSMC is not null");
            return (Criteria) this;
        }

        public Criteria andSpjsmcEqualTo(String value) {
            addCriterion("SPJSMC =", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcNotEqualTo(String value) {
            addCriterion("SPJSMC <>", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcGreaterThan(String value) {
            addCriterion("SPJSMC >", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcGreaterThanOrEqualTo(String value) {
            addCriterion("SPJSMC >=", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcLessThan(String value) {
            addCriterion("SPJSMC <", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcLessThanOrEqualTo(String value) {
            addCriterion("SPJSMC <=", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcLike(String value) {
            addCriterion("SPJSMC like", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcNotLike(String value) {
            addCriterion("SPJSMC not like", value, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcIn(List<String> values) {
            addCriterion("SPJSMC in", values, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcNotIn(List<String> values) {
            addCriterion("SPJSMC not in", values, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcBetween(String value1, String value2) {
            addCriterion("SPJSMC between", value1, value2, "spjsmc");
            return (Criteria) this;
        }

        public Criteria andSpjsmcNotBetween(String value1, String value2) {
            addCriterion("SPJSMC not between", value1, value2, "spjsmc");
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