package DataSheet;

import Events.DataSheetChangeEvent;
import Events.DataSheetChangeListener;

import javax.swing.table.AbstractTableModel;
import java.io.Serial;
import java.util.ArrayList;

public class DataSheetTableModel extends AbstractTableModel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String[] columnNames = {"X Value", "Y Value"};

    private final int columnCount;
    private int rowCount;
    private DataSheet dataSheet;

    private final ArrayList<DataSheetChangeListener> listenerArrayList;
    private final DataSheetChangeEvent event;

    public DataSheetTableModel() {
        columnCount = 2;
        rowCount = 0;
        dataSheet = new DataSheet();

        listenerArrayList = new ArrayList<>();
        event = new DataSheetChangeEvent(this);
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            double d;
            if (dataSheet != null) {
                if (columnIndex == 0) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getData(rowIndex).setX(d);
                } else if (columnIndex == 1) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getData(rowIndex).setY(d);
                }
            }

            fireDataSheetChange();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataSheet != null) {
            if (columnIndex == 0)
                return dataSheet.getData(rowIndex).getX();
            else if (columnIndex == 1)
                return dataSheet.getData(rowIndex).getY();
        }
        return null;
    }

    public void fireDataSheetChange() {
        for (DataSheetChangeListener listener : listenerArrayList) {
            listener.dataChanged(event);
        }
    }

    public void addDataSheetChangeListener(DataSheetChangeListener listener) {
        listenerArrayList.add(listener);
    }

    public void removeDataSheetChangeListener(DataSheetChangeListener listener) {
        listenerArrayList.remove(listener);
    }

    public void setRowCount(int rowCount) {
        if (rowCount > 0) {
            this.rowCount = rowCount;
        }
    }

    public DataSheet getDataSheet() {
        return dataSheet;
    }

    public void setDataSheet(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
        rowCount = this.dataSheet.size();

        fireDataSheetChange();
    }


}
