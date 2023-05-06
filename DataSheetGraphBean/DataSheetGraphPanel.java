package DataSheetGraphBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.DataSheet.DataSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serial;
import java.util.Comparator;

public class DataSheetGraphPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private DataSheet dataSheet;
    private boolean isConnected;
    private int deltaX;
    private int deltaY;
    transient private Color color;

    public DataSheetGraphPanel() {
        super();
        initialize();
    }

    private void initialize() {
        isConnected = false;
        deltaX = 5;
        deltaY = 5;
        color = Color.red;
        this.setSize(400, 400);

        this.setPreferredSize(new Dimension(300, 300));
    }

    private double getMinX() {
        if (dataSheet == null) {
            return 0;
        }

        return dataSheet.getDataArrayList().stream()
                .min(Comparator.comparingDouble(Data::getX))
                .get().getX();
    }

    private double getMaxX() {
        if (dataSheet == null) {
            return 0;
        }

        return dataSheet.getDataArrayList().stream()
                .max(Comparator.comparingDouble(Data::getX))
                .get().getX();
    }

    private double getMinY() {
        if (dataSheet == null) {
            return 0;
        }

        return dataSheet.getDataArrayList().stream()
                .min(Comparator.comparingDouble(Data::getY))
                .get().getY();
    }

    private double getMaxY() {
        if (dataSheet == null) {
            return 0;
        }

        return dataSheet.getDataArrayList().stream()
                .max(Comparator.comparingDouble(Data::getY))
                .get().getY();
    }

    public DataSheet getDataSheet() {
        return dataSheet;
    }

    public void setDataSheet(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        showGraph(g2);
    }

    // TODO: rewrite

    public void showGraph(Graphics2D gr) {
        double width = getWidth();
        double height = getHeight();

        double xMin = getMinX() - deltaX;
        double xMax = getMaxX() + deltaX;
        double yMin = getMinY() - deltaY;
        double yMax = getMaxY() + deltaY;

        // Scale and position of coordinates axis
        double xScale = width / (xMax - xMin);
        double yScale = height / (yMax - yMin);
        double x0 = -xMin * xScale;
        double y0 = yMax * xScale;

        // Fill background with white
        Paint oldColor = gr.getPaint();
        gr.setPaint(Color.WHITE);
        gr.fill(new Rectangle2D.Double(0.0, 0.0, width, height));

        // Save some properties
        Stroke oldStroke = gr.getStroke();
        Font oldFont = gr.getFont();

        // Створюємо лінії сітки
        // Сітка для вісі X
        float[] dashPattern = {5, 5};
        gr.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0));
        gr.setFont(new Font("Monospaced", Font.BOLD, 10));

        // Взагалі слід створити метод для обчислення кроку сітки
        double xStep = 1;
        for (double dx = xStep; dx < xMax; dx += xStep) {
            double x = x0 + dx * xScale;
            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(x, 0, x, height));
            gr.setPaint(Color.BLACK);
            gr.drawString(Math.round(dx / xStep) * xStep + "", (int) x + 2, 10);
        }
        for (double dx = -xStep; dx >= xMin; dx -= xStep) {
            double x = x0 + dx * xScale;
            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(x, 0, x, height));
            gr.setPaint(Color.BLACK);
            gr.drawString(Math.round(dx / xStep) * xStep + "", (int) x + 2, 10);
        }

        // Сітка для вісі Y
        // Взагалі слід створити метод для обчислення кроку сітки
        double yStep = 1;
        for (double dy = yStep; dy < yMax; dy += yStep) {
            double y = y0 - dy * yScale;
            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(0, y, width, y));
            gr.setPaint(Color.BLACK);
            gr.drawString(Math.round(dy / yStep) * yStep + "", 2, (int) y - 2);
        }
        for (double dy = -yStep; dy >= yMin; dy -= yStep) {
            double y = y0 - dy * yScale;
            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(0, y, width, y));
            gr.setPaint(Color.BLACK);
            gr.drawString(Math.round(dy / yStep) * yStep + "", 2, (int) y - 2);
        }

        // Axis
        gr.setPaint(Color.BLACK);
        gr.setStroke(new BasicStroke(2.0f));
        gr.draw(new Line2D.Double(x0, 0, x0, height));
        gr.draw(new Line2D.Double(0, y0, width, y0));
        gr.drawString("x", (int) width - 10, (int) y0 - 2);
        gr.drawString("y", (int) x0 + 2, 10);

        // Відображаємо точки, якщо визначено сховище
        if (dataSheet != null) {
            if (!isConnected) {
                for (int i = 0; i < dataSheet.size(); i++) {
                    double x = x0 + (dataSheet.getData(i).getX() * xScale);
                    double y = y0 - (dataSheet.getData(i).getY() * yScale);
                    gr.setColor(Color.white);
                    gr.fillOval((int) (x - 5 / 2), (int) (y - 5 / 2), 5, 5);
                    gr.setColor(color);
                    gr.drawOval((int) (x - 5 / 2), (int) (y - 5 / 2), 5, 5);
                }
            } else {
                gr.setPaint(color);
                gr.setStroke(new BasicStroke(2.0f));
                double xOld = x0 + dataSheet.getData(0).getX() * xScale;
                double yOld = y0 - dataSheet.getData(0).getY() * yScale;
                for (int i = 1; i < dataSheet.size(); i++) {
                    double x = x0 + dataSheet.getData(i).getX() * xScale;
                    double y = y0 - dataSheet.getData(i).getY() * yScale;
                    gr.draw(new Line2D.Double(xOld, yOld, (double) x, y));
                    xOld = x;
                    yOld = y;
                }
            }

            // Відновляємо вихідні значення
            gr.setPaint(oldColor);
            gr.setStroke(oldStroke);
            gr.setFont(oldFont);
        }
    }

    public JPanel getPanel() {
        return this;
    }
}
