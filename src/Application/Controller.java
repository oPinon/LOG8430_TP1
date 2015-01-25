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
	private View view;
	private ArrayList<ICommand> commandsList; 
	
	public Controller(String rootPath){
		this.commandsList = new ArrayList<ICommand>();
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
		
		Controller.model = new Model();
		Controller.model.setRootPath(rootPath);
		this.view = new View(this);
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public boolean getAutoRun(){
		return this.autoRun;
	}
	
	public View getView(){
		return this.view;
	}
	
	public ArrayList<ICommand> getCommands(){
		return this.commandsList;
	}
	
	public void setRootPath(String path){
		Controller.model.setRootPath(path);
	}
	
	public String getRootPath(){
		return Controller.model.getRootPath();
	}
	
	public void setSelectedElement(String path){
		File file = new File(path);
		
		Controller.model.setSelectedElement(file);
		
		for(ICommand command : this.commandsList){
			command.setEnable(file);
		}
		
		if(this.autoRun){
			for(ICommand command : this.commandsList){
				System.out.println(command.execute(file));
			}
		}
	}
	
	public File getSelectedElement(){
		return Controller.model.getSelectedElement();
	}
	
}
