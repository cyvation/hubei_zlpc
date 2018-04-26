package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NoticeExample() {
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

        public Criteria andBhIsNull() {
            addCriterion("BH is null");
            return (Criteria) this;
        }

        public Criteria andBhIsNotNull() {
            addCriterion("BH is not null");
            return (Criteria) this;
        }

        public Criteria andBhEqualTo(String value) {
            addCriterion("BH =", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhNotEqualTo(String value) {
            addCriterion("BH <>", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhGreaterThan(String value) {
            addCriterion("BH >", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhGreaterThanOrEqualTo(String value) {
            addCriterion("BH >=", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhLessThan(String value) {
            addCriterion("BH <", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhLessThanOrEqualTo(String value) {
            addCriterion("BH <=", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhLike(String value) {
            addCriterion("BH like", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhNotLike(String value) {
            addCriterion("BH not like", value, "bh");
            return (Criteria) this;
        }

        public Criteria andBhIn(List<String> values) {
            addCriterion("BH in", values, "bh");
            return (Criteria) this;
        }

        public Criteria andBhNotIn(List<String> values) {
            addCriterion("BH not in", values, "bh");
            return (Criteria) this;
        }

        public Criteria andBhBetween(String value1, String value2) {
            addCriterion("BH between", value1, value2, "bh");
            return (Criteria) this;
        }

        public Criteria andBhNotBetween(String value1, String value2) {
            addCriterion("BH not between", value1, value2, "bh");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmIsNull() {
            addCriterion("FBRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmIsNotNull() {
            addCriterion("FBRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmEqualTo(String value) {
            addCriterion("FBRDWBM =", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmNotEqualTo(String value) {
            addCriterion("FBRDWBM <>", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmGreaterThan(String value) {
            addCriterion("FBRDWBM >", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("FBRDWBM >=", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmLessThan(String value) {
            addCriterion("FBRDWBM <", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmLessThanOrEqualTo(String value) {
            addCriterion("FBRDWBM <=", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmLike(String value) {
            addCriterion("FBRDWBM like", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmNotLike(String value) {
            addCriterion("FBRDWBM not like", value, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmIn(List<String> values) {
            addCriterion("FBRDWBM in", values, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmNotIn(List<String> values) {
            addCriterion("FBRDWBM not in", values, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmBetween(String value1, String value2) {
            addCriterion("FBRDWBM between", value1, value2, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwbmNotBetween(String value1, String value2) {
            addCriterion("FBRDWBM not between", value1, value2, "fbrdwbm");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcIsNull() {
            addCriterion("FBRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcIsNotNull() {
            addCriterion("FBRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcEqualTo(String value) {
            addCriterion("FBRDWMC =", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcNotEqualTo(String value) {
            addCriterion("FBRDWMC <>", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcGreaterThan(String value) {
            addCriterion("FBRDWMC >", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("FBRDWMC >=", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcLessThan(String value) {
            addCriterion("FBRDWMC <", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcLessThanOrEqualTo(String value) {
            addCriterion("FBRDWMC <=", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcLike(String value) {
            addCriterion("FBRDWMC like", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcNotLike(String value) {
            addCriterion("FBRDWMC not like", value, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcIn(List<String> values) {
            addCriterion("FBRDWMC in", values, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcNotIn(List<String> values) {
            addCriterion("FBRDWMC not in", values, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcBetween(String value1, String value2) {
            addCriterion("FBRDWMC between", value1, value2, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrdwmcNotBetween(String value1, String value2) {
            addCriterion("FBRDWMC not between", value1, value2, "fbrdwmc");
            return (Criteria) this;
        }

        public Criteria andFbrghIsNull() {
            addCriterion("FBRGH is null");
            return (Criteria) this;
        }

        public Criteria andFbrghIsNotNull() {
            addCriterion("FBRGH is not null");
            return (Criteria) this;
        }

        public Criteria andFbrghEqualTo(String value) {
            addCriterion("FBRGH =", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghNotEqualTo(String value) {
            addCriterion("FBRGH <>", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghGreaterThan(String value) {
            addCriterion("FBRGH >", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghGreaterThanOrEqualTo(String value) {
            addCriterion("FBRGH >=", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghLessThan(String value) {
            addCriterion("FBRGH <", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghLessThanOrEqualTo(String value) {
            addCriterion("FBRGH <=", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghLike(String value) {
            addCriterion("FBRGH like", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghNotLike(String value) {
            addCriterion("FBRGH not like", value, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghIn(List<String> values) {
            addCriterion("FBRGH in", values, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghNotIn(List<String> values) {
            addCriterion("FBRGH not in", values, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghBetween(String value1, String value2) {
            addCriterion("FBRGH between", value1, value2, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrghNotBetween(String value1, String value2) {
            addCriterion("FBRGH not between", value1, value2, "fbrgh");
            return (Criteria) this;
        }

        public Criteria andFbrxmIsNull() {
            addCriterion("FBRXM is null");
            return (Criteria) this;
        }

        public Criteria andFbrxmIsNotNull() {
            addCriterion("FBRXM is not null");
            return (Criteria) this;
        }

        public Criteria andFbrxmEqualTo(String value) {
            addCriterion("FBRXM =", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmNotEqualTo(String value) {
            addCriterion("FBRXM <>", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmGreaterThan(String value) {
            addCriterion("FBRXM >", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmGreaterThanOrEqualTo(String value) {
            addCriterion("FBRXM >=", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmLessThan(String value) {
            addCriterion("FBRXM <", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmLessThanOrEqualTo(String value) {
            addCriterion("FBRXM <=", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmLike(String value) {
            addCriterion("FBRXM like", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmNotLike(String value) {
            addCriterion("FBRXM not like", value, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmIn(List<String> values) {
            addCriterion("FBRXM in", values, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmNotIn(List<String> values) {
            addCriterion("FBRXM not in", values, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmBetween(String value1, String value2) {
            addCriterion("FBRXM between", value1, value2, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andFbrxmNotBetween(String value1, String value2) {
            addCriterion("FBRXM not between", value1, value2, "fbrxm");
            return (Criteria) this;
        }

        public Criteria andLxdmIsNull() {
            addCriterion("LXDM is null");
            return (Criteria) this;
        }

        public Criteria andLxdmIsNotNull() {
            addCriterion("LXDM is not null");
            return (Criteria) this;
        }

        public Criteria andLxdmEqualTo(String value) {
            addCriterion("LXDM =", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmNotEqualTo(String value) {
            addCriterion("LXDM <>", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmGreaterThan(String value) {
            addCriterion("LXDM >", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmGreaterThanOrEqualTo(String value) {
            addCriterion("LXDM >=", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmLessThan(String value) {
            addCriterion("LXDM <", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmLessThanOrEqualTo(String value) {
            addCriterion("LXDM <=", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmLike(String value) {
            addCriterion("LXDM like", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmNotLike(String value) {
            addCriterion("LXDM not like", value, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmIn(List<String> values) {
            addCriterion("LXDM in", values, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmNotIn(List<String> values) {
            addCriterion("LXDM not in", values, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmBetween(String value1, String value2) {
            addCriterion("LXDM between", value1, value2, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxdmNotBetween(String value1, String value2) {
            addCriterion("LXDM not between", value1, value2, "lxdm");
            return (Criteria) this;
        }

        public Criteria andLxmcIsNull() {
            addCriterion("LXMC is null");
            return (Criteria) this;
        }

        public Criteria andLxmcIsNotNull() {
            addCriterion("LXMC is not null");
            return (Criteria) this;
        }

        public Criteria andLxmcEqualTo(String value) {
            addCriterion("LXMC =", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcNotEqualTo(String value) {
            addCriterion("LXMC <>", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcGreaterThan(String value) {
            addCriterion("LXMC >", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcGreaterThanOrEqualTo(String value) {
            addCriterion("LXMC >=", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcLessThan(String value) {
            addCriterion("LXMC <", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcLessThanOrEqualTo(String value) {
            addCriterion("LXMC <=", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcLike(String value) {
            addCriterion("LXMC like", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcNotLike(String value) {
            addCriterion("LXMC not like", value, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcIn(List<String> values) {
            addCriterion("LXMC in", values, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcNotIn(List<String> values) {
            addCriterion("LXMC not in", values, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcBetween(String value1, String value2) {
            addCriterion("LXMC between", value1, value2, "lxmc");
            return (Criteria) this;
        }

        public Criteria andLxmcNotBetween(String value1, String value2) {
            addCriterion("LXMC not between", value1, value2, "lxmc");
            return (Criteria) this;
        }

        public Criteria andBtIsNull() {
            addCriterion("BT is null");
            return (Criteria) this;
        }

        public Criteria andBtIsNotNull() {
            addCriterion("BT is not null");
            return (Criteria) this;
        }

        public Criteria andBtEqualTo(String value) {
            addCriterion("BT =", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtNotEqualTo(String value) {
            addCriterion("BT <>", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtGreaterThan(String value) {
            addCriterion("BT >", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtGreaterThanOrEqualTo(String value) {
            addCriterion("BT >=", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtLessThan(String value) {
            addCriterion("BT <", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtLessThanOrEqualTo(String value) {
            addCriterion("BT <=", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtLike(String value) {
            addCriterion("BT like", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtNotLike(String value) {
            addCriterion("BT not like", value, "bt");
            return (Criteria) this;
        }

        public Criteria andBtIn(List<String> values) {
            addCriterion("BT in", values, "bt");
            return (Criteria) this;
        }

        public Criteria andBtNotIn(List<String> values) {
            addCriterion("BT not in", values, "bt");
            return (Criteria) this;
        }

        public Criteria andBtBetween(String value1, String value2) {
            addCriterion("BT between", value1, value2, "bt");
            return (Criteria) this;
        }

        public Criteria andBtNotBetween(String value1, String value2) {
            addCriterion("BT not between", value1, value2, "bt");
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

        public Criteria andGhsjIsNull() {
            addCriterion("GHSJ is null");
            return (Criteria) this;
        }

        public Criteria andGhsjIsNotNull() {
            addCriterion("GHSJ is not null");
            return (Criteria) this;
        }

        public Criteria andGhsjEqualTo(Date value) {
            addCriterion("GHSJ =", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjNotEqualTo(Date value) {
            addCriterion("GHSJ <>", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjGreaterThan(Date value) {
            addCriterion("GHSJ >", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjGreaterThanOrEqualTo(Date value) {
            addCriterion("GHSJ >=", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjLessThan(Date value) {
            addCriterion("GHSJ <", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjLessThanOrEqualTo(Date value) {
            addCriterion("GHSJ <=", value, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjIn(List<Date> values) {
            addCriterion("GHSJ in", values, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjNotIn(List<Date> values) {
            addCriterion("GHSJ not in", values, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjBetween(Date value1, Date value2) {
            addCriterion("GHSJ between", value1, value2, "ghsj");
            return (Criteria) this;
        }

        public Criteria andGhsjNotBetween(Date value1, Date value2) {
            addCriterion("GHSJ not between", value1, value2, "ghsj");
            return (Criteria) this;
        }

        public Criteria andSfjykjIsNull() {
            addCriterion("SFJYKJ is null");
            return (Criteria) this;
        }

        public Criteria andSfjykjIsNotNull() {
            addCriterion("SFJYKJ is not null");
            return (Criteria) this;
        }

        public Criteria andSfjykjEqualTo(String value) {
            addCriterion("SFJYKJ =", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjNotEqualTo(String value) {
            addCriterion("SFJYKJ <>", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjGreaterThan(String value) {
            addCriterion("SFJYKJ >", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjGreaterThanOrEqualTo(String value) {
            addCriterion("SFJYKJ >=", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjLessThan(String value) {
            addCriterion("SFJYKJ <", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjLessThanOrEqualTo(String value) {
            addCriterion("SFJYKJ <=", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjLike(String value) {
            addCriterion("SFJYKJ like", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjNotLike(String value) {
            addCriterion("SFJYKJ not like", value, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjIn(List<String> values) {
            addCriterion("SFJYKJ in", values, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjNotIn(List<String> values) {
            addCriterion("SFJYKJ not in", values, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjBetween(String value1, String value2) {
            addCriterion("SFJYKJ between", value1, value2, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andSfjykjNotBetween(String value1, String value2) {
            addCriterion("SFJYKJ not between", value1, value2, "sfjykj");
            return (Criteria) this;
        }

        public Criteria andFjIsNull() {
            addCriterion("FJ is null");
            return (Criteria) this;
        }

        public Criteria andFjIsNotNull() {
            addCriterion("FJ is not null");
            return (Criteria) this;
        }

        public Criteria andFjEqualTo(String value) {
            addCriterion("FJ =", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotEqualTo(String value) {
            addCriterion("FJ <>", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThan(String value) {
            addCriterion("FJ >", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThanOrEqualTo(String value) {
            addCriterion("FJ >=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThan(String value) {
            addCriterion("FJ <", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThanOrEqualTo(String value) {
            addCriterion("FJ <=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLike(String value) {
            addCriterion("FJ like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotLike(String value) {
            addCriterion("FJ not like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjIn(List<String> values) {
            addCriterion("FJ in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotIn(List<String> values) {
            addCriterion("FJ not in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjBetween(String value1, String value2) {
            addCriterion("FJ between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotBetween(String value1, String value2) {
            addCriterion("FJ not between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andZlmcIsNull() {
            addCriterion("ZLMC is null");
            return (Criteria) this;
        }

        public Criteria andZlmcIsNotNull() {
            addCriterion("ZLMC is not null");
            return (Criteria) this;
        }

        public Criteria andZlmcEqualTo(String value) {
            addCriterion("ZLMC =", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcNotEqualTo(String value) {
            addCriterion("ZLMC <>", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcGreaterThan(String value) {
            addCriterion("ZLMC >", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcGreaterThanOrEqualTo(String value) {
            addCriterion("ZLMC >=", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcLessThan(String value) {
            addCriterion("ZLMC <", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcLessThanOrEqualTo(String value) {
            addCriterion("ZLMC <=", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcLike(String value) {
            addCriterion("ZLMC like", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcNotLike(String value) {
            addCriterion("ZLMC not like", value, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcIn(List<String> values) {
            addCriterion("ZLMC in", values, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcNotIn(List<String> values) {
            addCriterion("ZLMC not in", values, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcBetween(String value1, String value2) {
            addCriterion("ZLMC between", value1, value2, "zlmc");
            return (Criteria) this;
        }

        public Criteria andZlmcNotBetween(String value1, String value2) {
            addCriterion("ZLMC not between", value1, value2, "zlmc");
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