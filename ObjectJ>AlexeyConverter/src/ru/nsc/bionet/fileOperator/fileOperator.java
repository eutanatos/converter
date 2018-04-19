package ru.nsc.bionet.fileOperator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
