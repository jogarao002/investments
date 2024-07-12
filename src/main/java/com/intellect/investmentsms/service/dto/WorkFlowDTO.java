package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.appraisal.domain.WorkFlow} entity.
 */
public class WorkFlowDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String name;

	private String description;

	private Long effStartDate;

	private Long effEndDate;

	private Integer isActive;

	private String statusName;

	private Integer noOfSteps;

	private String code;

	private List<WorkFlowStepsDTO> workFlowStepsDTOList;

	private Long categoryId;

	private Integer isExceptional;

	private String categoryName;

	private String isExceptionalName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIsExceptionalName() {
		return isExceptionalName;
	}

	public void setIsExceptionalName(String isExceptionalName) {
		this.isExceptionalName = isExceptionalName;
	}

	public Integer getIsExceptional() {
		return isExceptional;
	}

	public void setIsExceptional(Integer isExceptional) {
		this.isExceptional = isExceptional;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEffStartDate() {
		return effStartDate;
	}

	public void setEffStartDate(Long effStartDate) {
		this.effStartDate = effStartDate;
	}

	public Long getEffEndDate() {
		return effEndDate;
	}

	public void setEffEndDate(Long effEndDate) {
		this.effEndDate = effEndDate;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<WorkFlowStepsDTO> getWorkFlowStepsDTOList() {
		return workFlowStepsDTOList;
	}

	public void setWorkFlowStepsDTOList(List<WorkFlowStepsDTO> workFlowStepsDTOList) {
		this.workFlowStepsDTOList = workFlowStepsDTOList;
	}

	public Integer getNoOfSteps() {
		return noOfSteps;
	}

	public void setNoOfSteps(Integer noOfSteps) {
		this.noOfSteps = noOfSteps;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WorkFlowDTO)) {
			return false;
		}

		return id != null && id.equals(((WorkFlowDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "WorkFlowDTO [id=" + id + ", name=" + name + ", description=" + description + ", effStartDate="
				+ effStartDate + ", effEndDate=" + effEndDate + ", isActive=" + isActive + ", statusName=" + statusName
				+ ", noOfSteps=" + noOfSteps + ", code=" + code + ", workFlowStepsDTOList=" + workFlowStepsDTOList
				+ ", categoryId=" + categoryId + ", isExceptional=" + isExceptional + ", categoryName=" + categoryName
				+ ", isExceptionalName=" + isExceptionalName + "]";
	}

}
