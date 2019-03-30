package utils;

public class Sleep {
	
	public static void ms(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

	public static void seconds(int s) {
		ms(s * 1000);
	}

	public static void ms(int ms, boolean verbose) {
		String prev = "";
		long deadline = System.currentTimeMillis() + ms;
		while (System.currentTimeMillis() < deadline) {
			ms(500);
			String msg = String.format("sleeping: %d s%80s\r", (deadline - System.currentTimeMillis()) / 1000, " ");
			if (verbose && !prev.equals(msg)) {
				System.out.printf(msg);
				prev = msg;
			}
		}
		if (verbose)
			System.out.printf("%80s\r","");
	}

	public static void seconds(int s, boolean verbose) {
		ms(s * 1000, verbose);
	}

}
