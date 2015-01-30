package Command.imp;

import java.io.File;

import Command.Command;

public class PrintFolderNameCommand extends Command {
	
	@Override
	public String getName() {
		return "PrintFolderNameCommand";
	}
	
	@Override
	public void setFile(File file) {
		super.setFile(file);
		this.disabledProperty.set(!file.isDirectory());
	}

	@Override
	public void execute() {
		
		this.result = "Folder name is: " + this.file.getName();
		
		super.execute();
		
	}

}