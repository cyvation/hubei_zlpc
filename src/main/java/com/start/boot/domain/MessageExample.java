package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
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

        public Criteria andXxxhIsNull() {
            addCriterion("XXXH is null");
            return (Criteria) this;
        }

        public Criteria andXxxhIsNotNull() {
            addCriterion("XXXH is not null");
            return (Criteria) this;
        }

        public Criteria andXxxhEqualTo(String value) {
            addCriterion("XXXH =", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhNotEqualTo(String value) {
            addCriterion("XXXH <>", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhGreaterThan(String value) {
            addCriterion("XXXH >", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhGreaterThanOrEqualTo(String value) {
            addCriterion("XXXH >=", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhLessThan(String value) {
            addCriterion("XXXH <", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhLessThanOrEqualTo(String value) {
            addCriterion("XXXH <=", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhLike(String value) {
            addCriterion("XXXH like", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhNotLike(String value) {
            addCriterion("XXXH not like", value, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhIn(List<String> values) {
            addCriterion("XXXH in", values, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhNotIn(List<String> values) {
            addCriterion("XXXH not in", values, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhBetween(String value1, String value2) {
            addCriterion("XXXH between", value1, value2, "xxxh");
            return (Criteria) this;
        }

        public Criteria andXxxhNotBetween(String value1, String value2) {
            addCriterion("XXXH not between", value1, value2, "xxxh");
            return (Criteria) this;
        }

        public Criteria andFsrqIsNull() {
            addCriterion("FSRQ is null");
            return (Criteria) this;
        }

        public Criteria andFsrqIsNotNull() {
            addCriterion("FSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andFsrqEqualTo(Date value) {
            addCriterion("FSRQ =", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqNotEqualTo(Date value) {
            addCriterion("FSRQ <>", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqGreaterThan(Date value) {
            addCriterion("FSRQ >", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqGreaterThanOrEqualTo(Date value) {
            addCriterion("FSRQ >=", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqLessThan(Date value) {
            addCriterion("FSRQ <", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqLessThanOrEqualTo(Date value) {
            addCriterion("FSRQ <=", value, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqIn(List<Date> values) {
            addCriterion("FSRQ in", values, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqNotIn(List<Date> values) {
            addCriterion("FSRQ not in", values, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqBetween(Date value1, Date value2) {
            addCriterion("FSRQ between", value1, value2, "fsrq");
            return (Criteria) this;
        }

        public Criteria andFsrqNotBetween(Date value1, Date value2) {
            addCriterion("FSRQ not between", value1, value2, "fsrq");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmIsNull() {
            addCriterion("JSRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmIsNotNull() {
            addCriterion("JSRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmEqualTo(String value) {
            addCriterion("JSRDWBM =", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmNotEqualTo(String value) {
            addCriterion("JSRDWBM <>", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmGreaterThan(String value) {
            addCriterion("JSRDWBM >", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("JSRDWBM >=", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmLessThan(String value) {
            addCriterion("JSRDWBM <", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmLessThanOrEqualTo(String value) {
            addCriterion("JSRDWBM <=", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmLike(String value) {
            addCriterion("JSRDWBM like", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmNotLike(String value) {
            addCriterion("JSRDWBM not like", value, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmIn(List<String> values) {
            addCriterion("JSRDWBM in", values, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmNotIn(List<String> values) {
            addCriterion("JSRDWBM not in", values, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmBetween(String value1, String value2) {
            addCriterion("JSRDWBM between", value1, value2, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrdwbmNotBetween(String value1, String value2) {
            addCriterion("JSRDWBM not between", value1, value2, "jsrdwbm");
            return (Criteria) this;
        }

        public Criteria andJsrghIsNull() {
            addCriterion("JSRGH is null");
            return (Criteria) this;
        }

        public Criteria andJsrghIsNotNull() {
            addCriterion("JSRGH is not null");
            return (Criteria) this;
        }

        public Criteria andJsrghEqualTo(String value) {
            addCriterion("JSRGH =", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghNotEqualTo(String value) {
            addCriterion("JSRGH <>", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghGreaterThan(String value) {
            addCriterion("JSRGH >", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghGreaterThanOrEqualTo(String value) {
            addCriterion("JSRGH >=", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghLessThan(String value) {
            addCriterion("JSRGH <", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghLessThanOrEqualTo(String value) {
            addCriterion("JSRGH <=", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghLike(String value) {
            addCriterion("JSRGH like", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghNotLike(String value) {
            addCriterion("JSRGH not like", value, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghIn(List<String> values) {
            addCriterion("JSRGH in", values, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghNotIn(List<String> values) {
            addCriterion("JSRGH not in", values, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghBetween(String value1, String value2) {
            addCriterion("JSRGH between", value1, value2, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andJsrghNotBetween(String value1, String value2) {
            addCriterion("JSRGH not between", value1, value2, "jsrgh");
            return (Criteria) this;
        }

        public Criteria andXxlxIsNull() {
            addCriterion("XXLX is null");
            return (Criteria) this;
        }

        public Criteria andXxlxIsNotNull() {
            addCriterion("XXLX is not null");
            return (Criteria) this;
        }

        public Criteria andXxlxEqualTo(String value) {
            addCriterion("XXLX =", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotEqualTo(String value) {
            addCriterion("XXLX <>", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxGreaterThan(String value) {
            addCriterion("XXLX >", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxGreaterThanOrEqualTo(String value) {
            addCriterion("XXLX >=", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxLessThan(String value) {
            addCriterion("XXLX <", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxLessThanOrEqualTo(String value) {
            addCriterion("XXLX <=", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxLike(String value) {
            addCriterion("XXLX like", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotLike(String value) {
            addCriterion("XXLX not like", value, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxIn(List<String> values) {
            addCriterion("XXLX in", values, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotIn(List<String> values) {
            addCriterion("XXLX not in", values, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxBetween(String value1, String value2) {
            addCriterion("XXLX between", value1, value2, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxlxNotBetween(String value1, String value2) {
            addCriterion("XXLX not between", value1, value2, "xxlx");
            return (Criteria) this;
        }

        public Criteria andXxbtIsNull() {
            addCriterion("XXBT is null");
            return (Criteria) this;
        }

        public Criteria andXxbtIsNotNull() {
            addCriterion("XXBT is not null");
            return (Criteria) this;
        }

        public Criteria andXxbtEqualTo(String value) {
            addCriterion("XXBT =", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtNotEqualTo(String value) {
            addCriterion("XXBT <>", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtGreaterThan(String value) {
            addCriterion("XXBT >", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtGreaterThanOrEqualTo(String value) {
            addCriterion("XXBT >=", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtLessThan(String value) {
            addCriterion("XXBT <", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtLessThanOrEqualTo(String value) {
            addCriterion("XXBT <=", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtLike(String value) {
            addCriterion("XXBT like", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtNotLike(String value) {
            addCriterion("XXBT not like", value, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtIn(List<String> values) {
            addCriterion("XXBT in", values, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtNotIn(List<String> values) {
            addCriterion("XXBT not in", values, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtBetween(String value1, String value2) {
            addCriterion("XXBT between", value1, value2, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxbtNotBetween(String value1, String value2) {
            addCriterion("XXBT not between", value1, value2, "xxbt");
            return (Criteria) this;
        }

        public Criteria andXxnrIsNull() {
            addCriterion("XXNR is null");
            return (Criteria) this;
        }

        public Criteria andXxnrIsNotNull() {
            addCriterion("XXNR is not null");
            return (Criteria) this;
        }

        public Criteria andXxnrEqualTo(String value) {
            addCriterion("XXNR =", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrNotEqualTo(String value) {
            addCriterion("XXNR <>", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrGreaterThan(String value) {
            addCriterion("XXNR >", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrGreaterThanOrEqualTo(String value) {
            addCriterion("XXNR >=", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrLessThan(String value) {
            addCriterion("XXNR <", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrLessThanOrEqualTo(String value) {
            addCriterion("XXNR <=", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrLike(String value) {
            addCriterion("XXNR like", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrNotLike(String value) {
            addCriterion("XXNR not like", value, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrIn(List<String> values) {
            addCriterion("XXNR in", values, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrNotIn(List<String> values) {
            addCriterion("XXNR not in", values, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrBetween(String value1, String value2) {
            addCriterion("XXNR between", value1, value2, "xxnr");
            return (Criteria) this;
        }

        public Criteria andXxnrNotBetween(String value1, String value2) {
            addCriterion("XXNR not between", value1, value2, "xxnr");
            return (Criteria) this;
        }

        public Criteria andGlbmsahIsNull() {
            addCriterion("GLBMSAH is null");
            return (Criteria) this;
        }

        public Criteria andGlbmsahIsNotNull() {
            addCriterion("GLBMSAH is not null");
            return (Criteria) this;
        }

        public Criteria andGlbmsahEqualTo(String value) {
            addCriterion("GLBMSAH =", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahNotEqualTo(String value) {
            addCriterion("GLBMSAH <>", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahGreaterThan(String value) {
            addCriterion("GLBMSAH >", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahGreaterThanOrEqualTo(String value) {
            addCriterion("GLBMSAH >=", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahLessThan(String value) {
            addCriterion("GLBMSAH <", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahLessThanOrEqualTo(String value) {
            addCriterion("GLBMSAH <=", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahLike(String value) {
            addCriterion("GLBMSAH like", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahNotLike(String value) {
            addCriterion("GLBMSAH not like", value, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahIn(List<String> values) {
            addCriterion("GLBMSAH in", values, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahNotIn(List<String> values) {
            addCriterion("GLBMSAH not in", values, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahBetween(String value1, String value2) {
            addCriterion("GLBMSAH between", value1, value2, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andGlbmsahNotBetween(String value1, String value2) {
            addCriterion("GLBMSAH not between", value1, value2, "glbmsah");
            return (Criteria) this;
        }

        public Criteria andXxztIsNull() {
            addCriterion("XXZT is null");
            return (Criteria) this;
        }

        public Criteria andXxztIsNotNull() {
            addCriterion("XXZT is not null");
            return (Criteria) this;
        }

        public Criteria andXxztEqualTo(String value) {
            addCriterion("XXZT =", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztNotEqualTo(String value) {
            addCriterion("XXZT <>", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztGreaterThan(String value) {
            addCriterion("XXZT >", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztGreaterThanOrEqualTo(String value) {
            addCriterion("XXZT >=", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztLessThan(String value) {
            addCriterion("XXZT <", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztLessThanOrEqualTo(String value) {
            addCriterion("XXZT <=", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztLike(String value) {
            addCriterion("XXZT like", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztNotLike(String value) {
            addCriterion("XXZT not like", value, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztIn(List<String> values) {
            addCriterion("XXZT in", values, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztNotIn(List<String> values) {
            addCriterion("XXZT not in", values, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztBetween(String value1, String value2) {
            addCriterion("XXZT between", value1, value2, "xxzt");
            return (Criteria) this;
        }

        public Criteria andXxztNotBetween(String value1, String value2) {
            addCriterion("XXZT not between", value1, value2, "xxzt");
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

        public Criteria andSffsIsNull() {
            addCriterion("SFFS is null");
            return (Criteria) this;
        }

        public Criteria andSffsIsNotNull() {
            addCriterion("SFFS is not null");
            return (Criteria) this;
        }

        public Criteria andSffsEqualTo(String value) {
            addCriterion("SFFS =", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsNotEqualTo(String value) {
            addCriterion("SFFS <>", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsGreaterThan(String value) {
            addCriterion("SFFS >", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsGreaterThanOrEqualTo(String value) {
            addCriterion("SFFS >=", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsLessThan(String value) {
            addCriterion("SFFS <", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsLessThanOrEqualTo(String value) {
            addCriterion("SFFS <=", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsLike(String value) {
            addCriterion("SFFS like", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsNotLike(String value) {
            addCriterion("SFFS not like", value, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsIn(List<String> values) {
            addCriterion("SFFS in", values, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsNotIn(List<String> values) {
            addCriterion("SFFS not in", values, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsBetween(String value1, String value2) {
            addCriterion("SFFS between", value1, value2, "sffs");
            return (Criteria) this;
        }

        public Criteria andSffsNotBetween(String value1, String value2) {
            addCriterion("SFFS not between", value1, value2, "sffs");
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