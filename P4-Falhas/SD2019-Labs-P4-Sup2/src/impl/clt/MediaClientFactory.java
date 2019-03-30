package impl.clt;

import java.net.URI;

import impl.clt.rest.RestMediaClient;
import impl.clt.soap.SoapMediaClient;
import microgram.api.java.Media;

public class MediaClientFactory {

	private static final String REST = "/rest";
	private static final String SOAP = "/soap";

	public static Media getMediaClient(URI uri) {
		String uriString = uri.toString();
		if (uriString.endsWith(REST))
			return new RestMediaClient(uri);
		else if (uriString.endsWith(SOAP))
			return new SoapMediaClient(uri);

		throw new RuntimeException("Unknown service type..." + uri);
	}
}
