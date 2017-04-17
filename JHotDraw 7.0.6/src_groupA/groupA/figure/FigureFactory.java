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
	styleFigure getFigure(FigureType type);
	

}
