package DataSheetTableBean;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();

            // TODO: remove
            // frame.setLayout(new BorderLayout(20, 0));
            frame.setSize(300, 400);

            frame.add(new MainPanel().getPanel());

            frame.setTitle("Rgr2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
