package Selection;

import java.util.Observable;

import Command.IVisitor;

public class SelectionController extends Observable implements IElement{

	protected String rootPath;
	protected String selectedPath;
		
	public void setRootPath(String path){
		this.rootPath = path;
	}
	
	public String getRootPath(){
		return this.rootPath;
	}
	
	public void setSelectedPath(String path){
		this.selectedPath = path;
	}
	
	public String getSelectedPath(){
		return this.selectedPath;
	}
	
	@Override
	public Object accept(IVisitor visitor) {
		return visitor.visit(this);
	}

}
