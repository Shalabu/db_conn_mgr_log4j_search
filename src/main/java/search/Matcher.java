package search;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Matcher {

	// private Scanner scanner;
	private ArrayList<Match> matches = new ArrayList<Match>();
	private int lineCounter = 0;

	public ArrayList<Match> countMatches(String filePath, String token)
			throws FileNotFoundException {
		FileInputStream fstream = new FileInputStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;

		try {
			while ((line = br.readLine()) != null) {
				lineCounter++;
				findInLine(line, token);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return matches;
	}

	private void findInLine(String line, String token) {
		if (line == null || line.length() < token.length())
			return;
		Match match;
		if (line.contains(token)) {
			// record a new match
			match = new Match();
			match.setColomn(line.indexOf(token));
			match.setLine(lineCounter);

			// add to list
			matches.add(match);

			// reform the line after cutting the first match
			line = line.substring(line.indexOf(token) + token.length());

			// recursion to find multiple occurrences in the same line
			findInLine(line, token);

		}

	}
}
