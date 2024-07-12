package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.RequiredDocuments} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequiredDocumentsDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private Long productId;

    private Long documentTypeId;

    private Boolean isMandatory;

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

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
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
        if (!(o instanceof RequiredDocumentsDTO)) {
            return false;
        }

        RequiredDocumentsDTO requiredDocumentsDTO = (RequiredDocumentsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requiredDocumentsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "RequiredDocumentsDTO [id=" + id + ", productId=" + productId + ", documentTypeId=" + documentTypeId
				+ ", isMandatory=" + isMandatory + ", effectiveStartDate=" + effectiveStartDate + ", effectiveEndDate="
				+ effectiveEndDate + ", status=" + status + ", productName=" + productName + ", statusName="
				+ statusName + "]";
	}

    
}
