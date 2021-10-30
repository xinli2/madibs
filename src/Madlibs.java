import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Madlibs {
	private MadlibsController controller;
	private HashMap map;
	private Map map2;

	public Madlibs(String templates, String wordsFile) throws FileNotFoundException {
		controller = new MadlibsController(templates, wordsFile);
		map = new HashMap<String, String>();
		// Store the tag and corresponding English meaning
		map.put("(ADJ)", "Adjective");
		map.put("(N)", "Noun");
		map.put("(PLN)", "Plural Noun");
		map.put("(GER)", "Verb Ending In \"-ing\"");
		map.put("(VPT)", "Verb Past Tense");
		map.put("(V)", "Verb");
		map.put("(PN)", "Proper Noun");
		map.put("(PPN)", "Plural Proper Noun");
		map.put("(AA)", "Article Adjective");
		map.put("(NUM)", "Number");

		map2 = controller.getMap();

	}

	// Start the game
	public void start() {
		// type no to end the game
		while (true) {
			int randomNum = ((int) (Math.random() * 4));
			String sentence = controller.getSentence(randomNum);

			Pattern p = Pattern.compile("\\([A-Z]+\\)");

			Matcher m = p.matcher(sentence);
			Scanner in = new Scanner(System.in);
			String out = null;

			while (m.find()) {
				String cur = m.group();
				System.out.print("Enter a(n) " + map.get(cur) + ": ");
				String input = in.next();

				while (map2.get(input) == null
						|| ((String) map2.get(input)).compareTo(cur.substring(1, cur.length() - 1)) != 0) {
					System.out.println(cur.substring(1, cur.length() - 1));
					System.out.println(map2.get(input));
					System.out.println("Please enter a valid " + map.get(cur));
					input = in.next();
				}
				out = m.replaceFirst(input);
				m = p.matcher(out);

			}
			System.out.println();
			System.out.println(out);
			System.out.println();
			System.out.println("Puzzle complete! Would you like to play again?");
			if (in.next().compareTo("no") == 0)
				break;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		Madlibs test = new Madlibs("templates.txt", "parts_of_speech.txt");
		test.start();

	}

}
