package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.List;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.appraisal.domain.CommonCategories} entity.
 */
public class CommonCategoriesDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String name;

	private String description;

	private Integer status;

	private String statusName;

	private List<CommonStatusDTO> commonStatusDTOList;

	public List<CommonStatusDTO> getCommonStatusDTOList() {
		return commonStatusDTOList;
	}

	public void setCommonStatusDTOList(List<CommonStatusDTO> commonStatusDTOList) {
		this.commonStatusDTOList = commonStatusDTOList;
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
	public String toString() {
		return "CommonCategoriesDTO [id=" + id + ", name=" + name + ", description=" + description + ", status="
				+ status + ", statusName=" + statusName + ", commonStatusDTOList=" + commonStatusDTOList + "]";
	}

}
