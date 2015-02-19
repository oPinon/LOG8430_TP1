package oxz.application.test;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Test;

import oxz.application.Controller;
import oxz.application.command.Command;
import oxz.application.command.ICommand;

public class ControllerTest {

	@Test
	public void testControllerSetAndGetAutoRun() {
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		Controller controller = new Controller(concreteCommandFolder);
		
		controller.setAutoRun(true);
		assert(controller.getAutoRun());		
	}
	
	
	@Test
	public void testSetAndGetRootPath(){
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		Controller controller = new Controller(concreteCommandFolder);
		
		controller.setRootPath(".");
		assertEquals(".", controller.getRootPath());
	}
	
	@Test
	public void testSetSelectedElement() throws IllegalArgumentException, IllegalAccessException{
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		Controller controller = new Controller(concreteCommandFolder);
		
		File file = new File(".");
		controller.setSelectedElement(file);
		
		Field [] fields = Command.class.getDeclaredFields();
		
		boolean fieldFound = false;
		
		for (Field f : fields){
			if (f.getName().equals("file")){
				f.setAccessible(true);
				
				File privateFile = (File)(f.get(controller.getCommands().get(0)));
				assertEquals (privateFile, file);
				fieldFound = true;
				break;
			}
		}
		
		assert(fieldFound);
	}
	
	@Test
	public void testAutoRun(){
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		Controller controller = new Controller(concreteCommandFolder);
		
		for (ICommand c : controller.getCommands()){
			if (c.getName().equals("PrintPathCommand")){
				assertEquals(c.resultStringProperty().get(), "");
				break;
			}
		}
		
		File file = new File(".");
		controller.setAutoRun(true);
		controller.setSelectedElement(file);
		
		for (ICommand c : controller.getCommands()){
			if (c.getName().equals("PrintPathCommand")){
				assertNotEquals(c.resultStringProperty().get(), "");
				break;
			}
		}
		
	}
	
	@Test
	public void testLoadCommands(){
		
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		
		int classNumber = 0;
		for (File f : concreteCommandFolder.listFiles()){
			if (f.getName().substring(f.getName().lastIndexOf(".")+1).equals("class")){
				classNumber++;
			}
			System.out.println(f.getName().substring(f.getName().lastIndexOf(".")));
		}
		
		Controller controller = new Controller(concreteCommandFolder);
		assertEquals(classNumber, controller.getCommands().size());
		
	}
	
	/*
	@Test
	public void testLoadCommandsAutomatically() {
		File concreteCommandFolder = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp");
		
		int classNumber = 0;
		for (File f : concreteCommandFolder.listFiles()){
			if (f.getName().substring(f.getName().lastIndexOf(".")+1).equals("class")){
				classNumber++;
			}
			System.out.println(f.getName().substring(f.getName().lastIndexOf(".")));
		}
		
		Controller controller = new Controller(concreteCommandFolder);
		assertEquals(classNumber, controller.getCommands().size());
		
		File fileToBeRenamed = concreteCommandFolder.listFiles()[0];
		String fileName = fileToBeRenamed.getName();
		fileToBeRenamed.renameTo(new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp/temp"));

		assertEquals(classNumber-1, controller.getCommands().size());
		
		File recoverFile = new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp/temp");
		recoverFile.renameTo(new File(System.getProperty("user.dir") + "/bin/oxz/application/command/imp/" + fileName));
	}
	*/
}
