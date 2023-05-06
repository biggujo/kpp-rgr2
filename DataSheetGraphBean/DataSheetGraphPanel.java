package DataSheetGraphBean;

import DataSheetTableBean.DataSheet.Data;
import DataSheetTableBean.DataSheet.DataSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serial;
import java.util.Comparator;
import java.util.Objects;

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

        return Objects.requireNonNull(dataSheet.getDataArrayList().stream()
                .min(Comparator.comparingDouble(Data::getX))
                .orElse(null)).getX();
    }

    private double getMaxX() {
        if (dataSheet == null) {
            return 0;
        }

        return Objects.requireNonNull(dataSheet.getDataArrayList().stream()
                .max(Comparator.comparingDouble(Data::getX))
                .orElse(null)).getX();
    }

    private double getMinY() {
        if (dataSheet == null) {
            return 0;
        }

        return Objects.requireNonNull(dataSheet.getDataArrayList().stream()
                .min(Comparator.comparingDouble(Data::getY))
                .orElse(null)).getY();
    }

    private double getMaxY() {
        if (dataSheet == null) {
            return 0;
        }

        return Objects.requireNonNull(dataSheet.getDataArrayList().stream()
                .max(Comparator.comparingDouble(Data::getY))
                .orElse(null)).getY();
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


    public void showGraph(Graphics2D gr) {
        // Save width and height
        double width = getWidth();
        double height = getHeight();

        // Get min and max values
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

        // Create dashes for the field
        float[] dashPattern = {5, 5};
        gr.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0));
        gr.setFont(new Font("SansSerif", Font.BOLD, 10));

        // Create X axis layout markup
        double xStep = calcStepX();
        for (double dx = xStep; dx < xMax; dx += xStep) {
            double x = x0 + dx * xScale;

            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(x, 0, x, height));

            gr.setPaint(Color.BLACK);
            gr.drawString(String.valueOf(Math.round(dx / xStep) * xStep), (int) x + 2, 10);
        }
        for (double dx = -xStep; dx >= xMin; dx -= xStep) {
            double x = x0 + dx * xScale;

            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(x, 0, x, height));

            gr.setPaint(Color.BLACK);
            gr.drawString(String.valueOf(Math.round(dx / xStep) * xStep), (int) x + 2, 10);
        }

        // Create Y axis layout markup
        double yStep = calcStepY();
        for (double dy = yStep; dy < yMax; dy += yStep) {
            double y = y0 - dy * yScale;

            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(0, y, width, y));

            gr.setPaint(Color.BLACK);
            gr.drawString(String.valueOf(Math.round(dy / yStep) * yStep), 2, (int) y - 2);
        }

        for (double dy = -yStep; dy >= yMin; dy -= yStep) {
            double y = y0 - dy * yScale;

            gr.setPaint(Color.LIGHT_GRAY);
            gr.draw(new Line2D.Double(0, y, width, y));

            gr.setPaint(Color.BLACK);
            gr.drawString(String.valueOf(Math.round(dy / yStep) * yStep), 2, (int) y - 2);
        }

        // Create axis
        gr.setPaint(Color.BLACK);
        gr.setStroke(new BasicStroke(2.0f));

        gr.draw(new Line2D.Double(x0, 0, x0, height));
        gr.draw(new Line2D.Double(0, y0, width, y0));

        gr.drawString("x", (int) width - 10, (int) y0 - 2);
        gr.drawString("y", (int) x0 + 2, 10);

        // Return if no data storage
        if (dataSheet == null) {
            return;
        }

        if (!(dataSheet.size() == 0)) {
            for (int i = 0; i < dataSheet.size(); i++) {
                double x = x0 + (dataSheet.getData(i).getX() * xScale);
                double y = y0 - (dataSheet.getData(i).getY() * yScale);
                gr.setColor(Color.WHITE);
                gr.fillOval((int) (x - 5 / 2), (int) (y - 5 / 2), 5, 5);
                gr.setColor(Color.BLUE);
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
                gr.draw(new Line2D.Double(xOld, yOld, x, y));
                xOld = x;
                yOld = y;
            }
        }

        // Load previous data
        gr.setPaint(oldColor);
        gr.setStroke(oldStroke);
        gr.setFont(oldFont);
    }

    private double calcStepX() {

        return Math.ceil(Math.max(2, Math.abs(getMaxX() - getMinX()) / 2));
    }

    private double calcStepY() {
        return Math.ceil(Math.max(2, Math.abs(getMaxY() - getMinY()) / 2));
    }

    public JPanel getPanel() {
        return this;
    }

}
