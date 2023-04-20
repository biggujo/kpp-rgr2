package DataSheetTableBean.DataSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DataSheetXMLLoader {
    public static DataSheet parseDataSheetFromXML(String filename) {
        DataSheet dataSheet = new DataSheet();
        Document document = parse(filename);

        NodeList rowsElement = document.getElementsByTagName("data");

        for (int i = 0; i < rowsElement.getLength(); i++) {
            Node node = rowsElement.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element nodeAsElement = (Element) node;

            String index = nodeAsElement.getElementsByTagName("index").item(0).getTextContent();
            int x = Integer.parseInt(nodeAsElement.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(nodeAsElement.getElementsByTagName("y").item(0).getTextContent());

            Data data = new Data(index, x, y);
            dataSheet.add(data);
        }

        return dataSheet;
    }

    private static Document parse(String filename) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        File xmlFile = new File(filename);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
