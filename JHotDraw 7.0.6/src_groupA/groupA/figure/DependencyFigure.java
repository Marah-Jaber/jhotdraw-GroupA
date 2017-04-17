
 
package groupA.figure;

import java.awt.*;
import org.jhotdraw.samples.*;
import java.beans.*;
import static org.jhotdraw.draw.AttributeKeys.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.xml.*;

/**
 * DependencyFigure.
 *
 * @author Werner Randelshofer.
 * @version 1.0 18. Juni 2006 Created.
 */
public class DependencyFigure extends LineConnectionFigure {
    /** Creates a new instance. */
    public DependencyFigure() {
        STROKE_COLOR.set(this, new Color(0x000099));
        STROKE_WIDTH.set(this, 1d);
        END_DECORATION.set(this, new ArrowTip());
        
        setAttributeEnabled(END_DECORATION, false);
        setAttributeEnabled(START_DECORATION, false);
        setAttributeEnabled(STROKE_DASHES, false);
        setAttributeEnabled(FONT_ITALIC, false);
        setAttributeEnabled(FONT_UNDERLINED, false);
    }
    
    /**
     * Checks if two figures can be connected. Implement this method
     * to constrain the allowed connections between figures.
     */
    public boolean canConnect(Figure start, Figure end) {
        if ((start instanceof NodeFigure)
        && (end instanceof NodeFigure)) {
            
            NodeFigure sf = (NodeFigure) start;
            NodeFigure ef = (NodeFigure) end;
            
            // Disallow multiple connections to same dependent
            if (ef.getPredecessors().contains(sf)) {
                return false;
            }
            
            if (! ef.getPredecessors().isEmpty()) {
                return false;
            }
            
            
            for(int i = 0; i<ef.getPredecessors().size();i++)
            {
            	for(int j = 0; j<sf.getPredecessors().size();j++)
                {
                	
                	if(ef.getPredecessors().get(i).equals(sf.getPredecessors().get(j))) return false;
                	if (sf.isDependentOf(ef.getPredecessors().get(i))) return false;
                    if (ef.isDependentOf(sf.getPredecessors().get(i))) return false;
                
                }
            }
            	
            
            // Disallow cyclic connections
            if (sf.isDependentOf(ef)) return false;
            if (ef.isDependentOf(sf)) return false;
            
            return true;
        }
        
        return false;
    }
    public boolean canConnect(Figure start) {
        return (start instanceof NodeFigure);
    }
    
    
    /**
     * Handles the disconnection of a connection.
     * Override this method to handle this event.
     */
    protected void handleDisconnect(Figure start, Figure end) {
        NodeFigure sf = ((NodeFigure) start.clone());
        NodeFigure ef = ((NodeFigure) end.clone());
        sf.addNode(ef);
        sf.removeDependency(this);
        ef.removeDependency(this);
    }
    
    /**
     * Handles the connection of a connection.
     * Override this method to handle this event.
     */
    protected void handleConnect(Figure start, Figure end) {
        NodeFigure sf = (NodeFigure) start;
        NodeFigure ef = (NodeFigure) end;
        ef.addNode(sf);
        sf.addDependency(this);
        ef.addDependency(this);
    }
    
    public DependencyFigure clone() {
        DependencyFigure that = (DependencyFigure) super.clone();
        
        return that;
    }
    
    public int getLayer() {
        return 1;
    }
    
    @Override public void removeNotify(Drawing d) {
        if (getStartFigure() != null) {
            ((NodeFigure) getStartFigure()).removeDependency(this);
        }
        if (getEndFigure() != null) {
            ((NodeFigure) getEndFigure()).removeDependency(this);
        }
        super.removeNotify(d);
    }
}
