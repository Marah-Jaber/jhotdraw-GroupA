package edu.bzu.swen6304.editor.action;


import java.util.LinkedList;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;

import edu.bzu.swen6304.editor.figure.NodeFigure;
import edu.bzu.swen6304.editor.sort.NodeSortStrategy;

public class TreeNodeSortAction extends SortAction {
	private static final long serialVersionUID = 1L;


	public TreeNodeSortAction(DrawingEditor editor) {
		this(editor, true);
	}

	/** Creates a new instance. */
	public TreeNodeSortAction(DrawingEditor editor,
			boolean canChangeLayout) {
		super(editor,new NodeSortStrategy());
	}

	@Override
	protected void updateEnabledState() {
		if (getView() != null) {
			setEnabled(canSort());
		} else {
			setEnabled(false);
		}
	}

	private boolean canSort() {
		return getView() != null
				&& (getView().getSelectionCount() > 1);
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		final DrawingView view = getView();

		final LinkedList<Figure> figures = new LinkedList<Figure>(
				view.getSelectedFigures());
	
		for(int i = 0 ; i < figures.size() ; i++) {
			Figure figure = figures.get(i);
			if(!(figure instanceof NodeFigure)) {
				figures.remove(figure);
			}
		}
		getSortContext().sort(figures);

	}

	
	
	@Override
	public String getId() {
		return "Sort based on node name";
	}
}
