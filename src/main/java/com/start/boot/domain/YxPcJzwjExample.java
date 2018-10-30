package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YxPcJzwjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxPcJzwjExample() {
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

        public Criteria andJzwjbhIsNull() {
            addCriterion("JZWJBH is null");
            return (Criteria) this;
        }

        public Criteria andJzwjbhIsNotNull() {
            addCriterion("JZWJBH is not null");
            return (Criteria) this;
        }

        public Criteria andJzwjbhEqualTo(String value) {
            addCriterion("JZWJBH =", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhNotEqualTo(String value) {
            addCriterion("JZWJBH <>", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhGreaterThan(String value) {
            addCriterion("JZWJBH >", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhGreaterThanOrEqualTo(String value) {
            addCriterion("JZWJBH >=", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhLessThan(String value) {
            addCriterion("JZWJBH <", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhLessThanOrEqualTo(String value) {
            addCriterion("JZWJBH <=", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhLike(String value) {
            addCriterion("JZWJBH like", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhNotLike(String value) {
            addCriterion("JZWJBH not like", value, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhIn(List<String> values) {
            addCriterion("JZWJBH in", values, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhNotIn(List<String> values) {
            addCriterion("JZWJBH not in", values, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhBetween(String value1, String value2) {
            addCriterion("JZWJBH between", value1, value2, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andJzwjbhNotBetween(String value1, String value2) {
            addCriterion("JZWJBH not between", value1, value2, "jzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhIsNull() {
            addCriterion("FJZWJBH is null");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhIsNotNull() {
            addCriterion("FJZWJBH is not null");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhEqualTo(String value) {
            addCriterion("FJZWJBH =", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhNotEqualTo(String value) {
            addCriterion("FJZWJBH <>", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhGreaterThan(String value) {
            addCriterion("FJZWJBH >", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhGreaterThanOrEqualTo(String value) {
            addCriterion("FJZWJBH >=", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhLessThan(String value) {
            addCriterion("FJZWJBH <", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhLessThanOrEqualTo(String value) {
            addCriterion("FJZWJBH <=", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhLike(String value) {
            addCriterion("FJZWJBH like", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhNotLike(String value) {
            addCriterion("FJZWJBH not like", value, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhIn(List<String> values) {
            addCriterion("FJZWJBH in", values, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhNotIn(List<String> values) {
            addCriterion("FJZWJBH not in", values, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhBetween(String value1, String value2) {
            addCriterion("FJZWJBH between", value1, value2, "fjzwjbh");
            return (Criteria) this;
        }

        public Criteria andFjzwjbhNotBetween(String value1, String value2) {
            addCriterion("FJZWJBH not between", value1, value2, "fjzwjbh");
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

        public Criteria andPczybmIsNull() {
            addCriterion("PCZYBM is null");
            return (Criteria) this;
        }

        public Criteria andPczybmIsNotNull() {
            addCriterion("PCZYBM is not null");
            return (Criteria) this;
        }

        public Criteria andPczybmEqualTo(String value) {
            addCriterion("PCZYBM =", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmNotEqualTo(String value) {
            addCriterion("PCZYBM <>", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmGreaterThan(String value) {
            addCriterion("PCZYBM >", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmGreaterThanOrEqualTo(String value) {
            addCriterion("PCZYBM >=", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmLessThan(String value) {
            addCriterion("PCZYBM <", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmLessThanOrEqualTo(String value) {
            addCriterion("PCZYBM <=", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmLike(String value) {
            addCriterion("PCZYBM like", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmNotLike(String value) {
            addCriterion("PCZYBM not like", value, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmIn(List<String> values) {
            addCriterion("PCZYBM in", values, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmNotIn(List<String> values) {
            addCriterion("PCZYBM not in", values, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmBetween(String value1, String value2) {
            addCriterion("PCZYBM between", value1, value2, "pczybm");
            return (Criteria) this;
        }

        public Criteria andPczybmNotBetween(String value1, String value2) {
            addCriterion("PCZYBM not between", value1, value2, "pczybm");
            return (Criteria) this;
        }

        public Criteria andWjlxIsNull() {
            addCriterion("WJLX is null");
            return (Criteria) this;
        }

        public Criteria andWjlxIsNotNull() {
            addCriterion("WJLX is not null");
            return (Criteria) this;
        }

        public Criteria andWjlxEqualTo(String value) {
            addCriterion("WJLX =", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotEqualTo(String value) {
            addCriterion("WJLX <>", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThan(String value) {
            addCriterion("WJLX >", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxGreaterThanOrEqualTo(String value) {
            addCriterion("WJLX >=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThan(String value) {
            addCriterion("WJLX <", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLessThanOrEqualTo(String value) {
            addCriterion("WJLX <=", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxLike(String value) {
            addCriterion("WJLX like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotLike(String value) {
            addCriterion("WJLX not like", value, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxIn(List<String> values) {
            addCriterion("WJLX in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotIn(List<String> values) {
            addCriterion("WJLX not in", values, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxBetween(String value1, String value2) {
            addCriterion("WJLX between", value1, value2, "wjlx");
            return (Criteria) this;
        }

        public Criteria andWjlxNotBetween(String value1, String value2) {
            addCriterion("WJLX not between", value1, value2, "wjlx");
            return (Criteria) this;
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

        public Criteria andGxlxIsNull() {
            addCriterion("GXLX is null");
            return (Criteria) this;
        }

        public Criteria andGxlxIsNotNull() {
            addCriterion("GXLX is not null");
            return (Criteria) this;
        }

        public Criteria andGxlxEqualTo(String value) {
            addCriterion("GXLX =", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxNotEqualTo(String value) {
            addCriterion("GXLX <>", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxGreaterThan(String value) {
            addCriterion("GXLX >", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxGreaterThanOrEqualTo(String value) {
            addCriterion("GXLX >=", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxLessThan(String value) {
            addCriterion("GXLX <", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxLessThanOrEqualTo(String value) {
            addCriterion("GXLX <=", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxLike(String value) {
            addCriterion("GXLX like", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxNotLike(String value) {
            addCriterion("GXLX not like", value, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxIn(List<String> values) {
            addCriterion("GXLX in", values, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxNotIn(List<String> values) {
            addCriterion("GXLX not in", values, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxBetween(String value1, String value2) {
            addCriterion("GXLX between", value1, value2, "gxlx");
            return (Criteria) this;
        }

        public Criteria andGxlxNotBetween(String value1, String value2) {
            addCriterion("GXLX not between", value1, value2, "gxlx");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNull() {
            addCriterion("WJMC is null");
            return (Criteria) this;
        }

        public Criteria andWjmcIsNotNull() {
            addCriterion("WJMC is not null");
            return (Criteria) this;
        }

        public Criteria andWjmcEqualTo(String value) {
            addCriterion("WJMC =", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotEqualTo(String value) {
            addCriterion("WJMC <>", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThan(String value) {
            addCriterion("WJMC >", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcGreaterThanOrEqualTo(String value) {
            addCriterion("WJMC >=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThan(String value) {
            addCriterion("WJMC <", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLessThanOrEqualTo(String value) {
            addCriterion("WJMC <=", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcLike(String value) {
            addCriterion("WJMC like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotLike(String value) {
            addCriterion("WJMC not like", value, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcIn(List<String> values) {
            addCriterion("WJMC in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotIn(List<String> values) {
            addCriterion("WJMC not in", values, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcBetween(String value1, String value2) {
            addCriterion("WJMC between", value1, value2, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWjmcNotBetween(String value1, String value2) {
            addCriterion("WJMC not between", value1, value2, "wjmc");
            return (Criteria) this;
        }

        public Criteria andWscfljIsNull() {
            addCriterion("WSCFLJ is null");
            return (Criteria) this;
        }

        public Criteria andWscfljIsNotNull() {
            addCriterion("WSCFLJ is not null");
            return (Criteria) this;
        }

        public Criteria andWscfljEqualTo(String value) {
            addCriterion("WSCFLJ =", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljNotEqualTo(String value) {
            addCriterion("WSCFLJ <>", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljGreaterThan(String value) {
            addCriterion("WSCFLJ >", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljGreaterThanOrEqualTo(String value) {
            addCriterion("WSCFLJ >=", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljLessThan(String value) {
            addCriterion("WSCFLJ <", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljLessThanOrEqualTo(String value) {
            addCriterion("WSCFLJ <=", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljLike(String value) {
            addCriterion("WSCFLJ like", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljNotLike(String value) {
            addCriterion("WSCFLJ not like", value, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljIn(List<String> values) {
            addCriterion("WSCFLJ in", values, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljNotIn(List<String> values) {
            addCriterion("WSCFLJ not in", values, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljBetween(String value1, String value2) {
            addCriterion("WSCFLJ between", value1, value2, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWscfljNotBetween(String value1, String value2) {
            addCriterion("WSCFLJ not between", value1, value2, "wscflj");
            return (Criteria) this;
        }

        public Criteria andWjkzmIsNull() {
            addCriterion("WJKZM is null");
            return (Criteria) this;
        }

        public Criteria andWjkzmIsNotNull() {
            addCriterion("WJKZM is not null");
            return (Criteria) this;
        }

        public Criteria andWjkzmEqualTo(String value) {
            addCriterion("WJKZM =", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmNotEqualTo(String value) {
            addCriterion("WJKZM <>", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmGreaterThan(String value) {
            addCriterion("WJKZM >", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmGreaterThanOrEqualTo(String value) {
            addCriterion("WJKZM >=", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmLessThan(String value) {
            addCriterion("WJKZM <", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmLessThanOrEqualTo(String value) {
            addCriterion("WJKZM <=", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmLike(String value) {
            addCriterion("WJKZM like", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmNotLike(String value) {
            addCriterion("WJKZM not like", value, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmIn(List<String> values) {
            addCriterion("WJKZM in", values, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmNotIn(List<String> values) {
            addCriterion("WJKZM not in", values, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmBetween(String value1, String value2) {
            addCriterion("WJKZM between", value1, value2, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andWjkzmNotBetween(String value1, String value2) {
            addCriterion("WJKZM not between", value1, value2, "wjkzm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmIsNull() {
            addCriterion("NZRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmIsNotNull() {
            addCriterion("NZRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmEqualTo(String value) {
            addCriterion("NZRDWBM =", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmNotEqualTo(String value) {
            addCriterion("NZRDWBM <>", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmGreaterThan(String value) {
            addCriterion("NZRDWBM >", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("NZRDWBM >=", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmLessThan(String value) {
            addCriterion("NZRDWBM <", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmLessThanOrEqualTo(String value) {
            addCriterion("NZRDWBM <=", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmLike(String value) {
            addCriterion("NZRDWBM like", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmNotLike(String value) {
            addCriterion("NZRDWBM not like", value, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmIn(List<String> values) {
            addCriterion("NZRDWBM in", values, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmNotIn(List<String> values) {
            addCriterion("NZRDWBM not in", values, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmBetween(String value1, String value2) {
            addCriterion("NZRDWBM between", value1, value2, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwbmNotBetween(String value1, String value2) {
            addCriterion("NZRDWBM not between", value1, value2, "nzrdwbm");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcIsNull() {
            addCriterion("NZRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcIsNotNull() {
            addCriterion("NZRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcEqualTo(String value) {
            addCriterion("NZRDWMC =", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcNotEqualTo(String value) {
            addCriterion("NZRDWMC <>", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcGreaterThan(String value) {
            addCriterion("NZRDWMC >", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("NZRDWMC >=", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcLessThan(String value) {
            addCriterion("NZRDWMC <", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcLessThanOrEqualTo(String value) {
            addCriterion("NZRDWMC <=", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcLike(String value) {
            addCriterion("NZRDWMC like", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcNotLike(String value) {
            addCriterion("NZRDWMC not like", value, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcIn(List<String> values) {
            addCriterion("NZRDWMC in", values, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcNotIn(List<String> values) {
            addCriterion("NZRDWMC not in", values, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcBetween(String value1, String value2) {
            addCriterion("NZRDWMC between", value1, value2, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrdwmcNotBetween(String value1, String value2) {
            addCriterion("NZRDWMC not between", value1, value2, "nzrdwmc");
            return (Criteria) this;
        }

        public Criteria andNzrghIsNull() {
            addCriterion("NZRGH is null");
            return (Criteria) this;
        }

        public Criteria andNzrghIsNotNull() {
            addCriterion("NZRGH is not null");
            return (Criteria) this;
        }

        public Criteria andNzrghEqualTo(String value) {
            addCriterion("NZRGH =", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghNotEqualTo(String value) {
            addCriterion("NZRGH <>", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghGreaterThan(String value) {
            addCriterion("NZRGH >", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghGreaterThanOrEqualTo(String value) {
            addCriterion("NZRGH >=", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghLessThan(String value) {
            addCriterion("NZRGH <", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghLessThanOrEqualTo(String value) {
            addCriterion("NZRGH <=", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghLike(String value) {
            addCriterion("NZRGH like", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghNotLike(String value) {
            addCriterion("NZRGH not like", value, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghIn(List<String> values) {
            addCriterion("NZRGH in", values, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghNotIn(List<String> values) {
            addCriterion("NZRGH not in", values, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghBetween(String value1, String value2) {
            addCriterion("NZRGH between", value1, value2, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrghNotBetween(String value1, String value2) {
            addCriterion("NZRGH not between", value1, value2, "nzrgh");
            return (Criteria) this;
        }

        public Criteria andNzrxmIsNull() {
            addCriterion("NZRXM is null");
            return (Criteria) this;
        }

        public Criteria andNzrxmIsNotNull() {
            addCriterion("NZRXM is not null");
            return (Criteria) this;
        }

        public Criteria andNzrxmEqualTo(String value) {
            addCriterion("NZRXM =", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmNotEqualTo(String value) {
            addCriterion("NZRXM <>", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmGreaterThan(String value) {
            addCriterion("NZRXM >", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmGreaterThanOrEqualTo(String value) {
            addCriterion("NZRXM >=", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmLessThan(String value) {
            addCriterion("NZRXM <", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmLessThanOrEqualTo(String value) {
            addCriterion("NZRXM <=", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmLike(String value) {
            addCriterion("NZRXM like", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmNotLike(String value) {
            addCriterion("NZRXM not like", value, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmIn(List<String> values) {
            addCriterion("NZRXM in", values, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmNotIn(List<String> values) {
            addCriterion("NZRXM not in", values, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmBetween(String value1, String value2) {
            addCriterion("NZRXM between", value1, value2, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andNzrxmNotBetween(String value1, String value2) {
            addCriterion("NZRXM not between", value1, value2, "nzrxm");
            return (Criteria) this;
        }

        public Criteria andXgrqIsNull() {
            addCriterion("XGRQ is null");
            return (Criteria) this;
        }

        public Criteria andXgrqIsNotNull() {
            addCriterion("XGRQ is not null");
            return (Criteria) this;
        }

        public Criteria andXgrqEqualTo(Date value) {
            addCriterion("XGRQ =", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqNotEqualTo(Date value) {
            addCriterion("XGRQ <>", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqGreaterThan(Date value) {
            addCriterion("XGRQ >", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqGreaterThanOrEqualTo(Date value) {
            addCriterion("XGRQ >=", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqLessThan(Date value) {
            addCriterion("XGRQ <", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqLessThanOrEqualTo(Date value) {
            addCriterion("XGRQ <=", value, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqIn(List<Date> values) {
            addCriterion("XGRQ in", values, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqNotIn(List<Date> values) {
            addCriterion("XGRQ not in", values, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqBetween(Date value1, Date value2) {
            addCriterion("XGRQ between", value1, value2, "xgrq");
            return (Criteria) this;
        }

        public Criteria andXgrqNotBetween(Date value1, Date value2) {
            addCriterion("XGRQ not between", value1, value2, "xgrq");
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