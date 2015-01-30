package oxz.application.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import oxz.application.Model;

public class ModelTest {

	@Test
	public void testTheSingleton() {
		
		Model model1 = Model.getInstance("/");
		Model model2 = Model.getInstance("/usr");
		
		assert(model1 == model2);
	}

	@Test
	public void setSelectElement() {
		
		Model model = Model.getInstance("/");
		
		File f = new File(".");
		
		model.setSelectedElement(f);
		
		assert(model.getSelectedElement() == f);
	}
	
	@Test
	public void setRootPath() {
		
		Model model = Model.getInstance("/");
		
		File f = new File("~");
		
		model.setRootPath("~");
		
		assert(model.getRootPath() == "~");
		assert(model.getSelectedElement() == f);
	}
}
