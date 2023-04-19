package DataSheetTableBean.DataSheet;

import java.util.ArrayList;

public class DataSheet {
    private ArrayList<Data> dataArrayList;

    public DataSheet() {
        dataArrayList = new ArrayList<>();
    }

    public DataSheet(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public void add(Data data) {
        dataArrayList.add(data);
    }

    public Data getData(int index) throws ArrayIndexOutOfBoundsException {
        return dataArrayList.get(index);
    }

    public Data setData(int index) throws ArrayIndexOutOfBoundsException {
        return dataArrayList.get(index);
    }

    public ArrayList<Data> getDataArrayList() {
        return dataArrayList;
    }

    public void setDataArrayList(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public int size() {
        return this.dataArrayList.size();
    }
}
