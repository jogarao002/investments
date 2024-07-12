package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import com.intellect.investmentsms.audit.Auditable;

/**
 * A InvestedBankDetails.
 */
@Entity
@Table(name = "invested_bank_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestedBankDetails extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "bank_ifsc_code")
    private String bankIfscCode;

    @Column(name = "poc_name")
    private String pocName;

    @Column(name = "poc_number")
    private String pocNumber;

    @Column(name = "poc_email")
    private String pocEmail;

    @Column(name = "status")
    private Integer status;
    
    @Column(name = "pacs_code")
    private String pacsCode;

    @Column(name = "pacs_id")
	private Long pacsId;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "branch_id")
	private Long branchId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InvestedBankDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public InvestedBankDetails bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public InvestedBankDetails branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankAddress() {
        return this.bankAddress;
    }

    public InvestedBankDetails bankAddress(String bankAddress) {
        this.setBankAddress(bankAddress);
        return this;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankIfscCode() {
        return this.bankIfscCode;
    }

    public InvestedBankDetails bankIfscCode(String bankIfscCode) {
        this.setBankIfscCode(bankIfscCode);
        return this;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
    }

    public String getPocName() {
        return this.pocName;
    }

    public InvestedBankDetails pocName(String pocName) {
        this.setPocName(pocName);
        return this;
    }

    public void setPocName(String pocName) {
        this.pocName = pocName;
    }

    public String getPocNumber() {
        return this.pocNumber;
    }

    public InvestedBankDetails pocNumber(String pocNumber) {
        this.setPocNumber(pocNumber);
        return this;
    }

    public void setPocNumber(String pocNumber) {
        this.pocNumber = pocNumber;
    }

    public String getPocEmail() {
        return this.pocEmail;
    }

    public InvestedBankDetails pocEmail(String pocEmail) {
        this.setPocEmail(pocEmail);
        return this;
    }

    public void setPocEmail(String pocEmail) {
        this.pocEmail = pocEmail;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InvestedBankDetails status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
        if (!(o instanceof InvestedBankDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((InvestedBankDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "InvestedBankDetails [id=" + id + ", bankName=" + bankName + ", branchName=" + branchName
				+ ", bankAddress=" + bankAddress + ", bankIfscCode=" + bankIfscCode + ", pocName=" + pocName
				+ ", pocNumber=" + pocNumber + ", pocEmail=" + pocEmail + ", status=" + status + ", pacsCode="
				+ pacsCode + ", pacsId=" + pacsId + ", remarks=" + remarks + ", branchId=" + branchId + "]";
	}

    // prettier-ignore
    
}
