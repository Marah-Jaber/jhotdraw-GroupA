package groupA.figure;

import static org.jhotdraw.draw.AttributeKeys.END_DECORATION;
import static org.jhotdraw.draw.AttributeKeys.FONT_BOLD;
import static org.jhotdraw.draw.AttributeKeys.FONT_ITALIC;
import static org.jhotdraw.draw.AttributeKeys.FONT_UNDERLINED;
import static org.jhotdraw.draw.AttributeKeys.START_DECORATION;
import static org.jhotdraw.draw.AttributeKeys.STROKE_COLOR;
import static org.jhotdraw.draw.AttributeKeys.STROKE_DASHES;
import static org.jhotdraw.draw.AttributeKeys.STROKE_WIDTH;
import static org.jhotdraw.draw.AttributeKeys.TEXT_COLOR;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jhotdraw.draw.ArrowTip;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.ConnectionHandle;
import org.jhotdraw.draw.EllipseFigure;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.GraphicalCompositeFigure;
import org.jhotdraw.draw.Handle;
import org.jhotdraw.draw.RelativeLocator;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.VerticalLayouter;
import org.jhotdraw.geom.Insets2DDouble;


 

/**
 * UseCaseFigue 
 *
 */
public class NodeFigure extends GraphicalCompositeFigure implements Figure{

	private String  nodeName ;
     
    /**
     * TextFigure for editing the class name
     */
    private TextFigure NodeTextFigure;
    private HashSet<DependencyFigure> dependencies;
    
    static final long serialVersionUID = 6098176631854387694L;

    /**
     * Create a new instance of UseCaseFigure with a RectangleFigure as presentation figure
     */    
    public NodeFigure() {
        this(new EllipseFigure());
        HashMap<AttributeKey,Object> attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.red);
        //attributes.put(AttributeKeys.STROKE_COLOR, Color.green);
       // attributes.put(AttributeKeys.TEXT_COLOR, Color.red);
        //attributes.put(AttributeKeys.FONT_BOLD, Color.red);
       this.setAttribute(STROKE_COLOR, Color.red);
       this.getPresentationFigure().setAttribute(AttributeKeys.FILL_COLOR,new Color(0x000099));
       //this.getChildren().setAttribute(AttributeKeys.FILL_COLOR,new Color(0x000099));
       this.getPresentationFigure().setAttribute(AttributeKeys.STROKE_COLOR,new Color(0x000099));
    }

    /**
     * Create a new instance of NodeFigure with a given presentation figure
     *
     * @param newPresentationFigure presentation figure
     */    
    public NodeFigure(Figure newPresentationFigure) {
        super(newPresentationFigure);
        removeAllChildren();
        setLayouter(new VerticalLayouter());
        
      
        setNodeNameFigure(new TextFigure() {
            public void setText(String newText) {
                super.setText(newText);
                setNodeName(newText);
                
                validate();
            }
        });
       
        STROKE_WIDTH.set(this, 1d);
        STROKE_COLOR.set(this, Color.RED);
        STROKE_WIDTH.set(this, 1d);
        GraphicalCompositeFigure componentFigure = new GraphicalCompositeFigure(new EllipseFigure());
        add(componentFigure);
        componentFigure.add(getNodeNameFigure());
        
        this.getPresentationFigure().setAttribute(AttributeKeys.FILL_COLOR,new Color(0x000099));
        this.getPresentationFigure().setAttribute(AttributeKeys.STROKE_COLOR,new Color(0x000099));
        
        dependencies = new HashSet<DependencyFigure>();
        Insets2DDouble insets = new Insets2DDouble(10,15,30,40);
        LAYOUT_INSETS.set(componentFigure, insets);

        addStyle();
    }
    
    public void addStyle(){
    	
    	 getNodeNameFigure().setFontSize(11);
         getNodeNameFigure().setAttributeEnabled(FONT_BOLD, false);
         getNodeNameFigure().setText("test");
         this.getPresentationFigure().setAttribute(AttributeKeys.FILL_COLOR,new Color(0x000099));
         this.getPresentationFigure().setAttribute(AttributeKeys.STROKE_COLOR,new Color(0x000099));
        STROKE_WIDTH.set(this, 1d);
        System.out.println(this.getPresentationFigure());
        STROKE_COLOR.set(this.getPresentationFigure(), Color.RED);
        STROKE_WIDTH.set(this, 1d);
        
        HashMap<AttributeKey,Object> attributes = new HashMap<AttributeKey,Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.red);
        //attributes.put(AttributeKeys.STROKE_COLOR, Color.green);
       // attributes.put(AttributeKeys.TEXT_COLOR, Color.red);
        //attributes.put(AttributeKeys.FONT_BOLD, Color.red);
       this.setAttribute(STROKE_COLOR, Color.red);
       //this.setAttribute(key, newValue);
        //FILL_COLOR.set(this, Color.LIGHT_GRAY);
      /*  this.setAttributeEnabled(FONT_BOLD, true);
        this.setAttributeEnabled(FONT_ITALIC, true);
        attributes.put(AttributeKeys.FILL_COLOR, Color.white);
        attributes.put(AttributeKeys.STROKE_COLOR, Color.black);
        attributes.put(AttributeKeys.TEXT_COLOR, Color.black); */
       
    }

   
   
    protected void setNodeNameFigure(TextFigure textFigure) {
    	NodeTextFigure = textFigure;
    }
    
   
    public TextFigure getNodeNameFigure() {
        return NodeTextFigure;
    }


     
    
    /**
     * Propagate the removeFromDrawing request up to the container.
     * A UseCaseFigure should not be removed just because one of its childern
     * is removed.
     */
 /*   public void figureRequestRemove(FigureEvent e) {
        this.figureRequestRemove(new FigureEvent(this, displayBox()));
     }
*/
 
    
    
    @Override public Collection<Handle> createHandles(int detailLevel) {
        java.util.List<Handle> handles = new LinkedList<Handle>();
        if (detailLevel == 0) {
            
            handles.add(new ConnectionHandle(this, RelativeLocator.south(), new DependencyFigure()));
        }
        return handles;
    }
 
    /**
     * Test whether this figure has child figures.
     *
     * @return true, if there are no child figures, false otherwise
     */
 /*   public boolean isEmpty() {
        return figureCount() == 0;
    } */

    /**
     * Read the figure and its contained elements from the StorableOutput and sets
     * the presentation figure and creates the popup menu.
     */
 /*   public void read(StorableInput dr) throws IOException {
        getUseCaseNameFigure().setText(dr.readString());
        setPresentationFigure((Figure)dr.readStorable());
        validate();
    } */
    
    /**
     * Write the figure and its contained elements to the StorableOutput.
     */
  /*   public void write(DOMOutput dw) throws IOException {
        dw.writeString(getUseCaseName());
        dw.writeStorable(getPresentationFigure());
    }  

    /**
     * Read the serialized figure and its contained elements from the input stream and
     * creates the popup menu
     */
    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        // call superclass' private readObject() indirectly
        s.defaultReadObject();
    }
    
    public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
    public Set<DependencyFigure> getDependencies() {
        return Collections.unmodifiableSet(dependencies);
    }
    
    public void addDependency(DependencyFigure f) {
        dependencies.add(f);
        //updateStartTime();
    }
    public void removeDependency(DependencyFigure f) {
        dependencies.remove(f);
        //updateStartTime();
    }
    
    
    /**
     * Returns dependent PertNodes which are directly connected via a
     * PertDependency to this NodeFigure.
     */
    public List<NodeFigure> getSuccessors() {
        LinkedList<NodeFigure> list = new LinkedList<NodeFigure>();
        for (DependencyFigure c : getDependencies()) {
            if (c.getStartFigure() == this) {
                list.add((NodeFigure) c.getEndFigure());
            }
        }
        return list;
    }
    
    /**
     * Returns predecessor PertNodes which are directly connected via a
     * PertDependency to this NodeFigure.
     */
    public List<NodeFigure> getPredecessors() {
        LinkedList<NodeFigure> list = new LinkedList<NodeFigure>();
        for (DependencyFigure c : getDependencies()) {
            if (c.getEndFigure() == this) {
                list.add((NodeFigure) c.getStartFigure());
            }
        }
        return list;
    }
    
    /**
     * Returns true, if the current Node is a direct or
     * indirect dependent of the specified Node.
     * If the dependency is cyclic, then this method returns true
     * if <code>this</code> is passed as a parameter and for every other
     * Node in the cycle.
     */
    public boolean isDependentOf(NodeFigure t) {
        if (this == t) return true;
        for (NodeFigure pre : getPredecessors()) {
            if (pre.isDependentOf(t)) {
                return true;
            }
        }
        return false;
    }
}