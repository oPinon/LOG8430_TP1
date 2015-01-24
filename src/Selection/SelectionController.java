package Selection;

import java.nio.file.Path;
import java.util.Observable;

import Command.IVisitor;

public class SelectionController extends Observable implements IElement{

	protected Path rootPath;
	protected Path elementPath;
		
	public void setRootPath(Path path){
		this.rootPath = path;
	}
	
	public Path getRootPath(){
		return this.rootPath;
	}
	
	public void setElementPath(Path path){
		this.rootPath = path;
	}
	
	public Path getElementPath(){
		return this.rootPath;
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

}
