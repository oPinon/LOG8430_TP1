package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintFolderNameCommand;

public class PrintFolderNameCommandTest {

	@Test
	public void testSetEnable() {
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintFolderNameCommand();
		c.setEnable(eFile);
		assert(!c.isEnable());
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setEnable(eFolder);
		assert(c.isEnable());
	}
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintFolderNameCommand();
		assertEquals(c.execute(eFile), "Error");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		assertEquals(c.execute(eFolder), "Folder Name is: src");
	}
}
