package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;
import com.intellect.investmentsms.domain.InterestPayments;
import com.intellect.investmentsms.domain.InvestmentAccountDocuments;
import com.intellect.investmentsms.domain.InvestmentAccountInstallments;
import com.intellect.investmentsms.domain.InvestmentAccountsTransaction;
import com.intellect.investmentsms.service.InterestPaymentsService;
import com.intellect.investmentsms.service.InvestmentAccountDocumentsService;
import com.intellect.investmentsms.service.InvestmentAccountInstallmentsService;
import com.intellect.investmentsms.service.InvestmentAccountsTransactionService;

/**
 * A DTO for the
 * {@link com.intellect.investmentsms.domain.TermDepositInvestmentAccounts}
 * entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TermDepositInvestmentAccountsDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private Long pacsId;

	private String pacsCode;

	private String admissionNumber;

	private Long branchId;

	private Long productId;

	private String accountNumber;

	private Double depositAmount;

	private Long depositDate;

	private Integer tenureInDays;

	private Integer tenureInMonths;

	private Integer tenureInYears;

	private Float roi;

	private Float penalRoi;

	private Float foreClosureRoi;

	private Float effectiveRoi;

	private Float foreClosureEffectiveRoi;

	private Integer interestCalculationType;

	private Integer interestOrInstallmentFrequency;

	private Double installmentAmount;

	private Integer noOfInstallments;

	private Double maturityInterest;

	private Double maturityAmount;

	private Boolean isAutoRenewal;

	private Integer autoRenewalType;

	private Boolean isForeClosure;

	private Double foreClosureMaturityIntrestAmount;

	private Double foreClosureMaturityAmount;

	private Long lastInterestPostedDate;

	private Long maturityDate;

	private Long closureDate;

	private String signedCopyPath;

	private String foreClosureReqSignedCopy;

	private String depositBondCopyPath;

	private Integer status;
    
    private String statusName;
    
	private String remarks;

	private String productName;
	
	private String ledgerFolioNumber;
	
	private String societyAccountNumber;
	
	private String resolutionNumber;
	
	private List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTOList;
	
	private List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTO;

	private List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTO;

	private List<InterestPaymentsDTO> interestPaymentsDTO;

	private List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTO;

	private Double systemMaturityInterest;

	private Double systemMaturityAmount;
	
	private Long systemMaturityDate;
	
	private Integer depositType;
	
	private String depositName;
	
    private String bankName;
	
	private Long investedBankDetailsId;
	
	
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<InvestmentAccountInstallmentsDTO> getInvestmentAccountInstallmentsDTOList() {
		return investmentAccountInstallmentsDTOList;
	}

	public void setInvestmentAccountInstallmentsDTOList(
			List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTOList) {
		this.investmentAccountInstallmentsDTOList = investmentAccountInstallmentsDTOList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<InvestmentAccountDocumentsDTO> getInvestmentAccountDocumentsDTO() {
		return investmentAccountDocumentsDTO;
	}

	public void setInvestmentAccountDocumentsDTO(List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTO) {
		this.investmentAccountDocumentsDTO = investmentAccountDocumentsDTO;
	}

	public List<InvestmentAccountInstallmentsDTO> getInvestmentAccountInstallmentsDTO() {
		return investmentAccountInstallmentsDTO;
	}

	public void setInvestmentAccountInstallmentsDTO(
			List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTO) {
		this.investmentAccountInstallmentsDTO = investmentAccountInstallmentsDTO;
	}

	public List<InterestPaymentsDTO> getInterestPaymentsDTO() {
		return interestPaymentsDTO;
	}

	public void setInterestPaymentsDTO(List<InterestPaymentsDTO> interestPaymentsDTO) {
		this.interestPaymentsDTO = interestPaymentsDTO;
	}

	public List<InvestmentAccountsTransactionDTO> getInvestmentAccountsTransactionDTO() {
		return investmentAccountsTransactionDTO;
	}

	public void setInvestmentAccountsTransactionDTO(
			List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTO) {
		this.investmentAccountsTransactionDTO = investmentAccountsTransactionDTO;
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
	
	public Integer getDepositType() {
		return depositType;
	}

	public void setDepositType(Integer depositType) {
		this.depositType = depositType;
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
        if (!(o instanceof TermDepositInvestmentAccountsDTO)) {
            return false;
        }

        TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO = (TermDepositInvestmentAccountsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, termDepositInvestmentAccountsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "TermDepositInvestmentAccountsDTO [id=" + id + ", pacsId=" + pacsId + ", pacsCode=" + pacsCode
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
				+ ", depositBondCopyPath=" + depositBondCopyPath + ", status=" + status + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", productName=" + productName + ", ledgerFolioNumber=" + ledgerFolioNumber
				+ ", societyAccountNumber=" + societyAccountNumber + ", resolutionNumber=" + resolutionNumber
				+ ", investmentAccountInstallmentsDTOList=" + investmentAccountInstallmentsDTOList
				+ ", investmentAccountDocumentsDTO=" + investmentAccountDocumentsDTO
				+ ", investmentAccountInstallmentsDTO=" + investmentAccountInstallmentsDTO + ", interestPaymentsDTO="
				+ interestPaymentsDTO + ", investmentAccountsTransactionDTO=" + investmentAccountsTransactionDTO
				+ ", systemMaturityInterest=" + systemMaturityInterest + ", systemMaturityAmount="
				+ systemMaturityAmount + ", systemMaturityDate=" + systemMaturityDate + ", depositType=" + depositType
				+ ", depositName=" + depositName + ", bankName=" + bankName + ", investedBankDetailsId="
				+ investedBankDetailsId + "]";
	}

	
}
