package Command;

import java.io.File;

import Selection.IElement;
import Selection.SelectionController;

public class PrintPathCommand implements ICommand {

	@Override
	public void visit(IElement element) {
		throw new IllegalStateException(element.getClass().getName());		
	}

	@Override
	public Object visit(SelectionController selectionController) {

		File f = new File(selectionController.getSelectedPath());
		
		if (f.exists() == true)
			return f.getAbsolutePath();
		else
			return "Cannot get absolute path";
		
	}

}