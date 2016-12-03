// by u5010309, used to MainGame to animate cannons in respect to the bucket
package game;
import javafx.scene.image.ImageView;

class Cannons {

	static void transitionCannons(ImageView Cannon8, ImageView Cannon16, ImageView Cannon24, ImageView Cannon32, ImageView CannonG){

		//takes cannons off screen 
		if (RLG.bucket.size()==0){
			Cannon8.setX(768);
			Cannon16.setX(768);
			Cannon24.setX(768);
			Cannon32.setX(768);
			CannonG.setX(768);
		}
		//first row
		else{
			if(RLG.bucket.size()>0&&RLG.bucket.size()<9){											
				for(int i = 1; i<9; i++){
					if(RLG.bucket.size()==i) Cannon8.setX(768-(17*i));
				}
				Cannon16.setX(768);
				Cannon24.setX(768);
				Cannon32.setX(768);

				if (RLG.bucket.size()==8){
					CannonG.setX(734);}
				else{CannonG.setX(768);}

			}
			//second row
			if(RLG.bucket.size()>8&&RLG.bucket.size()<17){
				for(int i = 1; i<9; i++){
					if ((RLG.bucket.size())%8==0)
					{Cannon16.setX(768-(17*8));

					}
					else if ((RLG.bucket.size()%8)==i){Cannon16.setX(768-(17*(i%8)));}
				}
				Cannon24.setX(768);
				Cannon32.setX(768);

				if ((RLG.bucket.size()%8)==0){
					CannonG.setX(678);}
				else{CannonG.setX(723);}

			}

			//third row
			if(RLG.bucket.size()>16&&RLG.bucket.size()<25){
				for(int i = 1; i<9; i++){
					if ((RLG.bucket.size())%8==0)
					{Cannon24.setX(768-(17*8));
					}
					else if ((RLG.bucket.size()%8)==i){Cannon24.setX(768-(17*(i%8)));}
				}
				Cannon32.setX(768);

				if ((RLG.bucket.size()%8)==0){
					CannonG.setX(633);}
				else{CannonG.setX(678);}

			}

			//fourth row
			if(RLG.bucket.size()>24&&RLG.bucket.size()<33){
				for(int i = 1; i<9; i++){
					if ((RLG.bucket.size())%8==0)
					{Cannon32.setX(768-(17*8));
					}
					else if ((RLG.bucket.size()%8)==i){Cannon32.setX(768-(17*(i%8)));}
					if ((RLG.bucket.size()%8)==0){
						CannonG.setX(588);}
					else{CannonG.setX(633);


					}
				}
			}
		}
	}
}
