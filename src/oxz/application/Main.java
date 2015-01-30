package oxz.application;

import oxz.application.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Launches one of each : Model, View, Controller
 */
public class Main extends Application {
	
	public static void main(String[] args) {
		Main.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		//Model model = Model.getInstance("/"); Singleton removed
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
    	
		Scene scene = new Scene(view,800,600);
		
		stage.setTitle("Main View");
        stage.setScene(scene);
        stage.show();
	}

}