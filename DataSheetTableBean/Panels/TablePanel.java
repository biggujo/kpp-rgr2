package DataSheetTableBean.Panels;

import DataSheetTableBean.TableModels.TableModel;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private final JScrollPane scrollPanel;
    private final JTable table;
    private TableModel tableModel;

    public TablePanel() {
        table = new JTable();
        tableModel = new TableModel();
        table.setModel(tableModel);

        scrollPanel = new JScrollPane(table);

//        scrollPanel.setHorizontalScrollBarPolicy(
//                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPanel.setVerticalScrollBarPolicy(
//                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public JScrollPane getScrollPanel() {
        return scrollPanel;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel dataSheetTableModel) {
        this.tableModel = dataSheetTableModel;
        table.revalidate();
    }

    public void revalidate() {
        if (table != null) table.revalidate();
    }
}
