package com.intellect.investmentsms.service.dto;

public enum DataSeedingEnum {

	NAME("name"), DESCRIPTION("description"), CATEGORY_NAME("categoryName"), STATUS("status");
	

	private String columnName;

	private DataSeedingEnum(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
