package del.ac.id.yosepri;

public class UangKurangEksepsi extends Exception {
    private double uang;

    public UangKurangEksepsi(double uang){
        this.uang = uang;
    }
    public double getUang() {
        return uang;
    }

    public void setUang(double uang) {
        this.uang = uang;
    }
}
