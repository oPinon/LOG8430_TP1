
import Selection.SelectionController;
import Command.CommandController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static void main(String[] args) {
		
		Main.launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {

		SelectionController s = new SelectionController();
		CommandController c = new CommandController();
		s.addObserver(c);
		s.setRootPath(System.getProperty("user.dir") + "/src");
		
		HBox mainView = new HBox();
		mainView.getChildren().addAll(s.getSelectionView(), c.getCommandView());
    	
		Scene scene = new Scene(mainView,800,600);
		
		stage.setTitle("Main View");
        stage.setScene(scene);
        stage.show();
	}

}