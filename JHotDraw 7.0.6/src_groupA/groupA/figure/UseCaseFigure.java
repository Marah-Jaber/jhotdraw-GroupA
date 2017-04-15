package groupA.figure;

import static org.jhotdraw.draw.AttributeKeys.FONT_BOLD;

import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import org.jhotdraw.draw.ConnectionHandle;
import org.jhotdraw.draw.EllipseFigure;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.FigureEvent;
import org.jhotdraw.draw.GraphicalCompositeFigure;
import org.jhotdraw.draw.Handle;
import org.jhotdraw.draw.LineConnectionFigure;
import org.jhotdraw.draw.ListFigure;
import org.jhotdraw.draw.MoveHandle;
import org.jhotdraw.draw.NullHandle;
import org.jhotdraw.draw.RectangleFigure;
import org.jhotdraw.draw.RelativeDecoratorLocator;
import org.jhotdraw.draw.RelativeLocator;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.VerticalLayouter;
import org.jhotdraw.geom.Insets2DDouble;
import org.jhotdraw.util.*;
import org.jhotdraw.xml.DOMOutput;

 

/**
 * UseCaseFigue 
 *
 */
public class UseCaseFigure extends GraphicalCompositeFigure {

	private String  useCaseName ;
     
    /**
     * TextFigure for editing the class name
     */
    private TextFigure UseCaseTextFigure;

    static final long serialVersionUID = 6098176631854387694L;

    /**
     * Create a new instance of UseCaseFigure with a RectangleFigure as presentation figure
     */    
    public UseCaseFigure() {
        this(new EllipseFigure());
    }

    /**
     * Create a new instance of UseCaseFigure with a given presentation figure
     *
     * @param newPresentationFigure presentation figure
     */    
    public UseCaseFigure(Figure newPresentationFigure) {
        super(newPresentationFigure);
        removeAllChildren();
        setLayouter(new VerticalLayouter());
        
      
        setUseCaseNameFigure(new TextFigure() {
            public void setText(String newText) {
                super.setText(newText);
                setUseCaseName(newText);
                
                validate();
            }
        });
        getUseCaseNameFigure().setFontSize(11);
        getUseCaseNameFigure().setAttributeEnabled(FONT_BOLD, false);
        getUseCaseNameFigure().setText("Use Case");
        GraphicalCompositeFigure componentFigure = new GraphicalCompositeFigure(new EllipseFigure());
        add(componentFigure);
        componentFigure.add(getUseCaseNameFigure());
        
     
        Insets2DDouble insets = new Insets2DDouble(10,10,40,60);
        LAYOUT_INSETS.set(componentFigure, insets);
        
        
    }

    /**
     * Hook method called to initizialize a UseCaseFigure.
     * It is called from the constructors and the clone() method.
     */
 
 
   
    protected void setUseCaseNameFigure(TextFigure textFigure) {
        UseCaseTextFigure = textFigure;
    }
    
   
    public TextFigure getUseCaseNameFigure() {
        return UseCaseTextFigure;
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
    /**
     * Return default handles on all four edges for this figure.
     */
    public Vector handles() {
        Vector handles = new Vector();
        handles.addElement(new NullHandle(getPresentationFigure(), RelativeLocator.northWest()));
        
        handles.addElement(new NullHandle(getPresentationFigure(), RelativeLocator.northEast()));
        handles.addElement(new NullHandle(getPresentationFigure(), RelativeLocator.southWest()));
        handles.addElement(new NullHandle(getPresentationFigure(), RelativeLocator.southEast()));

        return handles;
    }
    
    
    @Override public Collection<Handle> createHandles(int detailLevel) {
        java.util.List<Handle> handles = new LinkedList<Handle>();
        if (detailLevel == 0) {
            handles.add(new MoveHandle(this, RelativeLocator.northWest()));
            handles.add(new MoveHandle(this, RelativeLocator.northEast()));
            handles.add(new MoveHandle(this, RelativeLocator.southWest()));
            handles.add(new MoveHandle(this, RelativeLocator.southEast()));
            handles.add(new ConnectionHandle(this, RelativeLocator.north(), new LineConnectionFigure()));
            handles.add(new ConnectionHandle(this, RelativeLocator.east(), new LineConnectionFigure()));
            handles.add(new ConnectionHandle(this, RelativeLocator.south(), new LineConnectionFigure()));
            handles.add(new ConnectionHandle(this, RelativeLocator.west(), new LineConnectionFigure()));
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
    
    public String getUseCaseName() {
		return useCaseName;
	}

	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}
}