package groupA.app;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jhotdraw.app.*;
import org.jhotdraw.app.action.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.*;
import org.jhotdraw.samples.net.figures.*;
import org.jhotdraw.util.*;
import groupA.figure.*;



public class EditorApplicationModel extends DefaultApplicationModel {
    private final static double[] scaleFactors = {5, 4, 3, 2, 1.5, 1.25, 1, 0.75, 0.5, 0.25, 0.10};
    private static class ToolButtonListener implements ItemListener {
        private Tool tool;
        private DrawingEditor editor;
        public ToolButtonListener(Tool t, DrawingEditor editor) {
            this.tool = t;
            this.editor = editor;
        }
        public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                editor.setTool(tool);
            }
        }
    }
    /**
     * This editor is shared by all projects.
     */
    private DefaultDrawingEditor sharedEditor;
    
    private HashMap<String,Action> actions;
    
    
    //private DefaultDrawingEditor editor;

   	private FigureFactory figureFactory;

   	
   	
    /** Creates a new instance. */
    public EditorApplicationModel() {
    	figureFactory = new DefaultFigureFactory();
    }
    
   

    public void initApplication(Application a) {
        ResourceBundleUtil drawLabels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.net.Labels");
        AbstractAction aa;
        
        putAction(ExportAction.ID, new ExportAction(a));
        putAction("toggleGrid", aa = new ToggleProjectPropertyAction(a, "gridVisible"));
        drawLabels.configureAction(aa, "alignGrid");
        for (double sf : scaleFactors) {
            putAction((int) (sf*100)+"%",
                    aa = new ProjectPropertyAction(a, "scaleFactor", Double.TYPE, new Double(sf))
                    );
            aa.putValue(Action.NAME, (int) (sf*100)+" %");
            
        }
    }
    
    public DefaultDrawingEditor getSharedEditor() {
        if (sharedEditor == null) {
            sharedEditor = new DefaultDrawingEditor();
        }
        return sharedEditor;
    }
    
    public void initProject(Application a, Project p) {
        if (a.isSharingToolsAmongProjects()) {
            ((EditorProject) p).setDrawingEditor(getSharedEditor());
        }
    }
    private void addCreationButtonsTo(JToolBar tb, final DrawingEditor editor) {
        // AttributeKeys for the entitie sets
        HashMap<AttributeKey,Object> attributes;
        
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.net.Labels");
        ResourceBundleUtil drawLabels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        ToolBarButtonFactory.addSelectionToolTo(tb, editor);
        tb.addSeparator();
        
        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.white);
        attributes.put(AttributeKeys.STROKE_COLOR, Color.black);
        attributes.put(AttributeKeys.TEXT_COLOR, Color.black);
     //   ToolBarButtonFactory.addToolTo(tb, editor, new TextTool(new NodeFigure(), attributes), "createNode", labels);

        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.STROKE_COLOR, new Color(0x000099));
     //   ToolBarButtonFactory.addToolTo(tb, editor, new ConnectionTool(new LineConnectionFigure(), attributes), "createLink", labels);
    
        
        attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.white);
        attributes.put(AttributeKeys.STROKE_COLOR, Color.black);
        attributes.put(AttributeKeys.TEXT_COLOR, Color.black);
        
        //getFigure
      //  ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new RoundRectangleFigure(), attributes), "createRoundRectangle", drawLabels);
    
		// Add all the declared firures in FigureType enum
		for (FigureType figureType : FigureType.values() ) {
			
			Figure drawingFigure;
		//	if(figureType == FigureType.FLOWCHAR){
			// We need to show the decorated figure instead the line figure.
			
			
		//		drawingFigure = new FlowChartFigure();
		//		((FlowChartFigure)drawingFigure).registerFlowChartObserver(new FlowChartMessageObserver());
		//	}else{
				  drawingFigure = figureFactory.getFigure(figureType);
		//	}
				//  System.out.println(drawingFigure.toString());
				  ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(
					drawingFigure,attributes), figureType.getFigureLabel(), figureType
					.getLabelBundleUtil());
			
			// ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(new RoundRectangleFigure(), attributes), "createRoundRectangle", drawLabels);
			    
		}
        
    }
    /**
     * Creates toolbars for the application.
     * This class always returns an empty list. Subclasses may return other
     * values.
     */
    public java.util.List<JToolBar> createToolBars(Application a, Project pr) {
        ResourceBundleUtil drawLabels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.net.Labels");
        EditorProject p = (EditorProject) pr;
        
        DrawingEditor editor;
        if (p == null) {
            editor = getSharedEditor();
        } else {
            editor = p.getDrawingEditor();
        }
        
        LinkedList<JToolBar> list = new LinkedList<JToolBar>();
        JToolBar tb;
        tb = new JToolBar();
        addCreationButtonsTo(tb, editor);
        tb.setName(drawLabels.getString("drawToolBarTitle"));
        list.add(tb);
        tb = new JToolBar();
        ToolBarButtonFactory.addAttributesButtonsTo(tb, editor);
        tb.setName(drawLabels.getString("attributesToolBarTitle"));
        list.add(tb);
        tb = new JToolBar();
        ToolBarButtonFactory.addAlignmentButtonsTo(tb, editor);
        tb.setName(drawLabels.getString("alignmentToolBarTitle"));
        list.add(tb);
        return list;
    }
    
    public java.util.List<JMenu> createMenus(Application a, Project pr) {
        // FIXME - Add code for unconfiguring the menus!! We leak memory!
    	EditorProject p = (EditorProject) pr;
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.app.Labels");
        
        //  JMenuBar mb = new JMenuBar();
        LinkedList<JMenu> mb =  new LinkedList<JMenu>();
        JMenu m, m2;
        JMenuItem mi;
        JRadioButtonMenuItem rbmi;
        JCheckBoxMenuItem cbmi;
        ButtonGroup group;
        
        m = new JMenu();
        labels.configureMenu(m, "view");
        cbmi = new JCheckBoxMenuItem(getAction("toggleGrid"));
        Actions.configureJCheckBoxMenuItem(cbmi, getAction("toggleGrid"));
        m.add(cbmi);
        m2 = new JMenu("Zoom");
        for (double sf : scaleFactors) {
            String id = (int) (sf*100)+"%";
        cbmi = new JCheckBoxMenuItem(getAction(id));
        Actions.configureJCheckBoxMenuItem(cbmi, getAction(id));
        m2.add(cbmi);
        }
        m.add(m2);
        mb.add(m);
        
        return mb;
    }
}
