package game;
/* u5194042
 * test function for Longest
 * inspired by code for TestDictionaryOps by Ryan Rushton
 */
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;


public class TestLongest {

	final File dictionary = new File(getClass().getResource("dictionary.txt").getPath());
	DictionaryOps ops = new DictionaryOps();

	@Test
	public void getslongesttest() throws FileNotFoundException{
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<Character> testlistA = new ArrayList<Character>();
		ArrayList<Character> testlistB = new ArrayList<Character>();
		ArrayList<Character> testlistControl = new ArrayList<Character>(Arrays.asList('a', 'c', 'e' , 'm', 'f', 'l', 'g', 'r'));


		/*allows for a list of random letters to be created*/
		Random addrandomletter = new Random();

		for(int i = 0; i < 10; i++){
			testlistA.add(alphabet.charAt(addrandomletter.nextInt(alphabet.length())));
		}
		for(int i = 0; i < 10; i++){
			testlistB.add(alphabet.charAt(addrandomletter.nextInt(alphabet.length())));
		}



		if(testlistA.containsAll(testlistB) && testlistB.containsAll(testlistA)){
			for(int i = 1; i <=10; i++){
				testlistB.remove(testlistA.get(i));
			}	
			if(testlistB.isEmpty()){
				assertTrue(ops.getsLongest(testlistA) == ops.getsLongest(testlistB));
			}
		}


		assertTrue(ops.getsLongest(testlistA) == ops.getsLongest(testlistA));
		assertTrue(ops.getsLongest(testlistB) == ops.getsLongest(testlistB));
		assertTrue(ops.getsLongest(testlistControl) == 6);
	}
}