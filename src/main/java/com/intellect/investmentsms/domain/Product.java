package com.intellect.investmentsms.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import com.intellect.investmentsms.audit.Auditable;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product extends Auditable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_deposit_amount")
    private Double minDepositAmount;

    @Column(name = "max_deposit_amount")
    private Double maxDepositAmount;

    @Column(name = "min_tenure")
    private Integer minTenure;

    @Column(name = "max_tenure")
    private Integer maxTenure;

    @Column(name = "is_auto_renual")
    private Boolean isAutoRenual;

    @Column(name = "effective_start_date")
    private Long effectiveStartDate;

    @Column(name = "effective_end_date")
    private Long effectiveEndDate;

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
    
    @Column(name = "ledgerfolio_number")
    private String ledgerFolioNumber;
    
    @Column(name = "resolution_number")
    private String resolutionNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinDepositAmount() {
        return this.minDepositAmount;
    }

    public Product minDepositAmount(Double minDepositAmount) {
        this.setMinDepositAmount(minDepositAmount);
        return this;
    }

    public void setMinDepositAmount(Double minDepositAmount) {
        this.minDepositAmount = minDepositAmount;
    }

    public Double getMaxDepositAmount() {
        return this.maxDepositAmount;
    }

    public Product maxDepositAmount(Double maxDepositAmount) {
        this.setMaxDepositAmount(maxDepositAmount);
        return this;
    }

    public void setMaxDepositAmount(Double maxDepositAmount) {
        this.maxDepositAmount = maxDepositAmount;
    }

    public Integer getMinTenure() {
        return this.minTenure;
    }

    public Product minTenure(Integer minTenure) {
        this.setMinTenure(minTenure);
        return this;
    }

    public void setMinTenure(Integer minTenure) {
        this.minTenure = minTenure;
    }

    public Integer getMaxTenure() {
        return this.maxTenure;
    }

    public Product maxTenure(Integer maxTenure) {
        this.setMaxTenure(maxTenure);
        return this;
    }

    public void setMaxTenure(Integer maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Boolean getIsAutoRenual() {
        return this.isAutoRenual;
    }

    public Product isAutoRenual(Boolean isAutoRenual) {
        this.setIsAutoRenual(isAutoRenual);
        return this;
    }

    public void setIsAutoRenual(Boolean isAutoRenual) {
        this.isAutoRenual = isAutoRenual;
    }

    public Long getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public Product effectiveStartDate(Long effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Long getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public Product effectiveEndDate(Long effectiveEndDate) {
        this.setEffectiveEndDate(effectiveEndDate);
        return this;
    }

    public void setEffectiveEndDate(Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Product status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", minDepositAmount=" + minDepositAmount + ", maxDepositAmount="
				+ maxDepositAmount + ", minTenure=" + minTenure + ", maxTenure=" + maxTenure + ", isAutoRenual="
				+ isAutoRenual + ", effectiveStartDate=" + effectiveStartDate + ", effectiveEndDate=" + effectiveEndDate
				+ ", status=" + status + ", pacsCode=" + pacsCode + ", pacsId=" + pacsId + ", remarks=" + remarks
				+ ", branchId=" + branchId + ", ledgerFolioNumber=" + ledgerFolioNumber + ", resolutionNumber="
				+ resolutionNumber + "]";
	}

}
