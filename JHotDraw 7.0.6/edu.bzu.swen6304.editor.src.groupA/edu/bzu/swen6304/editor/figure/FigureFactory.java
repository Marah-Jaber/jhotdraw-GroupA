/**
 * 
 */
package edu.bzu.swen6304.editor.figure;

public interface FigureFactory {

	/**
	 * Return a default figure instance according to the passed figure type.
	 * @param type
	 * @return
	 */
	styleFigure getFigure(FigureType type);
	

}
