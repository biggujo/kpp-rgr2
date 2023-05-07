package Panels;

import DataSheet.DataSheetTableModel;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private final JScrollPane scrollPanel;
    private final JTable table;
    private DataSheetTableModel tableModel;

    public TablePanel() {
        table = new JTable();
        tableModel = new DataSheetTableModel();
        table.setModel(tableModel);

        scrollPanel = new JScrollPane(table);

        scrollPanel.setPreferredSize(new Dimension(200, 250));
    }

    public JScrollPane getScrollPanel() {
        return scrollPanel;
    }

    public JTable getTable() {
        return table;
    }

    public DataSheetTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DataSheetTableModel dataSheetTableModel) {
        this.tableModel = dataSheetTableModel;
        table.revalidate();
    }

    public void revalidate() {
        if (table != null) table.revalidate();
    }
}
