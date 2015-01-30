package oxz.application.command.imp;

import java.io.File;

import oxz.application.command.Command;

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
		
		this.result = "File name is: " + this.file.getName();
		
		super.execute();
		
	}
	
}
