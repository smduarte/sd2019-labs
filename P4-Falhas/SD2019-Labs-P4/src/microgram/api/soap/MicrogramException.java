package microgram.api.soap;

import javax.xml.ws.WebFault;

@WebFault
public class MicrogramException extends Exception {

	private static final long serialVersionUID = 1L;

	public MicrogramException() {
		super("");
	}

	public MicrogramException(String errorMessage ) {
		super(errorMessage);
	}
}
