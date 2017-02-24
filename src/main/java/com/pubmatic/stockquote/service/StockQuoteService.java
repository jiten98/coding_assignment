package com.pubmatic.stockquote.service;

import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

public interface StockQuoteService {
	public StockQuoteDetails getStockQuoteDetails(String scriptCode) throws PubmaticBusinessException;
}
