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
 * A SharesInvestmentAccounts.
 */
@Entity
@Table(name = "shares_investment_accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SharesInvestmentAccounts extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pacs_id")
    private Long pacsId;

    @Column(name = "pacs_code")
    private String pacsCode;

    @Column(name = "adminssion_number")
    private String admissionNumber;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "share_certificate_number")
    private String shareCertificateNumber;

    @Column(name = "no_of_shares_purchased")
    private Integer noOfSharesPurchased;

    @Column(name = "each_share_amount")
    private Double eachShareAmount;

    @Column(name = "share_certificate_copy_path")
    private String shareCertificateCopyPath;

    @Column(name = "closure_copy_path")
    private String closureCopyPath;

    @Column(name = "shares_purchased_date")
    private Long sharesPurchasedDate;

    @Column(name = "shares_maturity_amount")
    private Double sharesMaturityAmount;

    @Column(name = "closure_date")
    private Long closureDate;

    @Column(name = "status")
    private Integer status;
    
    @Column(name = "remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SharesInvestmentAccounts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacsId() {
        return this.pacsId;
    }

    public SharesInvestmentAccounts pacsId(Long pacsId) {
        this.setPacsId(pacsId);
        return this;
    }

    public void setPacsId(Long pacsId) {
        this.pacsId = pacsId;
    }

    public String getPacsCode() {
        return this.pacsCode;
    }

    public SharesInvestmentAccounts pacsCode(String pacsCode) {
        this.setPacsCode(pacsCode);
        return this;
    }

    public void setPacsCode(String pacsCode) {
        this.pacsCode = pacsCode;
    }

    public String getAdmissionNumber() {
        return this.admissionNumber;
    }

    public SharesInvestmentAccounts admissionNumber(String admissionNumber) {
        this.setAdmissionNumber(admissionNumber);
        return this;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public SharesInvestmentAccounts branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public SharesInvestmentAccounts productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getShareCertificateNumber() {
        return this.shareCertificateNumber;
    }

    public SharesInvestmentAccounts shareCertificateNumber(String shareCertificateNumber) {
        this.setShareCertificateNumber(shareCertificateNumber);
        return this;
    }

    public void setShareCertificateNumber(String shareCertificateNumber) {
        this.shareCertificateNumber = shareCertificateNumber;
    }

    public Integer getNoOfSharesPurchased() {
        return this.noOfSharesPurchased;
    }

    public SharesInvestmentAccounts noOfSharesPurchased(Integer noOfSharesPurchased) {
        this.setNoOfSharesPurchased(noOfSharesPurchased);
        return this;
    }

    public void setNoOfSharesPurchased(Integer noOfSharesPurchased) {
        this.noOfSharesPurchased = noOfSharesPurchased;
    }

    public Double getEachShareAmount() {
        return this.eachShareAmount;
    }

    public SharesInvestmentAccounts eachShareAmount(Double eachShareAmount) {
        this.setEachShareAmount(eachShareAmount);
        return this;
    }

    public void setEachShareAmount(Double eachShareAmount) {
        this.eachShareAmount = eachShareAmount;
    }

    public String getShareCertificateCopyPath() {
        return this.shareCertificateCopyPath;
    }

    public SharesInvestmentAccounts shareCertificateCopyPath(String shareCertificateCopyPath) {
        this.setShareCertificateCopyPath(shareCertificateCopyPath);
        return this;
    }

    public void setShareCertificateCopyPath(String shareCertificateCopyPath) {
        this.shareCertificateCopyPath = shareCertificateCopyPath;
    }

    public String getClosureCopyPath() {
        return this.closureCopyPath;
    }

    public SharesInvestmentAccounts closureCopyPath(String closureCopyPath) {
        this.setClosureCopyPath(closureCopyPath);
        return this;
    }

    public void setClosureCopyPath(String closureCopyPath) {
        this.closureCopyPath = closureCopyPath;
    }


    public SharesInvestmentAccounts sharesPurchasedDate(Long sharesPurchasedDate) {
        this.setSharesPurchasedDate(sharesPurchasedDate);
        return this;
    }



    public Long getSharesPurchasedDate() {
		return sharesPurchasedDate;
	}

	public void setSharesPurchasedDate(Long sharesPurchasedDate) {
		this.sharesPurchasedDate = sharesPurchasedDate;
	}

	public Double getSharesMaturityAmount() {
        return this.sharesMaturityAmount;
    }

    public SharesInvestmentAccounts sharesMaturityAmount(Double sharesMaturityAmount) {
        this.setSharesMaturityAmount(sharesMaturityAmount);
        return this;
    }

    public void setSharesMaturityAmount(Double sharesMaturityAmount) {
        this.sharesMaturityAmount = sharesMaturityAmount;
    }

    public Long getClosureDate() {
        return this.closureDate;
    }

    public SharesInvestmentAccounts closureDate(Long closureDate) {
        this.setClosureDate(closureDate);
        return this;
    }

    public void setClosureDate(Long closureDate) {
        this.closureDate = closureDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public SharesInvestmentAccounts status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SharesInvestmentAccounts)) {
            return false;
        }
        return getId() != null && getId().equals(((SharesInvestmentAccounts) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "SharesInvestmentAccounts [id=" + id + ", pacsId=" + pacsId + ", pacsCode=" + pacsCode
				+ ", admissionNumber=" + admissionNumber + ", branchId=" + branchId + ", productId=" + productId
				+ ", shareCertificateNumber=" + shareCertificateNumber + ", noOfSharesPurchased=" + noOfSharesPurchased
				+ ", eachShareAmount=" + eachShareAmount + ", shareCertificateCopyPath=" + shareCertificateCopyPath
				+ ", closureCopyPath=" + closureCopyPath + ", sharesPurchasedDate=" + sharesPurchasedDate
				+ ", sharesMaturityAmount=" + sharesMaturityAmount + ", closureDate=" + closureDate + ", status="
				+ status + ", remarks=" + remarks + "]";
	}

    // prettier-ignore
    
}
