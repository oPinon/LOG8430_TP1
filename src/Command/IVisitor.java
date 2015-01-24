package Command;

import Selection.IElement;
import Selection.SelectionController;

public interface IVisitor {
	public void visit(IElement element);
	public Object visit(SelectionController selectionController);
}
