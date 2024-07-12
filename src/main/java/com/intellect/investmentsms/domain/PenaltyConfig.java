package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A PenaltyConfig.
 */
@Entity
@Table(name = "penalty_config")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PenaltyConfig extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "min_amount")
    private Double minAmount;

    @Column(name = "max_amount")
    private Double maxAmount;

    @Column(name = "penalty_amount")
    private Float penaltyAmount;

    @Column(name = "effective_start_date")
    private Long effectiveStartDate;

    @Column(name = "effective_end_date")
    private Long effectiveEndDate;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PenaltyConfig id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public PenaltyConfig productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getMinAmount() {
        return this.minAmount;
    }

    public PenaltyConfig minAmount(Double minAmount) {
        this.setMinAmount(minAmount);
        return this;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return this.maxAmount;
    }

    public PenaltyConfig maxAmount(Double maxAmount) {
        this.setMaxAmount(maxAmount);
        return this;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Float getPenaltyAmount() {
        return this.penaltyAmount;
    }

    public PenaltyConfig penaltyAmount(Float penaltyAmount) {
        this.setPenaltyAmount(penaltyAmount);
        return this;
    }

    public void setPenaltyAmount(Float penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Long getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public PenaltyConfig effectiveStartDate(Long effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Long getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public PenaltyConfig effectiveEndDate(Long effectiveEndDate) {
        this.setEffectiveEndDate(effectiveEndDate);
        return this;
    }

    public void setEffectiveEndDate(Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public PenaltyConfig status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PenaltyConfig)) {
            return false;
        }
        return getId() != null && getId().equals(((PenaltyConfig) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PenaltyConfig{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", minAmount=" + getMinAmount() +
            ", maxAmount=" + getMaxAmount() +
            ", penaltyAmount=" + getPenaltyAmount() +
            ", effectiveStartDate=" + getEffectiveStartDate() +
            ", effectiveEndDate=" + getEffectiveEndDate() +
            ", status=" + getStatus() +
            "}";
    }
}
