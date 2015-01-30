package oxz.application;
import java.io.File;

public class Model {
	private String rootPath = "/";
	private File selectedElement;
	
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
