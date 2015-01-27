package Command;

import java.io.File;

import javafx.beans.property.StringProperty;

public interface ICommand {
	
	public String getName();
	
	public StringProperty resultStringProperty();
	
	public void clear();
	
	public void execute(File f);
	
}
