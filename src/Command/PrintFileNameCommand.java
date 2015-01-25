package Command;

import java.io.File;

public class PrintFileNameCommand extends Command {
	
	@Override
	public String getName() {
		return "PrintFileNameCommand";
	}
	
	@Override
	public void setEnable(File f) {
		if (f.isFile())
			this.enable = true;
		
	}

	@Override
	public Object execute(File f) {
		
		if (f.isFile())
			return "File Name is: " + f.getName();
		else
			return "Error";
		
	}
	
}
