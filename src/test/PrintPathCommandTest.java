package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import oxz.application.command.Command;
import oxz.application.command.imp.PrintPathCommand;

public class PrintPathCommandTest {
	
	@Test
	public void testExecute(){
		File eFile = new File(System.getProperty("user.dir") + "/src/Application/Main.java");
		Command c = new PrintPathCommand();
		c.setFile(eFile);
		c.execute();
		assertEquals(c.resultStringProperty().get(), System.getProperty("user.dir") + "/src/Application/Main.java");
		
		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setFile(eFolder);
		c.execute();
		assertEquals(c.resultStringProperty().get(), System.getProperty("user.dir") + "/src");
	}

}
