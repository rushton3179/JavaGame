/*Different sections written by Robert, Ryan and Aiden*/

package game;


import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class MainGame extends Stage {

	private String word = "";
	private String wordG = "";
	static int count = 0;
	private AnimationTimer timerA; //used to display list as it updates 
	private Text fps = new Text(0, 30, "");
	private long lastFrame = 0;
	private long frameCount = 0;
	private double totalSecs = 0;
	private double pirateSecs = 0;
	private int pirateCounter = 0;
	static Integer level = 0;
	private Level crntLevel;
	private double jiggleTime = 0;
	private static Integer score = 0;
	private static Integer highest = 0;//to be recorded for high scores 
	private static Integer bangCount = -1;//used in cannon fire

	private ArrayList<PrevBack> previewArray;
	private ArrayList<TextBack> lettersArray;

	/*The MainGame stage was modified to be put into a constructor so that it
	 * is easily called by StartMenu. We know this is unconventional but it was the 
	 * simplest way we could come up with swapping between menu and game play screens*/

	/*------------------------------MainGame Constructor-------------------------------*/

	MainGame(final Stage primaryStage) throws Exception {
		final Stage stage = new Stage();
		stage.setTitle("Word Cannon");
		final Group root = new Group();
		Scene scene = new Scene(root, 768, 1024); //correct window size
		stage.setScene(scene);

		/*Sets the background Image*/
		String backgroundURL = this.getClass().getResource("Background.png").toString();
		ImageView background = new ImageView(backgroundURL);
		background.setFitHeight(1024);
		background.setFitWidth(768);
		root.getChildren().add(background);

		/*Taken from the game made in class*/
		String pirateSongURI = this.getClass().getResource("PirateSong.mp3").toURI().toString();
		final MediaPlayer pirateSong = new MediaPlayer(new Media(pirateSongURI));
		pirateSong.play();
		pirateSong.setCycleCount(Integer.MAX_VALUE);

		//used to display the pirate animation
		final ImageView pirateA = new ImageView();
		pirateA.setX(14);
		pirateA.setY(869);
		root.getChildren().add(pirateA);

		//images for use in the animation of cannons
		Image cannon = new Image(getClass().getResource("newCannon.png").toString());
		Image cannonGameOver = new Image(getClass().getResource("cannonGameOver.png").toString());
		Image layerCover = new Image(getClass().getResource("layerCoverUp.png").toString());
		Image Bang = new Image(getClass().getResource("bang.png").toString());
		
		final ImageView Cannon8 = new ImageView();
		Cannon8.setX(768);
		Cannon8.setY(350);
		Cannon8.setImage(cannon);
		root.getChildren().add(Cannon8);

		final ImageView Cannon16 = new ImageView();
		Cannon16.setX(768);
		Cannon16.setY(416);
		Cannon16.setImage(cannon);
		root.getChildren().add(Cannon16);

		final ImageView Cannon24 = new ImageView();
		Cannon24.setX(768);
		Cannon24.setY(482);
		Cannon24.setImage(cannon);
		root.getChildren().add(Cannon24);

		final ImageView Cannon32 = new ImageView();
		Cannon32.setX(768);
		Cannon32.setY(558);
		Cannon32.setImage(cannon);
		root.getChildren().add(Cannon32);

		final ImageView CannonG = new ImageView();
		CannonG.setX(768);
		CannonG.setY(735);
		CannonG.setImage(cannonGameOver);
		root.getChildren().add(CannonG);

		final ImageView cannonBang = new ImageView();
		cannonBang.setX(500);
		cannonBang.setY(320);
		cannonBang.setImage(Bang);
		cannonBang.setOpacity(0.0);
		root.getChildren().add(cannonBang);

		final ImageView cannonBang2 = new ImageView();
		cannonBang2.setX(500);
		cannonBang2.setY(380);
		cannonBang2.setImage(Bang);
		cannonBang2.setOpacity(0.0);
		root.getChildren().add(cannonBang2);

		final ImageView cannonBang3 = new ImageView();
		cannonBang3.setX(500);
		cannonBang3.setY(450);
		cannonBang3.setImage(Bang);
		cannonBang3.setOpacity(0.0);
		root.getChildren().add(cannonBang3);

		final ImageView cannonBang4 = new ImageView();
		cannonBang4.setX(500);
		cannonBang4.setY(525);
		cannonBang4.setImage(Bang);
		cannonBang4.setOpacity(0.0);
		root.getChildren().add(cannonBang4);

		final ImageView cannonBangLast = new ImageView();
		cannonBangLast.setX(530);
		cannonBangLast.setY(700);
		cannonBangLast.setImage(Bang);
		cannonBangLast.setOpacity(0.0);
		root.getChildren().add(cannonBangLast);

		final ImageView CoverUp = new ImageView();
		CoverUp.setX(0);
		CoverUp.setY(0);
		CoverUp.setImage(layerCover);
		root.getChildren().add(CoverUp);


		/*These are the TextBack objects that display the letters for the game
		 * each one just gives the position and adds it to the root.
		 * The are all put into either a preview or bucket ArrayList for ease of 
		 * handling later*/

		previewArray = new ArrayList<PrevBack>();
		lettersArray = new ArrayList<TextBack>();

		final PrevBack p0 = new PrevBack(418, 80);
		root.getChildren().add(p0);
		previewArray.add(p0);

		final PrevBack p1 = new PrevBack(490, 80);
		root.getChildren().add(p1);
		previewArray.add(p1);

		/*Row 1*/
		final TextBack b0 = new TextBack(38, 346);
		root.getChildren().add(b0);
		lettersArray.add(b0);

		final TextBack b1 = new TextBack(110, 346);
		root.getChildren().add(b1);
		lettersArray.add(b1);

		final TextBack b2 = new TextBack(182, 346);
		root.getChildren().add(b2);
		lettersArray.add(b2);

		final TextBack b3 = new TextBack(254, 346);
		root.getChildren().add(b3);
		lettersArray.add(b3);

		final TextBack b4 = new TextBack(326, 346);
		root.getChildren().add(b4);
		lettersArray.add(b4);

		final TextBack b5 = new TextBack(398, 346);
		root.getChildren().add(b5);
		lettersArray.add(b5);

		final TextBack b6 = new TextBack(470, 346);
		root.getChildren().add(b6);
		lettersArray.add(b6);

		final TextBack b7 = new TextBack(541, 346);
		root.getChildren().add(b7);
		lettersArray.add(b7);

		/*Row 2*/
		final TextBack b8 = new TextBack(38, 412);
		root.getChildren().add(b8);
		lettersArray.add(b8);

		final TextBack b9 = new TextBack(110, 412);
		root.getChildren().add(b9);
		lettersArray.add(b9);

		final TextBack b10 = new TextBack(182, 412);
		root.getChildren().add(b10);
		lettersArray.add(b10);

		final TextBack b11 = new TextBack(254, 412);
		root.getChildren().add(b11);
		lettersArray.add(b11);

		final TextBack b12 = new TextBack(326, 412);
		root.getChildren().add(b12);
		lettersArray.add(b12);

		final TextBack b13 = new TextBack(398, 412);
		root.getChildren().add(b13);
		lettersArray.add(b13);

		final TextBack b14 = new TextBack(470, 412);
		root.getChildren().add(b14);
		lettersArray.add(b14);

		final TextBack b15 = new TextBack(541, 412);
		root.getChildren().add(b15);
		lettersArray.add(b15);

		/*Row 3*/
		final TextBack b16 = new TextBack(38, 478);
		root.getChildren().add(b16);
		lettersArray.add(b16);


		final TextBack b17 = new TextBack(110, 478);
		root.getChildren().add(b17);
		lettersArray.add(b17);

		final TextBack b18 = new TextBack(182, 478);
		root.getChildren().add(b18);
		lettersArray.add(b18);

		final TextBack b19 = new TextBack(254, 478);
		root.getChildren().add(b19);
		lettersArray.add(b19);

		final TextBack b20 = new TextBack(326, 478);
		root.getChildren().add(b20);
		lettersArray.add(b20);

		final TextBack b21 = new TextBack(398, 478);
		root.getChildren().add(b21);
		lettersArray.add(b21);

		final TextBack b22 = new TextBack(470, 478);
		root.getChildren().add(b22);
		lettersArray.add(b22);

		final TextBack b23 = new TextBack(541, 478);
		root.getChildren().add(b23);
		lettersArray.add(b23);

		/*Row 4*/
		final TextBack b24 = new TextBack(38, 544);
		root.getChildren().add(b24);
		lettersArray.add(b24);

		final TextBack b25 = new TextBack(110, 544);
		root.getChildren().add(b25);
		lettersArray.add(b25);

		final TextBack b26 = new TextBack(182, 544);
		root.getChildren().add(b26);
		lettersArray.add(b26);

		final TextBack b27 = new TextBack(254, 544);
		root.getChildren().add(b27);
		lettersArray.add(b27);

		final TextBack b28 = new TextBack(326, 544);
		root.getChildren().add(b28);
		lettersArray.add(b28);

		final TextBack b29 = new TextBack(398, 544);
		root.getChildren().add(b29);
		lettersArray.add(b29);

		final TextBack b30 = new TextBack(470, 544);
		root.getChildren().add(b30);
		lettersArray.add(b30);

		final TextBack b31 = new TextBack(541, 544);
		root.getChildren().add(b31);
		lettersArray.add(b31);
		/*End of letter display section*/

		/*Displays longest and choice */
		final Text stats = new Text(200,944, ("Pick one of 0 words, the longest is 0 ya scurvy dog"));
		stats.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
		stats.setFill(Color.BLUE);
		stats.setWrappingWidth(390);	
		stats.setOpacity(1.0);
		root.getChildren().add(stats);

		/*Displays letters to next level*/
		final Text LettersLeft = new Text(218,290, "");
		stats.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 22));
		stats.setFill(Color.BLACK);	
		LettersLeft.setWrappingWidth(200);
		LettersLeft.setTextAlignment(TextAlignment.CENTER);
		stats.setOpacity(1.0);
		root.getChildren().add(LettersLeft);

		final Text entry = new Text(250,656,""); //displays players entry
		entry.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 24));
		entry.setFill(Color.BLACK);
		entry.setOpacity(1.0);
		entry.setWrappingWidth(300);
		root.getChildren().add(entry);

		final Text scoreDisplay = new Text(628,115,"0"); //displays score in real time
		scoreDisplay.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 36));
		scoreDisplay.setFill(Color.BLACK);
		scoreDisplay.setWrappingWidth(100);
		scoreDisplay.setTextAlignment(TextAlignment.CENTER);
		scoreDisplay.setOpacity(1.0);
		root.getChildren().add(scoreDisplay);

		final Text highScoreDisplay = new Text(640,235,""+highest); //displays highest score in game session
		highScoreDisplay.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
		highScoreDisplay.setFill(Color.BLACK);
		highScoreDisplay.setWrappingWidth(100);
		highScoreDisplay.setTextAlignment(TextAlignment.CENTER);
		highScoreDisplay.setOpacity(1.0);
		root.getChildren().add(highScoreDisplay );


		/*Displays the level*/
		final Text levelDisplay = new Text(68, 73, "1");
		levelDisplay.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 30));
		levelDisplay.setFill(Color.BLACK);
		levelDisplay.setWrappingWidth(80);
		levelDisplay.setTextAlignment(TextAlignment.CENTER);
		levelDisplay.setOpacity(1.0);
		root.getChildren().add(levelDisplay);

		/* display the frame rate, toggled on and off with TAB */
		fps.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		fps.setOpacity(0.4);
		fps.setX(255);
		fps.setY(90);
		fps.setVisible(false);
		root.getChildren().add(fps);

		/*Displays the gap between each letter, toggled on and off with TAB*/

		final Text gap = new Text(0, 30, "0.00");
		gap.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		gap.setOpacity(0.4);
		gap.setX(255);
		gap.setY(55);
		gap.setVisible(false);
		root.getChildren().add(gap);

		stage.show();

		/*--------------------------Screen Placement Finished-------------------------------*/

		timerA = new AnimationTimer() {
			@Override
			public void handle(long now) {
				frameCount++;
				long nanos = now - lastFrame; 
				double secs = nanos / 1000000000.0;
				totalSecs = totalSecs + secs;
				jiggleTime = jiggleTime + secs;
				pirateSecs = pirateSecs + secs;
				crntLevel = Level.levels.get(level);

				/*Losing condition */
				if(RLG.bucket.size() >=32 && totalSecs >= crntLevel.getLevelTime()){
					timerA.stop();
					stats.setText("Garrrr, ya lose!");
					Pirate pirateArray = new Pirate();
					pirateA.setImage(pirateArray.getImage(pirateArray.list.size()-1));
					cannonBangLast.setOpacity(1.0);
					return;
				}

				/*Winning and level up condition*/
				else if (count>47){

					RLG.preview.clear();
					RLG.bucket.clear();
					if(level < 9){
						level++;
					}
					else{
						timerA.stop();
						stats.setText("Garrrr, ya WIN!");
						Pirate pirateArray = new Pirate();
						pirateA.setImage(pirateArray.getImage(pirateArray.list.size()-2));
						return;
					}
					count = 0;
				}

				/*Determines the time between letters being added, Ryan Rushton(u5192657)*/
				else if(totalSecs >= crntLevel.getLevelTime()){
					DecimalFormat f = new DecimalFormat("##.00");
					if(totalSecs < 3){
						gap.setText(f.format(totalSecs).toString());
					}
					RLG.AddingTask();
					totalSecs = 0;
				}
				updateFPS(nanos);
				scoreDisplay.setText(score.toString());
				levelDisplay.setText(crntLevel.toString());


				if((48-count) <11){LettersLeft.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 24));
				LettersLeft.setText(""+(48-count));
				LettersLeft.setX(218);
				LettersLeft.setY(302);}

				else{
					LettersLeft.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 14));
					LettersLeft.setText(""+(48-count+"\n LETTERS TO GO"));
					LettersLeft.setX(218);
					LettersLeft.setY(290);}

				stats.setText("Pick one of "+RLG.choice+" words, the longest is "+RLG.longest+
						" ya scurvy dog!");

				/*This is defined below, it modifies the letters on screen*/
				modifyLetters();


				// u5010309 cycles through pirate images to make an animated talking pirate 
				while(pirateSecs>0.3){
					Pirate pirateArray = new Pirate();
					pirateA.setImage(pirateArray.getImage(pirateCounter%8));
					pirateSecs=0;
					pirateCounter++;

				}
				//u5010309 simple cannon transitions
				Cannons.transitionCannons(Cannon8,Cannon16,Cannon24,Cannon32,CannonG);

				if (bangCount != RLG.bucket.size()){
					cannonBang.setOpacity(0.0);
					cannonBang2.setOpacity(0.0);
					cannonBang3.setOpacity(0.0);
					cannonBang4.setOpacity(0.0);
					bangCount = -1;}
				else{
					cannonBang.setX(Cannon8.getX()-61);
					cannonBang2.setX(Cannon16.getX()-61);
					cannonBang3.setX(Cannon24.getX()-61);
					cannonBang4.setX(Cannon32.getX()-61);
				}

				if (score>highest){
					highest = (Integer.valueOf(score));
					highScoreDisplay.setText(""+highest);
				}
				lastFrame = now;
			}
		};

		timerA.start();

		//KeyEvent Handler acts as a scanner
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {	
				/*Aiden Brown u5194042
				 * Sets the escape button to return to main menu, and quits the current game, allowing the user to restart

				 NOTE: Using this feature on lab machines may close the program and return "Failed to write core dump. 
				 'Core dumps have been disabled, try "ulimit -c unlimited" before starting Java again" */

				if (keyEvent.getCode()==KeyCode.ESCAPE){
					timerA.stop();
					pirateSong.stop();
					stage.close();
					clearGame();
					root.getChildren().removeAll();
					try {
						new StartMenu(primaryStage); // Returns user to StartMenu
					}	
					catch (Exception e) {
						e.printStackTrace();
					}
				}

				//toggles the display of FPS and the Time between adding letters
				if (keyEvent.getCode() == KeyCode.TAB) {
					fps.setVisible(!fps.isVisible());
					gap.setVisible(!gap.isVisible());
				}

				//u5010309 this shakes/shuffles the bucket
				if (keyEvent.getCode()==KeyCode.SPACE){
					RLG.bucket=AlterBucket.jumble(RLG.bucket);}

				//u5010309 allows the user to 'backspace' the last letter in the typed word
				if (RLG.bucket.size()<32&&word.length()>0&&keyEvent.getCode()==KeyCode.BACK_SPACE){
					for(int i = 0;i!=word.length()-1;i++){
						wordG=wordG+word.charAt(i);
					}
					word=wordG;
					entry.setText(word);
					wordG="";
				}
				//u5010309 words are typed here
				else if(RLG.bucket.size()<32&&keyEvent.getCode().isLetterKey()&& word.length()<8){
					word=word+keyEvent.getCode().toString();
					entry.setText(word);
				}
				/*u5010309 hitting enter checks if word's letters are in the bucket and word is in dictionary, 
				returns feedback, removes from bucket if true. Bang animation if correct word was entered */
				if (RLG.bucket.size()<32 && count<48&&keyEvent.getCode()==KeyCode.ENTER){

					DictionaryOps ops = new DictionaryOps();
					word=word.toLowerCase();
					char[] wordArray = word.toCharArray();
					ArrayList<Character> listOfArraysW = new ArrayList<Character>(word.length());

					for(int i = word.length()-1;i>=0;i--){
						listOfArraysW.add(wordArray[i]);
					}
					for(Character c:RLG.bucket){
						listOfArraysW.remove(c);
					}

					if(listOfArraysW.isEmpty() && ops.isLegalWord(word)){
						score = Score.getScore(word, score);
						jiggleTime = 0;
						for(int b = word.length()-1;b>=0;b--){							
							RLG.bucket.remove(RLG.bucket.indexOf(wordArray[b]));								
							word="";entry.setText("\"WORKS\"");
							if (RLG.bucket.size()>8){cannonBang.setX(Cannon8.getX()-61);}
							if (RLG.bucket.size()>16){cannonBang2.setX(Cannon16.getX()-61);}
							if (RLG.bucket.size()>24){cannonBang3.setX(Cannon24.getX()-61);}
							if (RLG.bucket.size()>32){cannonBang4.setX(Cannon32.getX()-61);}
							cannonBang.setOpacity(1.0);
							if(RLG.bucket.size()>8){cannonBang2.setOpacity(1.0);}
							if(RLG.bucket.size()>16){cannonBang3.setOpacity(1.0);}
							if(RLG.bucket.size()>24){cannonBang4.setOpacity(1.0);}
							bangCount = RLG.bucket.size();	
						}	
						
						/*Ryan - Modified from the example used in Caterpillar Game to play cannon fire*/
						String fireURI = null;
						try {
							fireURI = this.getClass().getResource("Cannon-SoundBible.com-1661203605.mp3").toURI().toString();
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
						AudioClip fire = new AudioClip(fireURI);
						fire.play();
					}
					else{
						word="";entry.setText("\"INVALID\"");
					}
					listOfArraysW.clear();// so it can be reused
				}
			}				
		}
				);	
		stage.setScene(scene);
		stage.show();		
	}

	/*----------------------------------Constructor Finished--------------------------------------*/

	/*This calculates the fps for the game, it was directly taken from the
	 * Caterpillar game used in class*/
	private void updateFPS(long elapsedNanos) {
		double elapsedSec =  elapsedNanos / 1000000000.0;
		if (frameCount  % 10 == 0)
			fps.setText(String.format("%.1f", 1/elapsedSec));	
	}

	//Created by Ryan Rushton u5192657 with input from Aiden Brown u5194042
	/*Clears the game, this is used due to issues that arose with returning to the main menu and using only timerA.stop()
	 * Timer would pause and then allow the user to reenter the game.  In itself, this is not an issue, but with the addition
	 * of animation of the letters, returning to the game would cause these letters to return to default. 
	 * clearGame is called within the escape key listener, thus clearing all attributes of the game scene, and refreshing the game
	 */	
	private void clearGame(){
		for(PrevBack p:previewArray){
			p.hideTextBack();
		}
		for(TextBack t:lettersArray){
			t.hideTextBack();
		}
		RLG.bucket.clear();
		RLG.preview.clear();

		score = 0;
		level = 0;
		jiggleTime = 0;
		bangCount = -1;
		count = 0;
		frameCount = 0;
		totalSecs = 0;
		pirateSecs = 0;
		pirateCounter = 0;
	}

	/*Written by Ryan Rushton u5192657
	 * This modifies the letters on screen as per functions in PrevBack and TextBack classes*/
	private void modifyLetters(){
		/*These following for statements add and hide the TextBack images
		 * to correspond with what is in the bucket*/
		for(int i = 0; i < 2; i++){
			if(RLG.preview.size()>i){
				previewArray.get(i).setTextBack(RLG.preview.get(i));
			}
		}
		/*Jiggles letters*/
		if(lettersArray.size()>1){
			for(int i = 0; i < lettersArray.size()-2; i++){
				lettersArray.get(i).jiggleText(jiggleTime, crntLevel);
			}
		}
		/*Sets new TextBack's and makes them expand*/
		for(int j=0; j<32; j++){
			if(RLG.bucket.size()>j){
				lettersArray.get(j).setTextBack(RLG.bucket.get(j));
				lettersArray.get(RLG.bucket.size()-1).expandTextBack(totalSecs, crntLevel);
			}
		}
		/*Hides TextBack if it doesnt have a corresponding letter*/
		for(int k = 31; k >=0; k--){
			if(RLG.bucket.size()<=k){
				lettersArray.get(k).hideTextBack();
			}
		}
		/*Hides PrevView if it doesn't have a corresponding letter*/
		for(int k = 1; k >=0; k--){
			if(RLG.preview.size()<=k){
				previewArray.get(k).hideTextBack();
			}
		}
		/*This makes it so only the next letter to be added fades red*/
		for(PrevBack p:previewArray){
			if(p.hasText()){
				p.expandTextBack(totalSecs, crntLevel);
				if(previewArray.get(1).hasText()){
					previewArray.get(0).fadePrevBack(totalSecs, crntLevel);
					previewArray.get(1).getRectangle2().setOpacity(0);
				}
				else{
					previewArray.get(0).getRectangle2().setOpacity(0);
					previewArray.get(1).getRectangle2().setOpacity(0);
				}
			}
		}
	}
}






