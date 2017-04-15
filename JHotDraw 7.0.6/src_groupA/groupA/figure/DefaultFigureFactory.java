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
		//System.out.println("testttt");
		return new RectangleFigure();
	}

/*	*//**
	 * @see org.simpleeditor.figure.FigureFactory#getDecoratedFigure(org.simpleeditor.figure.FigureType)
	 *//*
	@Override
	public Figure getDecoratedFigure(FigureType type) {
		// We implemented only line figure as an example of the decorator design
		// pattern.
		LineFigure decoratedArrow = (LineFigure) new LineFigure();
		decoratedArrow.set(AttributeKeys.START_DECORATION,
				new org.jhotdraw.draw.decoration.ArrowTip(0.35, 12, 11.3));
		// decoratedArrow.set(AttributeKeys.END_DECORATION, new
		// org.jhotdraw.draw.decoration.ArrowTip(0.35, 12, 11.3));
		return decoratedArrow;
	}*/

}
