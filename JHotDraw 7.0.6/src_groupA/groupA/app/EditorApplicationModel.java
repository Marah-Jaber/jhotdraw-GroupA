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
import groupA.action.*;


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
	private FigureMaker figureMaker;
   	
   	
    /** Creates a new instance. */
    public EditorApplicationModel() {
    	figureFactory = new DefaultFigureFactory();
    	figureMaker = new FigureMaker();
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
        
        tb.addSeparator();
        
        attributes = new HashMap<AttributeKey,Object>();
    
        attributes = new HashMap<AttributeKey,Object>();
        
        attributes = new HashMap<AttributeKey,Object>();
      
		// Add all the declared firures in FigureType enum
		
        Collection<Action> menuActions = new LinkedList<Action>();
		// Add separator
		menuActions.add(null);

		menuActions.add(new TreeDepthSortAction(editor));
		menuActions.add(new TreeNodeSortAction(editor));
		menuActions.add(new TreeChildgetAction(editor));
		ToolBarButtonFactory.addSelectionToolTo(tb, editor, ToolBarButtonFactory.createDrawingActions(editor),
				menuActions);

		tb.addSeparator();
        
        for (FigureType figureType : FigureType.values() ) {
			
			styleFigure drawingFigure;
			drawingFigure = figureMaker.addStyle(figureType);
			
				  ToolBarButtonFactory.addToolTo(tb, editor, new CreationTool(
					drawingFigure,attributes), figureType.getFigureLabel(), figureType
					.getLabelBundleUtil());
			
			   
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
