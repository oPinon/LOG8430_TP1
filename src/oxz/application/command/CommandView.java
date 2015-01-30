package oxz.application.command;

import oxz.application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CommandView extends HBox {
	
	private Button executeButton;
	
	public CommandView(Controller controller, ICommand command){
		
		// button to execute the command
		executeButton = new Button(command.getName());
		executeButton.disableProperty().bind(command.disabledProperty());
		
		// textField to display the result of the command
		final TextField commandResult = new TextField("");
		commandResult.textProperty().bind(command.resultStringProperty());
		HBox.setHgrow(commandResult, Priority.ALWAYS);
		
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				// This might violate the MVC, need to be changed -- Yan Xu 2015-01-29
				// add a CommandController class
				command.execute();
			}
		});
		
		this.getChildren().addAll(executeButton, commandResult);
	}
}