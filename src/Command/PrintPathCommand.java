package Command;

import java.io.File;

public class PrintPathCommand extends Command {

	@Override
	public String getName() {
		return "PrintPathCommand";
	}
		
	@Override
	public void setEnable(File f) {
		if (f.isDirectory() || f.isFile())
			this.enable = true;
		
	}

	@Override
	public Object execute(File f) {
		
		if (f.isFile() || f.isDirectory())
			return f.getAbsolutePath();
		else
			return "Error";
		
	}

}