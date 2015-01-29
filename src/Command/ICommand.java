package Command;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface ICommand {
	
	public String getName();
	
	public StringProperty resultStringProperty();
	public BooleanProperty disabledProperty();
	
	public void clear();
	
	public void setFile(File f);
	
	public void execute();
	
}
