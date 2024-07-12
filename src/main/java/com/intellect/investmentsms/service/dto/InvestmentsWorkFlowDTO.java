package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvestmentsWorkFlowDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String pacsCode;

	private Long pacsId;

	private Long branchId;

	private Long moduleId;

	private String moduleType;

	private Long updatedBy;

	private Long updatedOn;

	private String remarks;

	private Integer status;
	
	private String statusName;

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
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof InvestmentsWorkFlowDTO)) {
			return false;
		}

		InvestmentsWorkFlowDTO transationWorkFlowDTO = (InvestmentsWorkFlowDTO) o;
		if (this.id == null) {
			return false;
		}
		return Objects.equals(this.id, transationWorkFlowDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "InvestmentsWorkFlowDTO [id=" + id + ", pacsCode=" + pacsCode + ", pacsId=" + pacsId + ", branchId="
				+ branchId + ", moduleId=" + moduleId + ", moduleType=" + moduleType + ", updatedBy=" + updatedBy
				+ ", updatedOn=" + updatedOn + ", remarks=" + remarks + ", status=" + status + ", statusName="
				+ statusName + "]";
	}

	

}
