package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A InvestmentAccountsTransaction.
 */
@Entity
@Table(name = "investment_accounts_transaction")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountsTransaction extends Auditable<Long> implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "admission_number")
    private String admissionNumber;

    @Column(name = "term_acc_id")
    private Long termAccId;

    @Column(name = "transaction_module")
    private Integer transactionModule;

    @Column(name = "transaction_module_id")
    private Long transactionModuleId;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "transaction_date")
    private Long transactionDate;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "transaction_mode")
    private Long transactionMode;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InvestmentAccountsTransaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdmissionNumber() {
        return this.admissionNumber;
    }

    public InvestmentAccountsTransaction admissionNumber(String admissionNumber) {
        this.setAdmissionNumber(admissionNumber);
        return this;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Long getTermAccId() {
        return this.termAccId;
    }

    public InvestmentAccountsTransaction termAccId(Long termAccId) {
        this.setTermAccId(termAccId);
        return this;
    }

    public void setTermAccId(Long termAccId) {
        this.termAccId = termAccId;
    }

    public Integer getTransactionModule() {
        return this.transactionModule;
    }

    public InvestmentAccountsTransaction transactionModule(Integer transactionModule) {
        this.setTransactionModule(transactionModule);
        return this;
    }

    public void setTransactionModule(Integer transactionModule) {
        this.transactionModule = transactionModule;
    }

    public Long getTransactionModuleId() {
        return this.transactionModuleId;
    }

    public InvestmentAccountsTransaction transactionModuleId(Long transactionModuleId) {
        this.setTransactionModuleId(transactionModuleId);
        return this;
    }

    public void setTransactionModuleId(Long transactionModuleId) {
        this.transactionModuleId = transactionModuleId;
    }

    public Double getTransactionAmount() {
        return this.transactionAmount;
    }

    public InvestmentAccountsTransaction transactionAmount(Double transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getTransactionDate() {
        return this.transactionDate;
    }

    public InvestmentAccountsTransaction transactionDate(Long transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionType() {
        return this.transactionType;
    }

    public InvestmentAccountsTransaction transactionType(Integer transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Long getTransactionMode() {
        return this.transactionMode;
    }

    public InvestmentAccountsTransaction transactionMode(Long transactionMode) {
        this.setTransactionMode(transactionMode);
        return this;
    }

    public void setTransactionMode(Long transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public InvestmentAccountsTransaction transactionCode(String transactionCode) {
        this.setTransactionCode(transactionCode);
        return this;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InvestmentAccountsTransaction status(Integer status) {
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
        if (!(o instanceof InvestmentAccountsTransaction)) {
            return false;
        }
        return getId() != null && getId().equals(((InvestmentAccountsTransaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvestmentAccountsTransaction{" +
            "id=" + getId() +
            ", admissionNumber='" + getAdmissionNumber() + "'" +
            ", termAccId=" + getTermAccId() +
            ", transactionModule=" + getTransactionModule() +
            ", transactionModuleId=" + getTransactionModuleId() +
            ", transactionAmount=" + getTransactionAmount() +
            ", transactionDate=" + getTransactionDate() +
            ", transactionType=" + getTransactionType() +
            ", transactionMode=" + getTransactionMode() +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
