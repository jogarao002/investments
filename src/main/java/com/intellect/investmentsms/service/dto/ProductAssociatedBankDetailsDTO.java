package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the
 * {@link com.intellect.invest.domain.ProductAssociatedBankDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductAssociatedBankDetailsDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private Long productId;

	private Long investedBankDetailsId;

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

	public Long getInvestedBankDetailsId() {
		return investedBankDetailsId;
	}

	public void setInvestedBankDetailsId(Long investedBankDetailsId) {
		this.investedBankDetailsId = investedBankDetailsId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductAssociatedBankDetailsDTO)) {
			return false;
		}

		ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO = (ProductAssociatedBankDetailsDTO) o;
		if (this.id == null) {
			return false;
		}
		return Objects.equals(this.id, productAssociatedBankDetailsDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "ProductAssociatedBankDetailsDTO [id=" + id + ", productId=" + productId + ", investedBankDetailsId="
				+ investedBankDetailsId + ", status=" + status + ", productName=" + productName + ", statusName="
				+ statusName + "]";
	}

	// prettier-ignore

}
