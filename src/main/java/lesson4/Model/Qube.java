package lesson4.Model;

public class Qube extends Shape implements Shape3D {
    private Double x;
    private Double y;
    private Double z;

    public Qube(Double x, Double y, Double z, Double edgeSize) {
        super(edgeSize);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    @Override
    public Double getVolume() {
        return Math.pow(super.getEdgeSize(), 3);
    }
}
