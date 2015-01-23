import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

	ArrayList<ICommand> commands;
	INode fileTree;
	
	public static void main(String[] args) {
		
		//Main main = new Main();
		//main.launch(args);
		
		ArrayList<ICommand> commands = new ArrayList<ICommand>();
		commands.add(new VoidCommand());
		commands.add(new VoidCommand());
		commands.add(new VoidCommand());
	}

	@Override
	public void start(Stage arg0) throws Exception {

		HBox root = new HBox();
		Scene scene = new Scene(root,800,600);
		
		VBox fileExplorer = new VBox();
		
		
		
		arg0.setScene(scene);
		arg0.show();
	}

}
