package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.InvestmentAccountInstallments} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentAccountInstallmentsDTO extends AuditableDTO <Long> implements Serializable {

    private Long id;
    
    private String admissionNumber;

    private Long termAccId;

    private Integer installmentNumber;

    private Long installmentDate;

    private Double installmentAmount;

    private Double penaltyAmount;

    private Integer penaltyDays;

    private Integer status;
    
    private String statusName;


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

	public String getStatusName() {
		return statusName;
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

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestmentAccountInstallmentsDTO)) {
            return false;
        }

        InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO = (InvestmentAccountInstallmentsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, investmentAccountInstallmentsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "InvestmentAccountInstallmentsDTO [id=" + id + ", admissionNumber=" + admissionNumber + ", termAccId="
				+ termAccId + ", installmentNumber=" + installmentNumber + ", installmentDate=" + installmentDate
				+ ", installmentAmount=" + installmentAmount + ", penaltyAmount=" + penaltyAmount + ", penaltyDays="
				+ penaltyDays + ", status=" + status + ", statusName=" + statusName + "]";
	}

}