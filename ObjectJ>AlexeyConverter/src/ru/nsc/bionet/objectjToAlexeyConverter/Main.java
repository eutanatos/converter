package ru.nsc.bionet.objectjToAlexeyConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ru.nsc.bionet.fileOperator.fileOperator;

public class Main {
	static String fileName = "Results_Ulyana.txt"; //имя файла

	public static void main(String[] args) {
		/*
		 * загружаем из файла
		 */
		List<String> tempList = new ArrayList<String>(); //список для загрузки из файла
		tempList = fileOperator.readFile(fileName); //загружаем
		
		/*
		 * обрабатываем заголовок
		 */
		String headerIn = tempList.get(0); //берем строку с заголовком
		tempList.remove(0); //удаляем строку с заголовком чтобы больше не мешалась
		

		StringTokenizer headerTokenized = new StringTokenizer(headerIn,"\t"); //разбираем на токены
		String headerOut = "";	//заголовок, который будет выведен в файл
		int headerIndex = headerTokenized.countTokens();
		int LeafLengthIndex = 0;
		int MarkerIndex = 0;
		int FileNameIndex = 0;
		while (headerTokenized.hasMoreTokens()){				//проходим по токенам, запоминаем индекс заголовков
			String headerPart = headerTokenized.nextToken();
			switch (headerPart) {
			case "LeafLength":
				LeafLengthIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			case "Marker":
				headerOut = headerOut + headerPart + "\t";
				MarkerIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			case "FileName":
				headerOut = headerOut + headerPart + "\t";
				FileNameIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			default:
				break;
			}
		}
		headerOut = headerOut + "Leaf_1\t" + "Leaf_2\t" + "Leaf_3\t" + "Leaf_4\t" + "Leaf_5";
		System.out.println(headerOut);
		System.out.println(LeafLengthIndex + " " + MarkerIndex + "\t" + FileNameIndex);
		
		/*
		 * обрабатываем данные
		 */
		
		
		while (tempList.isEmpty() != true) { //пока в списке есть строки работаем
			String lineIn = tempList.get(0); //берем первую строку
			tempList.remove(0); //удаляем эту строку чтобы больше не мешалась
			
			StringTokenizer lineInTokenized = new StringTokenizer(lineIn,"\t"); //разбираем на токены
			int lineIndex = lineInTokenized.countTokens(); //размер строки
			String[] lineOutArray = new String[lineIndex];	//массив строк под 1 растение, которые будут выведены в файл
			int leafIndex = 0; //номер листа у растения
			
			while (lineInTokenized.hasMoreTokens()) { //
				String linePart = lineInTokenized.nextToken();
				switch (lineIndex - lineInTokenized.countTokens() - 1) { // смотрим что это - лист, имя файла, маркер растения...
				case LeafLengthIndex:
					System.out.println(linePart);
					break;
				case MarkerIndex: // если это маркер, то смотрим 
					System.out.println(linePart);
					if (lineOutArray[MarkerIndex] == linePart + "\t");
					lineOutArray[MarkerIndex] = linePart + "\t";
					break;
				case FileNameIndex: // тупо переписываем имя файла даже если оно такое же
					System.out.println(linePart);
					lineOutArray[FileNameIndex] = linePart + "\t";
					break;
				default:
					break;
			}
			String[] lineInArray = 
			while (headerTokenized.hasMoreTokens()){				//проходим по токенам строки
				String headerPart = headerTokenized.nextToken();
				/*
				 * выделяем блок
				 */
				int headerIndex = headerTokenized.countTokens();
				int LeafLengthIndex = 0;
				int MarkerIndex = 0;
				int FileNameIndex = 0;
				MarkerIndex = headerIndex - headerTokenized.countTokens() - 1;
				
		}
		/*
		 * трансформируем в нужный формат блока
		 */
		
		/*
		 * дописываем в вывод
		 */
	}
}