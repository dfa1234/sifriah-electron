package df.sifriah.control;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import df.sifriah.Utils;
import df.sifriah.model.Node;
import df.sifriah.view.MainPanel;
import df.sifriah.xmlParser.StaXParserChap;
import df.sifriah.xmlParser.StaXParserPid;

public class NodeTreeListener implements Listener {

	private Browser browser;
	private Tree chapTree;
	private MainPanel mainPanel;
	private Composite pidsPanel;

	public NodeTreeListener(MainPanel mainPanel, Browser browser,
			Tree chapTree, Composite pidsPanel) {
		super();
		this.mainPanel = mainPanel;
		this.browser = browser;
		this.chapTree = chapTree;
		this.pidsPanel = pidsPanel;
	}

	@Override
	public void handleEvent(Event event) {
		Tree nodeTree = (Tree) event.widget;
		TreeItem[] selection = nodeTree.getSelection();
		long nid = 0;
		for (int i = 0; i < selection.length; i++) {
			nid = ((Node) selection[i].getData()).getNid();
		}
		if (selection.length > 0) {
			TreeItem itemSelected = selection[selection.length - 1];
			if (nid != 0) {
				String sXml = nid + ".xml";

				// update chapter tree
				StaXParserChap stc = new StaXParserChap();
				ArrayList<String> chapters = stc.readChapter(sXml);
				chapTree.removeAll();
				TreeItem item = null;
				if (chapters.size() > 0) {
					for (String c : chapters) {
						item = new TreeItem(chapTree, SWT.NONE);
						item.setText(c);
						item.setData(new Long(nid));
					}
				}

				// update pids
				StaXParserPid stp = new StaXParserPid();
				ArrayList<String> pids = stp.readPids(sXml);
				for (Control kid : pidsPanel.getChildren()) {
					kid.dispose();
				}
				Map<String, String> parchanim = Utils.getComments();
				Map<String, Boolean> parchanimState = Utils.loadCommentsState();
				Button b;
				for (String pid : pids) {
					b = new Button(pidsPanel, SWT.CHECK);
					b.setText(parchanim.get(pid));
					b.setData(pid);
					b.addSelectionListener(new CheckboxListener(this.mainPanel,
							this.browser));
					b.setSelection(parchanimState.get(pid));
				}
				pidsPanel.layout();

				// update browser
				if (chapters.size() > 0) {
					mainPanel.refreshBrowser(nid, chapters.get(0));
				}else{
					mainPanel.refreshBrowser(nid, "");
				}

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