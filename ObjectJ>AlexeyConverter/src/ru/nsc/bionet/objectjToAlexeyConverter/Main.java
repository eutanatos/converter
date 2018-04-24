package ru.nsc.bionet.objectjToAlexeyConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ru.nsc.bionet.fileOperator.fileOperator;

public class Main {
	static String filePath = ""; //путь к файлу
	static String fileName = "Results_Ulyana.txt"; 						//имя файла

	public static void main(String[] args) {
		/*
		 * загружаем из файла
		 * @todo переименовать fileOperator -> LeafFileRW
		 */ 
		List<String> tempList = new ArrayList<String>(); 				//список для загрузки из файла
		tempList = fileOperator.readFile(fileName); 					//загружаем

		/*
		 * обрабатываем заголовок
		 */
		String headerIn = tempList.get(0); 								//берем строку с заголовком
		tempList.remove(0); 											//удаляем строку с заголовком чтобы больше не мешалась

		StringTokenizer headerTokenized = new StringTokenizer(headerIn,"\t"); 	//разбираем на токены
		String headerOut = "";													//заголовок, который будет выведен в файл
		int headerIndex = headerTokenized.countTokens();
		int leafLengthIndex = 0;
		int markerIndex = 0;
		int fileNameIndex = 0;
		while (headerTokenized.hasMoreTokens()){								//проходим по токенам, запоминаем индекс заголовков
			String headerPart = headerTokenized.nextToken();
			switch (headerPart) {
			case "LeafLength":
				leafLengthIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			case "Marker":
				headerOut = headerOut + headerPart + "\t";
				markerIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			case "FileName":
				headerOut = headerOut + headerPart + "\t";
				fileNameIndex = headerIndex - headerTokenized.countTokens() - 1;
				break;
			default:
				break;
			}
		}
		headerOut = headerOut + "Leaf_1\t" + "Leaf_2\t" + "Leaf_3\t" + "Leaf_4\t" + "Leaf_5"; //собираем заголовок, добавляем столбцы с листьями
		System.out.println(headerOut);
		System.out.println(leafLengthIndex + " " + markerIndex + "\t" + fileNameIndex);

		/*
		 * обрабатываем данные
		 */

		int markerIndexPrevious = 0; //стартовое значение счетчика маркеров
		String lineOut = "";//выходные данные, текущая строка
		List<String> plantsList = new ArrayList<String>(); //выходные данные, построчно в список

		while (tempList.isEmpty() != true) { //пока во входном списке есть строки работаем
			/*
			 * формируем блок данных по всем листьям одного растения
			 */		
			String lineIn = tempList.get(0); //берем очередную строку из начала списка
			tempList.remove(0); //удаляем эту строку из массива, чтобы больше не мешалась

			StringTokenizer lineInTokenized = new StringTokenizer(lineIn,"\t"); //разбираем строку на токены

			int lineIndex = lineInTokenized.countTokens(); //определяем размер массива для перевода токенизированной строки в массив входных данных 
			String[] lineInArray = new String[lineIndex];	//создаем массив входных данных

			while (lineInTokenized.hasMoreTokens()) { //переводим токены в массив входных данных
				String linePart = lineInTokenized.nextToken();
				lineInArray[lineIndex - lineInTokenized.countTokens() - 1] = linePart;
			}

			//пересобираем входную строку в заданном порядке {маркер растения, имя файла, длина листа 1, ...}
			if (Integer.parseInt(lineInArray[markerIndex]) == markerIndexPrevious) { //если предыдущая строка относится к тому же растению продолжаем дописывать длины листьев
				lineOut = lineOut + lineInArray[leafLengthIndex] + "\t"; //дописываем длину следующего листа к выводу
				markerIndexPrevious = Integer.parseInt(lineInArray[markerIndex]); //ставим текущий маркер растения
			} else {//if (markerIndex !== markerIndexPrevious) //если перешли к следующему маркеру или только начали (первый маркер 1)
				//скидываем готовую выводную строку в массив, подрезаем на всякий
				plantsList.add(lineOut.trim());
				//обнуляем выводную строку под следующее растение
				lineOut = "";
				lineOut = lineInArray[markerIndex] + "\t"; //записываем маркер и имя файла
				lineOut = lineOut + lineInArray[fileNameIndex] + "\t";
				lineOut = lineOut + lineInArray[leafLengthIndex] + "\t";//записываем длину 1 листа
				markerIndexPrevious = markerIndex;
			}	
		}

		/*
		 * вывод в файл
		 */
		fileOperator.saveResults(plantsList, filePath, fileName);
	}
}

}