package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Command.PrintFolderNameCommand;
import Command.PrintPathCommand;
import Selection.SelectionController;

public class PrintPathCommandTest {

	@Test
	public void test() {
		SelectionController s = new SelectionController();
		s.setSelectedPath(System.getProperty("user.dir") + "/src/test/");
		PrintPathCommand c = new PrintPathCommand();
				
		assertEquals(System.getProperty("user.dir") + "/src/test", s.accept(c));
		
	}

}
