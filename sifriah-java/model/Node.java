package df.sifriah.model;

public class Node {
	@Deprecated
	public static final int NAME_AUTO = 4;
	@Deprecated
	public static final int NAME_NID = 3;
	@Deprecated
	public static final int NAME_NOC_NID = 2; // Use Type Leaf instead
	@Deprecated
	public static final int NAME_ONLY = 1; // Use Type Node instead
	public static final int TYPE_LEAF = 2;
	public static final int TYPE_NODE = 1;
	public static final int TYPE_ROOT = 0;
	@Deprecated
	public static final int XMLIDPATTERN = 5;

	// private String xmlidpattern;
	private int auto;
	private int idParent;
	private int idPosition;
	private String name;
	private long nid;
	private int noc;
	private int nodeType;

	public Node() {
		super();
		this.auto = 0;
		this.idParent = 0;
		this.idPosition = 0;
		this.name = "";
		this.nid = 0;
		this.noc = 0;
		this.nodeType = 0;
	}

	public Node(int auto, int idParent, int idPosition, String name, long nid,
			int noc, int nodeType) {
		super();
		this.auto = auto;
		this.idParent = idParent;
		this.idPosition = idPosition;
		this.name = name;
		this.nid = nid;
		this.noc = noc;
		this.nodeType = nodeType;
	}

	public int getAuto() {
		return auto;
	}

	public int getIdParent() {
		return idParent;
	}

	public int getIdPosition() {
		return idPosition;
	}

	public String getName() {
		return name;
	}

	public long getNid() {
		return nid;
	}

	public int getNoc() {
		return noc;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNid(long nid) {
		this.nid = nid;
	}

	public void setNoc(int noc) {
		this.noc = noc;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public String toString() {
		return "Node [auto=" + auto + ", idParent=" + idParent
				+ ", idPosition=" + idPosition + ", name=" + name
				+ ", nodeType=" + nodeType + ", nid=" + nid + ", noc=" + noc
				+ "]";
	}

}
