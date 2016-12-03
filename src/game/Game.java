package game;

/*This just runs the game, it wasn't implemented in StartMenu as this way it allows us to 
 *swap between screens when esc is pressed and leaves StartMenu to be a class only implementing 
 *the menu screen.*/

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {

	@Override
	public void start(Stage primarystage) throws Exception {
		new StartMenu (primarystage);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch (args);
	}

}
