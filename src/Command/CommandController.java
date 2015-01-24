package Command;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CommandController implements Observer{
	
	protected ArrayList<ICommand> commandsList;
	
	CommandController(){
		this.commandsList.add(new PrintFileNameCommand());
		this.commandsList.add(new PrintFolderNameCommand());
		this.commandsList.add(new PrintPathCommand());
	}
	
	public void addCommand(ICommand command){
		this.commandsList.add(command);
	}
	
	public void removeCommand(ICommand command){
		this.commandsList.remove(command);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
