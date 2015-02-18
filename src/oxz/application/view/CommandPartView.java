package oxz.application.view;

import oxz.application.Controller;
import oxz.application.command.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * extends VBox, contains all the commandViews and a button "clean" and a
 * checkbox "Autorun"
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class CommandPartView extends VBox {

	private VBox commandsPart1;
	private Controller controller;

	/**
	 * View of the commands, with a Clear and an Autorun checkbox at the bottom
	 * 
	 * @param controller
	 *            controller delegated itself in view
	 */
	public CommandPartView(final Controller controller) {

		this.controller = controller;

		commandsPart1 = new VBox(); // each command is a HBox with button on
									// left, and text on right

		for (final ICommand command : controller.getCommands()) {

			CommandView singleCommandView = new CommandView(command);

			commandsPart1.getChildren().addAll(singleCommandView);
		}

		HBox commandsPart2 = new HBox(); // Clear button and Autorun checkbox
		commandsPart2.setAlignment(Pos.CENTER_RIGHT);
		commandsPart2.setSpacing(10);
		Button clearButton = new Button("Clear");
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (ICommand c : controller.getCommands()) {
					c.clear();
				}
			}
		});

		final CheckBox autorunCheckbox = new CheckBox("AutoRun");
		autorunCheckbox.setSelected(controller.getAutoRun());
		autorunCheckbox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.setAutoRun(autorunCheckbox.isSelected());
			}
		});

		commandsPart2.getChildren().addAll(clearButton, autorunCheckbox);

		ScrollPane scrollPanePart1 = new ScrollPane();
		scrollPanePart1.setContent(commandsPart1);
		scrollPanePart1.setFitToWidth(true);
		scrollPanePart1.setFitToHeight(true);
		VBox.setVgrow(scrollPanePart1, Priority.ALWAYS);
		this.getChildren().addAll(scrollPanePart1, new Separator(),
				commandsPart2);

	}

	/**
	 * if the Commands' list is modified by the user, this function shall be
	 * called to update the view
	 */
	public void updateCommands() {
		commandsPart1.getChildren().clear();
		for (final ICommand command : controller.getCommands()) {

			CommandView singleCommandView = new CommandView(command);

			commandsPart1.getChildren().addAll(singleCommandView);
		}
	}

}
