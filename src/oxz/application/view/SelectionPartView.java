package oxz.application.view;

import java.io.File;

import oxz.application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * extends VBox, contains a view with a file tree, and a button on the bottom to
 * change the root path
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class SelectionPartView extends VBox {

	/**
	 * SelectionPartView Constructor Contains a file tree view and two buttons
	 * that open file-choosers to select the rootPath
	 * 
	 * @param controller
	 *            controller set itself to view as delegate
	 */

	public SelectionPartView(final Controller controller) {

		// a view of the file tree in a scrollPane
		FileView fileView = new FileView(controller, new File(
				controller.getRootPath()), true);
		final ScrollPane fileViewPane = new ScrollPane();
		fileViewPane.setContent(fileView);
		fileViewPane.setFitToWidth(true);
		fileViewPane.setFitToHeight(true);
		VBox.setVgrow(fileViewPane, Priority.ALWAYS);

		// a button to change the root file
		Button selectRootButton = new Button("Select a folder");

		selectRootButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				// opens a directory chooser for the user to select the new root
				// path
				final Stage stage = new Stage();
				final DirectoryChooser directoryChooser = new DirectoryChooser();
				File file = directoryChooser.showDialog(stage);
				if (file != null) {
					controller.setRootPath(file.getAbsolutePath());

					fileViewPane.setContent(new FileView(controller, new File(
							controller.getRootPath()), true));
				}
			}
		});

		Button selectRootFileButton = new Button("Select a file");
		selectRootFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				// opens a directory chooser for the user to select the new root
				// path
				final Stage stage = new Stage();
				final FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					controller.setRootPath(file.getAbsolutePath());

					fileViewPane.setContent(new FileView(controller, new File(
							controller.getRootPath()), true));
				}
			}
		});

		HBox buttons = new HBox();
		buttons.getChildren().addAll(selectRootButton, selectRootFileButton);

		this.getChildren().addAll(fileViewPane, new Separator(), buttons);
	}
}
