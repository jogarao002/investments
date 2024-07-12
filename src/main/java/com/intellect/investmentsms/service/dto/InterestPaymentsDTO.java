package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.InterestPayments} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterestPaymentsDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private String admissionNumber;

    private Long termAccId;

    private Double interestAmount;

    private Long interestPostingDate;

    private Double foreClosureInterestAmount;

    private Integer days;

    private Long products;

    private Long foreClosureProducts;

    private Integer status;
    
    private String statusName;
    
    private String productName;

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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Long getTermAccId() {
        return termAccId;
    }

    public void setTermAccId(Long termAccId) {
        this.termAccId = termAccId;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Long getInterestPostingDate() {
        return interestPostingDate;
    }

    public void setInterestPostingDate(Long interestPostingDate) {
        this.interestPostingDate = interestPostingDate;
    }

    public Double getForeClosureInterestAmount() {
        return foreClosureInterestAmount;
    }

    public void setForeClosureInterestAmount(Double foreClosureInterestAmount) {
        this.foreClosureInterestAmount = foreClosureInterestAmount;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getProducts() {
        return products;
    }

    public void setProducts(Long products) {
        this.products = products;
    }

    public Long getForeClosureProducts() {
        return foreClosureProducts;
    }

    public void setForeClosureProducts(Long foreClosureProducts) {
        this.foreClosureProducts = foreClosureProducts;
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
        if (!(o instanceof InterestPaymentsDTO)) {
            return false;
        }

        InterestPaymentsDTO interestPaymentsDTO = (InterestPaymentsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, interestPaymentsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "InterestPaymentsDTO [id=" + id + ", admissionNumber=" + admissionNumber + ", termAccId=" + termAccId
				+ ", interestAmount=" + interestAmount + ", interestPostingDate=" + interestPostingDate
				+ ", foreClosureInterestAmount=" + foreClosureInterestAmount + ", days=" + days + ", products="
				+ products + ", foreClosureProducts=" + foreClosureProducts + ", status=" + status + ", statusName="
				+ statusName + ", productName=" + productName + "]";
	}

	
    // prettier-ignore
    
}
