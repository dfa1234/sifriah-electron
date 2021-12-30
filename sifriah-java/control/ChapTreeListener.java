package df.sifriah.control;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import df.sifriah.view.MainPanel;

public class ChapTreeListener implements Listener {

	private MainPanel mainPanel;

	public ChapTreeListener(MainPanel mainPanel) {
		super();
		this.mainPanel = mainPanel;
	}

	@Override
	public void handleEvent(Event event) {
		Tree chapTree = (Tree) event.widget;
		TreeItem[] selection = chapTree.getSelection();

		if (selection.length > 0) {
			TreeItem itemSelected = selection[selection.length - 1];
			Long nid = (Long) itemSelected.getData();
			String chapter = itemSelected.getText();
			if (nid != 0) {
				// update browser
				mainPanel.refreshBrowser(nid, chapter);
			} else {
				if (itemSelected.getExpanded()) {
					itemSelected.setExpanded(false);
				} else {
					itemSelected.setExpanded(true);
				}
			}
		}
		// System.out.println(event.widget.getClass());
	}

}
