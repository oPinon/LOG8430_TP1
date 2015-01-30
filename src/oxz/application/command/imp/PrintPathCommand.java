package oxz.application.command.imp;

import oxz.application.command.Command;

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