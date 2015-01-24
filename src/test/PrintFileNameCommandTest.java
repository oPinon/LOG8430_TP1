package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Command.PrintFileNameCommand;
import Selection.SelectionController;

public class PrintFileNameCommandTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
		//System.out.println(System.getProperty("user.dir"));
	    System.setOut(new PrintStream(outContent));
	}

	
	@Test
	public void test() {
		SelectionController s = new SelectionController();
		s.setSelectedPath(System.getProperty("user.dir") + "/src/test/PrintFileNameCommandTest.java");
		PrintFileNameCommand c = new PrintFileNameCommand();
		
		s.accept(c);
		
		assertEquals("File Name is: PrintFileNameCommandTest.java\n", outContent.toString());
		
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
}
