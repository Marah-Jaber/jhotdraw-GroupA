/**
 * 
 */
package groupA.action;

import java.awt.event.ActionEvent;


import org.jhotdraw.app.action.Actions;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AbstractSelectedAction;
import org.jhotdraw.util.ResourceBundleUtil;

import groupA.sort.SortStrategy;
import groupA.sort.TreeSorting;


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
