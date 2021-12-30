package df.sifriah.view;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import df.sifriah.Utils;
import df.sifriah.control.ChapTreeListener;
import df.sifriah.control.NodeTreeListener;
import df.sifriah.model.Node;

import df.sifriah.xmlParser.StaXParserTanach;
import df.sifriah.xslTransform.XmlTransformBuffered;
import df.sifriah.xslTransform.XmlTransformException;

public class MainPanel {

	public static  String NAME_APP = "שערי תורה";
	private Browser browser = null;
	private Tree chapTree = null;
	private Tree nodeTree = null;
	private Composite pids = null;

	private Tree chapTree(Composite parent, ArrayList<String> chapters) {
		Tree tree = new Tree(parent, SWT.RIGHT_TO_LEFT);
		TreeItem item = null;
		if (chapters.size() > 0) {
			for (String c : chapters) {
				item = new TreeItem(tree, SWT.NONE);
				item.setText(c);
			}
		}
		return tree;
	}

	public void displayMainPanel(String loc_tanach) {
		Display.setAppName(NAME_APP);
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText(NAME_APP);

		// tailles du moniteur et redimmensionnement
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		int width = (int) Math.round(bounds.width * .8);
		int height = (int) Math.round(bounds.height * .8);
		shell.setSize(width, height);

		// centrer le shell
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);

		// le layout
		// GridLayout gridLayout = new GridLayout();
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		shell.setLayout(fillLayout);

		// la fenetre gauche
		browser = new Browser(shell, SWT.NONE);
		browser.setText("Please choose a book");

		// la fenetre droite
		Composite compositeRight = new Composite(shell, SWT.NONE);
		compositeRight.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		// la liste des chap et des pid
		Composite compositeMiddle = new Composite(compositeRight, SWT.NONE);
		compositeMiddle.setLayout(new FillLayout(SWT.VERTICAL));

		// chap
		chapTree = chapTree(compositeMiddle, new ArrayList<String>());
		chapTree.setBackground(Utils.COLOR_AZURE);

		// pids
		pids = new Composite(compositeMiddle, SWT.NONE);
		pids.setBackground(Utils.COLOR_AZURE2);
		FillLayout fillLayout2 = new FillLayout(SWT.VERTICAL);
		pids.setLayout(fillLayout2);

		// le menu de droite
		StaXParserTanach stt = new StaXParserTanach();
		ArrayList<Node> nodes = stt.readChapter(loc_tanach);
		nodeTree = NodeTree(compositeRight, nodes);
		nodeTree.setBackground(Utils.COLOR_LIGHTCYAN);

		// ajout des listener
		nodeTree.addListener(SWT.Selection, new NodeTreeListener(this,
				this.browser, this.chapTree, this.pids));
		chapTree.addListener(SWT.Selection, new ChapTreeListener(this));

		// ouverture et activation
		// reduit la taille: shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	/**
	 * Paste tab1 with tab2
	 * 
	 * @param t1
	 *            treeitem tab
	 * @param t2
	 *            treeitem tab
	 * @return t3 = t1 + t2
	 */
	private TreeItem[] join(TreeItem[] t1, TreeItem[] t2) {
		TreeItem[] t3 = new TreeItem[t1.length + t2.length];
		for (int i = 0; i < t1.length; i++) {
			t3[i] = t1[i];
		}
		for (int i = 0; i < t2.length; i++) {
			t3[i + t1.length] = t2[i];
		}
		return t3;
	}

	/**
	 * Give the tree of node with a depth of 4 subitem
	 * 
	 * @param parent
	 *            : where to put the tree
	 * @param nodes
	 *            : list of node to display
	 * @return the tree
	 */
	private Tree NodeTree(Composite parent, ArrayList<Node> nodes) {
		Tree tree = new Tree(parent, SWT.RIGHT_TO_LEFT);
		int idParent = 0;
		TreeItem item = null;
		int i = 0;
		TreeItem[] treeItems = null;
		for (Node node : nodes) {
			idParent = node.getIdParent();
			if (idParent == Node.TYPE_ROOT) {
				item = new TreeItem(tree, SWT.NONE);
				item.setText(node.getName());
				item.setData(node);
			} else {
				treeItems = tree.getItems();
				for (TreeItem ti1 : treeItems) {
					if (ti1.getItemCount() != 0) {
						for (TreeItem ti2 : ti1.getItems()) {
							if (ti2.getItemCount() != 0) {
								for (TreeItem ti3 : ti2.getItems()) {
									if (ti3.getItemCount() != 0) {
										for (TreeItem ti4 : ti3.getItems()) {
											if (ti4.getItemCount() != 0) {
												treeItems = join(treeItems,
														ti4.getItems());
											}
										}
										treeItems = join(treeItems,
												ti3.getItems());
									}
								}
								treeItems = join(treeItems, ti2.getItems());
							}
						}
						treeItems = join(treeItems, ti1.getItems());
					}
				}
				for (TreeItem ti : treeItems) {
					i = ((Node) ti.getData()).getIdPosition();
					if (i == node.getIdParent()) {
						item = new TreeItem(ti, SWT.NONE);
						item.setText(node.getName());
						item.setData(node);
						break;
					}
				}
			}
		}
		tree.pack();
		return tree;
	}

	/**
	 * refresh the browser with the selected parametrer
	 * @param nid: -Long- code of the xml book
	 * @param chapter: -String- name of the chapter
	 */
	public void refreshBrowser(long nid, String chapter) {
		PrintStream out = null;
		try {
			out = new PrintStream(System.out, true, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	    out.println("Open book " + nid + ".xml, chapter: " + chapter);
		String sXml = nid + ".xml";
		String sXsd = Utils.LOC_XSD_BOOK;
		String sZip = Utils.LOC_ZIP;
		Map<String, String> params = new HashMap<String, String>();
		params.put("chapter", chapter);
		String listPids = Utils.getListPids();
		params.put("listPids", listPids);
		XmlTransformBuffered xt = new XmlTransformBuffered(sZip, sXml, sXsd, params);
		try {
			this.browser.setText(xt.getResult());
			this.browser.setData("nid", nid);
			this.browser.setData("chapter", chapter);
		} catch (XmlTransformException e) {
			this.browser.setText(e.toString());
			this.browser.setData("nid", new Long(0));
			this.browser.setData("chapter", "");
			e.printStackTrace();
		}
	}

}
