package microgram.impl.srv.rest;

import java.net.URI;
import java.util.logging.Logger;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import utils.IP;

public class RestMediaStorageServer {
	private static Logger Log = Logger.getLogger(RestMediaStorageServer.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}

	public static final int PORT = 9999;
	public static final String SERVICE = "Microgram-MediaStorage";
	public static String SERVER_BASE_URI = "http://%s:%s/rest";

	public static void main(String[] args) throws Exception {

		String ip = IP.hostAddress();
		String serverURI = String.format(SERVER_BASE_URI, ip, PORT);

		ResourceConfig config = new ResourceConfig();

		config.register(new RestMediaResources(serverURI));

		JdkHttpServerFactory.createHttpServer(URI.create(serverURI.replace(ip, "0.0.0.0")), config);

		Log.info(String.format("%s Server ready @ %s\n", SERVICE, serverURI));
	}
}
