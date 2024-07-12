
package com.intellect.investmentsms.service.dto;

public enum CommonStatusEnum {

	CREATED("Created"), APPROVED("Approved"), REJECTED("Rejected"), INACTIVE("Inactive"), DORMANT("Dormant"),
	Submission_for_approval("Submission for approval"), Request_for_resubmission("Request for resubmission"),
	PAID("Paid"), NOT_PAID("Not Paid"), CHQUE_USED("Cheque Used"), CHQUE_NOT_USED("Cheque Not Used"),
	DEPOSITED("Deposited"), SETTELED("Setteled"), DUE("Due");

	private String name;

	private CommonStatusEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
