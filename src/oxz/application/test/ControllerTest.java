package oxz.application.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import oxz.application.Controller;
import oxz.application.command.imp.PrintFileNameCommand;

public class ControllerTest {

	Model model;
	Controller controller;
	
	@Before 
	public void initialize() {
      this.controller = new Controller();
    }
	
	/* Can't run this text because buttons can't be initialized, unknown reason
	 * need to be fixed
	 * 
	@Test
	public void testCommandsList() {
		
		assert(this.controller.getCommands().size() == 3);
		this.controller.getCommands().add(new PrintFileNameCommand());
		assert(this.controller.getCommands().size() == 4);
		
	}
	
	@Test
	public void testSetRootPath(){
		this.controller.setRootPath("/");
		assert(this.controller.getRootPath() == "/");
		assert(this.controller.getSelectedElement() == new File("/"));
	}
	
	@Test
	public void testSetSelectedElement(){
		File f = new File("~");
		this.controller.setSelectedElement(f);
		assert(this.controller.getSelectedElement() == f);
	}
	*/
}
