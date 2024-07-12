package com.intellect.investmentsms.service.dto;

public enum FrequencyEnum {

	DAILY("Daily", 1), MONTHLY("Monthly", 2), QUARTERLY("Quarterly", 3), HALF_YEARLY("HalfYearly", 4),
	YEARLY("Yearly", 5), ON_MATURITY("On Maturity", 6);

	private String key;

	private Integer value;

	private FrequencyEnum(String key, Integer value) {
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
