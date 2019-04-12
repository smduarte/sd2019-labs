package microgram.impl.clt.rest;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

public class DownloadMediaClient {

	public static void main(String[] args) throws IOException {

		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		String earthURL = "http://localhost:9999/rest/media/84486F586FA514F31F07057F39B68C673B7A091F";

		Response r = client.target(earthURL).request().accept(MediaType.APPLICATION_OCTET_STREAM).get();

		if (r.getStatus() == Status.OK.getStatusCode() && r.hasEntity())
			System.out.println("Response: " + r.readEntity(byte[].class).length);
		else
			System.out.println("Status: " + r.getStatus());

	}

}
