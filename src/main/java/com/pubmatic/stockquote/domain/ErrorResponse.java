package com.pubmatic.stockquote.domain;

public class ErrorResponse {
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorResponse [error=" + error + "]";
	}
}
