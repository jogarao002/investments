package com.intellect.investmentsms.service.dto;

public enum TenureTypeEnum {

	DAYS("Days", 1), YEARS("Years", 2);

	private String key;

	private Integer value;

	private TenureTypeEnum(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
