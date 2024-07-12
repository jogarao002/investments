package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.InvestmentAccountDocuments} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountDocumentsDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private Long productId;
    
    private String productName;

    private Long termAccId;

    private Long requiredDocId;

    private Long requiredDocTypeId;

    private String requiredDocNumber;

    private String requiredDocPath;

    private Integer status;
    
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

    public Long getTermAccId() {
        return termAccId;
    }

    public void setTermAccId(Long termAccId) {
        this.termAccId = termAccId;
    }

    public Long getRequiredDocId() {
        return requiredDocId;
    }

    public void setRequiredDocId(Long requiredDocId) {
        this.requiredDocId = requiredDocId;
    }

    public Long getRequiredDocTypeId() {
        return requiredDocTypeId;
    }

    public void setRequiredDocTypeId(Long requiredDocTypeId) {
        this.requiredDocTypeId = requiredDocTypeId;
    }

    public String getRequiredDocNumber() {
        return requiredDocNumber;
    }

    public void setRequiredDocNumber(String requiredDocNumber) {
        this.requiredDocNumber = requiredDocNumber;
    }

    public String getRequiredDocPath() {
        return requiredDocPath;
    }

    public void setRequiredDocPath(String requiredDocPath) {
        this.requiredDocPath = requiredDocPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        if (!(o instanceof InvestmentAccountDocumentsDTO)) {
            return false;
        }

        InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO = (InvestmentAccountDocumentsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, investmentAccountDocumentsDTO.id);
    }

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvestmentAccountDocumentsDTO{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", termAccId=" + getTermAccId() +
            ", requiredDocId=" + getRequiredDocId() +
            ", requiredDocTypeId=" + getRequiredDocTypeId() +
            ", requiredDocNumber='" + getRequiredDocNumber() + "'" +
            ", requiredDocPath='" + getRequiredDocPath() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
