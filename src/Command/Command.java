package Command;

import java.io.File;

import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

public class Command implements ICommand {
	
	protected boolean enable = false;
	protected Object result;
	private StringPropertyBase resultString = new StringPropertyBase() {
		
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getBean() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	@Override
	public void execute(File f) {
		this.resultString.set(result.toString());
		return;
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

}
