package oxz.application.view;

import java.io.File;

import oxz.application.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * extends VBox, a FileView can contains its children to form a tree structure
 * every file element has a button, it call the controller to set the selected
 * element when is clicked
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class FileView extends VBox {

	static Image fileIcon = new Image("file:images/file.png");
	static Image folderOpenedIcon = new Image("file:images/folderOpened.png");
	static Image folderClosedIcon = new Image("file:images/folderClosed.png");

	private boolean isOpened;

	/**
	 * FileView constructor
	 * 
	 * @param controller
	 *            controller delegated itself in view
	 * @param file
	 *            the file need to be presented
	 * @param isRoot if the File file passed is the root directory of the system, need
	 *        to be set True, otherwise set to False
	 */
	public FileView(final Controller controller, final File file, boolean isRoot) {

		HBox fileView = new HBox();
		final VBox childrenView = new VBox();

		// if it is a directory, the icon is a button to open/close it
		// else the icon is just an image
		Node icon;
		if (file.isDirectory()) {
			Button openButton = new Button();
			final ImageView imageView = new ImageView(folderClosedIcon);
			openButton.setGraphic(imageView);
			openButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					isOpened = !isOpened;
					imageView.setImage(isOpened ? folderOpenedIcon
							: folderClosedIcon);
					childrenView.getChildren().clear();
					if (isOpened) {
						File[] children = file.listFiles();
						if (children != null) {
							for (File f : children) {
								FileView child = new FileView(controller, f,
										false);
								child.setPadding(new Insets(0, 0, 0, 20));
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

		Button selectButton = new Button(isRoot ? file.getAbsolutePath()
				: file.getName());
		selectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controller.setSelectedElement(file);
			}
		});

		fileView.getChildren().addAll(icon, selectButton);
		this.getChildren().addAll(fileView, childrenView);

	}
}