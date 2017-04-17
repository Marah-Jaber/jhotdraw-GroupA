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
	
	@Override
	public styleFigure getFigure(FigureType type) {
		try {
			return (styleFigure) type.getFigureClass().newInstance();
		} catch (Exception e2) {
			System.err
					.println("Unknown exception creating class return default Figure, rectangle figure");
		}
		return new Text_Figure();
	}



}
