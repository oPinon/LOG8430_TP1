package oxz.application.view;
import oxz.application.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class View extends HBox{
	
	private Controller controller;
	
	public View(Controller controller){
		
		this.controller = controller;
		
		// View of all the commands, on the right side of the window
		CommandView commandView = new CommandView(this.controller);
		HBox.setHgrow(commandView, Priority.ALWAYS);
		
		// View of the file explorer, on the left side
		SelectionView selectionView = new SelectionView(this.controller);
		
		this.getChildren().addAll(selectionView, new Separator(), commandView);
		this.setPadding(new Insets(10));
	}
}
