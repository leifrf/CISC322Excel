import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Logger;

import com.csvreader.CsvReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSV_Package_Test {
	private static final Logger log = Logger.getLogger( CSV_Package_Test.class.getName());
	private static final String TEST_IN_FILE_PATH = "resources/test_in_file.csv";
	private static final String TEST_OUT_FILE_PATH = "resources/test_out_file.csv";
	
	public static void main(String[] argv) throws Exception {
		log.info("Testing different csv read/write methods.");
		// System.out.println("opencsv:");
		// opencsv();
		
		System.out.println("javacsv:");
		javacsv();
	}
	
	/**
	 * http://opencsv.sourceforge.net/
	 * 
	 * Positive:
	 * Very simple to implement
	 * Widely used
	 * Updated 2016-01-29
	 * 
	 * Negative:
	 * Limited functionality
	 * 
	 * @throws Exception
	 */
	public static void opencsv() throws Exception{
	
		
	List<String[]> csvLines = null;

	// Test reading a CSV file
	FileReader csvFileReader = new FileReader(TEST_IN_FILE_PATH);
	CSVReader reader = new CSVReader(csvFileReader);
	csvLines = reader.readAll();
	reader.close();

	printStringList(csvLines);
	
	// Test writing a CSV file
	FileWriter csvFileWriter = new FileWriter(TEST_OUT_FILE_PATH);
	CSVWriter writer = new CSVWriter(csvFileWriter);
	writer.writeAll(csvLines);
	writer.close();
	}
	
	/**
	 * https://sourceforge.net/projects/javacsv/
	 * 
	 * Positive:
	 * Full test suite available
	 * JSON-like implementation
	 * Many flags/adjustable parameters
	 * Easy to write to files
	 * 
	 * Negative:
	 * Updated 2014-12-10
	 * Excess overhead for implementation
	 * 
	 * @throws Exception
	 */
	public static void javacsv() throws Exception{
		CsvReader products = new CsvReader(TEST_IN_FILE_PATH);
		products.readHeaders();
		while (products.readRecord())
		{
			String name = products.get("name");
			String age = products.get("age");
			String sex = products.get("sex");

			// perform program logic here
			System.out.println(name + " : " + age + " : " + sex);
		}

		products.close();
	}

	/**
	 * https://github.com/super-csv/super-csv
	 * 
	 * Negative:
	 * Very complex
	 * 
	 */
	public static void supercsv() {
		
	}
		
	public static void printStringList(List<String[]> lines) {
		for (String[] line : lines) {
			boolean first = true;
			for (String element : line) {
				if (!first) {
					System.out.print(", ");
				}
				System.out.print(element);
				first = false;
			}
			System.out.print("\n");			
		}
	}
}
