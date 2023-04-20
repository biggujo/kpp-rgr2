package DataSheetTableBean.DataSheet;

import java.util.Objects;

public class Data implements Comparable {
    private String index;
    private Point2D point2D;

    public Data() {
        this("", 0, 0);
    }

    public Data(String index, double x, double y) {
        this.index = index;

        this.point2D = new Point2D();
        this.point2D.setX(x);
        this.point2D.setY(y);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(index, data.index) && Objects.equals(point2D, data.point2D);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, point2D);
    }

    @Override
    public int compareTo(Object dataEntity) {
        return Integer.compare(Integer.parseInt(this.index), Integer.parseInt(((Data) dataEntity).getIndex()));
    }

    @Override
    public String toString() {
        return "Data{" +
                "index='" + index + '\'' +
                ", point2D=" + point2D +
                '}';
    }
}
