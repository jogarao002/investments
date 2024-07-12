package com.intellect.investmentsms.exception;

public class InvestmentsBusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvestmentsBusinessException() {
		super();
	}

	public InvestmentsBusinessException(String message) {
		super(message);
		this.message = message;
	}

	public InvestmentsBusinessException(Throwable message) {
		super(message);
	}

	@Override
	public String toString() {
		return message;

	}

}