package com.pubmatic.stockquote.file.writer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.pubmatic.stockquote.domain.Quote;
import com.pubmatic.stockquote.domain.StockQuoteDetails;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

public class CSVFileWriter implements FileWriter {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	private static final String CSV_FILE_HEADER = "StockSymbol, CurrentPrice, YearTargetPrice, YearHigh, YearLow";

	private List<StockQuoteDetails> stockQuoteDetails;
	private String fileName;

	public CSVFileWriter(List<StockQuoteDetails> stockQuoteDetails, String fileName) {
		this.stockQuoteDetails = stockQuoteDetails;
		this.fileName = fileName;
	}

	public void writeDataToFile() throws PubmaticBusinessException {
		java.io.FileWriter writer = null;
		try {
			File file = new File(fileName);
			if(file.exists()){
				file.delete();
			}
			writer = new java.io.FileWriter(file);
			writer.append(CSV_FILE_HEADER);
			writer.append(NEW_LINE_SEPARATOR);
			for (StockQuoteDetails stockQuoteDetail : stockQuoteDetails) {
				if (stockQuoteDetail.getStatusCode() == 200) {
					Quote quote = (Quote) stockQuoteDetail.getDetails();
					if(quote.getCurrentPrice() == null){
						writer.append("-1");
					}else{
						writer.append(quote.getStockSymbol()).append(COMMA_DELIMITER);
						writer.append(String.valueOf(quote.getCurrentPrice())).append(COMMA_DELIMITER);
						writer.append(String.valueOf(quote.getYearTargetPrice() == null ? "NA" : quote.getYearTargetPrice())).append(COMMA_DELIMITER);
						writer.append(String.valueOf(quote.getYearHighPrice() == null ? "NA" : quote.getYearHighPrice())).append(COMMA_DELIMITER);
						writer.append(String.valueOf(quote.getYearLowPrice() == null ? "NA" : quote.getYearLowPrice())).append(COMMA_DELIMITER);
					}
				} else {
					writer.append("-1");
				}
				writer.append(NEW_LINE_SEPARATOR);
			}
		} catch (Exception e) {
			throw new PubmaticBusinessException("Faild to generate stock quote csv file", e);
		} finally {
			try {
				if(writer != null){
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				throw new PubmaticBusinessException("Error while flushing/closing fileWriter", e);
			}
		}
	}
}
