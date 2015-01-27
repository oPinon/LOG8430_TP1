package Command;

import java.io.File;

public class PrintFolderNameCommand extends Command {
	
	@Override
	public String getName() {
		return "PrintFolderNameCommand";
	}

	@Override
	public void execute(File f) {
		
		if (f.isDirectory())
			this.result = "Folder Name is: " + f.getName();
		else
			this.result = "Error";
		
	}

}