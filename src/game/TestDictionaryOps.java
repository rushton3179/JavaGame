package game;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;


public class TestDictionaryOps {

	final File dictionary = new File(getClass().getResource("dictionary.txt").getPath());
	DictionaryOps ops = new DictionaryOps();
	/*The test for isLegalWord*/
	@Test
	public void testIsLegalWord(){
		/*count variable used when adding all words in dictionary to an array*/
		int count = 0;
		Random random = new Random();

		/*Scans in the dictionary twice for different uses in the test*/
		Scanner file = null;
		Scanner file2 = null;	
		try {
			file = new Scanner(dictionary);
			file2 = new Scanner(dictionary);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*Counts the number of lines in the text file so the size of the ArrayList can be correct*/
		while(file.hasNextLine()){
			count = count + 1;
			file.nextLine();	
		}
		/*Creates an ArrayList of the dictionary words*/
		ArrayList<String> wordsList = new ArrayList<String>(count);
		while(file2.hasNextLine()){
			String word = file2.nextLine();
			wordsList.add(word);
		}

		/*Tests to see if random words in the ArrayList of words returns true when isLegalWord is used*/
		for(int i = 0; i < 50; i++){
			assertTrue(ops.isLegalWord(wordsList.get(random.nextInt(wordsList.size()-1))));
		}
		/*3 tests to see if words I knew should/shouldn't be in the dictionary return true/false*/
		assertTrue(ops.isLegalWord("zebra"));
		assertTrue(ops.isLegalWord("aardvark"));
		assertFalse(ops.isLegalWord("ahgksao"));

		file.close();
		file2.close();
	}


	/*Test for getsChoice*/
	@Test
	public void testGetsChoice(){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<Character> list1 = new ArrayList<Character>(10);
		ArrayList<Character> list2 = new ArrayList<Character>(10);
		ArrayList<Character> list3 = new ArrayList<Character>(Arrays.asList('a', 'a'));
		ArrayList<Character> list4 = new ArrayList<Character>(10);
		ArrayList<Character> list5 = new ArrayList<Character>(10);

		Random random = new Random();

		/*Here we make 2 lists of random elements (list1, list2) then we make 2 lists that have the same 
		 * elements but in the reverse order(list4 is list1 reversed ect)*/
		for(int i = 0; i < 10; i++){
			list1.add(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		for(int i = 0; i < 10; i++){
			list2.add(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		for(int i = 1; i <= 10; i++){
			list4.add(list1.get(list1.size() - i));
		}
		for(int i = 1; i <= 10; i++){
			list5.add(list2.get(list2.size() - i));
		}

		/*First we see if list 1 and 2 are identical, if they are we test to see if they get the same 
		 *getsChoice value. 
		 *Note that the false case isn't tested as if they are different lists it 
		 *doesn't mean that they won't have the same number of choices*/
		if(list1.containsAll(list2) && list2.containsAll(list1)){
			ArrayList<Character> temp = new ArrayList<Character>(10);
			temp.addAll(list2);
			for(int i = 1; i <=10; i++){
				temp.remove(list1.get(i));
			}	
			if(temp.isEmpty()){
				assertTrue(ops.getsChoice(list1) == ops.getsChoice(list2));
			}
		}

		/*Here we test to see if lists get the same choices as identical lists then we test that list3
		 * which we know has one choice gets that value*/
		assertTrue(ops.getsChoice(list1) == ops.getsChoice(list1));
		assertTrue(ops.getsChoice(list2) == ops.getsChoice(list2));
		assertTrue(ops.getsChoice(list1) == ops.getsChoice(list4));
		assertTrue(ops.getsChoice(list2) == ops.getsChoice(list5));
		assertTrue(ops.getsChoice(list3) == 1);
	}
}
