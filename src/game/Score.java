package game;
/* Ryan Rushton u5192657, no references*/

import java.util.ArrayList;

class Score {

	/**
	 * @param args
	 */
	static Integer getScore(String word, Integer currentscore) {

		/*The following section sets us ArrayLists of Characters the correspond to a point value,
		 * the Scrabble letter point system was used. */	
		String onestring = "eaiounrtlsu";
		ArrayList<Character> onepoint = new ArrayList<Character>();
		for(int i = 0; i < onestring.length(); i ++){
			onepoint.add(onestring.charAt(i));
		}

		String twostring = "dg";
		ArrayList<Character> twopoint = new ArrayList<Character>();
		for(int i = 0; i < twostring.length(); i ++){
			twopoint.add(twostring.charAt(i));
		}

		String threestring = "bcmp";
		ArrayList<Character> threepoint = new ArrayList<Character>();
		for(int i = 0; i < threestring.length(); i ++){
			threepoint.add(threestring.charAt(i));
		}

		String fourstring = "fhvwy";
		ArrayList<Character> fourpoint = new ArrayList<Character>();
		for(int i = 0; i < fourstring.length(); i ++){
			fourpoint.add(fourstring.charAt(i));
		}

		String fivestring = "k";
		ArrayList<Character> fivepoint = new ArrayList<Character>();
		for(int i = 0; i < fivestring.length(); i ++){
			fivepoint.add(fivestring.charAt(i));
		}

		String eightstring = "jx";
		ArrayList<Character> eightpoint = new ArrayList<Character>();
		for(int i = 0; i < eightstring.length(); i ++){
			eightpoint.add(eightstring.charAt(i));
		}

		String tenstring = "qz";
		ArrayList<Character> tenpoint = new ArrayList<Character>();
		for(int i = 0; i < tenstring.length(); i ++){
			tenpoint.add(tenstring.charAt(i));
		}

		/*The following for loop checks each letter in the input word and compares it to a "point ArrayList" 
		 * then adds the appropriate amount to the score */	
		for(int i =0; i< word.length(); i++){
			if(onepoint.contains(word.charAt(i))){
				currentscore = currentscore + 1;
			}
			else if(twopoint.contains(word.charAt(i))){
				currentscore = currentscore + 2;
			}
			else if(threepoint.contains(word.charAt(i))){
				currentscore = currentscore + 4;
			}
			else if(fourpoint.contains(word.charAt(i))){
				currentscore = currentscore + 4;
			}
			else if(fivepoint.contains(word.charAt(i))){
				currentscore = currentscore + 5;
			}
			else if(eightpoint.contains(word.charAt(i))){
				currentscore = currentscore + 8;
			}
			else if(tenpoint.contains(word.charAt(i))){
				currentscore = currentscore + 10;
			}
		}

		/* The following set of if else statements just checks word length 
		 * and adds the given value for certain lengths */	
		if(word.length() < 4){
		}
		else if(word.length() == 4){
			currentscore = currentscore + 2;
		}
		else if(word.length() == 5){
			currentscore = currentscore + 4;
		}
		else if(word.length() == 6){
			currentscore = currentscore + 7;
		}
		else if(word.length() > 6){
			currentscore = currentscore + 10;
		}
		Double scoreAsDouble = Math.rint((double) (currentscore*(1+MainGame.level/10)));
		Integer scoreAsInteger = scoreAsDouble.intValue();
		return scoreAsInteger;
	}
}

