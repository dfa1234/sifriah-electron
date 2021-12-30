
function loadXMLDoc(filename) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", filename, false);
    xhttp.send("");
    return xhttp.responseXML;
}

function displayResult() {
    const xml = loadXMLDoc("./archives/170.xml");
    const xsl = loadXMLDoc("./xsl/displaybook1.xsl");

    const xsltProcessor = new XSLTProcessor();
    xsltProcessor.importStylesheet(xsl);
    const resultDocument = xsltProcessor.transformToFragment(xml, document);
    document.getElementById("content").appendChild(resultDocument);
}

displayResult();

