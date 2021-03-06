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

import javafx.application.Platform;
import javafx.concurrent.Task;
import oxz.application.command.ICommand;
import oxz.application.view.View;

/**
 * This is the controller of the program
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public class Controller {

	private boolean autoRun;

	private String rootPath = ".";

	private ArrayList<ICommand> commandsList = new ArrayList<ICommand>();
	private View view;
	public final File concreteCommandFolder;

	public Controller(File concreteCommandFolder){
		
		this.concreteCommandFolder = concreteCommandFolder;

		loadCommands();
		this.setSelectedElement(new File("."));

		Thread dirWatcher = new Thread(new DirectoryWatcher(this));
		dirWatcher.start();

	}

	/**
	 * This function is used to load the commands' classes
	 */
	private void loadCommands() {

		this.commandsList.clear();

		if (concreteCommandFolder.isDirectory()) {

			ClassLoader classLoader = Controller.class.getClassLoader();

			File[] commands = concreteCommandFolder.listFiles();

			for (int i = 0; i < commands.length; i++) {

				String fileName = commands[i].getName();
				String className = fileName.substring(0,
						fileName.lastIndexOf("."));

				try {

					Class<?> commandClass = classLoader
							.loadClass("oxz.application.command.imp."
									+ className);
					Object aCommand = commandClass.newInstance();
					ICommand command = (ICommand) aCommand;
					if (aCommand instanceof ICommand) {
						this.commandsList.add(command);
					}

				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					System.err.println(fileName + " is not a valid command");
				}

			}

		}

		// update the view (the commands part may have changed)
		if (view != null) {
			view.updateCommands();
		}
	}

	/**
	 * If the autoRun attribute is set to true, the commands will be executed
	 * after the file is chosen (default is False)
	 * 
	 * @param flag set true to auto run commands
	 */
	public void setAutoRun(boolean flag) {
		this.autoRun = flag;
	}

	public boolean getAutoRun() {
		return this.autoRun;
	}

	/**
	 * This method returns a list of the commands in the Controller
	 * 
	 * @return commandsList
	 */
	public ArrayList<ICommand> getCommands() {
		return this.commandsList;
	}

	/**
	 * sets the rootPath attribute once the root path is set also sets the
	 * selected element to be the rootPath's file
	 * 
	 * @param path the root path of the file tree in view
	 */
	public void setRootPath(String path) {
		this.rootPath = path;
		this.setSelectedElement(new File(path));
	}

	public String getRootPath() {
		return this.rootPath;
	}

	/**
	 * Set a file or folder to be the selected element, pass it to all the
	 * commands if this.autoRun is true, execute all the commands. This function
	 * will call loadCommands to reload commands classes
	 * 
	 * @param file
	 *            the element to be used in command, can be a file or a folder
	 */
	public void setSelectedElement(File file) {

		System.out.println("Selected element is: " + file.getName());
		for (ICommand c : commandsList) { // clear all results since the file
											// has changed

			c.setFile(file); // used for load the file to the command, command
								// will be disabled if the input is not valid
		}
		if (autoRun) {
			for (ICommand c : commandsList) {
				c.execute();
			}
		}
	}

	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return this.view;
	}

	private class DirectoryWatcher extends Task<Object> {

		private Controller controller;

		public DirectoryWatcher(Controller controller) {
			this.controller = controller;
		}

		@Override
		public Object call() {

			try {
				WatchService watcher = FileSystems.getDefault()
						.newWatchService();
				Path dir = concreteCommandFolder.toPath();
				dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

				while (true) {

					// wait for key to be signaled
					WatchKey key;
					try {
						key = watcher.take();
					} catch (InterruptedException x) {
						return null;
					}

					for (WatchEvent<?> event : key.pollEvents()) {

						WatchEvent.Kind<?> kind = event.kind();

						// This key is registered only for ENTRY_CREATE events,
						// but an OVERFLOW event can occur regardless if events
						// are
						// lost or discarded.
						if (kind == OVERFLOW) {
							continue;
						}

					}

					Platform.runLater(new Runnable() { // runLater so it doesn't
														// conflict with the UI
						@Override
						public void run() {
							controller.loadCommands();
						}
					});

					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}

			} catch (IOException e) {
				System.err.println("couldn't watch folder "
						+ concreteCommandFolder.getName() + " for events");
				e.printStackTrace();
			}

			return null;
		}

	}

}
