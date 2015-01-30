package oxz.application.test;

import java.io.File;

import org.junit.Test;

import oxz.application.Model;

public class ModelTest {


	@Test
	public void setSelectElement() {
		
		Model model = new Model();
		
		File f = new File(".");
		
		model.setSelectedElement(f);
		
		assert(model.getSelectedElement() == f);
	}
	
	@Test
	public void setRootPath() {
		
		Model model = new Model();
		
		File f = new File("~");
		
		model.setRootPath("~");
		
		assert(model.getRootPath() == "~");
		assert(model.getSelectedElement() == f);
	}
}
