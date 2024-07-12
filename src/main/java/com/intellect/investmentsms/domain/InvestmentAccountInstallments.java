package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A InvestmentAccountInstallments.
 */
@Entity
@Table(name = "investment_account_installments")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountInstallments extends Auditable<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "admission_number")
    private String admissionNumber;

    @Column(name = "term_acc_id")
    private Long termAccId;

    @Column(name = "installment_number")
    private Integer installmentNumber;

    @Column(name = "installment_date")
    private Long installmentDate;

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "penalty_amount")
    private Double penaltyAmount;

    @Column(name = "penalty_days")
    private Integer penaltyDays;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
		return id;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public Long getTermAccId() {
		return termAccId;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public Long getInstallmentDate() {
		return installmentDate;
	}

	public Double getInstallmentAmount() {
		return installmentAmount;
	}

	public Double getPenaltyAmount() {
		return penaltyAmount;
	}

	public Integer getPenaltyDays() {
		return penaltyDays;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public void setTermAccId(Long termAccId) {
		this.termAccId = termAccId;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public void setInstallmentDate(Long installmentDate) {
		this.installmentDate = installmentDate;
	}

	public void setInstallmentAmount(Double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public void setPenaltyDays(Integer penaltyDays) {
		this.penaltyDays = penaltyDays;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestmentAccountInstallments)) {
            return false;
        }
        return getId() != null && getId().equals(((InvestmentAccountInstallments) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "InvestmentAccountInstallments [id=" + id + ", admissionNumber=" + admissionNumber + ", termAccId="
				+ termAccId + ", installmentNumber=" + installmentNumber + ", installmentDate=" + installmentDate
				+ ", installmentAmount=" + installmentAmount + ", penaltyAmount=" + penaltyAmount + ", penaltyDays="
				+ penaltyDays + ", status=" + status + "]";
	}

}
