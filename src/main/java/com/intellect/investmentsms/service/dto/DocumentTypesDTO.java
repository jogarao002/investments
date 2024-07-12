package com.intellect.investmentsms.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.intellect.investmentsms.audit.AuditableDTO;

/**
 * A DTO for the {@link com.intellect.DocumentTypes.erp.domain.KycDocTypes} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentTypesDTO extends AuditableDTO<Long> implements Serializable {

	private Long id;

	private String description;

	private String name;

	private Integer status;
	
	private String statusName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(o instanceof DocumentTypesDTO)) {
			return false;
		}

		DocumentTypesDTO documentTypesDTO = (DocumentTypesDTO) o;
		if (this.id == null) {
			return false;
		}
		return Objects.equals(this.id, documentTypesDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return "DocumentTypesDTO [id=" + id + ", description=" + description + ", name=" + name + ", status=" + status
				+ ", statusName=" + statusName + "]";
	}

	
}
