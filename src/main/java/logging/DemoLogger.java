package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoLogger {
	private final static Logger logger = LogManager.getLogger(DemoLogger.class
			.getName());

	public static synchronized void log(String message) {
		logger.info(message);
	}

}
