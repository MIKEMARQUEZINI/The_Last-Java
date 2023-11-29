package prosper.thelast.model;

public class Refrigerator extends Product {
    private boolean zero;

    public Refrigerator(boolean zero) {
        this.zero = zero;
    }

    public boolean isZero() {
        return zero;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }
}
