package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class YxDcPdxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxDcPdxExample() {
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

        public Criteria andPdxbmIsNull() {
            addCriterion("PDXBM is null");
            return (Criteria) this;
        }

        public Criteria andPdxbmIsNotNull() {
            addCriterion("PDXBM is not null");
            return (Criteria) this;
        }

        public Criteria andPdxbmEqualTo(String value) {
            addCriterion("PDXBM =", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmNotEqualTo(String value) {
            addCriterion("PDXBM <>", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmGreaterThan(String value) {
            addCriterion("PDXBM >", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmGreaterThanOrEqualTo(String value) {
            addCriterion("PDXBM >=", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmLessThan(String value) {
            addCriterion("PDXBM <", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmLessThanOrEqualTo(String value) {
            addCriterion("PDXBM <=", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmLike(String value) {
            addCriterion("PDXBM like", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmNotLike(String value) {
            addCriterion("PDXBM not like", value, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmIn(List<String> values) {
            addCriterion("PDXBM in", values, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmNotIn(List<String> values) {
            addCriterion("PDXBM not in", values, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmBetween(String value1, String value2) {
            addCriterion("PDXBM between", value1, value2, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxbmNotBetween(String value1, String value2) {
            addCriterion("PDXBM not between", value1, value2, "pdxbm");
            return (Criteria) this;
        }

        public Criteria andPdxmcIsNull() {
            addCriterion("PDXMC is null");
            return (Criteria) this;
        }

        public Criteria andPdxmcIsNotNull() {
            addCriterion("PDXMC is not null");
            return (Criteria) this;
        }

        public Criteria andPdxmcEqualTo(String value) {
            addCriterion("PDXMC =", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcNotEqualTo(String value) {
            addCriterion("PDXMC <>", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcGreaterThan(String value) {
            addCriterion("PDXMC >", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcGreaterThanOrEqualTo(String value) {
            addCriterion("PDXMC >=", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcLessThan(String value) {
            addCriterion("PDXMC <", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcLessThanOrEqualTo(String value) {
            addCriterion("PDXMC <=", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcLike(String value) {
            addCriterion("PDXMC like", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcNotLike(String value) {
            addCriterion("PDXMC not like", value, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcIn(List<String> values) {
            addCriterion("PDXMC in", values, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcNotIn(List<String> values) {
            addCriterion("PDXMC not in", values, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcBetween(String value1, String value2) {
            addCriterion("PDXMC between", value1, value2, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxmcNotBetween(String value1, String value2) {
            addCriterion("PDXMC not between", value1, value2, "pdxmc");
            return (Criteria) this;
        }

        public Criteria andPdxflbmIsNull() {
            addCriterion("PDXFLBM is null");
            return (Criteria) this;
        }

        public Criteria andPdxflbmIsNotNull() {
            addCriterion("PDXFLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPdxflbmEqualTo(String value) {
            addCriterion("PDXFLBM =", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmNotEqualTo(String value) {
            addCriterion("PDXFLBM <>", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmGreaterThan(String value) {
            addCriterion("PDXFLBM >", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmGreaterThanOrEqualTo(String value) {
            addCriterion("PDXFLBM >=", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmLessThan(String value) {
            addCriterion("PDXFLBM <", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmLessThanOrEqualTo(String value) {
            addCriterion("PDXFLBM <=", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmLike(String value) {
            addCriterion("PDXFLBM like", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmNotLike(String value) {
            addCriterion("PDXFLBM not like", value, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmIn(List<String> values) {
            addCriterion("PDXFLBM in", values, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmNotIn(List<String> values) {
            addCriterion("PDXFLBM not in", values, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmBetween(String value1, String value2) {
            addCriterion("PDXFLBM between", value1, value2, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andPdxflbmNotBetween(String value1, String value2) {
            addCriterion("PDXFLBM not between", value1, value2, "pdxflbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmIsNull() {
            addCriterion("DCMBBM is null");
            return (Criteria) this;
        }

        public Criteria andDcmbbmIsNotNull() {
            addCriterion("DCMBBM is not null");
            return (Criteria) this;
        }

        public Criteria andDcmbbmEqualTo(String value) {
            addCriterion("DCMBBM =", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmNotEqualTo(String value) {
            addCriterion("DCMBBM <>", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmGreaterThan(String value) {
            addCriterion("DCMBBM >", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmGreaterThanOrEqualTo(String value) {
            addCriterion("DCMBBM >=", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmLessThan(String value) {
            addCriterion("DCMBBM <", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmLessThanOrEqualTo(String value) {
            addCriterion("DCMBBM <=", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmLike(String value) {
            addCriterion("DCMBBM like", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmNotLike(String value) {
            addCriterion("DCMBBM not like", value, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmIn(List<String> values) {
            addCriterion("DCMBBM in", values, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmNotIn(List<String> values) {
            addCriterion("DCMBBM not in", values, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmBetween(String value1, String value2) {
            addCriterion("DCMBBM between", value1, value2, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andDcmbbmNotBetween(String value1, String value2) {
            addCriterion("DCMBBM not between", value1, value2, "dcmbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmIsNull() {
            addCriterion("AJLBBM is null");
            return (Criteria) this;
        }

        public Criteria andAjlbbmIsNotNull() {
            addCriterion("AJLBBM is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbbmEqualTo(String value) {
            addCriterion("AJLBBM =", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmNotEqualTo(String value) {
            addCriterion("AJLBBM <>", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmGreaterThan(String value) {
            addCriterion("AJLBBM >", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmGreaterThanOrEqualTo(String value) {
            addCriterion("AJLBBM >=", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmLessThan(String value) {
            addCriterion("AJLBBM <", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmLessThanOrEqualTo(String value) {
            addCriterion("AJLBBM <=", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmLike(String value) {
            addCriterion("AJLBBM like", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmNotLike(String value) {
            addCriterion("AJLBBM not like", value, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmIn(List<String> values) {
            addCriterion("AJLBBM in", values, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmNotIn(List<String> values) {
            addCriterion("AJLBBM not in", values, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmBetween(String value1, String value2) {
            addCriterion("AJLBBM between", value1, value2, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbbmNotBetween(String value1, String value2) {
            addCriterion("AJLBBM not between", value1, value2, "ajlbbm");
            return (Criteria) this;
        }

        public Criteria andAjlbmcIsNull() {
            addCriterion("AJLBMC is null");
            return (Criteria) this;
        }

        public Criteria andAjlbmcIsNotNull() {
            addCriterion("AJLBMC is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbmcEqualTo(String value) {
            addCriterion("AJLBMC =", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcNotEqualTo(String value) {
            addCriterion("AJLBMC <>", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcGreaterThan(String value) {
            addCriterion("AJLBMC >", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcGreaterThanOrEqualTo(String value) {
            addCriterion("AJLBMC >=", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcLessThan(String value) {
            addCriterion("AJLBMC <", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcLessThanOrEqualTo(String value) {
            addCriterion("AJLBMC <=", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcLike(String value) {
            addCriterion("AJLBMC like", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcNotLike(String value) {
            addCriterion("AJLBMC not like", value, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcIn(List<String> values) {
            addCriterion("AJLBMC in", values, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcNotIn(List<String> values) {
            addCriterion("AJLBMC not in", values, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcBetween(String value1, String value2) {
            addCriterion("AJLBMC between", value1, value2, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andAjlbmcNotBetween(String value1, String value2) {
            addCriterion("AJLBMC not between", value1, value2, "ajlbmc");
            return (Criteria) this;
        }

        public Criteria andPdxlxIsNull() {
            addCriterion("PDXLX is null");
            return (Criteria) this;
        }

        public Criteria andPdxlxIsNotNull() {
            addCriterion("PDXLX is not null");
            return (Criteria) this;
        }

        public Criteria andPdxlxEqualTo(BigDecimal value) {
            addCriterion("PDXLX =", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxNotEqualTo(BigDecimal value) {
            addCriterion("PDXLX <>", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxGreaterThan(BigDecimal value) {
            addCriterion("PDXLX >", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PDXLX >=", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxLessThan(BigDecimal value) {
            addCriterion("PDXLX <", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PDXLX <=", value, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxIn(List<BigDecimal> values) {
            addCriterion("PDXLX in", values, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxNotIn(List<BigDecimal> values) {
            addCriterion("PDXLX not in", values, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PDXLX between", value1, value2, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPdxlxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PDXLX not between", value1, value2, "pdxlx");
            return (Criteria) this;
        }

        public Criteria andPcfsIsNull() {
            addCriterion("PCFS is null");
            return (Criteria) this;
        }

        public Criteria andPcfsIsNotNull() {
            addCriterion("PCFS is not null");
            return (Criteria) this;
        }

        public Criteria andPcfsEqualTo(String value) {
            addCriterion("PCFS =", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsNotEqualTo(String value) {
            addCriterion("PCFS <>", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsGreaterThan(String value) {
            addCriterion("PCFS >", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsGreaterThanOrEqualTo(String value) {
            addCriterion("PCFS >=", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsLessThan(String value) {
            addCriterion("PCFS <", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsLessThanOrEqualTo(String value) {
            addCriterion("PCFS <=", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsLike(String value) {
            addCriterion("PCFS like", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsNotLike(String value) {
            addCriterion("PCFS not like", value, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsIn(List<String> values) {
            addCriterion("PCFS in", values, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsNotIn(List<String> values) {
            addCriterion("PCFS not in", values, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsBetween(String value1, String value2) {
            addCriterion("PCFS between", value1, value2, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcfsNotBetween(String value1, String value2) {
            addCriterion("PCFS not between", value1, value2, "pcfs");
            return (Criteria) this;
        }

        public Criteria andPcjlbmIsNull() {
            addCriterion("PCJLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcjlbmIsNotNull() {
            addCriterion("PCJLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcjlbmEqualTo(String value) {
            addCriterion("PCJLBM =", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotEqualTo(String value) {
            addCriterion("PCJLBM <>", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmGreaterThan(String value) {
            addCriterion("PCJLBM >", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCJLBM >=", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLessThan(String value) {
            addCriterion("PCJLBM <", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLessThanOrEqualTo(String value) {
            addCriterion("PCJLBM <=", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLike(String value) {
            addCriterion("PCJLBM like", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotLike(String value) {
            addCriterion("PCJLBM not like", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmIn(List<String> values) {
            addCriterion("PCJLBM in", values, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotIn(List<String> values) {
            addCriterion("PCJLBM not in", values, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmBetween(String value1, String value2) {
            addCriterion("PCJLBM between", value1, value2, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotBetween(String value1, String value2) {
            addCriterion("PCJLBM not between", value1, value2, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andFzGdIsNull() {
            addCriterion("FZ_GD is null");
            return (Criteria) this;
        }

        public Criteria andFzGdIsNotNull() {
            addCriterion("FZ_GD is not null");
            return (Criteria) this;
        }

        public Criteria andFzGdEqualTo(BigDecimal value) {
            addCriterion("FZ_GD =", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdNotEqualTo(BigDecimal value) {
            addCriterion("FZ_GD <>", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdGreaterThan(BigDecimal value) {
            addCriterion("FZ_GD >", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FZ_GD >=", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdLessThan(BigDecimal value) {
            addCriterion("FZ_GD <", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FZ_GD <=", value, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdIn(List<BigDecimal> values) {
            addCriterion("FZ_GD in", values, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdNotIn(List<BigDecimal> values) {
            addCriterion("FZ_GD not in", values, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FZ_GD between", value1, value2, "fzGd");
            return (Criteria) this;
        }

        public Criteria andFzGdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FZ_GD not between", value1, value2, "fzGd");
            return (Criteria) this;
        }

        public Criteria andZdpccxIsNull() {
            addCriterion("ZDPCCX is null");
            return (Criteria) this;
        }

        public Criteria andZdpccxIsNotNull() {
            addCriterion("ZDPCCX is not null");
            return (Criteria) this;
        }

        public Criteria andZdpccxEqualTo(String value) {
            addCriterion("ZDPCCX =", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxNotEqualTo(String value) {
            addCriterion("ZDPCCX <>", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxGreaterThan(String value) {
            addCriterion("ZDPCCX >", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxGreaterThanOrEqualTo(String value) {
            addCriterion("ZDPCCX >=", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxLessThan(String value) {
            addCriterion("ZDPCCX <", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxLessThanOrEqualTo(String value) {
            addCriterion("ZDPCCX <=", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxLike(String value) {
            addCriterion("ZDPCCX like", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxNotLike(String value) {
            addCriterion("ZDPCCX not like", value, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxIn(List<String> values) {
            addCriterion("ZDPCCX in", values, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxNotIn(List<String> values) {
            addCriterion("ZDPCCX not in", values, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxBetween(String value1, String value2) {
            addCriterion("ZDPCCX between", value1, value2, "zdpccx");
            return (Criteria) this;
        }

        public Criteria andZdpccxNotBetween(String value1, String value2) {
            addCriterion("ZDPCCX not between", value1, value2, "zdpccx");
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

        public Criteria andSftjIsNull() {
            addCriterion("SFTJ is null");
            return (Criteria) this;
        }

        public Criteria andSftjIsNotNull() {
            addCriterion("SFTJ is not null");
            return (Criteria) this;
        }

        public Criteria andSftjEqualTo(String value) {
            addCriterion("SFTJ =", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotEqualTo(String value) {
            addCriterion("SFTJ <>", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjGreaterThan(String value) {
            addCriterion("SFTJ >", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjGreaterThanOrEqualTo(String value) {
            addCriterion("SFTJ >=", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLessThan(String value) {
            addCriterion("SFTJ <", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLessThanOrEqualTo(String value) {
            addCriterion("SFTJ <=", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLike(String value) {
            addCriterion("SFTJ like", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotLike(String value) {
            addCriterion("SFTJ not like", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjIn(List<String> values) {
            addCriterion("SFTJ in", values, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotIn(List<String> values) {
            addCriterion("SFTJ not in", values, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjBetween(String value1, String value2) {
            addCriterion("SFTJ between", value1, value2, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotBetween(String value1, String value2) {
            addCriterion("SFTJ not between", value1, value2, "sftj");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("BZ is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("BZ is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("BZ like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("BZ not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("BZ in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("BZ not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andPdjgIsNull() {
            addCriterion("PDJG is null");
            return (Criteria) this;
        }

        public Criteria andPdjgIsNotNull() {
            addCriterion("PDJG is not null");
            return (Criteria) this;
        }

        public Criteria andPdjgEqualTo(String value) {
            addCriterion("PDJG =", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgNotEqualTo(String value) {
            addCriterion("PDJG <>", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgGreaterThan(String value) {
            addCriterion("PDJG >", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgGreaterThanOrEqualTo(String value) {
            addCriterion("PDJG >=", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgLessThan(String value) {
            addCriterion("PDJG <", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgLessThanOrEqualTo(String value) {
            addCriterion("PDJG <=", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgLike(String value) {
            addCriterion("PDJG like", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgNotLike(String value) {
            addCriterion("PDJG not like", value, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgIn(List<String> values) {
            addCriterion("PDJG in", values, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgNotIn(List<String> values) {
            addCriterion("PDJG not in", values, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgBetween(String value1, String value2) {
            addCriterion("PDJG between", value1, value2, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdjgNotBetween(String value1, String value2) {
            addCriterion("PDJG not between", value1, value2, "pdjg");
            return (Criteria) this;
        }

        public Criteria andPdyjIsNull() {
            addCriterion("PDYJ is null");
            return (Criteria) this;
        }

        public Criteria andPdyjIsNotNull() {
            addCriterion("PDYJ is not null");
            return (Criteria) this;
        }

        public Criteria andPdyjEqualTo(String value) {
            addCriterion("PDYJ =", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjNotEqualTo(String value) {
            addCriterion("PDYJ <>", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjGreaterThan(String value) {
            addCriterion("PDYJ >", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjGreaterThanOrEqualTo(String value) {
            addCriterion("PDYJ >=", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjLessThan(String value) {
            addCriterion("PDYJ <", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjLessThanOrEqualTo(String value) {
            addCriterion("PDYJ <=", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjLike(String value) {
            addCriterion("PDYJ like", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjNotLike(String value) {
            addCriterion("PDYJ not like", value, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjIn(List<String> values) {
            addCriterion("PDYJ in", values, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjNotIn(List<String> values) {
            addCriterion("PDYJ not in", values, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjBetween(String value1, String value2) {
            addCriterion("PDYJ between", value1, value2, "pdyj");
            return (Criteria) this;
        }

        public Criteria andPdyjNotBetween(String value1, String value2) {
            addCriterion("PDYJ not between", value1, value2, "pdyj");
            return (Criteria) this;
        }

        public Criteria andJlsjIsNull() {
            addCriterion("JLSJ is null");
            return (Criteria) this;
        }

        public Criteria andJlsjIsNotNull() {
            addCriterion("JLSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJlsjEqualTo(Date value) {
            addCriterionForJDBCDate("JLSJ =", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotEqualTo(Date value) {
            addCriterionForJDBCDate("JLSJ <>", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjGreaterThan(Date value) {
            addCriterionForJDBCDate("JLSJ >", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JLSJ >=", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjLessThan(Date value) {
            addCriterionForJDBCDate("JLSJ <", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("JLSJ <=", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjIn(List<Date> values) {
            addCriterionForJDBCDate("JLSJ in", values, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotIn(List<Date> values) {
            addCriterionForJDBCDate("JLSJ not in", values, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JLSJ between", value1, value2, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("JLSJ not between", value1, value2, "jlsj");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIsNull() {
            addCriterion("PCRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIsNotNull() {
            addCriterion("PCRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmEqualTo(String value) {
            addCriterion("PCRDWBM =", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotEqualTo(String value) {
            addCriterion("PCRDWBM <>", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmGreaterThan(String value) {
            addCriterion("PCRDWBM >", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCRDWBM >=", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLessThan(String value) {
            addCriterion("PCRDWBM <", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLessThanOrEqualTo(String value) {
            addCriterion("PCRDWBM <=", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLike(String value) {
            addCriterion("PCRDWBM like", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotLike(String value) {
            addCriterion("PCRDWBM not like", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIn(List<String> values) {
            addCriterion("PCRDWBM in", values, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotIn(List<String> values) {
            addCriterion("PCRDWBM not in", values, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmBetween(String value1, String value2) {
            addCriterion("PCRDWBM between", value1, value2, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotBetween(String value1, String value2) {
            addCriterion("PCRDWBM not between", value1, value2, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIsNull() {
            addCriterion("PCRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIsNotNull() {
            addCriterion("PCRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcEqualTo(String value) {
            addCriterion("PCRDWMC =", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotEqualTo(String value) {
            addCriterion("PCRDWMC <>", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcGreaterThan(String value) {
            addCriterion("PCRDWMC >", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCRDWMC >=", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLessThan(String value) {
            addCriterion("PCRDWMC <", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLessThanOrEqualTo(String value) {
            addCriterion("PCRDWMC <=", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLike(String value) {
            addCriterion("PCRDWMC like", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotLike(String value) {
            addCriterion("PCRDWMC not like", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIn(List<String> values) {
            addCriterion("PCRDWMC in", values, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotIn(List<String> values) {
            addCriterion("PCRDWMC not in", values, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcBetween(String value1, String value2) {
            addCriterion("PCRDWMC between", value1, value2, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotBetween(String value1, String value2) {
            addCriterion("PCRDWMC not between", value1, value2, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrghIsNull() {
            addCriterion("PCRGH is null");
            return (Criteria) this;
        }

        public Criteria andPcrghIsNotNull() {
            addCriterion("PCRGH is not null");
            return (Criteria) this;
        }

        public Criteria andPcrghEqualTo(String value) {
            addCriterion("PCRGH =", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotEqualTo(String value) {
            addCriterion("PCRGH <>", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghGreaterThan(String value) {
            addCriterion("PCRGH >", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghGreaterThanOrEqualTo(String value) {
            addCriterion("PCRGH >=", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLessThan(String value) {
            addCriterion("PCRGH <", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLessThanOrEqualTo(String value) {
            addCriterion("PCRGH <=", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLike(String value) {
            addCriterion("PCRGH like", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotLike(String value) {
            addCriterion("PCRGH not like", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghIn(List<String> values) {
            addCriterion("PCRGH in", values, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotIn(List<String> values) {
            addCriterion("PCRGH not in", values, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghBetween(String value1, String value2) {
            addCriterion("PCRGH between", value1, value2, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotBetween(String value1, String value2) {
            addCriterion("PCRGH not between", value1, value2, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrmcIsNull() {
            addCriterion("PCRMC is null");
            return (Criteria) this;
        }

        public Criteria andPcrmcIsNotNull() {
            addCriterion("PCRMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrmcEqualTo(String value) {
            addCriterion("PCRMC =", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotEqualTo(String value) {
            addCriterion("PCRMC <>", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcGreaterThan(String value) {
            addCriterion("PCRMC >", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCRMC >=", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLessThan(String value) {
            addCriterion("PCRMC <", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLessThanOrEqualTo(String value) {
            addCriterion("PCRMC <=", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLike(String value) {
            addCriterion("PCRMC like", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotLike(String value) {
            addCriterion("PCRMC not like", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcIn(List<String> values) {
            addCriterion("PCRMC in", values, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotIn(List<String> values) {
            addCriterion("PCRMC not in", values, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcBetween(String value1, String value2) {
            addCriterion("PCRMC between", value1, value2, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotBetween(String value1, String value2) {
            addCriterion("PCRMC not between", value1, value2, "pcrmc");
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

        public Criteria andFlxtdmIsNull() {
            addCriterion("FLXTDM is null");
            return (Criteria) this;
        }

        public Criteria andFlxtdmIsNotNull() {
            addCriterion("FLXTDM is not null");
            return (Criteria) this;
        }

        public Criteria andFlxtdmEqualTo(String value) {
            addCriterion("FLXTDM =", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmNotEqualTo(String value) {
            addCriterion("FLXTDM <>", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmGreaterThan(String value) {
            addCriterion("FLXTDM >", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmGreaterThanOrEqualTo(String value) {
            addCriterion("FLXTDM >=", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmLessThan(String value) {
            addCriterion("FLXTDM <", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmLessThanOrEqualTo(String value) {
            addCriterion("FLXTDM <=", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmLike(String value) {
            addCriterion("FLXTDM like", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmNotLike(String value) {
            addCriterion("FLXTDM not like", value, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmIn(List<String> values) {
            addCriterion("FLXTDM in", values, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmNotIn(List<String> values) {
            addCriterion("FLXTDM not in", values, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmBetween(String value1, String value2) {
            addCriterion("FLXTDM between", value1, value2, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFlxtdmNotBetween(String value1, String value2) {
            addCriterion("FLXTDM not between", value1, value2, "flxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmIsNull() {
            addCriterion("FFLXTDM is null");
            return (Criteria) this;
        }

        public Criteria andFflxtdmIsNotNull() {
            addCriterion("FFLXTDM is not null");
            return (Criteria) this;
        }

        public Criteria andFflxtdmEqualTo(String value) {
            addCriterion("FFLXTDM =", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmNotEqualTo(String value) {
            addCriterion("FFLXTDM <>", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmGreaterThan(String value) {
            addCriterion("FFLXTDM >", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmGreaterThanOrEqualTo(String value) {
            addCriterion("FFLXTDM >=", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmLessThan(String value) {
            addCriterion("FFLXTDM <", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmLessThanOrEqualTo(String value) {
            addCriterion("FFLXTDM <=", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmLike(String value) {
            addCriterion("FFLXTDM like", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmNotLike(String value) {
            addCriterion("FFLXTDM not like", value, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmIn(List<String> values) {
            addCriterion("FFLXTDM in", values, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmNotIn(List<String> values) {
            addCriterion("FFLXTDM not in", values, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmBetween(String value1, String value2) {
            addCriterion("FFLXTDM between", value1, value2, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andFflxtdmNotBetween(String value1, String value2) {
            addCriterion("FFLXTDM not between", value1, value2, "fflxtdm");
            return (Criteria) this;
        }

        public Criteria andYwtxIsNull() {
            addCriterion("YWTX is null");
            return (Criteria) this;
        }

        public Criteria andYwtxIsNotNull() {
            addCriterion("YWTX is not null");
            return (Criteria) this;
        }

        public Criteria andYwtxEqualTo(String value) {
            addCriterion("YWTX =", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxNotEqualTo(String value) {
            addCriterion("YWTX <>", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxGreaterThan(String value) {
            addCriterion("YWTX >", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxGreaterThanOrEqualTo(String value) {
            addCriterion("YWTX >=", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxLessThan(String value) {
            addCriterion("YWTX <", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxLessThanOrEqualTo(String value) {
            addCriterion("YWTX <=", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxLike(String value) {
            addCriterion("YWTX like", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxNotLike(String value) {
            addCriterion("YWTX not like", value, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxIn(List<String> values) {
            addCriterion("YWTX in", values, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxNotIn(List<String> values) {
            addCriterion("YWTX not in", values, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxBetween(String value1, String value2) {
            addCriterion("YWTX between", value1, value2, "ywtx");
            return (Criteria) this;
        }

        public Criteria andYwtxNotBetween(String value1, String value2) {
            addCriterion("YWTX not between", value1, value2, "ywtx");
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

        public Criteria andWayIsNull() {
            addCriterion("WAY is null");
            return (Criteria) this;
        }

        public Criteria andWayIsNotNull() {
            addCriterion("WAY is not null");
            return (Criteria) this;
        }

        public Criteria andWayEqualTo(String value) {
            addCriterion("WAY =", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotEqualTo(String value) {
            addCriterion("WAY <>", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThan(String value) {
            addCriterion("WAY >", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayGreaterThanOrEqualTo(String value) {
            addCriterion("WAY >=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThan(String value) {
            addCriterion("WAY <", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLessThanOrEqualTo(String value) {
            addCriterion("WAY <=", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayLike(String value) {
            addCriterion("WAY like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotLike(String value) {
            addCriterion("WAY not like", value, "way");
            return (Criteria) this;
        }

        public Criteria andWayIn(List<String> values) {
            addCriterion("WAY in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotIn(List<String> values) {
            addCriterion("WAY not in", values, "way");
            return (Criteria) this;
        }

        public Criteria andWayBetween(String value1, String value2) {
            addCriterion("WAY between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andWayNotBetween(String value1, String value2) {
            addCriterion("WAY not between", value1, value2, "way");
            return (Criteria) this;
        }

        public Criteria andXtdmIsNull() {
            addCriterion("XTDM is null");
            return (Criteria) this;
        }

        public Criteria andXtdmIsNotNull() {
            addCriterion("XTDM is not null");
            return (Criteria) this;
        }

        public Criteria andXtdmEqualTo(String value) {
            addCriterion("XTDM =", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmNotEqualTo(String value) {
            addCriterion("XTDM <>", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmGreaterThan(String value) {
            addCriterion("XTDM >", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmGreaterThanOrEqualTo(String value) {
            addCriterion("XTDM >=", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmLessThan(String value) {
            addCriterion("XTDM <", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmLessThanOrEqualTo(String value) {
            addCriterion("XTDM <=", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmLike(String value) {
            addCriterion("XTDM like", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmNotLike(String value) {
            addCriterion("XTDM not like", value, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmIn(List<String> values) {
            addCriterion("XTDM in", values, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmNotIn(List<String> values) {
            addCriterion("XTDM not in", values, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmBetween(String value1, String value2) {
            addCriterion("XTDM between", value1, value2, "xtdm");
            return (Criteria) this;
        }

        public Criteria andXtdmNotBetween(String value1, String value2) {
            addCriterion("XTDM not between", value1, value2, "xtdm");
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