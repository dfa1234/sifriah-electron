package df.sifriah.xmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import df.sifriah.Utils;

public class StaXParserChap {

	// Liste des balises et attributs
	static final String CHAP = "chap";
	static final String N = "n";

	// @SuppressWarnings("null")
	@SuppressWarnings("unchecked")
	public ArrayList<String> readChapter(String xmlFile) {

		ArrayList<String> chapters = new ArrayList<String>();

		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader

			ZipFile zipFile=null;
			InputStream is=null;
			try {
				zipFile = new ZipFile(Utils.LOC_ZIP);
				ZipEntry entry = zipFile.getEntry(xmlFile);
				is = zipFile.getInputStream(entry);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			XMLEventReader eventReader = inputFactory.createXMLEventReader(is);

			// Boucle de lecture
			while (eventReader.hasNext()) {
				// Avancée de la boucle
				XMLEvent event = eventReader.nextEvent();

				// evaluation de chaque balise de depart
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					// BALISE DE TYPE CHAP
					if (startElement.getName().getLocalPart() == (CHAP)) {
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(N)) {
								chapters.add(attribute.getValue());
							}
						}
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return chapters;

	}

}