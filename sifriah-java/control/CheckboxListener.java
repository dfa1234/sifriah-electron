package df.sifriah.control;

import java.util.Map;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import df.sifriah.Utils;
import df.sifriah.view.MainPanel;

public class CheckboxListener extends SelectionAdapter {

	private Browser browser;
	private MainPanel mainPanel;

	public CheckboxListener(MainPanel mainPanel, Browser browser) {
		this.mainPanel = mainPanel;
		this.browser = browser;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Button button = (Button) e.widget;
		String pid = (String) button.getData();
		Long nid = (Long) browser.getData("nid");
		String chapter = (String) browser.getData("chapter");

		// update state file
		Map<String, Boolean> state = Utils.loadCommentsState();
		state.put(pid, button.getSelection());
		Utils.saveCommentsState(state);

		// update browser
		mainPanel.refreshBrowser(nid, chapter);
	}

}
