package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintFileNameCommand;

public class PrintFileNameCommandTest {

	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintFileNameCommand();
		c.execute(eFile);
		assertEquals(c.displayResult(), "File Name is: Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.execute(eFolder);
		assertEquals(c.displayResult(), "Error");
	}

}
