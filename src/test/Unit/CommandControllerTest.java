package test.Unit;

import static org.junit.Assert.*;

import org.junit.Test;

import Command.Command;
import Command.CommandController.Controller;

public class CommandControllerTest {

	@Test
	public void autoRunFlagtest() {
		Controller c = new Controller();
		c.setAutoRun(true);
		assertEquals(true, c.getAutoRun());
		c.setAutoRun(false);
		assertEquals(false, c.getAutoRun());
	}
	
	@Test
	public void addAndRemoveCommandTest(){
		Controller c = new Controller();
		Command command = new Command();
		c.addCommand(command);
		assert(c.removeCommand(command));
	}

}
