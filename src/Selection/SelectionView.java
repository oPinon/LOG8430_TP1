package Selection;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SelectionView extends VBox{
	
	protected SelectionController selectionController;
	
	public SelectionView(File f, SelectionController selectionController){
		this.selectionController = selectionController;
		
		FileView fileView = new FileView(f, this.selectionController);
		Button selectRootButton = new Button("Select a file or folder");
		
		this.getChildren().addAll(fileView, selectRootButton);
	}
	
	public void update(File f){
		
		this.getChildren().remove(0);
		this.getChildren().add(0, new FileView(f, this.selectionController));
		
	}
}
