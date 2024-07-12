package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.InvestedBankDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestedBankDetailsDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private String bankName;

    private String branchName;

    private String bankAddress;

    private String bankIfscCode;

    private String pocName;

    private String pocNumber;

    private String pocEmail;

    private Integer status;
    
    private String statusName;
    
	private String pacsCode;

	private Long pacsId;
    
    private String remarks;
    
	private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankIfscCode() {
        return bankIfscCode;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
    }

    public String getPocName() {
        return pocName;
    }

    public void setPocName(String pocName) {
        this.pocName = pocName;
    }

    public String getPocNumber() {
        return pocNumber;
    }

    public void setPocNumber(String pocNumber) {
        this.pocNumber = pocNumber;
    }

    public String getPocEmail() {
        return pocEmail;
    }

    public void setPocEmail(String pocEmail) {
        this.pocEmail = pocEmail;
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

	public String getPacsCode() {
		return pacsCode;
	}

	public void setPacsCode(String pacsCode) {
		this.pacsCode = pacsCode;
	}

	public Long getPacsId() {
		return pacsId;
	}

	public void setPacsId(Long pacsId) {
		this.pacsId = pacsId;
	}


	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestedBankDetailsDTO)) {
            return false;
        }

        InvestedBankDetailsDTO investedBankDetailsDTO = (InvestedBankDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, investedBankDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "InvestedBankDetailsDTO [id=" + id + ", bankName=" + bankName + ", branchName=" + branchName
				+ ", bankAddress=" + bankAddress + ", bankIfscCode=" + bankIfscCode + ", pocName=" + pocName
				+ ", pocNumber=" + pocNumber + ", pocEmail=" + pocEmail + ", status=" + status + ", statusName="
				+ statusName + ", pacsCode=" + pacsCode + ", pacsId=" + pacsId + ", remarks=" + remarks + ", branchId="
				+ branchId + "]";
	}

	
}
