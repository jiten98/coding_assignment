package com.pubmatic.stockquote.service.impl;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pubmatic.stockquote.domain.ErrorResponse;
import com.pubmatic.stockquote.domain.Query;
import com.pubmatic.stockquote.domain.Quote;
import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.domain.StockQuoteResponse;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;
import com.pubmatic.stockquote.http.client.HttpClient;
import com.pubmatic.stockquote.service.StockQuoteService;

public class StockQuoteServiceImpl implements StockQuoteService{
	private static final Logger logger = LoggerFactory.getLogger(StockQuoteServiceImpl.class);
	private static final Gson GSON  = new GsonBuilder().create();

	public StockQuoteDetails getStockQuoteDetails(String scriptCode) throws PubmaticBusinessException{
		String apiEndPoint = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22"+scriptCode+"%22)&format=json&diagnostics=true"
				+"&env=store://datatables.org/alltableswithkeys";
		StockQuoteDetails stockQuoteDetails = null;
		try{
			logger.debug("Sending request to Yahoo server for script code [{}]", scriptCode);
			Response response = HttpClient.sendHttpGetRequest(apiEndPoint);
			String responseInString = response.readEntity(String.class);
			logger.debug("Response from Yahoo server for script code [{}] is {}", scriptCode, responseInString);
			if (response.getStatus() != 200) {
				ErrorResponse errorResponse = GSON.fromJson(responseInString, ErrorResponse.class);
				stockQuoteDetails = new StockQuoteDetails(response.getStatus(), errorResponse);
				return stockQuoteDetails;
			}
			StockQuoteResponse stockQuoteResponse = GSON.fromJson(responseInString, StockQuoteResponse.class);
			if(stockQuoteResponse != null && stockQuoteResponse.getQuery() != null){
				Query query = stockQuoteResponse.getQuery();
				if(query.getResults() != null){
					Quote quote = query.getResults().getQuote();
					stockQuoteDetails = new StockQuoteDetails(response.getStatus(), quote);
				}
			}
			return stockQuoteDetails;
		}catch(Exception e){
			throw new PubmaticBusinessException(e.getMessage(), e);
		}
	}
}
