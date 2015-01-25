package Application;
import java.io.File;
import java.util.Observable;


public class Model extends Observable{
	private String rootPath;
	private File selectedElement;
	//private ArrayList<String> outputs;
	
	public void setRootPath(String path){
		this.rootPath = path;
		//this.setChanged();
		//notifyObservers("rootChanged");
	}
	
	public String getRootPath(){
		return this.rootPath;
	}
	
	public File getSelectedElement(){
		return this.selectedElement;
	}

	public void setSelectedElement(File file){
		this.selectedElement = file;
		System.out.println(this.selectedElement.getName() + " is the seletcted element");
		this.setChanged();
		notifyObservers("elementSelected");
	}
	
	public void cleanOutputs(){
		//this.outputs.clear();
		this.setChanged();
		notifyObservers("clean");
	}
}
