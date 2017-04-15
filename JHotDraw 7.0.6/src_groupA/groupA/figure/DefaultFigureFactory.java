/**
 * 
 */
package groupA.figure;

import org.jhotdraw.geom.*;
import org.jhotdraw.samples.net.figures.*;
import java.util.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.xml.*;

public class DefaultFigureFactory implements FigureFactory {
	/**
	 * @see org.simpleeditor.figure.FigureFactory#getFigure(org.simpleeditor.figure.FigureType)
	 */
	@Override
	public Figure getFigure(FigureType type) {
		try {
			//System.out.println("gettt figure");
			return (Figure) type.getFigureClass().newInstance();
		} catch (Exception e2) {
			System.err
					.println("Unknown exception creating class return default Figure, rectangle figure");
		}
		return new RectangleFigure();
	}



}
