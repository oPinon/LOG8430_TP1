package Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import oxz.application.command.Command;
import oxz.application.command.imp.PrintFolderNameCommand;

public class PrintFolderNameCommandTest {

	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintFolderNameCommand();
		c.setFile(eFile);
		c.execute();
		//assertEquals(c.displayResult(), "Error");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setFile(eFolder);
		c.execute();
		//assertEquals(c.displayResult(), "Folder Name is: src");
	}
}
