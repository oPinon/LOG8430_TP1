package oxz.application.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import oxz.application.command.Command;
import oxz.application.command.imp.PrintFolderNameCommand;

public class PrintFolderNameCommandTest {

	@Test
	public void testGetName(){
		Command c = new PrintFolderNameCommand();
		assertEquals(c.getName(), "PrintFolderNameCommand");
	}
	
	@Test
	public void testExecute() {
		File eFile = new File(System.getProperty("user.dir")
				+ "/src/oxz/application/Main.java");
		Command c = new PrintFolderNameCommand();
		c.setFile(eFile);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "");

		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setFile(eFolder);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "Folder name is: src");
	}
}
