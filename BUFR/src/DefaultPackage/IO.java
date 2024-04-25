package DefaultPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class IO {

	public static ArrayList<String> readFile(String fileName) {

		try {
			ArrayList<String> list = new ArrayList<String>();
			Scanner s = new Scanner(new FileInputStream(fileName));

			while (s.hasNextLine()) {
				list.add("STRING" + s.nextLine() + "\nENTER\nHOME");
			}

			return list;

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}

	}

	public static void writeFile(String fileName, ArrayList<String> list) {

		try {

			PrintStream p = new PrintStream(new File(fileName));

			for (int i = 0; i < list.size(); i++) {

				p.println(list.get(i));

			}

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
	}

}
