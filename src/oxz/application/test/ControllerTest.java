package oxz.application.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import oxz.application.Controller;
import oxz.application.Model;
import oxz.application.command.imp.PrintFileNameCommand;

public class ControllerTest {

	Model model;
	Controller controller;
	
	@Before 
	public void initialize() {
      this.model = new Model();
      this.controller = new Controller(model);
    }
	
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

}
