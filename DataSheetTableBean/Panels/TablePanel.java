package DataSheetTableBean.Panels;

import DataSheetTableBean.TableModels.TableModel;

import javax.swing.*;

public class TablePanel extends JPanel {
    private final JScrollPane scrollPanel;
    private final JPanel panel;
    private final JTable table;
    private TableModel tableModel;

    public TablePanel() {
        panel = new JPanel();
        scrollPanel = new JScrollPane(panel);

        table = new JTable();
        tableModel = new TableModel();
        table.setModel(tableModel);

        panel.add(table);
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
