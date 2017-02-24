package com.pubmatic.stockquote.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;
import com.pubmatic.stockquote.service.StockQuoteService;
import com.pubmatic.stockquote.service.impl.StockQuoteServiceImpl;

public class StockQuoteProcessor {
	private static final int CONCURRENT_THREADS = 10;
	
	public List<StockQuoteDetails> process(Set<String> scriptCodes) throws PubmaticBusinessException, Exception{
		ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_THREADS);
		StockQuoteService stockQuoteService = new StockQuoteServiceImpl();
		List<Future<StockQuoteDetails>> resultList = new ArrayList<Future<StockQuoteDetails>>();
		List<StockQuoteDetails> stockQuoteDetailsList = new ArrayList<StockQuoteDetails>();
		try{
			for(String scriptCode : scriptCodes){
				StockQuoteTaskExecutor task = new StockQuoteTaskExecutor(stockQuoteService, scriptCode);
				Future<StockQuoteDetails> result = executor.submit(task);
				resultList.add(result);
			}
			for(Future<StockQuoteDetails> future : resultList){
				stockQuoteDetailsList.add(future.get());
			}
		}catch(Throwable t){
			executor.shutdownNow();
			throw new PubmaticBusinessException("Failed while fetching stock details from server", t);
		}finally {
			executor.shutdown();
		}
		
		return stockQuoteDetailsList;
	}
}
