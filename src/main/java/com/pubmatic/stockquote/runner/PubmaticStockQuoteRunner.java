package com.pubmatic.stockquote.runner;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;
import com.pubmatic.stockquote.file.reader.FileReader;
import com.pubmatic.stockquote.file.reader.TextFileReader;
import com.pubmatic.stockquote.file.writer.CSVFileWriter;
import com.pubmatic.stockquote.file.writer.FileWriter;
import com.pubmatic.stockquote.processor.StockQuoteProcessor;
/**
 * Application entry point
 * @author jitendra
 *
 */
public class PubmaticStockQuoteRunner {
	private static final Logger logger = LoggerFactory.getLogger(PubmaticStockQuoteRunner.class);
	private static final String SCRIPT_CODE_FILE_NAME = "Stocks.txt";
	private static final String SCRIPT_CODE_QUOTES_FILE_NAME = "StocksQuotes.csv";
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		try {
			FileReader reader = new TextFileReader(SCRIPT_CODE_FILE_NAME);
			Set<String> scriptCodes = reader.readDataFromFile();
			logger.info("Processing... {}", SCRIPT_CODE_FILE_NAME);
			StockQuoteProcessor stockQuoteProcessor = new StockQuoteProcessor();
			List<StockQuoteDetails> quotes = stockQuoteProcessor.process(scriptCodes);
			logger.info("{} has been processed", SCRIPT_CODE_FILE_NAME);
			FileWriter writer = new CSVFileWriter(quotes, SCRIPT_CODE_QUOTES_FILE_NAME);
			writer.writeDataToFile();
			logger.info("Stocks details has been saved in {}", SCRIPT_CODE_QUOTES_FILE_NAME);
		} catch (PubmaticBusinessException e) {
			logger.error("Error occured while fetching stock details. Application is shutting down now", e);
			System.exit(-1);
		}catch (Exception e) {
			logger.error("Internal application error", e);
		}
		long endTime = System.currentTimeMillis();
		long executionTimeInMilis = endTime - startTime;
		logger.info("Total execution time of application is :: "+executionTimeInMilis+" ms");
	}
}
