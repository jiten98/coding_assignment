package com.pubmatic.stockquote.http.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.pubmatic.stockquote.exceptions.PubmaticBusinessException;

/**
 * This class is used to configure Http client
 */
public class HttpClientManager {

	private static final int MAX_TOTAL = 10;
	private static final int DEFAULT_MAX_PER_ROUTE = 5;
	private static final int READ_TIMEOUT = 10000;
	private static final int CONNECTION_TIMEOUT = 30000;

	private Client httpClient;

	private HttpClientManager() {

	}

	public Client getHttpClient() throws PubmaticBusinessException {
		configureHttpClient();
		if (httpClient == null) {
			throw new PubmaticBusinessException("Jersey Http Connection Manager is not initialzed yet");
		}
		return httpClient;
	}

	private void configureHttpClient() throws PubmaticBusinessException {
		try {
			PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
			connectionManager.setMaxTotal(MAX_TOTAL);
			connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
			ApacheConnectorProvider connectorProvider = new ApacheConnectorProvider();
			ClientConfig configuration = new ClientConfig();

			/* values are in milliseconds */
			configuration = configuration.property("httpconnection.pool.readtimeout", READ_TIMEOUT);
			configuration = configuration.property("httpconnection.pool.connectiontimeout", CONNECTION_TIMEOUT);
			configuration = configuration.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager).register(JacksonJaxbJsonProvider.class);
			configuration = configuration.connectorProvider(connectorProvider);
			httpClient = ClientBuilder.newClient(configuration);
		} catch (Exception e) {
			throw new PubmaticBusinessException("Exception occurred while configuring HttpClient.", e);
		}
	}

	private static class SingletonHolder {
		private SingletonHolder() {

		}
		private static final HttpClientManager _INSTANCE = new HttpClientManager();
	}

	public static HttpClientManager getInstance() {
		return SingletonHolder._INSTANCE;
	}

}
