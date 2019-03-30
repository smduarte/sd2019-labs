package impl.clt.rest;

import java.util.function.Supplier;

import javax.ws.rs.ProcessingException;

import utils.Sleep;

public abstract class RetryClient {

	protected static final int RETRY_SLEEP = 500;

	// higher order function to retry forever a call of a void return type,
	// until it succeeds and returns to break the loop
	protected void reTry(Runnable func) {
		for (;;)
			try {
				func.run();
				return;
			} catch (ProcessingException x) {
				x.printStackTrace();
				Sleep.ms(RETRY_SLEEP);
			}
	}

	// higher order function to retry forever a call until it succeeds
	// and return an object of some type T to break the loop
	protected <T> T reTry(Supplier<T> func) {
		for (;;)
			try {
				return func.get();
			} catch (ProcessingException x) {
				x.printStackTrace();
				Sleep.ms(RETRY_SLEEP);
			}
	}
}
