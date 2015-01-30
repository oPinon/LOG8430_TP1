package oxz.application.command;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.beans.value.ObservableBooleanValue;

public class Command implements ICommand {
	
	protected Object result;
	final protected StringPropertyBase resultString = new SimpleStringProperty();
	final protected SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty();
	protected File file;
	
	@Override
	public void setFile(File file) {
		this.file = file;
		this.clear(); // the file has changed => hence the result is invalidated
	}

	@Override
	public void execute() {
		if(!this.disabledProperty.get()) {
			this.resultString.set(result.toString());
		}
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public void clear() {
		this.resultString.set("");
	}

	@Override
	public StringProperty resultStringProperty() {
		return this.resultString;
	}

	@Override
	public BooleanProperty disabledProperty() {
		return this.disabledProperty;
	}
}
