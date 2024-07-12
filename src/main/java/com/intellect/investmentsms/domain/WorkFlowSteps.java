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
 * A WorkFlowSteps.
 */
@Entity
@Table(name = "work_flow_steps")
//@Document(indexName = "workflowsteps")

public class WorkFlowSteps extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "work_flow_id")
	private Integer workFlowId;

	@Column(name = "common_status_id")
	private Integer commonStatusId;

	@Column(name = "level")
	private Integer level;

	@Column(name = "previous_step")
	private Integer previousStep;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "is_exceptional")
	private Integer isExceptional;

	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name="relationship_type")
    private String relationshipType;
    
    @Column(name="relationship_type_id")
    private Integer relationshipTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public WorkFlowSteps name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public WorkFlowSteps code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWorkFlowId() {
		return workFlowId;
	}

	public WorkFlowSteps workFlowId(Integer workFlowId) {
		this.workFlowId = workFlowId;
		return this;
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

	public WorkFlowSteps level(Integer level) {
		this.level = level;
		return this;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPreviousStep() {
		return previousStep;
	}

	public WorkFlowSteps previousStep(Integer previousStep) {
		this.previousStep = previousStep;
		return this;
	}

	public void setPreviousStep(Integer previousStep) {
		this.previousStep = previousStep;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public WorkFlowSteps isActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
	
	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Integer getRelationshipTypeId() {
		return relationshipTypeId;
	}

	public void setRelationshipTypeId(Integer relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WorkFlowSteps)) {
			return false;
		}
		return id != null && id.equals(((WorkFlowSteps) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "WorkFlowSteps [id=" + id + ", name=" + name + ", code=" + code + ", workFlowId=" + workFlowId
				+ ", commonStatusId=" + commonStatusId + ", level=" + level + ", previousStep=" + previousStep
				+ ", isActive=" + isActive + ", isExceptional=" + isExceptional + ", categoryId=" + categoryId
				+ ", relationshipType=" + relationshipType + ", relationshipTypeId=" + relationshipTypeId + "]";
	}

	

}
