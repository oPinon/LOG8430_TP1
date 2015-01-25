package test;

import org.junit.Test;

import Command.ICommand;
import Command.CommandController.Controller;
import Selection.SelectionController.SelectionController;

public class IntegrationTest {

	@Test
	public void test() {
		SelectionController s = new SelectionController();
		Controller c = new Controller();
		s.addObserver(c);
		
		s.setRootPath(System.getProperty("user.dir") + "/src");
		
		for(ICommand command : c.getCommands()){
			System.out.println(command.execute(c.getSelectedElement()));
		}
		
	}

}
