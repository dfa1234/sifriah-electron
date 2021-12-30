package df.sifriah.xslTransform;

public class XmlTransformException extends Exception {

	private static final long serialVersionUID = 6306628147787003115L;
	private String typeOfException = "";

	public XmlTransformException(String string) {
		typeOfException = string;
	}

	@Override
	public String toString() {
		return "Type of Exception: " + typeOfException + " " + super.toString();
	}

}
