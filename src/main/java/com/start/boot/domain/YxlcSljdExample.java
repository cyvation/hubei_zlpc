package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YxlcSljdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxlcSljdExample() {
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

        public Criteria andLcsljdbhIsNull() {
            addCriterion("LCSLJDBH is null");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhIsNotNull() {
            addCriterion("LCSLJDBH is not null");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhEqualTo(String value) {
            addCriterion("LCSLJDBH =", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhNotEqualTo(String value) {
            addCriterion("LCSLJDBH <>", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhGreaterThan(String value) {
            addCriterion("LCSLJDBH >", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhGreaterThanOrEqualTo(String value) {
            addCriterion("LCSLJDBH >=", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhLessThan(String value) {
            addCriterion("LCSLJDBH <", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhLessThanOrEqualTo(String value) {
            addCriterion("LCSLJDBH <=", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhLike(String value) {
            addCriterion("LCSLJDBH like", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhNotLike(String value) {
            addCriterion("LCSLJDBH not like", value, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhIn(List<String> values) {
            addCriterion("LCSLJDBH in", values, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhNotIn(List<String> values) {
            addCriterion("LCSLJDBH not in", values, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhBetween(String value1, String value2) {
            addCriterion("LCSLJDBH between", value1, value2, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcsljdbhNotBetween(String value1, String value2) {
            addCriterion("LCSLJDBH not between", value1, value2, "lcsljdbh");
            return (Criteria) this;
        }

        public Criteria andLcbmIsNull() {
            addCriterion("LCBM is null");
            return (Criteria) this;
        }

        public Criteria andLcbmIsNotNull() {
            addCriterion("LCBM is not null");
            return (Criteria) this;
        }

        public Criteria andLcbmEqualTo(String value) {
            addCriterion("LCBM =", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmNotEqualTo(String value) {
            addCriterion("LCBM <>", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmGreaterThan(String value) {
            addCriterion("LCBM >", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmGreaterThanOrEqualTo(String value) {
            addCriterion("LCBM >=", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmLessThan(String value) {
            addCriterion("LCBM <", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmLessThanOrEqualTo(String value) {
            addCriterion("LCBM <=", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmLike(String value) {
            addCriterion("LCBM like", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmNotLike(String value) {
            addCriterion("LCBM not like", value, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmIn(List<String> values) {
            addCriterion("LCBM in", values, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmNotIn(List<String> values) {
            addCriterion("LCBM not in", values, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmBetween(String value1, String value2) {
            addCriterion("LCBM between", value1, value2, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcbmNotBetween(String value1, String value2) {
            addCriterion("LCBM not between", value1, value2, "lcbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmIsNull() {
            addCriterion("LCJDBM is null");
            return (Criteria) this;
        }

        public Criteria andLcjdbmIsNotNull() {
            addCriterion("LCJDBM is not null");
            return (Criteria) this;
        }

        public Criteria andLcjdbmEqualTo(String value) {
            addCriterion("LCJDBM =", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmNotEqualTo(String value) {
            addCriterion("LCJDBM <>", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmGreaterThan(String value) {
            addCriterion("LCJDBM >", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmGreaterThanOrEqualTo(String value) {
            addCriterion("LCJDBM >=", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmLessThan(String value) {
            addCriterion("LCJDBM <", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmLessThanOrEqualTo(String value) {
            addCriterion("LCJDBM <=", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmLike(String value) {
            addCriterion("LCJDBM like", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmNotLike(String value) {
            addCriterion("LCJDBM not like", value, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmIn(List<String> values) {
            addCriterion("LCJDBM in", values, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmNotIn(List<String> values) {
            addCriterion("LCJDBM not in", values, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmBetween(String value1, String value2) {
            addCriterion("LCJDBM between", value1, value2, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdbmNotBetween(String value1, String value2) {
            addCriterion("LCJDBM not between", value1, value2, "lcjdbm");
            return (Criteria) this;
        }

        public Criteria andLcjdmcIsNull() {
            addCriterion("LCJDMC is null");
            return (Criteria) this;
        }

        public Criteria andLcjdmcIsNotNull() {
            addCriterion("LCJDMC is not null");
            return (Criteria) this;
        }

        public Criteria andLcjdmcEqualTo(String value) {
            addCriterion("LCJDMC =", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcNotEqualTo(String value) {
            addCriterion("LCJDMC <>", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcGreaterThan(String value) {
            addCriterion("LCJDMC >", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcGreaterThanOrEqualTo(String value) {
            addCriterion("LCJDMC >=", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcLessThan(String value) {
            addCriterion("LCJDMC <", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcLessThanOrEqualTo(String value) {
            addCriterion("LCJDMC <=", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcLike(String value) {
            addCriterion("LCJDMC like", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcNotLike(String value) {
            addCriterion("LCJDMC not like", value, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcIn(List<String> values) {
            addCriterion("LCJDMC in", values, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcNotIn(List<String> values) {
            addCriterion("LCJDMC not in", values, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcBetween(String value1, String value2) {
            addCriterion("LCJDMC between", value1, value2, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andLcjdmcNotBetween(String value1, String value2) {
            addCriterion("LCJDMC not between", value1, value2, "lcjdmc");
            return (Criteria) this;
        }

        public Criteria andJdlxIsNull() {
            addCriterion("JDLX is null");
            return (Criteria) this;
        }

        public Criteria andJdlxIsNotNull() {
            addCriterion("JDLX is not null");
            return (Criteria) this;
        }

        public Criteria andJdlxEqualTo(String value) {
            addCriterion("JDLX =", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxNotEqualTo(String value) {
            addCriterion("JDLX <>", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxGreaterThan(String value) {
            addCriterion("JDLX >", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxGreaterThanOrEqualTo(String value) {
            addCriterion("JDLX >=", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxLessThan(String value) {
            addCriterion("JDLX <", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxLessThanOrEqualTo(String value) {
            addCriterion("JDLX <=", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxLike(String value) {
            addCriterion("JDLX like", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxNotLike(String value) {
            addCriterion("JDLX not like", value, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxIn(List<String> values) {
            addCriterion("JDLX in", values, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxNotIn(List<String> values) {
            addCriterion("JDLX not in", values, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxBetween(String value1, String value2) {
            addCriterion("JDLX between", value1, value2, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdlxNotBetween(String value1, String value2) {
            addCriterion("JDLX not between", value1, value2, "jdlx");
            return (Criteria) this;
        }

        public Criteria andJdsxIsNull() {
            addCriterion("JDSX is null");
            return (Criteria) this;
        }

        public Criteria andJdsxIsNotNull() {
            addCriterion("JDSX is not null");
            return (Criteria) this;
        }

        public Criteria andJdsxEqualTo(String value) {
            addCriterion("JDSX =", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxNotEqualTo(String value) {
            addCriterion("JDSX <>", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxGreaterThan(String value) {
            addCriterion("JDSX >", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxGreaterThanOrEqualTo(String value) {
            addCriterion("JDSX >=", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxLessThan(String value) {
            addCriterion("JDSX <", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxLessThanOrEqualTo(String value) {
            addCriterion("JDSX <=", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxLike(String value) {
            addCriterion("JDSX like", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxNotLike(String value) {
            addCriterion("JDSX not like", value, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxIn(List<String> values) {
            addCriterion("JDSX in", values, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxNotIn(List<String> values) {
            addCriterion("JDSX not in", values, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxBetween(String value1, String value2) {
            addCriterion("JDSX between", value1, value2, "jdsx");
            return (Criteria) this;
        }

        public Criteria andJdsxNotBetween(String value1, String value2) {
            addCriterion("JDSX not between", value1, value2, "jdsx");
            return (Criteria) this;
        }

        public Criteria andGjlcbhIsNull() {
            addCriterion("GJLCBH is null");
            return (Criteria) this;
        }

        public Criteria andGjlcbhIsNotNull() {
            addCriterion("GJLCBH is not null");
            return (Criteria) this;
        }

        public Criteria andGjlcbhEqualTo(String value) {
            addCriterion("GJLCBH =", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhNotEqualTo(String value) {
            addCriterion("GJLCBH <>", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhGreaterThan(String value) {
            addCriterion("GJLCBH >", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhGreaterThanOrEqualTo(String value) {
            addCriterion("GJLCBH >=", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhLessThan(String value) {
            addCriterion("GJLCBH <", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhLessThanOrEqualTo(String value) {
            addCriterion("GJLCBH <=", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhLike(String value) {
            addCriterion("GJLCBH like", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhNotLike(String value) {
            addCriterion("GJLCBH not like", value, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhIn(List<String> values) {
            addCriterion("GJLCBH in", values, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhNotIn(List<String> values) {
            addCriterion("GJLCBH not in", values, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhBetween(String value1, String value2) {
            addCriterion("GJLCBH between", value1, value2, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andGjlcbhNotBetween(String value1, String value2) {
            addCriterion("GJLCBH not between", value1, value2, "gjlcbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhIsNull() {
            addCriterion("FJDBH is null");
            return (Criteria) this;
        }

        public Criteria andFjdbhIsNotNull() {
            addCriterion("FJDBH is not null");
            return (Criteria) this;
        }

        public Criteria andFjdbhEqualTo(String value) {
            addCriterion("FJDBH =", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhNotEqualTo(String value) {
            addCriterion("FJDBH <>", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhGreaterThan(String value) {
            addCriterion("FJDBH >", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhGreaterThanOrEqualTo(String value) {
            addCriterion("FJDBH >=", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhLessThan(String value) {
            addCriterion("FJDBH <", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhLessThanOrEqualTo(String value) {
            addCriterion("FJDBH <=", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhLike(String value) {
            addCriterion("FJDBH like", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhNotLike(String value) {
            addCriterion("FJDBH not like", value, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhIn(List<String> values) {
            addCriterion("FJDBH in", values, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhNotIn(List<String> values) {
            addCriterion("FJDBH not in", values, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhBetween(String value1, String value2) {
            addCriterion("FJDBH between", value1, value2, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andFjdbhNotBetween(String value1, String value2) {
            addCriterion("FJDBH not between", value1, value2, "fjdbh");
            return (Criteria) this;
        }

        public Criteria andJdsxhIsNull() {
            addCriterion("JDSXH is null");
            return (Criteria) this;
        }

        public Criteria andJdsxhIsNotNull() {
            addCriterion("JDSXH is not null");
            return (Criteria) this;
        }

        public Criteria andJdsxhEqualTo(String value) {
            addCriterion("JDSXH =", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhNotEqualTo(String value) {
            addCriterion("JDSXH <>", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhGreaterThan(String value) {
            addCriterion("JDSXH >", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhGreaterThanOrEqualTo(String value) {
            addCriterion("JDSXH >=", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhLessThan(String value) {
            addCriterion("JDSXH <", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhLessThanOrEqualTo(String value) {
            addCriterion("JDSXH <=", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhLike(String value) {
            addCriterion("JDSXH like", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhNotLike(String value) {
            addCriterion("JDSXH not like", value, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhIn(List<String> values) {
            addCriterion("JDSXH in", values, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhNotIn(List<String> values) {
            addCriterion("JDSXH not in", values, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhBetween(String value1, String value2) {
            addCriterion("JDSXH between", value1, value2, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andJdsxhNotBetween(String value1, String value2) {
            addCriterion("JDSXH not between", value1, value2, "jdsxh");
            return (Criteria) this;
        }

        public Criteria andSfyxhtIsNull() {
            addCriterion("SFYXHT is null");
            return (Criteria) this;
        }

        public Criteria andSfyxhtIsNotNull() {
            addCriterion("SFYXHT is not null");
            return (Criteria) this;
        }

        public Criteria andSfyxhtEqualTo(String value) {
            addCriterion("SFYXHT =", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtNotEqualTo(String value) {
            addCriterion("SFYXHT <>", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtGreaterThan(String value) {
            addCriterion("SFYXHT >", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtGreaterThanOrEqualTo(String value) {
            addCriterion("SFYXHT >=", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtLessThan(String value) {
            addCriterion("SFYXHT <", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtLessThanOrEqualTo(String value) {
            addCriterion("SFYXHT <=", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtLike(String value) {
            addCriterion("SFYXHT like", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtNotLike(String value) {
            addCriterion("SFYXHT not like", value, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtIn(List<String> values) {
            addCriterion("SFYXHT in", values, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtNotIn(List<String> values) {
            addCriterion("SFYXHT not in", values, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtBetween(String value1, String value2) {
            addCriterion("SFYXHT between", value1, value2, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyxhtNotBetween(String value1, String value2) {
            addCriterion("SFYXHT not between", value1, value2, "sfyxht");
            return (Criteria) this;
        }

        public Criteria andSfyhkzIsNull() {
            addCriterion("SFYHKZ is null");
            return (Criteria) this;
        }

        public Criteria andSfyhkzIsNotNull() {
            addCriterion("SFYHKZ is not null");
            return (Criteria) this;
        }

        public Criteria andSfyhkzEqualTo(String value) {
            addCriterion("SFYHKZ =", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzNotEqualTo(String value) {
            addCriterion("SFYHKZ <>", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzGreaterThan(String value) {
            addCriterion("SFYHKZ >", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzGreaterThanOrEqualTo(String value) {
            addCriterion("SFYHKZ >=", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzLessThan(String value) {
            addCriterion("SFYHKZ <", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzLessThanOrEqualTo(String value) {
            addCriterion("SFYHKZ <=", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzLike(String value) {
            addCriterion("SFYHKZ like", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzNotLike(String value) {
            addCriterion("SFYHKZ not like", value, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzIn(List<String> values) {
            addCriterion("SFYHKZ in", values, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzNotIn(List<String> values) {
            addCriterion("SFYHKZ not in", values, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzBetween(String value1, String value2) {
            addCriterion("SFYHKZ between", value1, value2, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andSfyhkzNotBetween(String value1, String value2) {
            addCriterion("SFYHKZ not between", value1, value2, "sfyhkz");
            return (Criteria) this;
        }

        public Criteria andJdhtcsIsNull() {
            addCriterion("JDHTCS is null");
            return (Criteria) this;
        }

        public Criteria andJdhtcsIsNotNull() {
            addCriterion("JDHTCS is not null");
            return (Criteria) this;
        }

        public Criteria andJdhtcsEqualTo(Short value) {
            addCriterion("JDHTCS =", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsNotEqualTo(Short value) {
            addCriterion("JDHTCS <>", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsGreaterThan(Short value) {
            addCriterion("JDHTCS >", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsGreaterThanOrEqualTo(Short value) {
            addCriterion("JDHTCS >=", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsLessThan(Short value) {
            addCriterion("JDHTCS <", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsLessThanOrEqualTo(Short value) {
            addCriterion("JDHTCS <=", value, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsIn(List<Short> values) {
            addCriterion("JDHTCS in", values, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsNotIn(List<Short> values) {
            addCriterion("JDHTCS not in", values, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsBetween(Short value1, Short value2) {
            addCriterion("JDHTCS between", value1, value2, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdhtcsNotBetween(Short value1, Short value2) {
            addCriterion("JDHTCS not between", value1, value2, "jdhtcs");
            return (Criteria) this;
        }

        public Criteria andJdtssmIsNull() {
            addCriterion("JDTSSM is null");
            return (Criteria) this;
        }

        public Criteria andJdtssmIsNotNull() {
            addCriterion("JDTSSM is not null");
            return (Criteria) this;
        }

        public Criteria andJdtssmEqualTo(String value) {
            addCriterion("JDTSSM =", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmNotEqualTo(String value) {
            addCriterion("JDTSSM <>", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmGreaterThan(String value) {
            addCriterion("JDTSSM >", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmGreaterThanOrEqualTo(String value) {
            addCriterion("JDTSSM >=", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmLessThan(String value) {
            addCriterion("JDTSSM <", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmLessThanOrEqualTo(String value) {
            addCriterion("JDTSSM <=", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmLike(String value) {
            addCriterion("JDTSSM like", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmNotLike(String value) {
            addCriterion("JDTSSM not like", value, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmIn(List<String> values) {
            addCriterion("JDTSSM in", values, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmNotIn(List<String> values) {
            addCriterion("JDTSSM not in", values, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmBetween(String value1, String value2) {
            addCriterion("JDTSSM between", value1, value2, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdtssmNotBetween(String value1, String value2) {
            addCriterion("JDTSSM not between", value1, value2, "jdtssm");
            return (Criteria) this;
        }

        public Criteria andJdjrsjIsNull() {
            addCriterion("JDJRSJ is null");
            return (Criteria) this;
        }

        public Criteria andJdjrsjIsNotNull() {
            addCriterion("JDJRSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJdjrsjEqualTo(Date value) {
            addCriterion("JDJRSJ =", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjNotEqualTo(Date value) {
            addCriterion("JDJRSJ <>", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjGreaterThan(Date value) {
            addCriterion("JDJRSJ >", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjGreaterThanOrEqualTo(Date value) {
            addCriterion("JDJRSJ >=", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjLessThan(Date value) {
            addCriterion("JDJRSJ <", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjLessThanOrEqualTo(Date value) {
            addCriterion("JDJRSJ <=", value, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjIn(List<Date> values) {
            addCriterion("JDJRSJ in", values, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjNotIn(List<Date> values) {
            addCriterion("JDJRSJ not in", values, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjBetween(Date value1, Date value2) {
            addCriterion("JDJRSJ between", value1, value2, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdjrsjNotBetween(Date value1, Date value2) {
            addCriterion("JDJRSJ not between", value1, value2, "jdjrsj");
            return (Criteria) this;
        }

        public Criteria andJdlksjIsNull() {
            addCriterion("JDLKSJ is null");
            return (Criteria) this;
        }

        public Criteria andJdlksjIsNotNull() {
            addCriterion("JDLKSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJdlksjEqualTo(Date value) {
            addCriterion("JDLKSJ =", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjNotEqualTo(Date value) {
            addCriterion("JDLKSJ <>", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjGreaterThan(Date value) {
            addCriterion("JDLKSJ >", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjGreaterThanOrEqualTo(Date value) {
            addCriterion("JDLKSJ >=", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjLessThan(Date value) {
            addCriterion("JDLKSJ <", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjLessThanOrEqualTo(Date value) {
            addCriterion("JDLKSJ <=", value, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjIn(List<Date> values) {
            addCriterion("JDLKSJ in", values, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjNotIn(List<Date> values) {
            addCriterion("JDLKSJ not in", values, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjBetween(Date value1, Date value2) {
            addCriterion("JDLKSJ between", value1, value2, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdlksjNotBetween(Date value1, Date value2) {
            addCriterion("JDLKSJ not between", value1, value2, "jdlksj");
            return (Criteria) this;
        }

        public Criteria andJdzxzghIsNull() {
            addCriterion("JDZXZGH is null");
            return (Criteria) this;
        }

        public Criteria andJdzxzghIsNotNull() {
            addCriterion("JDZXZGH is not null");
            return (Criteria) this;
        }

        public Criteria andJdzxzghEqualTo(String value) {
            addCriterion("JDZXZGH =", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghNotEqualTo(String value) {
            addCriterion("JDZXZGH <>", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghGreaterThan(String value) {
            addCriterion("JDZXZGH >", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghGreaterThanOrEqualTo(String value) {
            addCriterion("JDZXZGH >=", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghLessThan(String value) {
            addCriterion("JDZXZGH <", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghLessThanOrEqualTo(String value) {
            addCriterion("JDZXZGH <=", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghLike(String value) {
            addCriterion("JDZXZGH like", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghNotLike(String value) {
            addCriterion("JDZXZGH not like", value, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghIn(List<String> values) {
            addCriterion("JDZXZGH in", values, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghNotIn(List<String> values) {
            addCriterion("JDZXZGH not in", values, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghBetween(String value1, String value2) {
            addCriterion("JDZXZGH between", value1, value2, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzghNotBetween(String value1, String value2) {
            addCriterion("JDZXZGH not between", value1, value2, "jdzxzgh");
            return (Criteria) this;
        }

        public Criteria andJdzxzIsNull() {
            addCriterion("JDZXZ is null");
            return (Criteria) this;
        }

        public Criteria andJdzxzIsNotNull() {
            addCriterion("JDZXZ is not null");
            return (Criteria) this;
        }

        public Criteria andJdzxzEqualTo(String value) {
            addCriterion("JDZXZ =", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzNotEqualTo(String value) {
            addCriterion("JDZXZ <>", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzGreaterThan(String value) {
            addCriterion("JDZXZ >", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzGreaterThanOrEqualTo(String value) {
            addCriterion("JDZXZ >=", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzLessThan(String value) {
            addCriterion("JDZXZ <", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzLessThanOrEqualTo(String value) {
            addCriterion("JDZXZ <=", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzLike(String value) {
            addCriterion("JDZXZ like", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzNotLike(String value) {
            addCriterion("JDZXZ not like", value, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzIn(List<String> values) {
            addCriterion("JDZXZ in", values, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzNotIn(List<String> values) {
            addCriterion("JDZXZ not in", values, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzBetween(String value1, String value2) {
            addCriterion("JDZXZ between", value1, value2, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxzNotBetween(String value1, String value2) {
            addCriterion("JDZXZ not between", value1, value2, "jdzxz");
            return (Criteria) this;
        }

        public Criteria andJdzxztIsNull() {
            addCriterion("JDZXZT is null");
            return (Criteria) this;
        }

        public Criteria andJdzxztIsNotNull() {
            addCriterion("JDZXZT is not null");
            return (Criteria) this;
        }

        public Criteria andJdzxztEqualTo(String value) {
            addCriterion("JDZXZT =", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztNotEqualTo(String value) {
            addCriterion("JDZXZT <>", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztGreaterThan(String value) {
            addCriterion("JDZXZT >", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztGreaterThanOrEqualTo(String value) {
            addCriterion("JDZXZT >=", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztLessThan(String value) {
            addCriterion("JDZXZT <", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztLessThanOrEqualTo(String value) {
            addCriterion("JDZXZT <=", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztLike(String value) {
            addCriterion("JDZXZT like", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztNotLike(String value) {
            addCriterion("JDZXZT not like", value, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztIn(List<String> values) {
            addCriterion("JDZXZT in", values, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztNotIn(List<String> values) {
            addCriterion("JDZXZT not in", values, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztBetween(String value1, String value2) {
            addCriterion("JDZXZT between", value1, value2, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdzxztNotBetween(String value1, String value2) {
            addCriterion("JDZXZT not between", value1, value2, "jdzxzt");
            return (Criteria) this;
        }

        public Criteria andJdjryyIsNull() {
            addCriterion("JDJRYY is null");
            return (Criteria) this;
        }

        public Criteria andJdjryyIsNotNull() {
            addCriterion("JDJRYY is not null");
            return (Criteria) this;
        }

        public Criteria andJdjryyEqualTo(String value) {
            addCriterion("JDJRYY =", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyNotEqualTo(String value) {
            addCriterion("JDJRYY <>", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyGreaterThan(String value) {
            addCriterion("JDJRYY >", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyGreaterThanOrEqualTo(String value) {
            addCriterion("JDJRYY >=", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyLessThan(String value) {
            addCriterion("JDJRYY <", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyLessThanOrEqualTo(String value) {
            addCriterion("JDJRYY <=", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyLike(String value) {
            addCriterion("JDJRYY like", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyNotLike(String value) {
            addCriterion("JDJRYY not like", value, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyIn(List<String> values) {
            addCriterion("JDJRYY in", values, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyNotIn(List<String> values) {
            addCriterion("JDJRYY not in", values, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyBetween(String value1, String value2) {
            addCriterion("JDJRYY between", value1, value2, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andJdjryyNotBetween(String value1, String value2) {
            addCriterion("JDJRYY not between", value1, value2, "jdjryy");
            return (Criteria) this;
        }

        public Criteria andLcmcIsNull() {
            addCriterion("LCMC is null");
            return (Criteria) this;
        }

        public Criteria andLcmcIsNotNull() {
            addCriterion("LCMC is not null");
            return (Criteria) this;
        }

        public Criteria andLcmcEqualTo(String value) {
            addCriterion("LCMC =", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcNotEqualTo(String value) {
            addCriterion("LCMC <>", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcGreaterThan(String value) {
            addCriterion("LCMC >", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcGreaterThanOrEqualTo(String value) {
            addCriterion("LCMC >=", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcLessThan(String value) {
            addCriterion("LCMC <", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcLessThanOrEqualTo(String value) {
            addCriterion("LCMC <=", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcLike(String value) {
            addCriterion("LCMC like", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcNotLike(String value) {
            addCriterion("LCMC not like", value, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcIn(List<String> values) {
            addCriterion("LCMC in", values, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcNotIn(List<String> values) {
            addCriterion("LCMC not in", values, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcBetween(String value1, String value2) {
            addCriterion("LCMC between", value1, value2, "lcmc");
            return (Criteria) this;
        }

        public Criteria andLcmcNotBetween(String value1, String value2) {
            addCriterion("LCMC not between", value1, value2, "lcmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmIsNull() {
            addCriterion("JDZXZDWBM is null");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmIsNotNull() {
            addCriterion("JDZXZDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmEqualTo(String value) {
            addCriterion("JDZXZDWBM =", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmNotEqualTo(String value) {
            addCriterion("JDZXZDWBM <>", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmGreaterThan(String value) {
            addCriterion("JDZXZDWBM >", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("JDZXZDWBM >=", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmLessThan(String value) {
            addCriterion("JDZXZDWBM <", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmLessThanOrEqualTo(String value) {
            addCriterion("JDZXZDWBM <=", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmLike(String value) {
            addCriterion("JDZXZDWBM like", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmNotLike(String value) {
            addCriterion("JDZXZDWBM not like", value, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmIn(List<String> values) {
            addCriterion("JDZXZDWBM in", values, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmNotIn(List<String> values) {
            addCriterion("JDZXZDWBM not in", values, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmBetween(String value1, String value2) {
            addCriterion("JDZXZDWBM between", value1, value2, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwbmNotBetween(String value1, String value2) {
            addCriterion("JDZXZDWBM not between", value1, value2, "jdzxzdwbm");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcIsNull() {
            addCriterion("JDZXZDWMC is null");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcIsNotNull() {
            addCriterion("JDZXZDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcEqualTo(String value) {
            addCriterion("JDZXZDWMC =", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcNotEqualTo(String value) {
            addCriterion("JDZXZDWMC <>", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcGreaterThan(String value) {
            addCriterion("JDZXZDWMC >", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("JDZXZDWMC >=", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcLessThan(String value) {
            addCriterion("JDZXZDWMC <", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcLessThanOrEqualTo(String value) {
            addCriterion("JDZXZDWMC <=", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcLike(String value) {
            addCriterion("JDZXZDWMC like", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcNotLike(String value) {
            addCriterion("JDZXZDWMC not like", value, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcIn(List<String> values) {
            addCriterion("JDZXZDWMC in", values, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcNotIn(List<String> values) {
            addCriterion("JDZXZDWMC not in", values, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcBetween(String value1, String value2) {
            addCriterion("JDZXZDWMC between", value1, value2, "jdzxzdwmc");
            return (Criteria) this;
        }

        public Criteria andJdzxzdwmcNotBetween(String value1, String value2) {
            addCriterion("JDZXZDWMC not between", value1, value2, "jdzxzdwmc");
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