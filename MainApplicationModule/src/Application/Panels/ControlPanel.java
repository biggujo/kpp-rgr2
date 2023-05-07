package Application.Panels;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private final JPanel panel;
    private final JButton loadFromXMLButton;
    private final JButton saveToXMLButton;
    private final JButton loadExampleButton;
    private final JButton clearTableButton;
    private final JButton shutdownButton;

    public ControlPanel() {
        Font defaultFont = new Font("Courier New", Font.PLAIN, 12);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        loadFromXMLButton = new JButton("Load");
        loadFromXMLButton.setFont(defaultFont);

        saveToXMLButton = new JButton("Save");
        saveToXMLButton.setFont(defaultFont);

        loadExampleButton = new JButton("Example");
        loadExampleButton.setFont(defaultFont);

        clearTableButton = new JButton("Clear");
        clearTableButton.setFont(defaultFont);

        shutdownButton = new JButton("Log out");
        shutdownButton.setFont(defaultFont);

        panel.add(loadFromXMLButton);
        panel.add(saveToXMLButton);
        panel.add(loadExampleButton);
        panel.add(clearTableButton);
        panel.add(shutdownButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getLoadFromXMLButton() {
        return loadFromXMLButton;
    }

    public JButton getSaveToXMLButton() {
        return saveToXMLButton;
    }

    public JButton getLoadExampleButton() {
        return loadExampleButton;
    }

    public JButton getClearTableButton() {
        return clearTableButton;
    }

    public JButton getShutdownButton() {
        return shutdownButton;
    }
}
