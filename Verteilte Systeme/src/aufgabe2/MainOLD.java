package aufgabe2;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MainOLD {
	
	private static Master master;
	private static int n;
	private static int m;
	private static int[][] matrix_a;
	private static int[][] matrix_b;
	
	public static void main(String[] args) {
		System.out.println("Geben Sie die Anzahl der Spalten ein:");
		Scanner scanner = new Scanner(System.in);
		n = parseStringInt(scanner.next());
		System.out.println("Geben Sie die Anzahl der Zeilen ein:");
		m = parseStringInt(scanner.next());
		System.out.println("Bitte geben Sie Matrix A ein\nHinweis:\nZahlen müssen mit einem Komma getrennt und neue Zeilen mit einem Semikolon begonnen werden.\n"
				+ "Beispiel:\n"+"1,2,3;4,5,6");
		String input = scanner.next();
		String[] line = input.split(";");
		matrix_a = parseInputToMatrix(line);
		System.out.println("Bitte geben Sie Matrix B ein\nHinweis:\nZahlen müssen mit einem Komma getrennt und neue Zeilen mit einem Semikolon begonnen werden.\n"
				+ "Beispiel:\n"+"1,2,3;4,5,6");
		input = scanner.next();
		line = input.split(";");
		matrix_b = parseInputToMatrix(line);
		master = new Master();
		//master.manageMasterWorker();
	}
	
	private static int parseStringInt(String stringToParse) {
		int i = 0;
		try {
			i = Integer.parseInt(stringToParse);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return i;
	}
	
	private static int[][] parseInputToMatrix(String[] input) {
		int matrix[][] = new int[n][m];
		int index = 0;
		for (String s : input) {
			String[] numbers = s.split(",");
			int[] temp = new int[m];
			int index1 = 0;
			for(String number : numbers) {
				int numberInt = Integer.parseInt(number);
				temp[index1] = numberInt;
				index1++;
			}
			index++;
			matrix[index] = temp;
		}
		return matrix;
	}
}
