package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Command.PrintFolderNameCommand;
import Selection.SelectionController;

public class PrintFolderNameCommandTest {
	
	@Test
	public void test() {
		SelectionController s = new SelectionController();
		s.setSelectedPath(System.getProperty("user.dir") + "/src/test/");
		PrintFolderNameCommand c = new PrintFolderNameCommand();
				
		assertEquals("Folder Name is: test", s.accept(c));
		
	}

}
