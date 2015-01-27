package Command;

import java.io.File;

public interface ICommand {
	
	public String getName();
	
	public void clear();
	public String displayResult();
	
	public void execute(File f);
}
