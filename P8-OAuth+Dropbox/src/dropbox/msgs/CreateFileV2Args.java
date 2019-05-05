package dropbox.msgs;

public class CreateFileV2Args {
	final String path;
	final String mode;
	final boolean autorename;
	final boolean mute;
	
	public CreateFileV2Args(String path) {
		this.path = path;
		this.mode = "add";
		this.autorename = false;
		this.mute = false;
	}
	
}
