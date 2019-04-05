package microgram.api.java;

/**
 * Interface of the media storage service...
 */
public interface MediaStorage {
		
	/**
	 * Uploads a media resource
	 * @param bytes the contents in bytes of the media resource
	 * @return the uri of the stored resource
	 */
	Result<String> upload( byte[] bytes);
	
	/**
	 * Downloads a media resource 
	 * @param id the (the path portion) of the media resource uri
	 * @return the bytes that comprise the contents of the media resource, or NOT_FOUND
	 */
	Result<byte[]> download(String id);
	
	
	/**
	 * Deletes a media resource 
	 * @param id the (the path portion) of the media resource uri
	 */
	Result<Void> delete(String id);
}

