package del.ac.id.yosepri;

public class StokEksepsi extends Exception {
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
