package oxz.application;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import oxz.application.command.ICommand;
import oxz.application.view.CommandPartView;
import oxz.application.view.View;


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
	private View view;
	
	public Controller(Model model){
		
		// Original method to load the commands' classes
		// replaced by dynamic loading --Yan 2015-02-01
		// this.commandsList.add(new PrintPathCommand());
		// this.commandsList.add(new PrintFileNameCommand());
		// this.commandsList.add(new PrintFolderNameCommand());
		
		//loadCommands(); loadClass when set the root path
		
		this.model = model;
		this.setRootPath(model.getRootPath()); 
		this.view = new View(this);
	}
	
	/**
	 * This function used for load the commands' classes
	 */
	private void loadCommands(){
		
		System.out.println("reload commands");
		
		this.commandsList.clear();
		
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/src/oxz/application/command/imp");
		
		if (concreteCommandFolder.isDirectory()){
			
			ClassLoader classLoader = Controller.class.getClassLoader();
			
			File[] commands = concreteCommandFolder.listFiles();
			
			for(int i=0; i<commands.length; i++){
				String fileName = commands[i].getName();
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				
				try {
					System.out.println("load class: " + className);
					
					Class<?> commandClass = classLoader.loadClass("oxz.application.command.imp."+className);
					ICommand aCommand = (ICommand) commandClass.newInstance();
					this.commandsList.add(aCommand);
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				} 
			
			}
			
		}
		
		//regenerate the view
		if (this.view != null){
			this.view.getChildren().remove(2);
			CommandPartView commandPartView = new CommandPartView(this);
			HBox.setHgrow(commandPartView, Priority.ALWAYS);
			this.view.getChildren().add(2, commandPartView);
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
		System.out.println("Selected element is: " + file.getName());
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
	
	public void setView(View view){
		this.view = view;
	}
	
	public View getView(){
		return this.view;
	}
	
}
