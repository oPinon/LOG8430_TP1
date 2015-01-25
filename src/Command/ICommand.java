package Command;

import java.io.File;

public interface ICommand {
	
	public String getName();
	
	public void setEnable(File f);
	public boolean isEnable();
	
	public Object execute(File f);
}
