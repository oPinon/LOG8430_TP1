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
	 * @return A string property to observe for the command's result
	 */
	public StringProperty resultStringProperty();

	/**
	 * @return A boolean property to observe for the command's enabled/disabled state
	 */
	public BooleanProperty disabledProperty();

	/**
	 * This method clears the result
	 */
	public void clear();

	/**
	 * @param f Set the target file (The Receiver in Command Pattern) to the command
	 */
	public void setFile(File f);

	/**
	 * Use to execute the command (on its target file)
	 */
	public void execute();

}
