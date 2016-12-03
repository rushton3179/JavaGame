//by u5010309, used to shuffle an array list of characters
package game;
import java.util.ArrayList;

class AlterBucket {


	static ArrayList<Character> jumble(ArrayList<Character> bucketlist) {

		ArrayList<Character> clone = new ArrayList<Character>();
		ArrayList<Character> morph = new ArrayList<Character>();
		char a = 'a'; 

		clone = bucketlist;

		for(int i=clone.size(); i>0; i-- )   {

			a = clone.get((int)(clone.size()*Math.random()));
			morph.add(a);
			clone.remove(clone.indexOf(a));
		}	

		bucketlist = morph;

		return(bucketlist);
	}
}

