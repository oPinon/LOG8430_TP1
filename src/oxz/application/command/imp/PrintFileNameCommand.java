package oxz.application.command.imp;

import java.io.File;

import oxz.application.command.Command;


/**
 * The PrintFileNameCommand can only execute on file which is not a folder
 * Call setFile to set the target file (Receiver in Command Pattern)
 * If the file is a folder, the disabledProperty will be set False and the execute() method will do nothing
 * If the file is a file (not a folder), the execute() method will set the command result to its resultString attribute
 */
public class PrintFileNameCommand extends Command {
	
	@Override
	public String getName() {
		return "PrintFileNameCommand";
	}
	
	
	
	@Override
	public void setFile(File file) {
		super.setFile(file);
		this.disabledProperty.set(!file.isFile());
	}
	
	@Override
	public void execute() {
		
		if(!this.disabledProperty.get()) {
			this.result = "File name is: " + this.file.getName();
			this.resultString.set(result.toString());
			
		}
	}
	
}
