/**
 * 
 */
package groupA.action;

import org.jhotdraw.app.action.Actions;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AbstractSelectedAction;
import org.jhotdraw.util.ResourceBundleUtil;




public abstract class getAction extends AbstractSelectedAction {

	private  String id = "edit.get";
	
	
	public getAction(DrawingEditor editor) {
		super(editor);
	/*	ResourceBundleUtil labels = ResourceBundleUtil
				.getLAFBundle("org.jhotdraw.draw.Labels"); */
		labels.configureAction(this, getId());
		putValue(Actions.SUBMENU_KEY, "Print Child Name");
		updateEnabledState();
	
	}

	protected void updateEnabledState() {
		if (getView() != null) {
			setEnabled(getView().getSelectedFigures().size() == 1);
		} else {
			setEnabled(false);
		}
	}

	public String getId() {
		return id;
	}
	
	
}
