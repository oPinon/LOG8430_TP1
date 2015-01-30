package oxz.application.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import oxz.application.command.Command;
import oxz.application.command.imp.PrintPathCommand;

public class PrintPathCommandTest {
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/oxz/application/Main.java");
		Command c = new PrintPathCommand();
		c.setFile(eFile);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "File path is: " + System.getProperty("user.dir") + "/src/oxz/application/Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setFile(eFolder);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "File path is: " + System.getProperty("user.dir") + "/src");
	}

}
