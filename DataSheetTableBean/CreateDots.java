package DataSheetTableBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.DataSheet.DataSheet;
import XML.DataSheetXMLSaver;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateDots {
    private static final String FILE_NAME_XML = "output.xml";

    public static void main(String[] args) {
        DataSheet dataSheet = new DataSheet();

        dataSheet.add(new Data(1, 2));
        dataSheet.add(new Data(2, 3));

        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(FILE_NAME_XML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSheetXMLSaver dataSheetXMLSaver = new DataSheetXMLSaver(dataSheet);
        dataSheetXMLSaver.buildDOMTree();
        dataSheetXMLSaver.saveDocumentToXML(fileOutputStream);
    }
}
