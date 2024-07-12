package com.intellect.investmentsms.service.dto;

import java.io.Serializable;

import com.intellect.investmentsms.audit.Auditable;

/**
 * A DTO for the {@link com.intellect.termdepositsms.domain.CardRates} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CardRatesDTO extends Auditable<Long> implements Serializable {

	private Long id;

	private Long pacsId;

	private Integer tenureType;

	private Integer minTenure;

	private Integer maxTenure;

	private Float generalRoi;

	private Float staffRoi;

	private Float seniorcitizenRoi;

	private Float penalRoi;

	private Long effectiveStartDate;

	private Long effectiveEndDate;

	private Integer status;

	private String statusName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacsId() {
		return pacsId;
	}

	public void setPacsId(Long pacsId) {
		this.pacsId = pacsId;
	}

	public Integer getTenureType() {
		return tenureType;
	}

	public void setTenureType(Integer tenureType) {
		this.tenureType = tenureType;
	}

	public Integer getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(Integer minTenure) {
		this.minTenure = minTenure;
	}

	public Integer getMaxTenure() {
		return maxTenure;
	}

	public void setMaxTenure(Integer maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Float getGeneralRoi() {
		return generalRoi;
	}

	public void setGeneralRoi(Float generalRoi) {
		this.generalRoi = generalRoi;
	}

	public Float getStaffRoi() {
		return staffRoi;
	}

	public void setStaffRoi(Float staffRoi) {
		this.staffRoi = staffRoi;
	}

	public Float getSeniorcitizenRoi() {
		return seniorcitizenRoi;
	}

	public void setSeniorcitizenRoi(Float seniorcitizenRoi) {
		this.seniorcitizenRoi = seniorcitizenRoi;
	}

	public Float getPenalRoi() {
		return penalRoi;
	}

	public void setPenalRoi(Float penalRoi) {
		this.penalRoi = penalRoi;
	}

	public Long getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Long effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Long getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Long effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
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
		return "CardRatesDTO [id=" + id + ", pacsId=" + pacsId + ", tenureType=" + tenureType + ", minTenure="
				+ minTenure + ", maxTenure=" + maxTenure + ", generalRoi=" + generalRoi + ", staffRoi=" + staffRoi
				+ ", seniorcitizenRoi=" + seniorcitizenRoi + ", penalRoi=" + penalRoi + ", effectiveStartDate="
				+ effectiveStartDate + ", effectiveEndDate=" + effectiveEndDate + ", status=" + status + ", statusName="
				+ statusName + "]";
	}

}
