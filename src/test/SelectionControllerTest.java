package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Selection.SelectionController;

public class SelectionControllerTest {

	@Test
	public void setSelectedElement(){
		SelectionController s = new SelectionController();
		File f = new File(System.getProperty("user.dir") + "/src");
		s.setSelectedElement(System.getProperty("user.dir") + "/src");
		assertEquals(f, s.getSelectedElement());
	}
	
	@Test
	public void setRootPathTest() {
		SelectionController s = new SelectionController();
		s.setRootPath(System.getProperty("user.dir") + "/src");
		
		assertEquals("src", s.getSelectedElement().getName());
	}
	


}
