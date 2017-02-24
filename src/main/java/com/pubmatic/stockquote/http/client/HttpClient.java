package com.pubmatic.stockquote.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

/**
 * HttpClient is used to communicate yahoo api
 *
 * @author jitendra
 */
public class HttpClient {
	private static final HttpClientManager _HTTP_CLIENT_MANAGER = HttpClientManager.getInstance();

	private HttpClient() {
	}

	public static Response sendHttpGetRequest(String url) throws PubmaticBusinessException {
		Client httpClient;
		try {
			httpClient = _HTTP_CLIENT_MANAGER.getHttpClient();
		} catch (Exception e) {
			throw new PubmaticBusinessException(e.getMessage(), e);
		}
		try {
			final WebTarget target = httpClient.target(url);
			return target.request("application/json").get();
		} catch (Exception e) {
			throw new PubmaticBusinessException("Http client could not process the request.", e);
		}
	}
	
}
