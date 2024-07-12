package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.SharesInvestmentAccounts} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SharesInvestmentAccountsDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private Long pacsId;

    private String pacsCode;

    private String admissionNumber;

    private Long branchId;

    private Long productId;
    
    private String productName;

    private String shareCertificateNumber;

    private Integer noOfSharesPurchased;

    private Double eachShareAmount;

    private String shareCertificateCopyPath;

    private String closureCopyPath;

    private Long sharesPurchasedDate;

    private Double sharesMaturityAmount;

    private Long closureDate;

    private Integer status;
    
    private String statusName;
    
    private String remarks;
    
    private String bankName;
    
    private String branchName;
	
	private Long investedBankDetailsId;
	
	private String depositName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacsId() {
        return pacsId;
    }

    public void setPacsId(Long pacsId) {
        this.pacsId = pacsId;
    }

    public String getPacsCode() {
        return pacsCode;
    }

    public void setPacsCode(String pacsCode) {
        this.pacsCode = pacsCode;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getShareCertificateNumber() {
        return shareCertificateNumber;
    }

    public void setShareCertificateNumber(String shareCertificateNumber) {
        this.shareCertificateNumber = shareCertificateNumber;
    }

    public Integer getNoOfSharesPurchased() {
        return noOfSharesPurchased;
    }

    public void setNoOfSharesPurchased(Integer noOfSharesPurchased) {
        this.noOfSharesPurchased = noOfSharesPurchased;
    }

    public Double getEachShareAmount() {
        return eachShareAmount;
    }

    public void setEachShareAmount(Double eachShareAmount) {
        this.eachShareAmount = eachShareAmount;
    }

    public String getShareCertificateCopyPath() {
        return shareCertificateCopyPath;
    }

    public void setShareCertificateCopyPath(String shareCertificateCopyPath) {
        this.shareCertificateCopyPath = shareCertificateCopyPath;
    }

    public String getClosureCopyPath() {
        return closureCopyPath;
    }

    public void setClosureCopyPath(String closureCopyPath) {
        this.closureCopyPath = closureCopyPath;
    }

    public Long getSharesPurchasedDate() {
		return sharesPurchasedDate;
	}

	public void setSharesPurchasedDate(Long sharesPurchasedDate) {
		this.sharesPurchasedDate = sharesPurchasedDate;
	}

	public Double getSharesMaturityAmount() {
        return sharesMaturityAmount;
    }

    public void setSharesMaturityAmount(Double sharesMaturityAmount) {
        this.sharesMaturityAmount = sharesMaturityAmount;
    }

    public Long getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Long closureDate) {
        this.closureDate = closureDate;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getInvestedBankDetailsId() {
		return investedBankDetailsId;
	}

	public void setInvestedBankDetailsId(Long investedBankDetailsId) {
		this.investedBankDetailsId = investedBankDetailsId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SharesInvestmentAccountsDTO)) {
            return false;
        }

        SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO = (SharesInvestmentAccountsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sharesInvestmentAccountsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "SharesInvestmentAccountsDTO [id=" + id + ", pacsId=" + pacsId + ", pacsCode=" + pacsCode
				+ ", admissionNumber=" + admissionNumber + ", branchId=" + branchId + ", productId=" + productId
				+ ", productName=" + productName + ", shareCertificateNumber=" + shareCertificateNumber
				+ ", noOfSharesPurchased=" + noOfSharesPurchased + ", eachShareAmount=" + eachShareAmount
				+ ", shareCertificateCopyPath=" + shareCertificateCopyPath + ", closureCopyPath=" + closureCopyPath
				+ ", sharesPurchasedDate=" + sharesPurchasedDate + ", sharesMaturityAmount=" + sharesMaturityAmount
				+ ", closureDate=" + closureDate + ", status=" + status + ", statusName=" + statusName + ", remarks="
				+ remarks + ", bankName=" + bankName + ", branchName=" + branchName + ", investedBankDetailsId="
				+ investedBankDetailsId + ", depositName=" + depositName + "]";
	}

	
}
