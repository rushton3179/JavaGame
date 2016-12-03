package game;
/* Heavily modified version of our original RLG implemented in the main text game
 * Originally developed by u5010309  Robert Blaszkowski with input from Ryan Rushton u5192657 and u5194042 Aiden Brown
 * Modified to include the implementation of preview by u5192657 Ryan Rushton */

import java.util.ArrayList;
import java.util.Random;

class RLG {

	static int choice;
	static int longest;
	static String previewString;
	private static String alphabet = "aeioubcdfghjklmnpqrstvwxyzeeeeeeeeeeeaaaaaaaaiiiiiiiiooooooonnnnnrrrrrtttttlllsssuuudddggbcmpfhvwy";; //0-4 are vowels 5-25 are constants
	private static char[] alphaArray= alphabet.toCharArray();	
	static ArrayList<Character> preview= new ArrayList<Character>();
	static ArrayList<Character> bucket= new ArrayList<Character>();


	static void AddingTask(){
		Random rand = new Random();
		char randlet = alphaArray[rand.nextInt(alphabet.length())];
		char randvowel = alphaArray[rand.nextInt(5)];

		/* if the game is in a state that it is not over then it adds letters to the preview
		 * if the preview has 2 letters in it, this also removes the first letter of the preview
		 * and adds it to the bucket */
		if (bucket.size()!=33 && MainGame.count!=48){      
			if(preview.size() == 2){
				bucket.add(preview.get(0));
				preview.remove(0);
				MainGame.count++;
			}

			/* if the bucket is empty or it contains a vowel it adds a letter from our scrabble list of letters */		
			if(bucket.isEmpty()|| 
					bucket.contains(alphaArray[0])||
					bucket.contains(alphaArray[1])||
					bucket.contains(alphaArray[2])||
					bucket.contains(alphaArray[3])||
					bucket.contains(alphaArray[4])||
					preview.contains(alphaArray[0])||
					preview.contains(alphaArray[1])||
					preview.contains(alphaArray[2])||
					preview.contains(alphaArray[3])||
					preview.contains(alphaArray[4])){
				preview.add(randlet);
			}

			/*This forces it to have a vowel if the preview or bucket doesn't already contain one.*/
			else{
				preview.add(randvowel);
			}

			/* if the two letters of the preview are the same then this creates an array containing all letters
			 * of our scabble alphabet except for the doubled letter, then deletes the 2nd letter and adds a 
			 * new one from the array*/
			if(preview.size()==2 && preview.get(0) == preview.get(1)){
				ArrayList<Character> reducedList = new ArrayList<Character>();
				char[] reduced = alphabet.toCharArray();
				for(int i = 0; i < reduced.length; i++){
					reducedList.add(reduced[i]);
				}
				while(reducedList.contains(preview.get(1))){
					reducedList.remove(preview.get(1));
				}
				preview.remove(1);
				preview.add(reducedList.get(rand.nextInt(reducedList.size())));
			}

			/* These are just values used for display in both game and here */

			DictionaryOps operation = new DictionaryOps();
			choice = operation.getsChoice(bucket);
			longest  = operation.getsLongest(bucket);
			previewString = preview.toString();

			/* prints out parts of the game for debugging */
			if (bucket.size()==33){
				System.out.println("You have lost.");
			}
			else if (MainGame.count>47){
				System.out.println("You have won!");
			}
			System.out.println(previewString);
			System.out.println(bucket);
			System.out.print("Size of the bucket is "+bucket.size());
			System.out.print(", choice is "+choice);
			System.out.print(", longest is "+longest);
			System.out.println(", you are "+ (48 - MainGame.count) +" letters away from winning!"); 
		}
	}
}


