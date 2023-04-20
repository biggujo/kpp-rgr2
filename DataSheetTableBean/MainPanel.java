package DataSheetTableBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.Panels.ButtonPanel;
import DataSheetTableBean.Panels.TablePanel;
import DataSheetTableBean.TableModels.TableModel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JPanel panel;
    private final JTable table;
    private final TableModel tableModel;

    MainPanel() {
        panel = new JPanel(new BorderLayout(1, 1));
        TablePanel dataSheetTablePanel = new TablePanel();
        ButtonPanel buttonPanel = new ButtonPanel();
        
        dataSheetTablePanel.revalidate();

        panel.add(buttonPanel.getPanel(), BorderLayout.SOUTH);
        panel.add(dataSheetTablePanel.getScrollPanel(), BorderLayout.CENTER);

        table = dataSheetTablePanel.getTable();
        tableModel = dataSheetTablePanel.getTableModel();

        buttonPanel.getAddButton().addActionListener(e -> {
            addTableRow();
            tableModel.fireDataSheetChange();
        });

        buttonPanel.getDelButton().addActionListener(e -> {
            System.out.println(tableModel.getDataSheet().size());
            if (tableModel.getRowCount() > 1) {
                removeTableRow();
                tableModel.fireDataSheetChange();
                return;
            }

            if (tableModel.getRowCount() == 1) {
                System.out.println(tableModel.getDataSheet().getData(0));
                tableModel.getDataSheet().getData(0).setIndex("");
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
        table.revalidate();
    }

    private void removeTableRow() {
        tableModel.setRowCount(tableModel.getRowCount() - 1);
        tableModel.getDataSheet().remove(tableModel.getDataSheet().size() - 1);
        table.revalidate();
    }

    public JPanel getPanel() {
        return panel;
    }
}
