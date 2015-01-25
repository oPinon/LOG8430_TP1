package Application;
import Application.SubViews.CommandView;
import Application.SubViews.SelectionView;
import javafx.scene.layout.HBox;


public class View extends HBox{
	
	private Controller controller;
	
	public View(Controller _controller){
		this.controller = _controller;
		CommandView commandView = new CommandView(this.controller);
		SelectionView selectionView = new SelectionView(this.controller);
		this.getChildren().addAll(selectionView, commandView);
	}
}
