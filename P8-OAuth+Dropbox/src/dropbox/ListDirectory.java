package dropbox;

public class ListDirectory  {
	public static void main(String[] args) throws Exception {
		DropboxClient.createClientWithAccessToken().listDirectory("");
	}
}
