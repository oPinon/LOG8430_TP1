package Application;
import java.io.File;
import java.util.Observable;


public class Model extends Observable{
	private String rootPath;
	private File selectedElement;
	private final static Model INSTANCE = new Model();
	
	//Singleton
	private Model(){}
	
    // default public constructor
    public static Model getInstance() {
        return INSTANCE;
    }
	
	public void setRootPath(String path){
		this.rootPath = path;
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

}
