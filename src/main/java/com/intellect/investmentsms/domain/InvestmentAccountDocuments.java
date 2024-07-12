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
 * A InvestmentAccountDocuments.
 */
@Entity
@Table(name = "investment_account_documents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountDocuments extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "term_acc_id")
    private Long termAccId;

    @Column(name = "required_doc_id")
    private Long requiredDocId;

    @Column(name = "required_doc_type_id")
    private Long requiredDocTypeId;

    @Column(name = "required_doc_number")
    private String requiredDocNumber;

    @Column(name = "required_doc_path")
    private String requiredDocPath;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InvestmentAccountDocuments id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public InvestmentAccountDocuments productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTermAccId() {
        return this.termAccId;
    }

    public InvestmentAccountDocuments termAccId(Long termAccId) {
        this.setTermAccId(termAccId);
        return this;
    }

    public void setTermAccId(Long termAccId) {
        this.termAccId = termAccId;
    }

    public Long getRequiredDocId() {
        return this.requiredDocId;
    }

    public InvestmentAccountDocuments requiredDocId(Long requiredDocId) {
        this.setRequiredDocId(requiredDocId);
        return this;
    }

    public void setRequiredDocId(Long requiredDocId) {
        this.requiredDocId = requiredDocId;
    }

    public Long getRequiredDocTypeId() {
        return this.requiredDocTypeId;
    }

    public InvestmentAccountDocuments requiredDocTypeId(Long requiredDocTypeId) {
        this.setRequiredDocTypeId(requiredDocTypeId);
        return this;
    }

    public void setRequiredDocTypeId(Long requiredDocTypeId) {
        this.requiredDocTypeId = requiredDocTypeId;
    }

    public String getRequiredDocNumber() {
        return this.requiredDocNumber;
    }

    public InvestmentAccountDocuments requiredDocNumber(String requiredDocNumber) {
        this.setRequiredDocNumber(requiredDocNumber);
        return this;
    }

    public void setRequiredDocNumber(String requiredDocNumber) {
        this.requiredDocNumber = requiredDocNumber;
    }

    public String getRequiredDocPath() {
        return this.requiredDocPath;
    }

    public InvestmentAccountDocuments requiredDocPath(String requiredDocPath) {
        this.setRequiredDocPath(requiredDocPath);
        return this;
    }

    public void setRequiredDocPath(String requiredDocPath) {
        this.requiredDocPath = requiredDocPath;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InvestmentAccountDocuments status(Integer status) {
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
        if (!(o instanceof InvestmentAccountDocuments)) {
            return false;
        }
        return getId() != null && getId().equals(((InvestmentAccountDocuments) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvestmentAccountDocuments{" +
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
