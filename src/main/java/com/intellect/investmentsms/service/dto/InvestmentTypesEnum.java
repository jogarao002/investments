package com.intellect.investmentsms.service.dto;

public enum InvestmentTypesEnum {

	DEPOSITS("Deposits", 1), SHARES("Shares", 2);

	private String key;
	private Integer value;

	private InvestmentTypesEnum(String key, Integer value) {
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
