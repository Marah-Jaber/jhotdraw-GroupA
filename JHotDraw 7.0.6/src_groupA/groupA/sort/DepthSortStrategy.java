/**
 * 
 */
package groupA.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.jhotdraw.draw.Figure;

public class DepthSortStrategy implements SortStrategy {

	/**
	 * 
	 */
	public DepthSortStrategy() {
	}

	public static Comparator<Figure> figureNamesComparator = new Comparator<Figure>() {

		public int compare(Figure fig1, Figure fig2) {
			String fig1Name = fig1.getClass().getSimpleName().toUpperCase();
			String fig2Name = fig2.getClass().getSimpleName().toUpperCase();
			System.out.println("CALL? "+fig1Name+" fig "+fig2Name);
			// ascending order
			return fig1Name.compareTo(fig2Name);

		}
	};

	
	@Override
	public void sortTree(List<Figure> figureToSort) {

		Collections.sort(figureToSort, figureNamesComparator);

	}


}
