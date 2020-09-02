package introtooop;

public class Manusia {
    private String nama, alamat;
    private int usia;

    public Manusia(){};

    public void setNama(String value){
        nama = value;
    }

    public void setAlamat(String value){
        alamat = value;
    }

    public void setUsia(int value){
        usia = value;
    }

    public String getNama(){
        return nama;
    }

    public String getAlamat(){
        return alamat;
    }
    public int getUsia(){
        return usia;
    }

    protected void cetakInformasi(Manusia p){
        System.out.println("Nama:\t" + p.nama);
        System.out.println("Usia:\t"+ p.usia);
        System.out.println("Alamat:\t"+ p.alamat);
    }
}
