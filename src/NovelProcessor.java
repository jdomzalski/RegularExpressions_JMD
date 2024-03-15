/**
 * This class reads a novel file and a regex file (that contains instances of multiple regex patterns) and matches how many times
 * each regex pattern is in a given novel. The answer of matches for each regex pattern is then written to an output file.
 * @author Joshua Domzalski
 * @version 1.0
 * Assignment 4
 * CS322 - Compiler Construction
 * Spring 2024 
 */

// Imports needed
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

// Class declaration
public class NovelProcessor {

	/**
	 * This is the main method. An IO exception is thrown because we are reading/writing.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// Variable declaration
		Scanner scanner = new Scanner(System.in);
        ArrayList<String> files = new ArrayList<String>();
        String novelFile = null;
        String regexFile = null;
        
        // Asking for inputs to the path of the novel file and the regex file, respectively
        System.out.println("Enter the path of the novel file to process: ");
        novelFile = scanner.nextLine();
        // I added my regex patterns in a text file here to be passed in. Each respective pattern from the file is then read and checked for matches.
        System.out.println("Enter the path of the regex.txt file: ");
        regexFile = scanner.nextLine();
              
        // Defining our reader that will read the regex file and our writer that will write the output file
       BufferedReader regexReader = new BufferedReader(new FileReader(regexFile));
       BufferedWriter outputWriter = new BufferedWriter(new FileWriter(novelFile.replace(".txt", "_wc.txt")));
       

       // Initializing an instances variable and defining a regex variable
       int instances = 0;
       String regex;
       // While loop that keeps going until there is not a regex pattern left to read
       while((regex = regexReader.readLine()) != null) {
    	       	   
    	   // Defining the reader that will read the novel file
    	   BufferedReader novelReader = new BufferedReader(new FileReader(novelFile));
    	   
    	   // Creating a pattern variable and passing in the regex from the reader. This defines what will be used in our search.
    	   Pattern pattern = Pattern.compile(regex);
    	   
    	   // Decalring a variable for the string of the novel
    	   String novelString;
    	   
    	   	   // While loop that keeps going until there is not a novel string left to read
    		   while((novelString = novelReader.readLine()) != null) {
        		   
    			   // Defining a matcher variable and passing in the novel string. This matcher is used to search the pattern we defined earlier
        		   Matcher matcher = pattern.matcher(novelString);
        		   // If a match is found, the number of instances is incremented by 1
        		   boolean matchFound = matcher.find();
        		   if (matchFound) {
        			   instances++;
        		   } // End if statement
        	   } // End while loop
    		  
    	   // Reseting the novel string variable
    	   novelString = novelReader.readLine();
    	   
    	   // Checking to make sure the regex pattern exists, and if it does, writing the instances output to the output file
    	   if(regex != null) {
    		   outputWriter.write(regex + " | "+ instances + "\n");
               outputWriter.flush();
    	   } // End if statement
    	   
           // Reseting the instances variable
           instances = 0;
           
    	   
       } // End while
     
	} // End main

} // End class