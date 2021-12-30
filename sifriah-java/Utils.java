package df.sifriah;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Utils {

	
	public static Color COLOR_AZURE = new Color(Display.getCurrent(), 224, 238,
			238);
	public static Color COLOR_AZURE2 = new Color(Display.getCurrent(), 220, 230,
			230);
	public static Color COLOR_LIGHTCYAN = new Color(Display.getCurrent(), 224,
			255, 255);
	public static Color COLOR_LIGHTSTEEL = new Color(Display.getCurrent(), 222,
			220, 238);

	public static  String repXml = System.getenv("CONTENTDIR");
	public static  String LOC_ZIP = Utils.repXml+"/xml/Archive.zip";;
	public static  String LOC_PIDS_NAME_PROPS = Utils.repXml+"/txt/pids.properties";
	public static  String LOC_PIDS_STATE_PROPS = Utils.repXml+"/txt/pidsState.properties";
	public static  String LOC_TANACH = Utils.repXml+"/xml/tanach.xml";
	public static  String LOC_XSD_BOOK = Utils.repXml+"/xsl/displaybook.xsl";

	public static Map<String, String> getComments() {
		/*
		FileReader reader = null;
		try {
			reader = new FileReader(LOC_PIDS_NAME_PROPS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = new Properties();
		try {
			props.load(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, String> myMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			myMap.put(key.toString(), props.get(key).toString());
		}
		return myMap;*/
		//////////////////////////////////////////////////////
		Map<String, String> m = new HashMap<String, String>();
		m.put("1", "אונקלוס");
		m.put("2", "ת' יהונתן");
		m.put("3", "רשי תורה");
		m.put("28", "שפתי חכמים");
		m.put("4", "רמב'ן");
		m.put("5", "אבן עזרא");
		m.put("6", "ספורנו");
		m.put("7", "בעל טורים");
		m.put("8", "אור החיים");
		m.put("29", "כלי יקר");
		m.put("10", "מ' דוד");
		m.put("11", "מ' ציון");
		m.put("12", "רלב'ג");
		m.put("30", "מלבי'ם ביאור תוכן");
		m.put("31", "מלבי'ם ביאור המילות");
		m.put("9", "תורה תמימה");
		m.put("14", "ר' עובדיה מברטנורה");
		m.put("15", "תוספות יום טוב");
		m.put("17", "רש'י");
		m.put("18", "תוספות");
		m.put("20", "משנה ברורה");
		m.put("21", "ביאור הלכה");
		m.put("23", "זוהר מתורגם");
		return m;
		
	}

	public static String getListPids() {
		Map<String, Boolean> commentsState = Utils.loadCommentsState();
		String listPids = "";
		for (String pid : commentsState.keySet()) {
			if (commentsState.get(pid)) {
				listPids += " " + pid + " ";
			}
		}
		return listPids;
	}

	public static Map<String, Boolean> loadCommentsState() {
		FileReader reader = null;
		try {
			reader = new FileReader(LOC_PIDS_STATE_PROPS);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = new Properties();
		try {
			props.load(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Boolean> myMap = new HashMap<String, Boolean>();
		for (Object key : props.keySet()) {
			myMap.put(key.toString(), new Boolean(props.get(key).toString()));
		}
		return myMap;
	}

	public static void main(String[] args) {

	}

	public static String readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

	public static void saveCommentsState(Map<String, Boolean> map) {
		try {
			Properties props = new Properties();
			for (String s : map.keySet()) {
				props.setProperty(s, map.get(s).toString());
			}

			File f = new File(LOC_PIDS_STATE_PROPS);
			OutputStream out = new FileOutputStream(f);
			props.store(out, "Pids display:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
