package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YX_PC_HDExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YX_PC_HDExample() {
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

        public Criteria andPchdmcIsNull() {
            addCriterion("PCHDMC is null");
            return (Criteria) this;
        }

        public Criteria andPchdmcIsNotNull() {
            addCriterion("PCHDMC is not null");
            return (Criteria) this;
        }

        public Criteria andPchdmcEqualTo(String value) {
            addCriterion("PCHDMC =", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcNotEqualTo(String value) {
            addCriterion("PCHDMC <>", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcGreaterThan(String value) {
            addCriterion("PCHDMC >", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCHDMC >=", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcLessThan(String value) {
            addCriterion("PCHDMC <", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcLessThanOrEqualTo(String value) {
            addCriterion("PCHDMC <=", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcLike(String value) {
            addCriterion("PCHDMC like", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcNotLike(String value) {
            addCriterion("PCHDMC not like", value, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcIn(List<String> values) {
            addCriterion("PCHDMC in", values, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcNotIn(List<String> values) {
            addCriterion("PCHDMC not in", values, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcBetween(String value1, String value2) {
            addCriterion("PCHDMC between", value1, value2, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPchdmcNotBetween(String value1, String value2) {
            addCriterion("PCHDMC not between", value1, value2, "pchdmc");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIsNull() {
            addCriterion("PCDWBM is null");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIsNotNull() {
            addCriterion("PCDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcdwbmEqualTo(String value) {
            addCriterion("PCDWBM =", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotEqualTo(String value) {
            addCriterion("PCDWBM <>", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmGreaterThan(String value) {
            addCriterion("PCDWBM >", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCDWBM >=", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLessThan(String value) {
            addCriterion("PCDWBM <", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLessThanOrEqualTo(String value) {
            addCriterion("PCDWBM <=", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLike(String value) {
            addCriterion("PCDWBM like", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotLike(String value) {
            addCriterion("PCDWBM not like", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIn(List<String> values) {
            addCriterion("PCDWBM in", values, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotIn(List<String> values) {
            addCriterion("PCDWBM not in", values, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmBetween(String value1, String value2) {
            addCriterion("PCDWBM between", value1, value2, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotBetween(String value1, String value2) {
            addCriterion("PCDWBM not between", value1, value2, "pcdwbm");
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

        public Criteria andSfssIsNull() {
            addCriterion("SFSS is null");
            return (Criteria) this;
        }

        public Criteria andSfssIsNotNull() {
            addCriterion("SFSS is not null");
            return (Criteria) this;
        }

        public Criteria andSfssEqualTo(String value) {
            addCriterion("SFSS =", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssNotEqualTo(String value) {
            addCriterion("SFSS <>", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssGreaterThan(String value) {
            addCriterion("SFSS >", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssGreaterThanOrEqualTo(String value) {
            addCriterion("SFSS >=", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssLessThan(String value) {
            addCriterion("SFSS <", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssLessThanOrEqualTo(String value) {
            addCriterion("SFSS <=", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssLike(String value) {
            addCriterion("SFSS like", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssNotLike(String value) {
            addCriterion("SFSS not like", value, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssIn(List<String> values) {
            addCriterion("SFSS in", values, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssNotIn(List<String> values) {
            addCriterion("SFSS not in", values, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssBetween(String value1, String value2) {
            addCriterion("SFSS between", value1, value2, "sfss");
            return (Criteria) this;
        }

        public Criteria andSfssNotBetween(String value1, String value2) {
            addCriterion("SFSS not between", value1, value2, "sfss");
            return (Criteria) this;
        }

        public Criteria andSffyIsNull() {
            addCriterion("SFFY is null");
            return (Criteria) this;
        }

        public Criteria andSffyIsNotNull() {
            addCriterion("SFFY is not null");
            return (Criteria) this;
        }

        public Criteria andSffyEqualTo(String value) {
            addCriterion("SFFY =", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotEqualTo(String value) {
            addCriterion("SFFY <>", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyGreaterThan(String value) {
            addCriterion("SFFY >", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyGreaterThanOrEqualTo(String value) {
            addCriterion("SFFY >=", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLessThan(String value) {
            addCriterion("SFFY <", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLessThanOrEqualTo(String value) {
            addCriterion("SFFY <=", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyLike(String value) {
            addCriterion("SFFY like", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotLike(String value) {
            addCriterion("SFFY not like", value, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyIn(List<String> values) {
            addCriterion("SFFY in", values, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotIn(List<String> values) {
            addCriterion("SFFY not in", values, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyBetween(String value1, String value2) {
            addCriterion("SFFY between", value1, value2, "sffy");
            return (Criteria) this;
        }

        public Criteria andSffyNotBetween(String value1, String value2) {
            addCriterion("SFFY not between", value1, value2, "sffy");
            return (Criteria) this;
        }

        public Criteria andPckssjIsNull() {
            addCriterion("PCKSSJ is null");
            return (Criteria) this;
        }

        public Criteria andPckssjIsNotNull() {
            addCriterion("PCKSSJ is not null");
            return (Criteria) this;
        }

        public Criteria andPckssjEqualTo(Date value) {
            addCriterion("PCKSSJ =", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjNotEqualTo(Date value) {
            addCriterion("PCKSSJ <>", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjGreaterThan(Date value) {
            addCriterion("PCKSSJ >", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjGreaterThanOrEqualTo(Date value) {
            addCriterion("PCKSSJ >=", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjLessThan(Date value) {
            addCriterion("PCKSSJ <", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjLessThanOrEqualTo(Date value) {
            addCriterion("PCKSSJ <=", value, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjIn(List<Date> values) {
            addCriterion("PCKSSJ in", values, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjNotIn(List<Date> values) {
            addCriterion("PCKSSJ not in", values, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjBetween(Date value1, Date value2) {
            addCriterion("PCKSSJ between", value1, value2, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPckssjNotBetween(Date value1, Date value2) {
            addCriterion("PCKSSJ not between", value1, value2, "pckssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjIsNull() {
            addCriterion("PCJSSJ is null");
            return (Criteria) this;
        }

        public Criteria andPcjssjIsNotNull() {
            addCriterion("PCJSSJ is not null");
            return (Criteria) this;
        }

        public Criteria andPcjssjEqualTo(Date value) {
            addCriterion("PCJSSJ =", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjNotEqualTo(Date value) {
            addCriterion("PCJSSJ <>", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjGreaterThan(Date value) {
            addCriterion("PCJSSJ >", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjGreaterThanOrEqualTo(Date value) {
            addCriterion("PCJSSJ >=", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjLessThan(Date value) {
            addCriterion("PCJSSJ <", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjLessThanOrEqualTo(Date value) {
            addCriterion("PCJSSJ <=", value, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjIn(List<Date> values) {
            addCriterion("PCJSSJ in", values, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjNotIn(List<Date> values) {
            addCriterion("PCJSSJ not in", values, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjBetween(Date value1, Date value2) {
            addCriterion("PCJSSJ between", value1, value2, "pcjssj");
            return (Criteria) this;
        }

        public Criteria andPcjssjNotBetween(Date value1, Date value2) {
            addCriterion("PCJSSJ not between", value1, value2, "pcjssj");
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

        public Criteria andXhEqualTo(Short value) {
            addCriterion("XH =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(Short value) {
            addCriterion("XH <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(Short value) {
            addCriterion("XH >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(Short value) {
            addCriterion("XH >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(Short value) {
            addCriterion("XH <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(Short value) {
            addCriterion("XH <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<Short> values) {
            addCriterion("XH in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<Short> values) {
            addCriterion("XH not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(Short value1, Short value2) {
            addCriterion("XH between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(Short value1, Short value2) {
            addCriterion("XH not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmIsNull() {
            addCriterion("CJRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmIsNotNull() {
            addCriterion("CJRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmEqualTo(String value) {
            addCriterion("CJRDWBM =", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmNotEqualTo(String value) {
            addCriterion("CJRDWBM <>", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmGreaterThan(String value) {
            addCriterion("CJRDWBM >", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("CJRDWBM >=", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmLessThan(String value) {
            addCriterion("CJRDWBM <", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmLessThanOrEqualTo(String value) {
            addCriterion("CJRDWBM <=", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmLike(String value) {
            addCriterion("CJRDWBM like", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmNotLike(String value) {
            addCriterion("CJRDWBM not like", value, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmIn(List<String> values) {
            addCriterion("CJRDWBM in", values, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmNotIn(List<String> values) {
            addCriterion("CJRDWBM not in", values, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmBetween(String value1, String value2) {
            addCriterion("CJRDWBM between", value1, value2, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrdwbmNotBetween(String value1, String value2) {
            addCriterion("CJRDWBM not between", value1, value2, "cjrdwbm");
            return (Criteria) this;
        }

        public Criteria andCjrghIsNull() {
            addCriterion("CJRGH is null");
            return (Criteria) this;
        }

        public Criteria andCjrghIsNotNull() {
            addCriterion("CJRGH is not null");
            return (Criteria) this;
        }

        public Criteria andCjrghEqualTo(String value) {
            addCriterion("CJRGH =", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghNotEqualTo(String value) {
            addCriterion("CJRGH <>", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghGreaterThan(String value) {
            addCriterion("CJRGH >", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghGreaterThanOrEqualTo(String value) {
            addCriterion("CJRGH >=", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghLessThan(String value) {
            addCriterion("CJRGH <", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghLessThanOrEqualTo(String value) {
            addCriterion("CJRGH <=", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghLike(String value) {
            addCriterion("CJRGH like", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghNotLike(String value) {
            addCriterion("CJRGH not like", value, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghIn(List<String> values) {
            addCriterion("CJRGH in", values, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghNotIn(List<String> values) {
            addCriterion("CJRGH not in", values, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghBetween(String value1, String value2) {
            addCriterion("CJRGH between", value1, value2, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrghNotBetween(String value1, String value2) {
            addCriterion("CJRGH not between", value1, value2, "cjrgh");
            return (Criteria) this;
        }

        public Criteria andCjrmcIsNull() {
            addCriterion("CJRMC is null");
            return (Criteria) this;
        }

        public Criteria andCjrmcIsNotNull() {
            addCriterion("CJRMC is not null");
            return (Criteria) this;
        }

        public Criteria andCjrmcEqualTo(String value) {
            addCriterion("CJRMC =", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcNotEqualTo(String value) {
            addCriterion("CJRMC <>", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcGreaterThan(String value) {
            addCriterion("CJRMC >", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcGreaterThanOrEqualTo(String value) {
            addCriterion("CJRMC >=", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcLessThan(String value) {
            addCriterion("CJRMC <", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcLessThanOrEqualTo(String value) {
            addCriterion("CJRMC <=", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcLike(String value) {
            addCriterion("CJRMC like", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcNotLike(String value) {
            addCriterion("CJRMC not like", value, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcIn(List<String> values) {
            addCriterion("CJRMC in", values, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcNotIn(List<String> values) {
            addCriterion("CJRMC not in", values, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcBetween(String value1, String value2) {
            addCriterion("CJRMC between", value1, value2, "cjrmc");
            return (Criteria) this;
        }

        public Criteria andCjrmcNotBetween(String value1, String value2) {
            addCriterion("CJRMC not between", value1, value2, "cjrmc");
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

        public Criteria andSfqdIsNull() {
            addCriterion("SFQD is null");
            return (Criteria) this;
        }

        public Criteria andSfqdIsNotNull() {
            addCriterion("SFQD is not null");
            return (Criteria) this;
        }

        public Criteria andSfqdEqualTo(String value) {
            addCriterion("SFQD =", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdNotEqualTo(String value) {
            addCriterion("SFQD <>", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdGreaterThan(String value) {
            addCriterion("SFQD >", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdGreaterThanOrEqualTo(String value) {
            addCriterion("SFQD >=", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdLessThan(String value) {
            addCriterion("SFQD <", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdLessThanOrEqualTo(String value) {
            addCriterion("SFQD <=", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdLike(String value) {
            addCriterion("SFQD like", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdNotLike(String value) {
            addCriterion("SFQD not like", value, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdIn(List<String> values) {
            addCriterion("SFQD in", values, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdNotIn(List<String> values) {
            addCriterion("SFQD not in", values, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdBetween(String value1, String value2) {
            addCriterion("SFQD between", value1, value2, "sfqd");
            return (Criteria) this;
        }

        public Criteria andSfqdNotBetween(String value1, String value2) {
            addCriterion("SFQD not between", value1, value2, "sfqd");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmIsNull() {
            addCriterion("QDR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmIsNotNull() {
            addCriterion("QDR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmEqualTo(String value) {
            addCriterion("QDR_DWBM =", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmNotEqualTo(String value) {
            addCriterion("QDR_DWBM <>", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmGreaterThan(String value) {
            addCriterion("QDR_DWBM >", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("QDR_DWBM >=", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmLessThan(String value) {
            addCriterion("QDR_DWBM <", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmLessThanOrEqualTo(String value) {
            addCriterion("QDR_DWBM <=", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmLike(String value) {
            addCriterion("QDR_DWBM like", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmNotLike(String value) {
            addCriterion("QDR_DWBM not like", value, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmIn(List<String> values) {
            addCriterion("QDR_DWBM in", values, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmNotIn(List<String> values) {
            addCriterion("QDR_DWBM not in", values, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmBetween(String value1, String value2) {
            addCriterion("QDR_DWBM between", value1, value2, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrDwbmNotBetween(String value1, String value2) {
            addCriterion("QDR_DWBM not between", value1, value2, "qdrDwbm");
            return (Criteria) this;
        }

        public Criteria andQdrGhIsNull() {
            addCriterion("QDR_GH is null");
            return (Criteria) this;
        }

        public Criteria andQdrGhIsNotNull() {
            addCriterion("QDR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andQdrGhEqualTo(String value) {
            addCriterion("QDR_GH =", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhNotEqualTo(String value) {
            addCriterion("QDR_GH <>", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhGreaterThan(String value) {
            addCriterion("QDR_GH >", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhGreaterThanOrEqualTo(String value) {
            addCriterion("QDR_GH >=", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhLessThan(String value) {
            addCriterion("QDR_GH <", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhLessThanOrEqualTo(String value) {
            addCriterion("QDR_GH <=", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhLike(String value) {
            addCriterion("QDR_GH like", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhNotLike(String value) {
            addCriterion("QDR_GH not like", value, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhIn(List<String> values) {
            addCriterion("QDR_GH in", values, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhNotIn(List<String> values) {
            addCriterion("QDR_GH not in", values, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhBetween(String value1, String value2) {
            addCriterion("QDR_GH between", value1, value2, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrGhNotBetween(String value1, String value2) {
            addCriterion("QDR_GH not between", value1, value2, "qdrGh");
            return (Criteria) this;
        }

        public Criteria andQdrMcIsNull() {
            addCriterion("QDR_MC is null");
            return (Criteria) this;
        }

        public Criteria andQdrMcIsNotNull() {
            addCriterion("QDR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andQdrMcEqualTo(String value) {
            addCriterion("QDR_MC =", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcNotEqualTo(String value) {
            addCriterion("QDR_MC <>", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcGreaterThan(String value) {
            addCriterion("QDR_MC >", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcGreaterThanOrEqualTo(String value) {
            addCriterion("QDR_MC >=", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcLessThan(String value) {
            addCriterion("QDR_MC <", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcLessThanOrEqualTo(String value) {
            addCriterion("QDR_MC <=", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcLike(String value) {
            addCriterion("QDR_MC like", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcNotLike(String value) {
            addCriterion("QDR_MC not like", value, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcIn(List<String> values) {
            addCriterion("QDR_MC in", values, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcNotIn(List<String> values) {
            addCriterion("QDR_MC not in", values, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcBetween(String value1, String value2) {
            addCriterion("QDR_MC between", value1, value2, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdrMcNotBetween(String value1, String value2) {
            addCriterion("QDR_MC not between", value1, value2, "qdrMc");
            return (Criteria) this;
        }

        public Criteria andQdSjIsNull() {
            addCriterion("QD_SJ is null");
            return (Criteria) this;
        }

        public Criteria andQdSjIsNotNull() {
            addCriterion("QD_SJ is not null");
            return (Criteria) this;
        }

        public Criteria andQdSjEqualTo(Date value) {
            addCriterion("QD_SJ =", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjNotEqualTo(Date value) {
            addCriterion("QD_SJ <>", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjGreaterThan(Date value) {
            addCriterion("QD_SJ >", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjGreaterThanOrEqualTo(Date value) {
            addCriterion("QD_SJ >=", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjLessThan(Date value) {
            addCriterion("QD_SJ <", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjLessThanOrEqualTo(Date value) {
            addCriterion("QD_SJ <=", value, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjIn(List<Date> values) {
            addCriterion("QD_SJ in", values, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjNotIn(List<Date> values) {
            addCriterion("QD_SJ not in", values, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjBetween(Date value1, Date value2) {
            addCriterion("QD_SJ between", value1, value2, "qdSj");
            return (Criteria) this;
        }

        public Criteria andQdSjNotBetween(Date value1, Date value2) {
            addCriterion("QD_SJ not between", value1, value2, "qdSj");
            return (Criteria) this;
        }

        public Criteria andSfjsIsNull() {
            addCriterion("SFJS is null");
            return (Criteria) this;
        }

        public Criteria andSfjsIsNotNull() {
            addCriterion("SFJS is not null");
            return (Criteria) this;
        }

        public Criteria andSfjsEqualTo(String value) {
            addCriterion("SFJS =", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsNotEqualTo(String value) {
            addCriterion("SFJS <>", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsGreaterThan(String value) {
            addCriterion("SFJS >", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsGreaterThanOrEqualTo(String value) {
            addCriterion("SFJS >=", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsLessThan(String value) {
            addCriterion("SFJS <", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsLessThanOrEqualTo(String value) {
            addCriterion("SFJS <=", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsLike(String value) {
            addCriterion("SFJS like", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsNotLike(String value) {
            addCriterion("SFJS not like", value, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsIn(List<String> values) {
            addCriterion("SFJS in", values, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsNotIn(List<String> values) {
            addCriterion("SFJS not in", values, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsBetween(String value1, String value2) {
            addCriterion("SFJS between", value1, value2, "sfjs");
            return (Criteria) this;
        }

        public Criteria andSfjsNotBetween(String value1, String value2) {
            addCriterion("SFJS not between", value1, value2, "sfjs");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmIsNull() {
            addCriterion("JSR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmIsNotNull() {
            addCriterion("JSR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmEqualTo(String value) {
            addCriterion("JSR_DWBM =", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmNotEqualTo(String value) {
            addCriterion("JSR_DWBM <>", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmGreaterThan(String value) {
            addCriterion("JSR_DWBM >", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("JSR_DWBM >=", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmLessThan(String value) {
            addCriterion("JSR_DWBM <", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmLessThanOrEqualTo(String value) {
            addCriterion("JSR_DWBM <=", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmLike(String value) {
            addCriterion("JSR_DWBM like", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmNotLike(String value) {
            addCriterion("JSR_DWBM not like", value, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmIn(List<String> values) {
            addCriterion("JSR_DWBM in", values, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmNotIn(List<String> values) {
            addCriterion("JSR_DWBM not in", values, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmBetween(String value1, String value2) {
            addCriterion("JSR_DWBM between", value1, value2, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrDwbmNotBetween(String value1, String value2) {
            addCriterion("JSR_DWBM not between", value1, value2, "jsrDwbm");
            return (Criteria) this;
        }

        public Criteria andJsrGhIsNull() {
            addCriterion("JSR_GH is null");
            return (Criteria) this;
        }

        public Criteria andJsrGhIsNotNull() {
            addCriterion("JSR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andJsrGhEqualTo(String value) {
            addCriterion("JSR_GH =", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhNotEqualTo(String value) {
            addCriterion("JSR_GH <>", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhGreaterThan(String value) {
            addCriterion("JSR_GH >", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhGreaterThanOrEqualTo(String value) {
            addCriterion("JSR_GH >=", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhLessThan(String value) {
            addCriterion("JSR_GH <", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhLessThanOrEqualTo(String value) {
            addCriterion("JSR_GH <=", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhLike(String value) {
            addCriterion("JSR_GH like", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhNotLike(String value) {
            addCriterion("JSR_GH not like", value, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhIn(List<String> values) {
            addCriterion("JSR_GH in", values, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhNotIn(List<String> values) {
            addCriterion("JSR_GH not in", values, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhBetween(String value1, String value2) {
            addCriterion("JSR_GH between", value1, value2, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrGhNotBetween(String value1, String value2) {
            addCriterion("JSR_GH not between", value1, value2, "jsrGh");
            return (Criteria) this;
        }

        public Criteria andJsrMcIsNull() {
            addCriterion("JSR_MC is null");
            return (Criteria) this;
        }

        public Criteria andJsrMcIsNotNull() {
            addCriterion("JSR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andJsrMcEqualTo(String value) {
            addCriterion("JSR_MC =", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcNotEqualTo(String value) {
            addCriterion("JSR_MC <>", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcGreaterThan(String value) {
            addCriterion("JSR_MC >", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcGreaterThanOrEqualTo(String value) {
            addCriterion("JSR_MC >=", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcLessThan(String value) {
            addCriterion("JSR_MC <", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcLessThanOrEqualTo(String value) {
            addCriterion("JSR_MC <=", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcLike(String value) {
            addCriterion("JSR_MC like", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcNotLike(String value) {
            addCriterion("JSR_MC not like", value, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcIn(List<String> values) {
            addCriterion("JSR_MC in", values, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcNotIn(List<String> values) {
            addCriterion("JSR_MC not in", values, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcBetween(String value1, String value2) {
            addCriterion("JSR_MC between", value1, value2, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsrMcNotBetween(String value1, String value2) {
            addCriterion("JSR_MC not between", value1, value2, "jsrMc");
            return (Criteria) this;
        }

        public Criteria andJsSjIsNull() {
            addCriterion("JS_SJ is null");
            return (Criteria) this;
        }

        public Criteria andJsSjIsNotNull() {
            addCriterion("JS_SJ is not null");
            return (Criteria) this;
        }

        public Criteria andJsSjEqualTo(Date value) {
            addCriterion("JS_SJ =", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjNotEqualTo(Date value) {
            addCriterion("JS_SJ <>", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjGreaterThan(Date value) {
            addCriterion("JS_SJ >", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjGreaterThanOrEqualTo(Date value) {
            addCriterion("JS_SJ >=", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjLessThan(Date value) {
            addCriterion("JS_SJ <", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjLessThanOrEqualTo(Date value) {
            addCriterion("JS_SJ <=", value, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjIn(List<Date> values) {
            addCriterion("JS_SJ in", values, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjNotIn(List<Date> values) {
            addCriterion("JS_SJ not in", values, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjBetween(Date value1, Date value2) {
            addCriterion("JS_SJ between", value1, value2, "jsSj");
            return (Criteria) this;
        }

        public Criteria andJsSjNotBetween(Date value1, Date value2) {
            addCriterion("JS_SJ not between", value1, value2, "jsSj");
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

        public Criteria andSxgzjIsNull() {
            addCriterion("SXGZJ is null");
            return (Criteria) this;
        }

        public Criteria andSxgzjIsNotNull() {
            addCriterion("SXGZJ is not null");
            return (Criteria) this;
        }

        public Criteria andSxgzjEqualTo(String value) {
            addCriterion("SXGZJ =", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjNotEqualTo(String value) {
            addCriterion("SXGZJ <>", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjGreaterThan(String value) {
            addCriterion("SXGZJ >", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjGreaterThanOrEqualTo(String value) {
            addCriterion("SXGZJ >=", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjLessThan(String value) {
            addCriterion("SXGZJ <", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjLessThanOrEqualTo(String value) {
            addCriterion("SXGZJ <=", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjLike(String value) {
            addCriterion("SXGZJ like", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjNotLike(String value) {
            addCriterion("SXGZJ not like", value, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjIn(List<String> values) {
            addCriterion("SXGZJ in", values, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjNotIn(List<String> values) {
            addCriterion("SXGZJ not in", values, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjBetween(String value1, String value2) {
            addCriterion("SXGZJ between", value1, value2, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSxgzjNotBetween(String value1, String value2) {
            addCriterion("SXGZJ not between", value1, value2, "sxgzj");
            return (Criteria) this;
        }

        public Criteria andSfhdxsEqualTo(String value) {
            addCriterion("SFHDXS =", value, "sfhdxs");
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