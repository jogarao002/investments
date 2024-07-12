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
 * A WorkFlow.
 */
@Entity
@Table(name = "work_flow")
//@Document(indexName = "workflow")

public class WorkFlow extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "eff_start_date")
	private Long effStartDate;

	@Column(name = "eff_end_date")
	private Long effEndDate;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "code")
	private String code;

	@Column(name = "noOfSteps")
	private Integer noOfSteps;

	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "is_exceptional")
	private Integer isExceptional;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public WorkFlow name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public WorkFlow description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEffStartDate() {
		return effStartDate;
	}

	public WorkFlow effStartDate(Long effStartDate) {
		this.effStartDate = effStartDate;
		return this;
	}

	public void setEffStartDate(Long effStartDate) {
		this.effStartDate = effStartDate;
	}

	public Long getEffEndDate() {
		return effEndDate;
	}

	public WorkFlow effEndDate(Long effEndDate) {
		this.effEndDate = effEndDate;
		return this;
	}

	public void setEffEndDate(Long effEndDate) {
		this.effEndDate = effEndDate;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public WorkFlow isActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getNoOfSteps() {
		return noOfSteps;
	}

	public void setNoOfSteps(Integer noOfSteps) {
		this.noOfSteps = noOfSteps;
	}

	public Integer getIsExceptional() {
		return isExceptional;
	}

	public void setIsExceptional(Integer isExceptional) {
		this.isExceptional = isExceptional;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WorkFlow)) {
			return false;
		}
		return id != null && id.equals(((WorkFlow) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "WorkFlow [id=" + id + ", name=" + name + ", description=" + description + ", effStartDate="
				+ effStartDate + ", effEndDate=" + effEndDate + ", isActive=" + isActive + ", code=" + code
				+ ", noOfSteps=" + noOfSteps + ", categoryId=" + categoryId + ", isExceptional=" + isExceptional + "]";
	}

	// prettier-ignore

}
