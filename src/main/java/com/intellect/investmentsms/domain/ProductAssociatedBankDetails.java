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
 * A ProductAssociatedBankDetails.
 */
@Entity
@Table(name = "product_associated_bank_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductAssociatedBankDetails extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "invested_bank_details_id")
    private Long investedBankDetailsId;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductAssociatedBankDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public ProductAssociatedBankDetails productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getInvestedBankDetailsId() {
        return this.investedBankDetailsId;
    }

    public ProductAssociatedBankDetails investedBankDetailsId(Long investedBankDetailsId) {
        this.setInvestedBankDetailsId(investedBankDetailsId);
        return this;
    }

    public void setInvestedBankDetailsId(Long investedBankDetailsId) {
        this.investedBankDetailsId = investedBankDetailsId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ProductAssociatedBankDetails status(Integer status) {
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
        if (!(o instanceof ProductAssociatedBankDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((ProductAssociatedBankDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductAssociatedBankDetails{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", investedBankDetailsId=" + getInvestedBankDetailsId() +
            ", status=" + getStatus() +
            "}";
    }
}
