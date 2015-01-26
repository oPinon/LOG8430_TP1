package Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static void main(String[] args) {
		
		Main.launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {

		Model model = Model.getInstance("/"); // creates a new model at the disk's root
		Controller controller = new Controller(model);
		View view = new View(controller);
    	
		Scene scene = new Scene(view,800,600);
		
		stage.setTitle("Main View");
        stage.setScene(scene);
        stage.show();
	}

}