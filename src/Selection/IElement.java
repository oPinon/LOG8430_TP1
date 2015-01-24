package Selection;

import Command.IVisitor;

public interface IElement {
	public Object accept(IVisitor visitor);
}
