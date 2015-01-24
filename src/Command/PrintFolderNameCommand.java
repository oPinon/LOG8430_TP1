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
	public void visit(SelectionController selectionController) {

		File f = new File(selectionController.getSelectedPath());
		
		
		try {
			if (f.exists() == false){
				throw new Exception("File doesn't exists");
			} else if (f.isDirectory() == false){
				throw new Exception("The path doesn't point to a folder");
			} else {
				System.out.println("Folder Name is" + f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}