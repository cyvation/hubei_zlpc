package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class XtPcLbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtPcLbExample() {
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

        public Criteria andPcflmcIsNull() {
            addCriterion("PCFLMC is null");
            return (Criteria) this;
        }

        public Criteria andPcflmcIsNotNull() {
            addCriterion("PCFLMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcflmcEqualTo(String value) {
            addCriterion("PCFLMC =", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcNotEqualTo(String value) {
            addCriterion("PCFLMC <>", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcGreaterThan(String value) {
            addCriterion("PCFLMC >", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCFLMC >=", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcLessThan(String value) {
            addCriterion("PCFLMC <", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcLessThanOrEqualTo(String value) {
            addCriterion("PCFLMC <=", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcLike(String value) {
            addCriterion("PCFLMC like", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcNotLike(String value) {
            addCriterion("PCFLMC not like", value, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcIn(List<String> values) {
            addCriterion("PCFLMC in", values, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcNotIn(List<String> values) {
            addCriterion("PCFLMC not in", values, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcBetween(String value1, String value2) {
            addCriterion("PCFLMC between", value1, value2, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andPcflmcNotBetween(String value1, String value2) {
            addCriterion("PCFLMC not between", value1, value2, "pcflmc");
            return (Criteria) this;
        }

        public Criteria andLcmbbmIsNull() {
            addCriterion("LCMBBM is null");
            return (Criteria) this;
        }

        public Criteria andLcmbbmIsNotNull() {
            addCriterion("LCMBBM is not null");
            return (Criteria) this;
        }

        public Criteria andLcmbbmEqualTo(String value) {
            addCriterion("LCMBBM =", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmNotEqualTo(String value) {
            addCriterion("LCMBBM <>", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmGreaterThan(String value) {
            addCriterion("LCMBBM >", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmGreaterThanOrEqualTo(String value) {
            addCriterion("LCMBBM >=", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmLessThan(String value) {
            addCriterion("LCMBBM <", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmLessThanOrEqualTo(String value) {
            addCriterion("LCMBBM <=", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmLike(String value) {
            addCriterion("LCMBBM like", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmNotLike(String value) {
            addCriterion("LCMBBM not like", value, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmIn(List<String> values) {
            addCriterion("LCMBBM in", values, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmNotIn(List<String> values) {
            addCriterion("LCMBBM not in", values, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmBetween(String value1, String value2) {
            addCriterion("LCMBBM between", value1, value2, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andLcmbbmNotBetween(String value1, String value2) {
            addCriterion("LCMBBM not between", value1, value2, "lcmbbm");
            return (Criteria) this;
        }

        public Criteria andSfdmbIsNull() {
            addCriterion("SFDMB is null");
            return (Criteria) this;
        }

        public Criteria andSfdmbIsNotNull() {
            addCriterion("SFDMB is not null");
            return (Criteria) this;
        }

        public Criteria andSfdmbEqualTo(String value) {
            addCriterion("SFDMB =", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbNotEqualTo(String value) {
            addCriterion("SFDMB <>", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbGreaterThan(String value) {
            addCriterion("SFDMB >", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbGreaterThanOrEqualTo(String value) {
            addCriterion("SFDMB >=", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbLessThan(String value) {
            addCriterion("SFDMB <", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbLessThanOrEqualTo(String value) {
            addCriterion("SFDMB <=", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbLike(String value) {
            addCriterion("SFDMB like", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbNotLike(String value) {
            addCriterion("SFDMB not like", value, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbIn(List<String> values) {
            addCriterion("SFDMB in", values, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbNotIn(List<String> values) {
            addCriterion("SFDMB not in", values, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbBetween(String value1, String value2) {
            addCriterion("SFDMB between", value1, value2, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfdmbNotBetween(String value1, String value2) {
            addCriterion("SFDMB not between", value1, value2, "sfdmb");
            return (Criteria) this;
        }

        public Criteria andSfpcfpIsNull() {
            addCriterion("SFPCFP is null");
            return (Criteria) this;
        }

        public Criteria andSfpcfpIsNotNull() {
            addCriterion("SFPCFP is not null");
            return (Criteria) this;
        }

        public Criteria andSfpcfpEqualTo(String value) {
            addCriterion("SFPCFP =", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpNotEqualTo(String value) {
            addCriterion("SFPCFP <>", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpGreaterThan(String value) {
            addCriterion("SFPCFP >", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpGreaterThanOrEqualTo(String value) {
            addCriterion("SFPCFP >=", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpLessThan(String value) {
            addCriterion("SFPCFP <", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpLessThanOrEqualTo(String value) {
            addCriterion("SFPCFP <=", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpLike(String value) {
            addCriterion("SFPCFP like", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpNotLike(String value) {
            addCriterion("SFPCFP not like", value, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpIn(List<String> values) {
            addCriterion("SFPCFP in", values, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpNotIn(List<String> values) {
            addCriterion("SFPCFP not in", values, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpBetween(String value1, String value2) {
            addCriterion("SFPCFP between", value1, value2, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpcfpNotBetween(String value1, String value2) {
            addCriterion("SFPCFP not between", value1, value2, "sfpcfp");
            return (Criteria) this;
        }

        public Criteria andSfpzqdIsNull() {
            addCriterion("SFPZQD is null");
            return (Criteria) this;
        }

        public Criteria andSfpzqdIsNotNull() {
            addCriterion("SFPZQD is not null");
            return (Criteria) this;
        }

        public Criteria andSfpzqdEqualTo(String value) {
            addCriterion("SFPZQD =", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdNotEqualTo(String value) {
            addCriterion("SFPZQD <>", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdGreaterThan(String value) {
            addCriterion("SFPZQD >", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdGreaterThanOrEqualTo(String value) {
            addCriterion("SFPZQD >=", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdLessThan(String value) {
            addCriterion("SFPZQD <", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdLessThanOrEqualTo(String value) {
            addCriterion("SFPZQD <=", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdLike(String value) {
            addCriterion("SFPZQD like", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdNotLike(String value) {
            addCriterion("SFPZQD not like", value, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdIn(List<String> values) {
            addCriterion("SFPZQD in", values, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdNotIn(List<String> values) {
            addCriterion("SFPZQD not in", values, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdBetween(String value1, String value2) {
            addCriterion("SFPZQD between", value1, value2, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzqdNotBetween(String value1, String value2) {
            addCriterion("SFPZQD not between", value1, value2, "sfpzqd");
            return (Criteria) this;
        }

        public Criteria andSfpzssIsNull() {
            addCriterion("SFPZSS is null");
            return (Criteria) this;
        }

        public Criteria andSfpzssIsNotNull() {
            addCriterion("SFPZSS is not null");
            return (Criteria) this;
        }

        public Criteria andSfpzssEqualTo(String value) {
            addCriterion("SFPZSS =", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssNotEqualTo(String value) {
            addCriterion("SFPZSS <>", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssGreaterThan(String value) {
            addCriterion("SFPZSS >", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssGreaterThanOrEqualTo(String value) {
            addCriterion("SFPZSS >=", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssLessThan(String value) {
            addCriterion("SFPZSS <", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssLessThanOrEqualTo(String value) {
            addCriterion("SFPZSS <=", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssLike(String value) {
            addCriterion("SFPZSS like", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssNotLike(String value) {
            addCriterion("SFPZSS not like", value, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssIn(List<String> values) {
            addCriterion("SFPZSS in", values, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssNotIn(List<String> values) {
            addCriterion("SFPZSS not in", values, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssBetween(String value1, String value2) {
            addCriterion("SFPZSS between", value1, value2, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzssNotBetween(String value1, String value2) {
            addCriterion("SFPZSS not between", value1, value2, "sfpzss");
            return (Criteria) this;
        }

        public Criteria andSfpzfyIsNull() {
            addCriterion("SFPZFY is null");
            return (Criteria) this;
        }

        public Criteria andSfpzfyIsNotNull() {
            addCriterion("SFPZFY is not null");
            return (Criteria) this;
        }

        public Criteria andSfpzfyEqualTo(String value) {
            addCriterion("SFPZFY =", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyNotEqualTo(String value) {
            addCriterion("SFPZFY <>", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyGreaterThan(String value) {
            addCriterion("SFPZFY >", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyGreaterThanOrEqualTo(String value) {
            addCriterion("SFPZFY >=", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyLessThan(String value) {
            addCriterion("SFPZFY <", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyLessThanOrEqualTo(String value) {
            addCriterion("SFPZFY <=", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyLike(String value) {
            addCriterion("SFPZFY like", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyNotLike(String value) {
            addCriterion("SFPZFY not like", value, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyIn(List<String> values) {
            addCriterion("SFPZFY in", values, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyNotIn(List<String> values) {
            addCriterion("SFPZFY not in", values, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyBetween(String value1, String value2) {
            addCriterion("SFPZFY between", value1, value2, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzfyNotBetween(String value1, String value2) {
            addCriterion("SFPZFY not between", value1, value2, "sfpzfy");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzIsNull() {
            addCriterion("SFPZSXGZ is null");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzIsNotNull() {
            addCriterion("SFPZSXGZ is not null");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzEqualTo(String value) {
            addCriterion("SFPZSXGZ =", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzNotEqualTo(String value) {
            addCriterion("SFPZSXGZ <>", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzGreaterThan(String value) {
            addCriterion("SFPZSXGZ >", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzGreaterThanOrEqualTo(String value) {
            addCriterion("SFPZSXGZ >=", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzLessThan(String value) {
            addCriterion("SFPZSXGZ <", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzLessThanOrEqualTo(String value) {
            addCriterion("SFPZSXGZ <=", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzLike(String value) {
            addCriterion("SFPZSXGZ like", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzNotLike(String value) {
            addCriterion("SFPZSXGZ not like", value, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzIn(List<String> values) {
            addCriterion("SFPZSXGZ in", values, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzNotIn(List<String> values) {
            addCriterion("SFPZSXGZ not in", values, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzBetween(String value1, String value2) {
            addCriterion("SFPZSXGZ between", value1, value2, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfpzsxgzNotBetween(String value1, String value2) {
            addCriterion("SFPZSXGZ not between", value1, value2, "sfpzsxgz");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbIsNull() {
            addCriterion("SFBDAJLB is null");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbIsNotNull() {
            addCriterion("SFBDAJLB is not null");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbEqualTo(String value) {
            addCriterion("SFBDAJLB =", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbNotEqualTo(String value) {
            addCriterion("SFBDAJLB <>", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbGreaterThan(String value) {
            addCriterion("SFBDAJLB >", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbGreaterThanOrEqualTo(String value) {
            addCriterion("SFBDAJLB >=", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbLessThan(String value) {
            addCriterion("SFBDAJLB <", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbLessThanOrEqualTo(String value) {
            addCriterion("SFBDAJLB <=", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbLike(String value) {
            addCriterion("SFBDAJLB like", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbNotLike(String value) {
            addCriterion("SFBDAJLB not like", value, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbIn(List<String> values) {
            addCriterion("SFBDAJLB in", values, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbNotIn(List<String> values) {
            addCriterion("SFBDAJLB not in", values, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbBetween(String value1, String value2) {
            addCriterion("SFBDAJLB between", value1, value2, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfbdajlbNotBetween(String value1, String value2) {
            addCriterion("SFBDAJLB not between", value1, value2, "sfbdajlb");
            return (Criteria) this;
        }

        public Criteria andSfzapcIsNull() {
            addCriterion("SFZAPC is null");
            return (Criteria) this;
        }

        public Criteria andSfzapcIsNotNull() {
            addCriterion("SFZAPC is not null");
            return (Criteria) this;
        }

        public Criteria andSfzapcEqualTo(String value) {
            addCriterion("SFZAPC =", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcNotEqualTo(String value) {
            addCriterion("SFZAPC <>", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcGreaterThan(String value) {
            addCriterion("SFZAPC >", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcGreaterThanOrEqualTo(String value) {
            addCriterion("SFZAPC >=", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcLessThan(String value) {
            addCriterion("SFZAPC <", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcLessThanOrEqualTo(String value) {
            addCriterion("SFZAPC <=", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcLike(String value) {
            addCriterion("SFZAPC like", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcNotLike(String value) {
            addCriterion("SFZAPC not like", value, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcIn(List<String> values) {
            addCriterion("SFZAPC in", values, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcNotIn(List<String> values) {
            addCriterion("SFZAPC not in", values, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcBetween(String value1, String value2) {
            addCriterion("SFZAPC between", value1, value2, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andSfzapcNotBetween(String value1, String value2) {
            addCriterion("SFZAPC not between", value1, value2, "sfzapc");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
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

        public Criteria andSfqyIsNull() {
            addCriterion("SFQY is null");
            return (Criteria) this;
        }

        public Criteria andSfqyIsNotNull() {
            addCriterion("SFQY is not null");
            return (Criteria) this;
        }

        public Criteria andSfqyEqualTo(String value) {
            addCriterion("SFQY =", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyNotEqualTo(String value) {
            addCriterion("SFQY <>", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyGreaterThan(String value) {
            addCriterion("SFQY >", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyGreaterThanOrEqualTo(String value) {
            addCriterion("SFQY >=", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyLessThan(String value) {
            addCriterion("SFQY <", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyLessThanOrEqualTo(String value) {
            addCriterion("SFQY <=", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyLike(String value) {
            addCriterion("SFQY like", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyNotLike(String value) {
            addCriterion("SFQY not like", value, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyIn(List<String> values) {
            addCriterion("SFQY in", values, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyNotIn(List<String> values) {
            addCriterion("SFQY not in", values, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyBetween(String value1, String value2) {
            addCriterion("SFQY between", value1, value2, "sfqy");
            return (Criteria) this;
        }

        public Criteria andSfqyNotBetween(String value1, String value2) {
            addCriterion("SFQY not between", value1, value2, "sfqy");
            return (Criteria) this;
        }

        public Criteria andUrlSmIsNull() {
            addCriterion("URL_SM is null");
            return (Criteria) this;
        }

        public Criteria andUrlSmIsNotNull() {
            addCriterion("URL_SM is not null");
            return (Criteria) this;
        }

        public Criteria andUrlSmEqualTo(String value) {
            addCriterion("URL_SM =", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmNotEqualTo(String value) {
            addCriterion("URL_SM <>", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmGreaterThan(String value) {
            addCriterion("URL_SM >", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmGreaterThanOrEqualTo(String value) {
            addCriterion("URL_SM >=", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmLessThan(String value) {
            addCriterion("URL_SM <", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmLessThanOrEqualTo(String value) {
            addCriterion("URL_SM <=", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmLike(String value) {
            addCriterion("URL_SM like", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmNotLike(String value) {
            addCriterion("URL_SM not like", value, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmIn(List<String> values) {
            addCriterion("URL_SM in", values, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmNotIn(List<String> values) {
            addCriterion("URL_SM not in", values, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmBetween(String value1, String value2) {
            addCriterion("URL_SM between", value1, value2, "urlSm");
            return (Criteria) this;
        }

        public Criteria andUrlSmNotBetween(String value1, String value2) {
            addCriterion("URL_SM not between", value1, value2, "urlSm");
            return (Criteria) this;
        }

        public Criteria andPcahjcIsNull() {
            addCriterion("PCAHJC is null");
            return (Criteria) this;
        }

        public Criteria andPcahjcIsNotNull() {
            addCriterion("PCAHJC is not null");
            return (Criteria) this;
        }

        public Criteria andPcahjcEqualTo(String value) {
            addCriterion("PCAHJC =", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcNotEqualTo(String value) {
            addCriterion("PCAHJC <>", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcGreaterThan(String value) {
            addCriterion("PCAHJC >", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcGreaterThanOrEqualTo(String value) {
            addCriterion("PCAHJC >=", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcLessThan(String value) {
            addCriterion("PCAHJC <", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcLessThanOrEqualTo(String value) {
            addCriterion("PCAHJC <=", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcLike(String value) {
            addCriterion("PCAHJC like", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcNotLike(String value) {
            addCriterion("PCAHJC not like", value, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcIn(List<String> values) {
            addCriterion("PCAHJC in", values, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcNotIn(List<String> values) {
            addCriterion("PCAHJC not in", values, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcBetween(String value1, String value2) {
            addCriterion("PCAHJC between", value1, value2, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andPcahjcNotBetween(String value1, String value2) {
            addCriterion("PCAHJC not between", value1, value2, "pcahjc");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxIsNull() {
            addCriterion("SFSXGZDX is null");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxIsNotNull() {
            addCriterion("SFSXGZDX is not null");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxEqualTo(String value) {
            addCriterion("SFSXGZDX =", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxNotEqualTo(String value) {
            addCriterion("SFSXGZDX <>", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxGreaterThan(String value) {
            addCriterion("SFSXGZDX >", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxGreaterThanOrEqualTo(String value) {
            addCriterion("SFSXGZDX >=", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxLessThan(String value) {
            addCriterion("SFSXGZDX <", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxLessThanOrEqualTo(String value) {
            addCriterion("SFSXGZDX <=", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxLike(String value) {
            addCriterion("SFSXGZDX like", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxNotLike(String value) {
            addCriterion("SFSXGZDX not like", value, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxIn(List<String> values) {
            addCriterion("SFSXGZDX in", values, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxNotIn(List<String> values) {
            addCriterion("SFSXGZDX not in", values, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxBetween(String value1, String value2) {
            addCriterion("SFSXGZDX between", value1, value2, "sfsxgzdx");
            return (Criteria) this;
        }

        public Criteria andSfsxgzdxNotBetween(String value1, String value2) {
            addCriterion("SFSXGZDX not between", value1, value2, "sfsxgzdx");
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