package com.intellect.investmentsms.domain;

import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A InterestPolicyConfig.
 */
@Entity
@Table(name = "interest_policy_config")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterestPolicyConfig extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tenure_type")
    private Integer tenureType;

    @Column(name = "min_tenure")
    private Integer minTenure;

    @Column(name = "max_tenure")
    private Integer maxTenure;

    @Column(name = "general_roi")
    private Float generalRoi;

    @Column(name = "penal_roi")
    private Float penalRoi;

    @Column(name = "effective_start_date")
    private Long effectiveStartDate;

    @Column(name = "effective_end_date")
    private Long effectiveEndDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "fore_closure_interest_applicable_as")
    private Integer foreClosureInterestApplicableAs;
	
    @Column(name = "fore_closure_penalty_roi_applicable")
	private Boolean foreClosurePenaltyRoiApplicable;
	
    @Column(name = "renewal_interest_applicable_as")
	private Integer renewalInterestApplicableAs;
	
    public Long getId() {
        return this.id;
    }

    public InterestPolicyConfig id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public InterestPolicyConfig productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getTenureType() {
        return this.tenureType;
    }

    public InterestPolicyConfig tenureType(Integer tenureType) {
        this.setTenureType(tenureType);
        return this;
    }

    public void setTenureType(Integer tenureType) {
        this.tenureType = tenureType;
    }

    public Integer getMinTenure() {
        return this.minTenure;
    }

    public InterestPolicyConfig minTenure(Integer minTenure) {
        this.setMinTenure(minTenure);
        return this;
    }

    public void setMinTenure(Integer minTenure) {
        this.minTenure = minTenure;
    }

    public Integer getMaxTenure() {
        return this.maxTenure;
    }

    public InterestPolicyConfig maxTenure(Integer maxTenure) {
        this.setMaxTenure(maxTenure);
        return this;
    }

    public void setMaxTenure(Integer maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Float getGeneralRoi() {
        return this.generalRoi;
    }

    public InterestPolicyConfig generalRoi(Float generalRoi) {
        this.setGeneralRoi(generalRoi);
        return this;
    }

    public void setGeneralRoi(Float generalRoi) {
        this.generalRoi = generalRoi;
    }

    public Float getPenalRoi() {
        return this.penalRoi;
    }

    public InterestPolicyConfig penalRoi(Float penalRoi) {
        this.setPenalRoi(penalRoi);
        return this;
    }

    public void setPenalRoi(Float penalRoi) {
        this.penalRoi = penalRoi;
    }

    public Long getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public InterestPolicyConfig effectiveStartDate(Long effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Long getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public InterestPolicyConfig effectiveEndDate(Long effectiveEndDate) {
        this.setEffectiveEndDate(effectiveEndDate);
        return this;
    }

    public void setEffectiveEndDate(Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InterestPolicyConfig status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getForeClosureInterestApplicableAs() {
		return foreClosureInterestApplicableAs;
	}

	public void setForeClosureInterestApplicableAs(Integer foreClosureInterestApplicableAs) {
		this.foreClosureInterestApplicableAs = foreClosureInterestApplicableAs;
	}

	public Boolean getForeClosurePenaltyRoiApplicable() {
		return foreClosurePenaltyRoiApplicable;
	}

	public void setForeClosurePenaltyRoiApplicable(Boolean foreClosurePenaltyRoiApplicable) {
		this.foreClosurePenaltyRoiApplicable = foreClosurePenaltyRoiApplicable;
	}

	public Integer getRenewalInterestApplicableAs() {
		return renewalInterestApplicableAs;
	}

	public void setRenewalInterestApplicableAs(Integer renewalInterestApplicableAs) {
		this.renewalInterestApplicableAs = renewalInterestApplicableAs;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterestPolicyConfig)) {
            return false;
        }
        return getId() != null && getId().equals(((InterestPolicyConfig) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "InterestPolicyConfig [id=" + id + ", productId=" + productId + ", tenureType=" + tenureType
				+ ", minTenure=" + minTenure + ", maxTenure=" + maxTenure + ", generalRoi=" + generalRoi + ", penalRoi="
				+ penalRoi + ", effectiveStartDate=" + effectiveStartDate + ", effectiveEndDate=" + effectiveEndDate
				+ ", status=" + status + ", foreClosureInterestApplicableAs=" + foreClosureInterestApplicableAs
				+ ", foreClosurePenaltyRoiApplicable=" + foreClosurePenaltyRoiApplicable
				+ ", renewalInterestApplicableAs=" + renewalInterestApplicableAs + "]";
	}

}
