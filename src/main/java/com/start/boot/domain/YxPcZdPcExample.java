package com.start.boot.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class YxPcZdPcExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YxPcZdPcExample() {
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

        public Criteria andPcslBmIsNull() {
            addCriterion("PCSL_BM is null");
            return (Criteria) this;
        }

        public Criteria andPcslBmIsNotNull() {
            addCriterion("PCSL_BM is not null");
            return (Criteria) this;
        }

        public Criteria andPcslBmEqualTo(String value) {
            addCriterion("PCSL_BM =", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmNotEqualTo(String value) {
            addCriterion("PCSL_BM <>", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmGreaterThan(String value) {
            addCriterion("PCSL_BM >", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmGreaterThanOrEqualTo(String value) {
            addCriterion("PCSL_BM >=", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmLessThan(String value) {
            addCriterion("PCSL_BM <", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmLessThanOrEqualTo(String value) {
            addCriterion("PCSL_BM <=", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmLike(String value) {
            addCriterion("PCSL_BM like", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmNotLike(String value) {
            addCriterion("PCSL_BM not like", value, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmIn(List<String> values) {
            addCriterion("PCSL_BM in", values, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmNotIn(List<String> values) {
            addCriterion("PCSL_BM not in", values, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmBetween(String value1, String value2) {
            addCriterion("PCSL_BM between", value1, value2, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcslBmNotBetween(String value1, String value2) {
            addCriterion("PCSL_BM not between", value1, value2, "pcslBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmIsNull() {
            addCriterion("PCX_BM is null");
            return (Criteria) this;
        }

        public Criteria andPcxBmIsNotNull() {
            addCriterion("PCX_BM is not null");
            return (Criteria) this;
        }

        public Criteria andPcxBmEqualTo(String value) {
            addCriterion("PCX_BM =", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmNotEqualTo(String value) {
            addCriterion("PCX_BM <>", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmGreaterThan(String value) {
            addCriterion("PCX_BM >", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmGreaterThanOrEqualTo(String value) {
            addCriterion("PCX_BM >=", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmLessThan(String value) {
            addCriterion("PCX_BM <", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmLessThanOrEqualTo(String value) {
            addCriterion("PCX_BM <=", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmLike(String value) {
            addCriterion("PCX_BM like", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmNotLike(String value) {
            addCriterion("PCX_BM not like", value, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmIn(List<String> values) {
            addCriterion("PCX_BM in", values, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmNotIn(List<String> values) {
            addCriterion("PCX_BM not in", values, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmBetween(String value1, String value2) {
            addCriterion("PCX_BM between", value1, value2, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPcxBmNotBetween(String value1, String value2) {
            addCriterion("PCX_BM not between", value1, value2, "pcxBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmIsNull() {
            addCriterion("PCLB_BM is null");
            return (Criteria) this;
        }

        public Criteria andPclbBmIsNotNull() {
            addCriterion("PCLB_BM is not null");
            return (Criteria) this;
        }

        public Criteria andPclbBmEqualTo(String value) {
            addCriterion("PCLB_BM =", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmNotEqualTo(String value) {
            addCriterion("PCLB_BM <>", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmGreaterThan(String value) {
            addCriterion("PCLB_BM >", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmGreaterThanOrEqualTo(String value) {
            addCriterion("PCLB_BM >=", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmLessThan(String value) {
            addCriterion("PCLB_BM <", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmLessThanOrEqualTo(String value) {
            addCriterion("PCLB_BM <=", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmLike(String value) {
            addCriterion("PCLB_BM like", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmNotLike(String value) {
            addCriterion("PCLB_BM not like", value, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmIn(List<String> values) {
            addCriterion("PCLB_BM in", values, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmNotIn(List<String> values) {
            addCriterion("PCLB_BM not in", values, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmBetween(String value1, String value2) {
            addCriterion("PCLB_BM between", value1, value2, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPclbBmNotBetween(String value1, String value2) {
            addCriterion("PCLB_BM not between", value1, value2, "pclbBm");
            return (Criteria) this;
        }

        public Criteria andPcxMcIsNull() {
            addCriterion("PCX_MC is null");
            return (Criteria) this;
        }

        public Criteria andPcxMcIsNotNull() {
            addCriterion("PCX_MC is not null");
            return (Criteria) this;
        }

        public Criteria andPcxMcEqualTo(String value) {
            addCriterion("PCX_MC =", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcNotEqualTo(String value) {
            addCriterion("PCX_MC <>", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcGreaterThan(String value) {
            addCriterion("PCX_MC >", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcGreaterThanOrEqualTo(String value) {
            addCriterion("PCX_MC >=", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcLessThan(String value) {
            addCriterion("PCX_MC <", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcLessThanOrEqualTo(String value) {
            addCriterion("PCX_MC <=", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcLike(String value) {
            addCriterion("PCX_MC like", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcNotLike(String value) {
            addCriterion("PCX_MC not like", value, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcIn(List<String> values) {
            addCriterion("PCX_MC in", values, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcNotIn(List<String> values) {
            addCriterion("PCX_MC not in", values, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcBetween(String value1, String value2) {
            addCriterion("PCX_MC between", value1, value2, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxMcNotBetween(String value1, String value2) {
            addCriterion("PCX_MC not between", value1, value2, "pcxMc");
            return (Criteria) this;
        }

        public Criteria andPcxFzIsNull() {
            addCriterion("PCX_FZ is null");
            return (Criteria) this;
        }

        public Criteria andPcxFzIsNotNull() {
            addCriterion("PCX_FZ is not null");
            return (Criteria) this;
        }

        public Criteria andPcxFzEqualTo(BigDecimal value) {
            addCriterion("PCX_FZ =", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzNotEqualTo(BigDecimal value) {
            addCriterion("PCX_FZ <>", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzGreaterThan(BigDecimal value) {
            addCriterion("PCX_FZ >", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PCX_FZ >=", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzLessThan(BigDecimal value) {
            addCriterion("PCX_FZ <", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PCX_FZ <=", value, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzIn(List<BigDecimal> values) {
            addCriterion("PCX_FZ in", values, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzNotIn(List<BigDecimal> values) {
            addCriterion("PCX_FZ not in", values, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PCX_FZ between", value1, value2, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPcxFzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PCX_FZ not between", value1, value2, "pcxFz");
            return (Criteria) this;
        }

        public Criteria andPclyIsNull() {
            addCriterion("PCLY is null");
            return (Criteria) this;
        }

        public Criteria andPclyIsNotNull() {
            addCriterion("PCLY is not null");
            return (Criteria) this;
        }

        public Criteria andPclyEqualTo(String value) {
            addCriterion("PCLY =", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyNotEqualTo(String value) {
            addCriterion("PCLY <>", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyGreaterThan(String value) {
            addCriterion("PCLY >", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyGreaterThanOrEqualTo(String value) {
            addCriterion("PCLY >=", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyLessThan(String value) {
            addCriterion("PCLY <", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyLessThanOrEqualTo(String value) {
            addCriterion("PCLY <=", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyLike(String value) {
            addCriterion("PCLY like", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyNotLike(String value) {
            addCriterion("PCLY not like", value, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyIn(List<String> values) {
            addCriterion("PCLY in", values, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyNotIn(List<String> values) {
            addCriterion("PCLY not in", values, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyBetween(String value1, String value2) {
            addCriterion("PCLY between", value1, value2, "pcly");
            return (Criteria) this;
        }

        public Criteria andPclyNotBetween(String value1, String value2) {
            addCriterion("PCLY not between", value1, value2, "pcly");
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