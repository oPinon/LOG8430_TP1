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

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	
	@Test
	public void test() {
		SelectionController s = new SelectionController();
		s.setSelectedPath(System.getProperty("user.dir") + "/src/test/");
		PrintPathCommand c = new PrintPathCommand();
		
		s.accept(c);
		
		assertEquals(System.getProperty("user.dir") + "/src/test\n", outContent.toString());
		
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
