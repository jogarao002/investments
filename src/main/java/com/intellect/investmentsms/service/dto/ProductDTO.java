package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.investmentsms.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO extends AuditableDTO<Long> implements Serializable {

    private Long id;

    private String name;

    private Double minDepositAmount;

    private Double maxDepositAmount;

    private Integer minTenure;

    private Integer maxTenure;

    private Boolean isAutoRenual;

    private Long effectiveStartDate;

    private Long effectiveEndDate;

    private Integer status;
    
    private String statusName;
    
	private String pacsCode;

	private Long pacsId;
    
    private String remarks;
    
	private Long branchId;
	
	private String ledgerFolioNumber;
	
	private String resolutionNumber;
	
	private List<ProductAssociatedBankDetailsDTO> productAssociatedBankDetailsDTO;
	
	private List<InterestPolicyConfigDTO> interestPolicyConfigDTO;
	
	private List<PenaltyConfigDTO> penaltyConfigDTO;
	
	private List<RequiredDocumentsDTO> requiredDocumentsDTO;
	
	private List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTO;
	
	private List<InterestPaymentsDTO> interestPaymentsDTO;
	
	private List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTO;
	
	private List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTO;

    private String bankName;
	
	private Long investedBankDetailsId;
	
    private String branchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinDepositAmount() {
        return minDepositAmount;
    }

    public void setMinDepositAmount(Double minDepositAmount) {
        this.minDepositAmount = minDepositAmount;
    }

    public Double getMaxDepositAmount() {
        return maxDepositAmount;
    }

    public void setMaxDepositAmount(Double maxDepositAmount) {
        this.maxDepositAmount = maxDepositAmount;
    }

    public Integer getMinTenure() {
        return minTenure;
    }

    public void setMinTenure(Integer minTenure) {
        this.minTenure = minTenure;
    }

    public Integer getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(Integer maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Boolean getIsAutoRenual() {
        return isAutoRenual;
    }

    public void setIsAutoRenual(Boolean isAutoRenual) {
        this.isAutoRenual = isAutoRenual;
    }

    public Long getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Long getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
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
	
	public List<ProductAssociatedBankDetailsDTO> getProductAssociatedBankDetailsDTO() {
		return productAssociatedBankDetailsDTO;
	}

	public void setProductAssociatedBankDetailsDTO(List<ProductAssociatedBankDetailsDTO> productAssociatedBankDetailsDTO) {
		this.productAssociatedBankDetailsDTO = productAssociatedBankDetailsDTO;
	}

	public List<InterestPolicyConfigDTO> getInterestPolicyConfigDTO() {
		return interestPolicyConfigDTO;
	}

	public void setInterestPolicyConfigDTO(List<InterestPolicyConfigDTO> interestPolicyConfigDTO) {
		this.interestPolicyConfigDTO = interestPolicyConfigDTO;
	}

	public List<PenaltyConfigDTO> getPenaltyConfigDTO() {
		return penaltyConfigDTO;
	}

	public void setPenaltyConfigDTO(List<PenaltyConfigDTO> penaltyConfigDTO) {
		this.penaltyConfigDTO = penaltyConfigDTO;
	}

	public List<RequiredDocumentsDTO> getRequiredDocumentsDTO() {
		return requiredDocumentsDTO;
	}

	public void setRequiredDocumentsDTO(List<RequiredDocumentsDTO> requiredDocumentsDTO) {
		this.requiredDocumentsDTO = requiredDocumentsDTO;
	}

	public List<InvestmentAccountDocumentsDTO> getInvestmentAccountDocumentsDTO() {
		return investmentAccountDocumentsDTO;
	}

	public void setInvestmentAccountDocumentsDTO(List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTO) {
		this.investmentAccountDocumentsDTO = investmentAccountDocumentsDTO;
	}

	public List<InterestPaymentsDTO> getInterestPaymentsDTO() {
		return interestPaymentsDTO;
	}

	public void setInterestPaymentsDTO(List<InterestPaymentsDTO> interestPaymentsDTO) {
		this.interestPaymentsDTO = interestPaymentsDTO;
	}

	public List<TermDepositInvestmentAccountsDTO> getTermDepositInvestmentAccountsDTO() {
		return termDepositInvestmentAccountsDTO;
	}

	public void setTermDepositInvestmentAccountsDTO(
			List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTO) {
		this.termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsDTO;
	}

	public List<SharesInvestmentAccountsDTO> getSharesInvestmentAccountsDTO() {
		return sharesInvestmentAccountsDTO;
	}

	public void setSharesInvestmentAccountsDTO(List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTO) {
		this.sharesInvestmentAccountsDTO = sharesInvestmentAccountsDTO;
	}
	
	public String getLedgerFolioNumber() {
		return ledgerFolioNumber;
	}

	public void setLedgerFolioNumber(String ledgerFolioNumber) {
		this.ledgerFolioNumber = ledgerFolioNumber;
	}

	public String getResolutionNumber() {
		return resolutionNumber;
	}

	public void setResolutionNumber(String resolutionNumber) {
		this.resolutionNumber = resolutionNumber;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", minDepositAmount=" + minDepositAmount
				+ ", maxDepositAmount=" + maxDepositAmount + ", minTenure=" + minTenure + ", maxTenure=" + maxTenure
				+ ", isAutoRenual=" + isAutoRenual + ", effectiveStartDate=" + effectiveStartDate
				+ ", effectiveEndDate=" + effectiveEndDate + ", status=" + status + ", statusName=" + statusName
				+ ", pacsCode=" + pacsCode + ", pacsId=" + pacsId + ", remarks=" + remarks + ", branchId=" + branchId
				+ ", ledgerFolioNumber=" + ledgerFolioNumber + ", resolutionNumber=" + resolutionNumber
				+ ", productAssociatedBankDetailsDTO=" + productAssociatedBankDetailsDTO + ", interestPolicyConfigDTO="
				+ interestPolicyConfigDTO + ", penaltyConfigDTO=" + penaltyConfigDTO + ", requiredDocumentsDTO="
				+ requiredDocumentsDTO + ", investmentAccountDocumentsDTO=" + investmentAccountDocumentsDTO
				+ ", interestPaymentsDTO=" + interestPaymentsDTO + ", termDepositInvestmentAccountsDTO="
				+ termDepositInvestmentAccountsDTO + ", sharesInvestmentAccountsDTO=" + sharesInvestmentAccountsDTO
				+ ", bankName=" + bankName + ", investedBankDetailsId=" + investedBankDetailsId + ", branchName="
				+ branchName + "]";
	}


}
