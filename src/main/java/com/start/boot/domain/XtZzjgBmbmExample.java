package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XtZzjgBmbmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XtZzjgBmbmExample() {
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

        public Criteria andFbmbmIsNull() {
            addCriterion("FBMBM is null");
            return (Criteria) this;
        }

        public Criteria andFbmbmIsNotNull() {
            addCriterion("FBMBM is not null");
            return (Criteria) this;
        }

        public Criteria andFbmbmEqualTo(String value) {
            addCriterion("FBMBM =", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmNotEqualTo(String value) {
            addCriterion("FBMBM <>", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmGreaterThan(String value) {
            addCriterion("FBMBM >", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmGreaterThanOrEqualTo(String value) {
            addCriterion("FBMBM >=", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmLessThan(String value) {
            addCriterion("FBMBM <", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmLessThanOrEqualTo(String value) {
            addCriterion("FBMBM <=", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmLike(String value) {
            addCriterion("FBMBM like", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmNotLike(String value) {
            addCriterion("FBMBM not like", value, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmIn(List<String> values) {
            addCriterion("FBMBM in", values, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmNotIn(List<String> values) {
            addCriterion("FBMBM not in", values, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmBetween(String value1, String value2) {
            addCriterion("FBMBM between", value1, value2, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andFbmbmNotBetween(String value1, String value2) {
            addCriterion("FBMBM not between", value1, value2, "fbmbm");
            return (Criteria) this;
        }

        public Criteria andBmmcIsNull() {
            addCriterion("BMMC is null");
            return (Criteria) this;
        }

        public Criteria andBmmcIsNotNull() {
            addCriterion("BMMC is not null");
            return (Criteria) this;
        }

        public Criteria andBmmcEqualTo(String value) {
            addCriterion("BMMC =", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotEqualTo(String value) {
            addCriterion("BMMC <>", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcGreaterThan(String value) {
            addCriterion("BMMC >", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcGreaterThanOrEqualTo(String value) {
            addCriterion("BMMC >=", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLessThan(String value) {
            addCriterion("BMMC <", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLessThanOrEqualTo(String value) {
            addCriterion("BMMC <=", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcLike(String value) {
            addCriterion("BMMC like", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotLike(String value) {
            addCriterion("BMMC not like", value, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcIn(List<String> values) {
            addCriterion("BMMC in", values, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotIn(List<String> values) {
            addCriterion("BMMC not in", values, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcBetween(String value1, String value2) {
            addCriterion("BMMC between", value1, value2, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmmcNotBetween(String value1, String value2) {
            addCriterion("BMMC not between", value1, value2, "bmmc");
            return (Criteria) this;
        }

        public Criteria andBmjcIsNull() {
            addCriterion("BMJC is null");
            return (Criteria) this;
        }

        public Criteria andBmjcIsNotNull() {
            addCriterion("BMJC is not null");
            return (Criteria) this;
        }

        public Criteria andBmjcEqualTo(String value) {
            addCriterion("BMJC =", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcNotEqualTo(String value) {
            addCriterion("BMJC <>", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcGreaterThan(String value) {
            addCriterion("BMJC >", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcGreaterThanOrEqualTo(String value) {
            addCriterion("BMJC >=", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcLessThan(String value) {
            addCriterion("BMJC <", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcLessThanOrEqualTo(String value) {
            addCriterion("BMJC <=", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcLike(String value) {
            addCriterion("BMJC like", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcNotLike(String value) {
            addCriterion("BMJC not like", value, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcIn(List<String> values) {
            addCriterion("BMJC in", values, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcNotIn(List<String> values) {
            addCriterion("BMJC not in", values, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcBetween(String value1, String value2) {
            addCriterion("BMJC between", value1, value2, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmjcNotBetween(String value1, String value2) {
            addCriterion("BMJC not between", value1, value2, "bmjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcIsNull() {
            addCriterion("BMWHJC is null");
            return (Criteria) this;
        }

        public Criteria andBmwhjcIsNotNull() {
            addCriterion("BMWHJC is not null");
            return (Criteria) this;
        }

        public Criteria andBmwhjcEqualTo(String value) {
            addCriterion("BMWHJC =", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcNotEqualTo(String value) {
            addCriterion("BMWHJC <>", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcGreaterThan(String value) {
            addCriterion("BMWHJC >", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcGreaterThanOrEqualTo(String value) {
            addCriterion("BMWHJC >=", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcLessThan(String value) {
            addCriterion("BMWHJC <", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcLessThanOrEqualTo(String value) {
            addCriterion("BMWHJC <=", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcLike(String value) {
            addCriterion("BMWHJC like", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcNotLike(String value) {
            addCriterion("BMWHJC not like", value, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcIn(List<String> values) {
            addCriterion("BMWHJC in", values, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcNotIn(List<String> values) {
            addCriterion("BMWHJC not in", values, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcBetween(String value1, String value2) {
            addCriterion("BMWHJC between", value1, value2, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmwhjcNotBetween(String value1, String value2) {
            addCriterion("BMWHJC not between", value1, value2, "bmwhjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcIsNull() {
            addCriterion("BMAHJC is null");
            return (Criteria) this;
        }

        public Criteria andBmahjcIsNotNull() {
            addCriterion("BMAHJC is not null");
            return (Criteria) this;
        }

        public Criteria andBmahjcEqualTo(String value) {
            addCriterion("BMAHJC =", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcNotEqualTo(String value) {
            addCriterion("BMAHJC <>", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcGreaterThan(String value) {
            addCriterion("BMAHJC >", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcGreaterThanOrEqualTo(String value) {
            addCriterion("BMAHJC >=", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcLessThan(String value) {
            addCriterion("BMAHJC <", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcLessThanOrEqualTo(String value) {
            addCriterion("BMAHJC <=", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcLike(String value) {
            addCriterion("BMAHJC like", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcNotLike(String value) {
            addCriterion("BMAHJC not like", value, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcIn(List<String> values) {
            addCriterion("BMAHJC in", values, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcNotIn(List<String> values) {
            addCriterion("BMAHJC not in", values, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcBetween(String value1, String value2) {
            addCriterion("BMAHJC between", value1, value2, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andBmahjcNotBetween(String value1, String value2) {
            addCriterion("BMAHJC not between", value1, value2, "bmahjc");
            return (Criteria) this;
        }

        public Criteria andSflsjgIsNull() {
            addCriterion("SFLSJG is null");
            return (Criteria) this;
        }

        public Criteria andSflsjgIsNotNull() {
            addCriterion("SFLSJG is not null");
            return (Criteria) this;
        }

        public Criteria andSflsjgEqualTo(String value) {
            addCriterion("SFLSJG =", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgNotEqualTo(String value) {
            addCriterion("SFLSJG <>", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgGreaterThan(String value) {
            addCriterion("SFLSJG >", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgGreaterThanOrEqualTo(String value) {
            addCriterion("SFLSJG >=", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgLessThan(String value) {
            addCriterion("SFLSJG <", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgLessThanOrEqualTo(String value) {
            addCriterion("SFLSJG <=", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgLike(String value) {
            addCriterion("SFLSJG like", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgNotLike(String value) {
            addCriterion("SFLSJG not like", value, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgIn(List<String> values) {
            addCriterion("SFLSJG in", values, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgNotIn(List<String> values) {
            addCriterion("SFLSJG not in", values, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgBetween(String value1, String value2) {
            addCriterion("SFLSJG between", value1, value2, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSflsjgNotBetween(String value1, String value2) {
            addCriterion("SFLSJG not between", value1, value2, "sflsjg");
            return (Criteria) this;
        }

        public Criteria andSfcbbmIsNull() {
            addCriterion("SFCBBM is null");
            return (Criteria) this;
        }

        public Criteria andSfcbbmIsNotNull() {
            addCriterion("SFCBBM is not null");
            return (Criteria) this;
        }

        public Criteria andSfcbbmEqualTo(String value) {
            addCriterion("SFCBBM =", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmNotEqualTo(String value) {
            addCriterion("SFCBBM <>", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmGreaterThan(String value) {
            addCriterion("SFCBBM >", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmGreaterThanOrEqualTo(String value) {
            addCriterion("SFCBBM >=", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmLessThan(String value) {
            addCriterion("SFCBBM <", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmLessThanOrEqualTo(String value) {
            addCriterion("SFCBBM <=", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmLike(String value) {
            addCriterion("SFCBBM like", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmNotLike(String value) {
            addCriterion("SFCBBM not like", value, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmIn(List<String> values) {
            addCriterion("SFCBBM in", values, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmNotIn(List<String> values) {
            addCriterion("SFCBBM not in", values, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmBetween(String value1, String value2) {
            addCriterion("SFCBBM between", value1, value2, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andSfcbbmNotBetween(String value1, String value2) {
            addCriterion("SFCBBM not between", value1, value2, "sfcbbm");
            return (Criteria) this;
        }

        public Criteria andBmxhIsNull() {
            addCriterion("BMXH is null");
            return (Criteria) this;
        }

        public Criteria andBmxhIsNotNull() {
            addCriterion("BMXH is not null");
            return (Criteria) this;
        }

        public Criteria andBmxhEqualTo(BigDecimal value) {
            addCriterion("BMXH =", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhNotEqualTo(BigDecimal value) {
            addCriterion("BMXH <>", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhGreaterThan(BigDecimal value) {
            addCriterion("BMXH >", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BMXH >=", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhLessThan(BigDecimal value) {
            addCriterion("BMXH <", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BMXH <=", value, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhIn(List<BigDecimal> values) {
            addCriterion("BMXH in", values, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhNotIn(List<BigDecimal> values) {
            addCriterion("BMXH not in", values, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BMXH between", value1, value2, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBmxhNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BMXH not between", value1, value2, "bmxh");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("BZ is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("BZ is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("BZ like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("BZ not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("BZ in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("BZ not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");
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

        public Criteria andBmysIsNull() {
            addCriterion("BMYS is null");
            return (Criteria) this;
        }

        public Criteria andBmysIsNotNull() {
            addCriterion("BMYS is not null");
            return (Criteria) this;
        }

        public Criteria andBmysEqualTo(String value) {
            addCriterion("BMYS =", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysNotEqualTo(String value) {
            addCriterion("BMYS <>", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysGreaterThan(String value) {
            addCriterion("BMYS >", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysGreaterThanOrEqualTo(String value) {
            addCriterion("BMYS >=", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysLessThan(String value) {
            addCriterion("BMYS <", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysLessThanOrEqualTo(String value) {
            addCriterion("BMYS <=", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysLike(String value) {
            addCriterion("BMYS like", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysNotLike(String value) {
            addCriterion("BMYS not like", value, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysIn(List<String> values) {
            addCriterion("BMYS in", values, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysNotIn(List<String> values) {
            addCriterion("BMYS not in", values, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysBetween(String value1, String value2) {
            addCriterion("BMYS between", value1, value2, "bmys");
            return (Criteria) this;
        }

        public Criteria andBmysNotBetween(String value1, String value2) {
            addCriterion("BMYS not between", value1, value2, "bmys");
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