package Application.SubViews;

import java.io.File;

import Application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SingleFileView extends HBox{
	
	static Image fileIcon = new Image("file:images/file.png");
	static Image folderOpenedIcon = new Image("file:images/folderOpened.png");
	static Image folderClosedIcon = new Image("file:images/folderClosed.png");
	
	boolean isOpened;
	boolean isSelected;
	FileView parent;
	Controller controller;
	
	public SingleFileView(Controller _controller, FileView _parent) {
		
		this.parent = _parent;
		this.controller = _controller;
		
		// Set the open Button
		// need to add a function to notify the view to update when open/close
		Button openButton = null;
		
		File f = _parent.file;
		if(f.isDirectory()){
			openButton = new Button("+");
						
			SingleFileView self = this;
			openButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					
					if(isOpened) {
						System.out.println("Close a folder");
						isOpened = false;
						parent.update(self);
					}
					else {
						System.out.println("Open a folder");
						isOpened = true;
						parent.update(self);
					}
				}
			});
		} else if (f.isFile()){
			openButton = new Button(" "); //in fact, this is not a button, need to be changed after;
		}
		
		//set the ImageView
		ImageView typeIcon = new ImageView(f.isDirectory()? (isOpened?folderOpenedIcon:folderClosedIcon):fileIcon );
		
		//set the selectButton
		//need to add a event handler to notify the controller which file is selected
		SingleFileView self = this;
		Button selectButton = new Button(f.getName());
		selectButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//need to be implemented
				System.out.println(f.getName()+"'s SelectButton is pressed");
				self.controller.setSelectedElement(f.getAbsolutePath());
				
			}
		});
		
		//combine the three button to this
		this.getChildren().addAll(openButton, typeIcon,selectButton);
	}
}