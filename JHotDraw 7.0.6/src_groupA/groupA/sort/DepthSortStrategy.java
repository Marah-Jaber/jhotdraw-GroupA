package groupA.sort;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jhotdraw.draw.Figure;

import groupA.figure.NodeFigure;

/**
 * This class is to implement the tree nodes based on their levels. This is a
 * part of the strategy design pattern, which represents the algorithm of
 * sorting by depth.
 */
public class DepthSortStrategy implements SortStrategy {

	/**
	 * Sort the nodes based on the depth of the nodes, i.e based on their levels
	 */
	@Override
	public void sortTree(List<Figure> figureToSort) {
		// The final sorted list
		List<Figure> orderedList = new ArrayList<>();
		// The root of the tree where we'll be navigating to get all children
		NodeFigure root = (NodeFigure) figureToSort.get(0);
		// The children of the root node, the starting point
		List<NodeFigure> childrenNodes = root.getNodes();
		orderedList.add(root);
		// Sort the nodes recursively
		sortByChildrenNodes(childrenNodes, orderedList, root.getPredecessors().size() + 1);
		// figureToSort = orderedList;
		// Print sorted figures
		String sortedNodes = "";
		for (Figure figure : orderedList) {
			sortedNodes += ", " + ((NodeFigure) figure).getNodeName();
		}
		// Display the sorted nodes as a popup
		JOptionPane.showMessageDialog(null, sortedNodes, "Sort by name: " + "Sorted Nodes",
				JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * Sort the tree nodes recursively based on their levels, i.e level 0 comes
	 * before level 1 and so on.
	 * 
	 * P.S, the composite design pattern is applied here, where each individual
	 * node and its compositions are treated uniformly
	 * 
	 * @param childrenNodes
	 *            The children of each node.
	 * @param orderedList
	 *            The sorted nodes
	 */
	private void sortByChildrenNodes(List<NodeFigure> childrenNodes, List<Figure> orderedList, int noOfNodes) {
		if (childrenNodes != null && childrenNodes.size() > 0 && noOfNodes != 0) {
			// Add each node to the sorted list
			// Go recursively to add the children nodes to the ordered list
			for (NodeFigure node : childrenNodes) {
				orderedList.add(node);
			}
		}
	}
}
