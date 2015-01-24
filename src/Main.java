import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

	//ArrayList<ICommand> commands;
	//INode fileTree;
	
	public static void main(String[] args) {
		
		Main.launch(args);
		
		/*ArrayList<ICommand> commands = new ArrayList<ICommand>();
		commands.add(new VoidCommand());
		commands.add(new VoidCommand());
		commands.add(new VoidCommand());*/
	}

	@Override
	public void start(Stage arg0) throws Exception {

		HBox root = new HBox(); // divides the window between the fileExplorer and the commands' list
		Scene scene = new Scene(root,800,600);
		
		FileFolder rootFile = new FileFolder(new File("/"));
		rootFile.open();
		VBox fileExplorer = new VBox(); // contains the fileTree and the 'selectFile' button
		fileExplorer.prefWidthProperty().bind(scene.widthProperty().divide(2f)); // will be half of the total window
		{
			VBox fileTreeView = rootFile.getView(); // display the tree of files
			ScrollPane fileTreeScrollPane = new ScrollPane(fileTreeView); // it will be inside a scroll pane
			fileTreeScrollPane.setFitToWidth(true); fileTreeScrollPane.setFitToHeight(true);
			VBox.setVgrow(fileTreeScrollPane, Priority.ALWAYS); // makes sure the pane enlarges as much as possible
			fileTreeView.setFillWidth(true);
			
			Button fileButton = new Button("Select a file or folder");
			fileButton.prefWidthProperty().bind(fileExplorer.widthProperty());
			
			fileExplorer.getChildren().addAll(fileTreeScrollPane,fileButton);
		}
		
		VBox commandWindow = new VBox(); // part containing commands, their results and Clear/Autorun buttons
		{
			VBox commandsList = new VBox(); // list of commands and their results
			ScrollPane commandsListScrollPane = new ScrollPane(commandsList); // the list is inside a scroll pane
			VBox.setVgrow(commandsListScrollPane, Priority.ALWAYS);
			commandsListScrollPane.setFitToWidth(true);
			commandsListScrollPane.setFitToHeight(true);
			
			ArrayList<HBox> commands = new ArrayList<HBox>(); // each command is a HBox with button on left, and text on right
			for(int i=0; i<17; i++) {
				HBox command = new HBox();
				Button commandButton = new Button("Command "+i);
				TextField commandResult = new TextField("no result"); commandResult.setAlignment(Pos.CENTER_RIGHT);
				HBox.setHgrow(commandResult, Priority.ALWAYS);
				command.getChildren().addAll(commandButton,commandResult);
				commands.add(command);
			}
			commandsList.getChildren().addAll(commands);
			commandsList.setSpacing(10); commandsList.setPadding(new Insets(5));
			
			HBox commandsButtons = new HBox(); // Clear button and Autorun checkbox
			HBox.setHgrow(commandWindow, Priority.ALWAYS);
			Button clearButton = new Button("Clear");
			CheckBox autorunCheckbox = new CheckBox("AutoRun");
			commandsButtons.setAlignment(Pos.CENTER_RIGHT); commandsButtons.setSpacing(20);
			commandsButtons.getChildren().addAll(clearButton, autorunCheckbox);
			
			commandWindow.getChildren().addAll(commandsListScrollPane,new Separator(),commandsButtons);
		}
		
		root.getChildren().addAll(fileExplorer, new Separator(), commandWindow);
		
		arg0.setScene(scene);
		arg0.show();
	}

}