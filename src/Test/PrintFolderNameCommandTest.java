package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Command.Command;
import Command.PrintFolderNameCommand;

public class PrintFolderNameCommandTest {

	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintFolderNameCommand();
		c.execute(eFile);
		//assertEquals(c.displayResult(), "Error");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.execute(eFolder);
		//assertEquals(c.displayResult(), "Folder Name is: src");
	}
}
