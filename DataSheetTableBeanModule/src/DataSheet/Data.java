package DataSheet;

import Points.Point2D;

import java.util.Objects;

public class Data implements Comparable<Data> {
    private final Point2D point2D;

    public Data() {
        this(0, 0);
    }

    public Data(double x, double y) {
        this.point2D = new Point2D();
        this.point2D.setX(x);
        this.point2D.setY(y);
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
        return Objects.equals(point2D, data.point2D);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point2D);
    }

    @Override
    public int compareTo(Data dataEntity) {
        return Double.compare(this.getX(), dataEntity.getX());
    }

    @Override
    public String toString() {
        return "Data{" +
                "point2D=" + point2D +
                '}';
    }
}
