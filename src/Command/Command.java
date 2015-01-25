package Command;

import java.io.File;

public class Command implements ICommand {
	
	protected boolean enable = false;
	
	@Override
	public boolean isEnable(){
		return this.enable;
	}

	@Override
	public void setEnable(File f) {
		//need to be override
	}

	@Override
	public Object execute(File f) {
		//need to be override
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

}
