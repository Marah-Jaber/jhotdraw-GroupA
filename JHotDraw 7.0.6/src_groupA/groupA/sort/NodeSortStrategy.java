/**
 * 
 */
package groupA.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jhotdraw.draw.Figure;


public class NodeSortStrategy implements SortStrategy {

	/**
	 * 
	 */
	public NodeSortStrategy() {
	}

	public static Comparator<Figure> figureWidthComparator = new Comparator<Figure>() {

		@Override
		public int compare(Figure arg0, Figure arg1) {
			// TODO Auto-generated method stub
			return 0;
		}


	}; 
	/**
	 * @see org.simpleeditor.sort.SortStrategy#sortFigures(java.util.List)
	 */
	@Override
	public  void sortTree(List<Figure> figureToSort) {
		
		Collections.sort(figureToSort, figureWidthComparator);
	}

}
