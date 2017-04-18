package edu.bzu.swen6304.editor.sort;

import java.util.List;

import org.jhotdraw.draw.Figure;

public interface SortStrategy {
	
	
	public void sortTree(List<Figure> figureToSort);
	
}
