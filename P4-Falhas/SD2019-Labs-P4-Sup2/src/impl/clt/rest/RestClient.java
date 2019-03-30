package impl.clt.rest;

import static microgram.api.java.Result.error;
import static microgram.api.java.Result.ok;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.glassfish.jersey.client.ClientConfig;

import microgram.api.java.Result;
import microgram.api.java.Result.ErrorCode;

abstract class RestClient extends RetryClient {

	private static final int READ_TIMEOUT = 15000;
	private static final int CONNECT_TIMEOUT = 10000;

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

	// Get the actual response, when the status matches what was expected, otherwise
	// return a default value
	protected <T> Result<T> verifyResponse(Response r, Status expected) {
		try {
			StatusType status = r.getStatusInfo();
			if (status.equals(expected))
				return ok();
			else
				return error(errorCode(status.getStatusCode()));
		} finally {
			r.close();
		}
	}

	// Get the actual response, when the status matches what was expected, otherwise
	// return a default value
	protected <T> Result<T> responseContents(Response r, Status expected, GenericType<T> gtype) {
		try {
			StatusType status = r.getStatusInfo();
			if (status.equals(expected))
				return ok(r.readEntity(gtype));
			else
				return error(errorCode(status.getStatusCode()));
		} finally {
			r.close();
		}
	}
}
