package impl.srv.rest;

import javax.ws.rs.core.Response.Status;

import microgram.api.java.Result;

public class RestResource {

	/**
	 * Translates a Result<T> to a HTTP Status code
	 */
	static protected Status statusCode(Result<?> result) {
		switch (result.error()) {
		case NOT_FOUND:
			return Status.NOT_FOUND;
		case CONFLICT:
			return Status.CONFLICT;
		case INTERNAL_ERROR:
			return Status.INTERNAL_SERVER_ERROR;
		case OK:
			return result.value() == null ? Status.NO_CONTENT : Status.OK;
		case NOT_IMPLEMENTED:
			return Status.NOT_IMPLEMENTED;
		default:
			return Status.INTERNAL_SERVER_ERROR;
		}
	}
}
