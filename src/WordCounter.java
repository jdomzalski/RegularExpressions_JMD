/**
 * This class reads the output files generated from NovelProcessor.java and calculates the total number of instances
 * of each regex pattern across all six novels.
 * @author Joshua Domzalski
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024 
 */

// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.*;
import java.io.*;

// Class declaration
public class WordCounter{

	/**
	 * This is the main method. An IO exception is thrown because we are reading inputs.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// Defining a scanner to read the paths of the output files generated from the NovelProcessor.java class
		Scanner scanner = new Scanner(System.in);
		
		// ArrayLists that will keep track of our regular expression patterns and our output files
		ArrayList<String> regexList = new ArrayList<String>();
		ArrayList<String> outputFilesList = new ArrayList<String>();
		
		// Copying the regular expression patterns used in the previous matching and adding them to the regexList ArrayList
		String regex_1 = "(G|g)loom(y|ed|ing|s|\\s)";
		regexList.add(regex_1);
		String regex_2 = "(H|h)aunt(ing|s|ed|y|\\s)";
		regexList.add(regex_2);
		String regex_3 = "(M|m)yster(ious|iously|y)";
		regexList.add(regex_3);
		String regex_4 = "(F|f)orebod(ing|e|ed)";
		regexList.add(regex_4);
		String regex_5 = "(M|m)elanchol(ic|y)";
		regexList.add(regex_5);
		String regex_6 = "(T|t)err(or|if)(s|ed|ing|ied|ying|\\s)";
		regexList.add(regex_6);
		String regex_7 = "(D|d)esolate(\\s|s|d)";
		regexList.add(regex_7);
		
		// Defining and intializing the Hashmap. This Hashmap will use the regular expression patterns as the keys and the number of instances as the values.
		HashMap<String, Integer> regexCounter = new HashMap<>() {{
			put(regex_1, 0);
			put(regex_2, 0);
			put(regex_3, 0);
			put(regex_4, 0);
			put(regex_5, 0);
			put(regex_6, 0);
		}};
		
		// Defining and intializing string variables that will be used to store the output file paths
		String draculaFile = null;
		String drJekyllFile = null;
		String frankensteinFile = null;
		String castleOfOtrantoFile = null;
		String pictureOfDGFFile = null;
		String turnOfTheScrewFile = null;
	
		// Asking the user to copy the path of each output file generated from the NovelProcessor.java class and adding each one to the outputFilesList ArrayList
		System.out.println("Enter the path of the Dracula output file: ");
		draculaFile = scanner.nextLine();
		outputFilesList.add(draculaFile);
		System.out.println("Enter the path of the Dr. Jekyll and Mr. Hyde output file: ");
		drJekyllFile = scanner.nextLine();
		outputFilesList.add(drJekyllFile);
		System.out.println("Enter the path of the Frakenstein output file: ");
		frankensteinFile = scanner.nextLine();
		outputFilesList.add(frankensteinFile);
		System.out.println("Enter the path of the The Castle of Otranto output file: ");
		castleOfOtrantoFile = scanner.nextLine();
		outputFilesList.add(castleOfOtrantoFile);
		System.out.println("Enter the path of the The Picture of Dorian Gray output file: ");
		pictureOfDGFFile = scanner.nextLine();
		outputFilesList.add(pictureOfDGFFile);
		System.out.println("Enter the path of the The Turn of the Screw output file: ");
		turnOfTheScrewFile = scanner.nextLine();
		outputFilesList.add(turnOfTheScrewFile);
		
		// Iterating through each output file
		for(String outputFile: outputFilesList) {
			
			// A reader that reads the contents of the output file
			BufferedReader outputFileReader = new BufferedReader(new FileReader(outputFile));
			// Defining a string variable for the output line
			String outputLine;
			
			// While loop that keeps running while the output line is not null
			while((outputLine = outputFileReader.readLine()) != null) {
					
					// Defining a StringTokenizer and passing the output line variable into it. This will be used to read the
					// string and int values
					StringTokenizer stringTokenizer = new StringTokenizer(outputLine);
					// Defining string tokens to take care of the text values of the output file
					String regex_instance = stringTokenizer.nextToken();
					String slash = stringTokenizer.nextToken();
					
					// Defining an Integer variable and token to keep track of the instance value defined in each line of the output file
					Integer instances = 0;					
					int i_instances = Integer.parseInt(stringTokenizer.nextToken());
					instances = Integer.valueOf(i_instances);
					
					// For loop that iterates through every entry in the Hashmap.
					for(Entry<String, Integer> entry : regexCounter.entrySet()) {
						// If statement that gets the value of the current regex pattern we are on and updates the value by adding the instances variable.
						if(entry.getKey().equals(regex_instance)) {
							int value = entry.getValue();
							entry.setValue(value + instances);
						} // End if statement
					} // End for loop
					
								
			} // End while loop
			
		} // End for loop
		
		// For loop that iterates through every entry in the Hashmap. The loop prints every key (regex pattern) and its respective value (total number of instances across all novels)
		for(Entry<String, Integer> entry : regexCounter.entrySet()) {
			System.out.println(entry.getKey() + " | " + entry.getValue());
		} // End for loop		

	} // End main method

} // End class
