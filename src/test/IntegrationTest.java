package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.CommandController;
import Command.ICommand;
import Selection.SelectionController;

public class IntegrationTest {

	@Test
	public void test() {
		SelectionController s = new SelectionController();
		CommandController c = new CommandController();
		s.addObserver(c);
		
		s.setRootPath(System.getProperty("user.dir") + "/src");
		
		for(ICommand command : c.getCommands()){
			System.out.println(command.execute(c.getSelectedElement()));
		}
		
	}

}
