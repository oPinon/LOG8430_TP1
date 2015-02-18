package oxz.application.view;

import oxz.application.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * extends HBox, The whole view has two parts, a selectionPartView and a
 * CommandPartView on the left and right respectively
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class View extends HBox {

	private CommandPartView commandView;

	/**
	 * The application View Constructor
	 * 
	 * @param controller
	 *            controller set itself to view as delegate
	 */
	public View(Controller controller) {

		controller.setView(this);

		// View of all the commands, on the right side of the window
		commandView = new CommandPartView(controller);
		HBox.setHgrow(commandView, Priority.ALWAYS);

		// View of the file explorer, on the left side
		SelectionPartView selectionView = new SelectionPartView(controller);

		this.getChildren().addAll(selectionView, new Separator(), commandView);
		this.setPadding(new Insets(10));
	}

	public void updateCommands() {
		commandView.updateCommands();
	}
}
