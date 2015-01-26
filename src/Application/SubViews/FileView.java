package Application.SubViews;
import java.io.File;
import Application.Controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class FileView extends VBox{
	
	File file;
	Controller controller;
			
	public FileView(Controller _controller, File _file) {
		
		this.controller = _controller;
		this.file = _file;
		
		SingleFileView selfView = new SingleFileView(this.controller, this);
		this.getChildren().addAll(selfView);
		
	}

	public void update(SingleFileView singleFileView){
		
		if (singleFileView.isOpened){
			for(File f : this.file.listFiles()) {
				HBox indentedFileView = new HBox();
				indentedFileView.getChildren().addAll(new Text("    "),new FileView(this.controller, f));
				this.getChildren().add(indentedFileView);
				//this.getChildren().add(new FileView(f));	
			}
		} else{
			int length = this.getChildren().size();
			//remove the child view except itself
			this.getChildren().remove(1, length);
		}
	}
}
