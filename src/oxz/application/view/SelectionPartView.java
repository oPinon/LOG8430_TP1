package oxz.application.view;

import java.io.File;

import oxz.application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/*
 * Window with a file tree, and a button on the bottom to change the root
 */
public class SelectionPartView extends VBox {
	
	protected Controller controller;
	
	public SelectionPartView(Controller controller){
		
		this.controller = controller;
		
		// a view of the file tree in a scrollPane
		FileView fileView = new FileView(this.controller, new File(this.controller.getRootPath()), true);		
		final ScrollPane fileViewPane = new ScrollPane(); fileViewPane.setContent(fileView);
		fileViewPane.setFitToWidth(true); fileViewPane.setFitToHeight(true);
		VBox.setVgrow(fileViewPane, Priority.ALWAYS);
		
		// a button to change the root file
		Button selectRootButton = new Button("Select a file or folder");
		
		final SelectionPartView self = this;
		selectRootButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				
				// opens a directory chooser for the user to select the new root path
				final Stage stage = new Stage();
				final DirectoryChooser directoryChooser = new DirectoryChooser();
				File file = directoryChooser.showDialog(stage);
                if (file != null) {
                	self.controller.setRootPath(file.getAbsolutePath());
                	fileViewPane.setContent(new FileView(self.controller, new File(self.controller.getRootPath()), true));
                }
			}
		});
		
		this.getChildren().addAll(fileViewPane, new Separator(), selectRootButton);
	}
}
