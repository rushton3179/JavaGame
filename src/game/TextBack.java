/*
 * Written by Ryan Rusthon U5192657
 * Inspiration from the Caterpillar class from the game done in class.
 * Oracle documentation was used. 
 * */

package game;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*This class combines Rectangle and Text classes to form an Object that 
 * is used to display the letter that are within the bucket.*/ 

class TextBack extends Group{

	protected Rectangle rectangle;
	protected Text text;

	/*Creates a yellow rectangle with text centered in it*/
	TextBack(int x, int y){
		this.rectangle = new Rectangle(57,51);
		this.rectangle.setFill(Color.YELLOW);
		this.rectangle.setOpacity(0.8);
		this.rectangle.setX(x);
		this.rectangle.setY(y);
		this.getChildren().add(rectangle);
		this.text = new Text();
		this.text.setX(x);
		this.text.setY(y);
		this.text.setFill(Color.BLACK);
		this.text.setTextAlignment(TextAlignment.CENTER);
		this.text.setTextOrigin(VPos.TOP);
		this.text.setOpacity(1.0);
		this.getChildren().add(text);
	}

	/*This just makes the Objects appear to disappear when the position in the bucket
	 * no longer holds a letter*/
	void hideTextBack(){
		text.setText(null);
		rectangle.setOpacity(0.0);
	}

	/*This is used to reintroduce a TextBack object when it is being used again*/
	void setTextBack(Character chr){
		String string = chr.toString().toUpperCase();
		text.setText(string);
		rectangle.setOpacity(0.8);
	}

	/*This makes the TextBack's expand out into the screen when they are added to the bucket*/
	void expandTextBack(double secs, Level level){
		double fadeTime = Math.exp((secs-level.getLevelTime()));
		rectangle.setWidth(57*( fadeTime));
		rectangle.setHeight(51*( fadeTime));
		text.setWrappingWidth(rectangle.getWidth());
		text.setX(rectangle.getX()+rectangle.getWidth()/2-fadeTime*31);
		text.setY(rectangle.getY()+rectangle.getHeight()/2-fadeTime*30);
		text.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 42*(fadeTime)));
	}

	/*This makes the letters bounce around in the yellow rectangle.
	 *Rather then defining boundaries and having if statements to change direction
	 *it uses the fact that a sin function is bounded by 1 and -1 to keep the
	 *letters straying too far from center.
	 *Also note the exponential decay is used to make them settle back down to center.*/
	void jiggleText(double secs, Level level){
		double x1 = rectangle.getX()+rectangle.getWidth()/2-31;
		double y1 = rectangle.getY()+rectangle.getHeight()/2-30;
		double fadeTime =  Math.exp((-secs));

		text.setX(x1 + 5*fadeTime*Math.sin(30*secs)*Math.random());
		text.setY(y1 + 5*fadeTime*Math.sin(30*secs)*Math.random());
	}
}

