package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tyyw_JbxxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Tyyw_JbxxExample() {
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

        public Criteria andBmsahIsNull() {
            addCriterion("BMSAH is null");
            return (Criteria) this;
        }

        public Criteria andBmsahIsNotNull() {
            addCriterion("BMSAH is not null");
            return (Criteria) this;
        }

        public Criteria andBmsahEqualTo(String value) {
            addCriterion("BMSAH =", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotEqualTo(String value) {
            addCriterion("BMSAH <>", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahGreaterThan(String value) {
            addCriterion("BMSAH >", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahGreaterThanOrEqualTo(String value) {
            addCriterion("BMSAH >=", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLessThan(String value) {
            addCriterion("BMSAH <", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLessThanOrEqualTo(String value) {
            addCriterion("BMSAH <=", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLike(String value) {
            addCriterion("BMSAH like", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotLike(String value) {
            addCriterion("BMSAH not like", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahIn(List<String> values) {
            addCriterion("BMSAH in", values, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotIn(List<String> values) {
            addCriterion("BMSAH not in", values, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahBetween(String value1, String value2) {
            addCriterion("BMSAH between", value1, value2, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotBetween(String value1, String value2) {
            addCriterion("BMSAH not between", value1, value2, "bmsah");
            return (Criteria) this;
        }

        public Criteria andTysahIsNull() {
            addCriterion("TYSAH is null");
            return (Criteria) this;
        }

        public Criteria andTysahIsNotNull() {
            addCriterion("TYSAH is not null");
            return (Criteria) this;
        }

        public Criteria andTysahEqualTo(String value) {
            addCriterion("TYSAH =", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotEqualTo(String value) {
            addCriterion("TYSAH <>", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahGreaterThan(String value) {
            addCriterion("TYSAH >", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahGreaterThanOrEqualTo(String value) {
            addCriterion("TYSAH >=", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLessThan(String value) {
            addCriterion("TYSAH <", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLessThanOrEqualTo(String value) {
            addCriterion("TYSAH <=", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLike(String value) {
            addCriterion("TYSAH like", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotLike(String value) {
            addCriterion("TYSAH not like", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahIn(List<String> values) {
            addCriterion("TYSAH in", values, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotIn(List<String> values) {
            addCriterion("TYSAH not in", values, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahBetween(String value1, String value2) {
            addCriterion("TYSAH between", value1, value2, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotBetween(String value1, String value2) {
            addCriterion("TYSAH not between", value1, value2, "tysah");
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

        public Criteria andSfygxtsjIsNull() {
            addCriterion("SFYGXTSJ is null");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjIsNotNull() {
            addCriterion("SFYGXTSJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjEqualTo(String value) {
            addCriterion("SFYGXTSJ =", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjNotEqualTo(String value) {
            addCriterion("SFYGXTSJ <>", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjGreaterThan(String value) {
            addCriterion("SFYGXTSJ >", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjGreaterThanOrEqualTo(String value) {
            addCriterion("SFYGXTSJ >=", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjLessThan(String value) {
            addCriterion("SFYGXTSJ <", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjLessThanOrEqualTo(String value) {
            addCriterion("SFYGXTSJ <=", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjLike(String value) {
            addCriterion("SFYGXTSJ like", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjNotLike(String value) {
            addCriterion("SFYGXTSJ not like", value, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjIn(List<String> values) {
            addCriterion("SFYGXTSJ in", values, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjNotIn(List<String> values) {
            addCriterion("SFYGXTSJ not in", values, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjBetween(String value1, String value2) {
            addCriterion("SFYGXTSJ between", value1, value2, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andSfygxtsjNotBetween(String value1, String value2) {
            addCriterion("SFYGXTSJ not between", value1, value2, "sfygxtsj");
            return (Criteria) this;
        }

        public Criteria andCbdwBmIsNull() {
            addCriterion("CBDW_BM is null");
            return (Criteria) this;
        }

        public Criteria andCbdwBmIsNotNull() {
            addCriterion("CBDW_BM is not null");
            return (Criteria) this;
        }

        public Criteria andCbdwBmEqualTo(String value) {
            addCriterion("CBDW_BM =", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmNotEqualTo(String value) {
            addCriterion("CBDW_BM <>", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmGreaterThan(String value) {
            addCriterion("CBDW_BM >", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmGreaterThanOrEqualTo(String value) {
            addCriterion("CBDW_BM >=", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmLessThan(String value) {
            addCriterion("CBDW_BM <", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmLessThanOrEqualTo(String value) {
            addCriterion("CBDW_BM <=", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmLike(String value) {
            addCriterion("CBDW_BM like", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmNotLike(String value) {
            addCriterion("CBDW_BM not like", value, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmIn(List<String> values) {
            addCriterion("CBDW_BM in", values, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmNotIn(List<String> values) {
            addCriterion("CBDW_BM not in", values, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmBetween(String value1, String value2) {
            addCriterion("CBDW_BM between", value1, value2, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwBmNotBetween(String value1, String value2) {
            addCriterion("CBDW_BM not between", value1, value2, "cbdwBm");
            return (Criteria) this;
        }

        public Criteria andCbdwMcIsNull() {
            addCriterion("CBDW_MC is null");
            return (Criteria) this;
        }

        public Criteria andCbdwMcIsNotNull() {
            addCriterion("CBDW_MC is not null");
            return (Criteria) this;
        }

        public Criteria andCbdwMcEqualTo(String value) {
            addCriterion("CBDW_MC =", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcNotEqualTo(String value) {
            addCriterion("CBDW_MC <>", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcGreaterThan(String value) {
            addCriterion("CBDW_MC >", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcGreaterThanOrEqualTo(String value) {
            addCriterion("CBDW_MC >=", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcLessThan(String value) {
            addCriterion("CBDW_MC <", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcLessThanOrEqualTo(String value) {
            addCriterion("CBDW_MC <=", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcLike(String value) {
            addCriterion("CBDW_MC like", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcNotLike(String value) {
            addCriterion("CBDW_MC not like", value, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcIn(List<String> values) {
            addCriterion("CBDW_MC in", values, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcNotIn(List<String> values) {
            addCriterion("CBDW_MC not in", values, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcBetween(String value1, String value2) {
            addCriterion("CBDW_MC between", value1, value2, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andCbdwMcNotBetween(String value1, String value2) {
            addCriterion("CBDW_MC not between", value1, value2, "cbdwMc");
            return (Criteria) this;
        }

        public Criteria andFqdwbmIsNull() {
            addCriterion("FQDWBM is null");
            return (Criteria) this;
        }

        public Criteria andFqdwbmIsNotNull() {
            addCriterion("FQDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andFqdwbmEqualTo(BigDecimal value) {
            addCriterion("FQDWBM =", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmNotEqualTo(BigDecimal value) {
            addCriterion("FQDWBM <>", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmGreaterThan(BigDecimal value) {
            addCriterion("FQDWBM >", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FQDWBM >=", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmLessThan(BigDecimal value) {
            addCriterion("FQDWBM <", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FQDWBM <=", value, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmIn(List<BigDecimal> values) {
            addCriterion("FQDWBM in", values, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmNotIn(List<BigDecimal> values) {
            addCriterion("FQDWBM not in", values, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FQDWBM between", value1, value2, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqdwbmNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FQDWBM not between", value1, value2, "fqdwbm");
            return (Criteria) this;
        }

        public Criteria andFqlIsNull() {
            addCriterion("FQL is null");
            return (Criteria) this;
        }

        public Criteria andFqlIsNotNull() {
            addCriterion("FQL is not null");
            return (Criteria) this;
        }

        public Criteria andFqlEqualTo(String value) {
            addCriterion("FQL =", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlNotEqualTo(String value) {
            addCriterion("FQL <>", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlGreaterThan(String value) {
            addCriterion("FQL >", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlGreaterThanOrEqualTo(String value) {
            addCriterion("FQL >=", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlLessThan(String value) {
            addCriterion("FQL <", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlLessThanOrEqualTo(String value) {
            addCriterion("FQL <=", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlLike(String value) {
            addCriterion("FQL like", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlNotLike(String value) {
            addCriterion("FQL not like", value, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlIn(List<String> values) {
            addCriterion("FQL in", values, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlNotIn(List<String> values) {
            addCriterion("FQL not in", values, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlBetween(String value1, String value2) {
            addCriterion("FQL between", value1, value2, "fql");
            return (Criteria) this;
        }

        public Criteria andFqlNotBetween(String value1, String value2) {
            addCriterion("FQL not between", value1, value2, "fql");
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

        public Criteria andSlrqIsNull() {
            addCriterion("SLRQ is null");
            return (Criteria) this;
        }

        public Criteria andSlrqIsNotNull() {
            addCriterion("SLRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSlrqEqualTo(Date value) {
            addCriterion("SLRQ =", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqNotEqualTo(Date value) {
            addCriterion("SLRQ <>", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqGreaterThan(Date value) {
            addCriterion("SLRQ >", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqGreaterThanOrEqualTo(Date value) {
            addCriterion("SLRQ >=", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqLessThan(Date value) {
            addCriterion("SLRQ <", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqLessThanOrEqualTo(Date value) {
            addCriterion("SLRQ <=", value, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqIn(List<Date> values) {
            addCriterion("SLRQ in", values, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqNotIn(List<Date> values) {
            addCriterion("SLRQ not in", values, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqBetween(Date value1, Date value2) {
            addCriterion("SLRQ between", value1, value2, "slrq");
            return (Criteria) this;
        }

        public Criteria andSlrqNotBetween(Date value1, Date value2) {
            addCriterion("SLRQ not between", value1, value2, "slrq");
            return (Criteria) this;
        }

        public Criteria andAjmcIsNull() {
            addCriterion("AJMC is null");
            return (Criteria) this;
        }

        public Criteria andAjmcIsNotNull() {
            addCriterion("AJMC is not null");
            return (Criteria) this;
        }

        public Criteria andAjmcEqualTo(String value) {
            addCriterion("AJMC =", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotEqualTo(String value) {
            addCriterion("AJMC <>", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcGreaterThan(String value) {
            addCriterion("AJMC >", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcGreaterThanOrEqualTo(String value) {
            addCriterion("AJMC >=", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLessThan(String value) {
            addCriterion("AJMC <", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLessThanOrEqualTo(String value) {
            addCriterion("AJMC <=", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLike(String value) {
            addCriterion("AJMC like", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotLike(String value) {
            addCriterion("AJMC not like", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcIn(List<String> values) {
            addCriterion("AJMC in", values, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotIn(List<String> values) {
            addCriterion("AJMC not in", values, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcBetween(String value1, String value2) {
            addCriterion("AJMC between", value1, value2, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotBetween(String value1, String value2) {
            addCriterion("AJMC not between", value1, value2, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIsNull() {
            addCriterion("AJLB_BM is null");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIsNotNull() {
            addCriterion("AJLB_BM is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbBmEqualTo(String value) {
            addCriterion("AJLB_BM =", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotEqualTo(String value) {
            addCriterion("AJLB_BM <>", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmGreaterThan(String value) {
            addCriterion("AJLB_BM >", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmGreaterThanOrEqualTo(String value) {
            addCriterion("AJLB_BM >=", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLessThan(String value) {
            addCriterion("AJLB_BM <", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLessThanOrEqualTo(String value) {
            addCriterion("AJLB_BM <=", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLike(String value) {
            addCriterion("AJLB_BM like", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotLike(String value) {
            addCriterion("AJLB_BM not like", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIn(List<String> values) {
            addCriterion("AJLB_BM in", values, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotIn(List<String> values) {
            addCriterion("AJLB_BM not in", values, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmBetween(String value1, String value2) {
            addCriterion("AJLB_BM between", value1, value2, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotBetween(String value1, String value2) {
            addCriterion("AJLB_BM not between", value1, value2, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIsNull() {
            addCriterion("AJLB_MC is null");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIsNotNull() {
            addCriterion("AJLB_MC is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbMcEqualTo(String value) {
            addCriterion("AJLB_MC =", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotEqualTo(String value) {
            addCriterion("AJLB_MC <>", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcGreaterThan(String value) {
            addCriterion("AJLB_MC >", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcGreaterThanOrEqualTo(String value) {
            addCriterion("AJLB_MC >=", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLessThan(String value) {
            addCriterion("AJLB_MC <", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLessThanOrEqualTo(String value) {
            addCriterion("AJLB_MC <=", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLike(String value) {
            addCriterion("AJLB_MC like", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotLike(String value) {
            addCriterion("AJLB_MC not like", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIn(List<String> values) {
            addCriterion("AJLB_MC in", values, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotIn(List<String> values) {
            addCriterion("AJLB_MC not in", values, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcBetween(String value1, String value2) {
            addCriterion("AJLB_MC between", value1, value2, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotBetween(String value1, String value2) {
            addCriterion("AJLB_MC not between", value1, value2, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmIsNull() {
            addCriterion("ZCJG_DWDM is null");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmIsNotNull() {
            addCriterion("ZCJG_DWDM is not null");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmEqualTo(String value) {
            addCriterion("ZCJG_DWDM =", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmNotEqualTo(String value) {
            addCriterion("ZCJG_DWDM <>", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmGreaterThan(String value) {
            addCriterion("ZCJG_DWDM >", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmGreaterThanOrEqualTo(String value) {
            addCriterion("ZCJG_DWDM >=", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmLessThan(String value) {
            addCriterion("ZCJG_DWDM <", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmLessThanOrEqualTo(String value) {
            addCriterion("ZCJG_DWDM <=", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmLike(String value) {
            addCriterion("ZCJG_DWDM like", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmNotLike(String value) {
            addCriterion("ZCJG_DWDM not like", value, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmIn(List<String> values) {
            addCriterion("ZCJG_DWDM in", values, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmNotIn(List<String> values) {
            addCriterion("ZCJG_DWDM not in", values, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmBetween(String value1, String value2) {
            addCriterion("ZCJG_DWDM between", value1, value2, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwdmNotBetween(String value1, String value2) {
            addCriterion("ZCJG_DWDM not between", value1, value2, "zcjgDwdm");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcIsNull() {
            addCriterion("ZCJG_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcIsNotNull() {
            addCriterion("ZCJG_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcEqualTo(String value) {
            addCriterion("ZCJG_DWMC =", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcNotEqualTo(String value) {
            addCriterion("ZCJG_DWMC <>", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcGreaterThan(String value) {
            addCriterion("ZCJG_DWMC >", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("ZCJG_DWMC >=", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcLessThan(String value) {
            addCriterion("ZCJG_DWMC <", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcLessThanOrEqualTo(String value) {
            addCriterion("ZCJG_DWMC <=", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcLike(String value) {
            addCriterion("ZCJG_DWMC like", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcNotLike(String value) {
            addCriterion("ZCJG_DWMC not like", value, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcIn(List<String> values) {
            addCriterion("ZCJG_DWMC in", values, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcNotIn(List<String> values) {
            addCriterion("ZCJG_DWMC not in", values, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcBetween(String value1, String value2) {
            addCriterion("ZCJG_DWMC between", value1, value2, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andZcjgDwmcNotBetween(String value1, String value2) {
            addCriterion("ZCJG_DWMC not between", value1, value2, "zcjgDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmIsNull() {
            addCriterion("YSDW_DWDM is null");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmIsNotNull() {
            addCriterion("YSDW_DWDM is not null");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmEqualTo(String value) {
            addCriterion("YSDW_DWDM =", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmNotEqualTo(String value) {
            addCriterion("YSDW_DWDM <>", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmGreaterThan(String value) {
            addCriterion("YSDW_DWDM >", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmGreaterThanOrEqualTo(String value) {
            addCriterion("YSDW_DWDM >=", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmLessThan(String value) {
            addCriterion("YSDW_DWDM <", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmLessThanOrEqualTo(String value) {
            addCriterion("YSDW_DWDM <=", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmLike(String value) {
            addCriterion("YSDW_DWDM like", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmNotLike(String value) {
            addCriterion("YSDW_DWDM not like", value, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmIn(List<String> values) {
            addCriterion("YSDW_DWDM in", values, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmNotIn(List<String> values) {
            addCriterion("YSDW_DWDM not in", values, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmBetween(String value1, String value2) {
            addCriterion("YSDW_DWDM between", value1, value2, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwdmNotBetween(String value1, String value2) {
            addCriterion("YSDW_DWDM not between", value1, value2, "ysdwDwdm");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcIsNull() {
            addCriterion("YSDW_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcIsNotNull() {
            addCriterion("YSDW_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcEqualTo(String value) {
            addCriterion("YSDW_DWMC =", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcNotEqualTo(String value) {
            addCriterion("YSDW_DWMC <>", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcGreaterThan(String value) {
            addCriterion("YSDW_DWMC >", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("YSDW_DWMC >=", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcLessThan(String value) {
            addCriterion("YSDW_DWMC <", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcLessThanOrEqualTo(String value) {
            addCriterion("YSDW_DWMC <=", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcLike(String value) {
            addCriterion("YSDW_DWMC like", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcNotLike(String value) {
            addCriterion("YSDW_DWMC not like", value, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcIn(List<String> values) {
            addCriterion("YSDW_DWMC in", values, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcNotIn(List<String> values) {
            addCriterion("YSDW_DWMC not in", values, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcBetween(String value1, String value2) {
            addCriterion("YSDW_DWMC between", value1, value2, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYsdwDwmcNotBetween(String value1, String value2) {
            addCriterion("YSDW_DWMC not between", value1, value2, "ysdwDwmc");
            return (Criteria) this;
        }

        public Criteria andYswswhIsNull() {
            addCriterion("YSWSWH is null");
            return (Criteria) this;
        }

        public Criteria andYswswhIsNotNull() {
            addCriterion("YSWSWH is not null");
            return (Criteria) this;
        }

        public Criteria andYswswhEqualTo(String value) {
            addCriterion("YSWSWH =", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhNotEqualTo(String value) {
            addCriterion("YSWSWH <>", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhGreaterThan(String value) {
            addCriterion("YSWSWH >", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhGreaterThanOrEqualTo(String value) {
            addCriterion("YSWSWH >=", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhLessThan(String value) {
            addCriterion("YSWSWH <", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhLessThanOrEqualTo(String value) {
            addCriterion("YSWSWH <=", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhLike(String value) {
            addCriterion("YSWSWH like", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhNotLike(String value) {
            addCriterion("YSWSWH not like", value, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhIn(List<String> values) {
            addCriterion("YSWSWH in", values, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhNotIn(List<String> values) {
            addCriterion("YSWSWH not in", values, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhBetween(String value1, String value2) {
            addCriterion("YSWSWH between", value1, value2, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYswswhNotBetween(String value1, String value2) {
            addCriterion("YSWSWH not between", value1, value2, "yswswh");
            return (Criteria) this;
        }

        public Criteria andYsayAydmIsNull() {
            addCriterion("YSAY_AYDM is null");
            return (Criteria) this;
        }

        public Criteria andYsayAydmIsNotNull() {
            addCriterion("YSAY_AYDM is not null");
            return (Criteria) this;
        }

        public Criteria andYsayAydmEqualTo(String value) {
            addCriterion("YSAY_AYDM =", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmNotEqualTo(String value) {
            addCriterion("YSAY_AYDM <>", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmGreaterThan(String value) {
            addCriterion("YSAY_AYDM >", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmGreaterThanOrEqualTo(String value) {
            addCriterion("YSAY_AYDM >=", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmLessThan(String value) {
            addCriterion("YSAY_AYDM <", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmLessThanOrEqualTo(String value) {
            addCriterion("YSAY_AYDM <=", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmLike(String value) {
            addCriterion("YSAY_AYDM like", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmNotLike(String value) {
            addCriterion("YSAY_AYDM not like", value, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmIn(List<String> values) {
            addCriterion("YSAY_AYDM in", values, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmNotIn(List<String> values) {
            addCriterion("YSAY_AYDM not in", values, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmBetween(String value1, String value2) {
            addCriterion("YSAY_AYDM between", value1, value2, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAydmNotBetween(String value1, String value2) {
            addCriterion("YSAY_AYDM not between", value1, value2, "ysayAydm");
            return (Criteria) this;
        }

        public Criteria andYsayAymcIsNull() {
            addCriterion("YSAY_AYMC is null");
            return (Criteria) this;
        }

        public Criteria andYsayAymcIsNotNull() {
            addCriterion("YSAY_AYMC is not null");
            return (Criteria) this;
        }

        public Criteria andYsayAymcEqualTo(String value) {
            addCriterion("YSAY_AYMC =", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcNotEqualTo(String value) {
            addCriterion("YSAY_AYMC <>", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcGreaterThan(String value) {
            addCriterion("YSAY_AYMC >", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcGreaterThanOrEqualTo(String value) {
            addCriterion("YSAY_AYMC >=", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcLessThan(String value) {
            addCriterion("YSAY_AYMC <", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcLessThanOrEqualTo(String value) {
            addCriterion("YSAY_AYMC <=", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcLike(String value) {
            addCriterion("YSAY_AYMC like", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcNotLike(String value) {
            addCriterion("YSAY_AYMC not like", value, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcIn(List<String> values) {
            addCriterion("YSAY_AYMC in", values, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcNotIn(List<String> values) {
            addCriterion("YSAY_AYMC not in", values, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcBetween(String value1, String value2) {
            addCriterion("YSAY_AYMC between", value1, value2, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsayAymcNotBetween(String value1, String value2) {
            addCriterion("YSAY_AYMC not between", value1, value2, "ysayAymc");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsIsNull() {
            addCriterion("YSQTAY_AYDMS is null");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsIsNotNull() {
            addCriterion("YSQTAY_AYDMS is not null");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsEqualTo(String value) {
            addCriterion("YSQTAY_AYDMS =", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsNotEqualTo(String value) {
            addCriterion("YSQTAY_AYDMS <>", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsGreaterThan(String value) {
            addCriterion("YSQTAY_AYDMS >", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsGreaterThanOrEqualTo(String value) {
            addCriterion("YSQTAY_AYDMS >=", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsLessThan(String value) {
            addCriterion("YSQTAY_AYDMS <", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsLessThanOrEqualTo(String value) {
            addCriterion("YSQTAY_AYDMS <=", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsLike(String value) {
            addCriterion("YSQTAY_AYDMS like", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsNotLike(String value) {
            addCriterion("YSQTAY_AYDMS not like", value, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsIn(List<String> values) {
            addCriterion("YSQTAY_AYDMS in", values, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsNotIn(List<String> values) {
            addCriterion("YSQTAY_AYDMS not in", values, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsBetween(String value1, String value2) {
            addCriterion("YSQTAY_AYDMS between", value1, value2, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAydmsNotBetween(String value1, String value2) {
            addCriterion("YSQTAY_AYDMS not between", value1, value2, "ysqtayAydms");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsIsNull() {
            addCriterion("YSQTAY_AYMCS is null");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsIsNotNull() {
            addCriterion("YSQTAY_AYMCS is not null");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsEqualTo(String value) {
            addCriterion("YSQTAY_AYMCS =", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsNotEqualTo(String value) {
            addCriterion("YSQTAY_AYMCS <>", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsGreaterThan(String value) {
            addCriterion("YSQTAY_AYMCS >", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsGreaterThanOrEqualTo(String value) {
            addCriterion("YSQTAY_AYMCS >=", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsLessThan(String value) {
            addCriterion("YSQTAY_AYMCS <", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsLessThanOrEqualTo(String value) {
            addCriterion("YSQTAY_AYMCS <=", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsLike(String value) {
            addCriterion("YSQTAY_AYMCS like", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsNotLike(String value) {
            addCriterion("YSQTAY_AYMCS not like", value, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsIn(List<String> values) {
            addCriterion("YSQTAY_AYMCS in", values, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsNotIn(List<String> values) {
            addCriterion("YSQTAY_AYMCS not in", values, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsBetween(String value1, String value2) {
            addCriterion("YSQTAY_AYMCS between", value1, value2, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andYsqtayAymcsNotBetween(String value1, String value2) {
            addCriterion("YSQTAY_AYMCS not between", value1, value2, "ysqtayAymcs");
            return (Criteria) this;
        }

        public Criteria andCbrghIsNull() {
            addCriterion("CBRGH is null");
            return (Criteria) this;
        }

        public Criteria andCbrghIsNotNull() {
            addCriterion("CBRGH is not null");
            return (Criteria) this;
        }

        public Criteria andCbrghEqualTo(String value) {
            addCriterion("CBRGH =", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghNotEqualTo(String value) {
            addCriterion("CBRGH <>", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghGreaterThan(String value) {
            addCriterion("CBRGH >", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghGreaterThanOrEqualTo(String value) {
            addCriterion("CBRGH >=", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghLessThan(String value) {
            addCriterion("CBRGH <", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghLessThanOrEqualTo(String value) {
            addCriterion("CBRGH <=", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghLike(String value) {
            addCriterion("CBRGH like", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghNotLike(String value) {
            addCriterion("CBRGH not like", value, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghIn(List<String> values) {
            addCriterion("CBRGH in", values, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghNotIn(List<String> values) {
            addCriterion("CBRGH not in", values, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghBetween(String value1, String value2) {
            addCriterion("CBRGH between", value1, value2, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrghNotBetween(String value1, String value2) {
            addCriterion("CBRGH not between", value1, value2, "cbrgh");
            return (Criteria) this;
        }

        public Criteria andCbrIsNull() {
            addCriterion("CBR is null");
            return (Criteria) this;
        }

        public Criteria andCbrIsNotNull() {
            addCriterion("CBR is not null");
            return (Criteria) this;
        }

        public Criteria andCbrEqualTo(String value) {
            addCriterion("CBR =", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrNotEqualTo(String value) {
            addCriterion("CBR <>", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrGreaterThan(String value) {
            addCriterion("CBR >", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrGreaterThanOrEqualTo(String value) {
            addCriterion("CBR >=", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrLessThan(String value) {
            addCriterion("CBR <", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrLessThanOrEqualTo(String value) {
            addCriterion("CBR <=", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrLike(String value) {
            addCriterion("CBR like", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrNotLike(String value) {
            addCriterion("CBR not like", value, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrIn(List<String> values) {
            addCriterion("CBR in", values, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrNotIn(List<String> values) {
            addCriterion("CBR not in", values, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrBetween(String value1, String value2) {
            addCriterion("CBR between", value1, value2, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbrNotBetween(String value1, String value2) {
            addCriterion("CBR not between", value1, value2, "cbr");
            return (Criteria) this;
        }

        public Criteria andCbbmBmIsNull() {
            addCriterion("CBBM_BM is null");
            return (Criteria) this;
        }

        public Criteria andCbbmBmIsNotNull() {
            addCriterion("CBBM_BM is not null");
            return (Criteria) this;
        }

        public Criteria andCbbmBmEqualTo(String value) {
            addCriterion("CBBM_BM =", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmNotEqualTo(String value) {
            addCriterion("CBBM_BM <>", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmGreaterThan(String value) {
            addCriterion("CBBM_BM >", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmGreaterThanOrEqualTo(String value) {
            addCriterion("CBBM_BM >=", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmLessThan(String value) {
            addCriterion("CBBM_BM <", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmLessThanOrEqualTo(String value) {
            addCriterion("CBBM_BM <=", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmLike(String value) {
            addCriterion("CBBM_BM like", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmNotLike(String value) {
            addCriterion("CBBM_BM not like", value, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmIn(List<String> values) {
            addCriterion("CBBM_BM in", values, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmNotIn(List<String> values) {
            addCriterion("CBBM_BM not in", values, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmBetween(String value1, String value2) {
            addCriterion("CBBM_BM between", value1, value2, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmBmNotBetween(String value1, String value2) {
            addCriterion("CBBM_BM not between", value1, value2, "cbbmBm");
            return (Criteria) this;
        }

        public Criteria andCbbmMcIsNull() {
            addCriterion("CBBM_MC is null");
            return (Criteria) this;
        }

        public Criteria andCbbmMcIsNotNull() {
            addCriterion("CBBM_MC is not null");
            return (Criteria) this;
        }

        public Criteria andCbbmMcEqualTo(String value) {
            addCriterion("CBBM_MC =", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcNotEqualTo(String value) {
            addCriterion("CBBM_MC <>", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcGreaterThan(String value) {
            addCriterion("CBBM_MC >", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcGreaterThanOrEqualTo(String value) {
            addCriterion("CBBM_MC >=", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcLessThan(String value) {
            addCriterion("CBBM_MC <", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcLessThanOrEqualTo(String value) {
            addCriterion("CBBM_MC <=", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcLike(String value) {
            addCriterion("CBBM_MC like", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcNotLike(String value) {
            addCriterion("CBBM_MC not like", value, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcIn(List<String> values) {
            addCriterion("CBBM_MC in", values, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcNotIn(List<String> values) {
            addCriterion("CBBM_MC not in", values, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcBetween(String value1, String value2) {
            addCriterion("CBBM_MC between", value1, value2, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andCbbmMcNotBetween(String value1, String value2) {
            addCriterion("CBBM_MC not between", value1, value2, "cbbmMc");
            return (Criteria) this;
        }

        public Criteria andAjztIsNull() {
            addCriterion("AJZT is null");
            return (Criteria) this;
        }

        public Criteria andAjztIsNotNull() {
            addCriterion("AJZT is not null");
            return (Criteria) this;
        }

        public Criteria andAjztEqualTo(String value) {
            addCriterion("AJZT =", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztNotEqualTo(String value) {
            addCriterion("AJZT <>", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztGreaterThan(String value) {
            addCriterion("AJZT >", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztGreaterThanOrEqualTo(String value) {
            addCriterion("AJZT >=", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztLessThan(String value) {
            addCriterion("AJZT <", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztLessThanOrEqualTo(String value) {
            addCriterion("AJZT <=", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztLike(String value) {
            addCriterion("AJZT like", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztNotLike(String value) {
            addCriterion("AJZT not like", value, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztIn(List<String> values) {
            addCriterion("AJZT in", values, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztNotIn(List<String> values) {
            addCriterion("AJZT not in", values, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztBetween(String value1, String value2) {
            addCriterion("AJZT between", value1, value2, "ajzt");
            return (Criteria) this;
        }

        public Criteria andAjztNotBetween(String value1, String value2) {
            addCriterion("AJZT not between", value1, value2, "ajzt");
            return (Criteria) this;
        }

        public Criteria andSfswajIsNull() {
            addCriterion("SFSWAJ is null");
            return (Criteria) this;
        }

        public Criteria andSfswajIsNotNull() {
            addCriterion("SFSWAJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfswajEqualTo(String value) {
            addCriterion("SFSWAJ =", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajNotEqualTo(String value) {
            addCriterion("SFSWAJ <>", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajGreaterThan(String value) {
            addCriterion("SFSWAJ >", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajGreaterThanOrEqualTo(String value) {
            addCriterion("SFSWAJ >=", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajLessThan(String value) {
            addCriterion("SFSWAJ <", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajLessThanOrEqualTo(String value) {
            addCriterion("SFSWAJ <=", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajLike(String value) {
            addCriterion("SFSWAJ like", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajNotLike(String value) {
            addCriterion("SFSWAJ not like", value, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajIn(List<String> values) {
            addCriterion("SFSWAJ in", values, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajNotIn(List<String> values) {
            addCriterion("SFSWAJ not in", values, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajBetween(String value1, String value2) {
            addCriterion("SFSWAJ between", value1, value2, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfswajNotBetween(String value1, String value2) {
            addCriterion("SFSWAJ not between", value1, value2, "sfswaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajIsNull() {
            addCriterion("SFDBAJ is null");
            return (Criteria) this;
        }

        public Criteria andSfdbajIsNotNull() {
            addCriterion("SFDBAJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfdbajEqualTo(String value) {
            addCriterion("SFDBAJ =", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajNotEqualTo(String value) {
            addCriterion("SFDBAJ <>", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajGreaterThan(String value) {
            addCriterion("SFDBAJ >", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajGreaterThanOrEqualTo(String value) {
            addCriterion("SFDBAJ >=", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajLessThan(String value) {
            addCriterion("SFDBAJ <", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajLessThanOrEqualTo(String value) {
            addCriterion("SFDBAJ <=", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajLike(String value) {
            addCriterion("SFDBAJ like", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajNotLike(String value) {
            addCriterion("SFDBAJ not like", value, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajIn(List<String> values) {
            addCriterion("SFDBAJ in", values, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajNotIn(List<String> values) {
            addCriterion("SFDBAJ not in", values, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajBetween(String value1, String value2) {
            addCriterion("SFDBAJ between", value1, value2, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andSfdbajNotBetween(String value1, String value2) {
            addCriterion("SFDBAJ not between", value1, value2, "sfdbaj");
            return (Criteria) this;
        }

        public Criteria andZxhdMcIsNull() {
            addCriterion("ZXHD_MC is null");
            return (Criteria) this;
        }

        public Criteria andZxhdMcIsNotNull() {
            addCriterion("ZXHD_MC is not null");
            return (Criteria) this;
        }

        public Criteria andZxhdMcEqualTo(String value) {
            addCriterion("ZXHD_MC =", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcNotEqualTo(String value) {
            addCriterion("ZXHD_MC <>", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcGreaterThan(String value) {
            addCriterion("ZXHD_MC >", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcGreaterThanOrEqualTo(String value) {
            addCriterion("ZXHD_MC >=", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcLessThan(String value) {
            addCriterion("ZXHD_MC <", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcLessThanOrEqualTo(String value) {
            addCriterion("ZXHD_MC <=", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcLike(String value) {
            addCriterion("ZXHD_MC like", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcNotLike(String value) {
            addCriterion("ZXHD_MC not like", value, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcIn(List<String> values) {
            addCriterion("ZXHD_MC in", values, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcNotIn(List<String> values) {
            addCriterion("ZXHD_MC not in", values, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcBetween(String value1, String value2) {
            addCriterion("ZXHD_MC between", value1, value2, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andZxhdMcNotBetween(String value1, String value2) {
            addCriterion("ZXHD_MC not between", value1, value2, "zxhdMc");
            return (Criteria) this;
        }

        public Criteria andWcrqIsNull() {
            addCriterion("WCRQ is null");
            return (Criteria) this;
        }

        public Criteria andWcrqIsNotNull() {
            addCriterion("WCRQ is not null");
            return (Criteria) this;
        }

        public Criteria andWcrqEqualTo(Date value) {
            addCriterion("WCRQ =", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqNotEqualTo(Date value) {
            addCriterion("WCRQ <>", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqGreaterThan(Date value) {
            addCriterion("WCRQ >", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqGreaterThanOrEqualTo(Date value) {
            addCriterion("WCRQ >=", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqLessThan(Date value) {
            addCriterion("WCRQ <", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqLessThanOrEqualTo(Date value) {
            addCriterion("WCRQ <=", value, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqIn(List<Date> values) {
            addCriterion("WCRQ in", values, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqNotIn(List<Date> values) {
            addCriterion("WCRQ not in", values, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqBetween(Date value1, Date value2) {
            addCriterion("WCRQ between", value1, value2, "wcrq");
            return (Criteria) this;
        }

        public Criteria andWcrqNotBetween(Date value1, Date value2) {
            addCriterion("WCRQ not between", value1, value2, "wcrq");
            return (Criteria) this;
        }

        public Criteria andGdrqIsNull() {
            addCriterion("GDRQ is null");
            return (Criteria) this;
        }

        public Criteria andGdrqIsNotNull() {
            addCriterion("GDRQ is not null");
            return (Criteria) this;
        }

        public Criteria andGdrqEqualTo(Date value) {
            addCriterion("GDRQ =", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqNotEqualTo(Date value) {
            addCriterion("GDRQ <>", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqGreaterThan(Date value) {
            addCriterion("GDRQ >", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqGreaterThanOrEqualTo(Date value) {
            addCriterion("GDRQ >=", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqLessThan(Date value) {
            addCriterion("GDRQ <", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqLessThanOrEqualTo(Date value) {
            addCriterion("GDRQ <=", value, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqIn(List<Date> values) {
            addCriterion("GDRQ in", values, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqNotIn(List<Date> values) {
            addCriterion("GDRQ not in", values, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqBetween(Date value1, Date value2) {
            addCriterion("GDRQ between", value1, value2, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrqNotBetween(Date value1, Date value2) {
            addCriterion("GDRQ not between", value1, value2, "gdrq");
            return (Criteria) this;
        }

        public Criteria andGdrghIsNull() {
            addCriterion("GDRGH is null");
            return (Criteria) this;
        }

        public Criteria andGdrghIsNotNull() {
            addCriterion("GDRGH is not null");
            return (Criteria) this;
        }

        public Criteria andGdrghEqualTo(String value) {
            addCriterion("GDRGH =", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghNotEqualTo(String value) {
            addCriterion("GDRGH <>", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghGreaterThan(String value) {
            addCriterion("GDRGH >", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghGreaterThanOrEqualTo(String value) {
            addCriterion("GDRGH >=", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghLessThan(String value) {
            addCriterion("GDRGH <", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghLessThanOrEqualTo(String value) {
            addCriterion("GDRGH <=", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghLike(String value) {
            addCriterion("GDRGH like", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghNotLike(String value) {
            addCriterion("GDRGH not like", value, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghIn(List<String> values) {
            addCriterion("GDRGH in", values, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghNotIn(List<String> values) {
            addCriterion("GDRGH not in", values, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghBetween(String value1, String value2) {
            addCriterion("GDRGH between", value1, value2, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrghNotBetween(String value1, String value2) {
            addCriterion("GDRGH not between", value1, value2, "gdrgh");
            return (Criteria) this;
        }

        public Criteria andGdrIsNull() {
            addCriterion("GDR is null");
            return (Criteria) this;
        }

        public Criteria andGdrIsNotNull() {
            addCriterion("GDR is not null");
            return (Criteria) this;
        }

        public Criteria andGdrEqualTo(String value) {
            addCriterion("GDR =", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrNotEqualTo(String value) {
            addCriterion("GDR <>", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrGreaterThan(String value) {
            addCriterion("GDR >", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrGreaterThanOrEqualTo(String value) {
            addCriterion("GDR >=", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrLessThan(String value) {
            addCriterion("GDR <", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrLessThanOrEqualTo(String value) {
            addCriterion("GDR <=", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrLike(String value) {
            addCriterion("GDR like", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrNotLike(String value) {
            addCriterion("GDR not like", value, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrIn(List<String> values) {
            addCriterion("GDR in", values, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrNotIn(List<String> values) {
            addCriterion("GDR not in", values, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrBetween(String value1, String value2) {
            addCriterion("GDR between", value1, value2, "gdr");
            return (Criteria) this;
        }

        public Criteria andGdrNotBetween(String value1, String value2) {
            addCriterion("GDR not between", value1, value2, "gdr");
            return (Criteria) this;
        }

        public Criteria andAqzyIsNull() {
            addCriterion("AQZY is null");
            return (Criteria) this;
        }

        public Criteria andAqzyIsNotNull() {
            addCriterion("AQZY is not null");
            return (Criteria) this;
        }

        public Criteria andAqzyEqualTo(String value) {
            addCriterion("AQZY =", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyNotEqualTo(String value) {
            addCriterion("AQZY <>", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyGreaterThan(String value) {
            addCriterion("AQZY >", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyGreaterThanOrEqualTo(String value) {
            addCriterion("AQZY >=", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyLessThan(String value) {
            addCriterion("AQZY <", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyLessThanOrEqualTo(String value) {
            addCriterion("AQZY <=", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyLike(String value) {
            addCriterion("AQZY like", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyNotLike(String value) {
            addCriterion("AQZY not like", value, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyIn(List<String> values) {
            addCriterion("AQZY in", values, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyNotIn(List<String> values) {
            addCriterion("AQZY not in", values, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyBetween(String value1, String value2) {
            addCriterion("AQZY between", value1, value2, "aqzy");
            return (Criteria) this;
        }

        public Criteria andAqzyNotBetween(String value1, String value2) {
            addCriterion("AQZY not between", value1, value2, "aqzy");
            return (Criteria) this;
        }

        public Criteria andDqjdIsNull() {
            addCriterion("DQJD is null");
            return (Criteria) this;
        }

        public Criteria andDqjdIsNotNull() {
            addCriterion("DQJD is not null");
            return (Criteria) this;
        }

        public Criteria andDqjdEqualTo(String value) {
            addCriterion("DQJD =", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdNotEqualTo(String value) {
            addCriterion("DQJD <>", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdGreaterThan(String value) {
            addCriterion("DQJD >", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdGreaterThanOrEqualTo(String value) {
            addCriterion("DQJD >=", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdLessThan(String value) {
            addCriterion("DQJD <", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdLessThanOrEqualTo(String value) {
            addCriterion("DQJD <=", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdLike(String value) {
            addCriterion("DQJD like", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdNotLike(String value) {
            addCriterion("DQJD not like", value, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdIn(List<String> values) {
            addCriterion("DQJD in", values, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdNotIn(List<String> values) {
            addCriterion("DQJD not in", values, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdBetween(String value1, String value2) {
            addCriterion("DQJD between", value1, value2, "dqjd");
            return (Criteria) this;
        }

        public Criteria andDqjdNotBetween(String value1, String value2) {
            addCriterion("DQJD not between", value1, value2, "dqjd");
            return (Criteria) this;
        }

        public Criteria andBlksrqIsNull() {
            addCriterion("BLKSRQ is null");
            return (Criteria) this;
        }

        public Criteria andBlksrqIsNotNull() {
            addCriterion("BLKSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBlksrqEqualTo(Date value) {
            addCriterion("BLKSRQ =", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqNotEqualTo(Date value) {
            addCriterion("BLKSRQ <>", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqGreaterThan(Date value) {
            addCriterion("BLKSRQ >", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqGreaterThanOrEqualTo(Date value) {
            addCriterion("BLKSRQ >=", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqLessThan(Date value) {
            addCriterion("BLKSRQ <", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqLessThanOrEqualTo(Date value) {
            addCriterion("BLKSRQ <=", value, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqIn(List<Date> values) {
            addCriterion("BLKSRQ in", values, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqNotIn(List<Date> values) {
            addCriterion("BLKSRQ not in", values, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqBetween(Date value1, Date value2) {
            addCriterion("BLKSRQ between", value1, value2, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBlksrqNotBetween(Date value1, Date value2) {
            addCriterion("BLKSRQ not between", value1, value2, "blksrq");
            return (Criteria) this;
        }

        public Criteria andBltsIsNull() {
            addCriterion("BLTS is null");
            return (Criteria) this;
        }

        public Criteria andBltsIsNotNull() {
            addCriterion("BLTS is not null");
            return (Criteria) this;
        }

        public Criteria andBltsEqualTo(Short value) {
            addCriterion("BLTS =", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsNotEqualTo(Short value) {
            addCriterion("BLTS <>", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsGreaterThan(Short value) {
            addCriterion("BLTS >", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsGreaterThanOrEqualTo(Short value) {
            addCriterion("BLTS >=", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsLessThan(Short value) {
            addCriterion("BLTS <", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsLessThanOrEqualTo(Short value) {
            addCriterion("BLTS <=", value, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsIn(List<Short> values) {
            addCriterion("BLTS in", values, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsNotIn(List<Short> values) {
            addCriterion("BLTS not in", values, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsBetween(Short value1, Short value2) {
            addCriterion("BLTS between", value1, value2, "blts");
            return (Criteria) this;
        }

        public Criteria andBltsNotBetween(Short value1, Short value2) {
            addCriterion("BLTS not between", value1, value2, "blts");
            return (Criteria) this;
        }

        public Criteria andDqrqIsNull() {
            addCriterion("DQRQ is null");
            return (Criteria) this;
        }

        public Criteria andDqrqIsNotNull() {
            addCriterion("DQRQ is not null");
            return (Criteria) this;
        }

        public Criteria andDqrqEqualTo(Date value) {
            addCriterion("DQRQ =", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotEqualTo(Date value) {
            addCriterion("DQRQ <>", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqGreaterThan(Date value) {
            addCriterion("DQRQ >", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqGreaterThanOrEqualTo(Date value) {
            addCriterion("DQRQ >=", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqLessThan(Date value) {
            addCriterion("DQRQ <", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqLessThanOrEqualTo(Date value) {
            addCriterion("DQRQ <=", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqIn(List<Date> values) {
            addCriterion("DQRQ in", values, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotIn(List<Date> values) {
            addCriterion("DQRQ not in", values, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqBetween(Date value1, Date value2) {
            addCriterion("DQRQ between", value1, value2, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotBetween(Date value1, Date value2) {
            addCriterion("DQRQ not between", value1, value2, "dqrq");
            return (Criteria) this;
        }

        public Criteria andBjrqIsNull() {
            addCriterion("BJRQ is null");
            return (Criteria) this;
        }

        public Criteria andBjrqIsNotNull() {
            addCriterion("BJRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBjrqEqualTo(Date value) {
            addCriterion("BJRQ =", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqNotEqualTo(Date value) {
            addCriterion("BJRQ <>", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqGreaterThan(Date value) {
            addCriterion("BJRQ >", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqGreaterThanOrEqualTo(Date value) {
            addCriterion("BJRQ >=", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqLessThan(Date value) {
            addCriterion("BJRQ <", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqLessThanOrEqualTo(Date value) {
            addCriterion("BJRQ <=", value, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqIn(List<Date> values) {
            addCriterion("BJRQ in", values, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqNotIn(List<Date> values) {
            addCriterion("BJRQ not in", values, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqBetween(Date value1, Date value2) {
            addCriterion("BJRQ between", value1, value2, "bjrq");
            return (Criteria) this;
        }

        public Criteria andBjrqNotBetween(Date value1, Date value2) {
            addCriterion("BJRQ not between", value1, value2, "bjrq");
            return (Criteria) this;
        }

        public Criteria andYjztIsNull() {
            addCriterion("YJZT is null");
            return (Criteria) this;
        }

        public Criteria andYjztIsNotNull() {
            addCriterion("YJZT is not null");
            return (Criteria) this;
        }

        public Criteria andYjztEqualTo(String value) {
            addCriterion("YJZT =", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztNotEqualTo(String value) {
            addCriterion("YJZT <>", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztGreaterThan(String value) {
            addCriterion("YJZT >", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztGreaterThanOrEqualTo(String value) {
            addCriterion("YJZT >=", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztLessThan(String value) {
            addCriterion("YJZT <", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztLessThanOrEqualTo(String value) {
            addCriterion("YJZT <=", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztLike(String value) {
            addCriterion("YJZT like", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztNotLike(String value) {
            addCriterion("YJZT not like", value, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztIn(List<String> values) {
            addCriterion("YJZT in", values, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztNotIn(List<String> values) {
            addCriterion("YJZT not in", values, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztBetween(String value1, String value2) {
            addCriterion("YJZT between", value1, value2, "yjzt");
            return (Criteria) this;
        }

        public Criteria andYjztNotBetween(String value1, String value2) {
            addCriterion("YJZT not between", value1, value2, "yjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztIsNull() {
            addCriterion("JYYJZT is null");
            return (Criteria) this;
        }

        public Criteria andJyyjztIsNotNull() {
            addCriterion("JYYJZT is not null");
            return (Criteria) this;
        }

        public Criteria andJyyjztEqualTo(String value) {
            addCriterion("JYYJZT =", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztNotEqualTo(String value) {
            addCriterion("JYYJZT <>", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztGreaterThan(String value) {
            addCriterion("JYYJZT >", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztGreaterThanOrEqualTo(String value) {
            addCriterion("JYYJZT >=", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztLessThan(String value) {
            addCriterion("JYYJZT <", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztLessThanOrEqualTo(String value) {
            addCriterion("JYYJZT <=", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztLike(String value) {
            addCriterion("JYYJZT like", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztNotLike(String value) {
            addCriterion("JYYJZT not like", value, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztIn(List<String> values) {
            addCriterion("JYYJZT in", values, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztNotIn(List<String> values) {
            addCriterion("JYYJZT not in", values, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztBetween(String value1, String value2) {
            addCriterion("JYYJZT between", value1, value2, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjztNotBetween(String value1, String value2) {
            addCriterion("JYYJZT not between", value1, value2, "jyyjzt");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsIsNull() {
            addCriterion("JYYJHCQXYRGS is null");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsIsNotNull() {
            addCriterion("JYYJHCQXYRGS is not null");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsEqualTo(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS =", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsNotEqualTo(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS <>", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsGreaterThan(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS >", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS >=", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsLessThan(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS <", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("JYYJHCQXYRGS <=", value, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsIn(List<BigDecimal> values) {
            addCriterion("JYYJHCQXYRGS in", values, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsNotIn(List<BigDecimal> values) {
            addCriterion("JYYJHCQXYRGS not in", values, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JYYJHCQXYRGS between", value1, value2, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andJyyjhcqxyrgsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JYYJHCQXYRGS not between", value1, value2, "jyyjhcqxyrgs");
            return (Criteria) this;
        }

        public Criteria andLcslbhIsNull() {
            addCriterion("LCSLBH is null");
            return (Criteria) this;
        }

        public Criteria andLcslbhIsNotNull() {
            addCriterion("LCSLBH is not null");
            return (Criteria) this;
        }

        public Criteria andLcslbhEqualTo(String value) {
            addCriterion("LCSLBH =", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotEqualTo(String value) {
            addCriterion("LCSLBH <>", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhGreaterThan(String value) {
            addCriterion("LCSLBH >", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhGreaterThanOrEqualTo(String value) {
            addCriterion("LCSLBH >=", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLessThan(String value) {
            addCriterion("LCSLBH <", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLessThanOrEqualTo(String value) {
            addCriterion("LCSLBH <=", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLike(String value) {
            addCriterion("LCSLBH like", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotLike(String value) {
            addCriterion("LCSLBH not like", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhIn(List<String> values) {
            addCriterion("LCSLBH in", values, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotIn(List<String> values) {
            addCriterion("LCSLBH not in", values, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhBetween(String value1, String value2) {
            addCriterion("LCSLBH between", value1, value2, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotBetween(String value1, String value2) {
            addCriterion("LCSLBH not between", value1, value2, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andFxdjIsNull() {
            addCriterion("FXDJ is null");
            return (Criteria) this;
        }

        public Criteria andFxdjIsNotNull() {
            addCriterion("FXDJ is not null");
            return (Criteria) this;
        }

        public Criteria andFxdjEqualTo(String value) {
            addCriterion("FXDJ =", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjNotEqualTo(String value) {
            addCriterion("FXDJ <>", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjGreaterThan(String value) {
            addCriterion("FXDJ >", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjGreaterThanOrEqualTo(String value) {
            addCriterion("FXDJ >=", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjLessThan(String value) {
            addCriterion("FXDJ <", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjLessThanOrEqualTo(String value) {
            addCriterion("FXDJ <=", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjLike(String value) {
            addCriterion("FXDJ like", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjNotLike(String value) {
            addCriterion("FXDJ not like", value, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjIn(List<String> values) {
            addCriterion("FXDJ in", values, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjNotIn(List<String> values) {
            addCriterion("FXDJ not in", values, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjBetween(String value1, String value2) {
            addCriterion("FXDJ between", value1, value2, "fxdj");
            return (Criteria) this;
        }

        public Criteria andFxdjNotBetween(String value1, String value2) {
            addCriterion("FXDJ not between", value1, value2, "fxdj");
            return (Criteria) this;
        }

        public Criteria andSfgzajIsNull() {
            addCriterion("SFGZAJ is null");
            return (Criteria) this;
        }

        public Criteria andSfgzajIsNotNull() {
            addCriterion("SFGZAJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfgzajEqualTo(String value) {
            addCriterion("SFGZAJ =", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajNotEqualTo(String value) {
            addCriterion("SFGZAJ <>", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajGreaterThan(String value) {
            addCriterion("SFGZAJ >", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajGreaterThanOrEqualTo(String value) {
            addCriterion("SFGZAJ >=", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajLessThan(String value) {
            addCriterion("SFGZAJ <", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajLessThanOrEqualTo(String value) {
            addCriterion("SFGZAJ <=", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajLike(String value) {
            addCriterion("SFGZAJ like", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajNotLike(String value) {
            addCriterion("SFGZAJ not like", value, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajIn(List<String> values) {
            addCriterion("SFGZAJ in", values, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajNotIn(List<String> values) {
            addCriterion("SFGZAJ not in", values, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajBetween(String value1, String value2) {
            addCriterion("SFGZAJ between", value1, value2, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andSfgzajNotBetween(String value1, String value2) {
            addCriterion("SFGZAJ not between", value1, value2, "sfgzaj");
            return (Criteria) this;
        }

        public Criteria andFzIsNull() {
            addCriterion("FZ is null");
            return (Criteria) this;
        }

        public Criteria andFzIsNotNull() {
            addCriterion("FZ is not null");
            return (Criteria) this;
        }

        public Criteria andFzEqualTo(String value) {
            addCriterion("FZ =", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotEqualTo(String value) {
            addCriterion("FZ <>", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThan(String value) {
            addCriterion("FZ >", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzGreaterThanOrEqualTo(String value) {
            addCriterion("FZ >=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThan(String value) {
            addCriterion("FZ <", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLessThanOrEqualTo(String value) {
            addCriterion("FZ <=", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzLike(String value) {
            addCriterion("FZ like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotLike(String value) {
            addCriterion("FZ not like", value, "fz");
            return (Criteria) this;
        }

        public Criteria andFzIn(List<String> values) {
            addCriterion("FZ in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotIn(List<String> values) {
            addCriterion("FZ not in", values, "fz");
            return (Criteria) this;
        }

        public Criteria andFzBetween(String value1, String value2) {
            addCriterion("FZ between", value1, value2, "fz");
            return (Criteria) this;
        }

        public Criteria andFzNotBetween(String value1, String value2) {
            addCriterion("FZ not between", value1, value2, "fz");
            return (Criteria) this;
        }

        public Criteria andYsyjDmIsNull() {
            addCriterion("YSYJ_DM is null");
            return (Criteria) this;
        }

        public Criteria andYsyjDmIsNotNull() {
            addCriterion("YSYJ_DM is not null");
            return (Criteria) this;
        }

        public Criteria andYsyjDmEqualTo(String value) {
            addCriterion("YSYJ_DM =", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmNotEqualTo(String value) {
            addCriterion("YSYJ_DM <>", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmGreaterThan(String value) {
            addCriterion("YSYJ_DM >", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmGreaterThanOrEqualTo(String value) {
            addCriterion("YSYJ_DM >=", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmLessThan(String value) {
            addCriterion("YSYJ_DM <", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmLessThanOrEqualTo(String value) {
            addCriterion("YSYJ_DM <=", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmLike(String value) {
            addCriterion("YSYJ_DM like", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmNotLike(String value) {
            addCriterion("YSYJ_DM not like", value, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmIn(List<String> values) {
            addCriterion("YSYJ_DM in", values, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmNotIn(List<String> values) {
            addCriterion("YSYJ_DM not in", values, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmBetween(String value1, String value2) {
            addCriterion("YSYJ_DM between", value1, value2, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjDmNotBetween(String value1, String value2) {
            addCriterion("YSYJ_DM not between", value1, value2, "ysyjDm");
            return (Criteria) this;
        }

        public Criteria andYsyjMcIsNull() {
            addCriterion("YSYJ_MC is null");
            return (Criteria) this;
        }

        public Criteria andYsyjMcIsNotNull() {
            addCriterion("YSYJ_MC is not null");
            return (Criteria) this;
        }

        public Criteria andYsyjMcEqualTo(String value) {
            addCriterion("YSYJ_MC =", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcNotEqualTo(String value) {
            addCriterion("YSYJ_MC <>", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcGreaterThan(String value) {
            addCriterion("YSYJ_MC >", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcGreaterThanOrEqualTo(String value) {
            addCriterion("YSYJ_MC >=", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcLessThan(String value) {
            addCriterion("YSYJ_MC <", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcLessThanOrEqualTo(String value) {
            addCriterion("YSYJ_MC <=", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcLike(String value) {
            addCriterion("YSYJ_MC like", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcNotLike(String value) {
            addCriterion("YSYJ_MC not like", value, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcIn(List<String> values) {
            addCriterion("YSYJ_MC in", values, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcNotIn(List<String> values) {
            addCriterion("YSYJ_MC not in", values, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcBetween(String value1, String value2) {
            addCriterion("YSYJ_MC between", value1, value2, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andYsyjMcNotBetween(String value1, String value2) {
            addCriterion("YSYJ_MC not between", value1, value2, "ysyjMc");
            return (Criteria) this;
        }

        public Criteria andSfjbajIsNull() {
            addCriterion("SFJBAJ is null");
            return (Criteria) this;
        }

        public Criteria andSfjbajIsNotNull() {
            addCriterion("SFJBAJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfjbajEqualTo(String value) {
            addCriterion("SFJBAJ =", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajNotEqualTo(String value) {
            addCriterion("SFJBAJ <>", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajGreaterThan(String value) {
            addCriterion("SFJBAJ >", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajGreaterThanOrEqualTo(String value) {
            addCriterion("SFJBAJ >=", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajLessThan(String value) {
            addCriterion("SFJBAJ <", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajLessThanOrEqualTo(String value) {
            addCriterion("SFJBAJ <=", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajLike(String value) {
            addCriterion("SFJBAJ like", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajNotLike(String value) {
            addCriterion("SFJBAJ not like", value, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajIn(List<String> values) {
            addCriterion("SFJBAJ in", values, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajNotIn(List<String> values) {
            addCriterion("SFJBAJ not in", values, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajBetween(String value1, String value2) {
            addCriterion("SFJBAJ between", value1, value2, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andSfjbajNotBetween(String value1, String value2) {
            addCriterion("SFJBAJ not between", value1, value2, "sfjbaj");
            return (Criteria) this;
        }

        public Criteria andZxhdDmIsNull() {
            addCriterion("ZXHD_DM is null");
            return (Criteria) this;
        }

        public Criteria andZxhdDmIsNotNull() {
            addCriterion("ZXHD_DM is not null");
            return (Criteria) this;
        }

        public Criteria andZxhdDmEqualTo(String value) {
            addCriterion("ZXHD_DM =", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmNotEqualTo(String value) {
            addCriterion("ZXHD_DM <>", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmGreaterThan(String value) {
            addCriterion("ZXHD_DM >", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmGreaterThanOrEqualTo(String value) {
            addCriterion("ZXHD_DM >=", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmLessThan(String value) {
            addCriterion("ZXHD_DM <", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmLessThanOrEqualTo(String value) {
            addCriterion("ZXHD_DM <=", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmLike(String value) {
            addCriterion("ZXHD_DM like", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmNotLike(String value) {
            addCriterion("ZXHD_DM not like", value, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmIn(List<String> values) {
            addCriterion("ZXHD_DM in", values, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmNotIn(List<String> values) {
            addCriterion("ZXHD_DM not in", values, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmBetween(String value1, String value2) {
            addCriterion("ZXHD_DM between", value1, value2, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andZxhdDmNotBetween(String value1, String value2) {
            addCriterion("ZXHD_DM not between", value1, value2, "zxhdDm");
            return (Criteria) this;
        }

        public Criteria andDqyjjdIsNull() {
            addCriterion("DQYJJD is null");
            return (Criteria) this;
        }

        public Criteria andDqyjjdIsNotNull() {
            addCriterion("DQYJJD is not null");
            return (Criteria) this;
        }

        public Criteria andDqyjjdEqualTo(String value) {
            addCriterion("DQYJJD =", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdNotEqualTo(String value) {
            addCriterion("DQYJJD <>", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdGreaterThan(String value) {
            addCriterion("DQYJJD >", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdGreaterThanOrEqualTo(String value) {
            addCriterion("DQYJJD >=", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdLessThan(String value) {
            addCriterion("DQYJJD <", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdLessThanOrEqualTo(String value) {
            addCriterion("DQYJJD <=", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdLike(String value) {
            addCriterion("DQYJJD like", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdNotLike(String value) {
            addCriterion("DQYJJD not like", value, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdIn(List<String> values) {
            addCriterion("DQYJJD in", values, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdNotIn(List<String> values) {
            addCriterion("DQYJJD not in", values, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdBetween(String value1, String value2) {
            addCriterion("DQYJJD between", value1, value2, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andDqyjjdNotBetween(String value1, String value2) {
            addCriterion("DQYJJD not between", value1, value2, "dqyjjd");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmIsNull() {
            addCriterion("YASCSSJD_DM is null");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmIsNotNull() {
            addCriterion("YASCSSJD_DM is not null");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmEqualTo(String value) {
            addCriterion("YASCSSJD_DM =", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmNotEqualTo(String value) {
            addCriterion("YASCSSJD_DM <>", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmGreaterThan(String value) {
            addCriterion("YASCSSJD_DM >", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmGreaterThanOrEqualTo(String value) {
            addCriterion("YASCSSJD_DM >=", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmLessThan(String value) {
            addCriterion("YASCSSJD_DM <", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmLessThanOrEqualTo(String value) {
            addCriterion("YASCSSJD_DM <=", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmLike(String value) {
            addCriterion("YASCSSJD_DM like", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmNotLike(String value) {
            addCriterion("YASCSSJD_DM not like", value, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmIn(List<String> values) {
            addCriterion("YASCSSJD_DM in", values, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmNotIn(List<String> values) {
            addCriterion("YASCSSJD_DM not in", values, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmBetween(String value1, String value2) {
            addCriterion("YASCSSJD_DM between", value1, value2, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdDmNotBetween(String value1, String value2) {
            addCriterion("YASCSSJD_DM not between", value1, value2, "yascssjdDm");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcIsNull() {
            addCriterion("YASCSSJD_MC is null");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcIsNotNull() {
            addCriterion("YASCSSJD_MC is not null");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcEqualTo(String value) {
            addCriterion("YASCSSJD_MC =", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcNotEqualTo(String value) {
            addCriterion("YASCSSJD_MC <>", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcGreaterThan(String value) {
            addCriterion("YASCSSJD_MC >", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcGreaterThanOrEqualTo(String value) {
            addCriterion("YASCSSJD_MC >=", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcLessThan(String value) {
            addCriterion("YASCSSJD_MC <", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcLessThanOrEqualTo(String value) {
            addCriterion("YASCSSJD_MC <=", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcLike(String value) {
            addCriterion("YASCSSJD_MC like", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcNotLike(String value) {
            addCriterion("YASCSSJD_MC not like", value, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcIn(List<String> values) {
            addCriterion("YASCSSJD_MC in", values, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcNotIn(List<String> values) {
            addCriterion("YASCSSJD_MC not in", values, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcBetween(String value1, String value2) {
            addCriterion("YASCSSJD_MC between", value1, value2, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYascssjdMcNotBetween(String value1, String value2) {
            addCriterion("YASCSSJD_MC not between", value1, value2, "yascssjdMc");
            return (Criteria) this;
        }

        public Criteria andYsrjdhIsNull() {
            addCriterion("YSRJDH is null");
            return (Criteria) this;
        }

        public Criteria andYsrjdhIsNotNull() {
            addCriterion("YSRJDH is not null");
            return (Criteria) this;
        }

        public Criteria andYsrjdhEqualTo(String value) {
            addCriterion("YSRJDH =", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhNotEqualTo(String value) {
            addCriterion("YSRJDH <>", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhGreaterThan(String value) {
            addCriterion("YSRJDH >", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhGreaterThanOrEqualTo(String value) {
            addCriterion("YSRJDH >=", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhLessThan(String value) {
            addCriterion("YSRJDH <", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhLessThanOrEqualTo(String value) {
            addCriterion("YSRJDH <=", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhLike(String value) {
            addCriterion("YSRJDH like", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhNotLike(String value) {
            addCriterion("YSRJDH not like", value, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhIn(List<String> values) {
            addCriterion("YSRJDH in", values, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhNotIn(List<String> values) {
            addCriterion("YSRJDH not in", values, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhBetween(String value1, String value2) {
            addCriterion("YSRJDH between", value1, value2, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andYsrjdhNotBetween(String value1, String value2) {
            addCriterion("YSRJDH not between", value1, value2, "ysrjdh");
            return (Criteria) this;
        }

        public Criteria andAjlyDmIsNull() {
            addCriterion("AJLY_DM is null");
            return (Criteria) this;
        }

        public Criteria andAjlyDmIsNotNull() {
            addCriterion("AJLY_DM is not null");
            return (Criteria) this;
        }

        public Criteria andAjlyDmEqualTo(String value) {
            addCriterion("AJLY_DM =", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmNotEqualTo(String value) {
            addCriterion("AJLY_DM <>", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmGreaterThan(String value) {
            addCriterion("AJLY_DM >", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmGreaterThanOrEqualTo(String value) {
            addCriterion("AJLY_DM >=", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmLessThan(String value) {
            addCriterion("AJLY_DM <", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmLessThanOrEqualTo(String value) {
            addCriterion("AJLY_DM <=", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmLike(String value) {
            addCriterion("AJLY_DM like", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmNotLike(String value) {
            addCriterion("AJLY_DM not like", value, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmIn(List<String> values) {
            addCriterion("AJLY_DM in", values, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmNotIn(List<String> values) {
            addCriterion("AJLY_DM not in", values, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmBetween(String value1, String value2) {
            addCriterion("AJLY_DM between", value1, value2, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyDmNotBetween(String value1, String value2) {
            addCriterion("AJLY_DM not between", value1, value2, "ajlyDm");
            return (Criteria) this;
        }

        public Criteria andAjlyMcIsNull() {
            addCriterion("AJLY_MC is null");
            return (Criteria) this;
        }

        public Criteria andAjlyMcIsNotNull() {
            addCriterion("AJLY_MC is not null");
            return (Criteria) this;
        }

        public Criteria andAjlyMcEqualTo(String value) {
            addCriterion("AJLY_MC =", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcNotEqualTo(String value) {
            addCriterion("AJLY_MC <>", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcGreaterThan(String value) {
            addCriterion("AJLY_MC >", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcGreaterThanOrEqualTo(String value) {
            addCriterion("AJLY_MC >=", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcLessThan(String value) {
            addCriterion("AJLY_MC <", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcLessThanOrEqualTo(String value) {
            addCriterion("AJLY_MC <=", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcLike(String value) {
            addCriterion("AJLY_MC like", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcNotLike(String value) {
            addCriterion("AJLY_MC not like", value, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcIn(List<String> values) {
            addCriterion("AJLY_MC in", values, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcNotIn(List<String> values) {
            addCriterion("AJLY_MC not in", values, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcBetween(String value1, String value2) {
            addCriterion("AJLY_MC between", value1, value2, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andAjlyMcNotBetween(String value1, String value2) {
            addCriterion("AJLY_MC not between", value1, value2, "ajlyMc");
            return (Criteria) this;
        }

        public Criteria andSfzxhdIsNull() {
            addCriterion("SFZXHD is null");
            return (Criteria) this;
        }

        public Criteria andSfzxhdIsNotNull() {
            addCriterion("SFZXHD is not null");
            return (Criteria) this;
        }

        public Criteria andSfzxhdEqualTo(String value) {
            addCriterion("SFZXHD =", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdNotEqualTo(String value) {
            addCriterion("SFZXHD <>", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdGreaterThan(String value) {
            addCriterion("SFZXHD >", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdGreaterThanOrEqualTo(String value) {
            addCriterion("SFZXHD >=", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdLessThan(String value) {
            addCriterion("SFZXHD <", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdLessThanOrEqualTo(String value) {
            addCriterion("SFZXHD <=", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdLike(String value) {
            addCriterion("SFZXHD like", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdNotLike(String value) {
            addCriterion("SFZXHD not like", value, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdIn(List<String> values) {
            addCriterion("SFZXHD in", values, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdNotIn(List<String> values) {
            addCriterion("SFZXHD not in", values, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdBetween(String value1, String value2) {
            addCriterion("SFZXHD between", value1, value2, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andSfzxhdNotBetween(String value1, String value2) {
            addCriterion("SFZXHD not between", value1, value2, "sfzxhd");
            return (Criteria) this;
        }

        public Criteria andZxhdmcIsNull() {
            addCriterion("ZXHDMC is null");
            return (Criteria) this;
        }

        public Criteria andZxhdmcIsNotNull() {
            addCriterion("ZXHDMC is not null");
            return (Criteria) this;
        }

        public Criteria andZxhdmcEqualTo(String value) {
            addCriterion("ZXHDMC =", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcNotEqualTo(String value) {
            addCriterion("ZXHDMC <>", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcGreaterThan(String value) {
            addCriterion("ZXHDMC >", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcGreaterThanOrEqualTo(String value) {
            addCriterion("ZXHDMC >=", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcLessThan(String value) {
            addCriterion("ZXHDMC <", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcLessThanOrEqualTo(String value) {
            addCriterion("ZXHDMC <=", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcLike(String value) {
            addCriterion("ZXHDMC like", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcNotLike(String value) {
            addCriterion("ZXHDMC not like", value, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcIn(List<String> values) {
            addCriterion("ZXHDMC in", values, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcNotIn(List<String> values) {
            addCriterion("ZXHDMC not in", values, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcBetween(String value1, String value2) {
            addCriterion("ZXHDMC between", value1, value2, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andZxhdmcNotBetween(String value1, String value2) {
            addCriterion("ZXHDMC not between", value1, value2, "zxhdmc");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmIsNull() {
            addCriterion("FZRSFZHM is null");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmIsNotNull() {
            addCriterion("FZRSFZHM is not null");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmEqualTo(String value) {
            addCriterion("FZRSFZHM =", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmNotEqualTo(String value) {
            addCriterion("FZRSFZHM <>", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmGreaterThan(String value) {
            addCriterion("FZRSFZHM >", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmGreaterThanOrEqualTo(String value) {
            addCriterion("FZRSFZHM >=", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmLessThan(String value) {
            addCriterion("FZRSFZHM <", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmLessThanOrEqualTo(String value) {
            addCriterion("FZRSFZHM <=", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmLike(String value) {
            addCriterion("FZRSFZHM like", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmNotLike(String value) {
            addCriterion("FZRSFZHM not like", value, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmIn(List<String> values) {
            addCriterion("FZRSFZHM in", values, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmNotIn(List<String> values) {
            addCriterion("FZRSFZHM not in", values, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmBetween(String value1, String value2) {
            addCriterion("FZRSFZHM between", value1, value2, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andFzrsfzhmNotBetween(String value1, String value2) {
            addCriterion("FZRSFZHM not between", value1, value2, "fzrsfzhm");
            return (Criteria) this;
        }

        public Criteria andBadybmIsNull() {
            addCriterion("BADYBM is null");
            return (Criteria) this;
        }

        public Criteria andBadybmIsNotNull() {
            addCriterion("BADYBM is not null");
            return (Criteria) this;
        }

        public Criteria andBadybmEqualTo(String value) {
            addCriterion("BADYBM =", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmNotEqualTo(String value) {
            addCriterion("BADYBM <>", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmGreaterThan(String value) {
            addCriterion("BADYBM >", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmGreaterThanOrEqualTo(String value) {
            addCriterion("BADYBM >=", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmLessThan(String value) {
            addCriterion("BADYBM <", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmLessThanOrEqualTo(String value) {
            addCriterion("BADYBM <=", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmLike(String value) {
            addCriterion("BADYBM like", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmNotLike(String value) {
            addCriterion("BADYBM not like", value, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmIn(List<String> values) {
            addCriterion("BADYBM in", values, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmNotIn(List<String> values) {
            addCriterion("BADYBM not in", values, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmBetween(String value1, String value2) {
            addCriterion("BADYBM between", value1, value2, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadybmNotBetween(String value1, String value2) {
            addCriterion("BADYBM not between", value1, value2, "badybm");
            return (Criteria) this;
        }

        public Criteria andBadymcIsNull() {
            addCriterion("BADYMC is null");
            return (Criteria) this;
        }

        public Criteria andBadymcIsNotNull() {
            addCriterion("BADYMC is not null");
            return (Criteria) this;
        }

        public Criteria andBadymcEqualTo(String value) {
            addCriterion("BADYMC =", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcNotEqualTo(String value) {
            addCriterion("BADYMC <>", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcGreaterThan(String value) {
            addCriterion("BADYMC >", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcGreaterThanOrEqualTo(String value) {
            addCriterion("BADYMC >=", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcLessThan(String value) {
            addCriterion("BADYMC <", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcLessThanOrEqualTo(String value) {
            addCriterion("BADYMC <=", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcLike(String value) {
            addCriterion("BADYMC like", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcNotLike(String value) {
            addCriterion("BADYMC not like", value, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcIn(List<String> values) {
            addCriterion("BADYMC in", values, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcNotIn(List<String> values) {
            addCriterion("BADYMC not in", values, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcBetween(String value1, String value2) {
            addCriterion("BADYMC between", value1, value2, "badymc");
            return (Criteria) this;
        }

        public Criteria andBadymcNotBetween(String value1, String value2) {
            addCriterion("BADYMC not between", value1, value2, "badymc");
            return (Criteria) this;
        }

        public Criteria andShjdztIsNull() {
            addCriterion("SHJDZT is null");
            return (Criteria) this;
        }

        public Criteria andShjdztIsNotNull() {
            addCriterion("SHJDZT is not null");
            return (Criteria) this;
        }

        public Criteria andShjdztEqualTo(String value) {
            addCriterion("SHJDZT =", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztNotEqualTo(String value) {
            addCriterion("SHJDZT <>", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztGreaterThan(String value) {
            addCriterion("SHJDZT >", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztGreaterThanOrEqualTo(String value) {
            addCriterion("SHJDZT >=", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztLessThan(String value) {
            addCriterion("SHJDZT <", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztLessThanOrEqualTo(String value) {
            addCriterion("SHJDZT <=", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztLike(String value) {
            addCriterion("SHJDZT like", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztNotLike(String value) {
            addCriterion("SHJDZT not like", value, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztIn(List<String> values) {
            addCriterion("SHJDZT in", values, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztNotIn(List<String> values) {
            addCriterion("SHJDZT not in", values, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztBetween(String value1, String value2) {
            addCriterion("SHJDZT between", value1, value2, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andShjdztNotBetween(String value1, String value2) {
            addCriterion("SHJDZT not between", value1, value2, "shjdzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztIsNull() {
            addCriterion("LCJKZT is null");
            return (Criteria) this;
        }

        public Criteria andLcjkztIsNotNull() {
            addCriterion("LCJKZT is not null");
            return (Criteria) this;
        }

        public Criteria andLcjkztEqualTo(String value) {
            addCriterion("LCJKZT =", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztNotEqualTo(String value) {
            addCriterion("LCJKZT <>", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztGreaterThan(String value) {
            addCriterion("LCJKZT >", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztGreaterThanOrEqualTo(String value) {
            addCriterion("LCJKZT >=", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztLessThan(String value) {
            addCriterion("LCJKZT <", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztLessThanOrEqualTo(String value) {
            addCriterion("LCJKZT <=", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztLike(String value) {
            addCriterion("LCJKZT like", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztNotLike(String value) {
            addCriterion("LCJKZT not like", value, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztIn(List<String> values) {
            addCriterion("LCJKZT in", values, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztNotIn(List<String> values) {
            addCriterion("LCJKZT not in", values, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztBetween(String value1, String value2) {
            addCriterion("LCJKZT between", value1, value2, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andLcjkztNotBetween(String value1, String value2) {
            addCriterion("LCJKZT not between", value1, value2, "lcjkzt");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfIsNull() {
            addCriterion("ZCJGSFJYSYRZRF is null");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfIsNotNull() {
            addCriterion("ZCJGSFJYSYRZRF is not null");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfEqualTo(String value) {
            addCriterion("ZCJGSFJYSYRZRF =", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfNotEqualTo(String value) {
            addCriterion("ZCJGSFJYSYRZRF <>", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfGreaterThan(String value) {
            addCriterion("ZCJGSFJYSYRZRF >", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfGreaterThanOrEqualTo(String value) {
            addCriterion("ZCJGSFJYSYRZRF >=", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfLessThan(String value) {
            addCriterion("ZCJGSFJYSYRZRF <", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfLessThanOrEqualTo(String value) {
            addCriterion("ZCJGSFJYSYRZRF <=", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfLike(String value) {
            addCriterion("ZCJGSFJYSYRZRF like", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfNotLike(String value) {
            addCriterion("ZCJGSFJYSYRZRF not like", value, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfIn(List<String> values) {
            addCriterion("ZCJGSFJYSYRZRF in", values, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfNotIn(List<String> values) {
            addCriterion("ZCJGSFJYSYRZRF not in", values, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfBetween(String value1, String value2) {
            addCriterion("ZCJGSFJYSYRZRF between", value1, value2, "zcjgsfjysyrzrf");
            return (Criteria) this;
        }

        public Criteria andZcjgsfjysyrzrfNotBetween(String value1, String value2) {
            addCriterion("ZCJGSFJYSYRZRF not between", value1, value2, "zcjgsfjysyrzrf");
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