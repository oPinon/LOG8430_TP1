package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Command.Command;
import Command.CommandController;

public class CommandControllerTest {

	@Test
	public void autoRunFlagtest() {
		CommandController c = new CommandController();
		c.setAutoRun(true);
		assertEquals(true, c.getAutoRun());
		c.setAutoRun(false);
		assertEquals(false, c.getAutoRun());
	}
	
	@Test
	public void addAndRemoveCommandTest(){
		CommandController c = new CommandController();
		Command command = new Command();
		c.addCommand(command);
		assert(c.removeCommand(command));
	}

}
