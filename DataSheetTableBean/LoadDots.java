package DataSheetTableBean;

import DataSheetTableBean.DataSheet.DataSheet;
import DataSheetTableBean.DataSheet.DataSheetXMLLoader;

public class LoadDots {
    private static final String FILE_NAME_XML = "output.xml";

    public static void main(String[] args) {
        DataSheet dataSheet = DataSheetXMLLoader.parseDataSheetFromXML(FILE_NAME_XML);

        dataSheet.getDataArrayList().forEach(System.out::println);
    }
}
