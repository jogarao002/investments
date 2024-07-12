package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A RequiredDocuments.
 */
@Entity
@Table(name = "required_documents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequiredDocuments extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "document_type_id")
    private Long documentTypeId;

    @Column(name = "is_mandatory")
    private Boolean isMandatory;

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

    public RequiredDocuments id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public RequiredDocuments productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public RequiredDocuments documentTypeId(Long documentTypeId) {
        this.setDocumentTypeId(documentTypeId);
        return this;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Boolean getIsMandatory() {
        return this.isMandatory;
    }

    public RequiredDocuments isMandatory(Boolean isMandatory) {
        this.setIsMandatory(isMandatory);
        return this;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Long getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public RequiredDocuments effectiveStartDate(Long effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Long getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public RequiredDocuments effectiveEndDate(Long effectiveEndDate) {
        this.setEffectiveEndDate(effectiveEndDate);
        return this;
    }

    public void setEffectiveEndDate(Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public RequiredDocuments status(Integer status) {
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
        if (!(o instanceof RequiredDocuments)) {
            return false;
        }
        return getId() != null && getId().equals(((RequiredDocuments) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequiredDocuments{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", documentTypeId=" + getDocumentTypeId() +
            ", isMandatory='" + getIsMandatory() + "'" +
            ", effectiveStartDate=" + getEffectiveStartDate() +
            ", effectiveEndDate=" + getEffectiveEndDate() +
            ", status=" + getStatus() +
            "}";
    }
}
