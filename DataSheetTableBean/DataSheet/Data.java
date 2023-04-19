package DataSheetTableBean.DataSheet;

public class Data {
    private String date;
    private Point2D point2D;

    public Data() {
    }

    public Data(String date, double x, double y) {
        this.date = date;

        this.point2D = new Point2D();
        this.point2D.setX(x);
        this.point2D.setY(y);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getX() {
        return point2D.getX();
    }

    public void setX(double newX) {
        point2D.setX(newX);
    }

    public double getY() {
        return point2D.getY();
    }

    public void setY(double newY) {
        point2D.setY(newY);
    }
}
