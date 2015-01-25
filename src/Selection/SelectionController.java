package Selection;

import java.io.File;
import java.util.Observable;


public class SelectionController extends Observable {

	protected String rootPath;
	//The selected Element could be a file or folder, normally
	protected File selectedElement;
	
	protected SelectionView selectionView;
	
	public SelectionController(){
		this.selectionView = new SelectionView(new File("/"), this);
	}
	
	public SelectionView getSelectionView(){
		return this.selectionView;
	}
	
		
	public void setRootPath(String path){
		this.rootPath = path;
		//when set the root path, we need to reset the selected element
		this.setSelectedElement(path);
		//update the view
		this.selectionView.update(new File(path));
	}
	
	public File getSelectedElement(){
		return this.selectedElement;
	}
	
	public void setSelectedElement(String path){
		this.selectedElement = new File(path);
		System.out.println(this.selectedElement.getName() + " is the seletcted element");
		this.setChanged();
		notifyObservers(this.selectedElement);
	}

}
