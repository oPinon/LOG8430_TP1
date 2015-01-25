package Application.SubViews;


import Application.Controller;
import Command.ICommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommandView extends VBox{
	
	protected Controller controller;
	
	public CommandView(Controller _controller){
		
		this.controller = _controller;
		
		VBox commandsPart1 = new VBox(); // each command is a HBox with button on left, and text on right
		
		for (ICommand command: _controller.getCommands()){
			
			HBox singleCommandView = new HBox();
			
			Button commandButton = new Button(command.getName());
			TextField commandResult = new TextField("no result"); 
			
			CommandView self = this;
			commandButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.out.println(command.getName()+" is pressed");
					System.out.println("In commandController, the selected element is: " + self.controller.getSelectedElement().getName()
							+ ". isFile?" + self.controller.getSelectedElement().isFile() 
							+ "; isFolder?" + self.controller.getSelectedElement().isDirectory() 
							);
					String result = (String)command.execute(self.controller.getSelectedElement());
					commandResult.setText(result);
				}
			});
			
			
			//commandResult.setAlignment(Pos.CENTER_RIGHT);
			//HBox.setHgrow(commandResult, Priority.ALWAYS);
			
			singleCommandView.getChildren().addAll(commandButton,commandResult);
			
			
			commandsPart1.getChildren().addAll(singleCommandView);
		}
		
		HBox commandsPart2 = new HBox(); // Clear button and Autorun checkbox
		Button clearButton = new Button("Clear");
		CommandView self = this;
		clearButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Clear function need to be implemented");
			}
		});
		
		CheckBox autorunCheckbox = new CheckBox("AutoRun");
		autorunCheckbox.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
	        	self.controller.setAutoRun(autorunCheckbox.isSelected());
	        	System.out.println("AutoRun is " + self.controller.getAutoRun());
	        }
		});

		commandsPart2.getChildren().addAll(clearButton, autorunCheckbox);
		
		this.getChildren().addAll(commandsPart1, commandsPart2);

	}
}
