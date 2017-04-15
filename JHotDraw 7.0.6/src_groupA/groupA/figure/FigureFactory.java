/**
 * 
 */
package groupA.figure;

import org.jhotdraw.draw.Figure;


public interface FigureFactory {

	/**
	 * Return a default figure instance according to the passed figure type.
	 * @param type
	 * @return
	 */
	Figure getFigure(FigureType type);
	
	/**
	 * Return a predefined decorated figure of the requested type.
	 * In this demo the supported type is only the line figure to 
	 * be decorated with 2 edges arrows.
	 * <p/>  
	 * @param type
	 * @return
	 */
//	Figure getDecoratedFigure(FigureType type);
}
