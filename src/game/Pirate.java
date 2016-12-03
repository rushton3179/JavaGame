//by u5010309, creates an array list of images for use in MainGame pirate animation

package game;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;


class Pirate {

	Image p0 = new Image(getClass().getResource("5p.png").toString());
	Image p1 = new Image(getClass().getResource("6p.png").toString());
	Image p2 = new Image(getClass().getResource("7p.png").toString());
	Image p3 = new Image(getClass().getResource("8p.png").toString());
	Image p4 = new Image(getClass().getResource("9p.png").toString());
	Image p5 = new Image(getClass().getResource("10p.png").toString());
	Image p6 = new Image(getClass().getResource("11p.png").toString());
	Image p7 = new Image(getClass().getResource("12p.png").toString());
	Image pWin = new Image(getClass().getResource("1p.png").toString());
	Image pLose = new Image(getClass().getResource("2p.png").toString());

	// creates a list that images can be selected from for animating the pirate
	Pirate(){}
	ArrayList<Image> list = new ArrayList<Image>(Arrays.asList(p0,p1,p2,p3,p4,p5,p6,p7,pWin,pLose));


	Image getImage(int i){
		return list.get(i);	
	}
}


