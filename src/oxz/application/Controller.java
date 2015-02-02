package oxz.application;
import java.io.File;
import java.util.ArrayList;

import oxz.application.command.ICommand;
/*
import oxz.application.command.imp.PrintFileNameCommand;
import oxz.application.command.imp.PrintFolderNameCommand;
import oxz.application.command.imp.PrintPathCommand;
*/

/**
 * This is the controller of the program, 
 * generated with the model of our application
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class Controller {
	
	private boolean autoRun;
	private Model model;
	private ArrayList<ICommand> commandsList = new ArrayList<ICommand>(); 
	
	public Controller(Model model){
		
		// get the commands folder
		
		loadCommands();
		this.model = model;
		this.setRootPath(model.getRootPath()); 
	}
	
	/**
	 * This function used for load the commands' classes
	 */
	private void loadCommands(){
		this.commandsList.clear();
		
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/src/oxz/application/command/imp");
		
		if (concreteCommandFolder.isDirectory()){
			
			//MyClassLoader classLoader = new MyClassLoader(Controller.class.getClassLoader());
			ClassLoader classLoader = Controller.class.getClassLoader();
			File[] commands = concreteCommandFolder.listFiles();
			
			for(int i=0; i<commands.length; i++){
				String fileName = commands[i].getName();
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				
				try {
					Class<?> commandClass = classLoader.loadClass("oxz.application.command.imp."+className);
					ICommand aCommand = (ICommand) commandClass.newInstance();
					this.commandsList.add(aCommand);
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} 
			
			}
			
		}
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public Model getModel(){
		return this.model;
	}
	
	public ArrayList<ICommand> getCommands(){
		return this.commandsList;
	}
	
	public void setRootPath(String path){
		this.model.setRootPath(path);
		this.setSelectedElement(new File(path));
	}
	
	public String getRootPath(){
		return this.model.getRootPath();
	}
	
	/**
	 * Set a file or folder to be the selected element, pass it to all the commands
	 * if this.autoRun is true, execute all the commands
	 * This function will call loadCommands to reload commands classes
	 * @param file the element to be used in command, can be a file or a folder
	 */
	public void setSelectedElement(File file){
		
		//reload the commands' classes
		loadCommands();
		
		this.model.setSelectedElement(file);
		for(ICommand c : commandsList) { // clear all results since the file has changed
			c.setFile(file); // used for load the file to the command, command will be disabled if the input is not valid
		}
		if(autoRun) {
			for(ICommand c : commandsList) {
				c.execute();
			}
		}
	}
	
	public File getSelectedElement(){
		return this.model.getSelectedElement();
	}
	
}
