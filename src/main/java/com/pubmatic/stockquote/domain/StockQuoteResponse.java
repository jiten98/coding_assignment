package com.pubmatic.stockquote.domain;

public class StockQuoteResponse {
	private Query query;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "StockQuoteResponse [query=" + query + "]";
	}
}
