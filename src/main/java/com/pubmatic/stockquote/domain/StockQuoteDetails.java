package com.pubmatic.stockquote.domain;

public class StockQuoteDetails {
	
	private int statusCode;
	private Object details;
	
	public StockQuoteDetails(int statusCode, Object details) {
		this.statusCode = statusCode;
		this.details = details;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public Object getDetails() {
		return details;
	}
	
	@Override
	public String toString() {
		return "StockQuoteDetails [statusCode=" + statusCode + ", details=" + details + "]";
	}
	
}
