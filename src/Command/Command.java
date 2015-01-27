package Command;

import java.io.File;

public class Command implements ICommand {
	
	protected boolean enable = false;
	protected Object result;

	@Override
	public void execute(File f) {
		//need to be override
		return;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public void clear() {
		
	}

	@Override
	public String displayResult() {
		return result.toString();
	}

}
