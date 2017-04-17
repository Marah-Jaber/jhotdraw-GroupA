package groupA.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jhotdraw.draw.Figure;

import groupA.figure.NodeFigure;

/**
 * This class is to implement the tree nodes based on their names.
 * This is a part of the strategy design pattern, which represents the algorithm of sorting by name.
 */
public class NodeSortStrategy implements SortStrategy {

	/**
	 * This is to add a concrete implementation of the comparator interface, 
	 * where each two nodes are compared using their names
	 */
	public static Comparator<Figure> figureWidthComparator = new Comparator<Figure>() {

		@Override
		public int compare(Figure fig1, Figure fig2) {
			String fig1Name = ((NodeFigure) fig1).getNodeName().toLowerCase();
			String fig2Name = ((NodeFigure) fig2).getNodeName().toLowerCase();
			System.out.println("CALL? " + fig1Name + " fig " + fig2Name);
			// ascending order
			return fig1Name.compareTo(fig2Name);
		}
	};

	/**
	 * Sort the tree nodes based on their names
	 */
	@Override
	public void sortTree(List<Figure> figuresToSort) {
		Collections.sort(figuresToSort, figureWidthComparator);
		// Print sorted figures
		String sortedNodes = "";
		for(Figure figure : figuresToSort) {
			sortedNodes += ", " + ((NodeFigure) figure).getNodeName();
		}
		// Display the sorted nodes as a popup
        JOptionPane.showMessageDialog(null, sortedNodes, "Sort by name: " + "Sorted Nodes", JOptionPane.INFORMATION_MESSAGE);
	}
}
