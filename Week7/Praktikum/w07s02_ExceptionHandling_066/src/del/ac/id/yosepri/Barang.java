package del.ac.id.yosepri;

public class Barang {
    private String id;
    private int stok;
    private double harga;
    private String nama;

    public Barang(String id, int stok, double harga, String nama) {
        this.id = id;
        this.stok = stok;
        this.harga = harga;
        this.nama = nama;
    }
    public Barang(String id){
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getHarga() {
        return harga;
    }

    public void kurangiStok(int jlh){
        if(jlh > stok){
            jlh -= stok;
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
