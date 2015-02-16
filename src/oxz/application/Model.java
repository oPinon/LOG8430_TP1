package oxz.application;
import java.io.File;

/**
 * This is the controller of the program, 
 * generated with the model of our application, 
 * It has two attributes : String rootPath, represent the root path which should used to build the document tree in view
 * 						   File selectedElement, use to store the file(or folder) can be executed
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class Model {
	private String rootPath = ".";
	private File selectedElement = new File(".");
	
	/*
	 * remove the Singleton Pattern, it is not necessary here
	 * Yan Xu 2015-01-29
	private final static Model INSTANCE = new Model();
	
	//Singleton
	private Model(){}
	
    // getInstance method used for get the unique instance  
    public static Model getInstance(String rootPath) {
    	INSTANCE.setRootPath(rootPath);
    	return INSTANCE;
    }
	*/
	
	public void setRootPath(String path){
		this.rootPath = path;
		this.setSelectedElement(new File(rootPath));
	}
	
	public String getRootPath(){
		return this.rootPath;
	}
	
	public File getSelectedElement(){
		return this.selectedElement;
	}

	public void setSelectedElement(File file){
		this.selectedElement = file;
	}

}
