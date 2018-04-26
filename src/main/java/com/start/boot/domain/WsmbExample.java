package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WsmbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WsmbExample() {
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

        public Criteria andWsmbbhIsNull() {
            addCriterion("WSMBBH is null");
            return (Criteria) this;
        }

        public Criteria andWsmbbhIsNotNull() {
            addCriterion("WSMBBH is not null");
            return (Criteria) this;
        }

        public Criteria andWsmbbhEqualTo(String value) {
            addCriterion("WSMBBH =", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhNotEqualTo(String value) {
            addCriterion("WSMBBH <>", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhGreaterThan(String value) {
            addCriterion("WSMBBH >", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhGreaterThanOrEqualTo(String value) {
            addCriterion("WSMBBH >=", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhLessThan(String value) {
            addCriterion("WSMBBH <", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhLessThanOrEqualTo(String value) {
            addCriterion("WSMBBH <=", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhLike(String value) {
            addCriterion("WSMBBH like", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhNotLike(String value) {
            addCriterion("WSMBBH not like", value, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhIn(List<String> values) {
            addCriterion("WSMBBH in", values, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhNotIn(List<String> values) {
            addCriterion("WSMBBH not in", values, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhBetween(String value1, String value2) {
            addCriterion("WSMBBH between", value1, value2, "wsmbbh");
            return (Criteria) this;
        }

        public Criteria andWsmbbhNotBetween(String value1, String value2) {
            addCriterion("WSMBBH not between", value1, value2, "wsmbbh");
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

        public Criteria andWsmbmcIsNull() {
            addCriterion("WSMBMC is null");
            return (Criteria) this;
        }

        public Criteria andWsmbmcIsNotNull() {
            addCriterion("WSMBMC is not null");
            return (Criteria) this;
        }

        public Criteria andWsmbmcEqualTo(String value) {
            addCriterion("WSMBMC =", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcNotEqualTo(String value) {
            addCriterion("WSMBMC <>", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcGreaterThan(String value) {
            addCriterion("WSMBMC >", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcGreaterThanOrEqualTo(String value) {
            addCriterion("WSMBMC >=", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcLessThan(String value) {
            addCriterion("WSMBMC <", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcLessThanOrEqualTo(String value) {
            addCriterion("WSMBMC <=", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcLike(String value) {
            addCriterion("WSMBMC like", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcNotLike(String value) {
            addCriterion("WSMBMC not like", value, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcIn(List<String> values) {
            addCriterion("WSMBMC in", values, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcNotIn(List<String> values) {
            addCriterion("WSMBMC not in", values, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcBetween(String value1, String value2) {
            addCriterion("WSMBMC between", value1, value2, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmbmcNotBetween(String value1, String value2) {
            addCriterion("WSMBMC not between", value1, value2, "wsmbmc");
            return (Criteria) this;
        }

        public Criteria andWsmblbIsNull() {
            addCriterion("WSMBLB is null");
            return (Criteria) this;
        }

        public Criteria andWsmblbIsNotNull() {
            addCriterion("WSMBLB is not null");
            return (Criteria) this;
        }

        public Criteria andWsmblbEqualTo(String value) {
            addCriterion("WSMBLB =", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbNotEqualTo(String value) {
            addCriterion("WSMBLB <>", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbGreaterThan(String value) {
            addCriterion("WSMBLB >", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbGreaterThanOrEqualTo(String value) {
            addCriterion("WSMBLB >=", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbLessThan(String value) {
            addCriterion("WSMBLB <", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbLessThanOrEqualTo(String value) {
            addCriterion("WSMBLB <=", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbLike(String value) {
            addCriterion("WSMBLB like", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbNotLike(String value) {
            addCriterion("WSMBLB not like", value, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbIn(List<String> values) {
            addCriterion("WSMBLB in", values, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbNotIn(List<String> values) {
            addCriterion("WSMBLB not in", values, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbBetween(String value1, String value2) {
            addCriterion("WSMBLB between", value1, value2, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmblbNotBetween(String value1, String value2) {
            addCriterion("WSMBLB not between", value1, value2, "wsmblb");
            return (Criteria) this;
        }

        public Criteria andWsmbljIsNull() {
            addCriterion("WSMBLJ is null");
            return (Criteria) this;
        }

        public Criteria andWsmbljIsNotNull() {
            addCriterion("WSMBLJ is not null");
            return (Criteria) this;
        }

        public Criteria andWsmbljEqualTo(String value) {
            addCriterion("WSMBLJ =", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljNotEqualTo(String value) {
            addCriterion("WSMBLJ <>", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljGreaterThan(String value) {
            addCriterion("WSMBLJ >", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljGreaterThanOrEqualTo(String value) {
            addCriterion("WSMBLJ >=", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljLessThan(String value) {
            addCriterion("WSMBLJ <", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljLessThanOrEqualTo(String value) {
            addCriterion("WSMBLJ <=", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljLike(String value) {
            addCriterion("WSMBLJ like", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljNotLike(String value) {
            addCriterion("WSMBLJ not like", value, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljIn(List<String> values) {
            addCriterion("WSMBLJ in", values, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljNotIn(List<String> values) {
            addCriterion("WSMBLJ not in", values, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljBetween(String value1, String value2) {
            addCriterion("WSMBLJ between", value1, value2, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWsmbljNotBetween(String value1, String value2) {
            addCriterion("WSMBLJ not between", value1, value2, "wsmblj");
            return (Criteria) this;
        }

        public Criteria andWspxIsNull() {
            addCriterion("WSPX is null");
            return (Criteria) this;
        }

        public Criteria andWspxIsNotNull() {
            addCriterion("WSPX is not null");
            return (Criteria) this;
        }

        public Criteria andWspxEqualTo(BigDecimal value) {
            addCriterion("WSPX =", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxNotEqualTo(BigDecimal value) {
            addCriterion("WSPX <>", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxGreaterThan(BigDecimal value) {
            addCriterion("WSPX >", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WSPX >=", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxLessThan(BigDecimal value) {
            addCriterion("WSPX <", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WSPX <=", value, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxIn(List<BigDecimal> values) {
            addCriterion("WSPX in", values, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxNotIn(List<BigDecimal> values) {
            addCriterion("WSPX not in", values, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WSPX between", value1, value2, "wspx");
            return (Criteria) this;
        }

        public Criteria andWspxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WSPX not between", value1, value2, "wspx");
            return (Criteria) this;
        }

        public Criteria andSpbbmIsNull() {
            addCriterion("SPBBM is null");
            return (Criteria) this;
        }

        public Criteria andSpbbmIsNotNull() {
            addCriterion("SPBBM is not null");
            return (Criteria) this;
        }

        public Criteria andSpbbmEqualTo(String value) {
            addCriterion("SPBBM =", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmNotEqualTo(String value) {
            addCriterion("SPBBM <>", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmGreaterThan(String value) {
            addCriterion("SPBBM >", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmGreaterThanOrEqualTo(String value) {
            addCriterion("SPBBM >=", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmLessThan(String value) {
            addCriterion("SPBBM <", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmLessThanOrEqualTo(String value) {
            addCriterion("SPBBM <=", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmLike(String value) {
            addCriterion("SPBBM like", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmNotLike(String value) {
            addCriterion("SPBBM not like", value, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmIn(List<String> values) {
            addCriterion("SPBBM in", values, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmNotIn(List<String> values) {
            addCriterion("SPBBM not in", values, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmBetween(String value1, String value2) {
            addCriterion("SPBBM between", value1, value2, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSpbbmNotBetween(String value1, String value2) {
            addCriterion("SPBBM not between", value1, value2, "spbbm");
            return (Criteria) this;
        }

        public Criteria andSfgxIsNull() {
            addCriterion("SFGX is null");
            return (Criteria) this;
        }

        public Criteria andSfgxIsNotNull() {
            addCriterion("SFGX is not null");
            return (Criteria) this;
        }

        public Criteria andSfgxEqualTo(String value) {
            addCriterion("SFGX =", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxNotEqualTo(String value) {
            addCriterion("SFGX <>", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxGreaterThan(String value) {
            addCriterion("SFGX >", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxGreaterThanOrEqualTo(String value) {
            addCriterion("SFGX >=", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxLessThan(String value) {
            addCriterion("SFGX <", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxLessThanOrEqualTo(String value) {
            addCriterion("SFGX <=", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxLike(String value) {
            addCriterion("SFGX like", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxNotLike(String value) {
            addCriterion("SFGX not like", value, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxIn(List<String> values) {
            addCriterion("SFGX in", values, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxNotIn(List<String> values) {
            addCriterion("SFGX not in", values, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxBetween(String value1, String value2) {
            addCriterion("SFGX between", value1, value2, "sfgx");
            return (Criteria) this;
        }

        public Criteria andSfgxNotBetween(String value1, String value2) {
            addCriterion("SFGX not between", value1, value2, "sfgx");
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

        public Criteria andJzmlhIsNull() {
            addCriterion("JZMLH is null");
            return (Criteria) this;
        }

        public Criteria andJzmlhIsNotNull() {
            addCriterion("JZMLH is not null");
            return (Criteria) this;
        }

        public Criteria andJzmlhEqualTo(String value) {
            addCriterion("JZMLH =", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhNotEqualTo(String value) {
            addCriterion("JZMLH <>", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhGreaterThan(String value) {
            addCriterion("JZMLH >", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhGreaterThanOrEqualTo(String value) {
            addCriterion("JZMLH >=", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhLessThan(String value) {
            addCriterion("JZMLH <", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhLessThanOrEqualTo(String value) {
            addCriterion("JZMLH <=", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhLike(String value) {
            addCriterion("JZMLH like", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhNotLike(String value) {
            addCriterion("JZMLH not like", value, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhIn(List<String> values) {
            addCriterion("JZMLH in", values, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhNotIn(List<String> values) {
            addCriterion("JZMLH not in", values, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhBetween(String value1, String value2) {
            addCriterion("JZMLH between", value1, value2, "jzmlh");
            return (Criteria) this;
        }

        public Criteria andJzmlhNotBetween(String value1, String value2) {
            addCriterion("JZMLH not between", value1, value2, "jzmlh");
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