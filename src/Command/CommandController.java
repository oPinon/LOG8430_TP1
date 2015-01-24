package Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Selection.SelectionController;

public class CommandController implements Observer{
	
	protected ArrayList<ICommand> commandsList;
	protected boolean autoRun;
	protected SelectionController selectionController;
	
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
	
	public void setAutoRun(boolean flag){
		this.autoRun = flag;
	}
	
	public boolean getAutoRun(){
		return this.autoRun;
	}
	
	@Override
	public void update(Observable o, Object selectedElement) {
		if (selectedElement instanceof File){
			
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
