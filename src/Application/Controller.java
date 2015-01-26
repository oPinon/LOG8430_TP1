package Application;
import java.io.File;
import java.util.ArrayList;

import Command.ICommand;
import Command.PrintFileNameCommand;
import Command.PrintFolderNameCommand;
import Command.PrintPathCommand;

public class Controller {
	
	private boolean autoRun;
	private static Model model;
	private ArrayList<ICommand> commandsList; 
	
	public Controller(Model model){
		this.commandsList = new ArrayList<ICommand>();
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
		
		Controller.model = model;
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public boolean getAutoRun(){
		return this.autoRun;
	}
	
	public Model getModel(){
		return Controller.model;
	}
	
	public ArrayList<ICommand> getCommands(){
		return this.commandsList;
	}
	
	public void setRootPath(String path){
		Controller.model.setRootPath(path);
		this.setSelectedElement(path);
	}
	
	public String getRootPath(){
		return Controller.model.getRootPath();
	}
	
	public void setSelectedElement(String path){
		File file = new File(path);
		
		for(ICommand command : this.commandsList){
			command.setEnable(file);
		}
		Controller.model.setSelectedElement(file);
	}
	
	public File getSelectedElement(){
		return Controller.model.getSelectedElement();
	}
	
}
