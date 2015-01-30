package oxz.application;
import java.io.File;
import java.util.ArrayList;
/*
 * for dynamic loading use -- Yan Xu
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import oxz.application.command.Command;
*/
import oxz.application.command.ICommand;
import oxz.application.command.imp.PrintFileNameCommand;
import oxz.application.command.imp.PrintFolderNameCommand;
import oxz.application.command.imp.PrintPathCommand;

public class Controller {
	
	private boolean autoRun;
	private Model model;
	private ArrayList<ICommand> commandsList; 
	
	public Controller(Model model){
		
		this.commandsList = new ArrayList<ICommand>();
		
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
		
		/*
		 * Yan 2015-01-29, remove the unfinished dynamic loading, not asked this week
		// get the commands folder
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		
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
					Class<?> aCommandClass = classLoader.loadClass("oxz.application.command.imp."+commandName);
					System.out.println(commandName + " has been loaded");
					// add the loaded command to the commandsList
					this.commandsList.add((Command)aCommandClass.newInstance());
				}
				
			} catch ( MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			
		}
		*/
		
		this.model = model;
		this.setRootPath(model.getRootPath());
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
	
	public void setSelectedElement(File file){
		
		this.model.setSelectedElement(file);
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
		return this.model.getSelectedElement();
	}
	
}
