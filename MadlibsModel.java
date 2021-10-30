import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MadlibsModel {

	private HashMap map;// Map to store the words and associated tag
	private Scanner in;// Scanner

	// Constructor, initialize map scanner and fill the map
	public MadlibsModel(String wordsFile) throws FileNotFoundException {

		map = new HashMap<String, String>();
		File file = new File(wordsFile);
		in = new Scanner(file);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] temp = line.split("	");
			map.put(temp[0], temp[1]);

			// System.out.println(temp[0] + " " + temp[1]);
		}

	}

	public Map getMap() {
		return map;
	}
}
