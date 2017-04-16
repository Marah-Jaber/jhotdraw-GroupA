/**
 * 
 */
package groupA.sort;

import java.util.List;

import org.jhotdraw.draw.Figure;

public class TreeSorting {

	private SortStrategy sortStrategy;

	
	public void setSortStrategy(SortStrategy sortStrategy) {
		this.sortStrategy = sortStrategy;
	}
	
	public void sort(List<Figure> figures){
		this.sortStrategy.sortTree(figures);
	}

}
