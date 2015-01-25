package Application.SubViews;

import java.io.File;

import Application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SelectionView extends VBox {
	
	protected Controller controller;
	
	public SelectionView(Controller _controller){
		this.controller = _controller;
		
		FileView fileView = new FileView(this.controller, new File(this.controller.getRootPath()));
		Button selectRootButton = new Button("Select a file or folder");
		
		SelectionView self = this;
		selectRootButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				final Stage stage = new Stage();
				final DirectoryChooser directoryChooser = new DirectoryChooser();
				File file = directoryChooser.showDialog(stage);
                if (file != null) {
                	self.controller.setRootPath(file.getAbsolutePath());
                	self.update();
                }
			}
		});
		
		
		this.getChildren().addAll(fileView, selectRootButton);
	}
	
	public void update() {
		this.getChildren().remove(0);
		this.getChildren().add(0, new FileView(this.controller, new File(this.controller.getRootPath())));
	}
}
