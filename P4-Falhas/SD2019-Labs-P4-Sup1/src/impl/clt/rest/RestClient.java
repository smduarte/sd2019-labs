package impl.clt.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;

import microgram.api.java.Result.ErrorCode;

abstract class RestClient {

	protected static final int READ_TIMEOUT = 5000;
	protected static final int CONNECT_TIMEOUT = 10000;

	protected final URI uri;
	protected final Client client;
	protected final WebTarget target;
	protected final ClientConfig config;

	public RestClient(URI uri, String path) {
		this.uri = uri;
		this.config = new ClientConfig();
		this.client = ClientBuilder.newClient(config);
		this.target = this.client.target(uri).path(path);
	}

	static protected ErrorCode errorCode(int status) {
		switch (status) {
		case 200:
		case 209:
			return ErrorCode.OK;
		case 404:
			return ErrorCode.NOT_FOUND;
		case 409:
			return ErrorCode.CONFLICT;
		case 500:
			return ErrorCode.INTERNAL_ERROR;
		case 501:
			return ErrorCode.NOT_IMPLEMENTED;
		default:
			return ErrorCode.INTERNAL_ERROR;
		}
	}
}
