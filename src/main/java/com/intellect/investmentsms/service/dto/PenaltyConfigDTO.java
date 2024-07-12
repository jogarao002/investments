package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.PenaltyConfig}
 * entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PenaltyConfigDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private Long productId;

	private Double minAmount;

	private Double maxAmount;

	private Float penaltyAmount;

	private Long effectiveStartDate;

	private Long effectiveEndDate;

	private Integer status;
	
	private String productName;
	
	private String statusName;

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

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Float getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Float penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
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
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PenaltyConfigDTO)) {
			return false;
		}

		PenaltyConfigDTO penaltyConfigDTO = (PenaltyConfigDTO) o;
		if (this.id == null) {
			return false;
		}
		return Objects.equals(this.id, penaltyConfigDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "PenaltyConfigDTO [id=" + id + ", productId=" + productId + ", minAmount=" + minAmount + ", maxAmount="
				+ maxAmount + ", penaltyAmount=" + penaltyAmount + ", effectiveStartDate=" + effectiveStartDate
				+ ", effectiveEndDate=" + effectiveEndDate + ", status=" + status + ", productName=" + productName
				+ ", statusName=" + statusName + "]";
	}

	
}
