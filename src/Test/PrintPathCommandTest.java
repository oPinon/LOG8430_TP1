package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintPathCommand;

public class PrintPathCommandTest {
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintPathCommand();
		c.execute(eFile);
		assertEquals(c.displayResult(), System.getProperty("user.dir") + "/src/Application/Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.execute(eFolder);
		assertEquals(c.displayResult(), System.getProperty("user.dir") + "/src");
	}

}
