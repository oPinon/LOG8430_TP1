package Application;
import java.io.File;
import java.util.ArrayList;

import Command.Command;
import Command.ICommand;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Controller {
	
	private boolean autoRun;
	private static Model model;
	private ArrayList<ICommand> commandsList; 
	
	public Controller(Model model){
		
		this.commandsList = new ArrayList<ICommand>();
		
		// get the commands folder
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/Command/imp");
		
		if (concreteCommandFolder.isDirectory()){

			// Create a loader to load the commands classes
			URLClassLoader classLoader;
			
			try {
				classLoader = new URLClassLoader(new URL[]{concreteCommandFolder.toURI().toURL()});
				// get the lists of commands
				File[] commands = concreteCommandFolder.listFiles();
				
				for(int i=0; i<commands.length; i++){
					String fileName = commands[i].getName(); 
					// cut the .class suffix in fileName
					String commandName = fileName.substring(0, fileName.lastIndexOf("."));
					// here need to be changed to load the correctly!  - Yan Xu
					Class<?> aCommandClass = classLoader.loadClass("Command.imp."+commandName);
					System.out.println(commandName + " has been loaded");
					// add the loaded command to the commandsList
					this.commandsList.add((Command)aCommandClass.newInstance());
				}
				
			} catch ( MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
		}
		
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
