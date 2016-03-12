package excel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ReverseCSV {

	private static final String TEST_IN_FILE_PATH = "resources/samples/test_in_file.csv";
	private static final String TEST_OUT_FILE_PATH = "resources/samples/test_out_file.csv";	
	

	// Need to adapt this to accept an input stream 
	public static List<String[]> readCsv(String csvFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		List<String[]> allLines = reader.readAll();
		
		reader.close();
		return allLines;
	}
	
	public static List<String[]> readCsv(InputStream csvStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(csvStream));
		List<String[]> allLines = new ArrayList<String[]>();
		String line;
		while((line = in.readLine()) != null) {
		    allLines.add(line.split(","));
		}
		return allLines;
	}
	
	// need to adapt this to accept an output stream
	public static void writeCsv(List<String[]> lines, String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName));
		writer.writeAll(lines);
		writer.close();
	}

	public static List<String[]> writeCsv(InputStream csvStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(csvStream));
		List<String[]> allLines = new ArrayList<String[]>();
		String line;
		while((line = in.readLine()) != null) {
		    allLines.add(line.split(","));
		}
		return allLines;
	}
	
	public static String[] reverseLine(String[] line) {
		String[] reversedLine = new String[line.length];
		int index = line.length - 1;
		for (String entry : line)
			reversedLine[index--] = entry;
		return reversedLine;
	}
	
	public static void reverseCsvFile(String sourceFile, String destinationFile) throws IOException {
		List<String[]> lines = readCsv(sourceFile);
		List<String[]> reversedLines = new ArrayList<String[]>();
		for (String[] line : lines)
			reversedLines.add(reverseLine(line));
		writeCsv(reversedLines, destinationFile);
	}
	
	public static void main(String[] argv) throws IOException {
		String outMessage = "There are " + argv.length + " arguments.\n";
		System.out.println(outMessage);
		for (String arg : argv) {
			System.out.println(arg);
		}

		String sourceFile = argv[0];
		String destinationFile = argv[1];
		
		reverseCsvFile(sourceFile, destinationFile);
	}
}
