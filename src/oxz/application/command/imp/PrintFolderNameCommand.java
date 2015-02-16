package oxz.application.command.imp;

import java.io.File;

import oxz.application.command.Command;


/**
 * The PrintFolderNameCommand can only execute on folder
 * Call setFile to set the target file (Receiver in Command Pattern)
 * If the file is not a folder, the disabledProperty will be set False and the execute() method will do nothing
 * If the file is a folder, the execute() method will set the command result to its resultString attribute
 */
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
		
		if(!this.disabledProperty.get()) {
			this.result = "Folder name is: " + this.file.getName();
			this.resultString.set(result.toString());
			
		}
	}

}