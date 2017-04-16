package groupA.action;


import java.util.LinkedList;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;



import groupA.sort.NodeSortStrategy;


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
	//	groupFiguresAndApplySortByName(view, new GraphicalCompositeFigure(), figures);

	}

	
	
	@Override
	public String getId() {
		return "edit.sort.byNode";
	}
}
