package Points;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Point2D extends Point implements Comparable<Point2D> {

    public Point2D(double x, double y) {
        super(2);
        setCoord(1, x);
        setCoord(2, y);
    }

    public Point2D() {
        this(0, 0);
    }

    @Override
    public int compareTo(Point2D givenPoint) {
        return Double.compare(this.getX(), givenPoint.getX());
    }

    public double getX() {
        return getCoord(1);
    }

    public void setX(double newX) {
        setCoord(1, newX);
    }

    public double getY() {
        return getCoord(2);
    }

    public void setY(double newY) {
        setCoord(2, newY);
    }

    public static void main(String[] args) {
        ArrayList<Point2D> point2DArrayList = new ArrayList<>();

        // Set locale
        DecimalFormatSymbols localeUS = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.######", localeUS);

        for (int i = 0; i < 5; i++) {
            double randX = Double.parseDouble(decimalFormat.format(Math.random()));
            double randY = Double.parseDouble(decimalFormat.format(Math.random()));

            point2DArrayList.add(new Point2D(randX, randY));
        }

        System.out.println("Unsorted:");
        point2DArrayList.forEach(System.out::println);

        Collections.sort(point2DArrayList);
        System.out.println("Sorted:");
        point2DArrayList.forEach(System.out::println);
    }
}
