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
	public void visit(SelectionController selectionController) {

		File f = new File(selectionController.getSelectedPath());
		
		
		try {
			if (f.exists() == false){
				throw new Exception("Path doesn't exists");
			} else {
				System.out.println(f.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}