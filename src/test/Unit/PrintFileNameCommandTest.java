package test.Unit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintFileNameCommand;

public class PrintFileNameCommandTest {

	@Test
	public void testSetEnable() {
		File eFile = new File(System.getProperty("user.dir") + "/src/Main.java");
		Command c = new PrintFileNameCommand();
		c.setEnable(eFile);
		assert(c.isEnable());
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setEnable(eFolder);
		assert(!c.isEnable());
	}
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Main.java");
		Command c = new PrintFileNameCommand();
		assertEquals(c.execute(eFile), "File Name is: Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		assertEquals(c.execute(eFolder), "Error");
	}

}
