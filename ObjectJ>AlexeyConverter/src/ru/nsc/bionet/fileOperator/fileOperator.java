package ru.nsc.bionet.fileOperator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class fileOperator {
	
	public static List<String> readFile(String fileName) { //загрузка из файла в массив строк
		BufferedReader reader;
		List<String> tempLinesList = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = reader.readLine()) != null) {
				tempLinesList.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return tempLinesList;
	}

	public static void saveResults(List<String> results, String filePath, String resultsFileName) throws IOException {
		PrintStream out;
		try {
			out = new PrintStream(filePath + resultsFileName);
			for (String string : results) {
				out.println(string);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
