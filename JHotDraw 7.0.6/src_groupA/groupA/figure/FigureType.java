/**
 * 
 */
package groupA.figure;

import org.jhotdraw.draw.BezierFigure;
import org.jhotdraw.draw.DefaultDrawing;
import org.jhotdraw.draw.DiamondFigure;
import org.jhotdraw.draw.EllipseFigure;
import org.jhotdraw.draw.GroupFigure;
import org.jhotdraw.draw.LineConnectionFigure;
import org.jhotdraw.draw.LineFigure;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.RectangleFigure;
import org.jhotdraw.draw.RoundRectangleFigure;
import org.jhotdraw.draw.TextAreaFigure;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.TriangleFigure;
import org.jhotdraw.util.ResourceBundleUtil;



public enum FigureType {
	TEXT("createText", Text_Figure.class),
	Node( "createNode",NodeFigure.class)
	;
	
	
	
	
	/**
	 * Default jhotdraw framework ResourceBundleutil
	 */
	private static ResourceBundleUtil jhotdrawLabels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");

	/**
	 * Design patterns demo custom resource bundle.
	 */
    
    /**
     * Label text of the given figure, to be used for its creation icon and tooltip
     */
	private String figureLabel;
	
	/**
	 * Figure class to support reflection in the factory.
	 */
	@SuppressWarnings("rawtypes")
	private Class figureClass;
	
	/**
	 * Construct a figure type.
	 * @param figureLabel
	 * @param figureClass
	 */
	@SuppressWarnings("rawtypes")
	private FigureType(String figureLabel,Class figureClass) {
		this.figureLabel = figureLabel;
		this.figureClass = figureClass;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFigureLabel() {
		return figureLabel;
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getFigureClass() {
		return figureClass;
	}
	
	/**
	 * Get the resource bundle util according to the figure type.
	 * @return
	 */
	public ResourceBundleUtil getLabelBundleUtil() {
	
		return jhotdrawLabels;
	}
	
	@Override
	public String toString() {
		return name()+" Used label "+getFigureLabel();
	}
}
