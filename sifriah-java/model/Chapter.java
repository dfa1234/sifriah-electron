package df.sifriah.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
	private String book;
	private String chap;
	private List<String[]> paragraphes;
	private List<String> pid;

	public Chapter() {
		super();
		this.book = "";
		this.chap = "";
		this.paragraphes = new ArrayList<String[]>();
		this.pid = new ArrayList<String>();
	}

	public String getBookName() {
		return book;
	}

	public String getChapterName() {
		return chap;
	}

	public List<String[]> getParagraphes() {
		return paragraphes;
	}

	public List<String> getPid() {
		return pid;
	}

	public void setBookName(String book) {
		this.book = book;
	}

	public void setChapterName(String chap) {
		this.chap = chap;
	}

	public void setParagraphes(List<String[]> paragraphes) {
		this.paragraphes = paragraphes;
	}

	public void setPid(List<String> pid) {
		this.pid = pid;
	}

	public String toHTML() {
		String r;
		r = "<htlm><head><title>" + book + " - " + chap + "</title>";
		r += "<style type=\"text/css\"> body {text-align: right}</style>";
		r += "</head><body>";
		r += "<H2>" + book + "</H2><br>";
		r += "<H3>" + chap + "</H3>";
		if (paragraphes != null) {
			for (String[] p : paragraphes) {
				if (p != null && p.length > 0)
					r += "<br><p dir=LTR><b>" + p[0] + "</b>		" + p[1] + "</p>";
			}
		}
		r += "</body></html>";
		return r;
	}

	@Override
	public String toString() {
		String r = "Chapter [Book=" + book + ", Chapter=" + chap + "]";
		if (paragraphes != null) {
			for (String[] p : paragraphes) {
				if (p != null && p.length > 0)
					r = r + "\n" + p[0] + "\t" + p[1];
			}
		}
		return r;
	}
}
