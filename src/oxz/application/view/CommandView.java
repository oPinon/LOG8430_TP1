package oxz.application.view;

import oxz.application.command.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * The command view, extends HBox, contains a Button and a TextField
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class CommandView extends HBox {

	/**
	 * The CommandView is constructed based on a command
	 * 
	 * @param command the command use for generate the view
	 */
	public CommandView(final ICommand command) {

		// button to execute the command
		Button executeButton = new Button(command.getName());
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