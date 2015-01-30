package oxz.application.command.imp;

import java.io.File;

import oxz.application.command.Command;

public class PrintPathCommand extends Command {

	@Override
	public String getName() {
		return "PrintPathCommand";
	}

	@Override
	public void execute() {
		
		this.result = "File path is: " + this.file.getAbsolutePath();
		
		super.execute();
		
	}

}