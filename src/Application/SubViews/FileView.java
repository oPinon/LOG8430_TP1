package Application.SubViews;

import java.io.File;

import Application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FileView extends VBox{
	
	static Image fileIcon = new Image("file:images/file.png");
	static Image folderOpenedIcon = new Image("file:images/folderOpened.png");
	static Image folderClosedIcon = new Image("file:images/folderClosed.png");
	
	boolean isOpened;
	boolean isSelected;
	Controller controller;
	
	public FileView(Controller controller, File file, boolean isRoot) {
		
		this.controller = controller;
		
		HBox fileView = new HBox();
		final VBox childrenView = new VBox();
		
		// if it is a directory, the icon is a button to open/close it
		// else the icon is just an image
		Node icon;
		if(file.isDirectory()) {
			Button openButton = new Button();
			final ImageView imageView = new ImageView(folderClosedIcon);
			openButton.setGraphic(imageView);
			openButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					isOpened = !isOpened;
					imageView.setImage( isOpened ? folderOpenedIcon : folderClosedIcon);
					childrenView.getChildren().clear();
					if(isOpened) {
						File[] children = file.listFiles();
						if(children!=null) {
							for(File f : children) {
								FileView child = new FileView(controller, f, false);
								child.setPadding(new Insets(0,0,0,20));
								childrenView.getChildren().add(child);
							}
						}
					}
				}
			});
			icon = openButton;
		} else {
			icon = new ImageView(fileIcon);
		}
		
		Button selectButton = new Button(isRoot ? file.getAbsolutePath() : file.getName());
		selectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controller.setSelectedElement(file);
			}
		});
		
		fileView.getChildren().addAll(icon,selectButton);
		this.getChildren().addAll(fileView,childrenView);
		
	}
}