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
 * A CardRates.
 */
@Entity
@Table(name = "card_rates")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CardRates extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "pacs_id")
	private Long pacsId;

	@Column(name = "tenure_type")
	private Integer tenureType;

	@Column(name = "min_tenure")
	private Integer minTenure;

	@Column(name = "max_tenure")
	private Integer maxTenure;

	@Column(name = "general_roi")
	private Float generalRoi;

	@Column(name = "staff_roi")
	private Float staffRoi;

	@Column(name = "seniorcitizen_roi")
	private Float seniorcitizenRoi;

	@Column(name = "penal_roi")
	private Float penalRoi;

	@Column(name = "effective_start_date")
	private Long effectiveStartDate;

	@Column(name = "effective_end_date")
	private Long effectiveEndDate;

	@Column(name = "status")
	private Integer status;

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

	@Override
	public int hashCode() {
		// see
		// https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "CardRates [id=" + id + ", pacsId=" + pacsId + ", tenureType=" + tenureType + ", minTenure=" + minTenure
				+ ", maxTenure=" + maxTenure + ", generalRoi=" + generalRoi + ", staffRoi=" + staffRoi
				+ ", seniorcitizenRoi=" + seniorcitizenRoi + ", penalRoi=" + penalRoi + ", effectiveStartDate="
				+ effectiveStartDate + ", effectiveEndDate=" + effectiveEndDate + ", status=" + status + "]";
	}

}
