package Application.SubViews;


import java.util.Observable;
import java.util.Observer;

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
	
	public CommandView(Controller _controller){
		
		this.controller = _controller;
		
		VBox commandsPart1 = new VBox(); // each command is a HBox with button on left, and text on right
		
		for (ICommand command: _controller.getCommands()){
			
			SingleCommandView singleCommandView = new SingleCommandView(this.controller);
			this.controller.getModel().addObserver(singleCommandView);
			
			Button commandButton = new Button(command.getName());
			TextField commandResult = new TextField("");
			HBox.setHgrow(commandResult, Priority.ALWAYS);
			
			CommandView self = this;
			commandButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					System.out.println(command.getName()+" is pressed");
					System.out.println("In commandController, the selected element is: " + self.controller.getSelectedElement().getName()
							+ ". isFile?" + self.controller.getSelectedElement().isFile() 
							+ "; isFolder?" + self.controller.getSelectedElement().isDirectory() 
							);
					if(command.isEnable()){
						String result = (String)command.execute(self.controller.getSelectedElement());
						commandResult.setText(result);
					}
				}
			});
			
			
			//commandResult.setAlignment(Pos.CENTER_RIGHT);
			//HBox.setHgrow(commandResult, Priority.ALWAYS);
			
			singleCommandView.getChildren().addAll(commandButton,commandResult);
			
			
			commandsPart1.getChildren().addAll(singleCommandView);
		}
		
		HBox commandsPart2 = new HBox(); // Clear button and Autorun checkbox
		commandsPart2.setAlignment(Pos.CENTER_RIGHT); commandsPart2.setSpacing(10);
		Button clearButton = new Button("Clear");
		CommandView self = this;
		clearButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				
				CommandView commandView = (CommandView) self.controller.getView().getChildren().get(1);
				VBox commandsPart1 = (VBox) commandView.getChildren().get(0);
				for (Node n: commandsPart1.getChildren()){
					SingleCommandView s = (SingleCommandView)n;
					TextField t = (TextField)s.getChildren().get(1);
					t.setText("");
				}
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
		
		ScrollPane scrollPanePart1 = new ScrollPane(commandsPart1);
		scrollPanePart1.setFitToWidth(true); scrollPanePart1.setFitToHeight(true);
		VBox.setVgrow(scrollPanePart1, Priority.ALWAYS);
		this.getChildren().addAll( scrollPanePart1, new Separator(), commandsPart2);

	}
	
	public class SingleCommandView extends HBox implements Observer{
		
		private Controller controller;
		
		public SingleCommandView(Controller _controller){
			this.controller = _controller;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			System.out.println("View update");
			
			if(this.controller.getAutoRun() && (String)arg=="elementSelected"){
				((TextField)this.getChildren().get(1)).setText(""); // HORRIBLE CODE :(
				this.getChildren().get(0).fireEvent(new ActionEvent());
			}
		}
		
	}

}
