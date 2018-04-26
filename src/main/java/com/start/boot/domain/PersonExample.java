package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PersonExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andZllxIsNull() {
            addCriterion("ZLLX is null");
            return (Criteria) this;
        }

        public Criteria andZllxIsNotNull() {
            addCriterion("ZLLX is not null");
            return (Criteria) this;
        }

        public Criteria andZllxEqualTo(Integer value) {
            addCriterion("ZLLX =", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxNotEqualTo(Integer value) {
            addCriterion("ZLLX <>", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxGreaterThan(Integer value) {
            addCriterion("ZLLX >", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxGreaterThanOrEqualTo(Integer value) {
            addCriterion("ZLLX >=", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxLessThan(Integer value) {
            addCriterion("ZLLX <", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxLessThanOrEqualTo(Integer value) {
            addCriterion("ZLLX <=", value, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxIn(List<Integer> values) {
            addCriterion("ZLLX in", values, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxNotIn(List<Integer> values) {
            addCriterion("ZLLX not in", values, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxBetween(Integer value1, Integer value2) {
            addCriterion("ZLLX between", value1, value2, "zllx");
            return (Criteria) this;
        }

        public Criteria andZllxNotBetween(Integer value1, Integer value2) {
            addCriterion("ZLLX not between", value1, value2, "zllx");
            return (Criteria) this;
        }

        public Criteria andGyzyIsNull() {
            addCriterion("GYZY is null");
            return (Criteria) this;
        }

        public Criteria andGyzyIsNotNull() {
            addCriterion("GYZY is not null");
            return (Criteria) this;
        }

        public Criteria andGyzyEqualTo(String value) {
            addCriterion("GYZY =", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyNotEqualTo(String value) {
            addCriterion("GYZY <>", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyGreaterThan(String value) {
            addCriterion("GYZY >", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyGreaterThanOrEqualTo(String value) {
            addCriterion("GYZY >=", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyLessThan(String value) {
            addCriterion("GYZY <", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyLessThanOrEqualTo(String value) {
            addCriterion("GYZY <=", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyLike(String value) {
            addCriterion("GYZY like", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyNotLike(String value) {
            addCriterion("GYZY not like", value, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyIn(List<String> values) {
            addCriterion("GYZY in", values, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyNotIn(List<String> values) {
            addCriterion("GYZY not in", values, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyBetween(String value1, String value2) {
            addCriterion("GYZY between", value1, value2, "gyzy");
            return (Criteria) this;
        }

        public Criteria andGyzyNotBetween(String value1, String value2) {
            addCriterion("GYZY not between", value1, value2, "gyzy");
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

        public Criteria andGxbmIsNull() {
            addCriterion("GXBM is null");
            return (Criteria) this;
        }

        public Criteria andGxbmIsNotNull() {
            addCriterion("GXBM is not null");
            return (Criteria) this;
        }

        public Criteria andGxbmEqualTo(String value) {
            addCriterion("GXBM =", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmNotEqualTo(String value) {
            addCriterion("GXBM <>", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmGreaterThan(String value) {
            addCriterion("GXBM >", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmGreaterThanOrEqualTo(String value) {
            addCriterion("GXBM >=", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmLessThan(String value) {
            addCriterion("GXBM <", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmLessThanOrEqualTo(String value) {
            addCriterion("GXBM <=", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmLike(String value) {
            addCriterion("GXBM like", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmNotLike(String value) {
            addCriterion("GXBM not like", value, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmIn(List<String> values) {
            addCriterion("GXBM in", values, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmNotIn(List<String> values) {
            addCriterion("GXBM not in", values, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmBetween(String value1, String value2) {
            addCriterion("GXBM between", value1, value2, "gxbm");
            return (Criteria) this;
        }

        public Criteria andGxbmNotBetween(String value1, String value2) {
            addCriterion("GXBM not between", value1, value2, "gxbm");
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