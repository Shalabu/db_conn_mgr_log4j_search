package util;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import search.Match;
import search.Matcher;

public class LogUtils {

	public static String findInLogs(String filePath, String word)
			throws FileNotFoundException {
		Matcher m = new Matcher();
		ArrayList<Match> listOfMatches = m.countMatches(filePath, word);
		int matches = listOfMatches.size();

		if (matches > 0)
			return "Found " + matches + " matches";
		else
			return "no match found!";
	}
}
