package Command;

import java.io.File;

public class PrintFileNameCommand extends Command {
	
	@Override
	public String getName() {
		return "PrintFileNameCommand";
	}

	@Override
	public void execute(File f) {
		
		if (f.isFile())
			this.result = "File Name is: " + f.getName();
		else
			this.result = "Error";
		
		super.execute(f);
		
	}
	
}
