package com.start.boot.domain;

import java.util.ArrayList;
import java.util.List;

public class AdvancedQueryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvancedQueryExample() {
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

        public Criteria andLxIsNull() {
            addCriterion("LX is null");
            return (Criteria) this;
        }

        public Criteria andLxIsNotNull() {
            addCriterion("LX is not null");
            return (Criteria) this;
        }

        public Criteria andLxEqualTo(String value) {
            addCriterion("LX =", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotEqualTo(String value) {
            addCriterion("LX <>", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThan(String value) {
            addCriterion("LX >", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxGreaterThanOrEqualTo(String value) {
            addCriterion("LX >=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThan(String value) {
            addCriterion("LX <", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLessThanOrEqualTo(String value) {
            addCriterion("LX <=", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxLike(String value) {
            addCriterion("LX like", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotLike(String value) {
            addCriterion("LX not like", value, "lx");
            return (Criteria) this;
        }

        public Criteria andLxIn(List<String> values) {
            addCriterion("LX in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotIn(List<String> values) {
            addCriterion("LX not in", values, "lx");
            return (Criteria) this;
        }

        public Criteria andLxBetween(String value1, String value2) {
            addCriterion("LX between", value1, value2, "lx");
            return (Criteria) this;
        }

        public Criteria andLxNotBetween(String value1, String value2) {
            addCriterion("LX not between", value1, value2, "lx");
            return (Criteria) this;
        }

        public Criteria andYsfIsNull() {
            addCriterion("YSF is null");
            return (Criteria) this;
        }

        public Criteria andYsfIsNotNull() {
            addCriterion("YSF is not null");
            return (Criteria) this;
        }

        public Criteria andYsfEqualTo(String value) {
            addCriterion("YSF =", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfNotEqualTo(String value) {
            addCriterion("YSF <>", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfGreaterThan(String value) {
            addCriterion("YSF >", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfGreaterThanOrEqualTo(String value) {
            addCriterion("YSF >=", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfLessThan(String value) {
            addCriterion("YSF <", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfLessThanOrEqualTo(String value) {
            addCriterion("YSF <=", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfLike(String value) {
            addCriterion("YSF like", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfNotLike(String value) {
            addCriterion("YSF not like", value, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfIn(List<String> values) {
            addCriterion("YSF in", values, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfNotIn(List<String> values) {
            addCriterion("YSF not in", values, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfBetween(String value1, String value2) {
            addCriterion("YSF between", value1, value2, "ysf");
            return (Criteria) this;
        }

        public Criteria andYsfNotBetween(String value1, String value2) {
            addCriterion("YSF not between", value1, value2, "ysf");
            return (Criteria) this;
        }

        public Criteria andCxIsNull() {
            addCriterion("CX is null");
            return (Criteria) this;
        }

        public Criteria andCxIsNotNull() {
            addCriterion("CX is not null");
            return (Criteria) this;
        }

        public Criteria andCxEqualTo(String value) {
            addCriterion("CX =", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotEqualTo(String value) {
            addCriterion("CX <>", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxGreaterThan(String value) {
            addCriterion("CX >", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxGreaterThanOrEqualTo(String value) {
            addCriterion("CX >=", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLessThan(String value) {
            addCriterion("CX <", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLessThanOrEqualTo(String value) {
            addCriterion("CX <=", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxLike(String value) {
            addCriterion("CX like", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotLike(String value) {
            addCriterion("CX not like", value, "cx");
            return (Criteria) this;
        }

        public Criteria andCxIn(List<String> values) {
            addCriterion("CX in", values, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotIn(List<String> values) {
            addCriterion("CX not in", values, "cx");
            return (Criteria) this;
        }

        public Criteria andCxBetween(String value1, String value2) {
            addCriterion("CX between", value1, value2, "cx");
            return (Criteria) this;
        }

        public Criteria andCxNotBetween(String value1, String value2) {
            addCriterion("CX not between", value1, value2, "cx");
            return (Criteria) this;
        }

        public Criteria andCsIsNull() {
            addCriterion("CS is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("CS is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(String value) {
            addCriterion("CS =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(String value) {
            addCriterion("CS <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(String value) {
            addCriterion("CS >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(String value) {
            addCriterion("CS >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(String value) {
            addCriterion("CS <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(String value) {
            addCriterion("CS <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLike(String value) {
            addCriterion("CS like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotLike(String value) {
            addCriterion("CS not like", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<String> values) {
            addCriterion("CS in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<String> values) {
            addCriterion("CS not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(String value1, String value2) {
            addCriterion("CS between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(String value1, String value2) {
            addCriterion("CS not between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andSfcxIsNull() {
            addCriterion("SFCX is null");
            return (Criteria) this;
        }

        public Criteria andSfcxIsNotNull() {
            addCriterion("SFCX is not null");
            return (Criteria) this;
        }

        public Criteria andSfcxEqualTo(String value) {
            addCriterion("SFCX =", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxNotEqualTo(String value) {
            addCriterion("SFCX <>", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxGreaterThan(String value) {
            addCriterion("SFCX >", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxGreaterThanOrEqualTo(String value) {
            addCriterion("SFCX >=", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxLessThan(String value) {
            addCriterion("SFCX <", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxLessThanOrEqualTo(String value) {
            addCriterion("SFCX <=", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxLike(String value) {
            addCriterion("SFCX like", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxNotLike(String value) {
            addCriterion("SFCX not like", value, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxIn(List<String> values) {
            addCriterion("SFCX in", values, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxNotIn(List<String> values) {
            addCriterion("SFCX not in", values, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxBetween(String value1, String value2) {
            addCriterion("SFCX between", value1, value2, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcxNotBetween(String value1, String value2) {
            addCriterion("SFCX not between", value1, value2, "sfcx");
            return (Criteria) this;
        }

        public Criteria andSfcsIsNull() {
            addCriterion("SFCS is null");
            return (Criteria) this;
        }

        public Criteria andSfcsIsNotNull() {
            addCriterion("SFCS is not null");
            return (Criteria) this;
        }

        public Criteria andSfcsEqualTo(String value) {
            addCriterion("SFCS =", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotEqualTo(String value) {
            addCriterion("SFCS <>", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsGreaterThan(String value) {
            addCriterion("SFCS >", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsGreaterThanOrEqualTo(String value) {
            addCriterion("SFCS >=", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLessThan(String value) {
            addCriterion("SFCS <", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLessThanOrEqualTo(String value) {
            addCriterion("SFCS <=", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsLike(String value) {
            addCriterion("SFCS like", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotLike(String value) {
            addCriterion("SFCS not like", value, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsIn(List<String> values) {
            addCriterion("SFCS in", values, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotIn(List<String> values) {
            addCriterion("SFCS not in", values, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsBetween(String value1, String value2) {
            addCriterion("SFCS between", value1, value2, "sfcs");
            return (Criteria) this;
        }

        public Criteria andSfcsNotBetween(String value1, String value2) {
            addCriterion("SFCS not between", value1, value2, "sfcs");
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

        public Criteria andParentIsNull() {
            addCriterion("PARENT is null");
            return (Criteria) this;
        }

        public Criteria andParentIsNotNull() {
            addCriterion("PARENT is not null");
            return (Criteria) this;
        }

        public Criteria andParentEqualTo(String value) {
            addCriterion("PARENT =", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotEqualTo(String value) {
            addCriterion("PARENT <>", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThan(String value) {
            addCriterion("PARENT >", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT >=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThan(String value) {
            addCriterion("PARENT <", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThanOrEqualTo(String value) {
            addCriterion("PARENT <=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLike(String value) {
            addCriterion("PARENT like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotLike(String value) {
            addCriterion("PARENT not like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentIn(List<String> values) {
            addCriterion("PARENT in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotIn(List<String> values) {
            addCriterion("PARENT not in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentBetween(String value1, String value2) {
            addCriterion("PARENT between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotBetween(String value1, String value2) {
            addCriterion("PARENT not between", value1, value2, "parent");
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

        public Criteria andBmIsNull() {
            addCriterion("BM is null");
            return (Criteria) this;
        }

        public Criteria andBmIsNotNull() {
            addCriterion("BM is not null");
            return (Criteria) this;
        }

        public Criteria andBmEqualTo(String value) {
            addCriterion("BM =", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmNotEqualTo(String value) {
            addCriterion("BM <>", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmGreaterThan(String value) {
            addCriterion("BM >", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmGreaterThanOrEqualTo(String value) {
            addCriterion("BM >=", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmLessThan(String value) {
            addCriterion("BM <", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmLessThanOrEqualTo(String value) {
            addCriterion("BM <=", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmLike(String value) {
            addCriterion("BM like", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmNotLike(String value) {
            addCriterion("BM not like", value, "bm");
            return (Criteria) this;
        }

        public Criteria andBmIn(List<String> values) {
            addCriterion("BM in", values, "bm");
            return (Criteria) this;
        }

        public Criteria andBmNotIn(List<String> values) {
            addCriterion("BM not in", values, "bm");
            return (Criteria) this;
        }

        public Criteria andBmBetween(String value1, String value2) {
            addCriterion("BM between", value1, value2, "bm");
            return (Criteria) this;
        }

        public Criteria andBmNotBetween(String value1, String value2) {
            addCriterion("BM not between", value1, value2, "bm");
            return (Criteria) this;
        }

        public Criteria andGyzdIsNull() {
            addCriterion("GYZD is null");
            return (Criteria) this;
        }

        public Criteria andGyzdIsNotNull() {
            addCriterion("GYZD is not null");
            return (Criteria) this;
        }

        public Criteria andGyzdEqualTo(String value) {
            addCriterion("GYZD =", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdNotEqualTo(String value) {
            addCriterion("GYZD <>", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdGreaterThan(String value) {
            addCriterion("GYZD >", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdGreaterThanOrEqualTo(String value) {
            addCriterion("GYZD >=", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdLessThan(String value) {
            addCriterion("GYZD <", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdLessThanOrEqualTo(String value) {
            addCriterion("GYZD <=", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdLike(String value) {
            addCriterion("GYZD like", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdNotLike(String value) {
            addCriterion("GYZD not like", value, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdIn(List<String> values) {
            addCriterion("GYZD in", values, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdNotIn(List<String> values) {
            addCriterion("GYZD not in", values, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdBetween(String value1, String value2) {
            addCriterion("GYZD between", value1, value2, "gyzd");
            return (Criteria) this;
        }

        public Criteria andGyzdNotBetween(String value1, String value2) {
            addCriterion("GYZD not between", value1, value2, "gyzd");
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

        public Criteria andPxIsNull() {
            addCriterion("PX is null");
            return (Criteria) this;
        }

        public Criteria andPxIsNotNull() {
            addCriterion("PX is not null");
            return (Criteria) this;
        }

        public Criteria andPxEqualTo(String value) {
            addCriterion("PX =", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotEqualTo(String value) {
            addCriterion("PX <>", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxGreaterThan(String value) {
            addCriterion("PX >", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxGreaterThanOrEqualTo(String value) {
            addCriterion("PX >=", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLessThan(String value) {
            addCriterion("PX <", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLessThanOrEqualTo(String value) {
            addCriterion("PX <=", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLike(String value) {
            addCriterion("PX like", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotLike(String value) {
            addCriterion("PX not like", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxIn(List<String> values) {
            addCriterion("PX in", values, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotIn(List<String> values) {
            addCriterion("PX not in", values, "px");
            return (Criteria) this;
        }

        public Criteria andPxBetween(String value1, String value2) {
            addCriterion("PX between", value1, value2, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotBetween(String value1, String value2) {
            addCriterion("PX not between", value1, value2, "px");
            return (Criteria) this;
        }

        public Criteria andSfdxIsNull() {
            addCriterion("SFDX is null");
            return (Criteria) this;
        }

        public Criteria andSfdxIsNotNull() {
            addCriterion("SFDX is not null");
            return (Criteria) this;
        }

        public Criteria andSfdxEqualTo(String value) {
            addCriterion("SFDX =", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxNotEqualTo(String value) {
            addCriterion("SFDX <>", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxGreaterThan(String value) {
            addCriterion("SFDX >", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxGreaterThanOrEqualTo(String value) {
            addCriterion("SFDX >=", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxLessThan(String value) {
            addCriterion("SFDX <", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxLessThanOrEqualTo(String value) {
            addCriterion("SFDX <=", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxLike(String value) {
            addCriterion("SFDX like", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxNotLike(String value) {
            addCriterion("SFDX not like", value, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxIn(List<String> values) {
            addCriterion("SFDX in", values, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxNotIn(List<String> values) {
            addCriterion("SFDX not in", values, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxBetween(String value1, String value2) {
            addCriterion("SFDX between", value1, value2, "sfdx");
            return (Criteria) this;
        }

        public Criteria andSfdxNotBetween(String value1, String value2) {
            addCriterion("SFDX not between", value1, value2, "sfdx");
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