package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintPathCommand;

public class PrintPathCommandTest {

	@Test
	public void testSetEnable() {
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintPathCommand();
		c.setEnable(eFile);
		assert(c.isEnable());
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setEnable(eFolder);
		assert(c.isEnable());
	}
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintPathCommand();
		assertEquals(c.execute(eFile), System.getProperty("user.dir") + "/src/Application/Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		assertEquals(c.execute(eFolder), System.getProperty("user.dir") + "/src");
	}

}
