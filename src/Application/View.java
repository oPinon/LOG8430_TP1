package Application;
import Application.SubViews.CommandView;
import Application.SubViews.SelectionView;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class View extends HBox{
	
	private Controller controller;
	
	public View(Controller _controller){
		this.controller = _controller;
		CommandView commandView = new CommandView(this.controller);
		HBox.setHgrow(commandView, Priority.ALWAYS);
		SelectionView selectionView = new SelectionView(this.controller);
		this.getChildren().addAll(selectionView, new Separator(), commandView);
		this.setPadding(new Insets(10));
	}
}
