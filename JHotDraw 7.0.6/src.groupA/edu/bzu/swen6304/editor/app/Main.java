package edu.bzu.swen6304.editor.app;

import org.jhotdraw.app.Application;
import org.jhotdraw.app.DefaultOSXApplication;
import org.jhotdraw.app.DefaultSDIApplication;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
	 
	        Application app;
	        String os = System.getProperty("os.name").toLowerCase();
	        if (os.startsWith("mac")) {
	            app = new DefaultOSXApplication();
	        } else if (os.startsWith("win")) {
	          //  app = new DefaultMDIApplication();
	            app = new DefaultSDIApplication();
	        } else {
	            app = new DefaultSDIApplication();
	        }
	        
	        
	        EditorApplicationModel model = new EditorApplicationModel();
	        model.setName("JHotDraw");
	        model.setVersion("0.5");
	        model.setCopyright("Copyright Group A.");
	        model.setProjectClassName("edu.bzu.swen6304.editor.app.EditorProject");
	        app.setModel(model);
	        app.launch(args);
	    }
	

}



