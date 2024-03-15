/**
 * This class is simply a class I was using to test the output of the LogFileProcessor.java file. The path of the auth.log file can be inputed
 * as well as a value for a print flag. Based on these inputs, the output will return a statement reflecting data about the regex pattern matches.
 * @author Joshua Domzalski
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024 
 */

// Imports
import java.io.IOException;
import java.util.Scanner;

// Class declaration
public class Main {

	/**
	 * Main method to test the LogFileProcessor.java class
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// Scanner to read inputs
		Scanner scanner = new Scanner(System.in);
		
		// Asking for auth.log path file and value for print flag
		System.out.println("Input the path of the auth.log file: ");
		String filename = scanner.nextLine();
		System.out.println("Enter print flag value: ");
		int printFlag = scanner.nextInt();
		
		// Creating an instance of LogFileProcessor and passing the inputed information as its arguments
		LogFileProcessor logFileProcessor = new LogFileProcessor(filename, printFlag);

	} // End main

} // End class
