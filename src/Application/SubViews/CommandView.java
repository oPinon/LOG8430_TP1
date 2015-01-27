package Application.SubViews;

import Application.Controller;
import Command.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CommandView extends VBox{
	
	protected Controller controller;
	
	// view of the commands, with a Clear and an Autorun button at the bottom
	public CommandView(Controller controller){
		
		this.controller = controller;
		
		VBox commandsPart1 = new VBox(); // each command is a HBox with button on left, and text on right
		
		for (final ICommand command: controller.getCommands()){
			
			SingleCommandView singleCommandView = new SingleCommandView(this.controller, command);			
			
			commandsPart1.getChildren().addAll(singleCommandView);
		}
		
		HBox commandsPart2 = new HBox(); // Clear button and Autorun checkbox
		commandsPart2.setAlignment(Pos.CENTER_RIGHT); commandsPart2.setSpacing(10);
		Button clearButton = new Button("Clear");
		clearButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				for(ICommand c : controller.getCommands()) {
					c.clear();
				}
			}
		});
		
		final CheckBox autorunCheckbox = new CheckBox("AutoRun");
		autorunCheckbox.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
	        	controller.setAutoRun(autorunCheckbox.isSelected());
	        }
		});

		commandsPart2.getChildren().addAll(clearButton, autorunCheckbox);
		
		ScrollPane scrollPanePart1 = new ScrollPane();
		scrollPanePart1.setContent(commandsPart1);
		scrollPanePart1.setFitToWidth(true); scrollPanePart1.setFitToHeight(true);
		VBox.setVgrow(scrollPanePart1, Priority.ALWAYS);
		this.getChildren().addAll( scrollPanePart1, new Separator(), commandsPart2);

	}

}
