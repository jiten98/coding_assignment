package com.pubmatic.stockquote.file.writer;

import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

public interface FileWriter {
	public void writeDataToFile() throws PubmaticBusinessException;

}
