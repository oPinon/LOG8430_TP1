package oxz.application;
import java.io.File;


public class Model {
	private String rootPath;
	private File selectedElement;
	private final static Model INSTANCE = new Model();
	
	//Singleton
	private Model(){}
	
    // default public constructor
    public static Model getInstance() {
        return INSTANCE;
    }
    
    public static Model getInstance(String rootPath) {
    	INSTANCE.rootPath = rootPath;
    	INSTANCE.setSelectedElement(new File(rootPath));
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
	}

}