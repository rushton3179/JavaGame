package game;

/*Written by Ryan Rushton u5192657 used the Gender class used in lectures and oracle documentation
 * Our ENUM class, it creates the levels for the game*/

import java.util.ArrayList;
import java.util.Arrays;

enum Level {
	LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5,
	LEVEL6, LEVEL7, LEVEL8, LEVEL9, LEVEL10;

	/*Provides an arrayList of the levels for the game.*/
	static ArrayList<Level> levels = new ArrayList<Level>(
			Arrays.asList(LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5, LEVEL6, LEVEL7, LEVEL8, LEVEL9, LEVEL10));

	/*The the time between adding letters to the bucket*/
	double getLevelTime(){
		switch(this){
		case LEVEL1: 
			return 2.0;
		case LEVEL2:
			return 1.8;
		case LEVEL3:
			return 1.6;
		case LEVEL4:
			return 1.4;
		case LEVEL5:
			return 1.2;
		case LEVEL6:
			return 1.0;
		case LEVEL7:
			return 0.8;
		case LEVEL8:
			return 0.7;
		case LEVEL9:
			return 0.6;
		case LEVEL10:
			return 0.5;
		}
		return 2.0;	
	}

	/*Provides a string of the level to be displayed*/
	@Override
	public String toString(){
		switch(this){
		case LEVEL1:
			return "Level1";
		case LEVEL2:
			return "Level2";
		case LEVEL3:
			return "Level3";
		case LEVEL4:
			return "Level4";
		case LEVEL5:
			return "Level5";
		case LEVEL6:
			return "Level6";
		case LEVEL7:
			return "Level7";
		case LEVEL8:
			return "Level8";
		case LEVEL9:
			return "Level9";
		case LEVEL10:
			return "Level10";
		}
		return null;
	}
}