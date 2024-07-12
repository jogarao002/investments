package com.intellect.investmentsms.service.dto;

public enum ModuleTypeEnum {
	
	PRODUCT_DEFINITION("Product Definition", 1), 
	TERM_DEPOSIT_INVESTMENT_ACCOUNTS("Term Deposit Investment Accounts", 2),
	SHARES_INVESTMENT_ACCOUNTS("Shares Investment Accounts", 3),
	INVESTMENT_BANK_DETAILS("Invested Bank Details", 4);

	private String key;
	private Integer value;

	private ModuleTypeEnum(String key, Integer value) {
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
