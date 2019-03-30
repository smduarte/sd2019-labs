package impl.srv.soap;

import java.util.Random;

import javax.jws.WebService;

import impl.srv.shared.JavaMedia;
import microgram.api.java.Media;
import microgram.api.java.Result;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapMedia;

@WebService(serviceName=SoapMedia.NAME, targetNamespace=SoapMedia.NAMESPACE, endpointInterface=SoapMedia.INTERFACE)
public class MediaWebService implements SoapMedia {

	final Media impl;
	
	MediaWebService() {
		this.impl = new JavaMedia();
	}
	
	@Override
	public String upload(byte[] bytes) throws MicrogramException {
		Result<String> result = impl.upload(bytes);
		if (result.isOK())
			return result.value();
		else
			throw new MicrogramException( result.error().toString() );
	}

	@Override
	public byte[] download(String id) throws MicrogramException {	
		Result<byte[]> result = impl.download(id);
		if (result.isOK())
			return result.value();
		else
			throw new MicrogramException( result.error().toString() );
	}
}



