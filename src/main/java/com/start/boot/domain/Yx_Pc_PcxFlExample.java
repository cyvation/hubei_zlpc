package com.start.boot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Yx_Pc_PcxFlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Yx_Pc_PcxFlExample() {
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

        public Criteria andPcxflbmIsNull() {
            addCriterion("PCXFLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcxflbmIsNotNull() {
            addCriterion("PCXFLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcxflbmEqualTo(String value) {
            addCriterion("PCXFLBM =", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmNotEqualTo(String value) {
            addCriterion("PCXFLBM <>", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmGreaterThan(String value) {
            addCriterion("PCXFLBM >", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCXFLBM >=", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmLessThan(String value) {
            addCriterion("PCXFLBM <", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmLessThanOrEqualTo(String value) {
            addCriterion("PCXFLBM <=", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmLike(String value) {
            addCriterion("PCXFLBM like", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmNotLike(String value) {
            addCriterion("PCXFLBM not like", value, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmIn(List<String> values) {
            addCriterion("PCXFLBM in", values, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmNotIn(List<String> values) {
            addCriterion("PCXFLBM not in", values, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmBetween(String value1, String value2) {
            addCriterion("PCXFLBM between", value1, value2, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflbmNotBetween(String value1, String value2) {
            addCriterion("PCXFLBM not between", value1, value2, "pcxflbm");
            return (Criteria) this;
        }

        public Criteria andPcxflmcIsNull() {
            addCriterion("PCXFLMC is null");
            return (Criteria) this;
        }

        public Criteria andPcxflmcIsNotNull() {
            addCriterion("PCXFLMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcxflmcEqualTo(String value) {
            addCriterion("PCXFLMC =", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcNotEqualTo(String value) {
            addCriterion("PCXFLMC <>", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcGreaterThan(String value) {
            addCriterion("PCXFLMC >", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCXFLMC >=", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcLessThan(String value) {
            addCriterion("PCXFLMC <", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcLessThanOrEqualTo(String value) {
            addCriterion("PCXFLMC <=", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcLike(String value) {
            addCriterion("PCXFLMC like", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcNotLike(String value) {
            addCriterion("PCXFLMC not like", value, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcIn(List<String> values) {
            addCriterion("PCXFLMC in", values, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcNotIn(List<String> values) {
            addCriterion("PCXFLMC not in", values, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcBetween(String value1, String value2) {
            addCriterion("PCXFLMC between", value1, value2, "pcxflmc");
            return (Criteria) this;
        }

        public Criteria andPcxflmcNotBetween(String value1, String value2) {
            addCriterion("PCXFLMC not between", value1, value2, "pcxflmc");
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

        public Criteria andPcyjIsNull() {
            addCriterion("PCYJ is null");
            return (Criteria) this;
        }

        public Criteria andPcyjIsNotNull() {
            addCriterion("PCYJ is not null");
            return (Criteria) this;
        }

        public Criteria andPcyjEqualTo(String value) {
            addCriterion("PCYJ =", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjNotEqualTo(String value) {
            addCriterion("PCYJ <>", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjGreaterThan(String value) {
            addCriterion("PCYJ >", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjGreaterThanOrEqualTo(String value) {
            addCriterion("PCYJ >=", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjLessThan(String value) {
            addCriterion("PCYJ <", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjLessThanOrEqualTo(String value) {
            addCriterion("PCYJ <=", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjLike(String value) {
            addCriterion("PCYJ like", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjNotLike(String value) {
            addCriterion("PCYJ not like", value, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjIn(List<String> values) {
            addCriterion("PCYJ in", values, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjNotIn(List<String> values) {
            addCriterion("PCYJ not in", values, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjBetween(String value1, String value2) {
            addCriterion("PCYJ between", value1, value2, "pcyj");
            return (Criteria) this;
        }

        public Criteria andPcyjNotBetween(String value1, String value2) {
            addCriterion("PCYJ not between", value1, value2, "pcyj");
            return (Criteria) this;
        }

        public Criteria andJlsjIsNull() {
            addCriterion("JLSJ is null");
            return (Criteria) this;
        }

        public Criteria andJlsjIsNotNull() {
            addCriterion("JLSJ is not null");
            return (Criteria) this;
        }

        public Criteria andJlsjEqualTo(Date value) {
            addCriterion("JLSJ =", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotEqualTo(Date value) {
            addCriterion("JLSJ <>", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjGreaterThan(Date value) {
            addCriterion("JLSJ >", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjGreaterThanOrEqualTo(Date value) {
            addCriterion("JLSJ >=", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjLessThan(Date value) {
            addCriterion("JLSJ <", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjLessThanOrEqualTo(Date value) {
            addCriterion("JLSJ <=", value, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjIn(List<Date> values) {
            addCriterion("JLSJ in", values, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotIn(List<Date> values) {
            addCriterion("JLSJ not in", values, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjBetween(Date value1, Date value2) {
            addCriterion("JLSJ between", value1, value2, "jlsj");
            return (Criteria) this;
        }

        public Criteria andJlsjNotBetween(Date value1, Date value2) {
            addCriterion("JLSJ not between", value1, value2, "jlsj");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIsNull() {
            addCriterion("PCRDWBM is null");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIsNotNull() {
            addCriterion("PCRDWBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmEqualTo(String value) {
            addCriterion("PCRDWBM =", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotEqualTo(String value) {
            addCriterion("PCRDWBM <>", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmGreaterThan(String value) {
            addCriterion("PCRDWBM >", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCRDWBM >=", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLessThan(String value) {
            addCriterion("PCRDWBM <", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLessThanOrEqualTo(String value) {
            addCriterion("PCRDWBM <=", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmLike(String value) {
            addCriterion("PCRDWBM like", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotLike(String value) {
            addCriterion("PCRDWBM not like", value, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmIn(List<String> values) {
            addCriterion("PCRDWBM in", values, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotIn(List<String> values) {
            addCriterion("PCRDWBM not in", values, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmBetween(String value1, String value2) {
            addCriterion("PCRDWBM between", value1, value2, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwbmNotBetween(String value1, String value2) {
            addCriterion("PCRDWBM not between", value1, value2, "pcrdwbm");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIsNull() {
            addCriterion("PCRDWMC is null");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIsNotNull() {
            addCriterion("PCRDWMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcEqualTo(String value) {
            addCriterion("PCRDWMC =", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotEqualTo(String value) {
            addCriterion("PCRDWMC <>", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcGreaterThan(String value) {
            addCriterion("PCRDWMC >", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCRDWMC >=", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLessThan(String value) {
            addCriterion("PCRDWMC <", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLessThanOrEqualTo(String value) {
            addCriterion("PCRDWMC <=", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcLike(String value) {
            addCriterion("PCRDWMC like", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotLike(String value) {
            addCriterion("PCRDWMC not like", value, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcIn(List<String> values) {
            addCriterion("PCRDWMC in", values, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotIn(List<String> values) {
            addCriterion("PCRDWMC not in", values, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcBetween(String value1, String value2) {
            addCriterion("PCRDWMC between", value1, value2, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrdwmcNotBetween(String value1, String value2) {
            addCriterion("PCRDWMC not between", value1, value2, "pcrdwmc");
            return (Criteria) this;
        }

        public Criteria andPcrghIsNull() {
            addCriterion("PCRGH is null");
            return (Criteria) this;
        }

        public Criteria andPcrghIsNotNull() {
            addCriterion("PCRGH is not null");
            return (Criteria) this;
        }

        public Criteria andPcrghEqualTo(String value) {
            addCriterion("PCRGH =", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotEqualTo(String value) {
            addCriterion("PCRGH <>", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghGreaterThan(String value) {
            addCriterion("PCRGH >", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghGreaterThanOrEqualTo(String value) {
            addCriterion("PCRGH >=", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLessThan(String value) {
            addCriterion("PCRGH <", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLessThanOrEqualTo(String value) {
            addCriterion("PCRGH <=", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghLike(String value) {
            addCriterion("PCRGH like", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotLike(String value) {
            addCriterion("PCRGH not like", value, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghIn(List<String> values) {
            addCriterion("PCRGH in", values, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotIn(List<String> values) {
            addCriterion("PCRGH not in", values, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghBetween(String value1, String value2) {
            addCriterion("PCRGH between", value1, value2, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrghNotBetween(String value1, String value2) {
            addCriterion("PCRGH not between", value1, value2, "pcrgh");
            return (Criteria) this;
        }

        public Criteria andPcrmcIsNull() {
            addCriterion("PCRMC is null");
            return (Criteria) this;
        }

        public Criteria andPcrmcIsNotNull() {
            addCriterion("PCRMC is not null");
            return (Criteria) this;
        }

        public Criteria andPcrmcEqualTo(String value) {
            addCriterion("PCRMC =", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotEqualTo(String value) {
            addCriterion("PCRMC <>", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcGreaterThan(String value) {
            addCriterion("PCRMC >", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcGreaterThanOrEqualTo(String value) {
            addCriterion("PCRMC >=", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLessThan(String value) {
            addCriterion("PCRMC <", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLessThanOrEqualTo(String value) {
            addCriterion("PCRMC <=", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcLike(String value) {
            addCriterion("PCRMC like", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotLike(String value) {
            addCriterion("PCRMC not like", value, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcIn(List<String> values) {
            addCriterion("PCRMC in", values, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotIn(List<String> values) {
            addCriterion("PCRMC not in", values, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcBetween(String value1, String value2) {
            addCriterion("PCRMC between", value1, value2, "pcrmc");
            return (Criteria) this;
        }

        public Criteria andPcrmcNotBetween(String value1, String value2) {
            addCriterion("PCRMC not between", value1, value2, "pcrmc");
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

        public Criteria andPcxflfbmIsNull() {
            addCriterion("PCXFLFBM is null");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmIsNotNull() {
            addCriterion("PCXFLFBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmEqualTo(String value) {
            addCriterion("PCXFLFBM =", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmNotEqualTo(String value) {
            addCriterion("PCXFLFBM <>", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmGreaterThan(String value) {
            addCriterion("PCXFLFBM >", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCXFLFBM >=", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmLessThan(String value) {
            addCriterion("PCXFLFBM <", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmLessThanOrEqualTo(String value) {
            addCriterion("PCXFLFBM <=", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmLike(String value) {
            addCriterion("PCXFLFBM like", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmNotLike(String value) {
            addCriterion("PCXFLFBM not like", value, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmIn(List<String> values) {
            addCriterion("PCXFLFBM in", values, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmNotIn(List<String> values) {
            addCriterion("PCXFLFBM not in", values, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmBetween(String value1, String value2) {
            addCriterion("PCXFLFBM between", value1, value2, "pcxflfbm");
            return (Criteria) this;
        }

        public Criteria andPcxflfbmNotBetween(String value1, String value2) {
            addCriterion("PCXFLFBM not between", value1, value2, "pcxflfbm");
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

        public Criteria andPcjlbmIsNull() {
            addCriterion("PCJLBM is null");
            return (Criteria) this;
        }

        public Criteria andPcjlbmIsNotNull() {
            addCriterion("PCJLBM is not null");
            return (Criteria) this;
        }

        public Criteria andPcjlbmEqualTo(String value) {
            addCriterion("PCJLBM =", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotEqualTo(String value) {
            addCriterion("PCJLBM <>", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmGreaterThan(String value) {
            addCriterion("PCJLBM >", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmGreaterThanOrEqualTo(String value) {
            addCriterion("PCJLBM >=", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLessThan(String value) {
            addCriterion("PCJLBM <", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLessThanOrEqualTo(String value) {
            addCriterion("PCJLBM <=", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmLike(String value) {
            addCriterion("PCJLBM like", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotLike(String value) {
            addCriterion("PCJLBM not like", value, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmIn(List<String> values) {
            addCriterion("PCJLBM in", values, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotIn(List<String> values) {
            addCriterion("PCJLBM not in", values, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmBetween(String value1, String value2) {
            addCriterion("PCJLBM between", value1, value2, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andPcjlbmNotBetween(String value1, String value2) {
            addCriterion("PCJLBM not between", value1, value2, "pcjlbm");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("XH is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("XH is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(Short value) {
            addCriterion("XH =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(Short value) {
            addCriterion("XH <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(Short value) {
            addCriterion("XH >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(Short value) {
            addCriterion("XH >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(Short value) {
            addCriterion("XH <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(Short value) {
            addCriterion("XH <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<Short> values) {
            addCriterion("XH in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<Short> values) {
            addCriterion("XH not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(Short value1, Short value2) {
            addCriterion("XH between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(Short value1, Short value2) {
            addCriterion("XH not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andSftjIsNull() {
            addCriterion("SFTJ is null");
            return (Criteria) this;
        }

        public Criteria andSftjIsNotNull() {
            addCriterion("SFTJ is not null");
            return (Criteria) this;
        }

        public Criteria andSftjEqualTo(String value) {
            addCriterion("SFTJ =", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotEqualTo(String value) {
            addCriterion("SFTJ <>", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjGreaterThan(String value) {
            addCriterion("SFTJ >", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjGreaterThanOrEqualTo(String value) {
            addCriterion("SFTJ >=", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLessThan(String value) {
            addCriterion("SFTJ <", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLessThanOrEqualTo(String value) {
            addCriterion("SFTJ <=", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjLike(String value) {
            addCriterion("SFTJ like", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotLike(String value) {
            addCriterion("SFTJ not like", value, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjIn(List<String> values) {
            addCriterion("SFTJ in", values, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotIn(List<String> values) {
            addCriterion("SFTJ not in", values, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjBetween(String value1, String value2) {
            addCriterion("SFTJ between", value1, value2, "sftj");
            return (Criteria) this;
        }

        public Criteria andSftjNotBetween(String value1, String value2) {
            addCriterion("SFTJ not between", value1, value2, "sftj");
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