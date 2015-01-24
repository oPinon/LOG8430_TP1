package Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CommandController implements Observer{
	
	protected ArrayList<ICommand> commandsList; 
	protected boolean autoRun;
	protected File selectedElement;
	
	public CommandController(){
		this.commandsList = new ArrayList<ICommand>();
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
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
			
			for(ICommand c : this.commandsList){
				c.setEnable((File)selectedElement);
			}
			
			if(autoRun){
				for(ICommand c : this.commandsList){
					c.execute((File)selectedElement);
				}
			}
			
		}
	}
}
