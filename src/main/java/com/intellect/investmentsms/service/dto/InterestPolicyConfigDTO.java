package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.invest.domain.IntrestPolicyConfig} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterestPolicyConfigDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private Long productId;

	private Integer tenureType;

	private Integer minTenure;

	private Integer maxTenure;

	private Float generalRoi;

	private Float penalRoi;

	private Long effectiveStartDate;

	private Long effectiveEndDate;

	private Integer status;

    private Integer foreClosureInterestApplicableAs;
	
	private Boolean foreClosurePenaltyRoiApplicable;
	
	private Integer renewalInterestApplicableAs;
	
	private String statusName;

	private String productName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getTenureType() {
		return tenureType;
	}

	public void setTenureType(Integer tenureType) {
		this.tenureType = tenureType;
	}

	public Integer getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(Integer minTenure) {
		this.minTenure = minTenure;
	}

	public Integer getMaxTenure() {
		return maxTenure;
	}

	public void setMaxTenure(Integer maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Float getGeneralRoi() {
		return generalRoi;
	}

	public void setGeneralRoi(Float generalRoi) {
		this.generalRoi = generalRoi;
	}

	public Float getPenalRoi() {
		return penalRoi;
	}

	public void setPenalRoi(Float penalRoi) {
		this.penalRoi = penalRoi;
	}

	public Long getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Long effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Long getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Long effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public Integer getStatus() {
		return status;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof InterestPolicyConfigDTO)) {
			return false;
		}

		InterestPolicyConfigDTO intrestPolicyConfigDTO = (InterestPolicyConfigDTO) o;
		if (this.id == null) {
			return false;
		}
		return Objects.equals(this.id, intrestPolicyConfigDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "InterestPolicyConfigDTO [id=" + id + ", productId=" + productId + ", tenureType=" + tenureType
				+ ", minTenure=" + minTenure + ", maxTenure=" + maxTenure + ", generalRoi=" + generalRoi + ", penalRoi="
				+ penalRoi + ", effectiveStartDate=" + effectiveStartDate + ", effectiveEndDate=" + effectiveEndDate
				+ ", status=" + status + ", foreClosureInterestApplicableAs=" + foreClosureInterestApplicableAs
				+ ", foreClosurePenaltyRoiApplicable=" + foreClosurePenaltyRoiApplicable
				+ ", renewalInterestApplicableAs=" + renewalInterestApplicableAs + ", statusName=" + statusName
				+ ", productName=" + productName + "]";
	}
	
}
