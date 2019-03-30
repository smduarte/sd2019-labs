package impl.clt.soap;

import java.net.URI;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import microgram.api.java.Result.ErrorCode;
import microgram.api.soap.MicrogramException;

abstract class SoapClient {
	private static final String WSDL = "?wsdl";

	protected final Service service;

	protected SoapClient(URI uri, QName qname) {
		this.service = Service.create(this.wsdlFrom(uri), qname);
	}

	// Translates the MicrogramException into an ErrorCode
	static protected ErrorCode errorCode(MicrogramException me) {
		switch (me.getMessage()) {
		case "OK":
			return ErrorCode.OK;
		case "CONFLICT":
			return ErrorCode.CONFLICT;
		case "NOT_FOUND":
			return ErrorCode.NOT_FOUND;
		case "INTERNAL_ERROR":
			return ErrorCode.INTERNAL_ERROR;
		case "NOT_IMPLEMENTED":
			return ErrorCode.NOT_IMPLEMENTED;
		default:
			return ErrorCode.INTERNAL_ERROR;
		}
	}

	protected URL wsdlFrom(URI uri) {
		try {
			return new URL(uri.toString() + WSDL);
		} catch (Exception x) {
			throw new RuntimeException(x.getMessage());
		}
	}
}
