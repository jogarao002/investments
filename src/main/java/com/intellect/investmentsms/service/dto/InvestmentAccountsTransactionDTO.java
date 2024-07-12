package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.InvestmentAccountsTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountsTransactionDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private String admissionNumber;

    private Long termAccId;

    private Integer transactionModule;

    private Long transactionModuleId;

    private Double transactionAmount;

    private Long transactionDate;

    private Integer transactionType;

    private Long transactionMode;

    private String transactionCode;

    private Integer status;
    
    private String statusName;

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

    public Integer getTransactionModule() {
        return transactionModule;
    }

    public void setTransactionModule(Integer transactionModule) {
        this.transactionModule = transactionModule;
    }

    public Long getTransactionModuleId() {
        return transactionModuleId;
    }

    public void setTransactionModuleId(Long transactionModuleId) {
        this.transactionModuleId = transactionModuleId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Long getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(Long transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
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
        if (!(o instanceof InvestmentAccountsTransactionDTO)) {
            return false;
        }

        InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO = (InvestmentAccountsTransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, investmentAccountsTransactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
	@Override
	public String toString() {
		return "InvestmentAccountsTransactionDTO [id=" + id + ", admissionNumber=" + admissionNumber + ", termAccId="
				+ termAccId + ", transactionModule=" + transactionModule + ", transactionModuleId="
				+ transactionModuleId + ", transactionAmount=" + transactionAmount + ", transactionDate="
				+ transactionDate + ", transactionType=" + transactionType + ", transactionMode=" + transactionMode
				+ ", transactionCode=" + transactionCode + ", status=" + status + ", statusName=" + statusName + "]";
	}

 
   
    
}
