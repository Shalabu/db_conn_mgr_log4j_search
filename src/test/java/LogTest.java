import java.io.FileNotFoundException;

import logging.DemoLogger;

import org.junit.Assert;
import org.junit.Test;

import util.LogUtils;

public class LogTest {

	/*
	 * Here I test all scenarios in one test method in order to make sure that
	 * all logs are written to log file in order, to execute test properly.
	 */

	private static final String LOG_FILE = "logMe/default.log";

	@Test
	public void testLogSearch() {
		try {

			// Log a single line with match case
			DemoLogger.log("bingo");
			Assert.assertEquals("Found 1 matches",
					LogUtils.findInLogs(LOG_FILE, "bingo"));

			// Log a match case inside another word
			DemoLogger.log("bingoWorld!");
			Assert.assertEquals("Found 2 matches",
					LogUtils.findInLogs(LOG_FILE, "bingo"));

			// Log match case two times in the same line
			DemoLogger.log("bingo bingo");
			Assert.assertEquals("Found 4 matches",
					LogUtils.findInLogs(LOG_FILE, "bingo"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
