package http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import discovery.Discovery;

/**
 * Implements a Discoverable WebServer.
 */
public class Server {
	static final int PORT = 8000;
	static final String NAME = "WebServer";
	
	/**
	 * Launches a basic WebServer that serves files from the current directory.
	 * Uses Discovery to announce the WebServer's base URI... 
	 */
	public static void main(String[] args) throws IOException {

		String ServerURI = String.format("http://%s:%d/", InetAddress.getLocalHost().getHostAddress(), PORT);
		
		Discovery.announce(NAME, ServerURI);			
		
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 1);		
		server.createContext("/", new FileHttpHandler());
		server.setExecutor(null);
		server.start();
	}


	/**
	 * Handle file requests, served from the current DIR;
	 */
	static class FileHttpHandler implements HttpHandler {
		static final int OK = 200, NOT_FOUND = 404;
		static final File BASE_DIR = new File(".");

		public void handle(HttpExchange t) throws IOException {
			try {
				String path = t.getRequestURI().getPath();
				byte[] data = Files.readAllBytes(new File(BASE_DIR, path).toPath());
				
				if( path.endsWith(JPEG_EXTENSION ))
					t.getResponseHeaders().set(CONTENT_TYPE_HEADER, JPEG_CONTENT_TYPE);				
				else
					t.getResponseHeaders().set(CONTENT_TYPE_HEADER, OCTET_STREAM_TYPE);				

				t.sendResponseHeaders(OK, data.length);

				OutputStream os = t.getResponseBody();
				os.write(data);
				os.close();
			} catch (IOException x) {
				x.printStackTrace();
				t.sendResponseHeaders(NOT_FOUND, 0);
				t.close();
			}
		}
	}
	
	static final String JPEG_EXTENSION = ".jpg";
	static final String JPEG_CONTENT_TYPE = "image/jpg";
	static final String OCTET_STREAM_TYPE = "application/octet-stream";
	static final String CONTENT_TYPE_HEADER = "Content-type";
}
