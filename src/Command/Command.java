package Command;

public abstract class Command implements ICommand {
	
	protected boolean enable = false;
	
	@Override
	public boolean isEnable(){
		return this.enable;
	}

}
