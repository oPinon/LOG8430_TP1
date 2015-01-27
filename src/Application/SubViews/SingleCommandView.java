package Application.SubViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import Application.Controller;
import Command.ICommand;

public class SingleCommandView extends HBox {
	
	private Controller controller;
	private ICommand command;
	private Button executeButton;
	
	public SingleCommandView(Controller controller, ICommand command){
		this.controller = controller;
		this.command = command;
		
		executeButton = new Button(command.getName());
		final TextField commandResult = new TextField("");
		HBox.setHgrow(commandResult, Priority.ALWAYS);
		
		executeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				command.execute(controller.getSelectedElement());
				commandResult.textProperty().bind(command.resultStringProperty());
			}
		});
		
		this.getChildren().addAll(executeButton, commandResult);
	}
}