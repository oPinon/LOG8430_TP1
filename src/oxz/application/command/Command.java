package oxz.application.command;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

/**
 * The abstract class Command, it implement some general method, as well as some
 * general attributes
 * 
 * @author Yan Xu, Olivier Pinon, Chunxia Zhang
 * @version 1.0
 */

public abstract class Command implements ICommand {

	protected Object result;
	final protected StringPropertyBase resultString = new SimpleStringProperty();
	final protected SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty();
	protected File file;

	/**
	 * @param file
	 *            : sets the file which the command will be applied on
	 * 
	 *            Sets the file and clears the result (because it is no longer
	 *            valid)
	 */
	@Override
	public void setFile(File file) {
		this.file = file;
		this.clear(); // the file has changed => hence the result is invalidated
	}

	@Override
	public String getName() {
		return "";
	}

	/**
	 * Clears the command's result
	 */
	@Override
	public void clear() {
		this.resultString.set("");
	}

	/**
	 * A string property to watch on to get the command's result
	 */
	@Override
	public StringProperty resultStringProperty() {
		return this.resultString;
	}

	/**
	 * A boolean property to watch on to get the command's enabled/disabled
	 * state
	 */
	@Override
	public BooleanProperty disabledProperty() {
		return this.disabledProperty;
	}
}
