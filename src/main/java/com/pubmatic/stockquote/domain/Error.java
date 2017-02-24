package com.pubmatic.stockquote.domain;

public class Error {
	private String lang;
	private String description;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Error [lang=" + lang + ", description=" + description + "]";
	}
}
