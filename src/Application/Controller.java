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
		this.setRootPath(model.getRootPath());
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public Model getModel(){
		return Controller.model;
	}
	
	public ArrayList<ICommand> getCommands(){
		return this.commandsList;
	}
	
	public void setRootPath(String path){
		Controller.model.setRootPath(path);
		this.setSelectedElement(new File(path));
	}
	
	public String getRootPath(){
		return Controller.model.getRootPath();
	}
	
	public void setSelectedElement(File file){
		
		Controller.model.setSelectedElement(file);
		for(ICommand c : commandsList) { // clear all results since the file has changed
			c.setFile(file);
		}
		if(autoRun) {
			for(ICommand c : commandsList) {
				c.execute();
			}
		}
	}
	
	public File getSelectedElement(){
		return Controller.model.getSelectedElement();
	}
	
}
