package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class YxRzCzExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxRzCzExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andLogidIsNull() {
            addCriterion("LOGID is null");
            return (Criteria) this;
        }

        public Criteria andLogidIsNotNull() {
            addCriterion("LOGID is not null");
            return (Criteria) this;
        }

        public Criteria andLogidEqualTo(BigDecimal value) {
            addCriterion("LOGID =", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotEqualTo(BigDecimal value) {
            addCriterion("LOGID <>", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThan(BigDecimal value) {
            addCriterion("LOGID >", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGID >=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThan(BigDecimal value) {
            addCriterion("LOGID <", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOGID <=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidIn(List<BigDecimal> values) {
            addCriterion("LOGID in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotIn(List<BigDecimal> values) {
            addCriterion("LOGID not in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGID between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOGID not between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andCzsjIsNull() {
            addCriterion("CZSJ is null");
            return (Criteria) this;
        }

        public Criteria andCzsjIsNotNull() {
            addCriterion("CZSJ is not null");
            return (Criteria) this;
        }

        public Criteria andCzsjEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ =", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ <>", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjGreaterThan(Date value) {
            addCriterionForJDBCDate("CZSJ >", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ >=", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjLessThan(Date value) {
            addCriterionForJDBCDate("CZSJ <", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ <=", value, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjIn(List<Date> values) {
            addCriterionForJDBCDate("CZSJ in", values, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotIn(List<Date> values) {
            addCriterionForJDBCDate("CZSJ not in", values, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CZSJ between", value1, value2, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzsjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CZSJ not between", value1, value2, "czsj");
            return (Criteria) this;
        }

        public Criteria andCzlxIsNull() {
            addCriterion("CZLX is null");
            return (Criteria) this;
        }

        public Criteria andCzlxIsNotNull() {
            addCriterion("CZLX is not null");
            return (Criteria) this;
        }

        public Criteria andCzlxEqualTo(String value) {
            addCriterion("CZLX =", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxNotEqualTo(String value) {
            addCriterion("CZLX <>", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxGreaterThan(String value) {
            addCriterion("CZLX >", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxGreaterThanOrEqualTo(String value) {
            addCriterion("CZLX >=", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxLessThan(String value) {
            addCriterion("CZLX <", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxLessThanOrEqualTo(String value) {
            addCriterion("CZLX <=", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxLike(String value) {
            addCriterion("CZLX like", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxNotLike(String value) {
            addCriterion("CZLX not like", value, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxIn(List<String> values) {
            addCriterion("CZLX in", values, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxNotIn(List<String> values) {
            addCriterion("CZLX not in", values, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxBetween(String value1, String value2) {
            addCriterion("CZLX between", value1, value2, "czlx");
            return (Criteria) this;
        }

        public Criteria andCzlxNotBetween(String value1, String value2) {
            addCriterion("CZLX not between", value1, value2, "czlx");
            return (Criteria) this;
        }

        public Criteria andGnbmIsNull() {
            addCriterion("GNBM is null");
            return (Criteria) this;
        }

        public Criteria andGnbmIsNotNull() {
            addCriterion("GNBM is not null");
            return (Criteria) this;
        }

        public Criteria andGnbmEqualTo(String value) {
            addCriterion("GNBM =", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmNotEqualTo(String value) {
            addCriterion("GNBM <>", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmGreaterThan(String value) {
            addCriterion("GNBM >", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmGreaterThanOrEqualTo(String value) {
            addCriterion("GNBM >=", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmLessThan(String value) {
            addCriterion("GNBM <", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmLessThanOrEqualTo(String value) {
            addCriterion("GNBM <=", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmLike(String value) {
            addCriterion("GNBM like", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmNotLike(String value) {
            addCriterion("GNBM not like", value, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmIn(List<String> values) {
            addCriterion("GNBM in", values, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmNotIn(List<String> values) {
            addCriterion("GNBM not in", values, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmBetween(String value1, String value2) {
            addCriterion("GNBM between", value1, value2, "gnbm");
            return (Criteria) this;
        }

        public Criteria andGnbmNotBetween(String value1, String value2) {
            addCriterion("GNBM not between", value1, value2, "gnbm");
            return (Criteria) this;
        }

        public Criteria andCzsmIsNull() {
            addCriterion("CZSM is null");
            return (Criteria) this;
        }

        public Criteria andCzsmIsNotNull() {
            addCriterion("CZSM is not null");
            return (Criteria) this;
        }

        public Criteria andCzsmEqualTo(String value) {
            addCriterion("CZSM =", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotEqualTo(String value) {
            addCriterion("CZSM <>", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmGreaterThan(String value) {
            addCriterion("CZSM >", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmGreaterThanOrEqualTo(String value) {
            addCriterion("CZSM >=", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLessThan(String value) {
            addCriterion("CZSM <", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLessThanOrEqualTo(String value) {
            addCriterion("CZSM <=", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmLike(String value) {
            addCriterion("CZSM like", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotLike(String value) {
            addCriterion("CZSM not like", value, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmIn(List<String> values) {
            addCriterion("CZSM in", values, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotIn(List<String> values) {
            addCriterion("CZSM not in", values, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmBetween(String value1, String value2) {
            addCriterion("CZSM between", value1, value2, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzsmNotBetween(String value1, String value2) {
            addCriterion("CZSM not between", value1, value2, "czsm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmIsNull() {
            addCriterion("CZRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmIsNotNull() {
            addCriterion("CZRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmEqualTo(String value) {
            addCriterion("CZRDWBM =", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmNotEqualTo(String value) {
            addCriterion("CZRDWBM <>", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmGreaterThan(String value) {
            addCriterion("CZRDWBM >", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("CZRDWBM >=", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmLessThan(String value) {
            addCriterion("CZRDWBM <", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmLessThanOrEqualTo(String value) {
            addCriterion("CZRDWBM <=", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmLike(String value) {
            addCriterion("CZRDWBM like", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmNotLike(String value) {
            addCriterion("CZRDWBM not like", value, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmIn(List<String> values) {
            addCriterion("CZRDWBM in", values, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmNotIn(List<String> values) {
            addCriterion("CZRDWBM not in", values, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmBetween(String value1, String value2) {
            addCriterion("CZRDWBM between", value1, value2, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrdwbmNotBetween(String value1, String value2) {
            addCriterion("CZRDWBM not between", value1, value2, "czrdwbm");
            return (Criteria) this;
        }

        public Criteria andCzrghIsNull() {
            addCriterion("CZRGH is null");
            return (Criteria) this;
        }

        public Criteria andCzrghIsNotNull() {
            addCriterion("CZRGH is not null");
            return (Criteria) this;
        }

        public Criteria andCzrghEqualTo(String value) {
            addCriterion("CZRGH =", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghNotEqualTo(String value) {
            addCriterion("CZRGH <>", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghGreaterThan(String value) {
            addCriterion("CZRGH >", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghGreaterThanOrEqualTo(String value) {
            addCriterion("CZRGH >=", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghLessThan(String value) {
            addCriterion("CZRGH <", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghLessThanOrEqualTo(String value) {
            addCriterion("CZRGH <=", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghLike(String value) {
            addCriterion("CZRGH like", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghNotLike(String value) {
            addCriterion("CZRGH not like", value, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghIn(List<String> values) {
            addCriterion("CZRGH in", values, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghNotIn(List<String> values) {
            addCriterion("CZRGH not in", values, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghBetween(String value1, String value2) {
            addCriterion("CZRGH between", value1, value2, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrghNotBetween(String value1, String value2) {
            addCriterion("CZRGH not between", value1, value2, "czrgh");
            return (Criteria) this;
        }

        public Criteria andCzrmcIsNull() {
            addCriterion("CZRMC is null");
            return (Criteria) this;
        }

        public Criteria andCzrmcIsNotNull() {
            addCriterion("CZRMC is not null");
            return (Criteria) this;
        }

        public Criteria andCzrmcEqualTo(String value) {
            addCriterion("CZRMC =", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcNotEqualTo(String value) {
            addCriterion("CZRMC <>", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcGreaterThan(String value) {
            addCriterion("CZRMC >", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcGreaterThanOrEqualTo(String value) {
            addCriterion("CZRMC >=", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcLessThan(String value) {
            addCriterion("CZRMC <", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcLessThanOrEqualTo(String value) {
            addCriterion("CZRMC <=", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcLike(String value) {
            addCriterion("CZRMC like", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcNotLike(String value) {
            addCriterion("CZRMC not like", value, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcIn(List<String> values) {
            addCriterion("CZRMC in", values, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcNotIn(List<String> values) {
            addCriterion("CZRMC not in", values, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcBetween(String value1, String value2) {
            addCriterion("CZRMC between", value1, value2, "czrmc");
            return (Criteria) this;
        }

        public Criteria andCzrmcNotBetween(String value1, String value2) {
            addCriterion("CZRMC not between", value1, value2, "czrmc");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPclbbmIsNull() {
            addCriterion("PCLBBM is null");
            return (Criteria) this;
        }

        public Criteria andPclbbmIsNotNull() {
            addCriterion("PCLBBM is not null");
            return (Criteria) this;
        }

        public Criteria andPclbbmEqualTo(String value) {
            addCriterion("PCLBBM =", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmNotEqualTo(String value) {
            addCriterion("PCLBBM <>", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmGreaterThan(String value) {
            addCriterion("PCLBBM >", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCLBBM >=", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmLessThan(String value) {
            addCriterion("PCLBBM <", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmLessThanOrEqualTo(String value) {
            addCriterion("PCLBBM <=", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmLike(String value) {
            addCriterion("PCLBBM like", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmNotLike(String value) {
            addCriterion("PCLBBM not like", value, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmIn(List<String> values) {
            addCriterion("PCLBBM in", values, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmNotIn(List<String> values) {
            addCriterion("PCLBBM not in", values, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmBetween(String value1, String value2) {
            addCriterion("PCLBBM between", value1, value2, "pclbbm");
            return (Criteria) this;
        }

        public Criteria andPclbbmNotBetween(String value1, String value2) {
            addCriterion("PCLBBM not between", value1, value2, "pclbbm");
            return (Criteria) this;
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

        public Criteria andGnmcIsNull() {
            addCriterion("GNMC is null");
            return (Criteria) this;
        }

        public Criteria andGnmcIsNotNull() {
            addCriterion("GNMC is not null");
            return (Criteria) this;
        }

        public Criteria andGnmcEqualTo(String value) {
            addCriterion("GNMC =", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcNotEqualTo(String value) {
            addCriterion("GNMC <>", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcGreaterThan(String value) {
            addCriterion("GNMC >", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcGreaterThanOrEqualTo(String value) {
            addCriterion("GNMC >=", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcLessThan(String value) {
            addCriterion("GNMC <", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcLessThanOrEqualTo(String value) {
            addCriterion("GNMC <=", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcLike(String value) {
            addCriterion("GNMC like", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcNotLike(String value) {
            addCriterion("GNMC not like", value, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcIn(List<String> values) {
            addCriterion("GNMC in", values, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcNotIn(List<String> values) {
            addCriterion("GNMC not in", values, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcBetween(String value1, String value2) {
            addCriterion("GNMC between", value1, value2, "gnmc");
            return (Criteria) this;
        }

        public Criteria andGnmcNotBetween(String value1, String value2) {
            addCriterion("GNMC not between", value1, value2, "gnmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcIsNull() {
            addCriterion("CZRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcIsNotNull() {
            addCriterion("CZRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcEqualTo(String value) {
            addCriterion("CZRDWMC =", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcNotEqualTo(String value) {
            addCriterion("CZRDWMC <>", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcGreaterThan(String value) {
            addCriterion("CZRDWMC >", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("CZRDWMC >=", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcLessThan(String value) {
            addCriterion("CZRDWMC <", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcLessThanOrEqualTo(String value) {
            addCriterion("CZRDWMC <=", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcLike(String value) {
            addCriterion("CZRDWMC like", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcNotLike(String value) {
            addCriterion("CZRDWMC not like", value, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcIn(List<String> values) {
            addCriterion("CZRDWMC in", values, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcNotIn(List<String> values) {
            addCriterion("CZRDWMC not in", values, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcBetween(String value1, String value2) {
            addCriterion("CZRDWMC between", value1, value2, "czrdwmc");
            return (Criteria) this;
        }

        public Criteria andCzrdwmcNotBetween(String value1, String value2) {
            addCriterion("CZRDWMC not between", value1, value2, "czrdwmc");
            return (Criteria) this;
        }
    }

    /**
     */
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