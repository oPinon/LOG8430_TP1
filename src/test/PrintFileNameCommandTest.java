package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Command.PrintFileNameCommand;
import Selection.SelectionController;

public class PrintFileNameCommandTest {	

	
	@Test
	public void test() {
		SelectionController s = new SelectionController();
		s.setSelectedPath(System.getProperty("user.dir") + "/src/test/PrintFileNameCommandTest.java");
		PrintFileNameCommand c = new PrintFileNameCommand();
		
		assertEquals("File Name is: PrintFileNameCommandTest.java", s.accept(c));
		
	}
	

}
