package uiTest;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Selection.SelectionView;

public class SelectionViewTest extends Application{

	
	public static void main(String[] args) {
		SelectionViewTest.launch(args);
	 }
	 
	
    public void start(Stage stage) {
    	
    	VBox view = new SelectionView(new File(System.getProperty("user.dir")), null);
    	
		Scene scene = new Scene(view,800,600);
		
		
		
        stage.setTitle("SelectionViewTest");
        stage.setScene(scene);
        stage.show();
    }
  

}
