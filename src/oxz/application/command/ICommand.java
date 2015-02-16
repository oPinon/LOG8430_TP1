package oxz.application.command;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * The ICommand Interface
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public interface ICommand {
	
	public String getName();
	
	/**
	 * It is use to double the result of command to view
	 */
	public StringProperty resultStringProperty();
	
	/**
	 * It is use to double the disabled status of command to view
	 */
	public BooleanProperty disabledProperty();
	
	/**
	 * This method use to clear its resultStringProperty()
	 */
	public void clear();
	
	/**
	 * Set the target file (The Receiver in Command Pattern) to the command
	 */
	public void setFile(File f);
	
	/**
	 * Use to execute the command (on its target file)
	 */
	public void execute();
	
}
