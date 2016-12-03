package game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/* This is 3 previous classes compiled and modified by Ryan Rushton(u5192657) to use InputStreams 
 * and BufferedReader so that the jar file would work.
 * getsLongest was originally written by u5194042 Aiden Brown
 * isLegalWord and getsChoice originally written by Ryan Rushton(u5192657)
 * tests for isLegalWord and getsChoice written by Ryan Rushton(u5192657)*/

class DictionaryOps {

	int choiceNum;
	int longestNum;

	/*These methods were originally static but modifying to get a runnable jar this created problems
	 * thus the empty constructor was a solution to this*/
	
	DictionaryOps(){}

	/*
	 * isLegalWord checks a given word against the dictionary to see if it is aloud
	 * */

	boolean isLegalWord(String new_word) {
		/*Scans in the dictionary file*/
		InputStream dict = getClass().getResourceAsStream("dictionary.txt");
		BufferedReader dictionary = new BufferedReader(new InputStreamReader(dict));

		String line;
		/*While the file has a next line it sets dictword as the word in the dictionaries next line 
		 *then if the input word is equal to this it return true, if it finds no mathing word it returns false */
		try {
			while((line = dictionary.readLine()) != null){
				if(line.equals(new_word)){
					dictionary.close();
					return true;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dictionary.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*getsChoice calculates the number of possible words that can be made from the bucket*/

	int getsChoice (ArrayList<Character> bucketlist){
		choiceNum = 0;

		/*Scans in the dictionary file*/
		InputStream dict = getClass().getResourceAsStream("dictionary.txt");
		BufferedReader dictionary = new BufferedReader(new InputStreamReader(dict));
		String line;
		/*While the file has a new line it adds each character of the word in the next line to an ArrayList*/
		try {
			while((line = dictionary.readLine()) != null){
				ArrayList<Character> dictionaryList = new ArrayList<Character>();

				for(int i = 0; i < line.length(); i++){
					dictionaryList.add(line.charAt(i));
				}

				/*If the ArrayList above is contained within the bucket we remove each element of the bucket from
				 * the ArrayList (note if the ArrayList does not contain a char it does nothing) and if the 
				 * ArrayList is empty after this we know that each letter of the bucket was used a single time so
				 * we can increment the number of choices by 1
				 * After this has been done for each word in the file we return choiceNum*/
				if(bucketlist.containsAll(dictionaryList)){
					for(int i = 0; i < bucketlist.size(); i++){							
						dictionaryList.remove(bucketlist.get(i));
					}
					if(dictionaryList.isEmpty()){
						choiceNum++;					
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dictionary.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return choiceNum;
	}

	/*
	 * getsLongest calculates the longest possible word that can be made from the bucket. Originally written by Aiden
	 * but merged into this class by Ryan
	 */

	/* u5194042:  This class is designed to present the longest possible word from the array of the bucket. 
	 * --THIS IS THE BEST IDEA I THINK the other way that it can be done is to list the words in the
	 * --text file in descending order, and then go through all possible words in the bucket array.
	 * --that way the first word that it finds as a match is the longest possible word.  
	 * The final implementation was not like I had planned in terms of inverting the text file.  Instead it is similar to the choice method. 
	 * The basic functionality is that the method starts at a length of 0.  When it finds a match that is greater than 0 it replaces the longest
	 * possible word with that one.  It goes through the text file until there is no more lines. */

	int getsLongest (ArrayList<Character> bucketlist) {
		longestNum = 0;

		InputStream dict = getClass().getResourceAsStream("dictionary.txt");
		BufferedReader dictionary = new BufferedReader(new InputStreamReader(dict));
		String line;
		try {
			while((line = dictionary.readLine()) != null){

				ArrayList<Character> wordlist = new ArrayList<Character>();

				for(int i = 0; i < line.length(); i++){
					wordlist.add(line.charAt(i));
				}

				if(bucketlist.containsAll(wordlist)){
					for(int i = 0; i < bucketlist.size(); i++){							
						wordlist.remove(bucketlist.get(i));
					}
					if(wordlist.size() == 0 && line.length()> longestNum){
						longestNum = line.length();					
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dictionary.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return longestNum;
	}

}



