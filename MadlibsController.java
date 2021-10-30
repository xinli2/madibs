import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MadlibsController {
	private ArrayList<String> list;
	private MadlibsModel model;// Model class object
	private Scanner in;// Scanner

	// Constructor to read template file and initialize the model

	public MadlibsController(String templates, String wordsFile) throws FileNotFoundException {
		model = new MadlibsModel(wordsFile);
		list = new ArrayList<String>();
		File file = new File(templates);
		in = new Scanner(file);
		while (in.hasNextLine()) {
			list.add(in.nextLine());
		}

	}

	// Return a random sentence from the templates
	public String getSentence(int randomNum) {
		return list.get(randomNum);
	}

	public Map getMap() {
		return model.getMap();
	}

}