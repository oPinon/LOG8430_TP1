package Selection;

import java.io.File;
import java.util.Observable;


public class SelectionController extends Observable {

	protected String rootPath;
	protected File selectedElement;
		
	public void setRootPath(String path){
		this.rootPath = path;
		//when set the root path, we need to reset the selected element
		this.setSelectedFile(path);
	}
	
	public void setSelectedFile(String path){
		this.selectedElement = new File(path);
		notifyObservers(this.selectedElement);
	}

}
