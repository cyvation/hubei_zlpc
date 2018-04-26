package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuButtonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuButtonExample() {
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

        public Criteria andDzbhIsNull() {
            addCriterion("DZBH is null");
            return (Criteria) this;
        }

        public Criteria andDzbhIsNotNull() {
            addCriterion("DZBH is not null");
            return (Criteria) this;
        }

        public Criteria andDzbhEqualTo(String value) {
            addCriterion("DZBH =", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhNotEqualTo(String value) {
            addCriterion("DZBH <>", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhGreaterThan(String value) {
            addCriterion("DZBH >", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhGreaterThanOrEqualTo(String value) {
            addCriterion("DZBH >=", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhLessThan(String value) {
            addCriterion("DZBH <", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhLessThanOrEqualTo(String value) {
            addCriterion("DZBH <=", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhLike(String value) {
            addCriterion("DZBH like", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhNotLike(String value) {
            addCriterion("DZBH not like", value, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhIn(List<String> values) {
            addCriterion("DZBH in", values, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhNotIn(List<String> values) {
            addCriterion("DZBH not in", values, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhBetween(String value1, String value2) {
            addCriterion("DZBH between", value1, value2, "dzbh");
            return (Criteria) this;
        }

        public Criteria andDzbhNotBetween(String value1, String value2) {
            addCriterion("DZBH not between", value1, value2, "dzbh");
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

        public Criteria andLcjdbhIsNull() {
            addCriterion("LCJDBH is null");
            return (Criteria) this;
        }

        public Criteria andLcjdbhIsNotNull() {
            addCriterion("LCJDBH is not null");
            return (Criteria) this;
        }

        public Criteria andLcjdbhEqualTo(String value) {
            addCriterion("LCJDBH =", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhNotEqualTo(String value) {
            addCriterion("LCJDBH <>", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhGreaterThan(String value) {
            addCriterion("LCJDBH >", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhGreaterThanOrEqualTo(String value) {
            addCriterion("LCJDBH >=", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhLessThan(String value) {
            addCriterion("LCJDBH <", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhLessThanOrEqualTo(String value) {
            addCriterion("LCJDBH <=", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhLike(String value) {
            addCriterion("LCJDBH like", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhNotLike(String value) {
            addCriterion("LCJDBH not like", value, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhIn(List<String> values) {
            addCriterion("LCJDBH in", values, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhNotIn(List<String> values) {
            addCriterion("LCJDBH not in", values, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhBetween(String value1, String value2) {
            addCriterion("LCJDBH between", value1, value2, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andLcjdbhNotBetween(String value1, String value2) {
            addCriterion("LCJDBH not between", value1, value2, "lcjdbh");
            return (Criteria) this;
        }

        public Criteria andCzlxbmIsNull() {
            addCriterion("CZLXBM is null");
            return (Criteria) this;
        }

        public Criteria andCzlxbmIsNotNull() {
            addCriterion("CZLXBM is not null");
            return (Criteria) this;
        }

        public Criteria andCzlxbmEqualTo(String value) {
            addCriterion("CZLXBM =", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmNotEqualTo(String value) {
            addCriterion("CZLXBM <>", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmGreaterThan(String value) {
            addCriterion("CZLXBM >", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmGreaterThanOrEqualTo(String value) {
            addCriterion("CZLXBM >=", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmLessThan(String value) {
            addCriterion("CZLXBM <", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmLessThanOrEqualTo(String value) {
            addCriterion("CZLXBM <=", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmLike(String value) {
            addCriterion("CZLXBM like", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmNotLike(String value) {
            addCriterion("CZLXBM not like", value, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmIn(List<String> values) {
            addCriterion("CZLXBM in", values, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmNotIn(List<String> values) {
            addCriterion("CZLXBM not in", values, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmBetween(String value1, String value2) {
            addCriterion("CZLXBM between", value1, value2, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andCzlxbmNotBetween(String value1, String value2) {
            addCriterion("CZLXBM not between", value1, value2, "czlxbm");
            return (Criteria) this;
        }

        public Criteria andDzmcIsNull() {
            addCriterion("DZMC is null");
            return (Criteria) this;
        }

        public Criteria andDzmcIsNotNull() {
            addCriterion("DZMC is not null");
            return (Criteria) this;
        }

        public Criteria andDzmcEqualTo(String value) {
            addCriterion("DZMC =", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcNotEqualTo(String value) {
            addCriterion("DZMC <>", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcGreaterThan(String value) {
            addCriterion("DZMC >", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcGreaterThanOrEqualTo(String value) {
            addCriterion("DZMC >=", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcLessThan(String value) {
            addCriterion("DZMC <", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcLessThanOrEqualTo(String value) {
            addCriterion("DZMC <=", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcLike(String value) {
            addCriterion("DZMC like", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcNotLike(String value) {
            addCriterion("DZMC not like", value, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcIn(List<String> values) {
            addCriterion("DZMC in", values, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcNotIn(List<String> values) {
            addCriterion("DZMC not in", values, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcBetween(String value1, String value2) {
            addCriterion("DZMC between", value1, value2, "dzmc");
            return (Criteria) this;
        }

        public Criteria andDzmcNotBetween(String value1, String value2) {
            addCriterion("DZMC not between", value1, value2, "dzmc");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("ICON is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("ICON is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("ICON =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("ICON <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("ICON >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("ICON >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("ICON <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("ICON <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("ICON like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("ICON not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("ICON in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("ICON not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("ICON between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("ICON not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andClcxIsNull() {
            addCriterion("CLCX is null");
            return (Criteria) this;
        }

        public Criteria andClcxIsNotNull() {
            addCriterion("CLCX is not null");
            return (Criteria) this;
        }

        public Criteria andClcxEqualTo(String value) {
            addCriterion("CLCX =", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxNotEqualTo(String value) {
            addCriterion("CLCX <>", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxGreaterThan(String value) {
            addCriterion("CLCX >", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxGreaterThanOrEqualTo(String value) {
            addCriterion("CLCX >=", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxLessThan(String value) {
            addCriterion("CLCX <", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxLessThanOrEqualTo(String value) {
            addCriterion("CLCX <=", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxLike(String value) {
            addCriterion("CLCX like", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxNotLike(String value) {
            addCriterion("CLCX not like", value, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxIn(List<String> values) {
            addCriterion("CLCX in", values, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxNotIn(List<String> values) {
            addCriterion("CLCX not in", values, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxBetween(String value1, String value2) {
            addCriterion("CLCX between", value1, value2, "clcx");
            return (Criteria) this;
        }

        public Criteria andClcxNotBetween(String value1, String value2) {
            addCriterion("CLCX not between", value1, value2, "clcx");
            return (Criteria) this;
        }

        public Criteria andDzxhIsNull() {
            addCriterion("DZXH is null");
            return (Criteria) this;
        }

        public Criteria andDzxhIsNotNull() {
            addCriterion("DZXH is not null");
            return (Criteria) this;
        }

        public Criteria andDzxhEqualTo(Integer value) {
            addCriterion("DZXH =", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhNotEqualTo(Integer value) {
            addCriterion("DZXH <>", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhGreaterThan(Integer value) {
            addCriterion("DZXH >", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhGreaterThanOrEqualTo(Integer value) {
            addCriterion("DZXH >=", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhLessThan(Integer value) {
            addCriterion("DZXH <", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhLessThanOrEqualTo(Integer value) {
            addCriterion("DZXH <=", value, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhIn(List<Integer> values) {
            addCriterion("DZXH in", values, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhNotIn(List<Integer> values) {
            addCriterion("DZXH not in", values, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhBetween(Integer value1, Integer value2) {
            addCriterion("DZXH between", value1, value2, "dzxh");
            return (Criteria) this;
        }

        public Criteria andDzxhNotBetween(Integer value1, Integer value2) {
            addCriterion("DZXH not between", value1, value2, "dzxh");
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

        public Criteria andXycxIsNull() {
            addCriterion("XYCX is null");
            return (Criteria) this;
        }

        public Criteria andXycxIsNotNull() {
            addCriterion("XYCX is not null");
            return (Criteria) this;
        }

        public Criteria andXycxEqualTo(String value) {
            addCriterion("XYCX =", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxNotEqualTo(String value) {
            addCriterion("XYCX <>", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxGreaterThan(String value) {
            addCriterion("XYCX >", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxGreaterThanOrEqualTo(String value) {
            addCriterion("XYCX >=", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxLessThan(String value) {
            addCriterion("XYCX <", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxLessThanOrEqualTo(String value) {
            addCriterion("XYCX <=", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxLike(String value) {
            addCriterion("XYCX like", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxNotLike(String value) {
            addCriterion("XYCX not like", value, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxIn(List<String> values) {
            addCriterion("XYCX in", values, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxNotIn(List<String> values) {
            addCriterion("XYCX not in", values, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxBetween(String value1, String value2) {
            addCriterion("XYCX between", value1, value2, "xycx");
            return (Criteria) this;
        }

        public Criteria andXycxNotBetween(String value1, String value2) {
            addCriterion("XYCX not between", value1, value2, "xycx");
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