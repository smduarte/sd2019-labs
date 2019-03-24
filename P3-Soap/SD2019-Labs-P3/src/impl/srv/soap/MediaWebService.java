package impl.srv.soap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.jws.WebService;

import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapMedia;
import utils.Hash;

@WebService(serviceName=SoapMedia.NAME, targetNamespace=SoapMedia.NAMESPACE, endpointInterface=SoapMedia.INTERFACE)
public class MediaWebService implements SoapMedia {

	private static final String MEDIA_EXTENSION = ".jpg";
	private static final String ROOT_DIR = "/tmp/microgram/";
	
	MediaWebService() {
		new File( ROOT_DIR ).mkdirs();
	}
	
	@Override
	public String upload(byte[] bytes) throws MicrogramException {
		try {
			String id = Hash.of(bytes);
			File filename = new File(ROOT_DIR + id + MEDIA_EXTENSION);
			
			if( filename.exists() )
				throw new MicrogramException("Conflict...");
			
			Files.write(filename.toPath(), bytes);
			
			return id;
			
		} catch( IOException x  ) { 
			x.printStackTrace();
			throw new MicrogramException("Internal Error..." + x.getMessage());
		}
	}

	@Override
	public byte[] download(String id) throws MicrogramException {		
		throw new MicrogramException("Not implemented...");
	}
}



