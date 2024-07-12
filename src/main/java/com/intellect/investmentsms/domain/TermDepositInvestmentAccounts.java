package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A TermDepositInvestmentAccounts.
 */
@Entity
@Table(name = "term_deposit_investment_accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TermDepositInvestmentAccounts extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pacs_id")
    private Long pacsId;

    @Column(name = "pacs_code")
    private String pacsCode;

    @Column(name = "admission_number")
    private String admissionNumber;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deposit_amount")
    private Double depositAmount;

    @Column(name = "deposit_date")
    private Long depositDate;

    @Column(name = "tenure_in_days")
    private Integer tenureInDays;

    @Column(name = "tenure_in_months")
    private Integer tenureInMonths;

    @Column(name = "tenure_in_years")
    private Integer tenureInYears;

    @Column(name = "roi")
    private Float roi;

    @Column(name = "penal_roi")
    private Float penalRoi;

    @Column(name = "fore_closure_roi")
    private Float foreClosureRoi;

    @Column(name = "effective_roi")
    private Float effectiveRoi;

    @Column(name = "fore_closure_effective_roi")
    private Float foreClosureEffectiveRoi;

    @Column(name = "interest_calculation_type")
    private Integer interestCalculationType;

    @Column(name = "interest_or_installment_frequency")
    private Integer interestOrInstallmentFrequency;

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "no_of_installments")
    private Integer noOfInstallments;

    @Column(name = "maturity_interest")
    private Double maturityInterest;

    @Column(name = "maturity_amount")
    private Double maturityAmount;

    @Column(name = "is_auto_renewal")
    private Boolean isAutoRenewal;

    @Column(name = "auto_renewal_type")
    private Integer autoRenewalType;

    @Column(name = "is_fore_closure")
    private Boolean isForeClosure;

    @Column(name = "fore_closure_maturity_intrest_amount")
    private Double foreClosureMaturityIntrestAmount;

    @Column(name = "fore_closure_maturity_amount")
    private Double foreClosureMaturityAmount;

    @Column(name = "last_interest_posted_date")
    private Long lastInterestPostedDate;

    @Column(name = "maturity_date")
    private Long maturityDate;

    @Column(name = "closure_date")
    private Long closureDate;

    @Column(name = "signed_copy_path")
    private String signedCopyPath;

    @Column(name = "fore_closure_req_signed_copy")
    private String foreClosureReqSignedCopy;

    @Column(name = "deposit_bond_copy_path")
    private String depositBondCopyPath;

    @Column(name = "status")
    private Integer status;
    
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "system_generated_deposit_amount")
    private Double systemGeneratedDepositAmount;
    
    @Column(name = "system_generated_deposit_date")
    private Long systemGeneratedDepositDate;
    
    @Column(name = "ledgerfolio_number")
    private String ledgerFolioNumber;
    
    @Column(name = "society_account_number")
    private String societyAccountNumber;
    
    @Column(name = "resolution_number")
    private String resolutionNumber;
    
    @Column(name = "system_maturity_interest")
    private Double systemMaturityInterest;

    @Column(name = "system_maturity_amount")
	private Double systemMaturityAmount;
	
    @Column(name = "system_maturity_date")
	private Long systemMaturityDate;
    
    @Column(name = "deposit_type")
    private Integer depositType;
	
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Long getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Long depositDate) {
		this.depositDate = depositDate;
	}

	public Integer getTenureInDays() {
		return tenureInDays;
	}

	public void setTenureInDays(Integer tenureInDays) {
		this.tenureInDays = tenureInDays;
	}

	public Integer getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(Integer tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public Integer getTenureInYears() {
		return tenureInYears;
	}

	public void setTenureInYears(Integer tenureInYears) {
		this.tenureInYears = tenureInYears;
	}

	public Float getRoi() {
		return roi;
	}

	public void setRoi(Float roi) {
		this.roi = roi;
	}

	public Float getPenalRoi() {
		return penalRoi;
	}

	public void setPenalRoi(Float penalRoi) {
		this.penalRoi = penalRoi;
	}

	public Float getForeClosureRoi() {
		return foreClosureRoi;
	}

	public void setForeClosureRoi(Float foreClosureRoi) {
		this.foreClosureRoi = foreClosureRoi;
	}

	public Float getEffectiveRoi() {
		return effectiveRoi;
	}

	public void setEffectiveRoi(Float effectiveRoi) {
		this.effectiveRoi = effectiveRoi;
	}

	public Float getForeClosureEffectiveRoi() {
		return foreClosureEffectiveRoi;
	}

	public void setForeClosureEffectiveRoi(Float foreClosureEffectiveRoi) {
		this.foreClosureEffectiveRoi = foreClosureEffectiveRoi;
	}

	public Integer getInterestCalculationType() {
		return interestCalculationType;
	}

	public void setInterestCalculationType(Integer interestCalculationType) {
		this.interestCalculationType = interestCalculationType;
	}

	public Integer getInterestOrInstallmentFrequency() {
		return interestOrInstallmentFrequency;
	}

	public void setInterestOrInstallmentFrequency(Integer interestOrInstallmentFrequency) {
		this.interestOrInstallmentFrequency = interestOrInstallmentFrequency;
	}

	public Double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(Double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public Integer getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(Integer noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public Double getMaturityInterest() {
		return maturityInterest;
	}

	public void setMaturityInterest(Double maturityInterest) {
		this.maturityInterest = maturityInterest;
	}

	public Double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(Double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public Boolean getIsAutoRenewal() {
		return isAutoRenewal;
	}

	public void setIsAutoRenewal(Boolean isAutoRenewal) {
		this.isAutoRenewal = isAutoRenewal;
	}

	public Integer getAutoRenewalType() {
		return autoRenewalType;
	}

	public void setAutoRenewalType(Integer autoRenewalType) {
		this.autoRenewalType = autoRenewalType;
	}

	public Boolean getIsForeClosure() {
		return isForeClosure;
	}

	public void setIsForeClosure(Boolean isForeClosure) {
		this.isForeClosure = isForeClosure;
	}

	public Double getForeClosureMaturityIntrestAmount() {
		return foreClosureMaturityIntrestAmount;
	}

	public void setForeClosureMaturityIntrestAmount(Double foreClosureMaturityIntrestAmount) {
		this.foreClosureMaturityIntrestAmount = foreClosureMaturityIntrestAmount;
	}

	public Double getForeClosureMaturityAmount() {
		return foreClosureMaturityAmount;
	}

	public void setForeClosureMaturityAmount(Double foreClosureMaturityAmount) {
		this.foreClosureMaturityAmount = foreClosureMaturityAmount;
	}

	public Long getLastInterestPostedDate() {
		return lastInterestPostedDate;
	}

	public void setLastInterestPostedDate(Long lastInterestPostedDate) {
		this.lastInterestPostedDate = lastInterestPostedDate;
	}

	public Long getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Long maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Long getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(Long closureDate) {
		this.closureDate = closureDate;
	}

	public String getSignedCopyPath() {
		return signedCopyPath;
	}

	public void setSignedCopyPath(String signedCopyPath) {
		this.signedCopyPath = signedCopyPath;
	}

	public String getForeClosureReqSignedCopy() {
		return foreClosureReqSignedCopy;
	}

	public void setForeClosureReqSignedCopy(String foreClosureReqSignedCopy) {
		this.foreClosureReqSignedCopy = foreClosureReqSignedCopy;
	}

	public String getDepositBondCopyPath() {
		return depositBondCopyPath;
	}

	public void setDepositBondCopyPath(String depositBondCopyPath) {
		this.depositBondCopyPath = depositBondCopyPath;
	}

	public Integer getStatus() {
		return status;
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

	public Double getSystemGeneratedDepositAmount() {
		return systemGeneratedDepositAmount;
	}

	public void setSystemGeneratedDepositAmount(Double systemGeneratedDepositAmount) {
		this.systemGeneratedDepositAmount = systemGeneratedDepositAmount;
	}

	public Long getSystemGeneratedDepositDate() {
		return systemGeneratedDepositDate;
	}

	public void setSystemGeneratedDepositDate(Long systemGeneratedDepositDate) {
		this.systemGeneratedDepositDate = systemGeneratedDepositDate;
	}
	
	public String getLedgerFolioNumber() {
		return ledgerFolioNumber;
	}

	public void setLedgerFolioNumber(String ledgerFolioNumber) {
		this.ledgerFolioNumber = ledgerFolioNumber;
	}

	public String getSocietyAccountNumber() {
		return societyAccountNumber;
	}

	public void setSocietyAccountNumber(String societyAccountNumber) {
		this.societyAccountNumber = societyAccountNumber;
	}

	public String getResolutionNumber() {
		return resolutionNumber;
	}

	public void setResolutionNumber(String resolutionNumber) {
		this.resolutionNumber = resolutionNumber;
	}
	
	public Double getSystemMaturityInterest() {
		return systemMaturityInterest;
	}

	public void setSystemMaturityInterest(Double systemMaturityInterest) {
		this.systemMaturityInterest = systemMaturityInterest;
	}

	public Double getSystemMaturityAmount() {
		return systemMaturityAmount;
	}

	public void setSystemMaturityAmount(Double systemMaturityAmount) {
		this.systemMaturityAmount = systemMaturityAmount;
	}

	public Long getSystemMaturityDate() {
		return systemMaturityDate;
	}

	public void setSystemMaturityDate(Long systemMaturityDate) {
		this.systemMaturityDate = systemMaturityDate;
	}

	public Integer getDepositType() {
		return depositType;
	}

	public void setDepositType(Integer depositType) {
		this.depositType = depositType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TermDepositInvestmentAccounts)) {
            return false;
        }
        return getId() != null && getId().equals(((TermDepositInvestmentAccounts) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "TermDepositInvestmentAccounts [id=" + id + ", pacsId=" + pacsId + ", pacsCode=" + pacsCode
				+ ", admissionNumber=" + admissionNumber + ", branchId=" + branchId + ", productId=" + productId
				+ ", accountNumber=" + accountNumber + ", depositAmount=" + depositAmount + ", depositDate="
				+ depositDate + ", tenureInDays=" + tenureInDays + ", tenureInMonths=" + tenureInMonths
				+ ", tenureInYears=" + tenureInYears + ", roi=" + roi + ", penalRoi=" + penalRoi + ", foreClosureRoi="
				+ foreClosureRoi + ", effectiveRoi=" + effectiveRoi + ", foreClosureEffectiveRoi="
				+ foreClosureEffectiveRoi + ", interestCalculationType=" + interestCalculationType
				+ ", interestOrInstallmentFrequency=" + interestOrInstallmentFrequency + ", installmentAmount="
				+ installmentAmount + ", noOfInstallments=" + noOfInstallments + ", maturityInterest="
				+ maturityInterest + ", maturityAmount=" + maturityAmount + ", isAutoRenewal=" + isAutoRenewal
				+ ", autoRenewalType=" + autoRenewalType + ", isForeClosure=" + isForeClosure
				+ ", foreClosureMaturityIntrestAmount=" + foreClosureMaturityIntrestAmount
				+ ", foreClosureMaturityAmount=" + foreClosureMaturityAmount + ", lastInterestPostedDate="
				+ lastInterestPostedDate + ", maturityDate=" + maturityDate + ", closureDate=" + closureDate
				+ ", signedCopyPath=" + signedCopyPath + ", foreClosureReqSignedCopy=" + foreClosureReqSignedCopy
				+ ", depositBondCopyPath=" + depositBondCopyPath + ", status=" + status + ", remarks=" + remarks
				+ ", systemGeneratedDepositAmount=" + systemGeneratedDepositAmount + ", systemGeneratedDepositDate="
				+ systemGeneratedDepositDate + ", ledgerFolioNumber=" + ledgerFolioNumber + ", societyAccountNumber="
				+ societyAccountNumber + ", resolutionNumber=" + resolutionNumber + ", systemMaturityInterest="
				+ systemMaturityInterest + ", systemMaturityAmount=" + systemMaturityAmount + ", systemMaturityDate="
				+ systemMaturityDate + ", depositType=" + depositType + "]";
	}

	

}
