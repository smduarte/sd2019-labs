package microgram.api.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName=SoapMedia.NAME, targetNamespace=SoapMedia.NAMESPACE, endpointInterface=SoapMedia.INTERFACE)
public interface SoapMedia {

	static final String NAME = "media";
	static final String NAMESPACE = "http://sd2019";
	static final String INTERFACE = "microgram.api.soap.SoapMedia";

	@WebMethod
	String upload( byte[] bytes) throws MicrogramException ;
	
	@WebMethod
	byte[] download(String id) throws MicrogramException;
}
