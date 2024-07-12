package com.intellect.investmentsms.service.dto;

import java.io.Serializable;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.appraisal.domain.CommonStatus} entity.
 */
public class CommonStatusDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String name;

	private String description;

	private Long categoryId;

	private Integer status;

	private String statusName;

	private String commonCategoryName;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public String getCommonCategoryName() {
		return commonCategoryName;
	}

	public void setCommonCategoryName(String commonCategoryName) {
		this.commonCategoryName = commonCategoryName;
	}

	@Override
	public String toString() {
		return "CommonStatusDTO [id=" + id + ", name=" + name + ", description=" + description + ", categoryId="
				+ categoryId + ", status=" + status + ", statusName=" + statusName + ", commonCategoryName="
				+ commonCategoryName + "]";
	}

}
