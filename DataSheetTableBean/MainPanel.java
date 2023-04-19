package DataSheetTableBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.DataSheet.DataSheet;
import DataSheetTableBean.Panels.ButtonPanel;
import DataSheetTableBean.Panels.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JPanel panel;

    MainPanel() {
        TablePanel dataSheetTablePanel = new TablePanel();

        DataSheet dataSheet = new DataSheet();
        dataSheet.add(new Data("123", 1, 2));
        dataSheetTablePanel.getTableModel().setDataSheet(dataSheet);

        dataSheetTablePanel.getScrollPanel().setBackground(Color.BLACK);

        panel = new JPanel(new BorderLayout(1, 1));

        panel.add(new ButtonPanel().getPanel(), BorderLayout.SOUTH);
        panel.add(dataSheetTablePanel.getScrollPanel(), BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
