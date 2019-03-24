package impl.clt;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import impl.srv.soap.MediaSoapServer;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapMedia;

public class UploadMediaClient {
	private static Logger Log = Logger.getLogger(UploadMediaClient.class.getName());

	private static final String WSDL = "?wsdl";

	private static final File EARTH = new File("earth.jpg");
	
	public static void main(String[] args) throws IOException {
		
		String serverUrl = args.length > 0 ? args[0] : String.format("http://localhost:%s/soap/%s", MediaSoapServer.PORT, SoapMedia.NAME);

		
		QName QNAME = new QName(SoapMedia.NAMESPACE, SoapMedia.NAME);		
		Service service = Service.create( new URL(serverUrl + WSDL), QNAME);
		SoapMedia media = service.getPort( microgram.api.soap.SoapMedia.class );
		
		try {
			byte[] bytes = Files.readAllBytes( EARTH.toPath() );
			String uri = media.upload( bytes );
			Log.info("Upload completed: " + uri );
		} catch( MicrogramException x ) {
			Log.info("Upload failed, reason: "  + x.getMessage());
		}
	} 

}
