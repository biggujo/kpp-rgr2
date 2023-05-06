package DataSheetTableBean.Panels;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private final JPanel panel;
    private final JButton addButton;
    private final JButton delButton;

    public ButtonPanel() {
        Font defaultFont = new Font("Courier New", Font.PLAIN, 12);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton = new JButton("Add (+)");
        addButton.setFont(defaultFont);
        panel.add(addButton);

        delButton = new JButton("Remove (-)");
        delButton.setFont(defaultFont);
        panel.add(delButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDelButton() {
        return delButton;
    }
}
