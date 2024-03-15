/**
 * This class accepts a String and and int variable as its arguments. The String variable will represent the auth.log file and the int variable will
 * represent a given print flag. This class iterates through the auth.log file and uses regular expressions to count the number of IP addresses and
 * usernames present.
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.*;
import java.io.*;

// Class declaration
public class LogFileProcessor {

		/**
		 * The constructor of the class. Accepts a String and int argument and throws an IOException since we are reading a document.
		 * @param fileName
		 * @param printFlag
		 * @throws IOException
		 */
		public LogFileProcessor(String fileName, int printFlag) throws IOException {
			
			// Calling a method that will process the inputed file
			process(fileName);
			
			// Calling different print methods depending on the inputed print flag
			if(printFlag == 0) {
				printDefault();
			} // End if
			
			else if (printFlag == 1) {
				printIPAddresses();
			} // End elif
			
			else if (printFlag == 2) {
				printUsernames();
			} // End elif
			else {
				printDefault();
			} // End else
			
		} // End constructor
		
		// Declaring variables for our HashMaps, regex patterns, and int and String variables
		HashMap<String, Integer> ipAddresses = new HashMap<>();
		HashMap<String, Integer> usernames = new HashMap<>();
		// From some research I did, I found that valif IPV4 IP addressed must contain 4 cells and those 4 cells can contain values that
		// range from 0-256. I designed the following regex pattern with that in mind.
		String ipRegex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]))";
		// Regex that checks for patterns that contain the word user followed by a word that is 3 to 15 characters long. This word that follows "user" is used as the username
		String usernameRegex = "user\s[a-z]{3,15}";
		int totalLines = 0;
		int totalIP = 0;
		int totalUsernames = 0;
		int ipCount = 0;
		int usernameCount = 0;
		String fileNameLine;
		
		/**
		 * Method that processes the inputed file from the constructor
		 * @param fileName
		 * @throws IOException
		 */
		void process (String fileName) throws IOException{
			
			// Reader to read the file
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			// While loop that keeps going until the entire file has been read
			while((fileNameLine = reader.readLine()) != null) {
				
				// Defining two patterns and two matchers that will be used to define and check matches of our regex patterns
				Pattern ipPattern = Pattern.compile(ipRegex);
				Matcher ipMatcher = ipPattern.matcher(fileNameLine);
				Pattern usernamePattern = Pattern.compile(usernameRegex);
				Matcher usernameMatcher = usernamePattern.matcher(fileNameLine);
				
				// Boolean variables that will return true if a match is found for a given pattern
				boolean ipMatchFound = ipMatcher.find();
				boolean usernameMatchFound = usernameMatcher.find();
				
				// If statement that checks if there was a match for the IP address regex pattern
				if(ipMatchFound) {
					// A variable for the value that was matched
					String ipAddress = ipMatcher.group();
					// Updating the count of the matched key if the key is already in the Hashmap
					if(ipAddresses.containsKey(ipAddress)) {
						ipAddresses.put(ipAddress, ipAddresses.get(ipAddress) + 1);
					} // End if
					// Adding the key to the Hashmap and setting it's value to 1 if the key is not already in the Hashmap
					if(!ipAddresses.containsKey(ipAddress)) {
						ipAddresses.put(ipAddress, 1);
						totalIP++;
					}// End if

				} // End if
				
				// If statement that checks if there was a match for the IP address regex pattern
				if(usernameMatchFound) {
					// A variable for the value that was matched
					String username = usernameMatcher.group();
					// Updating the count of the matched key if the key is already in the HashMap
					if(usernames.containsKey(username)) {
						usernames.put(username, usernames.get(username) + 1);
					} // End if
					// Adding the key to the Hashmap and setting it's value to 1 if the key is not already in the HashMap
					if(!usernames.containsKey(username)) {
						usernames.put(username, 1);
						totalUsernames++;
					} // End if
				} // End if
				
				// Incrementing the value of the total number of lines read from the file
				totalLines++;
			} // End while 
						
		} // End process method
		
		/**
		 * Method that simply returns the size of the ipAddresses HashMap
		 * @return
		 */
		public int getipAddressesSize() {
			return ipAddresses.size();
		} // End getipAddressesSize method
		
		/**
		 * // Method that simply returns the size of the usernames HashMap
		 * @return
		 */
		public int getusernamesSize() {
			return usernames.size();
		} // End usernames method
		
		/**
		 * Default print method that is called if the print flag is set to 0
		 */
		void printDefault() {
			System.out.println(totalLines + " lines in the log file were parsed." + "\n" +"There are "+totalIP + " unique IP addresses in the log."
								+ "\n" + "There are " + totalUsernames + " unique usernames in the log.");
		} // End method
		/**
		 * IP Address print method that is called if the print flag is set to 1
		 */
		void printIPAddresses() {
			for(Entry<String, Integer> entry : ipAddresses.entrySet()) {
				System.out.println(entry.getKey() + " | " + entry.getValue());
			} // End for loop
			System.out.println(totalLines + " lines in the log file were parsed." + "\n" +"There are "+totalIP + " unique IP addresses in the log."
					+ "\n" + "There are " + totalUsernames + " unique usernames in the log.");
		} // End method
		
		/**
		 * Usernames print method that is called if the print flag is set to 2
		 */
		void printUsernames() {
			for(Entry<String, Integer> entry : usernames.entrySet()) {
				System.out.println(entry.getKey() + " | " + entry.getValue());
			} // End for loop
			System.out.println(totalLines + " lines in the log file were parsed." + "\n" +"There are "+totalIP + " unique IP addresses in the log."
					+ "\n" + "There are " + totalUsernames + " unique usernames in the log.");
		} // End method
		
} // End class

