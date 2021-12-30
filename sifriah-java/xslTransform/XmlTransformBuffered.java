package df.sifriah.xslTransform;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlTransformBuffered {

	private Map<String, String> params = null;
	private String result = null;
	private String xml = null;
	private String xsl = null;
	private String zip = null;

	public XmlTransformBuffered(String zip, String xml, String xsl,
			Map<String, String> params) {
		this.xml = xml;
		this.xsl = xsl;
		this.params = params;
		this.zip = zip;
	}

	public String getResult() throws XmlTransformException {

		InputStream is = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(this.zip);
		} catch (IOException e2) {
			throw new XmlTransformException("Error with zip file (not found)");
		}
		ZipEntry entry = zipFile.getEntry(this.xml);
		try {
			is = zipFile.getInputStream(entry);
		} catch (IOException e2) {
			throw new XmlTransformException("Error with zip file (not read)");
		}

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		StreamSource xslSource = new StreamSource(xsl);

		try {
			transformer = tFactory.newTransformer(xslSource);
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
			throw new XmlTransformException("TransformerConfigurationException");
		}

		for (String key : params.keySet()) {
			transformer.setParameter(key, params.get(key));
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StreamResult st = new StreamResult(baos);
		StreamSource ss = new StreamSource(is);
		try {
			transformer.transform(ss, st);
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new XmlTransformException("TransformerException");
		}

		try {
			result = baos.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new XmlTransformException("UnsupportedEncodingException");
		}

		return result;
	}
}