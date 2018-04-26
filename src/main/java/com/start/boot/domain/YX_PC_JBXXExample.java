package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YX_PC_JBXXExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YX_PC_JBXXExample() {
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

        public Criteria andPcslbmIsNull() {
            addCriterion("PCSLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcslbmIsNotNull() {
            addCriterion("PCSLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcslbmEqualTo(String value) {
            addCriterion("PCSLBM =", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotEqualTo(String value) {
            addCriterion("PCSLBM <>", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmGreaterThan(String value) {
            addCriterion("PCSLBM >", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCSLBM >=", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLessThan(String value) {
            addCriterion("PCSLBM <", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLessThanOrEqualTo(String value) {
            addCriterion("PCSLBM <=", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmLike(String value) {
            addCriterion("PCSLBM like", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotLike(String value) {
            addCriterion("PCSLBM not like", value, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmIn(List<String> values) {
            addCriterion("PCSLBM in", values, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotIn(List<String> values) {
            addCriterion("PCSLBM not in", values, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmBetween(String value1, String value2) {
            addCriterion("PCSLBM between", value1, value2, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcslbmNotBetween(String value1, String value2) {
            addCriterion("PCSLBM not between", value1, value2, "pcslbm");
            return (Criteria) this;
        }

        public Criteria andPcsahIsNull() {
            addCriterion("PCSAH is null");
            return (Criteria) this;
        }

        public Criteria andPcsahIsNotNull() {
            addCriterion("PCSAH is not null");
            return (Criteria) this;
        }

        public Criteria andPcsahEqualTo(String value) {
            addCriterion("PCSAH =", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahNotEqualTo(String value) {
            addCriterion("PCSAH <>", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahGreaterThan(String value) {
            addCriterion("PCSAH >", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahGreaterThanOrEqualTo(String value) {
            addCriterion("PCSAH >=", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahLessThan(String value) {
            addCriterion("PCSAH <", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahLessThanOrEqualTo(String value) {
            addCriterion("PCSAH <=", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahLike(String value) {
            addCriterion("PCSAH like", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahNotLike(String value) {
            addCriterion("PCSAH not like", value, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahIn(List<String> values) {
            addCriterion("PCSAH in", values, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahNotIn(List<String> values) {
            addCriterion("PCSAH not in", values, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahBetween(String value1, String value2) {
            addCriterion("PCSAH between", value1, value2, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcsahNotBetween(String value1, String value2) {
            addCriterion("PCSAH not between", value1, value2, "pcsah");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIsNull() {
            addCriterion("PCDWBM is null");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIsNotNull() {
            addCriterion("PCDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcdwbmEqualTo(String value) {
            addCriterion("PCDWBM =", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotEqualTo(String value) {
            addCriterion("PCDWBM <>", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmGreaterThan(String value) {
            addCriterion("PCDWBM >", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCDWBM >=", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLessThan(String value) {
            addCriterion("PCDWBM <", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLessThanOrEqualTo(String value) {
            addCriterion("PCDWBM <=", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmLike(String value) {
            addCriterion("PCDWBM like", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotLike(String value) {
            addCriterion("PCDWBM not like", value, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmIn(List<String> values) {
            addCriterion("PCDWBM in", values, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotIn(List<String> values) {
            addCriterion("PCDWBM not in", values, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmBetween(String value1, String value2) {
            addCriterion("PCDWBM between", value1, value2, "pcdwbm");
            return (Criteria) this;
        }

        public Criteria andPcdwbmNotBetween(String value1, String value2) {
            addCriterion("PCDWBM not between", value1, value2, "pcdwbm");
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

        public Criteria andPchdbmIsNull() {
            addCriterion("PCHDBM is null");
            return (Criteria) this;
        }

        public Criteria andPchdbmIsNotNull() {
            addCriterion("PCHDBM is not null");
            return (Criteria) this;
        }

        public Criteria andPchdbmEqualTo(String value) {
            addCriterion("PCHDBM =", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotEqualTo(String value) {
            addCriterion("PCHDBM <>", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmGreaterThan(String value) {
            addCriterion("PCHDBM >", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCHDBM >=", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLessThan(String value) {
            addCriterion("PCHDBM <", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLessThanOrEqualTo(String value) {
            addCriterion("PCHDBM <=", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmLike(String value) {
            addCriterion("PCHDBM like", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotLike(String value) {
            addCriterion("PCHDBM not like", value, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmIn(List<String> values) {
            addCriterion("PCHDBM in", values, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotIn(List<String> values) {
            addCriterion("PCHDBM not in", values, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmBetween(String value1, String value2) {
            addCriterion("PCHDBM between", value1, value2, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andPchdbmNotBetween(String value1, String value2) {
            addCriterion("PCHDBM not between", value1, value2, "pchdbm");
            return (Criteria) this;
        }

        public Criteria andBmsahIsNull() {
            addCriterion("BMSAH is null");
            return (Criteria) this;
        }

        public Criteria andBmsahIsNotNull() {
            addCriterion("BMSAH is not null");
            return (Criteria) this;
        }

        public Criteria andBmsahEqualTo(String value) {
            addCriterion("BMSAH =", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotEqualTo(String value) {
            addCriterion("BMSAH <>", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahGreaterThan(String value) {
            addCriterion("BMSAH >", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahGreaterThanOrEqualTo(String value) {
            addCriterion("BMSAH >=", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLessThan(String value) {
            addCriterion("BMSAH <", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLessThanOrEqualTo(String value) {
            addCriterion("BMSAH <=", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahLike(String value) {
            addCriterion("BMSAH like", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotLike(String value) {
            addCriterion("BMSAH not like", value, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahIn(List<String> values) {
            addCriterion("BMSAH in", values, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotIn(List<String> values) {
            addCriterion("BMSAH not in", values, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahBetween(String value1, String value2) {
            addCriterion("BMSAH between", value1, value2, "bmsah");
            return (Criteria) this;
        }

        public Criteria andBmsahNotBetween(String value1, String value2) {
            addCriterion("BMSAH not between", value1, value2, "bmsah");
            return (Criteria) this;
        }

        public Criteria andTysahIsNull() {
            addCriterion("TYSAH is null");
            return (Criteria) this;
        }

        public Criteria andTysahIsNotNull() {
            addCriterion("TYSAH is not null");
            return (Criteria) this;
        }

        public Criteria andTysahEqualTo(String value) {
            addCriterion("TYSAH =", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotEqualTo(String value) {
            addCriterion("TYSAH <>", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahGreaterThan(String value) {
            addCriterion("TYSAH >", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahGreaterThanOrEqualTo(String value) {
            addCriterion("TYSAH >=", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLessThan(String value) {
            addCriterion("TYSAH <", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLessThanOrEqualTo(String value) {
            addCriterion("TYSAH <=", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahLike(String value) {
            addCriterion("TYSAH like", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotLike(String value) {
            addCriterion("TYSAH not like", value, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahIn(List<String> values) {
            addCriterion("TYSAH in", values, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotIn(List<String> values) {
            addCriterion("TYSAH not in", values, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahBetween(String value1, String value2) {
            addCriterion("TYSAH between", value1, value2, "tysah");
            return (Criteria) this;
        }

        public Criteria andTysahNotBetween(String value1, String value2) {
            addCriterion("TYSAH not between", value1, value2, "tysah");
            return (Criteria) this;
        }

        public Criteria andAjmcIsNull() {
            addCriterion("AJMC is null");
            return (Criteria) this;
        }

        public Criteria andAjmcIsNotNull() {
            addCriterion("AJMC is not null");
            return (Criteria) this;
        }

        public Criteria andAjmcEqualTo(String value) {
            addCriterion("AJMC =", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotEqualTo(String value) {
            addCriterion("AJMC <>", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcGreaterThan(String value) {
            addCriterion("AJMC >", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcGreaterThanOrEqualTo(String value) {
            addCriterion("AJMC >=", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLessThan(String value) {
            addCriterion("AJMC <", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLessThanOrEqualTo(String value) {
            addCriterion("AJMC <=", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcLike(String value) {
            addCriterion("AJMC like", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotLike(String value) {
            addCriterion("AJMC not like", value, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcIn(List<String> values) {
            addCriterion("AJMC in", values, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotIn(List<String> values) {
            addCriterion("AJMC not in", values, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcBetween(String value1, String value2) {
            addCriterion("AJMC between", value1, value2, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjmcNotBetween(String value1, String value2) {
            addCriterion("AJMC not between", value1, value2, "ajmc");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIsNull() {
            addCriterion("AJLB_BM is null");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIsNotNull() {
            addCriterion("AJLB_BM is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbBmEqualTo(String value) {
            addCriterion("AJLB_BM =", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotEqualTo(String value) {
            addCriterion("AJLB_BM <>", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmGreaterThan(String value) {
            addCriterion("AJLB_BM >", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmGreaterThanOrEqualTo(String value) {
            addCriterion("AJLB_BM >=", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLessThan(String value) {
            addCriterion("AJLB_BM <", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLessThanOrEqualTo(String value) {
            addCriterion("AJLB_BM <=", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmLike(String value) {
            addCriterion("AJLB_BM like", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotLike(String value) {
            addCriterion("AJLB_BM not like", value, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmIn(List<String> values) {
            addCriterion("AJLB_BM in", values, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotIn(List<String> values) {
            addCriterion("AJLB_BM not in", values, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmBetween(String value1, String value2) {
            addCriterion("AJLB_BM between", value1, value2, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbBmNotBetween(String value1, String value2) {
            addCriterion("AJLB_BM not between", value1, value2, "ajlbBm");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIsNull() {
            addCriterion("AJLB_MC is null");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIsNotNull() {
            addCriterion("AJLB_MC is not null");
            return (Criteria) this;
        }

        public Criteria andAjlbMcEqualTo(String value) {
            addCriterion("AJLB_MC =", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotEqualTo(String value) {
            addCriterion("AJLB_MC <>", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcGreaterThan(String value) {
            addCriterion("AJLB_MC >", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcGreaterThanOrEqualTo(String value) {
            addCriterion("AJLB_MC >=", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLessThan(String value) {
            addCriterion("AJLB_MC <", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLessThanOrEqualTo(String value) {
            addCriterion("AJLB_MC <=", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcLike(String value) {
            addCriterion("AJLB_MC like", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotLike(String value) {
            addCriterion("AJLB_MC not like", value, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcIn(List<String> values) {
            addCriterion("AJLB_MC in", values, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotIn(List<String> values) {
            addCriterion("AJLB_MC not in", values, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcBetween(String value1, String value2) {
            addCriterion("AJLB_MC between", value1, value2, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andAjlbMcNotBetween(String value1, String value2) {
            addCriterion("AJLB_MC not between", value1, value2, "ajlbMc");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmIsNull() {
            addCriterion("BPC_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmIsNotNull() {
            addCriterion("BPC_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmEqualTo(String value) {
            addCriterion("BPC_DWBM =", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmNotEqualTo(String value) {
            addCriterion("BPC_DWBM <>", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmGreaterThan(String value) {
            addCriterion("BPC_DWBM >", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_DWBM >=", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmLessThan(String value) {
            addCriterion("BPC_DWBM <", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmLessThanOrEqualTo(String value) {
            addCriterion("BPC_DWBM <=", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmLike(String value) {
            addCriterion("BPC_DWBM like", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmNotLike(String value) {
            addCriterion("BPC_DWBM not like", value, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmIn(List<String> values) {
            addCriterion("BPC_DWBM in", values, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmNotIn(List<String> values) {
            addCriterion("BPC_DWBM not in", values, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmBetween(String value1, String value2) {
            addCriterion("BPC_DWBM between", value1, value2, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwbmNotBetween(String value1, String value2) {
            addCriterion("BPC_DWBM not between", value1, value2, "bpcDwbm");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcIsNull() {
            addCriterion("BPC_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcIsNotNull() {
            addCriterion("BPC_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcEqualTo(String value) {
            addCriterion("BPC_DWMC =", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcNotEqualTo(String value) {
            addCriterion("BPC_DWMC <>", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcGreaterThan(String value) {
            addCriterion("BPC_DWMC >", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_DWMC >=", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcLessThan(String value) {
            addCriterion("BPC_DWMC <", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcLessThanOrEqualTo(String value) {
            addCriterion("BPC_DWMC <=", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcLike(String value) {
            addCriterion("BPC_DWMC like", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcNotLike(String value) {
            addCriterion("BPC_DWMC not like", value, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcIn(List<String> values) {
            addCriterion("BPC_DWMC in", values, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcNotIn(List<String> values) {
            addCriterion("BPC_DWMC not in", values, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcBetween(String value1, String value2) {
            addCriterion("BPC_DWMC between", value1, value2, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcDwmcNotBetween(String value1, String value2) {
            addCriterion("BPC_DWMC not between", value1, value2, "bpcDwmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmIsNull() {
            addCriterion("BPC_BMBM is null");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmIsNotNull() {
            addCriterion("BPC_BMBM is not null");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmEqualTo(String value) {
            addCriterion("BPC_BMBM =", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmNotEqualTo(String value) {
            addCriterion("BPC_BMBM <>", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmGreaterThan(String value) {
            addCriterion("BPC_BMBM >", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_BMBM >=", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmLessThan(String value) {
            addCriterion("BPC_BMBM <", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmLessThanOrEqualTo(String value) {
            addCriterion("BPC_BMBM <=", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmLike(String value) {
            addCriterion("BPC_BMBM like", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmNotLike(String value) {
            addCriterion("BPC_BMBM not like", value, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmIn(List<String> values) {
            addCriterion("BPC_BMBM in", values, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmNotIn(List<String> values) {
            addCriterion("BPC_BMBM not in", values, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmBetween(String value1, String value2) {
            addCriterion("BPC_BMBM between", value1, value2, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmbmNotBetween(String value1, String value2) {
            addCriterion("BPC_BMBM not between", value1, value2, "bpcBmbm");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcIsNull() {
            addCriterion("BPC_BMMC is null");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcIsNotNull() {
            addCriterion("BPC_BMMC is not null");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcEqualTo(String value) {
            addCriterion("BPC_BMMC =", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcNotEqualTo(String value) {
            addCriterion("BPC_BMMC <>", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcGreaterThan(String value) {
            addCriterion("BPC_BMMC >", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_BMMC >=", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcLessThan(String value) {
            addCriterion("BPC_BMMC <", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcLessThanOrEqualTo(String value) {
            addCriterion("BPC_BMMC <=", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcLike(String value) {
            addCriterion("BPC_BMMC like", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcNotLike(String value) {
            addCriterion("BPC_BMMC not like", value, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcIn(List<String> values) {
            addCriterion("BPC_BMMC in", values, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcNotIn(List<String> values) {
            addCriterion("BPC_BMMC not in", values, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcBetween(String value1, String value2) {
            addCriterion("BPC_BMMC between", value1, value2, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcBmmcNotBetween(String value1, String value2) {
            addCriterion("BPC_BMMC not between", value1, value2, "bpcBmmc");
            return (Criteria) this;
        }

        public Criteria andBpcGhIsNull() {
            addCriterion("BPC_GH is null");
            return (Criteria) this;
        }

        public Criteria andBpcGhIsNotNull() {
            addCriterion("BPC_GH is not null");
            return (Criteria) this;
        }

        public Criteria andBpcGhEqualTo(String value) {
            addCriterion("BPC_GH =", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhNotEqualTo(String value) {
            addCriterion("BPC_GH <>", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhGreaterThan(String value) {
            addCriterion("BPC_GH >", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_GH >=", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhLessThan(String value) {
            addCriterion("BPC_GH <", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhLessThanOrEqualTo(String value) {
            addCriterion("BPC_GH <=", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhLike(String value) {
            addCriterion("BPC_GH like", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhNotLike(String value) {
            addCriterion("BPC_GH not like", value, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhIn(List<String> values) {
            addCriterion("BPC_GH in", values, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhNotIn(List<String> values) {
            addCriterion("BPC_GH not in", values, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhBetween(String value1, String value2) {
            addCriterion("BPC_GH between", value1, value2, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcGhNotBetween(String value1, String value2) {
            addCriterion("BPC_GH not between", value1, value2, "bpcGh");
            return (Criteria) this;
        }

        public Criteria andBpcMcIsNull() {
            addCriterion("BPC_MC is null");
            return (Criteria) this;
        }

        public Criteria andBpcMcIsNotNull() {
            addCriterion("BPC_MC is not null");
            return (Criteria) this;
        }

        public Criteria andBpcMcEqualTo(String value) {
            addCriterion("BPC_MC =", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcNotEqualTo(String value) {
            addCriterion("BPC_MC <>", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcGreaterThan(String value) {
            addCriterion("BPC_MC >", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcGreaterThanOrEqualTo(String value) {
            addCriterion("BPC_MC >=", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcLessThan(String value) {
            addCriterion("BPC_MC <", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcLessThanOrEqualTo(String value) {
            addCriterion("BPC_MC <=", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcLike(String value) {
            addCriterion("BPC_MC like", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcNotLike(String value) {
            addCriterion("BPC_MC not like", value, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcIn(List<String> values) {
            addCriterion("BPC_MC in", values, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcNotIn(List<String> values) {
            addCriterion("BPC_MC not in", values, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcBetween(String value1, String value2) {
            addCriterion("BPC_MC between", value1, value2, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcMcNotBetween(String value1, String value2) {
            addCriterion("BPC_MC not between", value1, value2, "bpcMc");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqIsNull() {
            addCriterion("BPC_SLRQ is null");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqIsNotNull() {
            addCriterion("BPC_SLRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqEqualTo(Date value) {
            addCriterion("BPC_SLRQ =", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqNotEqualTo(Date value) {
            addCriterion("BPC_SLRQ <>", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqGreaterThan(Date value) {
            addCriterion("BPC_SLRQ >", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqGreaterThanOrEqualTo(Date value) {
            addCriterion("BPC_SLRQ >=", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqLessThan(Date value) {
            addCriterion("BPC_SLRQ <", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqLessThanOrEqualTo(Date value) {
            addCriterion("BPC_SLRQ <=", value, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqIn(List<Date> values) {
            addCriterion("BPC_SLRQ in", values, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqNotIn(List<Date> values) {
            addCriterion("BPC_SLRQ not in", values, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqBetween(Date value1, Date value2) {
            addCriterion("BPC_SLRQ between", value1, value2, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcSlrqNotBetween(Date value1, Date value2) {
            addCriterion("BPC_SLRQ not between", value1, value2, "bpcSlrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqIsNull() {
            addCriterion("BPC_WCRQ is null");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqIsNotNull() {
            addCriterion("BPC_WCRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqEqualTo(Date value) {
            addCriterion("BPC_WCRQ =", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqNotEqualTo(Date value) {
            addCriterion("BPC_WCRQ <>", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqGreaterThan(Date value) {
            addCriterion("BPC_WCRQ >", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqGreaterThanOrEqualTo(Date value) {
            addCriterion("BPC_WCRQ >=", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqLessThan(Date value) {
            addCriterion("BPC_WCRQ <", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqLessThanOrEqualTo(Date value) {
            addCriterion("BPC_WCRQ <=", value, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqIn(List<Date> values) {
            addCriterion("BPC_WCRQ in", values, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqNotIn(List<Date> values) {
            addCriterion("BPC_WCRQ not in", values, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqBetween(Date value1, Date value2) {
            addCriterion("BPC_WCRQ between", value1, value2, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcrqNotBetween(Date value1, Date value2) {
            addCriterion("BPC_WCRQ not between", value1, value2, "bpcWcrq");
            return (Criteria) this;
        }

        public Criteria andLcslbhIsNull() {
            addCriterion("LCSLBH is null");
            return (Criteria) this;
        }

        public Criteria andLcslbhIsNotNull() {
            addCriterion("LCSLBH is not null");
            return (Criteria) this;
        }

        public Criteria andLcslbhEqualTo(String value) {
            addCriterion("LCSLBH =", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotEqualTo(String value) {
            addCriterion("LCSLBH <>", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhGreaterThan(String value) {
            addCriterion("LCSLBH >", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhGreaterThanOrEqualTo(String value) {
            addCriterion("LCSLBH >=", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLessThan(String value) {
            addCriterion("LCSLBH <", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLessThanOrEqualTo(String value) {
            addCriterion("LCSLBH <=", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhLike(String value) {
            addCriterion("LCSLBH like", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotLike(String value) {
            addCriterion("LCSLBH not like", value, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhIn(List<String> values) {
            addCriterion("LCSLBH in", values, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotIn(List<String> values) {
            addCriterion("LCSLBH not in", values, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhBetween(String value1, String value2) {
            addCriterion("LCSLBH between", value1, value2, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andLcslbhNotBetween(String value1, String value2) {
            addCriterion("LCSLBH not between", value1, value2, "lcslbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhIsNull() {
            addCriterion("PCJDBH is null");
            return (Criteria) this;
        }

        public Criteria andPcjdbhIsNotNull() {
            addCriterion("PCJDBH is not null");
            return (Criteria) this;
        }

        public Criteria andPcjdbhEqualTo(String value) {
            addCriterion("PCJDBH =", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhNotEqualTo(String value) {
            addCriterion("PCJDBH <>", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhGreaterThan(String value) {
            addCriterion("PCJDBH >", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhGreaterThanOrEqualTo(String value) {
            addCriterion("PCJDBH >=", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhLessThan(String value) {
            addCriterion("PCJDBH <", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhLessThanOrEqualTo(String value) {
            addCriterion("PCJDBH <=", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhLike(String value) {
            addCriterion("PCJDBH like", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhNotLike(String value) {
            addCriterion("PCJDBH not like", value, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhIn(List<String> values) {
            addCriterion("PCJDBH in", values, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhNotIn(List<String> values) {
            addCriterion("PCJDBH not in", values, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhBetween(String value1, String value2) {
            addCriterion("PCJDBH between", value1, value2, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdbhNotBetween(String value1, String value2) {
            addCriterion("PCJDBH not between", value1, value2, "pcjdbh");
            return (Criteria) this;
        }

        public Criteria andPcjdmsIsNull() {
            addCriterion("PCJDMS is null");
            return (Criteria) this;
        }

        public Criteria andPcjdmsIsNotNull() {
            addCriterion("PCJDMS is not null");
            return (Criteria) this;
        }

        public Criteria andPcjdmsEqualTo(String value) {
            addCriterion("PCJDMS =", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsNotEqualTo(String value) {
            addCriterion("PCJDMS <>", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsGreaterThan(String value) {
            addCriterion("PCJDMS >", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsGreaterThanOrEqualTo(String value) {
            addCriterion("PCJDMS >=", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsLessThan(String value) {
            addCriterion("PCJDMS <", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsLessThanOrEqualTo(String value) {
            addCriterion("PCJDMS <=", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsLike(String value) {
            addCriterion("PCJDMS like", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsNotLike(String value) {
            addCriterion("PCJDMS not like", value, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsIn(List<String> values) {
            addCriterion("PCJDMS in", values, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsNotIn(List<String> values) {
            addCriterion("PCJDMS not in", values, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsBetween(String value1, String value2) {
            addCriterion("PCJDMS between", value1, value2, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andPcjdmsNotBetween(String value1, String value2) {
            addCriterion("PCJDMS not between", value1, value2, "pcjdms");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmIsNull() {
            addCriterion("SXR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmIsNotNull() {
            addCriterion("SXR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmEqualTo(String value) {
            addCriterion("SXR_DWBM =", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmNotEqualTo(String value) {
            addCriterion("SXR_DWBM <>", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmGreaterThan(String value) {
            addCriterion("SXR_DWBM >", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("SXR_DWBM >=", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmLessThan(String value) {
            addCriterion("SXR_DWBM <", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmLessThanOrEqualTo(String value) {
            addCriterion("SXR_DWBM <=", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmLike(String value) {
            addCriterion("SXR_DWBM like", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmNotLike(String value) {
            addCriterion("SXR_DWBM not like", value, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmIn(List<String> values) {
            addCriterion("SXR_DWBM in", values, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmNotIn(List<String> values) {
            addCriterion("SXR_DWBM not in", values, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmBetween(String value1, String value2) {
            addCriterion("SXR_DWBM between", value1, value2, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrDwbmNotBetween(String value1, String value2) {
            addCriterion("SXR_DWBM not between", value1, value2, "sxrDwbm");
            return (Criteria) this;
        }

        public Criteria andSxrGhIsNull() {
            addCriterion("SXR_GH is null");
            return (Criteria) this;
        }

        public Criteria andSxrGhIsNotNull() {
            addCriterion("SXR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andSxrGhEqualTo(String value) {
            addCriterion("SXR_GH =", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhNotEqualTo(String value) {
            addCriterion("SXR_GH <>", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhGreaterThan(String value) {
            addCriterion("SXR_GH >", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhGreaterThanOrEqualTo(String value) {
            addCriterion("SXR_GH >=", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhLessThan(String value) {
            addCriterion("SXR_GH <", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhLessThanOrEqualTo(String value) {
            addCriterion("SXR_GH <=", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhLike(String value) {
            addCriterion("SXR_GH like", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhNotLike(String value) {
            addCriterion("SXR_GH not like", value, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhIn(List<String> values) {
            addCriterion("SXR_GH in", values, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhNotIn(List<String> values) {
            addCriterion("SXR_GH not in", values, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhBetween(String value1, String value2) {
            addCriterion("SXR_GH between", value1, value2, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andSxrGhNotBetween(String value1, String value2) {
            addCriterion("SXR_GH not between", value1, value2, "sxrGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmIsNull() {
            addCriterion("FPDZ_FPR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmIsNotNull() {
            addCriterion("FPDZ_FPR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWBM =", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmNotEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWBM <>", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmGreaterThan(String value) {
            addCriterion("FPDZ_FPR_DWBM >", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWBM >=", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmLessThan(String value) {
            addCriterion("FPDZ_FPR_DWBM <", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmLessThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWBM <=", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmLike(String value) {
            addCriterion("FPDZ_FPR_DWBM like", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmNotLike(String value) {
            addCriterion("FPDZ_FPR_DWBM not like", value, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmIn(List<String> values) {
            addCriterion("FPDZ_FPR_DWBM in", values, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmNotIn(List<String> values) {
            addCriterion("FPDZ_FPR_DWBM not in", values, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_DWBM between", value1, value2, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwbmNotBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_DWBM not between", value1, value2, "fpdzFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhIsNull() {
            addCriterion("FPDZ_FPR_GH is null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhIsNotNull() {
            addCriterion("FPDZ_FPR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhEqualTo(String value) {
            addCriterion("FPDZ_FPR_GH =", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhNotEqualTo(String value) {
            addCriterion("FPDZ_FPR_GH <>", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhGreaterThan(String value) {
            addCriterion("FPDZ_FPR_GH >", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhGreaterThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_GH >=", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhLessThan(String value) {
            addCriterion("FPDZ_FPR_GH <", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhLessThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_GH <=", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhLike(String value) {
            addCriterion("FPDZ_FPR_GH like", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhNotLike(String value) {
            addCriterion("FPDZ_FPR_GH not like", value, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhIn(List<String> values) {
            addCriterion("FPDZ_FPR_GH in", values, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhNotIn(List<String> values) {
            addCriterion("FPDZ_FPR_GH not in", values, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_GH between", value1, value2, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdzFprGhNotBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_GH not between", value1, value2, "fpdzFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmIsNull() {
            addCriterion("FPDR_FPR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmIsNotNull() {
            addCriterion("FPDR_FPR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmEqualTo(String value) {
            addCriterion("FPDR_FPR_DWBM =", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmNotEqualTo(String value) {
            addCriterion("FPDR_FPR_DWBM <>", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmGreaterThan(String value) {
            addCriterion("FPDR_FPR_DWBM >", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_DWBM >=", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmLessThan(String value) {
            addCriterion("FPDR_FPR_DWBM <", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmLessThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_DWBM <=", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmLike(String value) {
            addCriterion("FPDR_FPR_DWBM like", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmNotLike(String value) {
            addCriterion("FPDR_FPR_DWBM not like", value, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmIn(List<String> values) {
            addCriterion("FPDR_FPR_DWBM in", values, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmNotIn(List<String> values) {
            addCriterion("FPDR_FPR_DWBM not in", values, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_DWBM between", value1, value2, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwbmNotBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_DWBM not between", value1, value2, "fpdrFprDwbm");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcIsNull() {
            addCriterion("FPDR_FPR_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcIsNotNull() {
            addCriterion("FPDR_FPR_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcEqualTo(String value) {
            addCriterion("FPDR_FPR_DWMC =", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcNotEqualTo(String value) {
            addCriterion("FPDR_FPR_DWMC <>", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcGreaterThan(String value) {
            addCriterion("FPDR_FPR_DWMC >", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_DWMC >=", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcLessThan(String value) {
            addCriterion("FPDR_FPR_DWMC <", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcLessThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_DWMC <=", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcLike(String value) {
            addCriterion("FPDR_FPR_DWMC like", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcNotLike(String value) {
            addCriterion("FPDR_FPR_DWMC not like", value, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcIn(List<String> values) {
            addCriterion("FPDR_FPR_DWMC in", values, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcNotIn(List<String> values) {
            addCriterion("FPDR_FPR_DWMC not in", values, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_DWMC between", value1, value2, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprDwmcNotBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_DWMC not between", value1, value2, "fpdrFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhIsNull() {
            addCriterion("FPDR_FPR_GH is null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhIsNotNull() {
            addCriterion("FPDR_FPR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhEqualTo(String value) {
            addCriterion("FPDR_FPR_GH =", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhNotEqualTo(String value) {
            addCriterion("FPDR_FPR_GH <>", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhGreaterThan(String value) {
            addCriterion("FPDR_FPR_GH >", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhGreaterThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_GH >=", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhLessThan(String value) {
            addCriterion("FPDR_FPR_GH <", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhLessThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_GH <=", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhLike(String value) {
            addCriterion("FPDR_FPR_GH like", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhNotLike(String value) {
            addCriterion("FPDR_FPR_GH not like", value, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhIn(List<String> values) {
            addCriterion("FPDR_FPR_GH in", values, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhNotIn(List<String> values) {
            addCriterion("FPDR_FPR_GH not in", values, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_GH between", value1, value2, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprGhNotBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_GH not between", value1, value2, "fpdrFprGh");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcIsNull() {
            addCriterion("FPDR_FPR_MC is null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcIsNotNull() {
            addCriterion("FPDR_FPR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcEqualTo(String value) {
            addCriterion("FPDR_FPR_MC =", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcNotEqualTo(String value) {
            addCriterion("FPDR_FPR_MC <>", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcGreaterThan(String value) {
            addCriterion("FPDR_FPR_MC >", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcGreaterThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_MC >=", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcLessThan(String value) {
            addCriterion("FPDR_FPR_MC <", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcLessThanOrEqualTo(String value) {
            addCriterion("FPDR_FPR_MC <=", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcLike(String value) {
            addCriterion("FPDR_FPR_MC like", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcNotLike(String value) {
            addCriterion("FPDR_FPR_MC not like", value, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcIn(List<String> values) {
            addCriterion("FPDR_FPR_MC in", values, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcNotIn(List<String> values) {
            addCriterion("FPDR_FPR_MC not in", values, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_MC between", value1, value2, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdrFprMcNotBetween(String value1, String value2) {
            addCriterion("FPDR_FPR_MC not between", value1, value2, "fpdrFprMc");
            return (Criteria) this;
        }

        public Criteria andPczBmIsNull() {
            addCriterion("PCZ_BM is null");
            return (Criteria) this;
        }

        public Criteria andPczBmIsNotNull() {
            addCriterion("PCZ_BM is not null");
            return (Criteria) this;
        }

        public Criteria andPczBmEqualTo(String value) {
            addCriterion("PCZ_BM =", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmNotEqualTo(String value) {
            addCriterion("PCZ_BM <>", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmGreaterThan(String value) {
            addCriterion("PCZ_BM >", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmGreaterThanOrEqualTo(String value) {
            addCriterion("PCZ_BM >=", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmLessThan(String value) {
            addCriterion("PCZ_BM <", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmLessThanOrEqualTo(String value) {
            addCriterion("PCZ_BM <=", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmLike(String value) {
            addCriterion("PCZ_BM like", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmNotLike(String value) {
            addCriterion("PCZ_BM not like", value, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmIn(List<String> values) {
            addCriterion("PCZ_BM in", values, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmNotIn(List<String> values) {
            addCriterion("PCZ_BM not in", values, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmBetween(String value1, String value2) {
            addCriterion("PCZ_BM between", value1, value2, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczBmNotBetween(String value1, String value2) {
            addCriterion("PCZ_BM not between", value1, value2, "pczBm");
            return (Criteria) this;
        }

        public Criteria andPczMcIsNull() {
            addCriterion("PCZ_MC is null");
            return (Criteria) this;
        }

        public Criteria andPczMcIsNotNull() {
            addCriterion("PCZ_MC is not null");
            return (Criteria) this;
        }

        public Criteria andPczMcEqualTo(String value) {
            addCriterion("PCZ_MC =", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcNotEqualTo(String value) {
            addCriterion("PCZ_MC <>", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcGreaterThan(String value) {
            addCriterion("PCZ_MC >", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcGreaterThanOrEqualTo(String value) {
            addCriterion("PCZ_MC >=", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcLessThan(String value) {
            addCriterion("PCZ_MC <", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcLessThanOrEqualTo(String value) {
            addCriterion("PCZ_MC <=", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcLike(String value) {
            addCriterion("PCZ_MC like", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcNotLike(String value) {
            addCriterion("PCZ_MC not like", value, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcIn(List<String> values) {
            addCriterion("PCZ_MC in", values, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcNotIn(List<String> values) {
            addCriterion("PCZ_MC not in", values, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcBetween(String value1, String value2) {
            addCriterion("PCZ_MC between", value1, value2, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPczMcNotBetween(String value1, String value2) {
            addCriterion("PCZ_MC not between", value1, value2, "pczMc");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmIsNull() {
            addCriterion("PCR_DWBM is null");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmIsNotNull() {
            addCriterion("PCR_DWBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmEqualTo(String value) {
            addCriterion("PCR_DWBM =", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmNotEqualTo(String value) {
            addCriterion("PCR_DWBM <>", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmGreaterThan(String value) {
            addCriterion("PCR_DWBM >", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCR_DWBM >=", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmLessThan(String value) {
            addCriterion("PCR_DWBM <", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmLessThanOrEqualTo(String value) {
            addCriterion("PCR_DWBM <=", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmLike(String value) {
            addCriterion("PCR_DWBM like", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmNotLike(String value) {
            addCriterion("PCR_DWBM not like", value, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmIn(List<String> values) {
            addCriterion("PCR_DWBM in", values, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmNotIn(List<String> values) {
            addCriterion("PCR_DWBM not in", values, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmBetween(String value1, String value2) {
            addCriterion("PCR_DWBM between", value1, value2, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwbmNotBetween(String value1, String value2) {
            addCriterion("PCR_DWBM not between", value1, value2, "pcrDwbm");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcIsNull() {
            addCriterion("PCR_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcIsNotNull() {
            addCriterion("PCR_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcEqualTo(String value) {
            addCriterion("PCR_DWMC =", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcNotEqualTo(String value) {
            addCriterion("PCR_DWMC <>", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcGreaterThan(String value) {
            addCriterion("PCR_DWMC >", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCR_DWMC >=", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcLessThan(String value) {
            addCriterion("PCR_DWMC <", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcLessThanOrEqualTo(String value) {
            addCriterion("PCR_DWMC <=", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcLike(String value) {
            addCriterion("PCR_DWMC like", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcNotLike(String value) {
            addCriterion("PCR_DWMC not like", value, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcIn(List<String> values) {
            addCriterion("PCR_DWMC in", values, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcNotIn(List<String> values) {
            addCriterion("PCR_DWMC not in", values, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcBetween(String value1, String value2) {
            addCriterion("PCR_DWMC between", value1, value2, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrDwmcNotBetween(String value1, String value2) {
            addCriterion("PCR_DWMC not between", value1, value2, "pcrDwmc");
            return (Criteria) this;
        }

        public Criteria andPcrGhIsNull() {
            addCriterion("PCR_GH is null");
            return (Criteria) this;
        }

        public Criteria andPcrGhIsNotNull() {
            addCriterion("PCR_GH is not null");
            return (Criteria) this;
        }

        public Criteria andPcrGhEqualTo(String value) {
            addCriterion("PCR_GH =", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhNotEqualTo(String value) {
            addCriterion("PCR_GH <>", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhGreaterThan(String value) {
            addCriterion("PCR_GH >", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhGreaterThanOrEqualTo(String value) {
            addCriterion("PCR_GH >=", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhLessThan(String value) {
            addCriterion("PCR_GH <", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhLessThanOrEqualTo(String value) {
            addCriterion("PCR_GH <=", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhLike(String value) {
            addCriterion("PCR_GH like", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhNotLike(String value) {
            addCriterion("PCR_GH not like", value, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhIn(List<String> values) {
            addCriterion("PCR_GH in", values, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhNotIn(List<String> values) {
            addCriterion("PCR_GH not in", values, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhBetween(String value1, String value2) {
            addCriterion("PCR_GH between", value1, value2, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrGhNotBetween(String value1, String value2) {
            addCriterion("PCR_GH not between", value1, value2, "pcrGh");
            return (Criteria) this;
        }

        public Criteria andPcrMcIsNull() {
            addCriterion("PCR_MC is null");
            return (Criteria) this;
        }

        public Criteria andPcrMcIsNotNull() {
            addCriterion("PCR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrMcEqualTo(String value) {
            addCriterion("PCR_MC =", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcNotEqualTo(String value) {
            addCriterion("PCR_MC <>", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcGreaterThan(String value) {
            addCriterion("PCR_MC >", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcGreaterThanOrEqualTo(String value) {
            addCriterion("PCR_MC >=", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcLessThan(String value) {
            addCriterion("PCR_MC <", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcLessThanOrEqualTo(String value) {
            addCriterion("PCR_MC <=", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcLike(String value) {
            addCriterion("PCR_MC like", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcNotLike(String value) {
            addCriterion("PCR_MC not like", value, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcIn(List<String> values) {
            addCriterion("PCR_MC in", values, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcNotIn(List<String> values) {
            addCriterion("PCR_MC not in", values, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcBetween(String value1, String value2) {
            addCriterion("PCR_MC between", value1, value2, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcrMcNotBetween(String value1, String value2) {
            addCriterion("PCR_MC not between", value1, value2, "pcrMc");
            return (Criteria) this;
        }

        public Criteria andPcmbbmIsNull() {
            addCriterion("PCMBBM is null");
            return (Criteria) this;
        }

        public Criteria andPcmbbmIsNotNull() {
            addCriterion("PCMBBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcmbbmEqualTo(String value) {
            addCriterion("PCMBBM =", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmNotEqualTo(String value) {
            addCriterion("PCMBBM <>", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmGreaterThan(String value) {
            addCriterion("PCMBBM >", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCMBBM >=", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmLessThan(String value) {
            addCriterion("PCMBBM <", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmLessThanOrEqualTo(String value) {
            addCriterion("PCMBBM <=", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmLike(String value) {
            addCriterion("PCMBBM like", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmNotLike(String value) {
            addCriterion("PCMBBM not like", value, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmIn(List<String> values) {
            addCriterion("PCMBBM in", values, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmNotIn(List<String> values) {
            addCriterion("PCMBBM not in", values, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmBetween(String value1, String value2) {
            addCriterion("PCMBBM between", value1, value2, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andPcmbbmNotBetween(String value1, String value2) {
            addCriterion("PCMBBM not between", value1, value2, "pcmbbm");
            return (Criteria) this;
        }

        public Criteria andAjglztIsNull() {
            addCriterion("AJGLZT is null");
            return (Criteria) this;
        }

        public Criteria andAjglztIsNotNull() {
            addCriterion("AJGLZT is not null");
            return (Criteria) this;
        }

        public Criteria andAjglztEqualTo(String value) {
            addCriterion("AJGLZT =", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztNotEqualTo(String value) {
            addCriterion("AJGLZT <>", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztGreaterThan(String value) {
            addCriterion("AJGLZT >", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztGreaterThanOrEqualTo(String value) {
            addCriterion("AJGLZT >=", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztLessThan(String value) {
            addCriterion("AJGLZT <", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztLessThanOrEqualTo(String value) {
            addCriterion("AJGLZT <=", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztLike(String value) {
            addCriterion("AJGLZT like", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztNotLike(String value) {
            addCriterion("AJGLZT not like", value, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztIn(List<String> values) {
            addCriterion("AJGLZT in", values, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztNotIn(List<String> values) {
            addCriterion("AJGLZT not in", values, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztBetween(String value1, String value2) {
            addCriterion("AJGLZT between", value1, value2, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andAjglztNotBetween(String value1, String value2) {
            addCriterion("AJGLZT not between", value1, value2, "ajglzt");
            return (Criteria) this;
        }

        public Criteria andPcjgIsNull() {
            addCriterion("PCJG is null");
            return (Criteria) this;
        }

        public Criteria andPcjgIsNotNull() {
            addCriterion("PCJG is not null");
            return (Criteria) this;
        }

        public Criteria andPcjgEqualTo(String value) {
            addCriterion("PCJG =", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgNotEqualTo(String value) {
            addCriterion("PCJG <>", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgGreaterThan(String value) {
            addCriterion("PCJG >", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgGreaterThanOrEqualTo(String value) {
            addCriterion("PCJG >=", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgLessThan(String value) {
            addCriterion("PCJG <", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgLessThanOrEqualTo(String value) {
            addCriterion("PCJG <=", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgLike(String value) {
            addCriterion("PCJG like", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgNotLike(String value) {
            addCriterion("PCJG not like", value, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgIn(List<String> values) {
            addCriterion("PCJG in", values, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgNotIn(List<String> values) {
            addCriterion("PCJG not in", values, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgBetween(String value1, String value2) {
            addCriterion("PCJG between", value1, value2, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjgNotBetween(String value1, String value2) {
            addCriterion("PCJG not between", value1, value2, "pcjg");
            return (Criteria) this;
        }

        public Criteria andPcjlIsNull() {
            addCriterion("PCJL is null");
            return (Criteria) this;
        }

        public Criteria andPcjlIsNotNull() {
            addCriterion("PCJL is not null");
            return (Criteria) this;
        }

        public Criteria andPcjlEqualTo(String value) {
            addCriterion("PCJL =", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlNotEqualTo(String value) {
            addCriterion("PCJL <>", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlGreaterThan(String value) {
            addCriterion("PCJL >", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlGreaterThanOrEqualTo(String value) {
            addCriterion("PCJL >=", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlLessThan(String value) {
            addCriterion("PCJL <", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlLessThanOrEqualTo(String value) {
            addCriterion("PCJL <=", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlLike(String value) {
            addCriterion("PCJL like", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlNotLike(String value) {
            addCriterion("PCJL not like", value, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlIn(List<String> values) {
            addCriterion("PCJL in", values, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlNotIn(List<String> values) {
            addCriterion("PCJL not in", values, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlBetween(String value1, String value2) {
            addCriterion("PCJL between", value1, value2, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcjlNotBetween(String value1, String value2) {
            addCriterion("PCJL not between", value1, value2, "pcjl");
            return (Criteria) this;
        }

        public Criteria andPcbgbhIsNull() {
            addCriterion("PCBGBH is null");
            return (Criteria) this;
        }

        public Criteria andPcbgbhIsNotNull() {
            addCriterion("PCBGBH is not null");
            return (Criteria) this;
        }

        public Criteria andPcbgbhEqualTo(String value) {
            addCriterion("PCBGBH =", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhNotEqualTo(String value) {
            addCriterion("PCBGBH <>", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhGreaterThan(String value) {
            addCriterion("PCBGBH >", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhGreaterThanOrEqualTo(String value) {
            addCriterion("PCBGBH >=", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhLessThan(String value) {
            addCriterion("PCBGBH <", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhLessThanOrEqualTo(String value) {
            addCriterion("PCBGBH <=", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhLike(String value) {
            addCriterion("PCBGBH like", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhNotLike(String value) {
            addCriterion("PCBGBH not like", value, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhIn(List<String> values) {
            addCriterion("PCBGBH in", values, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhNotIn(List<String> values) {
            addCriterion("PCBGBH not in", values, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhBetween(String value1, String value2) {
            addCriterion("PCBGBH between", value1, value2, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andPcbgbhNotBetween(String value1, String value2) {
            addCriterion("PCBGBH not between", value1, value2, "pcbgbh");
            return (Criteria) this;
        }

        public Criteria andEtlCsztIsNull() {
            addCriterion("ETL_CSZT is null");
            return (Criteria) this;
        }

        public Criteria andEtlCsztIsNotNull() {
            addCriterion("ETL_CSZT is not null");
            return (Criteria) this;
        }

        public Criteria andEtlCsztEqualTo(String value) {
            addCriterion("ETL_CSZT =", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztNotEqualTo(String value) {
            addCriterion("ETL_CSZT <>", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztGreaterThan(String value) {
            addCriterion("ETL_CSZT >", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztGreaterThanOrEqualTo(String value) {
            addCriterion("ETL_CSZT >=", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztLessThan(String value) {
            addCriterion("ETL_CSZT <", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztLessThanOrEqualTo(String value) {
            addCriterion("ETL_CSZT <=", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztLike(String value) {
            addCriterion("ETL_CSZT like", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztNotLike(String value) {
            addCriterion("ETL_CSZT not like", value, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztIn(List<String> values) {
            addCriterion("ETL_CSZT in", values, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztNotIn(List<String> values) {
            addCriterion("ETL_CSZT not in", values, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztBetween(String value1, String value2) {
            addCriterion("ETL_CSZT between", value1, value2, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCsztNotBetween(String value1, String value2) {
            addCriterion("ETL_CSZT not between", value1, value2, "etlCszt");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsIsNull() {
            addCriterion("ETL_CSSBCS is null");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsIsNotNull() {
            addCriterion("ETL_CSSBCS is not null");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsEqualTo(BigDecimal value) {
            addCriterion("ETL_CSSBCS =", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsNotEqualTo(BigDecimal value) {
            addCriterion("ETL_CSSBCS <>", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsGreaterThan(BigDecimal value) {
            addCriterion("ETL_CSSBCS >", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ETL_CSSBCS >=", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsLessThan(BigDecimal value) {
            addCriterion("ETL_CSSBCS <", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ETL_CSSBCS <=", value, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsIn(List<BigDecimal> values) {
            addCriterion("ETL_CSSBCS in", values, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsNotIn(List<BigDecimal> values) {
            addCriterion("ETL_CSSBCS not in", values, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ETL_CSSBCS between", value1, value2, "etlCssbcs");
            return (Criteria) this;
        }

        public Criteria andEtlCssbcsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ETL_CSSBCS not between", value1, value2, "etlCssbcs");
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

        public Criteria andSxgzbmIsNull() {
            addCriterion("SXGZBM is null");
            return (Criteria) this;
        }

        public Criteria andSxgzbmIsNotNull() {
            addCriterion("SXGZBM is not null");
            return (Criteria) this;
        }

        public Criteria andSxgzbmEqualTo(String value) {
            addCriterion("SXGZBM =", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmNotEqualTo(String value) {
            addCriterion("SXGZBM <>", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmGreaterThan(String value) {
            addCriterion("SXGZBM >", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmGreaterThanOrEqualTo(String value) {
            addCriterion("SXGZBM >=", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmLessThan(String value) {
            addCriterion("SXGZBM <", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmLessThanOrEqualTo(String value) {
            addCriterion("SXGZBM <=", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmLike(String value) {
            addCriterion("SXGZBM like", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmNotLike(String value) {
            addCriterion("SXGZBM not like", value, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmIn(List<String> values) {
            addCriterion("SXGZBM in", values, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmNotIn(List<String> values) {
            addCriterion("SXGZBM not in", values, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmBetween(String value1, String value2) {
            addCriterion("SXGZBM between", value1, value2, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andSxgzbmNotBetween(String value1, String value2) {
            addCriterion("SXGZBM not between", value1, value2, "sxgzbm");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqIsNull() {
            addCriterion("BPC_WCBZRQ is null");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqIsNotNull() {
            addCriterion("BPC_WCBZRQ is not null");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqEqualTo(Date value) {
            addCriterion("BPC_WCBZRQ =", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqNotEqualTo(Date value) {
            addCriterion("BPC_WCBZRQ <>", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqGreaterThan(Date value) {
            addCriterion("BPC_WCBZRQ >", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqGreaterThanOrEqualTo(Date value) {
            addCriterion("BPC_WCBZRQ >=", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqLessThan(Date value) {
            addCriterion("BPC_WCBZRQ <", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqLessThanOrEqualTo(Date value) {
            addCriterion("BPC_WCBZRQ <=", value, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqIn(List<Date> values) {
            addCriterion("BPC_WCBZRQ in", values, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqNotIn(List<Date> values) {
            addCriterion("BPC_WCBZRQ not in", values, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqBetween(Date value1, Date value2) {
            addCriterion("BPC_WCBZRQ between", value1, value2, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andBpcWcbzrqNotBetween(Date value1, Date value2) {
            addCriterion("BPC_WCBZRQ not between", value1, value2, "bpcWcbzrq");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcIsNull() {
            addCriterion("SXR_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcIsNotNull() {
            addCriterion("SXR_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcEqualTo(String value) {
            addCriterion("SXR_DWMC =", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcNotEqualTo(String value) {
            addCriterion("SXR_DWMC <>", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcGreaterThan(String value) {
            addCriterion("SXR_DWMC >", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("SXR_DWMC >=", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcLessThan(String value) {
            addCriterion("SXR_DWMC <", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcLessThanOrEqualTo(String value) {
            addCriterion("SXR_DWMC <=", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcLike(String value) {
            addCriterion("SXR_DWMC like", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcNotLike(String value) {
            addCriterion("SXR_DWMC not like", value, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcIn(List<String> values) {
            addCriterion("SXR_DWMC in", values, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcNotIn(List<String> values) {
            addCriterion("SXR_DWMC not in", values, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcBetween(String value1, String value2) {
            addCriterion("SXR_DWMC between", value1, value2, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrDwmcNotBetween(String value1, String value2) {
            addCriterion("SXR_DWMC not between", value1, value2, "sxrDwmc");
            return (Criteria) this;
        }

        public Criteria andSxrMcIsNull() {
            addCriterion("SXR_MC is null");
            return (Criteria) this;
        }

        public Criteria andSxrMcIsNotNull() {
            addCriterion("SXR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andSxrMcEqualTo(String value) {
            addCriterion("SXR_MC =", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcNotEqualTo(String value) {
            addCriterion("SXR_MC <>", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcGreaterThan(String value) {
            addCriterion("SXR_MC >", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcGreaterThanOrEqualTo(String value) {
            addCriterion("SXR_MC >=", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcLessThan(String value) {
            addCriterion("SXR_MC <", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcLessThanOrEqualTo(String value) {
            addCriterion("SXR_MC <=", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcLike(String value) {
            addCriterion("SXR_MC like", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcNotLike(String value) {
            addCriterion("SXR_MC not like", value, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcIn(List<String> values) {
            addCriterion("SXR_MC in", values, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcNotIn(List<String> values) {
            addCriterion("SXR_MC not in", values, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcBetween(String value1, String value2) {
            addCriterion("SXR_MC between", value1, value2, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andSxrMcNotBetween(String value1, String value2) {
            addCriterion("SXR_MC not between", value1, value2, "sxrMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcIsNull() {
            addCriterion("FPDZ_FPR_DWMC is null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcIsNotNull() {
            addCriterion("FPDZ_FPR_DWMC is not null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWMC =", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcNotEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWMC <>", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcGreaterThan(String value) {
            addCriterion("FPDZ_FPR_DWMC >", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcGreaterThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWMC >=", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcLessThan(String value) {
            addCriterion("FPDZ_FPR_DWMC <", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcLessThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_DWMC <=", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcLike(String value) {
            addCriterion("FPDZ_FPR_DWMC like", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcNotLike(String value) {
            addCriterion("FPDZ_FPR_DWMC not like", value, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcIn(List<String> values) {
            addCriterion("FPDZ_FPR_DWMC in", values, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcNotIn(List<String> values) {
            addCriterion("FPDZ_FPR_DWMC not in", values, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_DWMC between", value1, value2, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprDwmcNotBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_DWMC not between", value1, value2, "fpdzFprDwmc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcIsNull() {
            addCriterion("FPDZ_FPR_MC is null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcIsNotNull() {
            addCriterion("FPDZ_FPR_MC is not null");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcEqualTo(String value) {
            addCriterion("FPDZ_FPR_MC =", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcNotEqualTo(String value) {
            addCriterion("FPDZ_FPR_MC <>", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcGreaterThan(String value) {
            addCriterion("FPDZ_FPR_MC >", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcGreaterThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_MC >=", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcLessThan(String value) {
            addCriterion("FPDZ_FPR_MC <", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcLessThanOrEqualTo(String value) {
            addCriterion("FPDZ_FPR_MC <=", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcLike(String value) {
            addCriterion("FPDZ_FPR_MC like", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcNotLike(String value) {
            addCriterion("FPDZ_FPR_MC not like", value, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcIn(List<String> values) {
            addCriterion("FPDZ_FPR_MC in", values, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcNotIn(List<String> values) {
            addCriterion("FPDZ_FPR_MC not in", values, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_MC between", value1, value2, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andFpdzFprMcNotBetween(String value1, String value2) {
            addCriterion("FPDZ_FPR_MC not between", value1, value2, "fpdzFprMc");
            return (Criteria) this;
        }

        public Criteria andCljgIsNull() {
            addCriterion("CLJG is null");
            return (Criteria) this;
        }

        public Criteria andCljgIsNotNull() {
            addCriterion("CLJG is not null");
            return (Criteria) this;
        }

        public Criteria andCljgEqualTo(String value) {
            addCriterion("CLJG =", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgNotEqualTo(String value) {
            addCriterion("CLJG <>", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgGreaterThan(String value) {
            addCriterion("CLJG >", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgGreaterThanOrEqualTo(String value) {
            addCriterion("CLJG >=", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgLessThan(String value) {
            addCriterion("CLJG <", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgLessThanOrEqualTo(String value) {
            addCriterion("CLJG <=", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgLike(String value) {
            addCriterion("CLJG like", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgNotLike(String value) {
            addCriterion("CLJG not like", value, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgIn(List<String> values) {
            addCriterion("CLJG in", values, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgNotIn(List<String> values) {
            addCriterion("CLJG not in", values, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgBetween(String value1, String value2) {
            addCriterion("CLJG between", value1, value2, "cljg");
            return (Criteria) this;
        }

        public Criteria andCljgNotBetween(String value1, String value2) {
            addCriterion("CLJG not between", value1, value2, "cljg");
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