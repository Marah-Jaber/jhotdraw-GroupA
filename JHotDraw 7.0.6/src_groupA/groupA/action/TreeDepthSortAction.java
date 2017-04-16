package groupA.action;

import java.util.LinkedList;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;



import groupA.sort.DepthSortStrategy;


public class TreeDepthSortAction extends SortAction {
	private static final long serialVersionUID = 1L;

	public TreeDepthSortAction(DrawingEditor editor) {
		this(editor, true);
	}

	/** Creates a new instance. */
	public TreeDepthSortAction(DrawingEditor editor,
			boolean canChangeLayout) {
		super(editor,new DepthSortStrategy());
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
		//groupFiguresAndApplySortByWidth(view, new GraphicalCompositeFigure(), figures);

	}


	
	@Override
	public String getId() {
		return "edit.sort.byDepth";
	}
}
