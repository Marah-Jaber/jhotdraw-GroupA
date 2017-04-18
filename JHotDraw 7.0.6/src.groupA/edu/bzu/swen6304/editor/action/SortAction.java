/**
 * 
 */
package edu.bzu.swen6304.editor.action;

import org.jhotdraw.app.action.Actions;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AbstractSelectedAction;

import edu.bzu.swen6304.editor.sort.SortStrategy;
import edu.bzu.swen6304.editor.sort.TreeSorting;


public abstract class SortAction extends AbstractSelectedAction {

	private  String id = "edit.sort";
	
	private TreeSorting sortContext = new TreeSorting();
	
	public SortAction(DrawingEditor editor, SortStrategy strategy) {
		super(editor);
	/*	ResourceBundleUtil labels = ResourceBundleUtil
				.getLAFBundle("org.jhotdraw.draw.Labels"); */
		labels.configureAction(this, getId());
		putValue(Actions.SUBMENU_KEY, "Sort using");
		updateEnabledState();
		getSortContext().setSortStrategy(strategy);
	}

	protected void updateEnabledState() {
		if (getView() != null) {
			setEnabled(getView().getSelectedFigures().size() > 1);
		} else {
			setEnabled(false);
		}
	}

	public String getId() {
		return id;
	}
	
	public TreeSorting getSortContext() {
		return sortContext;
	}
}
