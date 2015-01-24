package Selection;

import Command.IVisitor;

public interface IElement {
	public void accept(IVisitor visitor);
}
