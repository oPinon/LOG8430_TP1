package oxz.application.command.imp;

import oxz.application.command.Command;

/**
 * The PrintPathCommand can execute on folder or a file
 * Call setFile to set the target file (Receiver in Command Pattern)
 * Call execute() method if the file is set. The result will set the command result to its resultString attribute
 */
public class PrintPathCommand extends Command {

	@Override
	public String getName() {
		return "PrintPathCommand";
	}

	@Override
	public void execute() {
		
		if(!this.disabledProperty.get()) {
			this.result = "File path is: " + this.file.getAbsolutePath();
			this.resultString.set(result.toString());
			
		}

	}

}