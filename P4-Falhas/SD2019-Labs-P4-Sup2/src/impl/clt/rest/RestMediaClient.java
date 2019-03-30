package impl.clt.rest;

import java.net.URI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import microgram.api.java.Media;
import microgram.api.java.Result;
import microgram.api.rest.RestMediaStorage;

public class RestMediaClient extends RestClient implements Media {

	public RestMediaClient(URI mediaStorage) {
		super(mediaStorage, RestMediaStorage.PATH);
	}

	public Result<String> upload(byte[] bytes) {
		return super.reTry(() -> _upload_v1(bytes));
		//return super.reTry(() -> _upload_v2(bytes));
	}

	public Result<byte[]> download(String url) {
		throw new RuntimeException("Not Implemented");
	}

	Result<String> _upload_v1(byte[] bytes) {
		Response r = super.target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));

		if (r.getStatusInfo().equals(Status.OK))
			return Result.ok(r.readEntity(String.class));
		else
			return Result.error(super.errorCode(r.getStatus()));
	}

	Result<String> _upload_v2(byte[] bytes) {
		Response r = super.target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));

		return super.responseContents(r, Status.OK, new GenericType<String>() {
		});
	}
}