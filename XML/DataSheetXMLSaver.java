package XML;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.DataSheet.DataSheet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataSheetXMLSaver {
    private DataSheet dataSheet;
    private Document document = null;

    public DataSheetXMLSaver() {
    }

    public DataSheetXMLSaver(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
    }

    public void buildDOMTree() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            document.setXmlStandalone(true);

            // Create <data>
            Element outputElement = document.createElement("data");
            document.appendChild(outputElement);

            ArrayList<Data> dataArrayList = dataSheet.getDataArrayList();
            for (Data d : dataArrayList) {
                Element dataElement = document.createElement("point");
                outputElement.appendChild(dataElement);

                Element coordXElement = document.createElement("x");
                coordXElement.setTextContent(String.valueOf(d.getX()));
                dataElement.appendChild(coordXElement);

                Element coordYElement = document.createElement("y");
                coordYElement.setTextContent(String.valueOf(d.getY()));
                dataElement.appendChild(coordYElement);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDocumentToXML(OutputStream outputStream) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;

        // Create transformer
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        // Save to XML with transformer
        try {
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
