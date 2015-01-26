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

		Controller controller = new Controller("/");
    	
		Scene scene = new Scene(controller.getView(),800,600);
		
		stage.setTitle("Main View");
        stage.setScene(scene);
        stage.show();
	}

}