package com.pubmatic.stockquote.processor;

import java.util.concurrent.Callable;

import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;
import com.pubmatic.stockquote.service.StockQuoteService;

public class StockQuoteTaskExecutor implements Callable<StockQuoteDetails>{

	private StockQuoteService stockQuoteService;
	private String scriptCode;
	
	public StockQuoteTaskExecutor(StockQuoteService stockQuoteService, String scriptCode){
		this.stockQuoteService = stockQuoteService;
		this.scriptCode = scriptCode;
	}
	
	public StockQuoteDetails call() throws PubmaticBusinessException {
		try{
			return stockQuoteService.getStockQuoteDetails(scriptCode);
		}catch(Exception e){
			throw new PubmaticBusinessException(e.getMessage(), e);
		}
	}
}
