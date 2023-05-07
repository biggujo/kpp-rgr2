import Panels.ControlPanel;
import DataSheet.Data;
import DataSheet.DataSheet;
import XML.DataSheetXMLLoader;
import XML.DataSheetXMLSaver;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainApplicationFrame extends JFrame {

    private final JPanel panel;
    private final JFrame frame;
    private final DataSheetTablePanel dataSheetTable;
    private final DataSheetGraphPanel dataSheetGraph;
    private final ControlPanel controlPanel;
    private DataSheet dataSheet;

    private final JFileChooser fileChooser = new JFileChooser();

    public MainApplicationFrame() {
        // Set file chooser path
        fileChooser.setCurrentDirectory(new java.io.File("."));

        // Create storage
        dataSheet = new DataSheet();
        dataSheet.add(new Data(0.0, 0.0));

        // Create basic frame and panel
        frame = new JFrame();
        panel = new JPanel(new BorderLayout(1, 1));
        frame.add(panel);

        // Add graph panel
        dataSheetGraph = new DataSheetGraphPanel();
        dataSheetGraph.setDataSheet(dataSheet);

        // Add table panel
        dataSheetTable = new DataSheetTablePanel();
        dataSheetTable.getTableModel().setDataSheet(dataSheet);
        dataSheetTable.getTableModel().addDataSheetChangeListener(e -> {
            dataSheetGraph.revalidate();
            dataSheetGraph.repaint();
        });

        // Add control panel
        controlPanel = new ControlPanel();

        panel.add(dataSheetTable.getPanel(), BorderLayout.WEST);
        panel.add(dataSheetGraph.getPanel(), BorderLayout.EAST);
        panel.add(controlPanel.getPanel(), BorderLayout.SOUTH);

        // Action listeners

        // Load table from XML
        controlPanel.getLoadFromXMLButton().addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();

                dataSheet = DataSheetXMLLoader.parseDataSheetFromXML(fileName);
                dataSheetTable.getTableModel().setDataSheet(dataSheet);

                dataSheetTable.getTable().revalidate();
                dataSheetTable.getTable().repaint();

                dataSheetGraph.setDataSheet(dataSheet);

                JOptionPane.showMessageDialog(null,
                        "Loaded from " + fileName, "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Save table to XML
        controlPanel.getSaveToXMLButton().addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String filename = fileChooser.getSelectedFile().getPath();
                try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                    DataSheetXMLSaver dataSheetXMLSaver = new DataSheetXMLSaver(dataSheet);
                    dataSheetXMLSaver.buildDOMTree();
                    dataSheetXMLSaver.saveDocumentToXML(fileOutputStream);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Write error to " + filename, "Error!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(null,
                        "Saved to " + filename, "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Load example set of dots
        controlPanel.getLoadExampleButton().addActionListener(e -> {
            clearTable();
            dataSheet = new DataSheet();

            // Add some points of y = x ^ 2
            for (double x = -3; x <= 3; x += 0.5) {
                dataSheet.add(new Data(x, Math.pow(x, 2)));
            }

            dataSheetGraph.setDataSheet(dataSheet);
            dataSheetTable.getTableModel().setDataSheet(dataSheet);

            JOptionPane.showMessageDialog(null,
                    "Add y = x ^ 2", "Example load",
                    JOptionPane.INFORMATION_MESSAGE);
        });


        // Clear table
        controlPanel.getClearTableButton().addActionListener(e -> {
            clearTable();

            JOptionPane.showMessageDialog(null,
                    "Table cleared!", "Clear table",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Shutdown the application
        controlPanel.getShutdownButton().addActionListener(b -> frame.dispose());

        // Calculate size of window automatically
        frame.pack();
        frame.setResizable(false);
        frame.setTitle("Rgr2");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void clearTable() {
        dataSheet = new DataSheet();
        dataSheet.add(new Data());

        dataSheetTable.getTableModel().setDataSheet(dataSheet);

        dataSheetTable.getTable().revalidate();
        dataSheetTable.getTable().repaint();

        dataSheetGraph.setDataSheet(dataSheet);
    }
}
