package oxz.application.view;
import oxz.application.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class View extends HBox{
		
	public View(Controller controller){
				
		// View of all the commands, on the right side of the window
		CommandPartView commandView = new CommandPartView(controller);
		HBox.setHgrow(commandView, Priority.ALWAYS);
		
		// View of the file explorer, on the left side
		SelectionPartView selectionView = new SelectionPartView(controller);
		
		this.getChildren().addAll(selectionView, new Separator(), commandView);
		this.setPadding(new Insets(10));
	}
}
