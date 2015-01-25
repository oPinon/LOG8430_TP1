package Command;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommandView extends VBox{
	public CommandView(ArrayList<ICommand> commandsList){
		
		VBox commandsPart1 = new VBox(); // each command is a HBox with button on left, and text on right
		
		for (ICommand command: commandsList){
			
			HBox singleCommandView = new HBox();
			
			Button commandButton = new Button(command.getName());
			TextField commandResult = new TextField("no result"); 
			//commandResult.setAlignment(Pos.CENTER_RIGHT);
			//HBox.setHgrow(commandResult, Priority.ALWAYS);
			
			singleCommandView.getChildren().addAll(commandButton,commandResult);
			
			commandsPart1.getChildren().addAll(singleCommandView);
		}
		
		HBox commandsPart2 = new HBox(); // Clear button and Autorun checkbox
		Button clearButton = new Button("Clear");
		CheckBox autorunCheckbox = new CheckBox("AutoRun");

		commandsPart2.getChildren().addAll(clearButton, autorunCheckbox);
		
		this.getChildren().addAll(commandsPart1, commandsPart2);

	}
}
