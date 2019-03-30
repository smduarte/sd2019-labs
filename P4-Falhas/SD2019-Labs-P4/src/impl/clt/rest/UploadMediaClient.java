package impl.clt.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import impl.srv.rest.RestMediaResources;


public class UploadMediaClient {
	private static final int READ_TIMEOUT = 2000;
	private static final int CONNECT_TIMEOUT = 10000;

	private static final File EARTH = new File("earth.jpg");
	
	public static void main(String[] args) throws IOException {
		
		String serverUrl = args.length > 0 ? args[0] : "http://localhost:9999/rest";

		byte[] bytes = Files.readAllBytes( EARTH.toPath() );
				
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		config.property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
		config.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
		
		WebTarget target = client.target( serverUrl ).path( RestMediaResources.PATH );
		
		Response r = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));

		if( r.getStatus() == Status.OK.getStatusCode() && r.hasEntity() )
			System.out.println("Response: " + r.readEntity(String.class ) );
		else
			System.out.println("Status: " + r.getStatus() );

	}

}
