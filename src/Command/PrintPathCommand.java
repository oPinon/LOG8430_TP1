package Command;

import java.io.File;

public class PrintPathCommand extends Command {

	@Override
	public String getName() {
		return "PrintPathCommand";
	}

	@Override
	public void execute(File f) {
		
		if (f.isFile() || f.isDirectory())
			this.result = f.getAbsolutePath();
		else
			this.result = "Error";
		
	}

}