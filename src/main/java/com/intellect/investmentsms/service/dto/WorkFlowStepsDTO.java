package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;
import com.intellect.investmentsms.audit.AuditableDTO;



/**
 * A DTO for the {@link com.intellect.appraisal.domain.WorkFlowSteps} entity.
 */
public class WorkFlowStepsDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String name;

	private String code;

	private Integer workFlowId;

	private Integer commonStatusId;

	private Integer level;

	private Integer previousStep;

	private Integer isActive;

	private String statusName;

	private Integer noOfSteps;

	private String activityName;

	private String commonStatusName;

	private String workFlowname;

	private String relationshipType;

	private Integer isExceptional;

	private Long categoryId;

	private String categoryName;

	private String prevStepName;

	private String isExceptionalName;

	private Integer relationshipTypeId;

	private List<WorkFlowStepsDTO> children;

	public List<WorkFlowStepsDTO> getChildren() {
		return children;
	}

	public void setChildren(List<WorkFlowStepsDTO> children) {
		this.children = children;
	}

	public String getWorkFlowname() {
		return workFlowname;
	}

	public void setWorkFlowname(String workFlowname) {
		this.workFlowname = workFlowname;
	}

	public String getCommonStatusName() {
		return commonStatusName;
	}

	public void setCommonStatusName(String commonStatusName) {
		this.commonStatusName = commonStatusName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Integer workFlowId) {
		this.workFlowId = workFlowId;
	}

	public Integer getCommonStatusId() {
		return commonStatusId;
	}

	public void setCommonStatusId(Integer commonStatusId) {
		this.commonStatusId = commonStatusId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPreviousStep() {
		return previousStep;
	}

	public void setPreviousStep(Integer previousStep) {
		this.previousStep = previousStep;
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

	public Integer getNoOfSteps() {
		return noOfSteps;
	}

	public void setNoOfSteps(Integer noOfSteps) {
		this.noOfSteps = noOfSteps;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPrevStepName() {
		return prevStepName;
	}

	public void setPrevStepName(String prevStepName) {
		this.prevStepName = prevStepName;
	}

	public String getIsExceptionalName() {
		return isExceptionalName;
	}

	public void setIsExceptionalName(String isExceptionalName) {
		this.isExceptionalName = isExceptionalName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WorkFlowStepsDTO)) {
			return false;
		}

		return id != null && id.equals(((WorkFlowStepsDTO) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	public Integer getRelationshipTypeId() {
		return relationshipTypeId;
	}

	public void setRelationshipTypeId(Integer relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}

	@Override
	public String toString() {
		return "WorkFlowStepsDTO [id=" + id + ", name=" + name + ", code=" + code + ", workFlowId=" + workFlowId
				+ ", commonStatusId=" + commonStatusId + ", level=" + level + ", previousStep=" + previousStep
				+ ", isActive=" + isActive + ", statusName=" + statusName + ", noOfSteps=" + noOfSteps
				+ ", activityName=" + activityName + ", commonStatusName=" + commonStatusName + ", workFlowname="
				+ workFlowname + ", relationshipType=" + relationshipType + ", isExceptional=" + isExceptional
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", prevStepName=" + prevStepName
				+ ", isExceptionalName=" + isExceptionalName + ", relationshipTypeId=" + relationshipTypeId
				+ ", children=" + children + "]";
	}


}
