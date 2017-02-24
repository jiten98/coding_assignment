package com.pubmatic.stockquote.domain;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class Quote {
	
	@SerializedName("Symbol")
	private String stockSymbol;
	@SerializedName("YearLow")
	private BigDecimal yearLowPrice;
	@SerializedName("YearHigh")
	private BigDecimal yearHighPrice;
	@SerializedName("LastTradePriceOnly")
	private BigDecimal currentPrice;
	@SerializedName("OneyrTargetPrice")
	private BigDecimal yearTargetPrice;
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public BigDecimal getYearLowPrice() {
		return yearLowPrice;
	}
	public void setYearLowPrice(BigDecimal yearLowPrice) {
		this.yearLowPrice = yearLowPrice;
	}
	public BigDecimal getYearHighPrice() {
		return yearHighPrice;
	}
	public void setYearHighPrice(BigDecimal yearHighPrice) {
		this.yearHighPrice = yearHighPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getYearTargetPrice() {
		return yearTargetPrice;
	}
	public void setYearTargetPrice(BigDecimal yearTargetPrice) {
		this.yearTargetPrice = yearTargetPrice;
	}
	@Override
	public String toString() {
		return "Quote [stockSymbol=" + stockSymbol + ", yearLowPrice=" + yearLowPrice + ", yearHighPrice="
				+ yearHighPrice + ", currentPrice=" + currentPrice + ", yearTargetPrice=" + yearTargetPrice + "]";
	}
}
