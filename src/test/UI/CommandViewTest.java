package test.UI;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Application.SubViews.CommandView;
import Command.ICommand;
import Command.PrintFileNameCommand;
import Command.PrintFolderNameCommand;
import Command.PrintPathCommand;

public class CommandViewTest extends Application {

	public static void main(String[] args) {
		SelectionViewTest.launch(args);
	 }
	 
	
    public void start(Stage stage) {
    	
    	ArrayList<ICommand> commandsList = new ArrayList<ICommand>();
		commandsList.add(new PrintFileNameCommand());
		commandsList.add(new PrintFolderNameCommand());
		commandsList.add(new PrintPathCommand());
    	
    	VBox view = new CommandView(commandsList, null);
    	
		Scene scene = new Scene(view,800,600);
		
        stage.setTitle("SelectionViewTest");
        stage.setScene(scene);
        stage.show();
    }

}
