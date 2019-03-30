package impl.clt.rest;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

public class DownloadMediaClient {
	private static final int READ_TIMEOUT = 2000;
	private static final int CONNECT_TIMEOUT = 10000;

	public static void main(String[] args) throws IOException {
		
		ClientConfig config = new ClientConfig();
		
		config.property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
		config.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);

		Client client = ClientBuilder.newClient(config);

		String earthURI = "http://localhost:9999/rest/media/84486F586FA514F31F07057F39B68C673B7A091F";
		WebTarget target = client.target( earthURI );
		
		Response r = target.request()
				.accept(MediaType.APPLICATION_OCTET_STREAM)
				.get();

		if( r.getStatus() == Status.OK.getStatusCode() && r.hasEntity() )
			System.out.println("Response: " + (r.readEntity(byte[].class)).length);
		else
			System.out.println("Status: " + r.getStatus() );

	}

}
