package impl.clt.soap;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.sun.xml.ws.client.BindingProviderProperties;

import impl.srv.soap.SoapMediaStorageServer;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapMedia;

import javax.xml.ws.BindingProvider;

public class DownloadMediaClient {
	// timeouts in ms
	static final int SOAP_CONN_TIMEOUT = 10000;
	static final int SOAP_RECV_TIMEOUT = 20000;

	private static Logger Log = Logger.getLogger(DownloadMediaClient.class.getName());

	private static final String WSDL = "?wsdl";

	public static void main(String[] args) throws IOException {

		String serverUrl = args.length > 0 ? args[0]
				: String.format("http://localhost:%s/soap/%s", SoapMediaStorageServer.PORT, SoapMedia.NAME);

		QName QNAME = new QName(SoapMedia.NAMESPACE, SoapMedia.NAME);
		try {
			Service service = Service.create(new URL(serverUrl + WSDL), QNAME);
			SoapMedia media = service.getPort(microgram.api.soap.SoapMedia.class);

			((BindingProvider) media).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, SOAP_RECV_TIMEOUT);
			((BindingProvider) media).getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT, SOAP_CONN_TIMEOUT);

			String id = "84486F586FA514F31F07057F39B68C673B7A091F";
			byte[] bytes = media.download(id);
			Log.info("Download completed: " + bytes.length + " bytes...");

		} catch (MicrogramException x) {
			Log.info("Download failed, reason: " + x.getMessage());
		} 
	}

}
