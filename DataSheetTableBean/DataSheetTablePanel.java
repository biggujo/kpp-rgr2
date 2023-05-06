package DataSheetTableBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.Panels.ButtonPanel;
import DataSheetTableBean.Panels.TablePanel;
import DataSheetTableBean.DataSheet.DataSheetTableModel;

import javax.swing.*;
import java.awt.*;

public class DataSheetTablePanel extends JPanel {
    private final JPanel panel;
    private final JTable table;
    private final DataSheetTableModel tableModel;

    DataSheetTablePanel() {
        panel = new JPanel(new BorderLayout(1, 1));
        TablePanel dataSheetTablePanel = new TablePanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        dataSheetTablePanel.revalidate();

        panel.add(buttonPanel.getPanel(), BorderLayout.SOUTH);
        panel.add(dataSheetTablePanel.getScrollPanel(), BorderLayout.CENTER);

        table = dataSheetTablePanel.getTable();
        tableModel = dataSheetTablePanel.getTableModel();

        addTableRow();
        tableModel.fireDataSheetChange();

        buttonPanel.getAddButton().addActionListener(e -> {
            addTableRow();

            table.revalidate();
            table.repaint();

            tableModel.fireDataSheetChange();
        });

        buttonPanel.getDelButton().addActionListener(e -> {
            if (tableModel.getRowCount() > 1) {
                removeTableRow();

                table.revalidate();
                table.repaint();

                tableModel.fireDataSheetChange();
                return;
            }

            if (tableModel.getRowCount() == 1) {
                tableModel.getDataSheet().getData(0).setX(0);
                tableModel.getDataSheet().getData(0).setY(0);

                table.revalidate();
                table.repaint();

                tableModel.fireDataSheetChange();
            }
        });
    }

    private void addTableRow() {
        tableModel.setRowCount(tableModel.getRowCount() + 1);
        tableModel.getDataSheet().add(new Data());
    }

    private void removeTableRow() {
        tableModel.setRowCount(tableModel.getRowCount() - 1);
        tableModel.getDataSheet().remove(tableModel.getDataSheet().size() - 1);
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();

            frame.setSize(300, 400);

            frame.add(new DataSheetTablePanel().getPanel());

            frame.setTitle("Rgr2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
