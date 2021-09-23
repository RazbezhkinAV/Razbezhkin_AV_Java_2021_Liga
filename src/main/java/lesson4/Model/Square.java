package lesson4.Model;

public class Square extends Shape implements Shape2D {
    private Double x;
    private Double y;

    public Square(Double x, Double y, Double edgeSize) {
        super(edgeSize);
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getPerimeter() {
        return Math.pow(super.getEdgeSize(), 2);
    }
}
