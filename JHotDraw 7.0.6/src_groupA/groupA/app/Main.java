package groupA.app;

import javax.swing.*;
import org.jhotdraw.app.*;
import org.jhotdraw.samples.draw.DrawApplicationModel;

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
	        model.setName("JHotDraw PlasmaDraw");
	        model.setVersion("0.5");
	        model.setCopyright("Copyright Group A.");
	        model.setProjectClassName("groupA.app.EditorProject");
	        app.setModel(model);
	        app.launch(args);
	    }
	

}



