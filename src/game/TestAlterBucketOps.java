package game;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;


public class TestAlterBucketOps {
	private static String testAlphabet = "aeioubcdfghjklmnpqrstvwxyz";
	private static char[] testAlphaArray = testAlphabet.toCharArray();
	private static ArrayList<Character> bucket = new ArrayList<Character>();

	Random rand = new Random();
	char randlet = testAlphaArray[rand.nextInt(testAlphabet.length())];


	// test to see if jumbled bucket returns a bucket with identical values 
	@Test
	public void testJumble(){

		for(int i = 0; i < 50; i++){
			for (int a = 0; a<11; a++){RLG.bucket.add(randlet);}
			assertTrue(bucket.size()==(AlterBucket.jumble(bucket)).size());
		}


	}

}




