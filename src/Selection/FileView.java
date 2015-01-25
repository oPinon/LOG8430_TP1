package Selection;
import java.io.File;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class FileView extends VBox{
	
	File file;
	SelectionController selectionController;
			
	public FileView(File _file, SelectionController selectionController) {
		
		this.file = _file;
		this.selectionController = selectionController;
				
		SingleFileView selfView = new SingleFileView(_file, this, this.selectionController);
		this.getChildren().addAll(selfView);
		
		/*if (_file.isDirectory() && selfView.isOpened){
			
			for(File f : _file.listFiles()) {
				
				//FileView fv = new FileView(f);
				HBox intentedFileView = new HBox(new Text("--"),new FileView(f));
				this.getChildren().add(intentedFileView);
				
			}
		}*/
	}

	public void update(SingleFileView singleFileView){
		
		if (singleFileView.isOpened){
			for(File f : this.file.listFiles()) {
				HBox intentedFileView = new HBox(new Text("    "),new FileView(f, this.selectionController));
				this.getChildren().add(intentedFileView);
				//this.getChildren().add(new FileView(f));	
			}
		} else{
			int length = this.getChildren().size();
			//remove the child view except itself
			this.getChildren().remove(1, length);
		}
	}
}
