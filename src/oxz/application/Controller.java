package oxz.application;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

import java.util.ArrayList;

import javafx.concurrent.Task;
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
	public final File concreteCommandFolder;
	
	public Controller(Model model){
		
		// Original method to load the commands' classes
		// replaced by dynamic loading --Yan 2015-02-01
		// this.commandsList.add(new PrintPathCommand());
		// this.commandsList.add(new PrintFileNameCommand());
		// this.commandsList.add(new PrintFolderNameCommand());
		
		//loadCommands(); loadClass when set the root path
		
		concreteCommandFolder = new File(System.getProperty("user.dir") + "/src/oxz/application/command/imp");
		this.model = model;
		this.setRootPath(model.getRootPath()); 
		this.view = new View(this);
		
		Thread dirWatcher = new Thread(new DirectoryWatcher(this));
		dirWatcher.start();
		
	}
	
	/**
	 * This function used for load the commands' classes
	 */
	private void loadCommands(){
		
		System.out.println("reload commands");
		
		this.commandsList.clear();
		
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
		if (view != null){
			view.updateCommands();
		}
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public boolean getAutoRun(){
		return this.autoRun;
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
	
	private class DirectoryWatcher extends Task {
		
		private Controller controller;
		public DirectoryWatcher(Controller controller) {
			this.controller = controller;
		}
		
		@Override
		public Object call() {
			
			try {
				WatchService watcher = FileSystems.getDefault().newWatchService();
				Path dir = concreteCommandFolder.toPath();
				dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
				
				while(true) {

				    //wait for key to be signaled
				    WatchKey key;
				    try {
				    	System.out.println("wating key");
				        key = watcher.take();
				    } catch (InterruptedException x) {
				        return null;
				    }

				    for (WatchEvent<?> event: key.pollEvents()) {
				    	
				        WatchEvent.Kind<?> kind = event.kind();

				        //This key is registered only for ENTRY_CREATE events,
				        //but an OVERFLOW event can occur regardless if events are
				        //lost or discarded.
				        if (kind == OVERFLOW) {
				            continue;
				        }

				        System.out.println(kind.toString());
				        
				    }
				    Thread.sleep(500); // let the system time to update files
				    controller.loadCommands();

				    //Reset the key -- this step is critical if you want to receive
				    //further watch events. If the key is no longer valid, the directory
				    //is inaccessible so exit the loop.
				    boolean valid = key.reset();
				    if (!valid) {
				        break;
				    }
				}
				
				
			} catch (IOException | InterruptedException e) {
				System.err.println("couldn't watch folder "+concreteCommandFolder.getName()+" for events");
				e.printStackTrace();
			}

			System.out.println("finished");
			return null;
		}
		
	}
	
}
