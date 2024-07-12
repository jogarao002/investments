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
 * A InterestPayments.
 */
@Entity
@Table(name = "interest_payments")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InterestPayments extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "admission_number")
    private String admissionNumber;

    @Column(name = "term_acc_id")
    private Long termAccId;

    @Column(name = "interest_amount")
    private Double interestAmount;

    @Column(name = "interest_posting_date")
    private Long interestPostingDate;

    @Column(name = "fore_closure_interest_amount")
    private Double foreClosureInterestAmount;

    @Column(name = "days")
    private Integer days;

    @Column(name = "products")
    private Long products;

    @Column(name = "fore_closure_products")
    private Long foreClosureProducts;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InterestPayments id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmissionNumber() {
        return this.admissionNumber;
    }

    public InterestPayments admissionNumber(String admissionNumber) {
        this.setAdmissionNumber(admissionNumber);
        return this;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Long getTermAccId() {
        return this.termAccId;
    }

    public InterestPayments termAccId(Long termAccId) {
        this.setTermAccId(termAccId);
        return this;
    }

    public void setTermAccId(Long termAccId) {
        this.termAccId = termAccId;
    }

    public Double getInterestAmount() {
        return this.interestAmount;
    }

    public InterestPayments interestAmount(Double interestAmount) {
        this.setInterestAmount(interestAmount);
        return this;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Long getInterestPostingDate() {
        return this.interestPostingDate;
    }

    public InterestPayments interestPostingDate(Long interestPostingDate) {
        this.setInterestPostingDate(interestPostingDate);
        return this;
    }

    public void setInterestPostingDate(Long interestPostingDate) {
        this.interestPostingDate = interestPostingDate;
    }

    public Double getForeClosureInterestAmount() {
        return this.foreClosureInterestAmount;
    }

    public InterestPayments foreClosureInterestAmount(Double foreClosureInterestAmount) {
        this.setForeClosureInterestAmount(foreClosureInterestAmount);
        return this;
    }

    public void setForeClosureInterestAmount(Double foreClosureInterestAmount) {
        this.foreClosureInterestAmount = foreClosureInterestAmount;
    }

    public Integer getDays() {
        return this.days;
    }

    public InterestPayments days(Integer days) {
        this.setDays(days);
        return this;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getProducts() {
        return this.products;
    }

    public InterestPayments products(Long products) {
        this.setProducts(products);
        return this;
    }

    public void setProducts(Long products) {
        this.products = products;
    }

    public Long getForeClosureProducts() {
        return this.foreClosureProducts;
    }

    public InterestPayments foreClosureProducts(Long foreClosureProducts) {
        this.setForeClosureProducts(foreClosureProducts);
        return this;
    }

    public void setForeClosureProducts(Long foreClosureProducts) {
        this.foreClosureProducts = foreClosureProducts;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InterestPayments status(Integer status) {
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
        if (!(o instanceof InterestPayments)) {
            return false;
        }
        return getId() != null && getId().equals(((InterestPayments) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InterestPayments{" +
            "id=" + getId() +
            ", admissionNumber='" + getAdmissionNumber() + "'" +
            ", termAccId=" + getTermAccId() +
            ", interestAmount=" + getInterestAmount() +
            ", interestPostingDate=" + getInterestPostingDate() +
            ", foreClosureInterestAmount=" + getForeClosureInterestAmount() +
            ", days=" + getDays() +
            ", products=" + getProducts() +
            ", foreClosureProducts=" + getForeClosureProducts() +
            ", status=" + getStatus() +
            "}";
    }
}
