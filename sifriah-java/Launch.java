package df.sifriah;

import df.sifriah.view.MainPanel;

public class Launch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(">>>Start");
		MainPanel mainPanel = new MainPanel();
		String repXml = System.getenv("CONTENTDIR");
		mainPanel.displayMainPanel(repXml+"/xml/tanach.xml");
		System.out.println("<<<Stop");

	}

}
