package oxz.application.view;

import oxz.application.Controller;
import oxz.application.command.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SingleCommandView extends HBox {
	
	private Controller controller;
	private ICommand command;
	private Button executeButton;
	
	public SingleCommandView(Controller controller, ICommand command){
		this.controller = controller;
		this.command = command;
		
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
				command.execute();
			}
		});
		
		this.getChildren().addAll(executeButton, commandResult);
	}
}