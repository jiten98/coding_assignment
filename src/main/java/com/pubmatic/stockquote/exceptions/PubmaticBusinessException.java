package com.pubmatic.stockquote.exceptions;

@SuppressWarnings("serial")
public class PubmaticBusinessException extends Exception{

	public PubmaticBusinessException(){
		super();
	}
	
	public PubmaticBusinessException(String message){
		super(message);
	}
	
	public PubmaticBusinessException(Throwable cause){
		super(cause);
	}
	
	public PubmaticBusinessException(String message, Throwable cause){
		super(message, cause);
	}
}
