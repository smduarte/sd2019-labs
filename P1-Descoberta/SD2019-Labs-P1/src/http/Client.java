package http;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import discovery.Discovery;

public class Client {
	
	
	public static void main(String[] args) throws IOException {
		
		URI[] instances = Discovery.findUrisOf(Server.NAME, 1);
		
		if( instances.length > 0 ) {
			String uri = instances[0].toURL() + "earth.jpg";

			URLConnection conn = new URL(uri).openConnection();
			conn.getHeaderFields().forEach( (h,v) -> {
				System.out.printf("%s: %s\n", h, v);
			});
			
		}
	}
	
}
