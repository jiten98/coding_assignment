package com.pubmatic.stockquote.domain;

import java.util.Date;

public class Query {
	private int count;
	private Date created;
	private String lang;
	private Results results;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Results getResults() {
		return results;
	}
	public void setResults(Results results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return "Query [count=" + count + ", created=" + created + ", lang=" + lang + ", results=" + results + "]";
	}
	
}
