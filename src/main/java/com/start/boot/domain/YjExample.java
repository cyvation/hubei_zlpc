package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YjExample() {
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

        public Criteria andPcslbmIsNull() {
            addCriterion("PCSLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcslbmIsNotNull() {
            addCriterion("PCSLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcslbmEqualTo(String value) {
            addCriterion("PCSLBM =", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotEqualTo(String value) {
            addCriterion("PCSLBM <>", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmGreaterThan(String value) {
            addCriterion("PCSLBM >", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCSLBM >=", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLessThan(String value) {
            addCriterion("PCSLBM <", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLessThanOrEqualTo(String value) {
            addCriterion("PCSLBM <=", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLike(String value) {
            addCriterion("PCSLBM like", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotLike(String value) {
            addCriterion("PCSLBM not like", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmIn(List<String> values) {
            addCriterion("PCSLBM in", values, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotIn(List<String> values) {
            addCriterion("PCSLBM not in", values, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmBetween(String value1, String value2) {
            addCriterion("PCSLBM between", value1, value2, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotBetween(String value1, String value2) {
            addCriterion("PCSLBM not between", value1, value2, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcyjbhIsNull() {
            addCriterion("PCYJBH is null");
            return (Criteria) this;
        }

        public Criteria andPcyjbhIsNotNull() {
            addCriterion("PCYJBH is not null");
            return (Criteria) this;
        }

        public Criteria andPcyjbhEqualTo(String value) {
            addCriterion("PCYJBH =", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhNotEqualTo(String value) {
            addCriterion("PCYJBH <>", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhGreaterThan(String value) {
            addCriterion("PCYJBH >", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhGreaterThanOrEqualTo(String value) {
            addCriterion("PCYJBH >=", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhLessThan(String value) {
            addCriterion("PCYJBH <", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhLessThanOrEqualTo(String value) {
            addCriterion("PCYJBH <=", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhLike(String value) {
            addCriterion("PCYJBH like", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhNotLike(String value) {
            addCriterion("PCYJBH not like", value, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhIn(List<String> values) {
            addCriterion("PCYJBH in", values, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhNotIn(List<String> values) {
            addCriterion("PCYJBH not in", values, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhBetween(String value1, String value2) {
            addCriterion("PCYJBH between", value1, value2, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjbhNotBetween(String value1, String value2) {
            addCriterion("PCYJBH not between", value1, value2, "pcyjbh");
            return (Criteria) this;
        }

        public Criteria andPcyjlxIsNull() {
            addCriterion("PCYJLX is null");
            return (Criteria) this;
        }

        public Criteria andPcyjlxIsNotNull() {
            addCriterion("PCYJLX is not null");
            return (Criteria) this;
        }

        public Criteria andPcyjlxEqualTo(String value) {
            addCriterion("PCYJLX =", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxNotEqualTo(String value) {
            addCriterion("PCYJLX <>", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxGreaterThan(String value) {
            addCriterion("PCYJLX >", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxGreaterThanOrEqualTo(String value) {
            addCriterion("PCYJLX >=", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxLessThan(String value) {
            addCriterion("PCYJLX <", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxLessThanOrEqualTo(String value) {
            addCriterion("PCYJLX <=", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxLike(String value) {
            addCriterion("PCYJLX like", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxNotLike(String value) {
            addCriterion("PCYJLX not like", value, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxIn(List<String> values) {
            addCriterion("PCYJLX in", values, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxNotIn(List<String> values) {
            addCriterion("PCYJLX not in", values, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxBetween(String value1, String value2) {
            addCriterion("PCYJLX between", value1, value2, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjlxNotBetween(String value1, String value2) {
            addCriterion("PCYJLX not between", value1, value2, "pcyjlx");
            return (Criteria) this;
        }

        public Criteria andPcyjjlIsNull() {
            addCriterion("PCYJJL is null");
            return (Criteria) this;
        }

        public Criteria andPcyjjlIsNotNull() {
            addCriterion("PCYJJL is not null");
            return (Criteria) this;
        }

        public Criteria andPcyjjlEqualTo(String value) {
            addCriterion("PCYJJL =", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlNotEqualTo(String value) {
            addCriterion("PCYJJL <>", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlGreaterThan(String value) {
            addCriterion("PCYJJL >", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlGreaterThanOrEqualTo(String value) {
            addCriterion("PCYJJL >=", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlLessThan(String value) {
            addCriterion("PCYJJL <", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlLessThanOrEqualTo(String value) {
            addCriterion("PCYJJL <=", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlLike(String value) {
            addCriterion("PCYJJL like", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlNotLike(String value) {
            addCriterion("PCYJJL not like", value, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlIn(List<String> values) {
            addCriterion("PCYJJL in", values, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlNotIn(List<String> values) {
            addCriterion("PCYJJL not in", values, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlBetween(String value1, String value2) {
            addCriterion("PCYJJL between", value1, value2, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andPcyjjlNotBetween(String value1, String value2) {
            addCriterion("PCYJJL not between", value1, value2, "pcyjjl");
            return (Criteria) this;
        }

        public Criteria andYjtcsjIsNull() {
            addCriterion("YJTCSJ is null");
            return (Criteria) this;
        }

        public Criteria andYjtcsjIsNotNull() {
            addCriterion("YJTCSJ is not null");
            return (Criteria) this;
        }

        public Criteria andYjtcsjEqualTo(Date value) {
            addCriterion("YJTCSJ =", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjNotEqualTo(Date value) {
            addCriterion("YJTCSJ <>", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjGreaterThan(Date value) {
            addCriterion("YJTCSJ >", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjGreaterThanOrEqualTo(Date value) {
            addCriterion("YJTCSJ >=", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjLessThan(Date value) {
            addCriterion("YJTCSJ <", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjLessThanOrEqualTo(Date value) {
            addCriterion("YJTCSJ <=", value, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjIn(List<Date> values) {
            addCriterion("YJTCSJ in", values, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjNotIn(List<Date> values) {
            addCriterion("YJTCSJ not in", values, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjBetween(Date value1, Date value2) {
            addCriterion("YJTCSJ between", value1, value2, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andYjtcsjNotBetween(Date value1, Date value2) {
            addCriterion("YJTCSJ not between", value1, value2, "yjtcsj");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmIsNull() {
            addCriterion("TCRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmIsNotNull() {
            addCriterion("TCRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmEqualTo(String value) {
            addCriterion("TCRDWBM =", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmNotEqualTo(String value) {
            addCriterion("TCRDWBM <>", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmGreaterThan(String value) {
            addCriterion("TCRDWBM >", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("TCRDWBM >=", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmLessThan(String value) {
            addCriterion("TCRDWBM <", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmLessThanOrEqualTo(String value) {
            addCriterion("TCRDWBM <=", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmLike(String value) {
            addCriterion("TCRDWBM like", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmNotLike(String value) {
            addCriterion("TCRDWBM not like", value, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmIn(List<String> values) {
            addCriterion("TCRDWBM in", values, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmNotIn(List<String> values) {
            addCriterion("TCRDWBM not in", values, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmBetween(String value1, String value2) {
            addCriterion("TCRDWBM between", value1, value2, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwbmNotBetween(String value1, String value2) {
            addCriterion("TCRDWBM not between", value1, value2, "tcrdwbm");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcIsNull() {
            addCriterion("TCRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcIsNotNull() {
            addCriterion("TCRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcEqualTo(String value) {
            addCriterion("TCRDWMC =", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcNotEqualTo(String value) {
            addCriterion("TCRDWMC <>", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcGreaterThan(String value) {
            addCriterion("TCRDWMC >", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("TCRDWMC >=", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcLessThan(String value) {
            addCriterion("TCRDWMC <", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcLessThanOrEqualTo(String value) {
            addCriterion("TCRDWMC <=", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcLike(String value) {
            addCriterion("TCRDWMC like", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcNotLike(String value) {
            addCriterion("TCRDWMC not like", value, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcIn(List<String> values) {
            addCriterion("TCRDWMC in", values, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcNotIn(List<String> values) {
            addCriterion("TCRDWMC not in", values, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcBetween(String value1, String value2) {
            addCriterion("TCRDWMC between", value1, value2, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrdwmcNotBetween(String value1, String value2) {
            addCriterion("TCRDWMC not between", value1, value2, "tcrdwmc");
            return (Criteria) this;
        }

        public Criteria andTcrghIsNull() {
            addCriterion("TCRGH is null");
            return (Criteria) this;
        }

        public Criteria andTcrghIsNotNull() {
            addCriterion("TCRGH is not null");
            return (Criteria) this;
        }

        public Criteria andTcrghEqualTo(String value) {
            addCriterion("TCRGH =", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghNotEqualTo(String value) {
            addCriterion("TCRGH <>", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghGreaterThan(String value) {
            addCriterion("TCRGH >", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghGreaterThanOrEqualTo(String value) {
            addCriterion("TCRGH >=", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghLessThan(String value) {
            addCriterion("TCRGH <", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghLessThanOrEqualTo(String value) {
            addCriterion("TCRGH <=", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghLike(String value) {
            addCriterion("TCRGH like", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghNotLike(String value) {
            addCriterion("TCRGH not like", value, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghIn(List<String> values) {
            addCriterion("TCRGH in", values, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghNotIn(List<String> values) {
            addCriterion("TCRGH not in", values, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghBetween(String value1, String value2) {
            addCriterion("TCRGH between", value1, value2, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrghNotBetween(String value1, String value2) {
            addCriterion("TCRGH not between", value1, value2, "tcrgh");
            return (Criteria) this;
        }

        public Criteria andTcrmcIsNull() {
            addCriterion("TCRMC is null");
            return (Criteria) this;
        }

        public Criteria andTcrmcIsNotNull() {
            addCriterion("TCRMC is not null");
            return (Criteria) this;
        }

        public Criteria andTcrmcEqualTo(String value) {
            addCriterion("TCRMC =", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcNotEqualTo(String value) {
            addCriterion("TCRMC <>", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcGreaterThan(String value) {
            addCriterion("TCRMC >", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcGreaterThanOrEqualTo(String value) {
            addCriterion("TCRMC >=", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcLessThan(String value) {
            addCriterion("TCRMC <", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcLessThanOrEqualTo(String value) {
            addCriterion("TCRMC <=", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcLike(String value) {
            addCriterion("TCRMC like", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcNotLike(String value) {
            addCriterion("TCRMC not like", value, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcIn(List<String> values) {
            addCriterion("TCRMC in", values, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcNotIn(List<String> values) {
            addCriterion("TCRMC not in", values, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcBetween(String value1, String value2) {
            addCriterion("TCRMC between", value1, value2, "tcrmc");
            return (Criteria) this;
        }

        public Criteria andTcrmcNotBetween(String value1, String value2) {
            addCriterion("TCRMC not between", value1, value2, "tcrmc");
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