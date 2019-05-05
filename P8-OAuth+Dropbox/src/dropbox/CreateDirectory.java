package dropbox;

public class CreateDirectory {

	public static void main(String[] args) throws Exception {
		DropboxClient.createClientWithAccessToken().createDirectory("/xpto");
	}

	
}
