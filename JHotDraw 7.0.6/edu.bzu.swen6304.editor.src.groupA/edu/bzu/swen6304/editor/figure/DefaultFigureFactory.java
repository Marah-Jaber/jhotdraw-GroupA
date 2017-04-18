/**
 * 
 */
package edu.bzu.swen6304.editor.figure;

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
