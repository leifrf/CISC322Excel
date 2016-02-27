import java.io.IOException;

import com.csvreader.CsvReader;



public class ReverseCSV {

	private static final String TEST_IN_FILE_PATH = "resources/test_in_file.csv";
	private static final String TEST_OUT_FILE_PATH = "resources/test_out_file.csv";	
	
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
		CsvReader products = new CsvReader("");

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
	

	public static Object[][] readCsv(String csvFile) throws IOException {
		CsvReader reader = new CsvReader(csvFile);
		int columnCount = reader.getColumnCount();
		// Logically equivalent to reader.getRowCount();
		int rowCount = reader.getHeaders().length;
		String[][] content = new String[rowCount][columnCount];
		int index = 0;
		while (reader.readRecord()) {
			content[index] = reader.getValues();
		}
		for (String[] entry : content) {
			for (String element : entry) {
				System.out.println(element);
			}
		}
		return null;
	}
	
	public static void main(String[] argv) throws IOException {
		System.out.println(argv.length);
		for (String arg : argv) {
			System.out.println(arg);
		}
		readCsv(argv[0]);
	}
}
