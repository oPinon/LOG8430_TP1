package test;

import org.junit.Test;

import Application.Controller;

public class ControllerTest {

	@Test
	public void test() {
		Controller controller = new Controller(System.getProperty("user.dir") + "/src");
		//controller.setRootPath(System.getProperty("user.dir") + "/src");
		//System.out.println(controller.getRootPath());
	}

}
