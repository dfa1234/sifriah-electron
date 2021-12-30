package df.sifriah.xmlParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import df.sifriah.model.Node;

@SuppressWarnings("unused")
public class StaXParserTanach {

	static final String AUTO = "auto";
	static final String N = "n";
	static final String NAME = "name";
	static final String NID = "nid";
	static final String NOC = "noc";
	// Liste des balises et attributs
	static final String NODE = "node";

	// @SuppressWarnings("null")
	@SuppressWarnings("unchecked")
	public ArrayList<Node> readChapter(String xmlFile) {

		ArrayList<Node> nodes = new ArrayList<Node>();
		int idPosition = 0;
		int idParent = 0;
		ArrayList<Integer> hierarchy = new ArrayList<Integer>();
		hierarchy.add(idPosition);
		Node node = new Node();

		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader
			InputStream in = new FileInputStream(xmlFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// Boucle de lecture
			while (eventReader.hasNext()) {
				// Avancée de la boucle
				XMLEvent event = eventReader.nextEvent();

				// evaluation de chaque balise de depart
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					// BALISE DE TYPE NODE
					if (startElement.getName().getLocalPart() == (NODE)) {

						node = new Node();
						idPosition++;
						node.setIdPosition(idPosition);
						if (hierarchy.size() > 0)
							node.setIdParent(hierarchy.get(hierarchy.size() - 1));
						// System.out.println("hierarchy + 1");
						hierarchy.add(idPosition);

						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {

							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(NAME)) {
								node.setName(attribute.getValue());
							}
							if (attribute.getName().toString().equals(AUTO)) {
								node.setAuto(Integer.valueOf(attribute
										.getValue()));
							}
							if (attribute.getName().toString().equals(NOC)) {
								node.setNoc(Integer.valueOf(attribute
										.getValue()));
							}
							if (attribute.getName().toString().equals(NID)) {
								node.setNid(Long.valueOf(attribute.getValue()));
							}
						}

						node.setNodeType(hierarchy.size() - 2);

						nodes.add(node);
					}
				}

				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (NODE)) {
						// System.out.println("hierarchy - 1");
						if (hierarchy.size() > 0)
							hierarchy.remove(hierarchy.size() - 1);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return nodes;

	}

}