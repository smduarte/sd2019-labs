package impl.clt;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.logging.Logger;

import discovery.Discovery;
import microgram.api.java.Media;
import microgram.api.java.Result;

public class MediaUploader {
	private static Logger Log = Logger.getLogger(MediaUploader.class.getName());

	public static final String SERVICE = "Microgram-MediaStorage";

	private static final File EARTH = new File("earth.jpg");

	public static void main(String[] args) throws Exception {

		URI[] mediaURIs = Discovery.findUrisOf(SERVICE, 1);
		if (mediaURIs.length > 0) {
			Media media = MediaClientFactory.getMediaClient(mediaURIs[0]);

			byte[] bytes = Files.readAllBytes(EARTH.toPath());
			Result<String> uri = media.upload(bytes);
			if (uri.isOK())
				Log.info("Upload completed: " + uri);
			else
				Log.info("Upload failed, reason: " + uri.error());
		}
	}
}
