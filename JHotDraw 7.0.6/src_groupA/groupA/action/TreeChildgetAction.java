package groupA.action;


import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.Figure;

import groupA.figure.NodeFigure;



public class TreeChildgetAction extends getAction {
	private static final long serialVersionUID = 1L;


	public TreeChildgetAction(DrawingEditor editor) {
		this(editor, true);
	}

	/** Creates a new instance. */
	public TreeChildgetAction(DrawingEditor editor,
			boolean canChangeLayout) {
		super(editor);
	}

	@Override
	protected void updateEnabledState() {
		if (getView() != null) {
			setEnabled(canGet());
		} else {
			setEnabled(false);
		}
	}

	private boolean canGet() {
		return getView() != null
				&& (getView().getSelectionCount() == 1)
		&& (getView().getSelectedFigures().iterator().next().getClass().getSimpleName().equals("NodeFigure"));
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		final DrawingView view = getView();

		final Figure nodeFigure = view.getSelectedFigures().iterator().next();
		System.out.println(nodeFigure);
		((NodeFigure) nodeFigure).printNodeChild();

	}

	
	
	@Override
	public String getId() {
		return "edit.sort.byNode";
	}
}
