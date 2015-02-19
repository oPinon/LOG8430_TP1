package oxz.application;

import java.io.File;

import oxz.application.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the entry point of the application It initialize a model, a
 * controller and a scene contains the view.
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */
public class Main extends Application {

	public static void main(String[] args) {
		Main.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		Controller controller = new Controller(concreteCommandFolder);
		View view = new View(controller);
    	
		Scene scene = new Scene(view,800,600);
		
		stage.setTitle(controller.concreteCommandFolder.getAbsolutePath());
		stage.setScene(scene);
		stage.show();
	}

}