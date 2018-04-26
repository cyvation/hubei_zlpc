package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class XtZzjgRmbmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtZzjgRmbmExample() {
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

        public Criteria andGhIsNull() {
            addCriterion("GH is null");
            return (Criteria) this;
        }

        public Criteria andGhIsNotNull() {
            addCriterion("GH is not null");
            return (Criteria) this;
        }

        public Criteria andGhEqualTo(String value) {
            addCriterion("GH =", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhNotEqualTo(String value) {
            addCriterion("GH <>", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhGreaterThan(String value) {
            addCriterion("GH >", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhGreaterThanOrEqualTo(String value) {
            addCriterion("GH >=", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhLessThan(String value) {
            addCriterion("GH <", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhLessThanOrEqualTo(String value) {
            addCriterion("GH <=", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhLike(String value) {
            addCriterion("GH like", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhNotLike(String value) {
            addCriterion("GH not like", value, "gh");
            return (Criteria) this;
        }

        public Criteria andGhIn(List<String> values) {
            addCriterion("GH in", values, "gh");
            return (Criteria) this;
        }

        public Criteria andGhNotIn(List<String> values) {
            addCriterion("GH not in", values, "gh");
            return (Criteria) this;
        }

        public Criteria andGhBetween(String value1, String value2) {
            addCriterion("GH between", value1, value2, "gh");
            return (Criteria) this;
        }

        public Criteria andGhNotBetween(String value1, String value2) {
            addCriterion("GH not between", value1, value2, "gh");
            return (Criteria) this;
        }

        public Criteria andDlbmIsNull() {
            addCriterion("DLBM is null");
            return (Criteria) this;
        }

        public Criteria andDlbmIsNotNull() {
            addCriterion("DLBM is not null");
            return (Criteria) this;
        }

        public Criteria andDlbmEqualTo(String value) {
            addCriterion("DLBM =", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmNotEqualTo(String value) {
            addCriterion("DLBM <>", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmGreaterThan(String value) {
            addCriterion("DLBM >", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmGreaterThanOrEqualTo(String value) {
            addCriterion("DLBM >=", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmLessThan(String value) {
            addCriterion("DLBM <", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmLessThanOrEqualTo(String value) {
            addCriterion("DLBM <=", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmLike(String value) {
            addCriterion("DLBM like", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmNotLike(String value) {
            addCriterion("DLBM not like", value, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmIn(List<String> values) {
            addCriterion("DLBM in", values, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmNotIn(List<String> values) {
            addCriterion("DLBM not in", values, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmBetween(String value1, String value2) {
            addCriterion("DLBM between", value1, value2, "dlbm");
            return (Criteria) this;
        }

        public Criteria andDlbmNotBetween(String value1, String value2) {
            addCriterion("DLBM not between", value1, value2, "dlbm");
            return (Criteria) this;
        }

        public Criteria andKlIsNull() {
            addCriterion("KL is null");
            return (Criteria) this;
        }

        public Criteria andKlIsNotNull() {
            addCriterion("KL is not null");
            return (Criteria) this;
        }

        public Criteria andKlEqualTo(String value) {
            addCriterion("KL =", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlNotEqualTo(String value) {
            addCriterion("KL <>", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlGreaterThan(String value) {
            addCriterion("KL >", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlGreaterThanOrEqualTo(String value) {
            addCriterion("KL >=", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlLessThan(String value) {
            addCriterion("KL <", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlLessThanOrEqualTo(String value) {
            addCriterion("KL <=", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlLike(String value) {
            addCriterion("KL like", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlNotLike(String value) {
            addCriterion("KL not like", value, "kl");
            return (Criteria) this;
        }

        public Criteria andKlIn(List<String> values) {
            addCriterion("KL in", values, "kl");
            return (Criteria) this;
        }

        public Criteria andKlNotIn(List<String> values) {
            addCriterion("KL not in", values, "kl");
            return (Criteria) this;
        }

        public Criteria andKlBetween(String value1, String value2) {
            addCriterion("KL between", value1, value2, "kl");
            return (Criteria) this;
        }

        public Criteria andKlNotBetween(String value1, String value2) {
            addCriterion("KL not between", value1, value2, "kl");
            return (Criteria) this;
        }

        public Criteria andMcIsNull() {
            addCriterion("MC is null");
            return (Criteria) this;
        }

        public Criteria andMcIsNotNull() {
            addCriterion("MC is not null");
            return (Criteria) this;
        }

        public Criteria andMcEqualTo(String value) {
            addCriterion("MC =", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotEqualTo(String value) {
            addCriterion("MC <>", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThan(String value) {
            addCriterion("MC >", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThanOrEqualTo(String value) {
            addCriterion("MC >=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThan(String value) {
            addCriterion("MC <", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThanOrEqualTo(String value) {
            addCriterion("MC <=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLike(String value) {
            addCriterion("MC like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotLike(String value) {
            addCriterion("MC not like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcIn(List<String> values) {
            addCriterion("MC in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotIn(List<String> values) {
            addCriterion("MC not in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcBetween(String value1, String value2) {
            addCriterion("MC between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotBetween(String value1, String value2) {
            addCriterion("MC not between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andGzzhIsNull() {
            addCriterion("GZZH is null");
            return (Criteria) this;
        }

        public Criteria andGzzhIsNotNull() {
            addCriterion("GZZH is not null");
            return (Criteria) this;
        }

        public Criteria andGzzhEqualTo(String value) {
            addCriterion("GZZH =", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhNotEqualTo(String value) {
            addCriterion("GZZH <>", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhGreaterThan(String value) {
            addCriterion("GZZH >", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhGreaterThanOrEqualTo(String value) {
            addCriterion("GZZH >=", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhLessThan(String value) {
            addCriterion("GZZH <", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhLessThanOrEqualTo(String value) {
            addCriterion("GZZH <=", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhLike(String value) {
            addCriterion("GZZH like", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhNotLike(String value) {
            addCriterion("GZZH not like", value, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhIn(List<String> values) {
            addCriterion("GZZH in", values, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhNotIn(List<String> values) {
            addCriterion("GZZH not in", values, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhBetween(String value1, String value2) {
            addCriterion("GZZH between", value1, value2, "gzzh");
            return (Criteria) this;
        }

        public Criteria andGzzhNotBetween(String value1, String value2) {
            addCriterion("GZZH not between", value1, value2, "gzzh");
            return (Criteria) this;
        }

        public Criteria andYddhhmIsNull() {
            addCriterion("YDDHHM is null");
            return (Criteria) this;
        }

        public Criteria andYddhhmIsNotNull() {
            addCriterion("YDDHHM is not null");
            return (Criteria) this;
        }

        public Criteria andYddhhmEqualTo(String value) {
            addCriterion("YDDHHM =", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmNotEqualTo(String value) {
            addCriterion("YDDHHM <>", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmGreaterThan(String value) {
            addCriterion("YDDHHM >", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmGreaterThanOrEqualTo(String value) {
            addCriterion("YDDHHM >=", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmLessThan(String value) {
            addCriterion("YDDHHM <", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmLessThanOrEqualTo(String value) {
            addCriterion("YDDHHM <=", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmLike(String value) {
            addCriterion("YDDHHM like", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmNotLike(String value) {
            addCriterion("YDDHHM not like", value, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmIn(List<String> values) {
            addCriterion("YDDHHM in", values, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmNotIn(List<String> values) {
            addCriterion("YDDHHM not in", values, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmBetween(String value1, String value2) {
            addCriterion("YDDHHM between", value1, value2, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andYddhhmNotBetween(String value1, String value2) {
            addCriterion("YDDHHM not between", value1, value2, "yddhhm");
            return (Criteria) this;
        }

        public Criteria andDzyjIsNull() {
            addCriterion("DZYJ is null");
            return (Criteria) this;
        }

        public Criteria andDzyjIsNotNull() {
            addCriterion("DZYJ is not null");
            return (Criteria) this;
        }

        public Criteria andDzyjEqualTo(String value) {
            addCriterion("DZYJ =", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjNotEqualTo(String value) {
            addCriterion("DZYJ <>", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjGreaterThan(String value) {
            addCriterion("DZYJ >", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjGreaterThanOrEqualTo(String value) {
            addCriterion("DZYJ >=", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjLessThan(String value) {
            addCriterion("DZYJ <", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjLessThanOrEqualTo(String value) {
            addCriterion("DZYJ <=", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjLike(String value) {
            addCriterion("DZYJ like", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjNotLike(String value) {
            addCriterion("DZYJ not like", value, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjIn(List<String> values) {
            addCriterion("DZYJ in", values, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjNotIn(List<String> values) {
            addCriterion("DZYJ not in", values, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjBetween(String value1, String value2) {
            addCriterion("DZYJ between", value1, value2, "dzyj");
            return (Criteria) this;
        }

        public Criteria andDzyjNotBetween(String value1, String value2) {
            addCriterion("DZYJ not between", value1, value2, "dzyj");
            return (Criteria) this;
        }

        public Criteria andYdwbmIsNull() {
            addCriterion("YDWBM is null");
            return (Criteria) this;
        }

        public Criteria andYdwbmIsNotNull() {
            addCriterion("YDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andYdwbmEqualTo(String value) {
            addCriterion("YDWBM =", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmNotEqualTo(String value) {
            addCriterion("YDWBM <>", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmGreaterThan(String value) {
            addCriterion("YDWBM >", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("YDWBM >=", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmLessThan(String value) {
            addCriterion("YDWBM <", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmLessThanOrEqualTo(String value) {
            addCriterion("YDWBM <=", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmLike(String value) {
            addCriterion("YDWBM like", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmNotLike(String value) {
            addCriterion("YDWBM not like", value, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmIn(List<String> values) {
            addCriterion("YDWBM in", values, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmNotIn(List<String> values) {
            addCriterion("YDWBM not in", values, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmBetween(String value1, String value2) {
            addCriterion("YDWBM between", value1, value2, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwbmNotBetween(String value1, String value2) {
            addCriterion("YDWBM not between", value1, value2, "ydwbm");
            return (Criteria) this;
        }

        public Criteria andYdwmcIsNull() {
            addCriterion("YDWMC is null");
            return (Criteria) this;
        }

        public Criteria andYdwmcIsNotNull() {
            addCriterion("YDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andYdwmcEqualTo(String value) {
            addCriterion("YDWMC =", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcNotEqualTo(String value) {
            addCriterion("YDWMC <>", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcGreaterThan(String value) {
            addCriterion("YDWMC >", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("YDWMC >=", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcLessThan(String value) {
            addCriterion("YDWMC <", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcLessThanOrEqualTo(String value) {
            addCriterion("YDWMC <=", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcLike(String value) {
            addCriterion("YDWMC like", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcNotLike(String value) {
            addCriterion("YDWMC not like", value, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcIn(List<String> values) {
            addCriterion("YDWMC in", values, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcNotIn(List<String> values) {
            addCriterion("YDWMC not in", values, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcBetween(String value1, String value2) {
            addCriterion("YDWMC between", value1, value2, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andYdwmcNotBetween(String value1, String value2) {
            addCriterion("YDWMC not between", value1, value2, "ydwmc");
            return (Criteria) this;
        }

        public Criteria andSflsryIsNull() {
            addCriterion("SFLSRY is null");
            return (Criteria) this;
        }

        public Criteria andSflsryIsNotNull() {
            addCriterion("SFLSRY is not null");
            return (Criteria) this;
        }

        public Criteria andSflsryEqualTo(String value) {
            addCriterion("SFLSRY =", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryNotEqualTo(String value) {
            addCriterion("SFLSRY <>", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryGreaterThan(String value) {
            addCriterion("SFLSRY >", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryGreaterThanOrEqualTo(String value) {
            addCriterion("SFLSRY >=", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryLessThan(String value) {
            addCriterion("SFLSRY <", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryLessThanOrEqualTo(String value) {
            addCriterion("SFLSRY <=", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryLike(String value) {
            addCriterion("SFLSRY like", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryNotLike(String value) {
            addCriterion("SFLSRY not like", value, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryIn(List<String> values) {
            addCriterion("SFLSRY in", values, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryNotIn(List<String> values) {
            addCriterion("SFLSRY not in", values, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryBetween(String value1, String value2) {
            addCriterion("SFLSRY between", value1, value2, "sflsry");
            return (Criteria) this;
        }

        public Criteria andSflsryNotBetween(String value1, String value2) {
            addCriterion("SFLSRY not between", value1, value2, "sflsry");
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

        public Criteria andSftzIsNull() {
            addCriterion("SFTZ is null");
            return (Criteria) this;
        }

        public Criteria andSftzIsNotNull() {
            addCriterion("SFTZ is not null");
            return (Criteria) this;
        }

        public Criteria andSftzEqualTo(String value) {
            addCriterion("SFTZ =", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzNotEqualTo(String value) {
            addCriterion("SFTZ <>", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzGreaterThan(String value) {
            addCriterion("SFTZ >", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzGreaterThanOrEqualTo(String value) {
            addCriterion("SFTZ >=", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzLessThan(String value) {
            addCriterion("SFTZ <", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzLessThanOrEqualTo(String value) {
            addCriterion("SFTZ <=", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzLike(String value) {
            addCriterion("SFTZ like", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzNotLike(String value) {
            addCriterion("SFTZ not like", value, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzIn(List<String> values) {
            addCriterion("SFTZ in", values, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzNotIn(List<String> values) {
            addCriterion("SFTZ not in", values, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzBetween(String value1, String value2) {
            addCriterion("SFTZ between", value1, value2, "sftz");
            return (Criteria) this;
        }

        public Criteria andSftzNotBetween(String value1, String value2) {
            addCriterion("SFTZ not between", value1, value2, "sftz");
            return (Criteria) this;
        }

        public Criteria andXbIsNull() {
            addCriterion("XB is null");
            return (Criteria) this;
        }

        public Criteria andXbIsNotNull() {
            addCriterion("XB is not null");
            return (Criteria) this;
        }

        public Criteria andXbEqualTo(String value) {
            addCriterion("XB =", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotEqualTo(String value) {
            addCriterion("XB <>", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbGreaterThan(String value) {
            addCriterion("XB >", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbGreaterThanOrEqualTo(String value) {
            addCriterion("XB >=", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLessThan(String value) {
            addCriterion("XB <", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLessThanOrEqualTo(String value) {
            addCriterion("XB <=", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLike(String value) {
            addCriterion("XB like", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotLike(String value) {
            addCriterion("XB not like", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbIn(List<String> values) {
            addCriterion("XB in", values, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotIn(List<String> values) {
            addCriterion("XB not in", values, "xb");
            return (Criteria) this;
        }

        public Criteria andXbBetween(String value1, String value2) {
            addCriterion("XB between", value1, value2, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotBetween(String value1, String value2) {
            addCriterion("XB not between", value1, value2, "xb");
            return (Criteria) this;
        }

        public Criteria andCaidIsNull() {
            addCriterion("CAID is null");
            return (Criteria) this;
        }

        public Criteria andCaidIsNotNull() {
            addCriterion("CAID is not null");
            return (Criteria) this;
        }

        public Criteria andCaidEqualTo(String value) {
            addCriterion("CAID =", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidNotEqualTo(String value) {
            addCriterion("CAID <>", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidGreaterThan(String value) {
            addCriterion("CAID >", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidGreaterThanOrEqualTo(String value) {
            addCriterion("CAID >=", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidLessThan(String value) {
            addCriterion("CAID <", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidLessThanOrEqualTo(String value) {
            addCriterion("CAID <=", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidLike(String value) {
            addCriterion("CAID like", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidNotLike(String value) {
            addCriterion("CAID not like", value, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidIn(List<String> values) {
            addCriterion("CAID in", values, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidNotIn(List<String> values) {
            addCriterion("CAID not in", values, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidBetween(String value1, String value2) {
            addCriterion("CAID between", value1, value2, "caid");
            return (Criteria) this;
        }

        public Criteria andCaidNotBetween(String value1, String value2) {
            addCriterion("CAID not between", value1, value2, "caid");
            return (Criteria) this;
        }

        public Criteria andZrjcgghIsNull() {
            addCriterion("ZRJCGGH is null");
            return (Criteria) this;
        }

        public Criteria andZrjcgghIsNotNull() {
            addCriterion("ZRJCGGH is not null");
            return (Criteria) this;
        }

        public Criteria andZrjcgghEqualTo(String value) {
            addCriterion("ZRJCGGH =", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghNotEqualTo(String value) {
            addCriterion("ZRJCGGH <>", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghGreaterThan(String value) {
            addCriterion("ZRJCGGH >", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghGreaterThanOrEqualTo(String value) {
            addCriterion("ZRJCGGH >=", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghLessThan(String value) {
            addCriterion("ZRJCGGH <", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghLessThanOrEqualTo(String value) {
            addCriterion("ZRJCGGH <=", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghLike(String value) {
            addCriterion("ZRJCGGH like", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghNotLike(String value) {
            addCriterion("ZRJCGGH not like", value, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghIn(List<String> values) {
            addCriterion("ZRJCGGH in", values, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghNotIn(List<String> values) {
            addCriterion("ZRJCGGH not in", values, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghBetween(String value1, String value2) {
            addCriterion("ZRJCGGH between", value1, value2, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andZrjcgghNotBetween(String value1, String value2) {
            addCriterion("ZRJCGGH not between", value1, value2, "zrjcggh");
            return (Criteria) this;
        }

        public Criteria andSfzhIsNull() {
            addCriterion("SFZH is null");
            return (Criteria) this;
        }

        public Criteria andSfzhIsNotNull() {
            addCriterion("SFZH is not null");
            return (Criteria) this;
        }

        public Criteria andSfzhEqualTo(String value) {
            addCriterion("SFZH =", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotEqualTo(String value) {
            addCriterion("SFZH <>", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhGreaterThan(String value) {
            addCriterion("SFZH >", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhGreaterThanOrEqualTo(String value) {
            addCriterion("SFZH >=", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLessThan(String value) {
            addCriterion("SFZH <", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLessThanOrEqualTo(String value) {
            addCriterion("SFZH <=", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLike(String value) {
            addCriterion("SFZH like", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotLike(String value) {
            addCriterion("SFZH not like", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhIn(List<String> values) {
            addCriterion("SFZH in", values, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotIn(List<String> values) {
            addCriterion("SFZH not in", values, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhBetween(String value1, String value2) {
            addCriterion("SFZH between", value1, value2, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotBetween(String value1, String value2) {
            addCriterion("SFZH not between", value1, value2, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfIsNull() {
            addCriterion("SF is null");
            return (Criteria) this;
        }

        public Criteria andSfIsNotNull() {
            addCriterion("SF is not null");
            return (Criteria) this;
        }

        public Criteria andSfEqualTo(String value) {
            addCriterion("SF =", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotEqualTo(String value) {
            addCriterion("SF <>", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThan(String value) {
            addCriterion("SF >", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfGreaterThanOrEqualTo(String value) {
            addCriterion("SF >=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThan(String value) {
            addCriterion("SF <", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLessThanOrEqualTo(String value) {
            addCriterion("SF <=", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfLike(String value) {
            addCriterion("SF like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotLike(String value) {
            addCriterion("SF not like", value, "sf");
            return (Criteria) this;
        }

        public Criteria andSfIn(List<String> values) {
            addCriterion("SF in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotIn(List<String> values) {
            addCriterion("SF not in", values, "sf");
            return (Criteria) this;
        }

        public Criteria andSfBetween(String value1, String value2) {
            addCriterion("SF between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andSfNotBetween(String value1, String value2) {
            addCriterion("SF not between", value1, value2, "sf");
            return (Criteria) this;
        }

        public Criteria andSsxzbmIsNull() {
            addCriterion("SSXZBM is null");
            return (Criteria) this;
        }

        public Criteria andSsxzbmIsNotNull() {
            addCriterion("SSXZBM is not null");
            return (Criteria) this;
        }

        public Criteria andSsxzbmEqualTo(String value) {
            addCriterion("SSXZBM =", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmNotEqualTo(String value) {
            addCriterion("SSXZBM <>", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmGreaterThan(String value) {
            addCriterion("SSXZBM >", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmGreaterThanOrEqualTo(String value) {
            addCriterion("SSXZBM >=", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmLessThan(String value) {
            addCriterion("SSXZBM <", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmLessThanOrEqualTo(String value) {
            addCriterion("SSXZBM <=", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmLike(String value) {
            addCriterion("SSXZBM like", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmNotLike(String value) {
            addCriterion("SSXZBM not like", value, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmIn(List<String> values) {
            addCriterion("SSXZBM in", values, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmNotIn(List<String> values) {
            addCriterion("SSXZBM not in", values, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmBetween(String value1, String value2) {
            addCriterion("SSXZBM between", value1, value2, "ssxzbm");
            return (Criteria) this;
        }

        public Criteria andSsxzbmNotBetween(String value1, String value2) {
            addCriterion("SSXZBM not between", value1, value2, "ssxzbm");
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