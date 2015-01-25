package Application.SubViews;

import java.io.File;

import Application.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SelectionView extends VBox{
	
	protected Controller controller;
	
	public SelectionView(Controller _controller){
		this.controller = _controller;
		
		FileView fileView = new FileView(this.controller, new File(this.controller.getRootPath()));
		Button selectRootButton = new Button("Select a file or folder");
		
		this.getChildren().addAll(fileView, selectRootButton);
	}
	
	public void update(File f){
		
		this.getChildren().remove(0);
		this.getChildren().add(0, new FileView(this.controller, new File(this.controller.getRootPath())));
		
	}
}
