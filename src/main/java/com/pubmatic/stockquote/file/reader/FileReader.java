package com.pubmatic.stockquote.file.reader;

import java.util.Set;

import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

public interface FileReader {
	
	public Set<String> readDataFromFile() throws PubmaticBusinessException;

}
