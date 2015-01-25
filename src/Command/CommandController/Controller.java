package Command.CommandController;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Application.SubViews.CommandView;
import Command.ICommand;
import Command.PrintFileNameCommand;
import Command.PrintFolderNameCommand;
import Command.PrintPathCommand;

public class Controller implements Observer{
	
	protected ArrayList<ICommand> commandsList; 
	protected boolean autoRun;
	protected File selectedElement;
	protected CommandView commandView;
	
	public Controller(){
		this.commandsList = new ArrayList<ICommand>();
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
		
		this.commandView = new CommandView(this.commandsList, this);
	}
	
	public CommandView getCommandView(){
		return this.commandView;
	}
	
	public boolean addCommand(ICommand command){
		return this.commandsList.add(command);
	}
	
	public boolean removeCommand(ICommand command){
		return this.commandsList.remove(command);
	}
	
	public ArrayList<ICommand> getCommands(){
		return this.commandsList;
	}
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public boolean getAutoRun(){
		return this.autoRun;
	}
	
	public File getSelectedElement(){
		return this.selectedElement;
	}
	
	//It will be notified if a new element is selected in SelectionController
	@Override
	public void update(Observable o, Object selectedElement) {
		if (selectedElement instanceof File){
			
			this.selectedElement = (File)selectedElement;
			System.out.println("CommandController get the selected element " + this.selectedElement.getName());
			System.out.println("In commandController, the autorun is " + this.autoRun);
			
			for(ICommand c : this.commandsList){
				c.setEnable((File)selectedElement);
			}
			
			if(this.autoRun){
				for(ICommand c : this.commandsList){
					System.out.println(c.execute((File)selectedElement));
				}
			}
			
		}
	}
}
