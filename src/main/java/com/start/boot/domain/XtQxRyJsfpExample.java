package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XtQxRyJsfpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtQxRyJsfpExample() {
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

        public Criteria andBmbmIsNull() {
            addCriterion("BMBM is null");
            return (Criteria) this;
        }

        public Criteria andBmbmIsNotNull() {
            addCriterion("BMBM is not null");
            return (Criteria) this;
        }

        public Criteria andBmbmEqualTo(String value) {
            addCriterion("BMBM =", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmNotEqualTo(String value) {
            addCriterion("BMBM <>", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmGreaterThan(String value) {
            addCriterion("BMBM >", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmGreaterThanOrEqualTo(String value) {
            addCriterion("BMBM >=", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmLessThan(String value) {
            addCriterion("BMBM <", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmLessThanOrEqualTo(String value) {
            addCriterion("BMBM <=", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmLike(String value) {
            addCriterion("BMBM like", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmNotLike(String value) {
            addCriterion("BMBM not like", value, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmIn(List<String> values) {
            addCriterion("BMBM in", values, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmNotIn(List<String> values) {
            addCriterion("BMBM not in", values, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmBetween(String value1, String value2) {
            addCriterion("BMBM between", value1, value2, "bmbm");
            return (Criteria) this;
        }

        public Criteria andBmbmNotBetween(String value1, String value2) {
            addCriterion("BMBM not between", value1, value2, "bmbm");
            return (Criteria) this;
        }

        public Criteria andJsbmIsNull() {
            addCriterion("JSBM is null");
            return (Criteria) this;
        }

        public Criteria andJsbmIsNotNull() {
            addCriterion("JSBM is not null");
            return (Criteria) this;
        }

        public Criteria andJsbmEqualTo(String value) {
            addCriterion("JSBM =", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmNotEqualTo(String value) {
            addCriterion("JSBM <>", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmGreaterThan(String value) {
            addCriterion("JSBM >", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmGreaterThanOrEqualTo(String value) {
            addCriterion("JSBM >=", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmLessThan(String value) {
            addCriterion("JSBM <", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmLessThanOrEqualTo(String value) {
            addCriterion("JSBM <=", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmLike(String value) {
            addCriterion("JSBM like", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmNotLike(String value) {
            addCriterion("JSBM not like", value, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmIn(List<String> values) {
            addCriterion("JSBM in", values, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmNotIn(List<String> values) {
            addCriterion("JSBM not in", values, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmBetween(String value1, String value2) {
            addCriterion("JSBM between", value1, value2, "jsbm");
            return (Criteria) this;
        }

        public Criteria andJsbmNotBetween(String value1, String value2) {
            addCriterion("JSBM not between", value1, value2, "jsbm");
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

        public Criteria andZjldghIsNull() {
            addCriterion("ZJLDGH is null");
            return (Criteria) this;
        }

        public Criteria andZjldghIsNotNull() {
            addCriterion("ZJLDGH is not null");
            return (Criteria) this;
        }

        public Criteria andZjldghEqualTo(String value) {
            addCriterion("ZJLDGH =", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghNotEqualTo(String value) {
            addCriterion("ZJLDGH <>", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghGreaterThan(String value) {
            addCriterion("ZJLDGH >", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghGreaterThanOrEqualTo(String value) {
            addCriterion("ZJLDGH >=", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghLessThan(String value) {
            addCriterion("ZJLDGH <", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghLessThanOrEqualTo(String value) {
            addCriterion("ZJLDGH <=", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghLike(String value) {
            addCriterion("ZJLDGH like", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghNotLike(String value) {
            addCriterion("ZJLDGH not like", value, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghIn(List<String> values) {
            addCriterion("ZJLDGH in", values, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghNotIn(List<String> values) {
            addCriterion("ZJLDGH not in", values, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghBetween(String value1, String value2) {
            addCriterion("ZJLDGH between", value1, value2, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andZjldghNotBetween(String value1, String value2) {
            addCriterion("ZJLDGH not between", value1, value2, "zjldgh");
            return (Criteria) this;
        }

        public Criteria andRyxhIsNull() {
            addCriterion("RYXH is null");
            return (Criteria) this;
        }

        public Criteria andRyxhIsNotNull() {
            addCriterion("RYXH is not null");
            return (Criteria) this;
        }

        public Criteria andRyxhEqualTo(BigDecimal value) {
            addCriterion("RYXH =", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhNotEqualTo(BigDecimal value) {
            addCriterion("RYXH <>", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhGreaterThan(BigDecimal value) {
            addCriterion("RYXH >", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RYXH >=", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhLessThan(BigDecimal value) {
            addCriterion("RYXH <", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RYXH <=", value, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhIn(List<BigDecimal> values) {
            addCriterion("RYXH in", values, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhNotIn(List<BigDecimal> values) {
            addCriterion("RYXH not in", values, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RYXH between", value1, value2, "ryxh");
            return (Criteria) this;
        }

        public Criteria andRyxhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RYXH not between", value1, value2, "ryxh");
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

        public Criteria andYbmbmIsNull() {
            addCriterion("YBMBM is null");
            return (Criteria) this;
        }

        public Criteria andYbmbmIsNotNull() {
            addCriterion("YBMBM is not null");
            return (Criteria) this;
        }

        public Criteria andYbmbmEqualTo(String value) {
            addCriterion("YBMBM =", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmNotEqualTo(String value) {
            addCriterion("YBMBM <>", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmGreaterThan(String value) {
            addCriterion("YBMBM >", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmGreaterThanOrEqualTo(String value) {
            addCriterion("YBMBM >=", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmLessThan(String value) {
            addCriterion("YBMBM <", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmLessThanOrEqualTo(String value) {
            addCriterion("YBMBM <=", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmLike(String value) {
            addCriterion("YBMBM like", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmNotLike(String value) {
            addCriterion("YBMBM not like", value, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmIn(List<String> values) {
            addCriterion("YBMBM in", values, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmNotIn(List<String> values) {
            addCriterion("YBMBM not in", values, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmBetween(String value1, String value2) {
            addCriterion("YBMBM between", value1, value2, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmbmNotBetween(String value1, String value2) {
            addCriterion("YBMBM not between", value1, value2, "ybmbm");
            return (Criteria) this;
        }

        public Criteria andYbmmcIsNull() {
            addCriterion("YBMMC is null");
            return (Criteria) this;
        }

        public Criteria andYbmmcIsNotNull() {
            addCriterion("YBMMC is not null");
            return (Criteria) this;
        }

        public Criteria andYbmmcEqualTo(String value) {
            addCriterion("YBMMC =", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcNotEqualTo(String value) {
            addCriterion("YBMMC <>", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcGreaterThan(String value) {
            addCriterion("YBMMC >", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcGreaterThanOrEqualTo(String value) {
            addCriterion("YBMMC >=", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcLessThan(String value) {
            addCriterion("YBMMC <", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcLessThanOrEqualTo(String value) {
            addCriterion("YBMMC <=", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcLike(String value) {
            addCriterion("YBMMC like", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcNotLike(String value) {
            addCriterion("YBMMC not like", value, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcIn(List<String> values) {
            addCriterion("YBMMC in", values, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcNotIn(List<String> values) {
            addCriterion("YBMMC not in", values, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcBetween(String value1, String value2) {
            addCriterion("YBMMC between", value1, value2, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYbmmcNotBetween(String value1, String value2) {
            addCriterion("YBMMC not between", value1, value2, "ybmmc");
            return (Criteria) this;
        }

        public Criteria andYjsbmIsNull() {
            addCriterion("YJSBM is null");
            return (Criteria) this;
        }

        public Criteria andYjsbmIsNotNull() {
            addCriterion("YJSBM is not null");
            return (Criteria) this;
        }

        public Criteria andYjsbmEqualTo(String value) {
            addCriterion("YJSBM =", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmNotEqualTo(String value) {
            addCriterion("YJSBM <>", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmGreaterThan(String value) {
            addCriterion("YJSBM >", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmGreaterThanOrEqualTo(String value) {
            addCriterion("YJSBM >=", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmLessThan(String value) {
            addCriterion("YJSBM <", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmLessThanOrEqualTo(String value) {
            addCriterion("YJSBM <=", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmLike(String value) {
            addCriterion("YJSBM like", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmNotLike(String value) {
            addCriterion("YJSBM not like", value, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmIn(List<String> values) {
            addCriterion("YJSBM in", values, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmNotIn(List<String> values) {
            addCriterion("YJSBM not in", values, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmBetween(String value1, String value2) {
            addCriterion("YJSBM between", value1, value2, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsbmNotBetween(String value1, String value2) {
            addCriterion("YJSBM not between", value1, value2, "yjsbm");
            return (Criteria) this;
        }

        public Criteria andYjsmcIsNull() {
            addCriterion("YJSMC is null");
            return (Criteria) this;
        }

        public Criteria andYjsmcIsNotNull() {
            addCriterion("YJSMC is not null");
            return (Criteria) this;
        }

        public Criteria andYjsmcEqualTo(String value) {
            addCriterion("YJSMC =", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcNotEqualTo(String value) {
            addCriterion("YJSMC <>", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcGreaterThan(String value) {
            addCriterion("YJSMC >", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcGreaterThanOrEqualTo(String value) {
            addCriterion("YJSMC >=", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcLessThan(String value) {
            addCriterion("YJSMC <", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcLessThanOrEqualTo(String value) {
            addCriterion("YJSMC <=", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcLike(String value) {
            addCriterion("YJSMC like", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcNotLike(String value) {
            addCriterion("YJSMC not like", value, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcIn(List<String> values) {
            addCriterion("YJSMC in", values, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcNotIn(List<String> values) {
            addCriterion("YJSMC not in", values, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcBetween(String value1, String value2) {
            addCriterion("YJSMC between", value1, value2, "yjsmc");
            return (Criteria) this;
        }

        public Criteria andYjsmcNotBetween(String value1, String value2) {
            addCriterion("YJSMC not between", value1, value2, "yjsmc");
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