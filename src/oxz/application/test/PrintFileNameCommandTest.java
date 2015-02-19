package oxz.application.test;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Test;

import oxz.application.command.Command;
import oxz.application.command.imp.PrintFileNameCommand;

public class PrintFileNameCommandTest {

	@Test
	public void testGetName(){
		Command c = new PrintFileNameCommand();
		assertEquals(c.getName(), "PrintFileNameCommand");
	}
	
	@Test
	public void testSetFile() throws IllegalArgumentException, IllegalAccessException{
		File eFile = new File(System.getProperty("user.dir")
				+ "/src/oxz/application/Main.java");
		Command c = new PrintFileNameCommand();
		c.setFile(eFile);
		
		Field [] fields = Command.class.getDeclaredFields();
		
		boolean fieldFound = false;
		
		for (Field f : fields){
			if (f.getName().equals("file")){
				f.setAccessible(true);
				
				File privateFile = (File)(f.get(c));
				assertEquals (privateFile, eFile);
				fieldFound = true;
				break;
			}
		}
		
		assert(fieldFound);
	}
	
	@Test
	public void testExecuteAndResultStringProperty() {
		File eFile = new File(System.getProperty("user.dir")
				+ "/src/oxz/application/Main.java");
		Command c = new PrintFileNameCommand();
		c.setFile(eFile);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "File name is: Main.java");

		File eFolder = new File(System.getProperty("user.dir") + "/src");
		c.setFile(eFolder);
		c.execute();
		assertEquals(c.resultStringProperty().get(), "");
	}
	
	
	@Test
	public void testClear() {
		File eFile = new File(System.getProperty("user.dir")
				+ "/src/oxz/application/Main.java");
		Command c = new PrintFileNameCommand();
		c.setFile(eFile);
		c.execute();		
		c.clear();
		
		assertEquals(c.resultStringProperty().get(), "");
	}
}
