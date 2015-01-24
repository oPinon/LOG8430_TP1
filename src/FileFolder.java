import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class FileFolder {
	
	static Image fileIcon = new Image("file:images/file.png");
	static Image folderOpenedIcon = new Image("file:images/folderOpened.png");
	static Image folderClosedIcon = new Image("file:images/folderClosed.png");

	// fileSystem attributes
	File file;
	boolean isOpened = false;
	ArrayList<FileFolder> children = new ArrayList<FileFolder>();
	
	// View attributes
	VBox view = new VBox();
	Button button;
	

	public FileFolder(File file) {
		
		this.file = file;
		button = new Button(this.file.getName());
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(isOpened) { close(); }
				else { open(); }
			}
		});
		/*button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if(arg0.getClickCount()>=2) { // if double click
					if(isOpened) { close(); }
					else { open(); }
				}
			}
		});*/
		updateView();
	}
	
	public void open() {
		
		this.isOpened = true;
		File[] children = this.file.listFiles();
		if(children!=null) {
			for(final File f : children) {
				this.children.add(new FileFolder(f));
			}
		}
		updateView();
	}
	
	public void close() {
		
		this.isOpened = false;
		this.children = new ArrayList<FileFolder>();
		updateView();
	}
	
	public void updateView() {
		
		this.view.getChildren().clear();
		ImageView typeIcon = new ImageView( this.file.isDirectory()? (isOpened?folderOpenedIcon:folderClosedIcon):fileIcon );
		
		HBox fileView = new HBox(typeIcon,button);
		view.getChildren().addAll(fileView);
		for(FileFolder f : this.children) {
			VBox childView = f.getView();
			childView.setPadding(new Insets(0,0,0,20));
			view.getChildren().add(childView);
		}
	}
	
	public VBox getView() { return this.view; }
	
}
