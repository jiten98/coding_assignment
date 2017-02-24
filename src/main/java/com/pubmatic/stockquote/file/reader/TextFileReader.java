package com.pubmatic.stockquote.file.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

public class TextFileReader implements FileReader{
	
	private String fileName;

	public TextFileReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Load txt file from resources and return data in form of set maintaining reading order of data
	 */
	public Set<String> readDataFromFile() throws PubmaticBusinessException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		FileInputStream fis = null;
		Scanner scanner = null;
		Set<String> stockScripts = null;
		try {
			stockScripts = new LinkedHashSet<String>();
			fis = new FileInputStream(file);
			scanner = new Scanner(fis, "UTF-8");
			while (scanner.hasNext()) {
				stockScripts.add(scanner.next());
			}
			return stockScripts;
		} catch (Exception e) {
			throw new PubmaticBusinessException("Failed to process given file:" + fileName, e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new PubmaticBusinessException("Exception occured while closing file stream", e);
				}
			}
		}
	}
}
