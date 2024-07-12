package com.intellect.investmentsms.service.dto;

public enum DepositTypesEnum {

	FD_NON_CUMMULATIVE("FD Non Cummulative", 1), FD_CUMMULATIVE("FD Cummulative", 2), RD("RD", 3),SHARES("Shares",4);

	private String key;

	private Integer value;

	private DepositTypesEnum(String key, Integer value) {
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
