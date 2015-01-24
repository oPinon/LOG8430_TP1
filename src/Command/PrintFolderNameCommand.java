package Command;

import java.io.File;

public class PrintFolderNameCommand extends Command {
	
	@Override
	public void setEnable(File f) {
		if (f.isDirectory())
			this.enable = true;
		
	}

	@Override
	public Object execute(File f) {
		
		if (f.isDirectory())
			return "Folder Name is: " + f.getName();
		else
			return "Error";
		
	}

}