package game;

/*Written by Ryan Rushton u5192657, references follow from TextBack class
 * This class is our inheritance class, it extends the TextBack class to include a red square to
 * give the player an indication of the speed the game is moving at*/

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


class PrevBack extends TextBack {

	private Rectangle rectangle2;

	/*Adds a rectangle inside TexkBack*/
	PrevBack(int x, int y) {
		super(x, y);
		this.rectangle2 = new Rectangle();
		this.rectangle2.setFill(Color.RED);
		this.rectangle2.setOpacity(0.7);
		this.rectangle2.setX(x+4);
		this.rectangle2.setY(y+4);
		this.rectangle.setFill(Color.YELLOW);
		this.getChildren().add(rectangle2);
		this.getChildren().remove(text);
		this.getChildren().add(text);
	}
	/*Extends clear from TextBack*/
	@Override
	void hideTextBack(){
		super.hideTextBack();
		getRectangle2().setOpacity(0.0);
	}

	/*This is used to reintroduce a TextBack object when it is being used again*/
	@Override
	void setTextBack(Character chr){
		super.setTextBack(chr);
		getRectangle2().setOpacity(0.7);
	}

	boolean hasText(){
		if(text.getText() != ""){
			return true;
		}
		return false;
	}
	/*Makes the red square fade in as time to introduce the letter comes*/
	void fadePrevBack(double secs, Level level){
		if(secs < level.getLevelTime()){
			double fadeTime = Math.exp(4*(secs-level.getLevelTime()));
			getRectangle2().setOpacity(fadeTime);
		}
	}

	/*Extends expandText from TexkBack*/
	@Override
	void expandTextBack(double secs, Level level){
		super.expandTextBack(secs, level);
		double fadeTime = Math.exp((secs-level.getLevelTime()));
		getRectangle2().setX(rectangle.getX() + fadeTime*4);
		getRectangle2().setY(rectangle.getY() + fadeTime*4);
		getRectangle2().setWidth(49*(fadeTime));
		getRectangle2().setHeight(43*( fadeTime));
	}

	/*Getter*/
	Rectangle getRectangle2() {
		return rectangle2;
	}
}
