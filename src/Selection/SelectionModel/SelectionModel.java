package Selection.SelectionModel;

import java.io.File;
import java.util.Observable;


public class SelectionModel extends Observable{
	
	private String rootPath;
	private File selectedElement;

	public void setRootPath(String path){
		this.rootPath = path;
		this.setSelectedElement(path);
	}
	
	public String getRootPath(){
		return this.rootPath;
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
