package DataSheetTableBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.Panels.ButtonPanel;
import DataSheetTableBean.Panels.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JPanel panel;

    MainPanel() {
        panel = new JPanel(new BorderLayout(1, 1));
        TablePanel dataSheetTablePanel = new TablePanel();
        for (int i = 0; i < 100; i++) {
            dataSheetTablePanel.getTableModel().add(new Data("123", 1, 2));
        }
        dataSheetTablePanel.revalidate();

        panel.add(new ButtonPanel().getPanel(), BorderLayout.SOUTH);
        panel.add(dataSheetTablePanel.getScrollPanel(), BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
