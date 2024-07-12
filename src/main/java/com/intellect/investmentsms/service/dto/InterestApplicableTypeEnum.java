package com.intellect.investmentsms.service.dto;

public enum InterestApplicableTypeEnum {

	PRODUCT("Product", 1), CARD_RATES("Card_Rates", 2);

	private String key;
	private Integer value;

	private InterestApplicableTypeEnum(String key, Integer value) {
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
