package game;

//u5194042  Aiden Brown


/* Creates a splash screen style main menu that allows the user to enter the game manually,
rather than the game automatically launching when the jar is run.
Also incorporates a how to play area to explain the controls to the user.
Includes a quit button that allows for the user to quit the game in the application rather than the OS close function.

Used http://docs.oracle.com/javafx/2/ui_controls/button.htm to figure out placing graphics on buttons
used http://java-buddy.blogspot.com.au/2012/03/detect-mouse-event.html  AND  http://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html

for mouseevent actions


 */



import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

class StartMenu extends Stage {
	// Creates the stage for running the menu
	StartMenu(final Stage primaryStage){
		final Stage stage = new Stage();  
		stage.setTitle("WordCannon!");
		Group root = new Group();
		Scene splashscreen = new Scene(root, 768, 1024);

		//adds the background to the scene
		String backgroundURL = this.getClass().getResource("menu.png").toString();
		final ImageView background = new ImageView(backgroundURL);
		background.setFitHeight(1024);
		background.setFitWidth(768);
		root.getChildren().add(background);

		//Adding in the images for the menu buttons
		String quitButtonURL = this.getClass().getResource("quitButt.png").toString();
		Image quitbutton = new Image (quitButtonURL);

		String menuButtonURL = this.getClass().getResource("playButt.png").toString();
		Image menubutton = new Image (menuButtonURL);

		//Adding in the buttons
		final Button startbutton = new Button();
		startbutton.setGraphic(new ImageView(menubutton)); //Sets a picture on to the button	
		root.getChildren().add(startbutton);

		final Button exit = new Button();
		exit.setGraphic(new ImageView(quitbutton));		
		root.getChildren().add(exit);

		//Button events
		startbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				try {
					new MainGame(primaryStage);
					stage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				stage.close();		
			}
		});

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				stage.close();		
				primaryStage.close();
			}
		});

		//Sets the layout and size of the buttons
		startbutton.setLayoutX(147);
		startbutton.setLayoutY(671);

		startbutton.setMaxHeight(77);//Sets the max and min height of the button the same. Allows for the image to cover the entire button
		startbutton.setMaxWidth(258);
		startbutton.setMinHeight(77);
		startbutton.setMinWidth(258);

		exit.setLayoutX(165);
		exit.setLayoutY(775);

		exit.setMaxHeight(65);
		exit.setMaxWidth(212);
		exit.setMinHeight(65);
		exit.setMinWidth(212);

		stage.setScene(splashscreen);
		stage.show();
	}
}


