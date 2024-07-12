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
 * A BorrowingsWorkFlow.
 */
@Entity
@Table(name = "investments_work_flow")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentsWorkFlow extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "pacs_code")
	private String pacsCode;

	@Column(name = "pacs_id")
	private Long pacsId;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "module_id")
	private Long moduleId;

	@Column(name = "module_type")
	private String moduleType;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private Long updatedOn;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof InvestmentsWorkFlow)) {
			return false;
		}
		return getId() != null && getId().equals(((InvestmentsWorkFlow) o).getId());
	}

	@Override
	public int hashCode() {
		// see
		// https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "BorrowingsWorkFlow [id=" + id + ", pacsCode=" + pacsCode + ", pacsId=" + pacsId + ", branchId="
				+ branchId + ", moduleId=" + moduleId + ", moduleType=" + moduleType + ", updatedBy=" + updatedBy
				+ ", updatedOn=" + updatedOn + ", remarks=" + remarks + ", status=" + status + "]";
	}

	

}
