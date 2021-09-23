package lesson4.Model;

public abstract class Shape {

    private Double edgeSize;

    public Shape(Double edgeSize) {
        this.edgeSize = edgeSize;
    }

    public double getEdgeSize() {
        return edgeSize;
    }

    public void setEdgeSize(double edgeSize) {
        this.edgeSize = edgeSize;
    }
}
