package Command;

import java.io.File;

import Selection.IElement;
import Selection.SelectionController;

public class PrintFolderNameCommand implements ICommand {

	@Override
	public void visit(IElement element) {
		throw new IllegalStateException(element.getClass().getName());		
	}

	@Override
	public Object visit(SelectionController selectionController) {

		File f = new File(selectionController.getSelectedPath());
		
		if (f.isDirectory() == true)
			return "Folder Name is: " + f.getName();
		else
			return "Cannot get folder name";
	}
}